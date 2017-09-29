
package at.sunplugged.celldatabase.rcp.modelviewer;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeViewerSWTFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import at.sunplugged.celldatabase.database.api.ModelDatabaseService;
import datamodel.Database;
import datamodel.DatamodelPackage;

public class ModelViewerPart {

	@Inject
	private MDirtyable dirtyable;

	private List<Object> dirtyTreeElements = new ArrayList<Object>() {
		@Override
		public boolean add(Object arg0) {
			dirtyable.setDirty(true);
			return super.add(arg0);
		}
	};

	private Map<URI, MPart> createdEditors = new HashMap<>();

	@Persist
	private void doSave(ModelDatabaseService databaseService, @Named("TreeViewer") @Optional TreeViewer treeViewer) {

		Job saveJob = new Job("Saving Database...") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				databaseService.save();
				Display.getDefault().asyncExec(() -> {
					dirtyTreeElements.clear();
					dirtyable.setDirty(false);
					if (treeViewer != null) {
						treeViewer.refresh();
					}
				});

				return Status.OK_STATUS;
			}

		};
		saveJob.setPriority(Job.LONG);
		saveJob.schedule();
	}

	@PostConstruct
	public void postConstruct(Composite parent, ModelDatabaseService databaseService, EMenuService menuService,
			IEclipseContext ctx, EPartService partService, EModelService modelService, MApplication app,
			@Named("editingDomain") EditingDomain editingDomain) {

		Database database = (Database) databaseService.getDatabase();

		editingDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {

			@Override
			public void commandStackChanged(EventObject event) {
				if (editingDomain.getCommandStack().canUndo() == false) {
					dirtyable.setDirty(false);
					dirtyTreeElements.clear();
				}
			}
		});

		// TreeViewer treeViewer = TreeViewerSWTFactory.createTreeViewer(parent,
		// databaseService.getDatabase());

		TreeViewer treeViewer = TreeViewerSWTFactory.fillDefaults(parent, databaseService.getDatabase())
				.customizeLabelDecorator(new ILabelDecorator() {

					@Override
					public void removeListener(ILabelProviderListener listener) {

					}

					@Override
					public boolean isLabelProperty(Object element, String property) {
						return false;
					}

					@Override
					public void dispose() {
					}

					@Override
					public void addListener(ILabelProviderListener listener) {
					}

					@Override
					public String decorateText(String text, Object element) {
						if (dirtyTreeElements.contains(element)) {
							return "*" + text;
						}
						return text;
					}

					@Override
					public Image decorateImage(Image image, Object element) {
						return null;
					}
				}).create();

		EContentAdapter adpater = new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				dirtyable.setDirty(true);
				dirtyTreeElements.add(notification.getNotifier());
				if (DatamodelPackage.eINSTANCE.getCellResult_Name().equals(notification.getFeature())) {
					URI uri = EcoreUtil.getURI((EObject) notification.getNotifier());
					MPart editorPart = createdEditors.get(uri);
					if (editorPart != null) {
						String newLabel = notification.getNewStringValue();
						if (newLabel.isEmpty() == false) {
							editorPart.setLabel(newLabel);
						}
					}
				}

			}
		};
		database.eAdapters().add(adpater);

		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {

				Object selectedElement = (EObject) treeViewer.getStructuredSelection().getFirstElement();
				if (selectedElement instanceof EObject == false) {
					return;
				}

				String label = null;
				if (selectedElement instanceof EObject) {
					EObject eObject = (EObject) selectedElement;
					EAttribute attribute = eObject.eClass().getEAttributes().stream()
							.filter(attr -> attr.getName() == "name").findFirst().orElse(null);
					if (attribute != null) {
						label = (String) eObject.eGet(attribute);
					}

				}
				if (label == null) {
					label = "Editor";
				}

				URI uri = EcoreUtil.getURI((EObject) selectedElement);

				MPart editorPart;

				editorPart = createdEditors.get(uri);
				if (editorPart != null) {
					partService.showPart(editorPart, PartState.ACTIVATE);
					return;
				}
				editorPart = partService.getParts().stream()
						.filter(part -> part.getElementId()
								.equals("at.sunplugged.celldatabase.rcp.modeleditor.partdescriptor.modeleditor"))
						.filter(part -> part.getTransientData().get("uri").equals(uri)).findAny().orElse(null);
				if (editorPart != null) {
					partService.showPart(editorPart, PartState.ACTIVATE);
					return;
				}
				editorPart = partService
						.createPart("at.sunplugged.celldatabase.rcp.modeleditor.partdescriptor.modeleditor");

				editorPart.setLabel(label);
				editorPart.getTransientData().put("data", selectedElement);

				editorPart.getTransientData().put("uri", uri);
				createdEditors.put(uri, editorPart);
				MPartStack partStack = (MPartStack) modelService.find("at.sunplugged.celldatabase.rcp.partstack.1",
						app);

				partStack.getChildren().add(editorPart);
				partService.showPart(editorPart, PartState.ACTIVATE);

			}
		});

		treeViewer.collapseAll();

		menuService.registerContextMenu(treeViewer.getControl(),
				"at.sunplugged.celldatabase.rcp.modelviewer.popupmenu.treepopupmenu");
		ctx.set("TreeViewer", treeViewer);

	}

}