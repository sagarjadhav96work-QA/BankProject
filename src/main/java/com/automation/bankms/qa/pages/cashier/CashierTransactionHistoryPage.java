package com.automation.bankms.qa.pages.cashier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierTransactionHistoryPage {
	
	By transactionhistorypagetitle=By.xpath("//h6[text()='Transaction Details']");
	
	
	
	
	
	WebDriver driver;
	WaitUtils wait;
	
	public CashierTransactionHistoryPage()
	{
		driver=DriverManager.getDriver();
		wait=new WaitUtils(driver, 20000);
	}
	
	public String getTitleofTransactionHistoryPage()
	{
		wait.waitforElementToBeVisible(transactionhistorypagetitle);
		return driver.findElement(transactionhistorypagetitle).getText();
	}
	
	

}
