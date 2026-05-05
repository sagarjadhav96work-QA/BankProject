package com.automation.bankms.qa.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage  {

	
	@FindBy(xpath="//h3[text()='Change Password']")private WebElement changepasswordpagetitle;
	@FindBy(id="currentpassword")private WebElement currentpasswordinputfield;
	@FindBy(id="newpassword")private WebElement newpasswordinputfield;
	@FindBy(id="confirmpassword")private WebElement confirmpasswordinputfield;
	@FindBy(id="submit")private WebElement changebutton;
	WebDriver driver;
	
	public ChangePasswordPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTitleTextofChangePasswordPage()
	{
		return changepasswordpagetitle.getText();
	}
	
	public void enterCurrentPassword(String CurrentPassword)
	{
		currentpasswordinputfield.sendKeys(CurrentPassword);
	}
	
	public void enterNewPassword(String NewPassword)
	{
		newpasswordinputfield.sendKeys(NewPassword);
	}
	
	public void enterConfirmPassword(String ConfirmPassword)
	{
		confirmpasswordinputfield.sendKeys(ConfirmPassword);
	}
	
	public ChangePasswordPage clickOnChangeButton()
	{
		changebutton.click();
		return new ChangePasswordPage(driver);
	}
	
	public String getValidationTextofCurrentPasswordInputField()
	{
		return currentpasswordinputfield.getAttribute("validationMessage");
	}
	
	public String getValidationTextofNewPasswordInputField()
	{
		return newpasswordinputfield.getAttribute("validationMessage");
	}
	
	public String getValidationTextofConfirmPasswordInputField()
	{
		return confirmpasswordinputfield.getAttribute("validationMessage");
	}
	
	public String getValuepresentinCurrentPasswordInputField()
	{
		return currentpasswordinputfield.getAttribute("value");
	}
	
	public String getValuepresentinNewPasswordInputField()
	{
		return newpasswordinputfield.getAttribute("value");
	}
	
	public String getValuepresentinConfirmPasswordInputField()
	{
		return confirmpasswordinputfield.getAttribute("value");
	}
	
	public void clickOnRefreshButton()
	{
		driver.navigate().refresh();
		
	}
	
	
}
