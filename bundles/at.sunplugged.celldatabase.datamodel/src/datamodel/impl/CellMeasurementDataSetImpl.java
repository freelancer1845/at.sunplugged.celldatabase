/**
 */
package datamodel.impl;

import datamodel.CellMeasurementDataSet;
import datamodel.DatamodelPackage;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cell Measurement Data Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link datamodel.impl.CellMeasurementDataSetImpl#getName <em>Name</em>}</li>
 *   <li>{@link datamodel.impl.CellMeasurementDataSetImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link datamodel.impl.CellMeasurementDataSetImpl#getDateMeasured <em>Date Measured</em>}</li>
 *   <li>{@link datamodel.impl.CellMeasurementDataSetImpl#getVoltageCurrentData <em>Voltage Current Data</em>}</li>
 *   <li>{@link datamodel.impl.CellMeasurementDataSetImpl#getArea <em>Area</em>}</li>
 *   <li>{@link datamodel.impl.CellMeasurementDataSetImpl#getPowerInput <em>Power Input</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CellMeasurementDataSetImpl extends MinimalEObjectImpl.Container implements CellMeasurementDataSet {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

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
	 * The default value of the '{@link #getDateMeasured() <em>Date Measured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDateMeasured()
	 * @generated
	 * @ordered
	 */
	protected static final Date DATE_MEASURED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDateMeasured() <em>Date Measured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDateMeasured()
	 * @generated
	 * @ordered
	 */
	protected Date dateMeasured = DATE_MEASURED_EDEFAULT;

	/**
	 * The default value of the '{@link #getVoltageCurrentData() <em>Voltage Current Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVoltageCurrentData()
	 * @generated
	 * @ordered
	 */
	protected static final double[][] VOLTAGE_CURRENT_DATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVoltageCurrentData() <em>Voltage Current Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVoltageCurrentData()
	 * @generated
	 * @ordered
	 */
	protected double[][] voltageCurrentData = VOLTAGE_CURRENT_DATA_EDEFAULT;

	/**
	 * The default value of the '{@link #getArea() <em>Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArea()
	 * @generated
	 * @ordered
	 */
	protected static final double AREA_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getArea() <em>Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArea()
	 * @generated
	 * @ordered
	 */
	protected double area = AREA_EDEFAULT;

	/**
	 * The default value of the '{@link #getPowerInput() <em>Power Input</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerInput()
	 * @generated
	 * @ordered
	 */
	protected static final double POWER_INPUT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPowerInput() <em>Power Input</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerInput()
	 * @generated
	 * @ordered
	 */
	protected double powerInput = POWER_INPUT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CellMeasurementDataSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatamodelPackage.Literals.CELL_MEASUREMENT_DATA_SET;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_MEASUREMENT_DATA_SET__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getDateMeasured() {
		return dateMeasured;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDateMeasured(Date newDateMeasured) {
		Date oldDateMeasured = dateMeasured;
		dateMeasured = newDateMeasured;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DATE_MEASURED, oldDateMeasured, dateMeasured));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double[][] getVoltageCurrentData() {
		return voltageCurrentData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVoltageCurrentData(double[][] newVoltageCurrentData) {
		double[][] oldVoltageCurrentData = voltageCurrentData;
		voltageCurrentData = newVoltageCurrentData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_MEASUREMENT_DATA_SET__VOLTAGE_CURRENT_DATA, oldVoltageCurrentData, voltageCurrentData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getArea() {
		return area;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArea(double newArea) {
		double oldArea = area;
		area = newArea;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_MEASUREMENT_DATA_SET__AREA, oldArea, area));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPowerInput() {
		return powerInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPowerInput(double newPowerInput) {
		double oldPowerInput = powerInput;
		powerInput = newPowerInput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatamodelPackage.CELL_MEASUREMENT_DATA_SET__POWER_INPUT, oldPowerInput, powerInput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__NAME:
				return getName();
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DESCRIPTION:
				return getDescription();
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DATE_MEASURED:
				return getDateMeasured();
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__VOLTAGE_CURRENT_DATA:
				return getVoltageCurrentData();
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__AREA:
				return getArea();
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__POWER_INPUT:
				return getPowerInput();
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
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__NAME:
				setName((String)newValue);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DATE_MEASURED:
				setDateMeasured((Date)newValue);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__VOLTAGE_CURRENT_DATA:
				setVoltageCurrentData((double[][])newValue);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__AREA:
				setArea((Double)newValue);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__POWER_INPUT:
				setPowerInput((Double)newValue);
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
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DATE_MEASURED:
				setDateMeasured(DATE_MEASURED_EDEFAULT);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__VOLTAGE_CURRENT_DATA:
				setVoltageCurrentData(VOLTAGE_CURRENT_DATA_EDEFAULT);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__AREA:
				setArea(AREA_EDEFAULT);
				return;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__POWER_INPUT:
				setPowerInput(POWER_INPUT_EDEFAULT);
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
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DATE_MEASURED:
				return DATE_MEASURED_EDEFAULT == null ? dateMeasured != null : !DATE_MEASURED_EDEFAULT.equals(dateMeasured);
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__VOLTAGE_CURRENT_DATA:
				return VOLTAGE_CURRENT_DATA_EDEFAULT == null ? voltageCurrentData != null : !VOLTAGE_CURRENT_DATA_EDEFAULT.equals(voltageCurrentData);
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__AREA:
				return area != AREA_EDEFAULT;
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__POWER_INPUT:
				return powerInput != POWER_INPUT_EDEFAULT;
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
		result.append(", dateMeasured: ");
		result.append(dateMeasured);
		result.append(", voltageCurrentData: ");
		result.append(voltageCurrentData);
		result.append(", area: ");
		result.append(area);
		result.append(", powerInput: ");
		result.append(powerInput);
		result.append(')');
		return result.toString();
	}

} //CellMeasurementDataSetImpl
