package com.automation.bankms.qa.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Forgotpasswordpage  {
	
	@FindBy(xpath="//h1[text()='Forgot Password!']")private WebElement forgotpasswordpagetitle;
	@FindBy(xpath="//input[@placeholder='E-mail Address']")private WebElement emailaddressinputfield;
	@FindBy(xpath="//input[@placeholder='Mobile Number']")private WebElement mobilenumberinputfield;
	@FindBy(xpath="//input[@placeholder='New Password']")private WebElement newpasswordinputfield;
	@FindBy(xpath="//input[@placeholder='Confirm Password']")private WebElement confirmpasswordinputfield;
	@FindBy(xpath="//button[@name='submit']")private WebElement resetbutton;
	@FindBy(xpath="//a[text()='Forgot Password?']")private WebElement forgotpasswordlink;
	@FindBy(xpath="//a[text()='Back to Home Page']")private WebElement backtohomepagelink;
	WebDriver driver;
	
	public Forgotpasswordpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public String checkForgotPasswordPageTitle()
	{
		return forgotpasswordpagetitle.getText();
	}
	
	public void enterEmailAddress(String EmailAddress)
	{
		emailaddressinputfield.sendKeys(EmailAddress);
	}
	
	public void enterMobileNumber(String MobileNumber)
	{
		mobilenumberinputfield.sendKeys(MobileNumber);
	}
	
	public void enterNewPassword(String NewPassword)
	{
		newpasswordinputfield.sendKeys(NewPassword);
	}
	
	public void enterConfirmPassword(String ConfirmPassword)
	{
		confirmpasswordinputfield.sendKeys(ConfirmPassword);
	}
	
	public Forgotpasswordpage clickOnResetButton()
	{
		resetbutton.click();
		return new Forgotpasswordpage(driver);
	}
	
	public Forgotpasswordpage clickOnForgotPasswordLink()
	{
		forgotpasswordlink.click();
		return new Forgotpasswordpage(driver);
	}
	
	public Homepage clickOnBackToHomePageLink()
	{
		backtohomepagelink.click();
		return new Homepage(driver);
	}
	
	public String getValidationMessageofEmailAddressInputField()
	{
		return emailaddressinputfield.getAttribute("validationMessage");
	}
	
	public String getValidationMessageofMobileNumberInputField()
	{
		return mobilenumberinputfield.getAttribute("validationMessage");
	}
	
	public String getValidationMessageofNewPasswordInputField()
	{
		return newpasswordinputfield.getAttribute("validationMessage");
	}
	
	public String getValidationMessageofConfirmPasswordInputField()
	{
		return confirmpasswordinputfield.getAttribute("validationMessage");
	}
	
	
	

}
