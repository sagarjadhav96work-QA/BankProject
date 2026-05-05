package com.automation.bankms.qa.pages.cashier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierSearchAccountHoldersPage {
	
	By searchaccountpagetitle=By.xpath("//h1[text()='Search Account Holder']");
	By enternameoraccountnumberormobilenumberinputbox=By.id("searchdata");
	By searchbutton=By.id("submit");
	By accountholdertable=By.xpath("//table[@class='table table-bordered']");
	By accountholderheaders=By.xpath("//table[@class='table table-bordered']/thead/tr/th");
	By srnoheader=By.xpath("//table[@class='table table-bordered']/thead/tr/th[1]");
	By nameheader=By.xpath("//table[@class='table table-bordered']/thead/tr/th[2]");
	By mobilenumberheader=By.xpath("//table[@class='table table-bordered']/thead/tr/th[3]");
	By emailheader=By.xpath("//table[@class='table table-bordered']/thead/tr/th[4]");
	By accountnumberheader=By.xpath("//table[@class='table table-bordered']/thead/tr/th[5]");
	By status=By.xpath("//table[@class='table table-bordered']/thead/tr/th[6]");
	By action=By.xpath("//table[@class='table table-bordered']/thead/tr/th[7]");
	By accountholderrows=By.xpath("//table[@class='table table-bordered']/tbody/tr");
	By viewbutton=By.xpath("//table[@class='table table-bordered']/tbody/tr/td[7]/a[1]");
	By transactionhistorybutton=By.xpath("//table[@class='table table-bordered']/tbody/tr/td[7]/a[2]");
	By listofaccountholdername=By.xpath("//table[@class='table table-bordered']/tbody/tr/td[2]");
	By listofaccountholdermobilenumber=By.xpath("//table[@class='table table-bordered']/tbody/tr/td[3]");
	By listofaccountholderaccountnumber=By.xpath("//table[@class='table table-bordered']/tbody/tr/td[5]");
	By listofstatus=By.xpath("//table[@class='table table-bordered']/tbody/tr/td[6]");
	
	
	WebDriver driver;
	WaitUtils wait;
	SoftAssert soft;
	protected static final Logger log=LogManagerUtil.getLogger(CashierSearchAccountHoldersPage.class);
	
	public CashierSearchAccountHoldersPage(WebDriver driver)
	{
		this.driver=driver;
		wait=new WaitUtils(driver, 20000);
		soft=new SoftAssert();
		
	}
	
	public String getSearchAccountHolderPageTitle()
	{
		log.info("Checking presence of account holder page title");
		return driver.findElement(searchaccountpagetitle).getText();
	}
	
	public void enterNameOrAccountnumberOrMobilenumber(String nameoraccountnumberormobilenumber)
	{
		log.info("Entering Name/Accountnumber/Mobilenumber");
		driver.findElement(enternameoraccountnumberormobilenumberinputbox).sendKeys(nameoraccountnumberormobilenumber);
	}
	
	public void clickOnSearchButton()
	{
		log.info("Clicking on Search Button");
		driver.findElement(searchbutton).click();
	}
	
	public void checkingPresenceOfElementsonSearchAccountHolderPage()
	{
		log.info("Checking presence of Search Account Holder Page Title");
		String titleofseachaccountholderpage = driver.findElement(searchaccountpagetitle).getText();
		soft.assertEquals(titleofseachaccountholderpage,"Search Account Holder","title not present on webpage");
		log.info("Checking Enter Name/Accountnumber/Mobilenumber Input Field is Enabled");
		boolean inputboxstatus = driver.findElement(enternameoraccountnumberormobilenumberinputbox).isEnabled();
		soft.assertTrue(inputboxstatus, "enter name or accountnumber or mobilenumber input box is enabled");
		log.info("Waiting For Enter Name/Accountnumber/Mobilenumber Input Field to be Clickable");
		wait.waitforElementToBeClickable(enternameoraccountnumberormobilenumberinputbox);
		log.info("Waiting For Search Button to be Clickable");
		wait.waitforElementToBeClickable(searchbutton);
		soft.assertAll();
		
	}
	
	public void checkPresenceofSearchAccountHolderPageTable()
	{
		
		log.info("Checking account holder table is present");
		wait.waitforElementToBePresent(accountholdertable);
		log.info("Checking all headers are present on the table");
		
		String[] listofheadervalues= {"S.No","Name","Mobile Number","Email","Account No.","Status","Action"};
		
		ArrayList<String>ExpectedList=new ArrayList<String>(Arrays.asList(listofheadervalues));
		
		
		List<WebElement> listofheaders = driver.findElements(accountholderheaders);
		
		ArrayList<String> ActualList=new ArrayList<String>();
		
		for(WebElement header:listofheaders)
		{
			String textofheader = header.getText();
			ActualList.add(textofheader);
			
			
		}
		soft.assertEquals(ActualList,ExpectedList, "All Headers are not present on the search account holders table");
		soft.assertAll();
		
	}
	
	public void clickOnViewButton()
	{
		log.info("Clicking on View Button");
		driver.findElement(viewbutton).click();
	}
	
	public void clickOnTransactionHistoryButton()
	{
		log.info("Clicking on Transaction History Button");
		driver.findElement(transactionhistorybutton).click();
	}
	
	public int checkNumberofAccountHoldersPresent()
	{
		return driver.findElements(accountholderrows).size();
	}
	
	public boolean checkWorkingofSearchFieldByName(String accountholdername)
	{
		List<WebElement> Namelist = driver.findElements(listofaccountholdername);
		for(WebElement Name:Namelist)
		{
			String nameofaccountholder = Name.getText().trim();
			if(!nameofaccountholder.contains(accountholdername))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean checkWorkingofSearchFieldByAccountNumber(String accountholderaccountnumber)
	{
		
		List<WebElement> accountnumberlist = driver.findElements(listofaccountholderaccountnumber);
		for(WebElement accountnumber:accountnumberlist)
		{
			String accountnumberofaccountholder = accountnumber.getText().trim();
			if(!accountnumberofaccountholder.equals(accountholderaccountnumber))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean checkWorkingofSearchFieldByMobileNumber(String accountholdermobilenumber)
	{
		
		List<WebElement> mobilenumberlist = driver.findElements(listofaccountholdermobilenumber);
		for(WebElement mobilenumber:mobilenumberlist)
		{
			String mobilenumberofaccountholder = mobilenumber.getText().trim();
			if(!mobilenumberofaccountholder.equals(accountholdermobilenumber))
			{
				return false;
			}
		}
		
		return true;
	}
	
			
	public void checkpresenceofViewButton()
	{
		log.info("Check presence of View Button");
		wait.waitforElementToBeClickable(viewbutton);
		
	}
	
	public void checkpresenceofTransactionHistoryButton()
	{
		
		log.info("Check presence of Transaction History Button");
		wait.waitforElementToBeClickable(transactionhistorybutton);
		
	}
	
	public boolean checkStatusBadge()
	{
		List<WebElement> StatusList = driver.findElements(listofstatus);
		for(WebElement Status:StatusList)
		{
			String statusvalue = Status.getText().trim();
			if(!statusvalue.contains("Approved")&&!statusvalue.contains("Rejected")&&!statusvalue.contains("New Request"))
			{
		
				return false;
				
			}
		}
		
		return true;
	}
	
	
	public String getValidationMessageofSearchInputField()
	{
		return driver.findElement(enternameoraccountnumberormobilenumberinputbox).getAttribute("validationMessage");
	}
	
	public void clearNameOrAccountnumberOrMobilenumber()
	{
		
		driver.findElement(enternameoraccountnumberormobilenumberinputbox).clear();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
