package at.sunplugged.celldatabase.rcp;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.sunplugged.celldatabase.common.PythonSettings;
import at.sunplugged.celldatabase.common.RegexPatterns;
import at.sunplugged.celldatabase.common.topics.DatabaseTopics;
import at.sunplugged.celldatabase.database.impl.DataStoreController;

@SuppressWarnings("restriction")
public class LifeCycleManager {

	private static final Logger LOG = LoggerFactory.getLogger(LifeCycleManager.class);

	@PostContextCreate
	protected void postContextCreate(IApplicationContext context) {
		LOG.debug("POST CONTEXT CREATE START...");

		createDefaultProperties();

		LOG.debug("Loading Database package... Initilizing HSQLDatastore...");
		if (DataStoreController.setupHsqlDataStore() == true) {
			LOG.debug("Successfully loaded HSQLDataStore...");
		} else {
			LOG.error("Failed loading HSQLDataStore!");
		}

		LOG.debug("POST CONTEXT CREATE DONE...");
	}

	private void createDefaultProperties() {
		PythonSettings.setDefaults(false);
		RegexPatterns.setDefaults(false);
	}

	private void hookModelViewer(IEventBroker eventBroker, EPartService partService, EModelService modelService,
			MApplication app) {
		MPart viewPart;
		viewPart = partService.createPart("at.sunplugged.celldatabase.rcp.modelviewer.partdescriptor.modelviewer");
		MPartStack stack = (MPartStack) modelService.find("at.sunplugged.celldatabase.rcp.partstack.0", app);
		stack.getChildren().add(viewPart);
		partService.activate(viewPart);
		eventBroker.subscribe(DatabaseTopics.TOPIC_CONNECT_LOCAL, new EventHandler() {

			@Override
			public void handleEvent(Event event) {
				MPart viewPart = partService
						.findPart("at.sunplugged.celldatabase.rcp.modelviewer.partdescriptor.modelviewer");
				if ((boolean) event.getProperty(IEventBroker.DATA) == true) {
					if (viewPart != null) {
						partService.activate(viewPart);
					} else {
						viewPart = partService
								.createPart("at.sunplugged.celldatabase.rcp.modelviewer.partdescriptor.modelviewer");
						MPartStack stack = (MPartStack) modelService.find("at.sunplugged.celldatabase.rcp.partstack.0",
								app);
						stack.getChildren().add(viewPart);
						partService.activate(viewPart);
					}
				}
				eventBroker.unsubscribe(this);
			}
		});
	}

}
