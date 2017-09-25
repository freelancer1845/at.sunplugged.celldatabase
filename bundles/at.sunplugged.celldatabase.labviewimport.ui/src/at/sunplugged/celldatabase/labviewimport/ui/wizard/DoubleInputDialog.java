package at.sunplugged.celldatabase.labviewimport.ui.wizard;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DoubleInputDialog extends TitleAreaDialog {

	private final String title;

	private final String message;

	private Double value;

	private Text input;

	public DoubleInputDialog(Shell parentShell, String title, String message, Double initialValue) {
		super(parentShell);
		this.title = title;
		this.message = message;
		value = initialValue;
		setBlockOnOpen(true);
	}

	@Override
	public void create() {
		super.create();
		setTitle(title);
		setMessage(message, IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container.setLayout(new GridLayout(1, true));

		input = new Text(container, SWT.BORDER | SWT.SINGLE);
		input.setText(value.toString());
		input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		input.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String text = input.getText();
				try {
					double value = Double.valueOf(text);
					getOKButton().setEnabled(true);
				} catch (NumberFormatException nfe) {
					input.setToolTipText("Format number according to Java double format!!!");
					getOKButton().setEnabled(false);
				}
			}
		});

		return container;
	}

	@Override
	protected void okPressed() {
		value = Double.valueOf(input.getText());
		super.okPressed();
	}

	public Double getValue() {
		return value;
	}

}
