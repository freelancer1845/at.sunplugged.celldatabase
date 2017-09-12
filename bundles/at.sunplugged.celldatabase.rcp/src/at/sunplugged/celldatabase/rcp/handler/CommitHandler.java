
package at.sunplugged.celldatabase.rcp.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;

public class CommitHandler {
	@Execute
	public void execute(ModelDatabaseService databaseService) {
		databaseService.commit();
	}

	@CanExecute
	public boolean canExecute(ModelDatabaseService databaseService) {

		return databaseService.isOpen();
	}

}