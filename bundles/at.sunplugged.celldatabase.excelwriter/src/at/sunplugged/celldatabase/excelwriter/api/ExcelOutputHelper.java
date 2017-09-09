package at.sunplugged.celldatabase.excelwriter.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EAttribute;

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
				Object value = res.eGet(attrib);
				if (value != null) {
					cCell.setCellValue(res.eGet(attrib).toString());
				} else {
					cCell.setCellValue("");
				}

			}
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
			cCell.setCellValue(attr.getName());
			cCell = valueRow.createCell(colId++);
			Object value = res.eGet(attr);
			if (value != null) {
				cCell.setCellValue(res.eGet(attr).toString());
			} else {
				cCell.setCellValue("");
			}
		}

		colId = 0;

		CellMeasurementDataSet dataSet = res.getCellMeasurementDataSet();
		if (dataSet == null) {
			return;
		}

		XSSFRow seperatorRow = sheet.createRow(rowId++);
		seperatorRow.createCell(0).setCellValue("Measurement Data");

		nameRow = sheet.createRow(rowId++);
		valueRow = sheet.createRow(rowId++);
		for (EAttribute attr : DatamodelPackage.eINSTANCE.getCellMeasurementDataSet().getEAttributes()) {
			cCell = nameRow.createCell(colId);
			cCell.setCellValue(attr.getName());
			cCell = valueRow.createCell(colId++);
			Object value = res.eGet(attr);
			if (value != null) {
				cCell.setCellValue(dataSet.eGet(attr).toString());
			} else {
				cCell.setCellValue("");
			}

		}

		XSSFRow seperatorRow2 = sheet.createRow(rowId++);
		seperatorRow2.createCell(0).setCellValue("Measured Data");

		XSSFRow dataNameRow = sheet.createRow(rowId++);

		dataNameRow.createCell(0).setCellValue("Voltage[V]");
		dataNameRow.createCell(1).setCellValue("Current [A]");

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

}
