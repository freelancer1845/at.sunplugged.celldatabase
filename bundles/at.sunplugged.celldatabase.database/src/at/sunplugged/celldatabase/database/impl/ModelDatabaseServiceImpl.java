package at.sunplugged.celldatabase.database.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import datamodel.Database;
import datamodel.DatamodelFactory;

@SuppressWarnings("restriction")
@Component(immediate = true)
public class ModelDatabaseServiceImpl implements ModelDatabaseService {

	private Database database;

	private AdapterFactory composedAdapterFactory;

	private EditingDomain editingDomain;

	private static final Logger LOG = LoggerFactory.getLogger(ModelDatabaseServiceImpl.class);

	@Activate
	protected void activateService() {
		editingDomain = new AdapterFactoryEditingDomain(getAdapterFactory(), new BasicCommandStack());
		IEclipseContext context = E4Workbench.getServiceContext();
		while (context.getParent() != null) {
			context = context.getParent();
		}
		context.set("editingDomain", editingDomain);

	}

//	@Override
//	public boolean connnectRemote() {
//		boolean success = false;
//		if (DataStoreController.setupSqlExpressDataStore() == false) {
//			LOG.error(
//					"Failed to open connection to database. Probably the database is locked(used) by somebody else...");
//		} else {
//			success = true;
//		}
//		return success;
//
//	}

//	@Override
//	public void disconnectRemote() {
//		DataStoreController.disconnectRemote();
//	}
//
//	@Override
//	public void close() {
//		if (database != null) {
//			database.eResource().unload();
//			database = null;
//
//		}
//		DataStoreController.dispose();
//	}
	
	@Override
	public Database getDatabase() {
		if (database == null) {
			
			Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	        Map<String, Object> m = reg.getExtensionToFactoryMap();
	        m.put("database", new XMIResourceFactoryImpl());

	        ResourceSet resSet = new ResourceSetImpl();

	        Resource resource = resSet.createResource(URI
	                .createURI("database/MainDatabase.database"));
	        
	        EList<EObject> content = resource.getContents();
			if (content.isEmpty()) {
				database = DatamodelFactory.eINSTANCE.createDatabase();
				content.add(database);
			} else {
				database = (Database) content.get(0);
			}
			
			
		}
		
		
		return database;
	}
	

//	@Override
//	public Database getDatabase() {
//		if (database == null) {
//
//			String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM + "="
//					+ DataStoreController.DATA_STORE_NAME_LOCAL;
//
//			Resource resource = editingDomain.createResource(uriStr);
//			try {
//				resource.load(null);
//			} catch (IOException e) {
//				e.printStackTrace();
//				// logger.log(IStatus.ERROR, "Failed to load resource...", e);
//			} catch (Exception others) {
//				others.printStackTrace();
//			}
//			EList<EObject> content = resource.getContents();
//			if (content.isEmpty()) {
//				database = DatamodelFactory.eINSTANCE.createDatabase();
//				content.add(database);
//
//			} else {
//				database = (Database) content.get(0);
//			}
//		}
//		return database;
//	}

	@Override
	public void save() {
		try {
			database.eResource().save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// logger.log(IStatus.INFO, "Database save requested...");
	}

//	@Override
//	public void load() {
//		if (isConnected() == true) {
//			LOG.debug("(Re)Loading database...");
//
//			HibernateResource localRes = (HibernateResource) database.eResource();
//			String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM + "="
//					+ DataStoreController.DATA_STOE_NAME_REMOTE;
//
//			Resource remoteRes = editingDomain.createResource(uriStr);
//			try {
//				LOG.debug("Loading remote repository...");
//				remoteRes.load(null);
//				IComparisonScope scope = new DefaultComparisonScope(remoteRes, localRes, null);
//				Comparison comparison = EMFCompare.builder().build().compare(scope);
//
//				List<Diff> differences = comparison.getDifferences();
//				// Let's merge every single diff
//
//				for (Diff diff : differences) {
//					LOG.info("Diff found...: " + diff.toString());
//				}
//				IMerger.Registry mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
//				IBatchMerger merger = new BatchMerger(mergerRegistry);
//				merger.copyAllLeftToRight(differences, new BasicMonitor());
//
//				remoteRes.unload();
//
//				LOG.debug("Comparision done... Number of diffs: " + differences.size());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		} else {
//			LOG.warn("Database reload requested but not connected...");
//		}
//
//	}

	/**
	 * Return an ComposedAdapterFactory for all registered modesl
	 * 
	 * @return a ComposedAdapterFactory
	 */
	protected AdapterFactory getAdapterFactory() {
		if (composedAdapterFactory == null) {
			composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}
		return composedAdapterFactory;
	}

//	@Override
//	public boolean isConnected() {
//		return DataStoreController.isRemoteConnected();
//	}
//
//	@Override
//	public void cancelConnecting() {
//		DataStoreController.cancel();
//
//	}

//	@Override
//	public void commit() {
//		if (isConnected() == true) {
//			LOG.debug("Commiting local database to remote...");
//
//			Resource localRes = getLocalResource();
//
//			Resource remoteRes = getRemoteResource();
//			try {
//				LOG.debug("Loading remote repository...");
//				remoteRes.load(null);
//				IComparisonScope scope = new DefaultComparisonScope(localRes, remoteRes, null);
//				Comparison comparison = EMFCompare.builder().build().compare(scope);
//
//				List<Diff> differences = comparison.getDifferences();
//				// Let's merge every single diff
//
//				for (Diff diff : differences) {
//					LOG.info("Diff found...: " + diff.toString());
//				}
//				IMerger.Registry mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
//				IBatchMerger merger = new BatchMerger(mergerRegistry);
//				merger.copyAllLeftToRight(differences, new BasicMonitor());
//				LOG.debug("Saving changes...");
//				remoteRes.save(null);
//				remoteRes.unload();
//				LOG.debug("Remote database updated...");
//
//				LOG.debug("Commit  done... Number of diffs: " + differences.size());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		} else {
//			LOG.warn("Database commit requested but not connected...");
//		}
//	}
//
//	@Override
//	public Resource getLocalResource() {
//		HibernateResource localRes = (HibernateResource) database.eResource();
//		return localRes;
//	}

//	@Override
//	public Resource getRemoteResource() {
//		String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM + "="
//				+ DataStoreController.DATA_STOE_NAME_REMOTE;
//		Resource remoteRes = editingDomain.createResource(uriStr);
//		return remoteRes;
//	}
}
