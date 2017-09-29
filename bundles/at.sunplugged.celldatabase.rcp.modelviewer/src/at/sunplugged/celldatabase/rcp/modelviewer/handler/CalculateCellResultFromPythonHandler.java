
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import at.sunplugged.celldatabase.labviewimport.ui.wizard.LabviewImportWizard;
import datamodel.CellGroup;

public class CalculateCellResultFromPythonHandler {
	@Execute
	public void execute(Shell parent, @Named("TreeViewer") TreeViewer treeViewer, ModelDatabaseService databaseService,
			@Named("editingDomain") EditingDomain editingDomain) {

		LabviewImportWizard wizard = new LabviewImportWizard();

		WizardDialog wizardDialog = new WizardDialog(parent, wizard);
		wizardDialog.open();
		Command cmd = AddCommand.create(editingDomain,
				((CellGroup) treeViewer.getStructuredSelection().getFirstElement()), null, wizard.getCellResults());
		editingDomain.getCommandStack().execute(cmd);

	}

	@CanExecute
	public boolean canExecute(@Named("TreeViewer") TreeViewer treeViewer) {
		return HandlerHelper.isSingleSelected(treeViewer.getStructuredSelection(), CellGroup.class);
	}

}