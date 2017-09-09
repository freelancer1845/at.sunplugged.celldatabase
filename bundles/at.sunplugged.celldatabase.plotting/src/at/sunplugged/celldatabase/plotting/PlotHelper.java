package at.sunplugged.celldatabase.plotting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.osgi.service.prefs.Preferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import at.sunplugged.celldatabase.plotting.internal.CellResultExclusingStrategy;
import at.sunplugged.celldatabase.rcp.settings.ids.PythonSettings;
import datamodel.CellResult;

public class PlotHelper {

	private final static Logger LOG = LoggerFactory.getLogger(PlotHelper.class);

	public static void plotCellResults(List<CellResult> cellResults) {
		LOG.debug("Plotting CellResults...");

		Preferences pref = ConfigurationScope.INSTANCE.getNode(at.sunplugged.celldatabase.rcp.Activator.PLUGIN_ID);

		String pythonPath = pref.get(PythonSettings.PYTHON_PATH, "D:/Anaconda3/python.exe");
		String scriptPath = pref.get(PythonSettings.PLOT_SCRIPT_PATH,
				"C:\\Users\\jasch\\SunpluggedJob\\at.sunplugged.celldatabase.master\\at.sunplugged.celldatabase\\features\\at.sunplugged.celldatabase.feature\\python\\plotScript.py");

		File dataFile = Activator.getContext().getDataFile("plotCellResults.json");

		Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new CellResultExclusingStrategy())
				.setPrettyPrinting().create();

		try (FileWriter writer = new FileWriter(dataFile)) {
			Type typeOfSrc = new TypeToken<List<CellResult>>() {
			}.getType();

			gson.toJson(cellResults, typeOfSrc, writer);

		} catch (IOException e) {
			LOG.error("Failed to execute plotting. Failed to write data to json...", e);
			return;
		}

		CommandLine cmdLine = new CommandLine(pythonPath);

		cmdLine.addArgument(scriptPath);

		HashMap<String, Object> map = new HashMap<>();

		map.put("file", dataFile.getAbsolutePath());
		cmdLine.addArgument("${file}");
		cmdLine.setSubstitutionMap(map);

		// DefaultExecuteResultHandler resultHandler = new
		// DefaultExecuteResultHandler();

		ExecuteWatchdog watchdog = new ExecuteWatchdog(60 * 1000);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(0);
		executor.setWatchdog(watchdog);
		try {
			executor.execute(cmdLine);
		} catch (ExecuteException e) {
			LOG.error("Failed to execute plot operation...", e);
			return;
		} catch (IOException e) {
			LOG.error("Failed to communicate with plot operation...", e);
			return;
		}

		LOG.debug("Successfull plotted CellResults...");
	}

	private PlotHelper() {

	}

}
