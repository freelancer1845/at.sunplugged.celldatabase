package at.sunplugged.celldatabase.database.impl.datastore;

import java.util.Properties;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.hibernate.cfg.Environment;

import at.sunplugged.celldatabase.database.impl.DataStoreController;
import datamodel.DatamodelPackage;

public class SqlExpressDataStoreHelper {

	public static boolean initSqlExpressDatastore() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		hibernateProperties.setProperty(Environment.USER, "celldatabase");
		hibernateProperties.setProperty(Environment.URL,
				"jdbc:sqlserver://testdatabaseteneoemfsunplugged.ceu1dfiokowz.eu-central-1.rds.amazonaws.com:1433;");
		hibernateProperties.setProperty(Environment.PASS, "xsmnn2n78f67b2");
		hibernateProperties.setProperty(Environment.DIALECT, org.hibernate.dialect.SQLServerDialect.class.getName());

		// set a specific option
		// see this page http://wiki.eclipse.org/Teneo/Hibernate/Configuration_Options
		// for all the available options
		hibernateProperties.setProperty(PersistenceOptions.CASCADE_POLICY_ON_NON_CONTAINMENT, "ALL");
		hibernateProperties.setProperty(PersistenceOptions.CASCADE_POLICY_ON_CONTAINMENT, "ALL");

		// use the joined inheritance mapping
		hibernateProperties.setProperty(PersistenceOptions.INHERITANCE_MAPPING, "JOINED");

		// Create the DataStore.
		final HbDataStore dataStore = HbHelper.INSTANCE.createRegisterDataStore(DataStoreController.DATA_STORE_NAME);
		dataStore.setDataStoreProperties(hibernateProperties);

		// Configure the EPackages used by this DataStore.
		dataStore.setEPackages(new EPackage[] { (EPackage) DatamodelPackage.eINSTANCE });

		// Initialize the DataStore. This sets up the Hibernate mapping and, in
		// turn, creates the corresponding tables in the database.
		try {
			dataStore.initialize();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			dataStore.close();
			return false;
		} finally {

		}

	}

}
