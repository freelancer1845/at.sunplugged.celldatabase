package at.sunplugged.celldatabase.rcp;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class DummyBottomToolControl {

	@PostConstruct
	public void createGui(Composite parent) {
		Composite dummy = new Composite(parent, SWT.NONE);
		GridData gd = new GridData(1, 100);
		dummy.setLayoutData(gd);
	}
}