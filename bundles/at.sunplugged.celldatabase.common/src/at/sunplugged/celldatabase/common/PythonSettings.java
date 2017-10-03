package at.sunplugged.celldatabase.common;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.osgi.service.datalocation.Location;

public class PythonSettings {

	public static final String PYTHON_PATH = "pythonPath";

	public static final String LABVIEW_IMPORT_SCRIPT_PATH = "labViewImportScriptPath";

	private static final String DEFAULT_LABVIEW_IMPORT_SCRIPT_PATH;

	public static final String PLOT_SCRIPT_PATH = "plotScriptPyth";

	private static final String DEFAULT_PLOT_SCRIPT_PATH;

	static {
		Location installLocation = Platform.getInstallLocation();
		String installPath = installLocation.getURL().getPath().replaceAll("^/", "");
		DEFAULT_LABVIEW_IMPORT_SCRIPT_PATH = installPath + "python/" + "main.py";
		DEFAULT_PLOT_SCRIPT_PATH = installPath + "python/" + "main.py";
	}

	public static void setDefaults(boolean overwrite) {
		IEclipsePreferences node = ConfigurationScope.INSTANCE.getNode(PrefNodes.PYTHON);
		if (overwrite == false) {
			Utils.setPrefIfNotSet(node, PythonSettings.PLOT_SCRIPT_PATH, DEFAULT_PLOT_SCRIPT_PATH);
			Utils.setPrefIfNotSet(node, PythonSettings.LABVIEW_IMPORT_SCRIPT_PATH, DEFAULT_LABVIEW_IMPORT_SCRIPT_PATH);
		}
	}

	private PythonSettings() {

	}
}
