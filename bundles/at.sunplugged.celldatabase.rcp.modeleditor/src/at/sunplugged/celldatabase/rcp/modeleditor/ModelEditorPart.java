
package at.sunplugged.celldatabase.rcp.modeleditor;

import javax.annotation.PostConstruct;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTView;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class ModelEditorPart {

  @PostConstruct
  public void postConstruct(Composite parent, MPart part) {
    Composite marginComposite = new Composite(parent, SWT.BORDER);
    FillLayout fillLayout = new FillLayout();
    fillLayout.marginHeight = 10;
    fillLayout.marginWidth = 10;
    marginComposite.setLayout(fillLayout);
    ScrolledComposite composite =
        new ScrolledComposite(marginComposite, SWT.V_SCROLL | SWT.H_SCROLL);
    // Composite composite = new Composite(parent, SWT.BORDER);

    composite.setExpandHorizontal(true);
    composite.setExpandVertical(true);
    composite.setMinSize(250, 250);
    FillLayout fillLayout2 = new FillLayout();
    fillLayout2.marginHeight = 10;
    fillLayout2.marginWidth = 10;
    composite.setLayout(fillLayout2);

    composite.addListener(SWT.Resize, event -> {
      int width = composite.getClientArea().width;
      composite.setMinSize(parent.computeSize(width, SWT.DEFAULT));
    });

    try {
      ECPSWTView view = ECPSWTViewRenderer.INSTANCE.render(composite,
          (EObject) part.getTransientData().get("data"));
      composite.setContent(view.getSWTControl());
    } catch (ECPRendererException e) {
      e.printStackTrace();
    }
  }

}
