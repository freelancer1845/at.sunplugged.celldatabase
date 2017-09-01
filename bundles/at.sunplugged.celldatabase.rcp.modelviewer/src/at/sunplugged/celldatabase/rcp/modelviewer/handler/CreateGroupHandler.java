
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import datamodel.CellGroup;
import datamodel.Database;
import datamodel.DatamodelFactory;

public class CreateGroupHandler {
	@Execute
	public void execute(@Optional @Named("at.sunplugged.celldatabase.rcp.commandparameter.groupname") String groupName,
			ModelDatabaseService databaseService) {

		Database database = databaseService.getDatabase();

		CellGroup cellGroup = DatamodelFactory.eINSTANCE.createCellGroup();
		if (groupName != null) {
			cellGroup.setName(groupName);
		} else {
			cellGroup.setName("Unkown Name");
		}
		database.getCellGroups().add(cellGroup);

	}

}