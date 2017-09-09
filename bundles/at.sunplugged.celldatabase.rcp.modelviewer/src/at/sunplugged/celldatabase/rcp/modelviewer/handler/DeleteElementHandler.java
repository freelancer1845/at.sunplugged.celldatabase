
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;

public class DeleteElementHandler {
	@Execute
	public void execute(@Named("TreeViewer") TreeViewer viewer, ModelDatabaseService databaseService,
			@Named("editingDomain") EditingDomain domain) {

		if (viewer instanceof TreeViewer) {

			IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();
			Object[] selectedItems = selection.toArray();
			List<EObject> objectsToDelete = new ArrayList<>();
			for (Object object : selectedItems) {
				if (object instanceof EObject) {
					objectsToDelete.add((EObject) object);
				}
			}
			if (objectsToDelete.isEmpty() == false) {
				Command cmd = RemoveCommand.create(domain, objectsToDelete);
				domain.getCommandStack().execute(cmd);
			}
		}

	}

	@CanExecute
	public boolean canExecute(@Named("TreeViewer") TreeViewer viewer) {
		return !viewer.getStructuredSelection().isEmpty();
	}

}