package at.sunplugged.celldatabase.database.impl;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IBatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.teneo.hibernate.resource.HibernateResource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.sunplugged.celldatabase.database.Activator;
import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import datamodel.Database;
import datamodel.DatamodelFactory;

@SuppressWarnings("restriction")
@Component(immediate = true)
public class ModelDatabaseServiceImpl implements ModelDatabaseService {

	@Inject
	private StatusReporter statusReporter;

	private Database database;

	private AdapterFactory composedAdapterFactory;

	private EditingDomain editingDomain;

	private IEclipsePreferences pref = ConfigurationScope.INSTANCE.getNode(Activator.PLUGIN_ID);

	private static final Logger LOG = LoggerFactory.getLogger(ModelDatabaseServiceImpl.class);

	public ModelDatabaseServiceImpl() {
		getDatabase();
	}

	@Override
	public boolean connnectRemote() {
		boolean success = false;
		if (DataStoreController.setupSqlExpressDataStore() == false) {
			LOG.error(
					"Failed to open connection to database. Probably the database is locked(used) by somebody else...");
		} else {
			success = true;
		}
		return success;

	}

	@Override
	public void close() {
		if (database != null) {
			database.eResource().unload();
			database = null;

		}
		DataStoreController.dispose();
	}

	@Override
	public Database getDatabase() {
		if (database == null) {
			editingDomain = new AdapterFactoryEditingDomain(getAdapterFactory(), new BasicCommandStack());
			IEclipseContext context = E4Workbench.getServiceContext();
			while (context.getParent() != null) {
				context = context.getParent();
			}
			context.set("editingDomain", editingDomain);

			String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM + "="
					+ DataStoreController.DATA_STORE_NAME_LOCAL;

			Resource resource = editingDomain.createResource(uriStr);
			try {
				resource.load(null);
			} catch (IOException e) {
				e.printStackTrace();
				// logger.log(IStatus.ERROR, "Failed to load resource...", e);
			} catch (Exception others) {
				others.printStackTrace();
			}
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

	@Override
	public void load() {
		if (isConnected() == true) {
			LOG.debug("(Re)Loading database...");

			HibernateResource localRes = (HibernateResource) database.eResource();
			String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM + "="
					+ DataStoreController.DATA_STOE_NAME_REMOTE;

			Resource remoteRes = editingDomain.createResource(uriStr);
			try {
				LOG.debug("Loading remote repository...");
				remoteRes.load(null);
				IComparisonScope scope = new DefaultComparisonScope(remoteRes, localRes, null);
				Comparison comparison = EMFCompare.builder().build().compare(scope);

				List<Diff> differences = comparison.getDifferences();
				// Let's merge every single diff

				for (Diff diff : differences) {
					LOG.info("Diff found...: " + diff.toString());
				}
				IMerger.Registry mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
				IBatchMerger merger = new BatchMerger(mergerRegistry);
				ChangeRecorder changeRecorder = new ChangeRecorder(localRes);
				merger.copyAllLeftToRight(differences, new BasicMonitor());

				remoteRes.unload();

				LOG.debug("Comparision done... Number of diffs: " + differences.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			LOG.warn("Database reload requested but not connected...");
		}

	}

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

	@Override
	public boolean isConnected() {
		return DataStoreController.isRemoteConnected();
	}

	@Override
	public void cancelConnecting() {
		DataStoreController.cancel();

	}

	@Override
	public void commit() {
		if (isConnected() == true) {
			LOG.debug("Commiting local database to remote...");

			Resource localRes = getLocalResource();

			Resource remoteRes = getRemoteResource();
			try {
				LOG.debug("Loading remote repository...");
				remoteRes.load(null);
				IComparisonScope scope = new DefaultComparisonScope(localRes, remoteRes, null);
				Comparison comparison = EMFCompare.builder().build().compare(scope);

				List<Diff> differences = comparison.getDifferences();
				// Let's merge every single diff

				for (Diff diff : differences) {
					LOG.info("Diff found...: " + diff.toString());
				}
				IMerger.Registry mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
				IBatchMerger merger = new BatchMerger(mergerRegistry);
				merger.copyAllLeftToRight(differences, new BasicMonitor());
				LOG.debug("Saving changes...");
				remoteRes.save(null);
				remoteRes.unload();
				LOG.debug("Remote database updated...");

				LOG.debug("Commit  done... Number of diffs: " + differences.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			LOG.warn("Database commit requested but not connected...");
		}
	}

	@Override
	public Resource getLocalResource() {
		HibernateResource localRes = (HibernateResource) database.eResource();
		return localRes;
	}

	@Override
	public Resource getRemoteResource() {
		String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM + "="
				+ DataStoreController.DATA_STOE_NAME_REMOTE;
		Resource remoteRes = editingDomain.createResource(uriStr);
		return remoteRes;
	}
}
