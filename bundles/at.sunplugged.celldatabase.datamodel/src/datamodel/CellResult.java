/**
 */
package datamodel;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cell Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link datamodel.CellResult#getName <em>Name</em>}</li>
 *   <li>{@link datamodel.CellResult#getDescription <em>Description</em>}</li>
 *   <li>{@link datamodel.CellResult#getEvaluationMethod <em>Evaluation Method</em>}</li>
 *   <li>{@link datamodel.CellResult#getDataEvaluated <em>Data Evaluated</em>}</li>
 *   <li>{@link datamodel.CellResult#getRawData <em>Raw Data</em>}</li>
 *   <li>{@link datamodel.CellResult#getOpenCircuitVoltage <em>Open Circuit Voltage</em>}</li>
 *   <li>{@link datamodel.CellResult#getShortCircuitCurrent <em>Short Circuit Current</em>}</li>
 *   <li>{@link datamodel.CellResult#getParallelResistance <em>Parallel Resistance</em>}</li>
 *   <li>{@link datamodel.CellResult#getSeriesResistance <em>Series Resistance</em>}</li>
 *   <li>{@link datamodel.CellResult#getMaximumPower <em>Maximum Power</em>}</li>
 *   <li>{@link datamodel.CellResult#getMaximumPowerVoltage <em>Maximum Power Voltage</em>}</li>
 *   <li>{@link datamodel.CellResult#getMaximumPowerCurrent <em>Maximum Power Current</em>}</li>
 *   <li>{@link datamodel.CellResult#getEfficency <em>Efficency</em>}</li>
 *   <li>{@link datamodel.CellResult#getFillFactor <em>Fill Factor</em>}</li>
 *   <li>{@link datamodel.CellResult#getCellMeasurmenetDataSet <em>Cell Measurmenet Data Set</em>}</li>
 * </ul>
 *
 * @see datamodel.DatamodelPackage#getCellResult()
 * @model
 * @generated
 */
public interface CellResult extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>"default..."</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see datamodel.DatamodelPackage#getCellResult_Name()
	 * @model default="default..."
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see datamodel.DatamodelPackage#getCellResult_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Evaluation Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Evaluation Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evaluation Method</em>' reference.
	 * @see #setEvaluationMethod(EvaluationMethod)
	 * @see datamodel.DatamodelPackage#getCellResult_EvaluationMethod()
	 * @model
	 * @generated
	 */
	EvaluationMethod getEvaluationMethod();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getEvaluationMethod <em>Evaluation Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Evaluation Method</em>' reference.
	 * @see #getEvaluationMethod()
	 * @generated
	 */
	void setEvaluationMethod(EvaluationMethod value);

	/**
	 * Returns the value of the '<em><b>Data Evaluated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Evaluated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Evaluated</em>' attribute.
	 * @see #setDataEvaluated(Date)
	 * @see datamodel.DatamodelPackage#getCellResult_DataEvaluated()
	 * @model
	 * @generated
	 */
	Date getDataEvaluated();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getDataEvaluated <em>Data Evaluated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Evaluated</em>' attribute.
	 * @see #getDataEvaluated()
	 * @generated
	 */
	void setDataEvaluated(Date value);

	/**
	 * Returns the value of the '<em><b>Raw Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Raw Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Raw Data</em>' containment reference.
	 * @see #setRawData(CellMeasurementDataSet)
	 * @see datamodel.DatamodelPackage#getCellResult_RawData()
	 * @model containment="true"
	 * @generated
	 */
	CellMeasurementDataSet getRawData();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getRawData <em>Raw Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Raw Data</em>' containment reference.
	 * @see #getRawData()
	 * @generated
	 */
	void setRawData(CellMeasurementDataSet value);

	/**
	 * Returns the value of the '<em><b>Open Circuit Voltage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Open Circuit Voltage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Open Circuit Voltage</em>' attribute.
	 * @see #setOpenCircuitVoltage(double)
	 * @see datamodel.DatamodelPackage#getCellResult_OpenCircuitVoltage()
	 * @model
	 * @generated
	 */
	double getOpenCircuitVoltage();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getOpenCircuitVoltage <em>Open Circuit Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Open Circuit Voltage</em>' attribute.
	 * @see #getOpenCircuitVoltage()
	 * @generated
	 */
	void setOpenCircuitVoltage(double value);

	/**
	 * Returns the value of the '<em><b>Short Circuit Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Short Circuit Current</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Short Circuit Current</em>' attribute.
	 * @see #setShortCircuitCurrent(double)
	 * @see datamodel.DatamodelPackage#getCellResult_ShortCircuitCurrent()
	 * @model
	 * @generated
	 */
	double getShortCircuitCurrent();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getShortCircuitCurrent <em>Short Circuit Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Short Circuit Current</em>' attribute.
	 * @see #getShortCircuitCurrent()
	 * @generated
	 */
	void setShortCircuitCurrent(double value);

	/**
	 * Returns the value of the '<em><b>Parallel Resistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parallel Resistance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parallel Resistance</em>' attribute.
	 * @see #setParallelResistance(double)
	 * @see datamodel.DatamodelPackage#getCellResult_ParallelResistance()
	 * @model
	 * @generated
	 */
	double getParallelResistance();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getParallelResistance <em>Parallel Resistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parallel Resistance</em>' attribute.
	 * @see #getParallelResistance()
	 * @generated
	 */
	void setParallelResistance(double value);

	/**
	 * Returns the value of the '<em><b>Series Resistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Series Resistance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Series Resistance</em>' attribute.
	 * @see #setSeriesResistance(double)
	 * @see datamodel.DatamodelPackage#getCellResult_SeriesResistance()
	 * @model
	 * @generated
	 */
	double getSeriesResistance();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getSeriesResistance <em>Series Resistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Series Resistance</em>' attribute.
	 * @see #getSeriesResistance()
	 * @generated
	 */
	void setSeriesResistance(double value);

	/**
	 * Returns the value of the '<em><b>Maximum Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Power</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Power</em>' attribute.
	 * @see #setMaximumPower(double)
	 * @see datamodel.DatamodelPackage#getCellResult_MaximumPower()
	 * @model
	 * @generated
	 */
	double getMaximumPower();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getMaximumPower <em>Maximum Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Power</em>' attribute.
	 * @see #getMaximumPower()
	 * @generated
	 */
	void setMaximumPower(double value);

	/**
	 * Returns the value of the '<em><b>Maximum Power Voltage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Power Voltage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Power Voltage</em>' attribute.
	 * @see #setMaximumPowerVoltage(double)
	 * @see datamodel.DatamodelPackage#getCellResult_MaximumPowerVoltage()
	 * @model
	 * @generated
	 */
	double getMaximumPowerVoltage();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getMaximumPowerVoltage <em>Maximum Power Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Power Voltage</em>' attribute.
	 * @see #getMaximumPowerVoltage()
	 * @generated
	 */
	void setMaximumPowerVoltage(double value);

	/**
	 * Returns the value of the '<em><b>Maximum Power Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Power Current</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Power Current</em>' attribute.
	 * @see #setMaximumPowerCurrent(double)
	 * @see datamodel.DatamodelPackage#getCellResult_MaximumPowerCurrent()
	 * @model
	 * @generated
	 */
	double getMaximumPowerCurrent();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getMaximumPowerCurrent <em>Maximum Power Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Power Current</em>' attribute.
	 * @see #getMaximumPowerCurrent()
	 * @generated
	 */
	void setMaximumPowerCurrent(double value);

	/**
	 * Returns the value of the '<em><b>Efficency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Efficency</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Efficency</em>' attribute.
	 * @see #setEfficency(double)
	 * @see datamodel.DatamodelPackage#getCellResult_Efficency()
	 * @model
	 * @generated
	 */
	double getEfficency();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getEfficency <em>Efficency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Efficency</em>' attribute.
	 * @see #getEfficency()
	 * @generated
	 */
	void setEfficency(double value);

	/**
	 * Returns the value of the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fill Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill Factor</em>' attribute.
	 * @see #setFillFactor(double)
	 * @see datamodel.DatamodelPackage#getCellResult_FillFactor()
	 * @model
	 * @generated
	 */
	double getFillFactor();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getFillFactor <em>Fill Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill Factor</em>' attribute.
	 * @see #getFillFactor()
	 * @generated
	 */
	void setFillFactor(double value);

	/**
	 * Returns the value of the '<em><b>Cell Measurmenet Data Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cell Measurmenet Data Set</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cell Measurmenet Data Set</em>' reference.
	 * @see #setCellMeasurmenetDataSet(CellMeasurementDataSet)
	 * @see datamodel.DatamodelPackage#getCellResult_CellMeasurmenetDataSet()
	 * @model
	 * @generated
	 */
	CellMeasurementDataSet getCellMeasurmenetDataSet();

	/**
	 * Sets the value of the '{@link datamodel.CellResult#getCellMeasurmenetDataSet <em>Cell Measurmenet Data Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cell Measurmenet Data Set</em>' reference.
	 * @see #getCellMeasurmenetDataSet()
	 * @generated
	 */
	void setCellMeasurmenetDataSet(CellMeasurementDataSet value);

} // CellResult
