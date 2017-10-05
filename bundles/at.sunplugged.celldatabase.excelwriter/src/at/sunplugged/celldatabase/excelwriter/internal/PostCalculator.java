package at.sunplugged.celldatabase.excelwriter.internal;

import datamodel.CellResult;

public interface PostCalculator {

  double calc(double value, CellResult result);

}
