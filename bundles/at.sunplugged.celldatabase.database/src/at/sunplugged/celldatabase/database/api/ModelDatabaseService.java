package at.sunplugged.celldatabase.database.api;

import datamodel.Database;

public interface ModelDatabaseService {

	/**
	 * 
	 */
	boolean open();

	void cancelConnecting();

	void close();

	Database getDatabase();

	void save();

	void load();

	boolean isOpen();

	void commit();

}
