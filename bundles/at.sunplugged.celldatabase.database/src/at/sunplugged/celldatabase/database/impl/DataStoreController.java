package at.sunplugged.celldatabase.database.impl;

import java.util.Properties;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.sunplugged.celldatabase.database.Activator;
import at.sunplugged.celldatabase.database.api.Settings;
import datamodel.DatamodelPackage;

public class DataStoreController {

	public static final String DATA_STORE_NAME = "dataStoreDefault";

	private static boolean isInit = false;

	private static final Logger LOG = LoggerFactory.getLogger(DataStoreController.class);

	public static boolean isInit() {
		return isInit;
	}

	public static void cancel() {
		LOG.debug("Canceling is not implemented yet....");
		// HbHelper.INSTANCE.getDataStore(DATA_STORE_NAME).close();
	}

	public static boolean initFromSettings() {
		if (isInit == true) {
			System.out.println("Already initilized...");
			return false;
		}
		isInit = true;
		Properties hibernateProperties = new Properties();

		IEclipsePreferences pref = ConfigurationScope.INSTANCE.getNode(Activator.PLUGIN_ID);

		switch (pref.get(Settings.DRIVER, Settings.DRIVER_HSQL)) {

		case Settings.DRIVER_HSQL:
		default:
			hibernateProperties.setProperty(Environment.DRIVER, "org.hsqldb.jdbcDriver");
			hibernateProperties.setProperty(Environment.DIALECT, org.hibernate.dialect.HSQLDialect.class.getName());
			String filePath = pref.get(Settings.ADDRESS, "tmp/hsqldb");
			hibernateProperties.setProperty(Environment.URL, "jdbc:hsqldb:file:/" + filePath);
			break;

		case Settings.DRIVER_SQL_EXPRESS:
			hibernateProperties.setProperty(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String address = pref.get(Settings.ADDRESS, "");
			hibernateProperties.setProperty(Environment.URL, "jdbc:sqlserver://" + address + ";");
			hibernateProperties.setProperty(Environment.DIALECT,
					org.hibernate.dialect.SQLServerDialect.class.getName());
			break;
		}

		hibernateProperties.setProperty(Environment.USER, pref.get(Settings.USERNAME, "sa"));

		hibernateProperties.setProperty(Environment.PASS, pref.get(Settings.PASSWORD, ""));

		hibernateProperties.setProperty(PersistenceOptions.CASCADE_POLICY_ON_NON_CONTAINMENT, "ALL");
		hibernateProperties.setProperty(PersistenceOptions.CASCADE_POLICY_ON_CONTAINMENT, "ALL");
		hibernateProperties.setProperty(PersistenceOptions.INHERITANCE_MAPPING, "JOINED");

		final HbDataStore dataStore = HbHelper.INSTANCE.createRegisterDataStore(DataStoreController.DATA_STORE_NAME);
		dataStore.setDataStoreProperties(hibernateProperties);
		dataStore.setEPackages(new EPackage[] { (EPackage) DatamodelPackage.eINSTANCE });

		try {
			dataStore.initialize();
			LOG.debug("Successfully connected to database...\"" + pref.get(Settings.ADDRESS, "") + "\"");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			dataStore.close();
			isInit = false;
			return false;
		} finally {

		}

	}

	public static boolean dispose() {
		if (isInit == false) {
			System.out.println("Not initilized...");
			return false;
		}
		HbHelper.INSTANCE.deRegisterDataStore(DATA_STORE_NAME);
		isInit = false;
		return true;
	}

}
