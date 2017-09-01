package at.sunplugged.celldatabase.database.impl;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.osgi.service.component.annotations.Component;

import at.sunplugged.celldatabase.database.Activator;
import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import datamodel.Database;
import datamodel.DatamodelFactory;

@Component
public class ModelDatabaseServiceImpl implements ModelDatabaseService {

	@Inject
	private Logger logger;

	private Database database;

	private AdapterFactory composedAdapterFactory;
	
	private EditingDomain editingDomain;
	
	public ModelDatabaseServiceImpl() {
		editingDomain = new AdapterFactoryEditingDomain(getAdapterFactory(),
				new BasicCommandStack());
		IEclipseContext context = E4Workbench.getServiceContext();
		while (context.getParent() != null) {
			context = context.getParent();
		}
		context.set("editingDomain", editingDomain);
	}

	@Override
	public void open() {
		//logger.log(IStatus.INFO, "Model Database Connection opened");
	}

	@Override
	public void close() {
		//logger.log(IStatus.INFO, "Model Database Connection closed");
	}

	@Override
	public Database getDatabase() {
		if (database == null) {

			File file = Activator.getContext().getDataFile("resource.xml");

			
			Resource resource = editingDomain.createResource("file://" + file.getAbsolutePath().toString());
			try {
				resource.load(null);
			} catch (IOException e) {
				e.printStackTrace();
				//logger.log(IStatus.ERROR, "Failed to load resource...", e);
			} catch (Exception others) {
				others.printStackTrace();
			}
			EList<EObject> content = resource.getContents();
			if (content.isEmpty()) {
				database = DatamodelFactory.eINSTANCE.createDatabase();
				/*
				CellGroup cellGroup = DatamodelFactory.eINSTANCE.createCellGroup();
				cellGroup.setName("Default Group");

				CellResult cellResult = DatamodelFactory.eINSTANCE.createCellResult();
				cellResult.setName("Default Result");

				cellGroup.getCellResults().add(cellResult);

				database.getCellGroups().add(cellGroup);
				*/
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
		//logger.log(IStatus.INFO, "Database save requested...");
	}

	@Override
	public void load() {
		//logger.log(IStatus.INFO, "Database load requested...");
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
