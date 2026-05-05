package com.automation.bankms.qa.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	Workbook workbook;
	Sheet sheet;
	String filepath;
	
	
	public ExcelUtils(String filepath,String sheetname) throws IOException
	{
		FileInputStream fileip=new FileInputStream(filepath);
		this.filepath=filepath;
		workbook=new XSSFWorkbook(fileip);
		this.sheet=workbook.getSheet(sheetname);
		
		fileip.close();
		
	}
	
	public int getRowCount()
	{
		int lastrownumber = sheet.getLastRowNum();
		return lastrownumber+1;
	}
	
	public int getCellCount(int rownum)
	{
		if(sheet.getRow(rownum)==null)
		{
			return 0;
		}
		int lastcellnumber = sheet.getRow(rownum).getLastCellNum();
		return lastcellnumber;
		
	}
	
	public String readdata(int rownum,int cellnum)
	{
		DataFormatter formatter=new DataFormatter();
		Row row = sheet.getRow(rownum);
		if(row==null)
			return "";
		Cell cell = row.getCell(cellnum);
		if(cell==null)
			return "";
		
		return formatter.formatCellValue(cell);
	}
	
	public void writedata(int rownum,int cellnum,String value) throws IOException 
	{
		Row row = sheet.getRow(rownum);
		if(row==null)
		{
		     row=sheet.createRow(rownum);
		}
		
		Cell cell = row.getCell(cellnum);
		if(cell==null)
		{
			cell=row.createCell(cellnum);
		}
		cell.setCellValue(value);
		
		FileOutputStream fos=new FileOutputStream(filepath);
		workbook.write(fos);
		
		fos.close();
	}
	
	
	public void closingworkbook() throws IOException
	{
		 workbook.close();
	}
	
	
	
	
	
	

}
