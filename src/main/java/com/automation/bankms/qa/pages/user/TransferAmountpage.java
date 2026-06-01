package com.automation.bankms.qa.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.bankms.qa.driver.DriverManager;

public class TransferAmountpage {

	@FindBy(xpath="//h3[text()='Transfer Amount']")private WebElement Transferamountpagetitle;
	@FindBy(xpath="(//div[@class='form-group row'])[1]/span[1]/text()[1]")private WebElement Accountbalancetext;
	@FindBy(name="amount")private WebElement Amountinputfield;
	@FindBy(id="submit")private WebElement Transferamountsubmitbutton;
	
	WebDriver driver;
	
	public TransferAmountpage()
	{
		driver=DriverManager.getDriver();
		PageFactory.initElements(driver,this);
	}
	
	public String GetTransferAmountPageText()
	{
		return Transferamountpagetitle.getText();
	}
	
	public String GetCurrentAccountBalance()
	{
		return Accountbalancetext.getText();
	}
	
	public void EnterTransferAmount(String EnterAmount)
	{
		Amountinputfield.sendKeys(EnterAmount);
	}
	
	public void ClickonSubmitButton()
	{
		Transferamountsubmitbutton.click();
	}
	
	public String getValidationMessageofAmountField()
	{
		return Amountinputfield.getAttribute("validationMessage");
	}
	
	public String getValuePresentinAmountField()
	{
		return Amountinputfield.getAttribute("value");
	}
	
	
	
	
	
	
}
