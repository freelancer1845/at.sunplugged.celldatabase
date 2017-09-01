
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import datamodel.CellGroup;
import datamodel.CellResult;
import datamodel.DatamodelFactory;

public class AddRawCellResultHandler {
	@Execute
	public void execute(@Named("TreeViewer") TreeViewer treeViewer, ModelDatabaseService databaseService, @Named("editingDomain") EditingDomain editingDomain) {
		IStructuredSelection selection = treeViewer.getStructuredSelection();
		CellGroup cellGroup = (CellGroup) selection.getFirstElement();

		CellResult cellResult = DatamodelFactory.eINSTANCE.createCellResult();

		cellResult.setName("Undefined Cell Result");

		Command cmd = AddCommand.create(editingDomain, cellGroup, null, cellResult);
		editingDomain.getCommandStack().execute(cmd);

	}

	@CanExecute
	public boolean canExecute(@Named("TreeViewer") TreeViewer treeViewer) {
		return HandlerHelper.isSingleSelected(treeViewer.getStructuredSelection(), CellGroup.class);
	}

}