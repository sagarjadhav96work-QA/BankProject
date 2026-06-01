package com.automation.bankms.qa.pages.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.utils.WaitUtils;

public class TransactionHistorypage  {
	
	@FindBy(xpath="//h1[text()='Transaction History']")private WebElement transactionhistorypagetitle;
	@FindBy(xpath="//input[@type='search']")private WebElement searchbox;
	@FindBy(xpath="//select[@name='dataTable_length']")private WebElement entriesdropdown;
	@FindBy(id="dataTable_previous")private WebElement previousbutton;
	@FindBy(id="dataTable_next")private WebElement nextbutton;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[1]")private WebElement Srnoheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[2]")private WebElement transactionnumberheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[3]")private WebElement receivedorsentaccountnumberheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[4]")private WebElement amountheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[5]")private WebElement transactiontypeheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[6]")private WebElement statusheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[7]")private WebElement transactiondateheader;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr")private List<WebElement> transactionrows;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr")private WebElement firstrowdata;
	@FindBy(xpath="//table[@id='dataTable']")private WebElement transactionhistorytable;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[2]")private List<WebElement> listofTransactionNumber;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[3]")private List<WebElement> listofReceivedorSentAccountNumber;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[4]")private List<WebElement> listofAmount;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[5]")private List<WebElement> listofTransactionType;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[6]")private List<WebElement> listofStatus;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[7]")private List<WebElement> listofdates;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td") private WebElement Emptytransaction; 
	By statuscolumn=By.xpath("./td[6]");
	By Transactionnumbercolumn=By.xpath("./td[2]");
	By ReceivedorSentaccountNumbercolumn=By.xpath("./td[3]");
	By Amountcolumn=By.xpath("./td[4]");
	By TransactionTypecolumn=By.xpath("./td[5]");
	By TransactionDatecolumn=By.xpath("./td[7]");
	
	 Select select;
	 WaitUtils wait;
	 Actions act;
	 WebDriver driver;
	
	
	public TransactionHistorypage()
	{
		driver=DriverManager.getDriver();
		PageFactory.initElements(driver, this);
		wait=new WaitUtils(driver, 20000);
		act=new Actions(driver);
	}
	
	public String getTitleofTransactionHistoryPage()
	{
		return transactionhistorypagetitle.getText();
	}
	
	public void searchtransactionrecord(String transactionvalue)
	{
		searchbox.clear();
		 searchbox.sendKeys(transactionvalue);
	}
	
	public void selectentriesfromdropdown(String entriesrequired)
	{
		select=new Select(entriesdropdown);
		select.selectByValue(entriesrequired);
		
	}
	
	public void clickonnextbutton()
	{
		nextbutton.click();
	}
	
	public void clickonpreviousbutton()
	{
		previousbutton.click();
	}
	
	public boolean GoToNextPage()
	{
		String Receivedatt = nextbutton.getAttribute("class");
		if(!Receivedatt.contains("disabled"))
		{
			nextbutton.click();
			return true;
		}
		
		return false;
		
	}
	
	public boolean Checknextbuttonisenabled() {
	    
	    String classes = nextbutton.getAttribute("class");
	    return !classes.contains("disabled");
	}
	
	public String getfirstrowdata()
	{
		return firstrowdata.getText();
	}
	
	public boolean CheckWorkingofSearchFieldByTransactionNumber(String SearchTransactionNumberText)
	{
		long Transactionnumberinput = Long.parseLong(SearchTransactionNumberText);
		do {
			wait.waitforElementsToBeVisible(transactionrows);
			if(transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:transactionrows)
		{
			long transactionnumbertext = Long.parseLong(transactionrow.findElement(Transactionnumbercolumn).getText().trim());
			if(transactionnumbertext!=Transactionnumberinput)
			{
				System.out.println("Mismatch Transaction Number "+transactionnumbertext);
				return false;
			}
				
			
		}
		
		}
		while(GoToNextPage());
		return true;
	}
	
	public boolean CheckWorkingofSearchFieldByReceivedorSentAccountNumber(String SearchReceivedorSentAccountNumberText)
	{
		long ReceivedorSentAccountNumberinput = Long.parseLong(SearchReceivedorSentAccountNumberText);
		do {
			wait.waitforElementsToBeVisible(transactionrows);
			if(transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:transactionrows)
		{
			long ReceivedorSentAccountNumbertext = Long.parseLong(transactionrow.findElement(ReceivedorSentaccountNumbercolumn).getText().trim());
			if(ReceivedorSentAccountNumbertext!=ReceivedorSentAccountNumberinput)
			{
				System.out.println("Mismatch Received or Sent Account Number "+ReceivedorSentAccountNumbertext);
				return false;
			}
				
			
		}
		
		}
		while(GoToNextPage());
		return true;
	}
	
	public boolean CheckWorkingofSearchFieldByAmount(String SearchAmount)
	{
		long Amountinput = Long.parseLong(SearchAmount);
		do {
			wait.waitforElementsToBeVisible(transactionrows);
			if(transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:transactionrows)
		{
			long Amounttext = Long.parseLong(transactionrow.findElement(Amountcolumn).getText().trim());
			if(Amounttext!=Amountinput)
			{
				System.out.println("Mismatch Amount "+Amounttext);
				return false;
			}
				
			
		}
		
		}
		while(GoToNextPage());
		return true;
	}
	
	public boolean CheckWorkingofSearchFieldByTransactionType(String SearchTransactionTypeText)
	{
		
		do {
		
			wait.waitforElementsToBeVisible(transactionrows);
			if(transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:transactionrows)
		{
			String TransactionTypetext = transactionrow.findElement(TransactionTypecolumn).getText().trim();
			if(!TransactionTypetext.equalsIgnoreCase(SearchTransactionTypeText))
			{
				System.out.println("Mismatch Transaction Type "+TransactionTypetext);
				return false;
			}
				
			
		}
		
		}
		while(GoToNextPage());
		return true;
	}
	
	public boolean CheckWorkingofSearchFieldByStatus(String SearchStatusText)
	{
		
		do {
		
			wait.waitforElementsToBeVisible(transactionrows);
			if(transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:transactionrows)
		{
			String statustext = transactionrow.findElement(statuscolumn).getText().trim();
			if(!statustext.equalsIgnoreCase(SearchStatusText))
			{
				System.out.println("Mismatch Transaction Status "+statustext);
				return false;
			}
				
			
		}
		
		}
		while(GoToNextPage());
		return true;
	}
	
	
	public boolean CheckWorkingofSearchFieldByTransactionDate(String SearchTransactionDateText)
	{
		
		do {
		
			wait.waitforElementsToBeVisible(transactionrows);
			if(transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:transactionrows)
		{
			String TransactionDateandtimetext = transactionrow.findElement(TransactionDatecolumn).getText().trim();
			String TransactionDatetext = TransactionDateandtimetext.split(" ")[0];
			
			
			if(!TransactionDatetext.equalsIgnoreCase(SearchTransactionDateText))
			{
				System.out.println("Mismatch Transaction Type "+TransactionDatetext);
				return false;
			}
				
			
		}
		
		}
		while(GoToNextPage());
		return true;
	}
	
	public boolean ValidateEntriesCount(int ExpectedCount)
	{
	     int actualCount = transactionrows.size();
	     return actualCount>0 && actualCount<=ExpectedCount;
	}
	
	public void ClickonTransactionNumberHeader()
	{
		transactionnumberheader.click();
	}
	
	public void DoubleClickonTransactionNumberHeader()
	{
		
		act.doubleClick(transactionnumberheader).perform();
		
	}
	
	public void ClickonReceivedorSentAccountnumberheader()
	{
		receivedorsentaccountnumberheader.click();
	}
	
	public void DoubleClickonReceivedorSentAccountnumberheader()
	{
		
		act.doubleClick(receivedorsentaccountnumberheader).perform();
		
	}
	
	public void ClickonAmountheader()
	{
		amountheader.click();
	}
	
	public void DoubleClickonAmountheader()
	{
		
		act.doubleClick(amountheader).perform();
		
	}
	
	public void ClickonTransactionTypeheader()
	{
		transactiontypeheader.click();
	}
	
	public void DoubleClickonTransactionTypeheader()
	{
		
		act.doubleClick(transactiontypeheader).perform();
		
	}
	
	public void ClickonTransactionDateheader()
	{
		transactiondateheader.click();
	}
	
	public void DoubleClickonTransactionDateheader()
	{
		
		act.doubleClick(transactiondateheader).perform();
		
	}
	
	public void ClickonStatusheader()
	{
		statusheader.click();
	}
	
	public void DoubleClickonStatusheader()
	{
		
		act.doubleClick(statusheader).perform();
		
	}
	
	public ArrayList<Long> ListofTransactionNumber()
	{
		ArrayList<Long>TransactionNumberList=new ArrayList<Long>();
		for(WebElement transactionnumber:listofTransactionNumber)
		{
			TransactionNumberList.add(Long.parseLong(transactionnumber.getText().trim()));
		}
		
		return TransactionNumberList;
	}
	
	public ArrayList<Long> ListofReceivedorSentAccountNumber()
	{
		ArrayList<Long>ReceivedorSentAccountNumberList=new ArrayList<Long>();
		for(WebElement receivedorsentaccount:listofReceivedorSentAccountNumber)
		{
			String accountnumbertext = receivedorsentaccount.getText().trim();
			if(accountnumbertext.isEmpty())
			{
				System.out.println("Empty cell found,Skipping...");
				continue;
			}
			
			try {
			ReceivedorSentAccountNumberList.add(Long.parseLong(receivedorsentaccount.getText().trim()));
			}
			catch(NumberFormatException N)
			{
				System.out.println("Invalid Number Found "+accountnumbertext);
			}
		}
		
		return ReceivedorSentAccountNumberList;
	}
	
	public ArrayList<Long> ListofAmount()
	{
		ArrayList<Long>AmountList=new ArrayList<Long>();
		for(WebElement Amount:listofAmount)
		{
			AmountList.add(Long.parseLong(Amount.getText().trim()));
		}
		
		return AmountList;
	}
	
	public ArrayList<String> ListofTransactionType()
	{
		ArrayList<String> Transactiontypelist=new ArrayList<String>();
		for(WebElement transactiontype:listofTransactionType)
		{
			Transactiontypelist.add(transactiontype.getText().trim());
		}
		return Transactiontypelist;
	}
	
	public ArrayList<String> ListofStatus()
	{
		ArrayList<String> Statuslist=new ArrayList<String>();
		for(WebElement status:listofStatus)
		{
			Statuslist.add(status.getText().trim());
		}
		return Statuslist;
	}
	
	public ArrayList<LocalDateTime> ListofTransactionDate()
	{
		DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ArrayList<LocalDateTime> datetimelist=new ArrayList<LocalDateTime>();
		for(WebElement date:listofdates)
		{
			datetimelist.add(LocalDateTime.parse(date.getText().trim(),datetimeformatter));
		} 
		return datetimelist;
		
	}
	
	public String gettextforinvalidsearchinput()
	{
		return Emptytransaction.getText();
	}
	
	public String getselectedentries() {
	    WebElement dropdown = driver.findElement(By.xpath("//select[@name='dataTable_length']"));
	    Select select = new Select(dropdown);
	    return select.getFirstSelectedOption().getText();
	}

	public void refreshage() {
	    driver.navigate().refresh();
	}

}
