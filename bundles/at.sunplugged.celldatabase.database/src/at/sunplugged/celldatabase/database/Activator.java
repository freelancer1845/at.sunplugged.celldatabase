package at.sunplugged.celldatabase.database;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.sunplugged.celldatabase.database.impl.DataStoreController;

public class Activator extends Plugin {

	private static final Logger LOG = LoggerFactory.getLogger(Activator.class);

	public static final String PLUGIN_ID = "at.sunplugged.celldatabase.database";

	private static BundleContext context;

	public static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		LOG.debug("Loading Database package... Initilizing HSQLDatastore...");
		if (DataStoreController.setupHsqlDataStore() == true) {
			LOG.debug("Successfully loaded HSQLDataStore...");
		} else {
			LOG.error("Failed loading HSQLDataStore!");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
