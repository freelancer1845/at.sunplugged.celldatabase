
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
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import at.sunplugged.celldatabase.rcp.Activator;

public class ConnectToDatabaseHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ConnectToDatabaseHandler.class);

	private static final String TOPIC_CONNECT = "TOPIC_CONNECT_DATABASE/HANDLER";

	private static boolean connecting = false;

	@Execute
	public void execute(ModelDatabaseService databaseService, EPartService partService, EModelService modelService,
			MApplication app, IEventBroker eventBroker) {
		connecting = true;
		eventBroker.subscribe(TOPIC_CONNECT, new EventHandler() {

			@Override
			public void handleEvent(Event event) {
				connecting = false;
				if ((boolean) event.getProperty(IEventBroker.DATA) == true) {
					MPart viewPart = partService
							.createPart("at.sunplugged.celldatabase.rcp.modelviewer.partdescriptor.modelviewer");
					MPartStack stack = (MPartStack) modelService.find("at.sunplugged.celldatabase.rcp.partstack.0",
							app);
					stack.getChildren().add(viewPart);
					partService.activate(viewPart);
				}
				eventBroker.unsubscribe(this);
			}
		});

		Job job = new Job("Connecting To Database...") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				LOG.debug("Connecting to database...");
				if (databaseService.open() == false) {
					eventBroker.send(TOPIC_CONNECT, false);
					LOG.error("Failed to connect to database...");
					return new Status(Status.ERROR, Activator.PLUGIN_ID, "Failed to connect to database...");
				}
				eventBroker.send(TOPIC_CONNECT, true);
				LOG.debug("Successfully connected to databsae...");

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
		return !databaseService.isOpen() && !connecting;
	}

}