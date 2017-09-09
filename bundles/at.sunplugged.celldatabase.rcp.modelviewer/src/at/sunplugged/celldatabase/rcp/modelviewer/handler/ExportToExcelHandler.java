
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import at.sunplugged.celldatabase.excelwriter.api.ExcelOutputHelper;
import datamodel.CellGroup;
import datamodel.CellResult;

public class ExportToExcelHandler {
	@Execute
	public void execute(@Named("TreeViewer") TreeViewer viewer, Shell shell) {

		FileDialog fd = new FileDialog(shell, SWT.SAVE);
		String fileName = fd.open();
		if (fileName != null) {
			IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();
			Set<CellResult> cellResults = new LinkedHashSet<>();
			for (Object selected : selection.toList()) {
				if (selected instanceof CellResult) {
					cellResults.add((CellResult) selected);
				} else if (selected instanceof CellGroup) {
					cellResults.addAll(((CellGroup) selected).getCellResults());
				}
			}

			ExcelOutputHelper helper = new ExcelOutputHelper(new ArrayList<CellResult>(cellResults), fileName);
			helper.execute();
		}
	}

	@SuppressWarnings("unchecked")
	@CanExecute
	public boolean canExecute(@Named("TreeViewer") TreeViewer viewer) {

		IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();
		if (selection.isEmpty()) {
			return false;
		}
		return selection.toList().stream().anyMatch(arg -> (arg instanceof CellResult) || (arg instanceof CellGroup));

	}

}