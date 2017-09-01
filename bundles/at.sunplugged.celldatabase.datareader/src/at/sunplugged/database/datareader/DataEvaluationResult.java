package at.sunplugged.database.datareader;

import datamodel.CellMeasurementDataSet;
import datamodel.CellResult;

/**
 * Wrapper class for {@link CellResult} and {@link CellMeasurementDataSet} when
 * evaluating via python.
 * 
 * @author Jascha Riedel
 *
 */
public final class DataEvaluationResult {

	private final CellResult cellResult;

	private final CellMeasurementDataSet cellMeasurementDataSet;

	public DataEvaluationResult(CellResult cellResult, CellMeasurementDataSet cellMeasurementDataSet) {
		super();
		this.cellResult = cellResult;
		this.cellMeasurementDataSet = cellMeasurementDataSet;
	}

	public CellResult getCellResult() {
		return cellResult;
	}

	public CellMeasurementDataSet getCellMeasurementDataSet() {
		return cellMeasurementDataSet;
	}

}
