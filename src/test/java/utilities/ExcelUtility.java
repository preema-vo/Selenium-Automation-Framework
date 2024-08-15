package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
		
	}
	
	public int getRowCount(String xlSheet) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(xlSheet);
		int rowCount=sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
		
			
	}
	
	public int getCellCount(String xlSheet, int rowNum) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(xlSheet);
		row=sheet.getRow(rowNum);
		int cellCount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
		
			
	}
	
	public String getCellData(String xlSheet, int rowNum, int colNum) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(xlSheet);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		String data;
		try {
			
			DataFormatter formatter =new DataFormatter();
			data=formatter.formatCellValue(cell); // returns formatted value of cell as string format
			
		}
		catch(Exception e) {
			
			data="";
		}
		
				
		wb.close();
		fi.close();
		return data;	
			
	}
	
	public void setCellData(String xlSheet, int rowNum, int colNum, String data) throws IOException {
		
		File xlfile=new File(path);
		if(!xlfile.exists()) {
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
			
		}
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(xlSheet)==-1)
			wb.createSheet(xlSheet);
		sheet=wb.getSheet(xlSheet);
		
		if(sheet.getRow(rowNum)==null)
			sheet.createRow(rowNum);
		row=sheet.getRow(rowNum);
		
		cell=row.createCell(colNum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
		
		
	}
	
	public void fillGreenColor(String xlSheet, int rowNum, int colNum) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(xlSheet);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style); 
		
		 
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
		
		
		
	}
	
	public void fillRedColor(String xlSheet, int rowNum, int colNum) throws IOException {
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(xlSheet);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style); 
		
		
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
		
	}

}
