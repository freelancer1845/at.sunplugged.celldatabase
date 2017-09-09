package at.sunplugged.celldatabase.excelwriter.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EcorePackage;

import datamodel.CellMeasurementDataSet;
import datamodel.CellResult;
import datamodel.DatamodelPackage;
import datamodel.UIDataPoint;

public class ExcelOutputHelper {

	private static final String SHEET_NAME_SUMMARY = "Summary";

	private boolean executed = false;

	private final List<CellResult> cellResults;

	private final String fileName;

	private List<EAttribute> resAttribs = DatamodelPackage.eINSTANCE.getCellResult().getEAllAttributes();

	private XSSFWorkbook workbook = new XSSFWorkbook();

	private CellStyle dateCellStyle;

	private CellStyle headerCellStyle;
	{
		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		dateCellStyle = cellStyle;
		headerCellStyle = workbook.createCellStyle();

		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headerCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
	}

	private XSSFSheet summarySheet;

	public ExcelOutputHelper(List<CellResult> cellResults, String fileName) {
		this.cellResults = cellResults;
		this.fileName = fileName;
	}

	public void execute() {
		if (executed == true) {
			throw new IllegalStateException("Use a new instance of ExcelOutputHelper!!! Already executed...");
		}
		executed = true;
		Job job = new Job("ExcelOutput Job") {
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Writing Excel file...", cellResults.size() + 2);
				monitor.subTask("Creating Summary Sheet");

				createSummarySheet();

				monitor.worked(1);

				monitor.subTask("Creating Result Sheets");

				cellResults.stream().forEach(res -> {
					createCellResultSheet(res);
					monitor.worked(1);
				});

				monitor.subTask("Writing to hard drive...");

				writeWorkbook(fileName, workbook);
				monitor.worked(1);

				monitor.done();
				return Status.OK_STATUS;

			};
		};

		job.setPriority(Job.INTERACTIVE);
		job.schedule();
	}

	private void createSummarySheet() {
		summarySheet = workbook.createSheet(SHEET_NAME_SUMMARY);

		XSSFRow cRow;
		XSSFCell cCell;

		int rowId = 0;
		int colId = 0;

		cRow = summarySheet.createRow(rowId++);

		for (EAttribute attrib : resAttribs) {
			cCell = cRow.createCell(colId++);
			cCell.setCellValue(attrib.getName());
		}

		for (CellResult res : cellResults) {
			cRow = summarySheet.createRow(rowId++);
			colId = 0;
			for (EAttribute attrib : resAttribs) {
				cCell = cRow.createCell(colId++);
				writeValueToCell(cCell, res.eGet(attrib), attrib);

			}
		}

		for (int i = 0; i < 15; i++) {
			summarySheet.getColumnHelper().setColWidth(i, 18);
		}
	}

	private void createCellResultSheet(CellResult res) {

		XSSFSheet sheet = workbook.createSheet(res.getName());

		int rowId = 0;
		int colId = 0;
		XSSFRow nameRow = sheet.createRow(rowId++);
		XSSFRow valueRow = sheet.createRow(rowId++);

		XSSFCell cCell;

		for (EAttribute attr : resAttribs) {
			cCell = nameRow.createCell(colId);
			cCell.setCellStyle(headerCellStyle);
			writeValueToCell(cCell, attr.getName());
			cCell = valueRow.createCell(colId++);
			writeValueToCell(cCell, res.eGet(attr), attr);
		}

		colId = 0;

		CellMeasurementDataSet dataSet = res.getCellMeasurementDataSet();
		if (dataSet == null) {
			return;
		}

		rowId++;
		XSSFRow seperatorRow = sheet.createRow(rowId++);
		cCell = seperatorRow.createCell(0);
		cCell.setCellValue("Measurement Data");
		cCell.setCellStyle(headerCellStyle);

		nameRow = sheet.createRow(rowId++);
		valueRow = sheet.createRow(rowId++);
		for (EAttribute attr : DatamodelPackage.eINSTANCE.getCellMeasurementDataSet().getEAttributes()) {
			cCell = nameRow.createCell(colId);
			cCell.setCellStyle(headerCellStyle);
			writeValueToCell(cCell, attr.getName());
			cCell = valueRow.createCell(colId++);
			writeValueToCell(cCell, dataSet.eGet(attr), attr);

		}

		rowId++;
		XSSFRow seperatorRow2 = sheet.createRow(rowId++);
		cCell = seperatorRow2.createCell(0);
		cCell.setCellValue("Measured Data");
		cCell.setCellStyle(headerCellStyle);

		XSSFRow dataNameRow = sheet.createRow(rowId++);

		cCell = dataNameRow.createCell(0);
		cCell.setCellStyle(headerCellStyle);
		cCell.setCellValue("Voltage[V]");
		cCell = dataNameRow.createCell(1);
		cCell.setCellStyle(headerCellStyle);
		cCell.setCellValue("Current [A]");

		XSSFRow cRow;
		for (UIDataPoint dataPoint : dataSet.getData()) {
			cRow = sheet.createRow(rowId++);
			cCell = cRow.createCell(0);
			cCell.setCellValue(dataPoint.getVoltage());
			cCell = cRow.createCell(1);
			cCell.setCellValue(dataPoint.getCurrent());
		}

		for (int i = 0; i < 15; i++) {
			sheet.getColumnHelper().setColWidth(i, 18);
		}

	}

	private void writeWorkbook(String filePath, XSSFWorkbook workbook) {
		try (FileOutputStream out = new FileOutputStream(new File(filePath))) {

			workbook.write(out);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeValueToCell(XSSFCell cell, Object value) {
		writeValueToCell(cell, value, null);
	}

	private void writeValueToCell(XSSFCell cell, Object value, EAttribute attr) {
		if (value != null) {
			if (attr != null) {
				if (attr.getEAttributeType().equals(EcorePackage.Literals.EDOUBLE)) {
					cell.setCellValue((double) value);
				} else if (attr.getEAttributeType().equals(EcorePackage.Literals.EDATE)) {

					Date date = (Date) value;
					cell.setCellValue((Date) date);
					cell.setCellStyle(dateCellStyle);
				} else {
					cell.setCellValue(value.toString());
				}
			} else {
				cell.setCellValue(value.toString());
			}

		} else {
			cell.setCellValue("");
		}
	}

}
