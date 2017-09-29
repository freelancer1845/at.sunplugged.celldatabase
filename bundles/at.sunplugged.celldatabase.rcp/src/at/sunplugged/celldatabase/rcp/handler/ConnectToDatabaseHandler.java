
package at.sunplugged.celldatabase.rcp.handler;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.sunplugged.celldatabase.common.topics.DatabaseTopics;
import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import at.sunplugged.celldatabase.rcp.Activator;

public class ConnectToDatabaseHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ConnectToDatabaseHandler.class);

	private static boolean connecting = false;

	private static MPart viewPart;

	@Execute
	public void execute(ModelDatabaseService databaseService, EPartService partService, EModelService modelService,
			MApplication app, IEventBroker eventBroker) {
		connecting = true;

		Job job = new Job("Connecting To Database...") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				LOG.debug("Connecting to database...");
				if (databaseService.connnectRemote() == false) {
					eventBroker.send(DatabaseTopics.TOPIC_CONNECT_REMOTE, false);
					LOG.error("Failed to connect to database...");
					connecting = false;
					return new Status(Status.ERROR, Activator.PLUGIN_ID,
							"Failed to connect to database, credentials wrong?...");
				}
				eventBroker.send(DatabaseTopics.TOPIC_CONNECT_REMOTE, true);
				LOG.debug("Successfully connected to databsae...");
				connecting = false;
				eventBroker.send(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIEvents.ALL_ELEMENT_ID);
				return Status.OK_STATUS;
			}

			@Override
			protected void canceling() {
				databaseService.cancelConnecting();
				System.out.println("Cancel Requested");
			}
		};
		job.setPriority(Job.LONG);
		job.schedule();

	}

	@CanExecute
	public boolean canExecute(ModelDatabaseService databaseService) {
		return !databaseService.isConnected() && !connecting;
	}

}