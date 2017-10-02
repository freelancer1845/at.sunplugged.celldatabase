package at.sunplugged.celldatabase.rcp.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class ConfirmRefreshDialog extends TitleAreaDialog {

	private static final String TITLE = "Confirm Refresh";

	private static final String MESSAGE = "Review changes made to local database...";

	private List<Diff> verifiedDiffs;

	private Comparison comparison;

	private CheckboxTreeViewerExtended treeViewer;

	public ConfirmRefreshDialog(Shell parentShell, List<Diff> differences, Comparison comparision) {
		super(parentShell);
		this.comparison = comparision;
	}

	@Override
	public void create() {
		super.create();
		setTitle(TITLE);
		setMessage(MESSAGE, IMessageProvider.INFORMATION);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			verifiedDiffs = Arrays.asList(Arrays.stream(treeViewer.getCheckedElements())
					.filter(object -> object instanceof Diff).toArray(Diff[]::new));
		}
		super.buttonPressed(buttonId);
	}

	public List<Diff> getVerifiedDiffs() {
		return verifiedDiffs;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		container.setLayout(new FillLayout());

		treeViewer = new CheckboxTreeViewerExtended(container,
				SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);

		treeViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				treeViewer.refresh();
			}

		});

		treeViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object element = event.getElement();
				ITreeContentProvider provider = (ITreeContentProvider) treeViewer.getContentProvider();

				if (event.getChecked() == false) {
					handleUnchecked(element, provider);
				} else {
					handleChecked(element, provider);
				}

				treeViewer.refresh();

			}

			private void handleChecked(Object element, ITreeContentProvider provider) {
				List<Object> allChildren = new ArrayList<>();
				if (provider.getParent(element) != null) {
					getAllChildren(provider, provider.getParent(element), allChildren);
				}

				treeViewer.setSubtreeChecked(element, true);

				Object parent = provider.getParent(element);
				while (parent != null) {
					treeViewer.setChecked(parent, true);
					if (allChildrenChecked(treeViewer, parent)) {
						treeViewer.setGrayed(parent, false);
					} else {
						treeViewer.setGrayed(parent, true);
					}

					parent = provider.getParent(parent);
				}
				allChildren.clear();
				getAllChildren(provider, element, allChildren);

				allChildren.forEach(child -> treeViewer.setGrayed(child, false));

				treeViewer.setGrayed(element, false);
			}

			private void handleUnchecked(Object element, ITreeContentProvider provider) {
				treeViewer.setSubtreeChecked(element, false);
				treeViewer.setParentsGrayed(element, true);
			}
		});
		treeViewer.setContentProvider(new MatchTreeContentProvider());

		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(composedAdapterFactory));
		treeViewer.setInput(comparison.getMatches());
		treeViewer.expandToLevel(2);
		treeViewer.setSubtreeChecked(comparison.getMatches().get(0), true);

		return container;
	}

	private boolean allChildrenChecked(CheckboxTreeViewer treeViewer, Object element) {
		List<Object> children = new ArrayList<>();
		getAllChildren((ITreeContentProvider) treeViewer.getContentProvider(), element, children);
		return children.stream().allMatch(child -> treeViewer.getChecked(child));
	}

	private void getAllChildren(ITreeContentProvider provider, Object element, List<Object> children) {
		if (provider.hasChildren(element) == false) {
			return;
		}
		for (Object child : provider.getChildren(element)) {
			children.add(child);
			getAllChildren(provider, child, children);
		}
	}

	private final class MatchTreeContentProvider implements ITreeContentProvider {
		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof Match) {
				EList<Match> submatches = ((Match) element).getSubmatches();

				List<Diff> diffs = new ArrayList<Diff>();
				((Match) element).getAllDifferences().forEach(diffs::add);
				if (diffs.isEmpty() == false) {
					System.out.println("has children");
					return true;
				} else {
					return false;
				}

			} else if (element instanceof Diff) {
				return false;
			}

			return false;
		}

		private Object findParent(Match toFindOf, Match match) {
			if (match.getSubmatches().contains(toFindOf)) {
				return match;
			}
			Object parent = null;
			for (Match submatch : match.getSubmatches()) {
				parent = (findParent(toFindOf, submatch));
				if (parent != null) {
					return parent;
				}
			}
			return null;
		}

		@Override
		public Object getParent(Object element) {

			if (element instanceof Match) {
				for (Match match : comparison.getMatches()) {
					if (match == element) {
						return null;
					}
					Object parent = findParent((Match) element, match);
					return parent;
				}
				return null;
			} else if (element instanceof Diff) {
				return ((Diff) element).getMatch();
			} else {
				return null;
			}
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return ((EList<Match>) inputElement).toArray();
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			List<Object> children = new ArrayList<>();
			if (parentElement instanceof Match) {
				children.addAll(((Match) parentElement).getDifferences());
				children.addAll(((Match) parentElement).getSubmatches().stream().filter(match -> {
					List<Match> allSubmatches = new ArrayList<>();
					match.getAllSubmatches().forEach(allSubmatches::add);
					return allSubmatches.stream().filter(submatch -> submatch.getDifferences().isEmpty() == false)
							.findAny().isPresent();
				}).collect(Collectors.toCollection(ArrayList::new)));
				return children.toArray();
			} else {
				return new Object[0];
			}
		}
	}

	private class CheckboxTreeViewerExtended extends CheckboxTreeViewer {

		public CheckboxTreeViewerExtended(Composite parent, int style) {
			super(parent, style);
		}

		@Override
		public void fireCheckStateChanged(CheckStateChangedEvent event) {
			super.fireCheckStateChanged(event);
		}

	}

}
