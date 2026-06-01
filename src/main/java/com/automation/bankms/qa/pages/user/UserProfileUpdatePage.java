package com.automation.bankms.qa.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.bankms.qa.driver.DriverManager;

public class UserProfileUpdatePage  {
	
	@FindBy(xpath="//h1[text()='Profile']")private WebElement profileUpdatePageTitle;
	@FindBy(id="fname")private WebElement firstname;
	@FindBy(id="lname")private WebElement lastname;
	@FindBy(xpath="(//input[@readonly='true'])[2]")private WebElement emailaddress;
	@FindBy(id="mobno")private WebElement mobilenumber;
	@FindBy(xpath="(//input[@readonly='true'])[2]")private WebElement registrationdate;
	@FindBy(id="submit")private WebElement updatebutton;
	
	WebDriver driver;
	
	public UserProfileUpdatePage()
	{
		driver=DriverManager.getDriver();
		PageFactory.initElements(driver,this);
	}
	
	public String getProfileUpdatePageTitle()
	{
		return profileUpdatePageTitle.getText();
	}
	
	public void clearUserFirstName()
	{
		firstname.clear();
		
	}
	public void updateUserFirstName(String FirstName)
	{
		
		firstname.sendKeys(FirstName);
	}
	
	public void clearUserLastName()
	{
		lastname.clear();
		
	}
	public void updateUserLastName(String LastName)
	{
		
		lastname.sendKeys(LastName);
	}
	
	public boolean checkEmailAddressFieldisReadOnly()
	{
		return emailaddress.getAttribute("readonly")!=null;
	}
	
	public void clearUserMobileNumber()
	{
		mobilenumber.clear();
		
	}
	
	public void enterMobileNumber(String MobileNumber)
	{
		
		mobilenumber.sendKeys(MobileNumber);
	}
	
	public boolean checkRegistrationDateisReadOnly()
	{
		return registrationdate.getAttribute("readonly")!=null;
	}
	
	public void clickonUpdateButton()
	{
		updatebutton.click();
	}
	
	public String getValidationMessageofFirstNameField()
	{
		return firstname.getAttribute("validationMessage");
	}
	
	public String getValidationMessageofLastNameField()
	{
		return lastname.getAttribute("validationMessage");
	}
	
	public String getValidationMessageofMobileNumberField()
	{
		return mobilenumber.getAttribute("validationMessage");
	}
	
	public String getUpdatedMobileNumber()
	{
		return mobilenumber.getAttribute("value");
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
