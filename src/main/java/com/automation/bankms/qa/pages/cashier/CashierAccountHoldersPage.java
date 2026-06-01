package com.automation.bankms.qa.pages.cashier;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierAccountHoldersPage {
	
	By accountholderspagetitle=By.xpath("//h1[text()='Details of Account Holders']");
	By searchinputbox=By.xpath("//input[@type='search']");
	By showeentriesdropdown=By.xpath("//select[@name='dataTable_length']");
	By srnoheader=By.xpath("//table[@id='dataTable']/thead/tr/th[1]");
	By nameheader=By.xpath("//table[@id='dataTable']/thead/tr/th[2]");
	By mobilenumberheader=By.xpath("//table[@id='dataTable']/thead/tr/th[3]");
	By emailheader=By.xpath("//table[@id='dataTable']/thead/tr/th[4]");
	By accountnumberheader=By.xpath("//table[@id='dataTable']/thead/tr/th[5]");
	By accountuseridnumberheader=By.xpath("//table[@id='dataTable']/thead/tr/th[6]");
	By statusheader=By.xpath("//table[@id='dataTable']/thead/tr/th[7]");
	By actionheader=By.xpath("//table[@id='dataTable']/thead/tr/th[8]");
	By viewbutton=By.xpath("//table[@id='dataTable']/tbody/tr/td[8]/a");
	By previousbutton=By.xpath("//li[@id='dataTable_previous']");
	By nextbutton=By.xpath("//li[@id='dataTable_next']");
	By accountholdersrow=By.xpath("//table[@id='dataTable']/tbody/tr");
	By listofheaders=By.xpath("//table[@id='dataTable']/thead/tr/th");
	By listofsrno=By.xpath("//table[@id='dataTable']/tbody/tr/td[1]");
	By listofnames=By.xpath("//table[@id='dataTable']/tbody/tr/td[2]");
	By listofmobilenumber=By.xpath("//table[@id='dataTable']/tbody/tr/td[3]");
	By listofemails=By.xpath("//table[@id='dataTable']/tbody/tr/td[4]");
	By listofaccountnumber=By.xpath("//table[@id='dataTable']/tbody/tr/td[5]");
	By listofaccountuseridnumber=By.xpath("//table[@id='dataTable']/tbody/tr/td[6]");
	By listofstatus=By.xpath("//table[@id='dataTable']/tbody/tr/td[7]");
	By listofaction=By.xpath("//table[@id='dataTable']/tbody/tr/td[8]/a");
	By accountholdertable=By.xpath("//table[@id='dataTable']");
	By firstrowofaccountholderlist=By.xpath("//table[@id='dataTable']/tbody/tr/td");
	By currentpage=By.xpath("//li[@class='paginate_button page-item active']/a");
	
	protected static final Logger log=LogManagerUtil.getLogger(CashierAccountHoldersPage.class);
	WebDriver driver;
	SoftAssert soft;
	WaitUtils wait;
	
	
	public CashierAccountHoldersPage()
	{
		driver=DriverManager.getDriver();
		wait=new WaitUtils(driver, 20000);
		soft=new SoftAssert();
	}
	
	public String getTitleofAccountHoldersPage()
	{
		log.info("Checking the title of account holders page");
		return driver.findElement(accountholderspagetitle).getText();
	}
	
	public void selectsearchentriesvalue(String value)
	{
		log.info("Selecting Value from search entries dropdown");
		Select select=new Select(driver.findElement(showeentriesdropdown));
		select.selectByValue(value);
	}
	
	public void enterInputinSearchField(String inputvalue)
	{
		driver.findElement(searchinputbox).sendKeys(inputvalue);
	}
	
	public void clickOnNextButton()
	{
		String attributeofnextbutton = driver.findElement(nextbutton).getAttribute("class");
		if(!attributeofnextbutton.contains("disabled"))
		{
			 driver.findElement(nextbutton).click();
		}
	}
	
	public void clickOnPreviousButton()
	{
		String attributeofpreviousbutton = driver.findElement(previousbutton).getAttribute("class");
		if(!attributeofpreviousbutton.contains("disabled"))
		{
			 driver.findElement(previousbutton).click();
		}
	}
	
	public void clickOnViewButton()
	{
		
		driver.findElement(viewbutton).click();
	}
	
	public void clickonSrNoHeader()
	{
		driver.findElement(srnoheader).click();
	}
	
	public void doubleClickonSrNoHeader()
	{
		driver.findElement(srnoheader).click();
		driver.findElement(srnoheader).click();
	}
	
	public void clickonNameHeader()
	{
		driver.findElement(nameheader).click();
	}
	
	public void doubleClickonNameHeader()
	{
		driver.findElement(nameheader).click();
		driver.findElement(nameheader).click();
	}
	
	public void clickonMobileNumberHeader()
	{
		driver.findElement(mobilenumberheader).click();
	}
	
	public void doubleClickonMobileNumberHeader()
	{
		driver.findElement(mobilenumberheader).click();
		driver.findElement(mobilenumberheader).click();
	}
	
	public void clickonEmailHeader()
	{
		driver.findElement(emailheader).click();
	}
	
	public void doubleClickonEmailHeader()
	{
		driver.findElement(emailheader).click();
		driver.findElement(emailheader).click();
	}
	
	public void clickonAccountNumberHeader()
	{
		driver.findElement(accountnumberheader).click();
	}
	
	public void doubleClickonAccountNumberHeader()
	{
		driver.findElement(accountnumberheader).click();
		driver.findElement(accountnumberheader).click();
	}
	
	public void clickonAccountUserIDNumberHeader()
	{
		driver.findElement(accountuseridnumberheader).click();
	}
	
	public void doubleClickonAccountUserIDNumberHeader()
	{
		driver.findElement(accountuseridnumberheader).click();
		driver.findElement(accountuseridnumberheader).click();
	}
	
	public void clickonStatusHeader()
	{
		driver.findElement(statusheader).click();
	}
	
	public void doubleClickonStatusHeader()
	{
		driver.findElement(statusheader).click();
		driver.findElement(statusheader).click();
	}
	
	public void clickonActionHeader()
	{
		driver.findElement(actionheader).click();
	}
	
	public void doubleClickonActionHeader()
	{
		driver.findElement(actionheader).click();
		driver.findElement(actionheader).click();
	}
	
	public boolean goToNextPage()
	{
		String attributeofnextbutton = driver.findElement(nextbutton).getAttribute("class");
		if(!attributeofnextbutton.contains("disabled"))
		{
			wait.waitforElementToBePresent(accountholdersrow);
			driver.findElement(nextbutton).click();	
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public String getAttributeofLastPage()
	{
		return driver.findElement(nextbutton).getAttribute("class");
	}
	
	
	public boolean goToPreviousPage()
	{
		String attributeofpreviousbutton = driver.findElement(previousbutton).getAttribute("class");
		if(!attributeofpreviousbutton.contains("disabled"))
		{
			wait.waitforElementToBePresent(accountholdersrow);
			driver.findElement(previousbutton).click();	
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public String getAttributeofFirstPage()
	{
		return driver.findElement(previousbutton).getAttribute("class");
	}
	
	public ArrayList<Integer> getListofSrNo()
	{
		ArrayList<Integer> srnolist=new ArrayList<Integer>();
		List<WebElement> elements=driver.findElements(listofsrno);
		for(WebElement srno:elements)
		{
			
			srnolist.add(Integer.parseInt(srno.getText().trim()));
			
		}
		
		return srnolist;
	}
	
	public ArrayList<String> getListofName()
	{
		ArrayList<String> namelist=new ArrayList<String>();
		List<WebElement> elements=driver.findElements(listofnames);
		for(WebElement name:elements)
		{
			
			namelist.add(name.getText().trim());
			
		}
		
		return namelist;
	}
	
	public ArrayList<Long> getListofMobileNumber()
	{
		ArrayList<Long> mobilenolist=new ArrayList<Long>();
		List<WebElement> elements=driver.findElements(listofmobilenumber);
		for(WebElement mobilenumber:elements)
		{
			
			mobilenolist.add(Long.parseLong(mobilenumber.getText().trim()));
			
		}
		
		return mobilenolist;
	}
	
	public ArrayList<String> getListofEmail()
	{
		ArrayList<String> Emaillist=new ArrayList<String>();
		List<WebElement> elements=driver.findElements(listofemails);
		for(WebElement email:elements)
		{
			
			Emaillist.add(email.getText().trim());
			
		}
		
		return Emaillist;
	}
	
	public ArrayList<Long> getListofAccountNumber()
	{
		ArrayList<Long> accountnolist=new ArrayList<Long>();
		List<WebElement> elements=driver.findElements(listofaccountnumber);
		for(WebElement accountno:elements)
		{
			
			accountnolist.add(Long.parseLong(accountno.getText().trim()));
			
		}
		
		return accountnolist;
	}
	
	public ArrayList<Long> getListofUserIDNumber()
	{
		ArrayList<Long> UserIDList=new ArrayList<Long>();
		List<WebElement> elements=driver.findElements(listofaccountuseridnumber);
		for(WebElement userid:elements)
		{
			
			UserIDList.add(Long.parseLong(userid.getText().trim()));
			
		}
		
		return UserIDList;
	}
	
	public ArrayList<String> getListofStatus()
	{
		ArrayList<String> statusList=new ArrayList<String>();
		List<WebElement> elements=driver.findElements(listofstatus);
		for(WebElement status:elements)
		{
			
			statusList.add(status.getText().trim());
			
		}
		
		return statusList;
	}
	
	public void checkLaunchofAccountHolderspage()
	{
		log.info("Checking page tile is present on Account Holders Page");
		String titleofaccountholderspage = driver.findElement(accountholderspagetitle).getText();
		soft.assertEquals(titleofaccountholderspage, "Details of Account Holders","title of account holders page is not present");
		log.info("Checking search input box is enabled");
		soft.assertTrue(driver.findElement(searchinputbox).isEnabled(),"Search Input Box is not Enabled");
		log.info("Checking view button is enabled");
		soft.assertTrue(driver.findElement(viewbutton).isEnabled(),"View Button is not Enabled");
		
		
	
		
	}
	
	public boolean checkAccountHoldersPageTableisLoaded()
	{
		log.info("Checking Account Holders Page Table is loaded");
           List<WebElement> headerslist = driver.findElements(listofheaders);
           boolean status=false;
		
		for(WebElement header:headerslist)
		{
			String headervalue = header.getText().trim();
			if(headervalue.contains("S.No")||headervalue.contains("Name")||headervalue.contains("Mobile Number")||headervalue.contains("Email")
					||headervalue.contains("Account Number")||headervalue.contains("Account Userid Number")||headervalue.contains("Status")||
					headervalue.contains("Action"))
			{
				status=true;
			}
			
		}
		
		
		return status;
		
		
	}
	
	public void waitforAccountHoldersPageTableToLoad()
	{
		log.info("waiting for loading of account holder table");
		wait.waitforElementToBeVisible(accountholdertable);
	}
	
	public int checkingNoofEntriesinSinglePage()
	{
		int noofentries = driver.findElements(accountholdersrow).size();
		return noofentries;
	}
	
	public boolean checkWorkingofSearchInputFieldByName(String NameValue)
	{
		
		String inputname=NameValue;
		do {
		
		wait.waitforElementToBePresent(accountholdersrow);
		List<WebElement> Namelist= driver.findElements(listofnames);
		if(Namelist.isEmpty())
		{
			System.out.println("No Records Found");
			return false;
		}
		
		for(WebElement name:Namelist)
		{
			String textvalueofname = name.getText().trim();
			
			if(!textvalueofname.toLowerCase().contains(inputname.toLowerCase()))
			{
				return false;
			}
			
		}
		
		}
		while(goToNextPage());
		return true;
	}
	
	
	public boolean checkWorkingofSearchInputFieldByMobileNumber(String MobileNumberValue)
	{
		
		String inputmobilenumber=MobileNumberValue;
		do {
		
		wait.waitforElementToBePresent(accountholdersrow);
		List<WebElement> MobileNumberlist= driver.findElements(listofmobilenumber);
		if(MobileNumberlist.isEmpty())
		{
			System.out.println("No Records Found");
			return false;
		}
		
		for(WebElement MobileNumber:MobileNumberlist)
		{
			String valueofMobileNumber =MobileNumber.getText().trim();
			
			if(!inputmobilenumber.contains(valueofMobileNumber))
			{
				return false;
			}
			
		}
		
		}
		while(goToNextPage());
		return true;
	}
	
	public boolean checkWorkingofSearchInputFieldByEmail(String EmailValue)
	{
		
		String inputemail=EmailValue;
		do {
		
		wait.waitforElementToBePresent(accountholdersrow);
		List<WebElement> Emaillist= driver.findElements(listofemails);
		if(Emaillist.isEmpty())
		{
			System.out.println("No Records Found");
			return false;
		}
		
		for(WebElement Email:Emaillist)
		{
			String valueofEmail = Email.getText().trim();
			
			if(!inputemail.equalsIgnoreCase(valueofEmail))
			{
				return false;
			}
			
		}
		
		}
		while(goToNextPage());
		return true;
	}
	
	public boolean checkWorkingofSearchInputFieldByAccountNumber(String AccountNumberValue)
	{
		
		String inputaccountnumber=AccountNumberValue;
		do {
		
		wait.waitforElementToBePresent(accountholdersrow);
		List<WebElement> AccountNumberlist= driver.findElements(listofaccountnumber);
		if(AccountNumberlist.isEmpty())
		{
			System.out.println("No Records Found");
			return false;
		}
		
		for(WebElement AccountNumber:AccountNumberlist)
		{
			String valueofaccountnumber = AccountNumber.getText().trim();
			
			if(!inputaccountnumber.contains(valueofaccountnumber))
			{
				return false;
			}
			
		}
		
		}
		while(goToNextPage());
		return true;
	}
	
	public boolean checkWorkingofSearchInputFieldByAccountUserIDNumber(String AccountUserIDNumberValue)
	{
		
		String inputaccountuseridnumber=AccountUserIDNumberValue;
		do {
		
		wait.waitforElementToBePresent(accountholdersrow);
		List<WebElement> AccountUserIDNumberlist= driver.findElements(listofaccountuseridnumber);
		if(AccountUserIDNumberlist.isEmpty())
		{
			System.out.println("No Records Found");
			return false;
		}
		
		for(WebElement AccountUserIDNumber:AccountUserIDNumberlist)
		{
			String valueofaccountuseridnumber = AccountUserIDNumber.getText().trim();
			
			if(!inputaccountuseridnumber.contains(valueofaccountuseridnumber))
			{
				return false;
			}
			
		}
		
		}
		while(goToNextPage());
		return true;
	}
	
	public boolean checkWorkingofSearchInputFieldByStatus(String StatusValue)
	{
		
		String inputstatus=StatusValue;
		do {
		
		wait.waitforElementToBePresent(accountholdersrow);
		List<WebElement> Statuslist= driver.findElements(listofstatus);
		if(Statuslist.isEmpty())
		{
			System.out.println("No Records Found");
			return false;
		}
		
		for(WebElement Status:Statuslist)
		{
			String valueofstatus = Status.getText().trim();
			
			if(!inputstatus.contains(valueofstatus))
			{
				return false;
			}
			
		}
		
		}
		while(goToNextPage());
		return true;
	}
	
	
	public boolean checkWorkingofSearchInputFieldByAction(String ActionValue)
	{
		
		String inputaction=ActionValue;
		do {
		
		wait.waitforElementToBePresent(accountholdersrow);
		List<WebElement> Actionlist= driver.findElements(listofaction);
		if(Actionlist.isEmpty())
		{
			System.out.println("No Records Found");
			return false;
		}
		
		for(WebElement Action:Actionlist)
		{
			String valueofaction = Action.getText().trim();
			
			if(!inputaction.contains(valueofaction))
			{
				return false;
			}
			
		}
		
		}
		while(goToNextPage());
		return true;
	}
	
	public String checkWorkingofSearchInputFieldBySQLInput()
	{
	
		wait.waitforElementToBePresent(accountholdersrow);
		return  driver.findElement(firstrowofaccountholderlist).getText();
		
	}
	
	public String checkWorkingofSearchInputFieldByInputWithSpecialCharacters()
	{
	
		wait.waitforElementToBePresent(accountholdersrow);
		return  driver.findElement(firstrowofaccountholderlist).getText();
		
	}
	
	
	
	
	public ArrayList<String> getNameListofAccountHolders()
	{
		
			
			ArrayList<String> actualUIlist=new ArrayList<String>();
			List<WebElement> NameList = driver.findElements(listofnames);
			for(WebElement Name:NameList)
			{
				String extractednamefromtable = Name.getText().trim();
				actualUIlist.add(extractednamefromtable);
			}
			
			
			
		return actualUIlist;
		
		
	}
	
	public ArrayList<Long> getMobileNumberListofAccountHolders()
	{
		
			
			ArrayList<Long> actualUIlist=new ArrayList<Long>();
			List<WebElement> MobileNumberList = driver.findElements(listofmobilenumber);
			for(WebElement Mobilenumber:MobileNumberList)
			{
				String extractednamefromtable = Mobilenumber.getText().trim();
				actualUIlist.add(Long.parseLong(extractednamefromtable));
			}
			
			
			
		return actualUIlist;
		
		
	}
	
	public ArrayList<String> getEmailListofAccountHolders()
	{
		
			
			ArrayList<String> actualUIlist=new ArrayList<String>();
			List<WebElement> EmailList = driver.findElements(listofemails);
			for(WebElement Email:EmailList)
			{
				String extractedemailfromtable = Email.getText().trim();
				actualUIlist.add(extractedemailfromtable);
			}
			
			
			
		return actualUIlist;
		
		
	}
	
	public ArrayList<Long> getAccountNumberListofAccountHolders()
	{
		
			
			ArrayList<Long> actualUIlist=new ArrayList<Long>();
			List<WebElement> AccountNumberList = driver.findElements(listofaccountnumber);
			for(WebElement Accountnumber:AccountNumberList)
			{
				String extractedaccountnumberfromtable = Accountnumber.getText().trim();
				actualUIlist.add(Long.parseLong(extractedaccountnumberfromtable));
			}
			
			
			
		return actualUIlist;
		
		
	}
	
	
	public ArrayList<Long> getUserIDListofAccountHolders()
	{
		
			
			ArrayList<Long> actualUIlist=new ArrayList<Long>();
			List<WebElement> UserIDList = driver.findElements(listofaccountuseridnumber);
			for(WebElement UserID:UserIDList)
			{
				String extractedauseridfromtable = UserID.getText().trim();
				actualUIlist.add(Long.parseLong(extractedauseridfromtable));
			}
			
			
			
		return actualUIlist;
		
		
	}
	
	
	
	public String getNumberOfCurrentPage()
	{
		return driver.findElement(currentpage).getText();
	}
	
	public void RefreshingWebPage()
	{
		driver.navigate().refresh();
	}
	
	
	
	
	

}
