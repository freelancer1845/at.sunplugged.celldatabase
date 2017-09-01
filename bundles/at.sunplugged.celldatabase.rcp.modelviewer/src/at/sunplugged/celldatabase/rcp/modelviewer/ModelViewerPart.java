
package at.sunplugged.celldatabase.rcp.modelviewer;

import java.util.EventObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeViewerSWTFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import datamodel.Database;

public class ModelViewerPart {

	@Inject
	private MDirtyable dirtyable;
	
	@Persist
	private void doSave(ModelDatabaseService databaseService) {
		databaseService.save();
	}
	
	@PostConstruct
	public void postConstruct(Composite parent, ModelDatabaseService databaseService, EMenuService menuService,
			IEclipseContext ctx, EPartService partService, EModelService modelService, MApplication app, @Named("editingDomain") EditingDomain editingDomain) {

		Database database = (Database) databaseService.getDatabase();
		
		editingDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {
			
			@Override
			public void commandStackChanged(EventObject event) {
				dirtyable.setDirty(editingDomain.getCommandStack().canUndo());
			}
		});
		
		EContentAdapter adpater = new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				editingDomain.getCommandStack().canUndo();
				dirtyable.setDirty(editingDomain.getCommandStack().canUndo());
			}
		};
		database.eAdapters().add(adpater);
		
		TreeViewer treeViewer = TreeViewerSWTFactory.createTreeViewer(parent, databaseService.getDatabase());

		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {

				Object selectedElement = (EObject) treeViewer.getStructuredSelection().getFirstElement();
				if (selectedElement instanceof EObject == false) {
					return;
				}

				MPart editorPart = partService
						.createPart("at.sunplugged.celldatabase.rcp.modeleditor.partdescriptor.modeleditor");
				editorPart.setLabel(selectedElement.toString());
				editorPart.setObject(selectedElement);
				MPartStack partStack = (MPartStack) modelService.find("at.sunplugged.celldatabase.rcp.partstack.1",
						app);
				partStack.getChildren().add(editorPart);
				partService.showPart(editorPart, PartState.ACTIVATE);
			}
		});

		menuService.registerContextMenu(treeViewer.getControl(),
				"at.sunplugged.celldatabase.rcp.modelviewer.popupmenu.treepopupmenu");
		ctx.set("TreeViewer", treeViewer);

	}

}