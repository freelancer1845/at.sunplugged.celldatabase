package at.sunplugged.celldatabase.rcp.dialogs;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.utils.MatchUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import datamodel.CellMeasurementDataSet;

public class ConfirmRefreshDialog extends TitleAreaDialog {

	private static final String TITLE = "Confirm Refresh";

	private static final String MESSAGE = "Review changes made to local database...";

	private final Image CHECKED = getImageDescriptor("checked.png").createImage();

	private final Image UNCHECKED = getImageDescriptor("unchecked.png").createImage();

	private TableViewer viewer;

	private Map<Diff, Boolean> differencesMap;

	private Comparison comparison;

	public ConfirmRefreshDialog(Shell parentShell, List<Diff> differences, Comparison comparision) {
		super(parentShell);
		this.differencesMap = new LinkedHashMap<>();
		this.comparison = comparision;
		for (Diff diff : differences) {
			differencesMap.put(diff, true);
		}
	}

	@Override
	public void create() {
		super.create();
		setTitle(TITLE);
		setMessage(MESSAGE, IMessageProvider.INFORMATION);
	}

	public List<Diff> getVerifiedDiffs() {
		return differencesMap.entrySet().stream().filter(map -> map.getValue() == true).map(map -> map.getKey())
				.collect(Collectors.toList());
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		container.setLayout(new FillLayout());

		// TreeViewer treeViewer = new CheckboxTreeViewer(container,
		// SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		//
		// treeViewer.setContentProvider(new ITreeContentProvider() {
		//
		// @Override
		// public boolean hasChildren(Object element) {
		//
		// EObject eObject = (EObject) element;
		// if (comparison.getDifferences(eObject).isEmpty() == true) {
		// if (eObject.eContents().isEmpty()) {
		// return false;
		// }
		// } else {
		// return true;
		// }
		//
		// EObject eObject = diff.getMatch().getLeft();
		// return eObject.eContents().isEmpty() == false;
		// }
		//
		// @Override
		// public Object getParent(Object element) {
		// Diff diff = (Diff) element;
		// EObject eObject = diff.getMatch().getLeft();
		// return eObject.eContainmentFeature();
		// }
		//
		// @Override
		// public Object[] getElements(Object inputElement) {
		// List<Diff> differences = (List<Diff>) inputElement;
		// List<CellGroup> groups = new ArrayList<>();
		// for (Diff diff : differences) {
		// EObject root = diff.getMatch().getLeft().eContainmentFeature();
		// while (root instanceof CellGroup == false) {
		// root = root.eContainmentFeature();
		// }
		// groups.add((CellGroup) root);
		//
		// }
		// return groups.toArray();
		// }
		//
		// @Override
		// public Object[] getChildren(Object parentElement) {
		// Diff diff = (Diff) parentElement;
		// EObject eObject = diff.getMatch().getLeft();
		// return eObject.eContents().toArray();
		// }
		// });

		viewer = new TableViewer(container, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		createColumns(container, viewer);
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		viewer.setContentProvider(new IStructuredContentProvider() {

			@Override
			public Object[] getElements(Object inputElement) {
				Set<Diff> diffs = (Set<Diff>) inputElement;
				return diffs.stream().filter(diff -> {
					if (diff.getMatch().getLeft() instanceof CellMeasurementDataSet) {
						return false;
					} else {
						return true;
					}
				}).toArray();

			}
		});
		viewer.setInput(differencesMap.keySet());
		return container;
	}

	private void createColumns(Composite container, TableViewer viewer2) {

		String[] titles = { "Object Label", "Type", "Attribute", "Do" };
		int[] bounds = { 100, 100, 100, 100 };

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Diff diff = (Diff) element;
				EObject eObject = diff.getMatch().getLeft();
				String title = null;
				EAttribute attribute = eObject.eClass().getEAttributes().stream()
						.filter(attr -> attr.getName() == "name").findFirst().orElse(null);
				if (attribute != null) {
					title = (String) eObject.eGet(attribute);
				}

				if (title == null) {
					title = "Unkown";
				}
				return title;
			}
		});

		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				Diff diff = (Diff) element;
				return diff.getKind().toString();
			};
		});

		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				Diff diff = (Diff) element;
				EStructuralFeature feature = MatchUtil.getStructuralFeature(diff);
				return feature.getName();
			};
		});

		col = createTableViewerColumn(titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return null;
			}

			@Override
			public Image getImage(Object element) {
				if (differencesMap.get(element)) {
					return CHECKED;
				} else {
					return UNCHECKED;
				}
			}
		});
		col.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected void setValue(Object element, Object value) {
				differencesMap.put((Diff) element, (boolean) value);
				// if ((boolean) value == true) {
				// List<Diff> childDiffs = comparison.getDifferences(((Diff)
				// element).getMatch().getLeft());
				// ((Diff) element).getMatch().getAllDifferences();
				// System.out.println(Arrays.toString(childDiffs.toArray()));
				// childDiffs.forEach(diff -> differencesMap.put(diff, (boolean) value ==
				// true));
				// viewer.refresh();
				// } else {
				// Iterable<Diff> childDiffs = ((Diff) element).getMatch().getAllDifferences();
				// childDiffs.forEach(diff -> System.out.println(diff.toString()));
				// childDiffs.forEach(diff -> differencesMap.put(diff, (boolean) value));
				// viewer.refresh();
				// viewer.update(element, null);
				// }

			}

			@Override
			protected Object getValue(Object element) {
				return differencesMap.get((Diff) element);
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, int colNumber) {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);

		return viewerColumn;
	}

	private static ImageDescriptor getImageDescriptor(String file) {
		// assume that the current class is called View.java
		Bundle bundle = FrameworkUtil.getBundle(ConfirmRefreshDialog.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		return ImageDescriptor.createFromURL(url);
	}

}
