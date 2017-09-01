 
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.edit.domain.EditingDomain;

public class RedoHandler {
	@Execute
	public void execute(@Named("editingDomain") EditingDomain editingDomain) {
		editingDomain.getCommandStack().redo();
	}
	
	
	@CanExecute
	public boolean canExecute(@Named("editingDomain") EditingDomain editingDomain) {
		return editingDomain.getCommandStack().canRedo();
	}
		
}