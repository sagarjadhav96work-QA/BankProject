package com.automation.bankms.qa.pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Useraccountopeningpage {

	WebDriver driver;
    Select select;
	@FindBy(xpath="//h6[text()='Account Opening Details']")private WebElement accountopeningpagetitle;
	@FindBy(xpath="//select[@name='addproof']")private WebElement addressproofdropdown;
	@FindBy(id="addpidnum")private WebElement addressproofnumber;
	@FindBy(id="attaddproof")private WebElement uploadaddressprooflink;
	@FindBy(id="uplpancard")private WebElement uploadpancardlink;
	@FindBy(id="pancardnum")private WebElement pancardnumber;
	@FindBy(xpath="//textarea[@name='address']")private WebElement useraddress;
	@FindBy(id="dob")private WebElement userdateofbirth;
	@FindBy(id="tandc")private WebElement termsandconditioncheckbox;
	@FindBy(id="submit")private WebElement submitbutton;
	@FindBy(xpath="//h3[text()='Account Details']")private WebElement accountdetailstitle;
	
	
	
	public Useraccountopeningpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void selectvoteridfromdropdown()
	{
		select=new Select(addressproofdropdown);
		select.selectByValue("Voter ID");		
	}
	
	public void selectaadharcardfromdropdown()
	{
		select=new Select(addressproofdropdown);
		select.selectByValue("Adhar Card");
	}
	
	public void selectdrivinglicensefromdropdown()
	{
		select=new Select(addressproofdropdown);
		select.selectByValue("Driving Licence");
	}
	
	public void selectpassportfromdropdown()
	{
		select=new Select(addressproofdropdown);
		select.selectByValue("Passport");
	}
	
	public void enteraddressproofnumber(String addressidnumber)
	{
		addressproofnumber.sendKeys(addressidnumber);
	}
	
	public void uploadaddressproof(String addressproofpath)
	{
		uploadaddressprooflink.sendKeys(addressproofpath);
	}
	
	public void uploadpancard(String addressproofpath)
	{
		uploadpancardlink.sendKeys(addressproofpath);
	}
	
	public void enterpancardnumber(String pancardno)
	{
		pancardnumber.sendKeys(pancardno);
	}
	
	public void enteraddress(String address)
	{
		useraddress.sendKeys(address);
	}
	
	public void selectdateofbirth(String dateofbirth)
	{
		userdateofbirth.sendKeys(dateofbirth);
	}
	
	public void clickontermsandconditioncheckbox()
	{
		termsandconditioncheckbox.click();
	}
	
	public void clickonaccountopeningsubmitbutton()
	{
		submitbutton.click();
	}
	
	public String getAddressProofValidationMessage()
	{
	    return addressproofnumber.getAttribute("validationMessage");
	}
	
	public String getAccountDetailsTitleText()
	{
	    return accountdetailstitle.getText();	
	}

	
	
	
}
