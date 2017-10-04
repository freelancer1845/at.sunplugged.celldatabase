
package at.sunplugged.celldatabase.rcp.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import at.sunplugged.celldatabase.excelwriter.ui.wizards.summary.SummaryExportWizard;

public class ExportSummaryToExcel {
	@Execute
	public void execute(Shell parentShell, ModelDatabaseService databaseService) {

		WizardDialog wizardDialog = new WizardDialog(parentShell,
				new SummaryExportWizard(databaseService.getDatabase()));
		wizardDialog.open();

	}

}