package com.automation.bankms.qa.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registrationpage {
	
	@FindBy(id="fname")private WebElement firstnameinputfield;
	@FindBy(id="lname")private WebElement lastnameinputfield;
	@FindBy(id="email")private WebElement emailinputfield;
	@FindBy(id="mobno")private WebElement Mobilenumberinputfield;
	@FindBy(id="password")private WebElement passwordinputfield;
	@FindBy(id="submit")private WebElement Registeraccountbutton;
	@FindBy(xpath="//a[text()='Already have an account? Login!']")private WebElement alreadyhaveanaccountlink;
	@FindBy(xpath="//a[text()='Bcak to Home']")private WebElement backtohomelink;
	@FindBy(xpath = "//h1[text()='e-Banking | User Create an Account!']")private WebElement registrationpagetitle;
	
	WebDriver driver;
	public Registrationpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createanewaccount(String firstname,String lastname,String email,String mobile,String password)
	{
		firstnameinputfield.sendKeys(firstname);
		lastnameinputfield.sendKeys(lastname);
		emailinputfield.sendKeys(email);
		Mobilenumberinputfield.sendKeys(mobile);
		passwordinputfield.sendKeys(password);
		Registeraccountbutton.click();
		
	}
	
	public Homepage clickonbacktohomelink()
	{
		backtohomelink.click();
		return new Homepage(driver);
	}
	
	public Loginpage clickonalreadyhaveanaccountlink()
	{
		alreadyhaveanaccountlink.click();
		return new Loginpage(driver);
	}
	
	public String checkworkingofregistrationpage()
	{
		String registrationtitle = registrationpagetitle.getText();
		return registrationtitle;
	}
	
	
	
	
	
	
	

}
