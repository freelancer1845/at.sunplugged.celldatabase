package at.sunplugged.celldatabase.database.ui.compare;

import java.util.List;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import datamodel.DatamodelPackage;

public class DiffDialog extends TitleAreaDialog {

	private final List<Diff> differences;

	private TableViewer viewer;

	public DiffDialog(Shell parentShell, List<Diff> differences) {
		super(parentShell);
		this.differences = differences;
	}

	@Override
	public void create() {
		super.create();
		setTitle("Commit Dialog");
		setMessage("Choose changes that will be committed...");
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		viewer = new TableViewer(parent);

		createColumns(parent);

		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(differences);
		// set the sorter for the table

		// define layout for the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);

		return super.createDialogArea(parent);
	}

	private void createColumns(Composite parent) {
		String[] titles = { "Type", "Conflict Kind", "DiffKind" };
		int[] bounds = { 100, 100, 100, 100 };

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Diff diff = (Diff) element;
				EObject leftObject = diff.getMatch().getLeft();
				if (leftObject == null) {
					return "NULL";
				}
				if (DatamodelPackage.eINSTANCE.getCellResult().isInstance(leftObject)) {
					return "Cell Result";
				} else if (DatamodelPackage.eINSTANCE.getCellGroup().isInstance(leftObject)) {
					return "Cell Group";
				} else if (DatamodelPackage.eINSTANCE.getCellMeasurementDataSet().isInstance(leftObject)) {
					return "Measurement Data Set";
				} else {
					return "NULL";
				}
			}
		});

		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Diff diff = (Diff) element;
				EObject leftObject = diff.getMatch().getLeft();
				if (leftObject == null) {
					return "NULL";
				}
				if (DatamodelPackage.eINSTANCE.getCellResult().isInstance(leftObject)) {

					return "Cell Result";
				} else if (DatamodelPackage.eINSTANCE.getCellGroup().isInstance(leftObject)) {
					return "Cell Group";
				} else if (DatamodelPackage.eINSTANCE.getCellMeasurementDataSet().isInstance(leftObject)) {
					return "Measurement Data Set";
				} else {
					return "NULL";
				}
			}
		});

		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Diff diff = (Diff) element;
				return diff.getKind().getName();
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

}
