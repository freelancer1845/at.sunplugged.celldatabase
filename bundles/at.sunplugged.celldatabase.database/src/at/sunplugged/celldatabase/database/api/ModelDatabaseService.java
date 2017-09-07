package at.sunplugged.celldatabase.database.api;

import datamodel.Database;

public interface ModelDatabaseService {

	/**
	 * 
	 */
	void open();

	void close();

	Database getDatabase();

	void save();

	void load();

	boolean isOpen();

}
