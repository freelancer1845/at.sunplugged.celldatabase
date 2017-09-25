package at.sunplugged.celldatabase.database.impl;

import java.util.Properties;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.eclipse.osgi.service.datalocation.Location;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.sunplugged.celldatabase.database.Activator;
import at.sunplugged.celldatabase.database.api.Settings;
import datamodel.DatamodelPackage;

public class DataStoreController {

	public static final String DATA_STORE_NAME_LOCAL = "localDataStore";

	public static final String DATA_STOE_NAME_REMOTE = "remoteDataStore";

	private static boolean isInit = false;

	private static final Logger LOG = LoggerFactory.getLogger(DataStoreController.class);

	public static boolean isInit() {
		return isInit;
	}

	public static boolean isRemoteConnected() {
		if (HbHelper.INSTANCE.getDataStore(DATA_STOE_NAME_REMOTE) != null) {
			return true;
		} else {
			return false;
		}
	}

	public static void cancel() {
		LOG.debug("Canceling is not implemented yet....");
		// HbHelper.INSTANCE.getDataStore(DATA_STORE_NAME).close();
	}

	public static boolean setupSqlExpressDataStore() {
		if (HbHelper.INSTANCE.getDataStore(DATA_STOE_NAME_REMOTE) != null) {
			LOG.debug("Remote datastore already initilized ignoring... ");
			return true;
		}

		Properties hibernateProperties = new Properties();

		IEclipsePreferences pref = ConfigurationScope.INSTANCE.getNode(Activator.PLUGIN_ID);

		hibernateProperties.setProperty(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String address = pref.get(Settings.ADDRESS, "");
		// hibernateProperties.setProperty(Environment.URL, "jdbc:sqlserver://" +
		// address + ";");
		// hibernateProperties.setProperty(Environment.DIALECT,
		// org.hibernate.dialect.SQLServerDialect.class.getName());
		//
		// hibernateProperties.setProperty(Environment.USER, pref.get(Settings.USERNAME,
		// "sa"));
		//
		// hibernateProperties.setProperty(Environment.PASS, pref.get(Settings.PASSWORD,
		// ""));

		hibernateProperties.setProperty(Environment.URL, "jdbc:sqlserver://"
				+ "testdatabaseteneoemfsunplugged.ceu1dfiokowz.eu-central-1.rds.amazonaws.com:1433;");
		hibernateProperties.setProperty(Environment.DIALECT, org.hibernate.dialect.SQLServerDialect.class.getName());

		hibernateProperties.setProperty(Environment.USER, pref.get(Settings.USERNAME, "celldatabase"));

		hibernateProperties.setProperty(Environment.PASS, pref.get(Settings.PASSWORD, "xsmnn2n78f67b2"));

		hibernateProperties.setProperty(PersistenceOptions.CASCADE_POLICY_ON_NON_CONTAINMENT, "ALL");
		hibernateProperties.setProperty(PersistenceOptions.CASCADE_POLICY_ON_CONTAINMENT, "ALL");
		hibernateProperties.setProperty(PersistenceOptions.INHERITANCE_MAPPING, "JOINED");

		final HbDataStore dataStore = HbHelper.INSTANCE
				.createRegisterDataStore(DataStoreController.DATA_STOE_NAME_REMOTE);
		dataStore.setDataStoreProperties(hibernateProperties);
		dataStore.setEPackages(new EPackage[] { (EPackage) DatamodelPackage.eINSTANCE });

		try {
			dataStore.initialize();
			isInit = true;
			LOG.debug("Successfully connected to remote database...");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			dataStore.close();
			isInit = false;
			return false;
		} finally {

		}
	}

	public static boolean setupHsqlDataStore() {

		if (HbHelper.INSTANCE.getDataStore(DATA_STORE_NAME_LOCAL) != null) {
			LOG.debug("Local datastore already initilized... ignoring.");
			return true;
		}

		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty(Environment.DRIVER, "org.hsqldb.jdbcDriver");
		hibernateProperties.setProperty(Environment.DIALECT, org.hibernate.dialect.HSQLDialect.class.getName());

		Location installLocation = Platform.getInstallLocation();
		if (installLocation == null) {
			LOG.debug("Couldn't find install location...");
			return false;
		}
		String installPath = installLocation.getURL().getPath().replaceAll("^/", "");

		String filePath = installPath + "/localDatabase/data";
		hibernateProperties.setProperty(Environment.URL, "jdbc:hsqldb:file:/" + filePath);

		hibernateProperties.setProperty(Environment.USER, "sa");

		hibernateProperties.setProperty(Environment.PASS, "");

		hibernateProperties.setProperty(PersistenceOptions.CASCADE_POLICY_ON_NON_CONTAINMENT, "ALL");
		hibernateProperties.setProperty(PersistenceOptions.CASCADE_POLICY_ON_CONTAINMENT, "ALL");
		hibernateProperties.setProperty(PersistenceOptions.INHERITANCE_MAPPING, "JOINED");

		final HbDataStore dataStore = HbHelper.INSTANCE
				.createRegisterDataStore(DataStoreController.DATA_STORE_NAME_LOCAL);
		dataStore.setDataStoreProperties(hibernateProperties);
		dataStore.setEPackages(new EPackage[] { (EPackage) DatamodelPackage.eINSTANCE });

		try {
			dataStore.initialize();
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
		HbHelper.INSTANCE.closeAll();
		isInit = false;
		return true;
	}

}
