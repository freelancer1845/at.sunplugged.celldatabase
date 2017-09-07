
package at.sunplugged.celldatabase.rcp.handler;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;

import at.sunplugged.celldatabase.database.api.Settings;
import at.sunplugged.celldatabase.rcp.dialogs.DatabaseSettingsDialog;

public class DatabaseSettingsHandler {
	@Execute
	public void execute(Shell shell) {
		DatabaseSettingsDialog dialog = new DatabaseSettingsDialog(shell);
		if (dialog.open() == Window.OK) {
			try {
				IEclipsePreferences pref = dialog.getPreferences();
				pref.put(Settings.ADDRESS, dialog.getAddressText());
				pref.put(Settings.DATABASE, dialog.getDatabaseNameText());
				pref.put(Settings.USERNAME, dialog.getUsernameText());
				pref.put(Settings.PASSWORD, dialog.getPasswordText());
				pref.put(Settings.DRIVER, dialog.getDriverText());
				dialog.getPreferences().flush();
			} catch (BackingStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}