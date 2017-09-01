package at.sunplugged.celldatabase.database.impl;

import at.sunplugged.celldatabase.database.impl.datastore.HsqlDbDataStoreHelper;

public class DataStoreController {

	public static final String DATA_STORE_NAME = "dataStoreDefault";

	private static boolean isInit = false;

	public static void initHsqlStore() {
		if (isInit == true) {
			System.out.println("Already initilized...");
			return;
		}
		if (HsqlDbDataStoreHelper.initHsqlDatastore()) {
			isInit = true;
		}
	}

}
