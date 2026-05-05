package com.automation.bankms.qa.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtils  {
	
	public static String takeScreenshot(WebDriver driver,String TestName) 
	{
		String path=System.getProperty("user.dir")+"/Screenshots/"+TestName+"_"+System.currentTimeMillis()+".png";
		try {
		File Sourcelocation = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File destinationlocation=new File(path);
		
		
			FileHandler.copy(Sourcelocation, destinationlocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}

}
