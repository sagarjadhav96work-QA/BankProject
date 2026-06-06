package com.automation.bankms.qa.pages.cashier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierChangePasswordPage {
	
	By changepasswordpagetitle=By.xpath("//h3[text()='Change Password']");
	By currentpasswordinputfield=By.id("currentpassword");
	By newpasswordinputfield=By.id("newpassword");
	By confirmpasswordinputfield=By.id("confirmpassword");
	By submitbutton=By.id("submit");
	
	
	protected static final Logger log=LogManagerUtil.getLogger(CashierChangePasswordPage.class);
	WebDriver driver;
	WaitUtils wait;
	
	public CashierChangePasswordPage()
	{
		driver=DriverManager.getDriver();
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
	}
	
	public String checkPresenceofTitleofChangePasswordPage()
	{
		log.info("Checking presence of title on dashboard page");
		return driver.findElement(changepasswordpagetitle).getText();
	}
	
	public void enteringCurrentPassword(String CurrentPassword)
	{
		log.info("Entering current Password");
		driver.findElement(currentpasswordinputfield).sendKeys(CurrentPassword);
	}
	
	public void enteringNewPassword(String NewPassword)
	{
		log.info("Entering new Password");
		driver.findElement(newpasswordinputfield).sendKeys(NewPassword);
	}
	
	public void enteringConfirmPassword(String ConfirmPassword)
	{
		log.info("Entering confirm Password");
		driver.findElement(confirmpasswordinputfield).sendKeys(ConfirmPassword);
	}
	
	public CashierChangePasswordPage clickonChangePasswordButton()
	{
		log.info("Clicking on Change Password Submit Button");
		driver.findElement(submitbutton).click();
		return new CashierChangePasswordPage();
	}
	
	public boolean checkalertispresent()
	{
		try
		{
			wait.waitforAlert();
			return true;
		}
		catch(Exception E)
		{
			return false;
		}
		
	}
	
	public String getValidationMessageofCurrentPasswordInputField()
	{
		return driver.findElement(currentpasswordinputfield).getAttribute("validationMessage");
	}
	
	public String getValidationMessageofNewPasswordInputField()
	{
		return driver.findElement(newpasswordinputfield).getAttribute("validationMessage");
	}
	
	public String getValidationMessageofConfirmPasswordInputField()
	{
		return driver.findElement(confirmpasswordinputfield).getAttribute("validationMessage");
	}

}
