
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import java.io.File;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import at.sunplugged.database.datareader.DataEvaluationResult;
import at.sunplugged.database.datareader.DataReaderHelper;
import datamodel.CellGroup;

public class CalculateCellResultFromPythonHandler {
	@Execute
	public void execute(Shell parent, @Named("TreeViewer") TreeViewer treeViewer) {
		FileDialog dialog = new FileDialog(parent);
		String filePath = dialog.open();
		DataEvaluationResult result = DataReaderHelper.readAndCalculateFile(new File(filePath));
		((CellGroup) treeViewer.getStructuredSelection().getFirstElement()).getCellResults()
				.add(result.getCellResult());
	}

	@CanExecute
	public boolean canExecute(@Named("TreeViewer") TreeViewer treeViewer) {
		return HandlerHelper.isSingleSelected(treeViewer.getStructuredSelection(), CellGroup.class);
	}

}