package at.sunplugged.celldatabase.database.impl;

import java.io.File;
import javax.inject.Inject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.equinox.log.Logger;
import org.osgi.service.component.annotations.Component;
import at.sunplugged.celldatabase.database.Activator;
import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import datamodel.CellGroup;
import datamodel.CellResult;
import datamodel.Database;
import datamodel.DatamodelFactory;

@Component
public class ModelDatabaseServiceImpl implements ModelDatabaseService {

	@Inject
	private Logger logger;

	private Database database;

	private AdapterFactory composedAdapterFactory;

	@Override
	public void open() {
		logger.log(IStatus.INFO, "Model Database Connection opened");
	}

	@Override
	public void close() {
		logger.log(IStatus.INFO, "Model Database Connection closed");
	}

	@Override
	public Database getDatabase() {
		if (database == null) {

			database = DatamodelFactory.eINSTANCE.createDatabase();
			CellGroup cellGroup = DatamodelFactory.eINSTANCE.createCellGroup();
			cellGroup.setName("Default Group");

			CellResult cellResult = DatamodelFactory.eINSTANCE.createCellResult();
			cellResult.setName("Default Result");

			cellGroup.getCellResults().add(cellResult);

			database.getCellGroups().add(cellGroup);

			AdapterFactoryEditingDomain domain = new AdapterFactoryEditingDomain(getAdapterFactory(),
					new BasicCommandStack());

			File file = Activator.getContext().getDataFile("resource.xml");

			Resource resource = domain.createResource(file.getAbsolutePath().toString());
			resource.getContents().add(database);
		}
		return database;
	}

	@Override
	public void save() {
		logger.log(IStatus.INFO, "Database save requested...");
	}

	@Override
	public void load() {
		logger.log(IStatus.INFO, "Database load requested...");
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
}
