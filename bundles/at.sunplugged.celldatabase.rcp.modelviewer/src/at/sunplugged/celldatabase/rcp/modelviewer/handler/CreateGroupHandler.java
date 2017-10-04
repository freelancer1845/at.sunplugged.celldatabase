
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import datamodel.CellGroup;
import datamodel.Database;
import datamodel.DatamodelFactory;

public class CreateGroupHandler {
	@Execute
	public void execute(@Optional @Named("at.sunplugged.celldatabase.rcp.commandparameter.groupname") String groupName,
			ModelDatabaseService databaseService, @Named("editingDomain") EditingDomain editingDomain) {

		Database database = databaseService.getDatabase();

		CellGroup cellGroup = DatamodelFactory.eINSTANCE.createCellGroup();
		if (groupName != null) {
			cellGroup.setName(groupName);
		}
		Command cmd = AddCommand.create(editingDomain, database, null, cellGroup);
		editingDomain.getCommandStack().execute(cmd);
	}

}