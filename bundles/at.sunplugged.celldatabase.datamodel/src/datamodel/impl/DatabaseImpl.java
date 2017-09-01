/**
 */
package datamodel.impl;

import datamodel.CellGroup;
import datamodel.CellMeasurementDataSet;
import datamodel.Database;
import datamodel.DatamodelPackage;
import datamodel.EvaluationMethod;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link datamodel.impl.DatabaseImpl#getCellGroups <em>Cell Groups</em>}</li>
 *   <li>{@link datamodel.impl.DatabaseImpl#getCellMeasurementDataSets <em>Cell Measurement Data Sets</em>}</li>
 *   <li>{@link datamodel.impl.DatabaseImpl#getEvluationMethods <em>Evluation Methods</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DatabaseImpl extends MinimalEObjectImpl.Container implements Database {
	/**
	 * The cached value of the '{@link #getCellGroups() <em>Cell Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<CellGroup> cellGroups;

	/**
	 * The cached value of the '{@link #getCellMeasurementDataSets() <em>Cell Measurement Data Sets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellMeasurementDataSets()
	 * @generated
	 * @ordered
	 */
	protected EList<CellMeasurementDataSet> cellMeasurementDataSets;

	/**
	 * The cached value of the '{@link #getEvluationMethods() <em>Evluation Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvluationMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<EvaluationMethod> evluationMethods;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DatabaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatamodelPackage.Literals.DATABASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CellGroup> getCellGroups() {
		if (cellGroups == null) {
			cellGroups = new EObjectContainmentEList<CellGroup>(CellGroup.class, this, DatamodelPackage.DATABASE__CELL_GROUPS);
		}
		return cellGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CellMeasurementDataSet> getCellMeasurementDataSets() {
		if (cellMeasurementDataSets == null) {
			cellMeasurementDataSets = new EObjectContainmentEList<CellMeasurementDataSet>(CellMeasurementDataSet.class, this, DatamodelPackage.DATABASE__CELL_MEASUREMENT_DATA_SETS);
		}
		return cellMeasurementDataSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EvaluationMethod> getEvluationMethods() {
		if (evluationMethods == null) {
			evluationMethods = new EObjectContainmentEList<EvaluationMethod>(EvaluationMethod.class, this, DatamodelPackage.DATABASE__EVLUATION_METHODS);
		}
		return evluationMethods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatamodelPackage.DATABASE__CELL_GROUPS:
				return ((InternalEList<?>)getCellGroups()).basicRemove(otherEnd, msgs);
			case DatamodelPackage.DATABASE__CELL_MEASUREMENT_DATA_SETS:
				return ((InternalEList<?>)getCellMeasurementDataSets()).basicRemove(otherEnd, msgs);
			case DatamodelPackage.DATABASE__EVLUATION_METHODS:
				return ((InternalEList<?>)getEvluationMethods()).basicRemove(otherEnd, msgs);
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
			case DatamodelPackage.DATABASE__CELL_GROUPS:
				return getCellGroups();
			case DatamodelPackage.DATABASE__CELL_MEASUREMENT_DATA_SETS:
				return getCellMeasurementDataSets();
			case DatamodelPackage.DATABASE__EVLUATION_METHODS:
				return getEvluationMethods();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DatamodelPackage.DATABASE__CELL_GROUPS:
				getCellGroups().clear();
				getCellGroups().addAll((Collection<? extends CellGroup>)newValue);
				return;
			case DatamodelPackage.DATABASE__CELL_MEASUREMENT_DATA_SETS:
				getCellMeasurementDataSets().clear();
				getCellMeasurementDataSets().addAll((Collection<? extends CellMeasurementDataSet>)newValue);
				return;
			case DatamodelPackage.DATABASE__EVLUATION_METHODS:
				getEvluationMethods().clear();
				getEvluationMethods().addAll((Collection<? extends EvaluationMethod>)newValue);
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
			case DatamodelPackage.DATABASE__CELL_GROUPS:
				getCellGroups().clear();
				return;
			case DatamodelPackage.DATABASE__CELL_MEASUREMENT_DATA_SETS:
				getCellMeasurementDataSets().clear();
				return;
			case DatamodelPackage.DATABASE__EVLUATION_METHODS:
				getEvluationMethods().clear();
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
			case DatamodelPackage.DATABASE__CELL_GROUPS:
				return cellGroups != null && !cellGroups.isEmpty();
			case DatamodelPackage.DATABASE__CELL_MEASUREMENT_DATA_SETS:
				return cellMeasurementDataSets != null && !cellMeasurementDataSets.isEmpty();
			case DatamodelPackage.DATABASE__EVLUATION_METHODS:
				return evluationMethods != null && !evluationMethods.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DatabaseImpl
