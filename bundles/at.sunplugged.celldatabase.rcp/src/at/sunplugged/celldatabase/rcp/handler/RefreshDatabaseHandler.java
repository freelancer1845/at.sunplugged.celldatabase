
package at.sunplugged.celldatabase.rcp.handler;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;

public class RefreshDatabaseHandler {
	@Execute
	public void execute(ModelDatabaseService databaseService) {
		Job job = new Job("Refreshing Database...") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {

				databaseService.load();

				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.LONG);
		job.schedule();
	}

	@CanExecute
	public boolean canExecute(ModelDatabaseService databaseService) {
		return databaseService.isOpen();
	}

}