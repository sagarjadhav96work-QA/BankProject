package com.automation.bankms.qa.utils;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;

public class CommonUtils {
	
	private static final String ALPHABETS="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHANUMERIC="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String SPECIAL_CHAR="!@#$%^&*";
	
	public boolean checkIfAlertIsPresent(WebDriver driver)
	{
		try
		{   driver.switchTo().alert();
		    return true;
		}
		
		catch (Exception e) {
			return false;
		}
	}
	
	// Generate Employee ID (e.g., EMP12345)
	public static String generateRandomEmployeeID()
	{
		int number = ThreadLocalRandom.current().nextInt(10000, 99999);
		
		return "EMP"+number;
	}
	
	// Generate Strong Password
	public static String generateRandomPassword(int length)
	{
		StringBuilder sb=new StringBuilder();
		//Ensure Atleast one uppercase letter
		sb.append(ALPHABETS.charAt(ThreadLocalRandom.current().nextInt(ALPHABETS.length())));
		
		//Ensure Atleast one lowercase letter
		sb.append(ALPHANUMERIC.charAt(ThreadLocalRandom.current().nextInt(ALPHANUMERIC.length())));
		
		//Ensure Atleast one special character
		sb.append(SPECIAL_CHAR.charAt(ThreadLocalRandom.current().nextInt(SPECIAL_CHAR.length())));
		
		//Fill remaining length
		
		for(int a=sb.length();a<length;a++)
		{
			sb.append(ALPHANUMERIC.charAt(ThreadLocalRandom.current().nextInt(ALPHANUMERIC.length())));
			
			
		}
		
		return sb.toString();
	}
	
	

}
