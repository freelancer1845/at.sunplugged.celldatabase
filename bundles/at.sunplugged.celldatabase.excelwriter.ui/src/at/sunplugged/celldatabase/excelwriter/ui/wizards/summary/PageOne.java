package at.sunplugged.celldatabase.excelwriter.ui.wizards.summary;

import java.util.Arrays;
import java.util.EventObject;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeViewerBuilder;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeViewerSWTFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import datamodel.CellGroup;
import datamodel.CellResult;
import datamodel.Database;

public class PageOne extends WizardPage {

	private static final String PAGE_NAME = "Page One";

	private static final String PAGE_DESCRIPTION = "Page Description";

	private final Database database;

	private EditingDomain editingDomain;

	protected PageOne(Database database) {
		super(PAGE_NAME);
		setTitle(PAGE_NAME);
		setDescription(PAGE_DESCRIPTION);
		this.database = database;
		editingDomain = new AdapterFactoryEditingDomain(
				new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE),
				new BasicCommandStack());
		editingDomain.createResource("tempResource").getContents().add(this.database);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;

		container.setLayout(layout);

		CheckboxTreeViewer treeViewer = (CheckboxTreeViewer) TreeViewerSWTFactory.fillDefaults(container, database)
				.customizeTree(new TreeViewerBuilder() {

					@Override
					public TreeViewer createTree(Composite parent) {
						TreeViewer treeViewer = new CheckboxTreeViewer(parent);
						treeViewer.setAutoExpandLevel(2);
						return treeViewer;
					}
				}).customizeContentProvider(new ITreeContentProvider() {
					@Override
					public boolean hasChildren(Object element) {
						if (element instanceof Database) {
							return ((Database) element).getCellGroups().isEmpty() == false;
						} else if (element instanceof CellGroup) {
							return ((CellGroup) element).getCellResults().isEmpty() == false;
						} else if (element instanceof CellResult) {
							return false;
						}
						return false;
					}

					@Override
					public Object getParent(Object element) {
						if (element instanceof Database) {
							return null;
						} else if (element instanceof CellGroup) {
							return database;
						} else if (element instanceof CellResult) {
							return database.getCellGroups().stream()
									.filter(cellGroup -> cellGroup.getCellResults().contains(element)).findAny()
									.orElse(null);
						}
						return false;
					}

					@Override
					public Object[] getElements(Object inputElement) {
						return ((Database) inputElement).getCellGroups().toArray();
					}

					@Override
					public Object[] getChildren(Object parentElement) {
						if (parentElement instanceof Database) {
							return ((Database) parentElement).getCellGroups().toArray();
						} else if (parentElement instanceof CellGroup) {
							return ((CellGroup) parentElement).getCellResults().toArray();
						}
						return null;
					};
				}).create();

		database.getCellGroups().forEach(group -> treeViewer.setSubtreeChecked(group, true));
		treeViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				CheckboxTreeViewer treeViewer = (CheckboxTreeViewer) event.getSource();
				Object element = event.getElement();
				if (element instanceof CellGroup) {
					treeViewer.setSubtreeChecked(element, event.getChecked());
					treeViewer.setGrayed(element, false);
				} else if (element instanceof CellResult) {
					ITreeContentProvider provider = (ITreeContentProvider) treeViewer.getContentProvider();
					Object parent = provider.getParent(element);
					if (event.getChecked() == true) {
						if (Arrays.stream(provider.getChildren(parent))
								.allMatch(child -> treeViewer.getChecked(child))) {
							treeViewer.setGrayed(parent, false);
						}
					} else {
						if (Arrays.stream(provider.getChildren(parent))
								.anyMatch(child -> treeViewer.getChecked(child))) {
							treeViewer.setGrayed(parent, true);
						} else {
							treeViewer.setChecked(parent, false);
						}
					}

				}

			}
		});

		editingDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {

			@Override
			public void commandStackChanged(EventObject event) {
				treeViewer.refresh();
			}
		});

		setControl(container);
		setPageComplete(false);
	}

}
