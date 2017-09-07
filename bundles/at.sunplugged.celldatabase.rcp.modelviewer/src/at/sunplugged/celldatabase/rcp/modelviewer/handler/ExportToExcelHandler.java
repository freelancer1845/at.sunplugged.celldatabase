
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import java.util.List;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import at.sunplugged.celldatabase.excelwriter.api.ExcelOutputHelper;
import datamodel.CellResult;

public class ExportToExcelHandler {
	@Execute
	public void execute(@Named("TreeViewer") TreeViewer viewer, Shell shell) {

		FileDialog fd = new FileDialog(shell, SWT.SAVE);
		String fileName = fd.open();
		if (fileName != null) {
			IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();
			@SuppressWarnings("unchecked")
			List<CellResult> cellResults = selection.toList();

			ExcelOutputHelper helper = new ExcelOutputHelper(cellResults, fileName);
			helper.execute();
		}
	}

	@SuppressWarnings("unchecked")
	@CanExecute
	public boolean canExecute(@Named("TreeViewer") TreeViewer viewer) {

		IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();

		return selection.toList().stream().allMatch(arg -> (arg instanceof CellResult));

	}

}