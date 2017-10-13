package at.sunplugged.celldatabase.sprodaccess.ui.wizard;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.wizard.Wizard;
import at.sunplugged.celldatabase.sprodaccess.api.SprodAccessService;
import at.sunplugged.celldatabase.sprodaccess.ui.wizard.internal.PageOne;
import datamodel.CellGroup;

public class SprodImportWizard extends Wizard {

  private static final String WINDOW_TITLE = "Sprod Import Wizard";

  private PageOne pageOne;

  private final SprodAccessService sprodAccessService;

  private final CellGroup cellGroup;

  public SprodImportWizard(SprodAccessService sprodAccessService, CellGroup cellGroup) {
    super();
    this.sprodAccessService = sprodAccessService;
    this.cellGroup = cellGroup;
  }

  @Override
  public void addPages() {
    pageOne = new PageOne(sprodAccessService);

    addPage(pageOne);
  }

  @Override
  public String getWindowTitle() {
    return WINDOW_TITLE;
  }

  @Override
  public boolean performFinish() {
    cellGroup.getCellResults().addAll(pageOne.getSprodResults());
    return true;
  }

}
