/**
 */
package datamodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link datamodel.Database#getCellGroups <em>Cell Groups</em>}</li>
 *   <li>{@link datamodel.Database#getCellMeasurementDataSets <em>Cell Measurement Data Sets</em>}</li>
 *   <li>{@link datamodel.Database#getEvluationMethods <em>Evluation Methods</em>}</li>
 * </ul>
 *
 * @see datamodel.DatamodelPackage#getDatabase()
 * @model
 * @generated
 */
public interface Database extends EObject {
	/**
	 * Returns the value of the '<em><b>Cell Groups</b></em>' containment reference list.
	 * The list contents are of type {@link datamodel.CellGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cell Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cell Groups</em>' containment reference list.
	 * @see datamodel.DatamodelPackage#getDatabase_CellGroups()
	 * @model containment="true"
	 * @generated
	 */
	EList<CellGroup> getCellGroups();

	/**
	 * Returns the value of the '<em><b>Cell Measurement Data Sets</b></em>' containment reference list.
	 * The list contents are of type {@link datamodel.CellMeasurementDataSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cell Measurement Data Sets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cell Measurement Data Sets</em>' containment reference list.
	 * @see datamodel.DatamodelPackage#getDatabase_CellMeasurementDataSets()
	 * @model containment="true"
	 * @generated
	 */
	EList<CellMeasurementDataSet> getCellMeasurementDataSets();

	/**
	 * Returns the value of the '<em><b>Evluation Methods</b></em>' containment reference list.
	 * The list contents are of type {@link datamodel.EvaluationMethod}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Evluation Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evluation Methods</em>' containment reference list.
	 * @see datamodel.DatamodelPackage#getDatabase_EvluationMethods()
	 * @model containment="true"
	 * @generated
	 */
	EList<EvaluationMethod> getEvluationMethods();

} // Database
