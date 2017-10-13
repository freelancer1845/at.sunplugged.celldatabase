package at.sunplugged.celldatabase.common.ui.imperativeexpressions;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import datamodel.CellGroup;

public class CellGroupSelectedImperativeExpression {

//  @Evaluate
  @CanExecute
  public boolean evaluate(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) CellGroup group) {
    return group != null ? true : false;
  }

}
