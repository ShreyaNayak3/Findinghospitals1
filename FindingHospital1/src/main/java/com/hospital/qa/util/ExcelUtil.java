package com.hospital.qa.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static XSSFSheet worksheet;
	public static XSSFWorkbook workbook;
	public static FileOutputStream workbook_write;
	public static String file_path;
	public static XSSFRow row;
	public static FileInputStream work_file;
	public static void writeExcelAllDEtails(String sheetName, ArrayList<String> details) {

		workbook = new XSSFWorkbook();
		worksheet = workbook.createSheet(sheetName);
		

		row = worksheet.createRow(0);
		row.createCell(0).setCellValue("Hospitals Ratings");
		
		Iterator<String> itrDetails = details.iterator();
		System.out.println("hospitals are dispalyed in Writesheet"+itrDetails.hasNext());

		String[] rowDataArr;
		int r = 1;
		while (itrDetails.hasNext()) {
			String dataToInsert = (String) itrDetails.next();
			
			rowDataArr = dataToInsert.split(":");
			row = worksheet.createRow(r++);
			for (int i = 0; i < rowDataArr.length; i++) {
				row.createCell(i).setCellValue(rowDataArr[i]);
			}
		}

		try {
			workbook_write = new FileOutputStream("Hospitals.xlsx");
			workbook.write(workbook_write);
			workbook_write.close();
			// workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void writeExcelAlertMsg(String sheetName, String msg) {
		
		
		workbook = new XSSFWorkbook();
		worksheet = workbook.createSheet(sheetName);
		row = worksheet.createRow(0);
		

			row.createCell(0).setCellValue(msg);
		
		try {
			workbook_write = new FileOutputStream("AlertMS36.xlsx");
			workbook.write(workbook_write);
			workbook_write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void writeExcelAllDEtailscity(String sheetName, String city) {

		workbook = new XSSFWorkbook();
		worksheet = workbook.createSheet(sheetName);
		

		row = worksheet.createRow(0);
		row.createCell(0).setCellValue("topcites");

		String[] topcity;
		int r = 1;
		
			String dataToInsert = city;
			
		topcity = dataToInsert.split("\n");
			row = worksheet.createRow(r++);
			for (int i = 0; i < topcity.length; i++) {
				
				row.createCell(i).setCellValue(topcity[i]);
			}
		

		try {
			workbook_write = new FileOutputStream("topcities.xlsx");
			workbook.write(workbook_write);
			workbook_write.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


