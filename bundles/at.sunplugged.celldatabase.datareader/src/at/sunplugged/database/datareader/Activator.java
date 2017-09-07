package at.sunplugged.database.datareader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

	/** The plug-in ID. **/
	public static final String PLUGIN_ID = "at.sungplugged.database.datareader"; //$NON-NLS-1$

	/** The shared instance. **/
	private static Activator plugin;

	/**
	 * The constructor.
	 */
	public Activator() {
	}

	/** {@inheritDoc} **/
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/** {@inheritDoc} **/
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	private static void log(IStatus status) {
		plugin.getLog().log(status);
	}

	/**
	 * Logs a message with a specific status.
	 *
	 * @param status
	 *            the {@link IStatus} value
	 * @param message
	 *            the message to log
	 */
	public static void log(int status, String message) {
		plugin.getLog().log(new Status(status, PLUGIN_ID, message));
	}

	/**
	 * Logs a {@link Throwable}.
	 *
	 * @param t
	 *            the {@link Throwable}
	 * @return the message of the {@link Throwable}
	 */
	public static String log(Throwable t) {
		final IStatus status = getStatus(t);
		log(status);
		return status.getMessage();
	}

	private static IStatus getStatus(Throwable t) {
		if (t instanceof CoreException) {
			final CoreException coreException = (CoreException) t;
			return coreException.getStatus();
		}

		String msg = t.getLocalizedMessage();
		if (msg == null || msg.length() == 0) {
			msg = t.getClass().getName();
		}

		return new Status(IStatus.ERROR, PLUGIN_ID, msg, t);
	}

}
