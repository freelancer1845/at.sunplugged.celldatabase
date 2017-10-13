
package at.sunplugged.celldatabase.rcp.handler;

import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import at.sunplugged.celldatabase.database.ui.compare.CommitHelper;
import at.sunplugged.celldatabase.rcp.Activator;

public class CommitHandler {
	@Execute
	public void execute(Shell shell, ModelDatabaseService databaseService) {

		if (MessageDialog.openConfirm(shell, "Commit",
				"Are you sure you want to commit the changes to the database (Cannot be undone)?")) {
			Job job = new Job("Commiting changes to database") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						CommitHelper.commit(shell, databaseService.getLocalResource(),
								databaseService.getRemoteResource());
					} catch (IOException e) {
						return new Status(Status.ERROR, Activator.PLUGIN_ID, "Failed to commit to remote server...", e);
					}
					return Status.OK_STATUS;
				}
			};
			job.setPriority(Job.LONG);
			job.schedule();

		}

	}

	@CanExecute
	public boolean canExecute(ModelDatabaseService databaseService) {

		return databaseService.isConnected();
	}

}