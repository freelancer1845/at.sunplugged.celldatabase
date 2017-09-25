package at.sunplugged.celldatabase.database.api;

import org.eclipse.emf.ecore.resource.Resource;

import datamodel.Database;

public interface ModelDatabaseService {

	/**
	 * 
	 */
	boolean connnectRemote();

	void cancelConnecting();

	void close();

	Database getDatabase();

	void save();

	void load();

	boolean isConnected();

	void commit();

	Resource getLocalResource();

	Resource getRemoteResource();

}
