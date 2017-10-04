package at.sunplugged.celldatabase.excelwriter.ui.wizards.summary;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.Wizard;

import datamodel.Database;

public class SummaryExportWizard extends Wizard {

	private static final String WINDOW_TITLE = "Summary Export Wizard";

	private final Database database;

	protected PageOne pageOne;

	public SummaryExportWizard(Database database) {
		super();

		this.database = EcoreUtil.copy(database);
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		pageOne = new PageOne(database);

		addPage(pageOne);
	}

	@Override
	public String getWindowTitle() {
		return WINDOW_TITLE;
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
