package at.sunplugged.celldatabase.labviewimport.ui.wizard;

import org.eclipse.jface.wizard.Wizard;

public class LabviewImportWizard extends Wizard {

	protected PageOne pageOne;

	public LabviewImportWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		pageOne = new PageOne();
		addPage(pageOne);
	}

	@Override
	public String getWindowTitle() {
		return "Import Labview Data";
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
