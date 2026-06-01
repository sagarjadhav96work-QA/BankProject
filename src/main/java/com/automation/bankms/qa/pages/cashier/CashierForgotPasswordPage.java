package com.automation.bankms.qa.pages.cashier;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.user.Forgotpasswordpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierForgotPasswordPage {
	
	
	
	By forgotpasswordpagetitle=By.xpath("//h1[text()='Forgot Password!']");
	By emailaddressinputfield=By.name("email");
	By mobilenumberinputfield=By.name("mobile");
	By newpasswordinputfield=By.name("newpassword");
	By confirmpasswordinputfield=By.name("confirmpassword");
	By resetbutton=By.name("submit");
	By forgotpasswordlink=By.xpath("//a[text()='Forgot Password?']");
	By backtohomepagelink=By.xpath("//a[text()='Back to Home Page']");
	protected static final Logger log=LogManagerUtil.getLogger(CashierForgotPasswordPage.class);
	WebDriver driver;
	WaitUtils wait;
	
	
	
	
	public CashierForgotPasswordPage()
	{
		driver=DriverManager.getDriver();
		wait=new WaitUtils(driver, 20000);;
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
	
	public void enterEmailAddress(String EmailAddress)
	{
		log.info("Entering Email Address  {}",EmailAddress);
		driver.findElement(emailaddressinputfield).sendKeys(EmailAddress);
	}
	
	public void enterMobileNumber(String Mobilenumber)
	{
		log.info("Entering Mobile Number  {}",Mobilenumber);
		driver.findElement(mobilenumberinputfield).sendKeys(Mobilenumber);
	}
	
	public void enterNewPassword(String NewPassword)
	{
		log.info("Entering New Password {}",NewPassword);
		driver.findElement(newpasswordinputfield).sendKeys(NewPassword);
	}
	
	public void enterConfirmPassword(String ConfirmPassword)
	{
		log.info("Entering Confirm Password {}",ConfirmPassword);
		driver.findElement(confirmpasswordinputfield).sendKeys(ConfirmPassword);
	}
	
	public Forgotpasswordpage clickonResetButton()
	{
		log.info("Clicking on Reset Button");
		driver.findElement(resetbutton).click();
		return new Forgotpasswordpage();
	}
	
	public Forgotpasswordpage clickonForgotPasswordLink()
	{
		log.info("Clicking on Forgot Password Link");
		driver.findElement(forgotpasswordlink).click();
		return new Forgotpasswordpage();
	}
	
	public Homepage clickonBackToHomePageLink()
	{
		log.info("Clicking on Back to Homepage Link");
		driver.findElement(backtohomepagelink).click();
		return new Homepage();
	}
	
	public boolean checkAlertisPresent()
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
	
	public String getValidationMessageofEmailAddressField()
	{
		return driver.findElement(emailaddressinputfield).getAttribute("validationMessage");
	}
	
	public String getValidationMessageofMobileNumberField()
	{
		return driver.findElement(mobilenumberinputfield).getAttribute("validationMessage");
	}
	
	public String getValidationMessageofNewPasswordField()
	{
		return driver.findElement(newpasswordinputfield).getAttribute("validationMessage");
	}
	
	public String getValidationMessageofConfirmPasswordField()
	{
		return driver.findElement(confirmpasswordinputfield).getAttribute("validationMessage");
	}
	
	public void waitForLoadingofForgotPasswordPage()
	{
		log.info("Waiting for visibility of all fields on Forgot Password Page");
		wait.waitforElementToBeVisible(emailaddressinputfield);
		wait.waitforElementToBeVisible(mobilenumberinputfield);
		wait.waitforElementToBeVisible(newpasswordinputfield);
		wait.waitforElementToBeVisible(confirmpasswordinputfield);
		log.info("Waiting for Forgot Password Page title to be present");
		wait.waitforElementToBePresent(confirmpasswordinputfield);
		
	}
	
	

}
