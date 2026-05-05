package com.automation.bankms.qa.pages.cashier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierUserDetailsPage {
	
	By titleofuserdetailspage=By.xpath("//h1[text()='Details of User']");
	
	
	
	WebDriver driver;
	WaitUtils wait;
	protected static final Logger log=LogManagerUtil.getLogger(CashierUserDetailsPage.class);
	
	public CashierUserDetailsPage(WebDriver driver)
	{
		this.driver=driver;
		wait=new WaitUtils(driver, 20000);
		
	}
	
	public String getTitleofUserDetailsPage()
	{
		log.info("Waiting for loading of User Details Page");
		wait.waitforElementToBePresent(titleofuserdetailspage);
		log.info("Checking Title of User Details Page");
		return driver.findElement(titleofuserdetailspage).getText();
	}
	
	public void clickOnBackButton()
	{
		driver.navigate().back();
	}
	
	
	
	
	
	
	

}
