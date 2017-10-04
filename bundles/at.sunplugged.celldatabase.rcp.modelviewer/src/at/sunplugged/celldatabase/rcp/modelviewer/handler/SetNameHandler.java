
package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Evaluate;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import datamodel.CellGroup;

public class SetNameHandler {
	@Execute
	public void execute(@Named("TreeViewer") TreeViewer viewer, Shell shell) {
		IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();
		Object[] selectedItems = selection.toArray();
		Object selected = selectedItems[0];
		if (selected instanceof CellGroup) {
			CellGroup cellGroup = (CellGroup) selected;
			InputDialog dialog = new InputDialog(shell, "Set Name Dialog", "Set name for selected CellGroup...",
					cellGroup.getName(), new IInputValidator() {

						private static final String REGEX = "[a-zA-Z0-9�_\\-\\(\\)]+";

						@Override
						public String isValid(String newText) {
							if (newText.matches(REGEX)) {
								return null;
							}
							return "Must match Regex: " + REGEX;
						}
					});
			if (dialog.open() == Window.OK) {
				cellGroup.setName(dialog.getValue());
			}
		}

	}

	@Evaluate
	public boolean evaluate(@Named("TreeViewer") TreeViewer viewer) {

		IStructuredSelection selection = ((TreeViewer) viewer).getStructuredSelection();
		Object[] selectedItems = selection.toArray();
		if (selectedItems.length != 1) {
			return false;
		}
		Object selected = selectedItems[0];
		if (selected instanceof CellGroup) {
			return true;
		}

		return false;
	}
}