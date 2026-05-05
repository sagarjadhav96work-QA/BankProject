package com.automation.bankms.qa.pages.cashier;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import com.automation.bankms.qa.utils.LogManagerUtil;

public class CashierForgotPasswordPage {
	
	WebDriver driver;
	
	By forgotpasswordpagetitle=By.xpath("//h1[text()='Forgot Password!']");
	By emailaddressinputfield=By.name("email");
	By mobilenumberinputfield=By.name("mobile");
	By newpasswordinputfield=By.name("newpassword");
	By confirmpasswordinputfield=By.name("confirmpassword");
	By resetbutton=By.name("submit");
	By forgotpasswordlink=By.xpath("//a[text()='Forgot Password?']");
	By backtohomepagelink=By.xpath("//a[text()='Back to Home Page']");
	protected static final Logger log=LogManagerUtil.getLogger(CashierForgotPasswordPage.class);
	
	
	
	
	
	public CashierForgotPasswordPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getTitleofForgotPasswordPage()
	{
		log.info("Checking Forgot Password Page Title is present");
		log.info("Extracting title of Forgot Password Page");
		return driver.findElement(forgotpasswordpagetitle).getText();
	}
	
	public boolean checkEmailAddressInputFieldIsEnabled()
	{
		log.info("Checking Email address Field is Enabled");
		return driver.findElement(emailaddressinputfield).isEnabled();
	}
	public boolean checkMobileNumberInputFieldIsEnabled()
	{
		log.info("Checking Mobile Number Field is Enabled");
		return driver.findElement(mobilenumberinputfield).isEnabled();
	}
	public boolean checkNewPasswordInputFieldIsEnabled()
	{
		log.info("Checking New Password Field is Enabled");
		return driver.findElement(newpasswordinputfield).isEnabled();
	}
	public boolean checkConfirmPasswordInputFieldIsEnabled()
	{
		log.info("Checking Confirm Password Field is Enabled");
		return driver.findElement(confirmpasswordinputfield).isEnabled();
	}
	
	

}
