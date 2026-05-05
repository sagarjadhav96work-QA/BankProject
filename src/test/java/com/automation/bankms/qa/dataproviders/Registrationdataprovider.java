package com.automation.bankms.qa.dataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.automation.bankms.qa.utils.ExcelUtils;

public class Registrationdataprovider {
	
	ExcelUtils excut;
	
	@DataProvider(name = "UserRegistrationData")
	public Object[][] registrationdata() throws IOException
	{
		excut=new ExcelUtils("C:\\Users\\Admin\\eclipse-workspace\\BankMS\\Excelstudy.xlsx", "Registrationdata");
		int totalrows = excut.getRowCount();
		int totalcolumns = excut.getCellCount(0);
		
		Object[][] data=new Object[totalrows-1][totalcolumns];
		
		int rowindex=0;
		for(int a=1;a<totalrows;a++)
		{
			String firstname=excut.readdata(a, 0);
			if(firstname==null||firstname.trim().isEmpty())
			{
				break;
			}
			for(int b=0;b<totalcolumns;b++)
			{
				 data[rowindex][b]= excut.readdata(a, b);
				
				
			}
			
			rowindex++;
		}
		return java.util.Arrays.copyOf(data, rowindex);
		
		
		
	}
	

}
