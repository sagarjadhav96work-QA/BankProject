package com.automation.bankms.qa.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;
	
	static {
		
		try
		{
			prop=new Properties();
			FileInputStream fileip=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\BankMS\\src\\main\\java\\com\\automation\\bankms\\qa\\config\\commondata.properties");
			prop.load(fileip);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static String getProperty(String Key)
	{
		return prop.getProperty(Key);
	}
	
	
}
