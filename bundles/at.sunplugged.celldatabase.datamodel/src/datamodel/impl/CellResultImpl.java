/**
 */
package datamodel.impl;

import datamodel.CellMeasurementDataSet;
import datamodel.CellResult;
import datamodel.DatamodelPackage;
import datamodel.EvaluationMethod;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cell Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link datamodel.impl.CellResultImpl#getName <em>Name</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getEvaluationMethod <em>Evaluation Method</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getDataEvaluated <em>Data Evaluated</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getRawData <em>Raw Data</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getOpenCircuitVoltage <em>Open Circuit Voltage</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getShortCircuitCurrent <em>Short Circuit Current</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getParallelResistance <em>Parallel Resistance</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getSeriesResistance <em>Series Resistance</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getMaximumPower <em>Maximum Power</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getMaximumPowerVoltage <em>Maximum Power Voltage</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getMaximumPowerCurrent <em>Maximum Power Current</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getEfficency <em>Efficency</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getFillFactor <em>Fill Factor</em>}</li>
 *   <li>{@link datamodel.impl.CellResultImpl#getCellMeasurmenetDataSet <em>Cell Measurmenet Data Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CellResultImpl extends MinimalEObjectImpl.Container implements CellResult {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "default...";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEvaluationMethod() <em>Evaluation Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvaluationMethod()
	 * @generated
	 * @ordered
	 */
	protected EvaluationMethod evaluationMethod;

	/**
	 * The default value of the '{@link #getDataEvaluated() <em>Data Evaluated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataEvaluated()
	 * @generated
	 * @ordered
	 */
	protected static final Date DATA_EVALUATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataEvaluated() <em>Data Evaluated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataEvaluated()
	 * @generated
	 * @ordered
	 */
	protected Date dataEvaluated = DATA_EVALUATED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRawData() <em>Raw Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRawData()
	 * @generated
	 * @ordered
	 */
	protected CellMeasurementDataSet rawData;

	/**
	 * The default value of the '{@link #getOpenCircuitVoltage() <em>Open Circuit Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpenCircuitVoltage()
	 * @generated
	 * @ordered
	 */
	protected static final double OPEN_CIRCUIT_VOLTAGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getOpenCircuitVoltage() <em>Open Circuit Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpenCircuitVoltage()
	 * @generated
	 * @ordered
	 */
	protected double openCircuitVoltage = OPEN_CIRCUIT_VOLTAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getShortCircuitCurrent() <em>Short Circuit Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortCircuitCurrent()
	 * @generated
	 * @ordered
	 */
	protected static final double SHORT_CIRCUIT_CURRENT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getShortCircuitCurrent() <em>Short Circuit Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortCircuitCurrent()
	 * @generated
	 * @ordered
	 */
	protected double shortCircuitCurrent = SHORT_CIRCUIT_CURRENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getParallelResistance() <em>Parallel Resistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParallelResistance()
	 * @generated
	 * @ordered
	 */
	protected static final double PARALLEL_RESISTANCE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getParallelResistance() <em>Parallel Resistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParallelResistance()
	 * @generated
	 * @ordered
	 */
	protected double parallelResistance = PARALLEL_RESISTANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSeriesResistance() <em>Series Resistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeriesResistance()
	 * @generated
	 * @ordered
	 */
	protected static final double SERIES_RESISTANCE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSeriesResistance() <em>Series Resistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeriesResistance()
	 * @generated
	 * @ordered
	 */
	protected double seriesResistance = SERIES_RESISTANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumPower() <em>Maximum Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPower()
	 * @generated
	 * @ordered
	 */
	protected static final double MAXIMUM_POWER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaximumPower() <em>Maximum Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPower()
	 * @generated
	 * @ordered
	 */
	protected double maximumPower = MAXIMUM_POWER_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumPowerVoltage() <em>Maximum Power Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPowerVoltage()
	 * @generated
	 * @ordered
	 */
	protected static final double MAXIMUM_POWER_VOLTAGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaximumPowerVoltage() <em>Maximum Power Voltage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPowerVoltage()
	 * @generated
	 * @ordered
	 */
	protected double maximumPowerVoltage = MAXIMUM_POWER_VOLTAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumPowerCurrent() <em>Maximum Power Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPowerCurrent()
	 * @generated
	 * @ordered
	 */
	protected static final double MAXIMUM_POWER_CURRENT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaximumPowerCurrent() <em>Maximum Power Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPowerCurrent()
	 * @generated
	 * @ordered
	 */
	protected double maximumPowerCurrent = MAXIMUM_POWER_CURRENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getEfficency() <em>Efficency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEfficency()
	 * @generated
	 * @ordered
	 */
	protected static final double EFFICENCY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getEfficency() <em>Efficency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEfficency()
	 * @generated
	 * @ordered
	 */
	protected double efficency = EFFICENCY_EDEFAULT;

	/**
	 * The default value of the '{@link #getFillFactor() <em>Fill Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillFactor()
	 * @generated
	 * @ordered
	 */
	protected static final double FILL_FACTOR_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getFillFactor() <em>Fill Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillFactor()
	 * @generated
	 * @ordered
	 */
	protected double fillFactor = FILL_FACTOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCellMeasurmenetDataSet() <em>Cell Measurmenet Data Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellMeasurmenetDataSet()
	 * @generated
	 * @ordered
	 */
	protected CellMeasurementDataSet cellMeasurmenetDataSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CellResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatamodelPackage.Literals.CELL_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationMethod getEvaluationMethod() {
		if (evaluationMethod != null && evaluationMethod.eIsProxy()) {
			InternalEObject oldEvaluationMethod = (InternalEObject)evaluationMethod;
			evaluationMethod = (EvaluationMethod)eResolveProxy(oldEvaluationMethod);
			if (evaluationMethod != oldEvaluationMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatamodelPackage.CELL_RESULT__EVALUATION_METHOD, oldEvaluationMethod, evaluationMethod));
			}
		}
		return evaluationMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationMethod basicGetEvaluationMethod() {
		return evaluationMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvaluationMethod(EvaluationMethod newEvaluationMethod) {
		EvaluationMethod oldEvaluationMethod = evaluationMethod;
		evaluationMethod = newEvaluationMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__EVALUATION_METHOD, oldEvaluationMethod, evaluationMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getDataEvaluated() {
		return dataEvaluated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataEvaluated(Date newDataEvaluated) {
		Date oldDataEvaluated = dataEvaluated;
		dataEvaluated = newDataEvaluated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__DATA_EVALUATED, oldDataEvaluated, dataEvaluated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CellMeasurementDataSet getRawData() {
		return rawData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRawData(CellMeasurementDataSet newRawData, NotificationChain msgs) {
		CellMeasurementDataSet oldRawData = rawData;
		rawData = newRawData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__RAW_DATA, oldRawData, newRawData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRawData(CellMeasurementDataSet newRawData) {
		if (newRawData != rawData) {
			NotificationChain msgs = null;
			if (rawData != null)
				msgs = ((InternalEObject)rawData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatamodelPackage.CELL_RESULT__RAW_DATA, null, msgs);
			if (newRawData != null)
				msgs = ((InternalEObject)newRawData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatamodelPackage.CELL_RESULT__RAW_DATA, null, msgs);
			msgs = basicSetRawData(newRawData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__RAW_DATA, newRawData, newRawData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getOpenCircuitVoltage() {
		return openCircuitVoltage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpenCircuitVoltage(double newOpenCircuitVoltage) {
		double oldOpenCircuitVoltage = openCircuitVoltage;
		openCircuitVoltage = newOpenCircuitVoltage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__OPEN_CIRCUIT_VOLTAGE, oldOpenCircuitVoltage, openCircuitVoltage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getShortCircuitCurrent() {
		return shortCircuitCurrent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortCircuitCurrent(double newShortCircuitCurrent) {
		double oldShortCircuitCurrent = shortCircuitCurrent;
		shortCircuitCurrent = newShortCircuitCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__SHORT_CIRCUIT_CURRENT, oldShortCircuitCurrent, shortCircuitCurrent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getParallelResistance() {
		return parallelResistance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParallelResistance(double newParallelResistance) {
		double oldParallelResistance = parallelResistance;
		parallelResistance = newParallelResistance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__PARALLEL_RESISTANCE, oldParallelResistance, parallelResistance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSeriesResistance() {
		return seriesResistance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeriesResistance(double newSeriesResistance) {
		double oldSeriesResistance = seriesResistance;
		seriesResistance = newSeriesResistance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__SERIES_RESISTANCE, oldSeriesResistance, seriesResistance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMaximumPower() {
		return maximumPower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumPower(double newMaximumPower) {
		double oldMaximumPower = maximumPower;
		maximumPower = newMaximumPower;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__MAXIMUM_POWER, oldMaximumPower, maximumPower));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMaximumPowerVoltage() {
		return maximumPowerVoltage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumPowerVoltage(double newMaximumPowerVoltage) {
		double oldMaximumPowerVoltage = maximumPowerVoltage;
		maximumPowerVoltage = newMaximumPowerVoltage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_VOLTAGE, oldMaximumPowerVoltage, maximumPowerVoltage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMaximumPowerCurrent() {
		return maximumPowerCurrent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumPowerCurrent(double newMaximumPowerCurrent) {
		double oldMaximumPowerCurrent = maximumPowerCurrent;
		maximumPowerCurrent = newMaximumPowerCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_CURRENT, oldMaximumPowerCurrent, maximumPowerCurrent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getEfficency() {
		return efficency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEfficency(double newEfficency) {
		double oldEfficency = efficency;
		efficency = newEfficency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__EFFICENCY, oldEfficency, efficency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFillFactor() {
		return fillFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillFactor(double newFillFactor) {
		double oldFillFactor = fillFactor;
		fillFactor = newFillFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__FILL_FACTOR, oldFillFactor, fillFactor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CellMeasurementDataSet getCellMeasurmenetDataSet() {
		if (cellMeasurmenetDataSet != null && cellMeasurmenetDataSet.eIsProxy()) {
			InternalEObject oldCellMeasurmenetDataSet = (InternalEObject)cellMeasurmenetDataSet;
			cellMeasurmenetDataSet = (CellMeasurementDataSet)eResolveProxy(oldCellMeasurmenetDataSet);
			if (cellMeasurmenetDataSet != oldCellMeasurmenetDataSet) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatamodelPackage.CELL_RESULT__CELL_MEASURMENET_DATA_SET, oldCellMeasurmenetDataSet, cellMeasurmenetDataSet));
			}
		}
		return cellMeasurmenetDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CellMeasurementDataSet basicGetCellMeasurmenetDataSet() {
		return cellMeasurmenetDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCellMeasurmenetDataSet(CellMeasurementDataSet newCellMeasurmenetDataSet) {
		CellMeasurementDataSet oldCellMeasurmenetDataSet = cellMeasurmenetDataSet;
		cellMeasurmenetDataSet = newCellMeasurmenetDataSet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_RESULT__CELL_MEASURMENET_DATA_SET, oldCellMeasurmenetDataSet, cellMeasurmenetDataSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatamodelPackage.CELL_RESULT__RAW_DATA:
				return basicSetRawData(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatamodelPackage.CELL_RESULT__NAME:
				return getName();
			case DatamodelPackage.CELL_RESULT__DESCRIPTION:
				return getDescription();
			case DatamodelPackage.CELL_RESULT__EVALUATION_METHOD:
				if (resolve) return getEvaluationMethod();
				return basicGetEvaluationMethod();
			case DatamodelPackage.CELL_RESULT__DATA_EVALUATED:
				return getDataEvaluated();
			case DatamodelPackage.CELL_RESULT__RAW_DATA:
				return getRawData();
			case DatamodelPackage.CELL_RESULT__OPEN_CIRCUIT_VOLTAGE:
				return getOpenCircuitVoltage();
			case DatamodelPackage.CELL_RESULT__SHORT_CIRCUIT_CURRENT:
				return getShortCircuitCurrent();
			case DatamodelPackage.CELL_RESULT__PARALLEL_RESISTANCE:
				return getParallelResistance();
			case DatamodelPackage.CELL_RESULT__SERIES_RESISTANCE:
				return getSeriesResistance();
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER:
				return getMaximumPower();
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_VOLTAGE:
				return getMaximumPowerVoltage();
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_CURRENT:
				return getMaximumPowerCurrent();
			case DatamodelPackage.CELL_RESULT__EFFICENCY:
				return getEfficency();
			case DatamodelPackage.CELL_RESULT__FILL_FACTOR:
				return getFillFactor();
			case DatamodelPackage.CELL_RESULT__CELL_MEASURMENET_DATA_SET:
				if (resolve) return getCellMeasurmenetDataSet();
				return basicGetCellMeasurmenetDataSet();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DatamodelPackage.CELL_RESULT__NAME:
				setName((String)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__EVALUATION_METHOD:
				setEvaluationMethod((EvaluationMethod)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__DATA_EVALUATED:
				setDataEvaluated((Date)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__RAW_DATA:
				setRawData((CellMeasurementDataSet)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__OPEN_CIRCUIT_VOLTAGE:
				setOpenCircuitVoltage((Double)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__SHORT_CIRCUIT_CURRENT:
				setShortCircuitCurrent((Double)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__PARALLEL_RESISTANCE:
				setParallelResistance((Double)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__SERIES_RESISTANCE:
				setSeriesResistance((Double)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER:
				setMaximumPower((Double)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_VOLTAGE:
				setMaximumPowerVoltage((Double)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_CURRENT:
				setMaximumPowerCurrent((Double)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__EFFICENCY:
				setEfficency((Double)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__FILL_FACTOR:
				setFillFactor((Double)newValue);
				return;
			case DatamodelPackage.CELL_RESULT__CELL_MEASURMENET_DATA_SET:
				setCellMeasurmenetDataSet((CellMeasurementDataSet)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DatamodelPackage.CELL_RESULT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__EVALUATION_METHOD:
				setEvaluationMethod((EvaluationMethod)null);
				return;
			case DatamodelPackage.CELL_RESULT__DATA_EVALUATED:
				setDataEvaluated(DATA_EVALUATED_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__RAW_DATA:
				setRawData((CellMeasurementDataSet)null);
				return;
			case DatamodelPackage.CELL_RESULT__OPEN_CIRCUIT_VOLTAGE:
				setOpenCircuitVoltage(OPEN_CIRCUIT_VOLTAGE_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__SHORT_CIRCUIT_CURRENT:
				setShortCircuitCurrent(SHORT_CIRCUIT_CURRENT_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__PARALLEL_RESISTANCE:
				setParallelResistance(PARALLEL_RESISTANCE_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__SERIES_RESISTANCE:
				setSeriesResistance(SERIES_RESISTANCE_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER:
				setMaximumPower(MAXIMUM_POWER_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_VOLTAGE:
				setMaximumPowerVoltage(MAXIMUM_POWER_VOLTAGE_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_CURRENT:
				setMaximumPowerCurrent(MAXIMUM_POWER_CURRENT_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__EFFICENCY:
				setEfficency(EFFICENCY_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__FILL_FACTOR:
				setFillFactor(FILL_FACTOR_EDEFAULT);
				return;
			case DatamodelPackage.CELL_RESULT__CELL_MEASURMENET_DATA_SET:
				setCellMeasurmenetDataSet((CellMeasurementDataSet)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DatamodelPackage.CELL_RESULT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DatamodelPackage.CELL_RESULT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case DatamodelPackage.CELL_RESULT__EVALUATION_METHOD:
				return evaluationMethod != null;
			case DatamodelPackage.CELL_RESULT__DATA_EVALUATED:
				return DATA_EVALUATED_EDEFAULT == null ? dataEvaluated != null : !DATA_EVALUATED_EDEFAULT.equals(dataEvaluated);
			case DatamodelPackage.CELL_RESULT__RAW_DATA:
				return rawData != null;
			case DatamodelPackage.CELL_RESULT__OPEN_CIRCUIT_VOLTAGE:
				return openCircuitVoltage != OPEN_CIRCUIT_VOLTAGE_EDEFAULT;
			case DatamodelPackage.CELL_RESULT__SHORT_CIRCUIT_CURRENT:
				return shortCircuitCurrent != SHORT_CIRCUIT_CURRENT_EDEFAULT;
			case DatamodelPackage.CELL_RESULT__PARALLEL_RESISTANCE:
				return parallelResistance != PARALLEL_RESISTANCE_EDEFAULT;
			case DatamodelPackage.CELL_RESULT__SERIES_RESISTANCE:
				return seriesResistance != SERIES_RESISTANCE_EDEFAULT;
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER:
				return maximumPower != MAXIMUM_POWER_EDEFAULT;
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_VOLTAGE:
				return maximumPowerVoltage != MAXIMUM_POWER_VOLTAGE_EDEFAULT;
			case DatamodelPackage.CELL_RESULT__MAXIMUM_POWER_CURRENT:
				return maximumPowerCurrent != MAXIMUM_POWER_CURRENT_EDEFAULT;
			case DatamodelPackage.CELL_RESULT__EFFICENCY:
				return efficency != EFFICENCY_EDEFAULT;
			case DatamodelPackage.CELL_RESULT__FILL_FACTOR:
				return fillFactor != FILL_FACTOR_EDEFAULT;
			case DatamodelPackage.CELL_RESULT__CELL_MEASURMENET_DATA_SET:
				return cellMeasurmenetDataSet != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", dataEvaluated: ");
		result.append(dataEvaluated);
		result.append(", openCircuitVoltage: ");
		result.append(openCircuitVoltage);
		result.append(", shortCircuitCurrent: ");
		result.append(shortCircuitCurrent);
		result.append(", parallelResistance: ");
		result.append(parallelResistance);
		result.append(", seriesResistance: ");
		result.append(seriesResistance);
		result.append(", maximumPower: ");
		result.append(maximumPower);
		result.append(", maximumPowerVoltage: ");
		result.append(maximumPowerVoltage);
		result.append(", maximumPowerCurrent: ");
		result.append(maximumPowerCurrent);
		result.append(", efficency: ");
		result.append(efficency);
		result.append(", fillFactor: ");
		result.append(fillFactor);
		result.append(')');
		return result.toString();
	}

} //CellResultImpl
