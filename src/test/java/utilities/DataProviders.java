package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	
	public String [][] getData() throws IOException{
		
		String path=".\\testData\\OpenCart_LoginData.xlsx";
		ExcelUtility xlUtil=new ExcelUtility(path);
		
		int totalRows=xlUtil.getRowCount("Sheet1");
		int totalCol=xlUtil.getCellCount("Sheet1", 1);
		
		String loginData[][]=new String[totalRows][totalCol];
		
		for(int i=1; i<=totalRows; i++) {  //reading data from 2nd row as 1st row is header
			for(int j=0; j<totalCol; j++) {
				loginData[i-1][j]=xlUtil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
		
		
	}

}
