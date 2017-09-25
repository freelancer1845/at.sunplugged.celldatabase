
package at.sunplugged.celldatabase.rcp.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ISaveHandler;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;

public class DisconnectHandler {
	@Execute
	public void execute(ModelDatabaseService databaseService, EPartService partService, ISaveHandler saveHandler,
			MApplication app) {
		MPart viewerPart = partService
				.findPart("at.sunplugged.celldatabase.rcp.modelviewer.partdescriptor.modelviewer");
		MPart[] parts = partService.getParts().toArray(new MPart[] {});
		List<MPart> partsList = new ArrayList<>();
		for (MPart part : parts) {
			if (part.getElementId().equals("at.sunplugged.celldatabase.rcp.modeleditor.partdescriptor.modeleditor")) {
				partsList.add(part);
			}
		}
		partsList.add(viewerPart);
		if (viewerPart != null && viewerPart.isDirty()) {
			saveHandler.save(viewerPart, true);
		}
		databaseService.close();
		for (MPart part : partsList) {
			partService.hidePart(part, true);
		}

	}

	@CanExecute
	public boolean canExecute(ModelDatabaseService databaseService) {
		return databaseService.isConnected();
	}

}