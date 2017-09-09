
package at.sunplugged.celldatabase.rcp.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;

public class ConnectToDatabaseHandler {
	@Execute
	public void execute(ModelDatabaseService databaseService, EPartService partService, EModelService modelService,
			MApplication app) {
		if (databaseService.open() == false) {
			return;
		}
		MPart viewPart = partService
				.createPart("at.sunplugged.celldatabase.rcp.modelviewer.partdescriptor.modelviewer");
		MPartStack stack = (MPartStack) modelService.find("at.sunplugged.celldatabase.rcp.partstack.0", app);
		stack.getChildren().add(viewPart);
		partService.activate(viewPart);
	}

	@CanExecute
	public boolean canExecute(ModelDatabaseService databaseService) {
		return !databaseService.isOpen();
	}

}