package at.sunplugged.celldatabase.database.impl;

import java.io.IOException;

import javax.inject.Inject;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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
	}

	@Override
	public boolean open() {
		if (database == null) {
			editingDomain = new AdapterFactoryEditingDomain(getAdapterFactory(), new BasicCommandStack());
			IEclipseContext context = E4Workbench.getServiceContext();
			while (context.getParent() != null) {
				context = context.getParent();
			}
			context.set("editingDomain", editingDomain);

			if (DataStoreController.initFromSettings() == false) {
				LOG.error(
						"Failed to open connection to database. Probably the database is locked(used) by somebody else...");
				return false;
			}
			String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM + "="
					+ DataStoreController.DATA_STORE_NAME;

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
		return true;

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
			System.out.println("Database not open..");
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
		// logger.log(IStatus.INFO, "Database load requested...");
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
	public boolean isOpen() {
		boolean value = DataStoreController.isInit();
		return value;
	}
}
