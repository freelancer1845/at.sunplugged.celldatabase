
package at.sunplugged.celldatabase.rcp.handler;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;

import at.sunplugged.celldatabase.rcp.dialogs.PythonSettingsDialog;
import at.sunplugged.celldatabase.rcp.settings.ids.PythonSettings;

public class PythonSettingsHandler {
	@Execute
	public void execute(Shell shell) {
		PythonSettingsDialog dialog = new PythonSettingsDialog(shell);
		dialog.setBlockOnOpen(true);
		if (dialog.open() == Window.OK) {
			IEclipsePreferences pref = dialog.getPreferences();

			pref.put(PythonSettings.PYTHON_PATH, dialog.getPythonPathText());
			pref.put(PythonSettings.LABVIEW_IMPORT_SCRIPT_PATH, dialog.getLabViewScriptPathText());

			try {
				pref.flush();
			} catch (BackingStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}