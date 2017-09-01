/**
 */
package datamodel.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import datamodel.CellMeasurementDataSet;
import datamodel.DatamodelPackage;

/**
 * This is the item provider adapter for a {@link datamodel.CellMeasurementDataSet} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CellMeasurementDataSetItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CellMeasurementDataSetItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addDateMeasuredPropertyDescriptor(object);
			addVoltageCurrentDataPropertyDescriptor(object);
			addAreaPropertyDescriptor(object);
			addPowerInputPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CellMeasurementDataSet_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CellMeasurementDataSet_name_feature", "_UI_CellMeasurementDataSet_type"),
				 DatamodelPackage.Literals.CELL_MEASUREMENT_DATA_SET__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CellMeasurementDataSet_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CellMeasurementDataSet_description_feature", "_UI_CellMeasurementDataSet_type"),
				 DatamodelPackage.Literals.CELL_MEASUREMENT_DATA_SET__DESCRIPTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Date Measured feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDateMeasuredPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CellMeasurementDataSet_dateMeasured_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CellMeasurementDataSet_dateMeasured_feature", "_UI_CellMeasurementDataSet_type"),
				 DatamodelPackage.Literals.CELL_MEASUREMENT_DATA_SET__DATE_MEASURED,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Voltage Current Data feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVoltageCurrentDataPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CellMeasurementDataSet_voltageCurrentData_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CellMeasurementDataSet_voltageCurrentData_feature", "_UI_CellMeasurementDataSet_type"),
				 DatamodelPackage.Literals.CELL_MEASUREMENT_DATA_SET__VOLTAGE_CURRENT_DATA,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Area feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAreaPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CellMeasurementDataSet_area_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CellMeasurementDataSet_area_feature", "_UI_CellMeasurementDataSet_type"),
				 DatamodelPackage.Literals.CELL_MEASUREMENT_DATA_SET__AREA,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Power Input feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPowerInputPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CellMeasurementDataSet_powerInput_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CellMeasurementDataSet_powerInput_feature", "_UI_CellMeasurementDataSet_type"),
				 DatamodelPackage.Literals.CELL_MEASUREMENT_DATA_SET__POWER_INPUT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns CellMeasurementDataSet.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CellMeasurementDataSet.png"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		String label = ((CellMeasurementDataSet)object).getName().replaceAll("\\.txt$", "");
		return label == null || label.length() == 0 ?
			getString("_UI_CellMeasurementDataSet_type") :
			label;
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(CellMeasurementDataSet.class)) {
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__NAME:
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DESCRIPTION:
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__DATE_MEASURED:
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__VOLTAGE_CURRENT_DATA:
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__AREA:
			case DatamodelPackage.CELL_MEASUREMENT_DATA_SET__POWER_INPUT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return DatamodelEditPlugin.INSTANCE;
	}

}
