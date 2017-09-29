package at.sunplugged.celldatabase.labviewimport.ui.wizard;

import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class PageOne extends WizardPage {

	private List<LabviewDataFile> dataFiles;

	private TableViewer viewer;

	protected PageOne(List<LabviewDataFile> dataFiles) {
		super("Choose Data Files...");
		this.dataFiles = dataFiles;
		setTitle("Choose Data Files...");
		setDescription("Labview Import Wizard: First Page");
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;

		viewer = new TableViewer(container, SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER) {
			@Override
			public void refresh() {
				if (dataFiles.size() > 0) {
					setPageComplete(true);
				}
				super.refresh();
			}

		};

		createColumns(viewer);

		Table table = viewer.getTable();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new IStructuredContentProvider() {

			@Override
			public Object[] getElements(Object inputElement) {
				@SuppressWarnings("unchecked")
				List<String> input = (List<String>) inputElement;
				return input.toArray();
			}
		});

		viewer.setInput(dataFiles);

		viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Group buttonsGroup = new Group(container, SWT.NONE);
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = false;
		gd.grabExcessVerticalSpace = true;
		gd.horizontalAlignment = SWT.RIGHT;
		gd.verticalAlignment = SWT.FILL;
		buttonsGroup.setLayoutData(gd);
		GridLayout buttonsLayout = new GridLayout(1, true);
		buttonsGroup.setLayout(buttonsLayout);

		Button buttonAdd = new Button(buttonsGroup, SWT.PUSH);
		buttonAdd.setText("Add Files...");
		buttonAdd.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(buttonsGroup.getShell(), SWT.MULTI);
				dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });
				dialog.setFilterNames(new String[] { "Text Files", "All" });
				if (dialog.open() != null) {

					String[] paths = dialog.getFileNames();

					for (String path : paths) {
						dataFiles.add(new LabviewDataFile(Paths.get(dialog.getFilterPath(), path).toString(), path));

					}
					viewer.refresh();
				}
			}
		});

		Label seperator = new Label(buttonsGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		seperator.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));

		Button setAreaButton = new Button(buttonsGroup, SWT.PUSH);
		setAreaButton.setText("Set Area selected");
		setAreaButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
		setAreaButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {

				IStructuredSelection selection = viewer.getStructuredSelection();
				if (selection.isEmpty() == false) {

					DoubleInputDialog dialog = new DoubleInputDialog(buttonsGroup.getShell(), "Set Area...",
							"Input area to set...", 0D);

					if (dialog.open() == Window.OK) {
						selection.iterator()
								.forEachRemaining(data -> ((LabviewDataFile) data).setArea(dialog.getValue()));
						viewer.refresh();
					}

				}

			}

		});
		Button setPowerInputButton = new Button(buttonsGroup, SWT.PUSH);
		setPowerInputButton.setText("Set PowerInput selected");
		setPowerInputButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
		setPowerInputButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {

				IStructuredSelection selection = viewer.getStructuredSelection();
				if (selection.isEmpty() == false) {

					DoubleInputDialog dialog = new DoubleInputDialog(buttonsGroup.getShell(), "Set PowerInput...",
							"PowerInput to set...", 0D);

					if (dialog.open() == Window.OK) {
						selection.iterator()
								.forEachRemaining(data -> ((LabviewDataFile) data).setPowerInput(dialog.getValue()));
						viewer.refresh();
					}

				}

			}
		});

		Label seperator2 = new Label(buttonsGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		seperator2.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));

		Button buttonRemove = new Button(buttonsGroup, SWT.PUSH);
		buttonRemove.setText("Remove selected");
		buttonRemove.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
		buttonRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = viewer.getStructuredSelection();
				if (selection.size() > 0) {
					@SuppressWarnings("unchecked")
					Iterator<LabviewDataFile> iterator = selection.iterator();
					iterator.forEachRemaining(dataFile -> dataFiles.remove(dataFile));
					viewer.refresh();
				}
			}
		});

		setControl(container);
		setPageComplete(false);
	}

	private void createColumns(TableViewer viewer2) {

		String[] titles = { "Name", "Area [cm^2]", "Power Input" };
		int[] bounds = { 150, 80, 80 };

		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		createTableColumn(column, titles[0], bounds[0]);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				LabviewDataFile data = (LabviewDataFile) element;
				return data.getName();
			}
		});

		column = new TableViewerColumn(viewer, SWT.NONE);
		createTableColumn(column, titles[1], bounds[1]);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				LabviewDataFile data = (LabviewDataFile) element;
				if (data.getArea() == null) {
					return "NaN";
				}
				return String.format("%s", data.getArea());
			}
		});
		column.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new TextCellEditor((Composite) viewer.getControl());
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected Object getValue(Object element) {
				if (((LabviewDataFile) element).getArea() != null) {
					return ((LabviewDataFile) element).getArea().toString();
				} else {
					return "NaN";
				}

			}

			@Override
			protected void setValue(Object element, Object value) {
				try {
					((LabviewDataFile) element).setArea(Double.valueOf(value.toString()));
					viewer.update(element, null);
				} catch (NumberFormatException e) {
					((LabviewDataFile) element).setArea(null);
					viewer.update(element, null);
				}

			}
		});

		column = new TableViewerColumn(viewer, SWT.NONE);
		createTableColumn(column, titles[2], bounds[2]);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				LabviewDataFile data = (LabviewDataFile) element;
				if (data.getPowerInput() == null) {
					return "NaN";
				}
				return String.format("%s", data.getPowerInput());
			}
		});

		column.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new TextCellEditor((Composite) viewer.getControl());
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected Object getValue(Object element) {
				if (((LabviewDataFile) element).getPowerInput() != null) {
					return ((LabviewDataFile) element).getPowerInput().toString();
				} else {
					return "NaN";
				}

			}

			@Override
			protected void setValue(Object element, Object value) {
				try {
					((LabviewDataFile) element).setPowerInput(Double.valueOf(value.toString()));
					viewer.update(element, null);
				} catch (NumberFormatException e) {
					((LabviewDataFile) element).setPowerInput(null);
					viewer.update(element, null);
				}

			}
		});
	}

	private TableColumn createTableColumn(TableViewerColumn viewerColumn, String name, int width) {
		TableColumn column = viewerColumn.getColumn();
		column.setText(name);
		column.setResizable(true);
		column.setMoveable(false);
		column.setWidth(width);
		return column;
	}

}
