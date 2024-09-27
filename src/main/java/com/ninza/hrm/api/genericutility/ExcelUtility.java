package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String SheetName, int rowNum, int CellNum)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(SheetName).getRow(rowNum).getCell(CellNum).getStringCellValue();
		wb.close();
		return data;

	}

	public int getRowcount(String SheetName) throws Exception {
		FileInputStream fis = new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowNum = wb.getSheet(SheetName).getLastRowNum();
		wb.close();
		return rowNum;
	}

	public void setDataintoExcel(String SheetName, int rowNum, int CellNum, String Data) throws Exception {
		FileInputStream fis = new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(SheetName).createRow(rowNum).createCell(CellNum).setCellValue(Data);

		FileOutputStream fos = new FileOutputStream("./testData/testScriptData.xlsx");
		wb.write(fos);
		wb.close();

	}
	
	

}
