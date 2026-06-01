package com.automation.bankms.qa.pages.cashier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;


public class CashierLoginPage {
	By cashierLoginpagetitle=By.xpath("//h1[text()='e-Banking System | Cashier Login']");
	By employeeidinputfield=By.id("empid");
	By passwordinputfield=By.id("password");
	By loginbutton=By.name("login");
	By forgotpasswordlink=By.xpath("//a[text()='Forgot Password?']");
	By homepagelink=By.xpath("//a[text()='Home Page']");
	
	
   WebDriver driver;
   WaitUtils wait;
   protected static final Logger log=LogManagerUtil.getLogger(CashierLoginPage.class);
	public CashierLoginPage()
	{
		driver=DriverManager.getDriver();
		wait=new WaitUtils(driver, 20000);
	}
	
	public String getTitleofCashierLoginPage()
	{
		log.info("Checking title of Cashier Login Page is Present or not");
		log.info("Cashier Login Page title Checked: {}", driver.findElement(cashierLoginpagetitle).getText());
		return driver.findElement(cashierLoginpagetitle).getText();
		
	}
	
	public void enterEmployeeId(String empid)
	{
		log.info("Entering Employee id");
		driver.findElement(employeeidinputfield).sendKeys(empid);
	}
	
	public void enterPassword(String pass)
	{
		log.info("Entering Password");
		driver.findElement(passwordinputfield).sendKeys(pass);
	}
	
	public CashierDashboardPage clickOnLoginbutton()
	{
		log.info("Clicking on Loginbutton");
		driver.findElement(loginbutton).click();
		return new CashierDashboardPage();
		
	}
	
	public CashierForgotPasswordPage clickOnForgotPasswordLink()
	{
		log.info("Clicking on Forgot password link");
		driver.findElement(forgotpasswordlink).click();
		return new  CashierForgotPasswordPage();
		
	}
	
	public Homepage clickOnHomepagelink()
	{
		log.info("Clicking on Homepage link");
		driver.findElement(homepagelink).click();
		return new Homepage();
	}
	
	public void waitForVisibilityofCashierLoginPage()
	{
		log.info("waiting for cashier login page title to be present");
		wait.waitforElementToBePresent(cashierLoginpagetitle);
		log.info("cashier login page title is present");
		log.info("waiting for login button to be clickable");
		wait.waitforElementToBeClickable(loginbutton);
		log.info("log in button is clickable");
		log.info("waiting for password input field to be visible");
		wait.waitforElementToBeVisible(passwordinputfield);
		log.info("password input field is Visible");
		log.info("waiting for employee id input field to be visible");
		wait.waitforElementToBeVisible(employeeidinputfield);
		log.info("employee id input field is Visible");
		log.info("waiting for password input field to be Clickable");
		wait.waitforElementToBeClickable(passwordinputfield);
		log.info("password input field is Clickable");
		log.info("waiting for employee id input field to be Clickable");
		wait.waitforElementToBeClickable(employeeidinputfield);
		log.info("employee id input field is Clickable");
	}
	
	public boolean checkEmployeeIdInputFieldisEnabled()
	{
		return driver.findElement(employeeidinputfield).isEnabled();
	}
	
	public boolean checkPasswordInputFieldisEnabled()
	{
		return driver.findElement(passwordinputfield).isEnabled();
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
	
	public String getValidationTextofEmployeeIDField()
	{
		return driver.findElement(employeeidinputfield).getAttribute("validationMessage");
	}
	
	public String getValidationTextofPasswordField()
	{
		return driver.findElement(passwordinputfield).getAttribute("validationMessage");
	}
	

}

