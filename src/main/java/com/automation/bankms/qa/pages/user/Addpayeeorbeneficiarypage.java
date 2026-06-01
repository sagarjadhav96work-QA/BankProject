package com.automation.bankms.qa.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.bankms.qa.driver.DriverManager;

public class Addpayeeorbeneficiarypage {
	
	@FindBy(xpath="//h3[text()='Add Payee / beneficiary']")private WebElement Addpayeeorbeneficiarypagetitle;
	@FindBy(id="accountnumber")private WebElement Accountnumberinputfield;
	@FindBy(id="conaccountnumber")private WebElement Confirmaccountnumberinputfield;
	@FindBy(id="acountholdername")private WebElement Accountholdernameinputfield;
	@FindBy(id="submit")private WebElement Addpayeeorbeneficiarysubmitbutton;
	@FindBy(xpath="//div[contains(text(),'payee/beneficiary.')]")private WebElement openingrequestunapprovedtext;
	WebDriver driver;
	
	public Addpayeeorbeneficiarypage()
	{
		driver=DriverManager.getDriver();
		PageFactory.initElements(driver,this);
	}
	
	public void enteraccountnumber(String accountnumber)
	{
		Accountnumberinputfield.sendKeys(accountnumber);
	}
	
	public void enterconfirmaccountnumber(String confirmaccountnumber)
	{
		Confirmaccountnumberinputfield.sendKeys(confirmaccountnumber);
	}
	
	public void enteraccountholdername(String accountholdername)
	{
		Accountholdernameinputfield.sendKeys(accountholdername);
	}
	
	public Managepayeeorbeneficiarypage clickonaddpayeeorbeneficiarysubmitbutton()
	{
		Addpayeeorbeneficiarysubmitbutton.click();
		return new Managepayeeorbeneficiarypage();
	}
	
	public String getaccountnumbervalidationmessage()
	{
		return Accountholdernameinputfield.getAttribute("validationMessage");
	}
	
	public String getunapprovedaccountrequesttext()
	{
		return openingrequestunapprovedtext.getText();
	}
	
	public String getAddPayeeorBeneficiaryTitletext()
	{
		return Addpayeeorbeneficiarypagetitle.getText();
	}
	
	
	

}
