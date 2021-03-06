package at.sunplugged.celldatabase.rcp.dialogs;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import at.sunplugged.celldatabase.database.api.Settings;

public class DatabaseSettingsDialog extends TitleAreaDialog {

	private Text passwordText;
	private Text usernameText;
	private Text databaseNameText;
	private Text addressText;

	private String password;
	private String username;
	private String databaseName;
	private String address;

	private GridDataFactory gridDataFactory;

	private IEclipsePreferences preferences = ConfigurationScope.INSTANCE
			.getNode(at.sunplugged.celldatabase.database.Activator.PLUGIN_ID);

	public DatabaseSettingsDialog(Shell parentShell) {
		super(parentShell);
		gridDataFactory = GridDataFactory.fillDefaults();
		gridDataFactory.grab(true, false);
		setBlockOnOpen(true);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Database Settings");
		setMessage("Configure the database used as backend", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);

		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(gridDataFactory.create());

		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		createProviderCombo(container);
		createAddressField(container);
		createDatabaseNameField(container);
		createUsernameField(container);
		createPasswordField(container);

		return area;
	}

	@Override
	protected void okPressed() {
		// provider = providerCombo.getText();
		password = passwordText.getText();
		username = usernameText.getText();
		databaseName = databaseNameText.getText();
		address = addressText.getText();
		super.okPressed();
	}

	private void createProviderCombo(Composite container) {
		Label label = new Label(container, SWT.NONE);
		label.setText("Database Driver");
		label.setLayoutData(gridDataFactory.create());

	}

	private void createAddressField(Composite container) {
		Label label = new Label(container, SWT.NONE);
		label.setText("Address");
		label.setLayoutData(gridDataFactory.create());

		addressText = new Text(container, SWT.BORDER);
		addressText.setLayoutData(gridDataFactory.create());
		addressText.setText(preferences.get(Settings.ADDRESS, "tmp/hsqldb"));
	}

	private void createDatabaseNameField(Composite container) {
		Label label = new Label(container, SWT.NONE);
		label.setText("Database Name");
		label.setLayoutData(gridDataFactory.create());

		databaseNameText = new Text(container, SWT.BORDER);
		databaseNameText.setLayoutData(gridDataFactory.create());
		databaseNameText.setText(preferences.get(Settings.DATABASE, ""));
	}

	private void createUsernameField(Composite container) {
		Label label = new Label(container, SWT.NONE);
		label.setText("Username");
		label.setLayoutData(gridDataFactory.create());

		usernameText = new Text(container, SWT.BORDER);
		usernameText.setLayoutData(gridDataFactory.create());
		usernameText.setText(preferences.get(Settings.USERNAME, "sa"));
	}

	private void createPasswordField(Composite container) {
		Label label = new Label(container, SWT.NONE);
		label.setText("Password");

		label.setLayoutData(gridDataFactory.create());

		passwordText = new Text(container, SWT.BORDER | SWT.PASSWORD);
		passwordText.setLayoutData(gridDataFactory.create());
		passwordText.setText(preferences.get(Settings.PASSWORD, ""));

	}

	public String getPasswordText() {
		return password;
	}

	public String getUsernameText() {
		return username;
	}

	public String getDatabaseNameText() {
		return databaseName;
	}

	public String getAddressText() {
		return address;
	}

	public IEclipsePreferences getPreferences() {
		return preferences;
	}

}
