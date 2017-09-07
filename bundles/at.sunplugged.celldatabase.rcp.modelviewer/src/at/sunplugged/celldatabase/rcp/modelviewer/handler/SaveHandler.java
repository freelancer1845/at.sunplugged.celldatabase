
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ISaveHandler;

public class SaveHandler {
	@Execute
	public void execute(EPartService partService, ISaveHandler saveHandler) {
		MPart viewerPart = partService
				.findPart("at.sunplugged.celldatabase.rcp.modelviewer.partdescriptor.modelviewer");
		saveHandler.save(viewerPart, true);
	}

	@CanExecute
	public boolean canExecute(EPartService partService) {
		MPart viewerPart = partService
				.findPart("at.sunplugged.celldatabase.rcp.modelviewer.partdescriptor.modelviewer");
		if (viewerPart == null) {
			return false;
		}
		if (viewerPart.isDirty()) {
			return true;
		}
		return false;
	}

}