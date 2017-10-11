
package at.sunplugged.celldatabase.rcp.handler;

import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import at.sunplugged.celldatabase.sprodaccess.api.SprodAccessService;
import at.sunplugged.celldatabase.sprodaccess.ui.wizard.SprodImportWizard;
import datamodel.CellGroup;

public class ImprotFromSprodHandler {
  @Execute
  public void execute(Shell parentShell, SprodAccessService sprodAccessService,
      @Named(IServiceConstants.ACTIVE_SELECTION) CellGroup group) {
    WizardDialog dialog =
        new WizardDialog(parentShell, new SprodImportWizard(sprodAccessService, group));
    dialog.open();
  }

  @CanExecute
  public boolean canExecute(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) CellGroup group) {
    return group == null ? false : true;
  }

}
