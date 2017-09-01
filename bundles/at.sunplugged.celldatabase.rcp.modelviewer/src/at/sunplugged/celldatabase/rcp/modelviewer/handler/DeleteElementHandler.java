
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;

public class DeleteElementHandler {
	@Execute
	public void execute(@Named("TreeViewer") TreeViewer viewer, ModelDatabaseService databaseService) {

		if (viewer instanceof TreeViewer) {

			IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();
			Object[] selectedItems = selection.toArray();
			for (Object object : selectedItems) {
				if (object instanceof EObject) {
					EcoreUtil.remove((EObject) object);
				}

			}
		}

	}

}