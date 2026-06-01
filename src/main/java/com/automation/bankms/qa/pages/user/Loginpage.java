package com.automation.bankms.qa.pages.user;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.bankms.qa.config.ConfigReader;
import com.automation.bankms.qa.driver.DriverManager;


public class Loginpage {

	@FindBy(xpath="//h1[text()='e-Banking System | User Login']")private WebElement loginpagetitle;
	@FindBy(id="email")private WebElement emailaddress;
	@FindBy(id="password")private WebElement password;
	@FindBy(css = "button[type='submit']")private WebElement loginbutton;
	@FindBy(xpath="//a[text()='Forgot Password?']")private WebElement forgotpasswordlink;
	@FindBy(xpath="//a[text()='Create an Account!']")private WebElement createanaccountlink;
	
	
	
	WebDriver driver;
	
	public Loginpage()
	{
		driver=DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	
	
	public String checkloginpagetitle()
	{
		return loginpagetitle.getText();
	}
	
	
	
	public void entervalidemailid()
	{
		emailaddress.sendKeys(ConfigReader.getProperty("emailaddress"));
		//ElementActions.type(emailaddress, prop.getProperty("emailaddress"));
		
	}
	
	public void entervalidemailidwithaccountnotopened()
	{
		emailaddress.sendKeys(ConfigReader.getProperty("emailaddresswithnoaccountopened"));
		//ElementActions.type(emailaddress,prop.getProperty("emailaddresswithnoaccountopened"));
		
	}
	
	public void dataDrivenLoginofUser(String Email,String Password)
	{
		//ElementActions.type(emailaddress, Email);
		emailaddress.sendKeys(Email);
		//ElementActions.type(password, Password);
		password.sendKeys(Password);
	}
	
	
	public void entervalidpassword()
	{
		//ElementActions.type(password, prop.getProperty("password"));
		password.sendKeys(ConfigReader.getProperty("password"));
		
	}
	
	public void entervalidpasswordwithaccountnotopened()
	{
		
		password.sendKeys(ConfigReader.getProperty("passwordwithnoaccountopened"));
	//	ElementActions.type(password, prop.getProperty("passwordwithnoaccountopened"));
	}
	
	public void entervalidpasswordafterpasswordchange(String ChangedPassword)
	{
		//ElementActions.type(password, ChangedPassword);
		password.sendKeys(ChangedPassword);
		
	}
	
	public Dashboardpage clickonloginbutton()
	{
		//ElementActions.click(loginbutton);
		loginbutton.click();
		
		return new Dashboardpage();
		
	}
	
	public void enterinvalidemailaddress()
	{
		//ElementActions.type(emailaddress, "Sagar10920@gmail.com");
		emailaddress.sendKeys("Sagar10920@gmail.com");
		
	}
	
	public void enterinvalidpassword()
	{
		//ElementActions.type(password,"Sagar@1929823");
		password.sendKeys("Sagar@1929823");
		
	}
	
	public void entersqlvalidationemail()
	{
		//ElementActions.type(emailaddress, "1=1 --");
		emailaddress.sendKeys("1=1 --");
		
	}
	
	
	
	public Forgotpasswordpage clickonforgotpasswordlink()
	{
		//ElementActions.click(forgotpasswordlink);
		forgotpasswordlink.click();
		return new Forgotpasswordpage();
	}
	
	
	public Registrationpage clickoncreateanaccountlink()
	{
		return new Registrationpage();
		
	}
	
}
