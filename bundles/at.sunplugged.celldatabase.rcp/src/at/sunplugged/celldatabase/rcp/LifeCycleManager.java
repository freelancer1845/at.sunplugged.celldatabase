package at.sunplugged.celldatabase.rcp;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.service.datalocation.Location;

import at.sunplugged.celldatabase.rcp.settings.ids.PythonSettings;

@SuppressWarnings("restriction")
public class LifeCycleManager {

	@PostContextCreate
	protected void postContextCreate(IApplicationContext context) {

		createDefaultProperties();
	}

	private void createDefaultProperties() {
		createPythonDefaultPrefs();
	}

	private void createPythonDefaultPrefs() {
		Location installLocation = Platform.getInstallLocation();
		if (installLocation == null) {
			return;
		}

		String installPath = installLocation.getURL().getPath().replaceAll("^/", "");
		System.out.println(installPath);
		IEclipsePreferences pref = ConfigurationScope.INSTANCE.getNode(Activator.PLUGIN_ID);
		if (pref.get(PythonSettings.PLOT_SCRIPT_PATH, "noneValue").equals("noneValue")) {
			pref.put(PythonSettings.PLOT_SCRIPT_PATH, installPath + "python/" + "plotScript.py");
		}
		if (pref.get(PythonSettings.LABVIEW_IMPORT_SCRIPT_PATH, "noneValue").equals("noneValue")) {
			pref.put(PythonSettings.LABVIEW_IMPORT_SCRIPT_PATH, installPath + "python/" + "main.py");
		}
	}

}
