package at.sunplugged.celldatabase.rcp.modelviewer.handler;

import org.eclipse.jface.viewers.IStructuredSelection;

public class HandlerHelper {

	@SuppressWarnings("rawtypes")
	public static boolean isSingleSelected(IStructuredSelection selection, Class clazz) {
		if (selection.size() != 1) {
			return false;
		}
		if (clazz.isInstance(selection.getFirstElement()) == false) {
			return false;
		}
		return true;
	}

}
