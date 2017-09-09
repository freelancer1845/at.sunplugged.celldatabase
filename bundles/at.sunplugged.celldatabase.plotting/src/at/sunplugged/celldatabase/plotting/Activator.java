package at.sunplugged.celldatabase.plotting;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

	private static final String PLUGIN_ID = "at.sunplugged.celldatabase.plotting";

	private static BundleContext context;

	public static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		Activator.context = context;
		super.start(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		Activator.context = null;
		super.stop(context);
	}
}
