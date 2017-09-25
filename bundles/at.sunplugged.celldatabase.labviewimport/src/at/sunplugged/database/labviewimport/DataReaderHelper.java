package at.sunplugged.database.labviewimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

import at.sunplugged.celldatabase.rcp.settings.ids.PythonSettings;
import datamodel.CellMeasurementDataSet;
import datamodel.CellResult;
import datamodel.DatamodelFactory;
import datamodel.UIDataPoint;

public class DataReaderHelper {

	private static final Logger LOG = LoggerFactory.getLogger(DataReaderHelper.class);

	public static List<CellResult> readAndCalculateFile(Collection<File> files) {
		Preferences preferences = ConfigurationScope.INSTANCE
				.getNode(at.sunplugged.celldatabase.rcp.Activator.PLUGIN_ID);

		String pythonLocation = preferences.get(PythonSettings.PYTHON_PATH, "D:\\Anaconda3\\python.exe");
		String pythonScript = preferences.get(PythonSettings.LABVIEW_IMPORT_SCRIPT_PATH,
				"C:\\Users\\jasch\\SunpluggedJob\\at.sunplugged.celldatabase.master\\at.sunplugged.celldatabase\\bundles\\at.sunplugged.celldatabase.datareader\\pythonsrc\\main.py");
		String pluginLocation = Activator.getDefault().getStateLocation().toOSString();

		List<CellResult> resultList = new ArrayList<>();

		for (File file : files) {
			CommandLine cmdLine = new CommandLine(pythonLocation);

			cmdLine.addArgument(pythonScript);

			HashMap<String, Object> map = new HashMap<>();

			map.put("file", file);
			cmdLine.addArgument("${file}");
			cmdLine.addArgument(pluginLocation);
			cmdLine.setSubstitutionMap(map);

			// DefaultExecuteResultHandler resultHandler = new
			// DefaultExecuteResultHandler();

			ExecuteWatchdog watchdog = new ExecuteWatchdog(60 * 1000);
			Executor executor = new DefaultExecutor();
			executor.setExitValue(0);
			executor.setWatchdog(watchdog);
			try {
				executor.execute(cmdLine);
			} catch (ExecuteException e1) {
				LOG.error("Failed to execute Python Evaluation... (ExecuteException)", e1);
				continue;
			} catch (IOException e1) {
				LOG.error("Failed to execute Python Evaluation... (IOException", e1);
				continue;
			}

			CellResult result = DatamodelFactory.eINSTANCE.createCellResult();
			result.setName("Created From Button...");

			CellMeasurementDataSet dataSet = DatamodelFactory.eINSTANCE.createCellMeasurementDataSet();
			dataSet.setName("Create From Button!!:");
			try (Reader reader = new InputStreamReader(new FileInputStream(pluginLocation + "/result.json"), "UTF-8")) {
				Gson gson = new GsonBuilder().create();
				ResultJsonClass p = gson.fromJson(reader, ResultJsonClass.class);
				result.setName(p.getID());
				result.setOpenCircuitVoltage(p.getVoc());
				result.setShortCircuitCurrent(p.getIsc());
				result.setFillFactor(p.getFF());
				result.setEfficiency(p.getEff());
				result.setMaximumPower(p.getMaximumPower());
				result.setMaximumPowerCurrent(p.getMaximumPowerI());
				result.setMaximumPowerVoltage(p.getMaximumPowerV());
				result.setDataEvaluated(new Date());
				result.setParallelResistance(p.getRp());
				result.setSeriesResistance(p.getRs());
				dataSet.setName(p.getID());
				dataSet.setPowerInput(p.getPowerInput());
				dataSet.setArea(p.getArea());
			} catch (UnsupportedEncodingException e) {
				LOG.error("Failed to read results...", e);
				continue;
			} catch (FileNotFoundException e) {
				LOG.error("Failed to read results...", e);
				continue;
			} catch (IOException e) {
				LOG.error("Failed to read results...", e);
				continue;
			}

			try (Reader reader = new InputStreamReader(new FileInputStream(pluginLocation + "/data.json"), "UTF-8")) {
				Gson gson = new GsonBuilder().create();
				DataJsonClass dataObject = gson.fromJson(reader, DataJsonClass.class);
				List<List<Double>> data = dataObject.getData();
				for (int i = 0; i < data.size(); i++) {
					List<Double> pair = data.get(i);
					UIDataPoint dataPoint = DatamodelFactory.eINSTANCE.createUIDataPoint();
					dataPoint.setVoltage(pair.get(0));
					dataPoint.setCurrent(pair.get(1));
					dataSet.getData().add(dataPoint);
				}
			} catch (UnsupportedEncodingException e) {
				LOG.error("Failed to read results...", e);
				continue;
			} catch (FileNotFoundException e) {
				LOG.error("Failed to read results...", e);
				continue;
			} catch (IOException e) {
				LOG.error("Failed to read results...", e);
				continue;
			}
			File resultFile = new File(pluginLocation + "/result.json");
			resultFile.delete();
			File dataFile = new File(pluginLocation + "/data.json");
			dataFile.delete();
			result.setCellMeasurementDataSet(dataSet);
			resultList.add(result);
		}

		return resultList;
	}

	private final static class ResultJsonClass {
		private double Area;
		private double Eff;
		private double FF;
		private String ID;
		private double Isc;
		private double Jsc;
		private double MaximumPower;
		private double MaximumPowerI;
		private double MaximumPowerV;
		private double PowerInput;
		private double Rp;
		private double Rs;
		private double Voc;

		public double getArea() {
			return Area;
		}

		public double getEff() {
			return Eff;
		}

		public double getFF() {
			return FF;
		}

		public String getID() {
			return ID;
		}

		public double getIsc() {
			return Isc;
		}

		public double getJsc() {
			return Jsc;
		}

		public double getMaximumPower() {
			return MaximumPower;
		}

		public double getMaximumPowerI() {
			return MaximumPowerI;
		}

		public double getMaximumPowerV() {
			return MaximumPowerV;
		}

		public double getPowerInput() {
			return PowerInput;
		}

		public double getRp() {
			return Rp;
		}

		public double getRs() {
			return Rs;
		}

		public double getVoc() {
			return Voc;
		}

	}

	private final static class DataJsonClass {
		private List<List<Double>> data = new ArrayList<>();

		public List<List<Double>> getData() {
			return data;
		}

	}
}
