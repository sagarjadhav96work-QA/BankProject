package com.automation.bankms.qa.pages.user;



import java.time.LocalDate;
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

public class TransactionReportpage  {
	
	@FindBy(xpath="//h1[text()='Transaction Report']")private WebElement TransactionReportpagetitle;
	@FindBy(id="fromdate")private WebElement FromDateinputfield;
	@FindBy(id="todate")private WebElement ToDateinputfield;
	@FindBy(id="submit")private WebElement SubmitButton;
	@FindBy(xpath="//input[@type='search']")private WebElement Searchinputbox;
	@FindBy(xpath="//select[@name='dataTable_length']")private WebElement EntriesDropdown;
	@FindBy(id="dataTable_previous")private WebElement Previousbutton;
	@FindBy(id="dataTable_next")private WebElement Nextbutton;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[2]")private WebElement TransactionNumberheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[3]")private WebElement ReceivedorSentAccountnumberheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[4]")private WebElement Amountheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[5]")private WebElement TransactionTypeheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[6]")private WebElement Statusheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[7]")private WebElement TransactionDateheader;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[7]")private List<WebElement> listofdates;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr")private List<WebElement> Transactionrows;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[2]")private List<WebElement> listofTransactionNumber;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[3]")private List<WebElement> listofReceivedorSentAccountNumber;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[4]")private List<WebElement> listofAmount;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[5]")private List<WebElement> listofTransactionType;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[6]")private List<WebElement> listofStatus;
	By statuscolumn=By.xpath("./td[6]");
	By Transactionnumbercolumn=By.xpath("./td[2]");
	By ReceivedorSentaccountNumbercolumn=By.xpath("./td[3]");
	By Amountcolumn=By.xpath("./td[4]");
	By TransactionTypecolumn=By.xpath("./td[5]");
	By TransactionDatecolumn=By.xpath("./td[7]");
	WaitUtils wait;
	Actions act;
	WebDriver driver;
	
	
	public TransactionReportpage()
	{
		driver=DriverManager.getDriver();;
		PageFactory.initElements(driver,this);
		wait=new WaitUtils(driver, 5000);
		act=new Actions(driver);
	}
	
	public String getTransactionReportPageTitle()
	{
		return TransactionReportpagetitle.getText();
	}
	
	public void EnterFromDate(String FromDate)
	{
		 FromDateinputfield.sendKeys(FromDate);
	}
	
	public void EnterToDate(String ToDate)
	{
		ToDateinputfield.sendKeys(ToDate);
	}
	
	public void ClickOnSubmitButton()
	{
		SubmitButton.click();
	}
	
	public void SearchValueinInputBox(String Searchvalue)
	{
		Searchinputbox.sendKeys(Searchvalue);
	}
	
	public void ClickOnPreviousButton()
	{
		Previousbutton.click();
	}
	
	public void ClickOnNextButton()
	{
		Nextbutton.click();
	}
	
	public void SelectNoofEntries(String numbervalue)
	{
		Select select=new Select(EntriesDropdown);
		select.selectByValue(numbervalue);
		
	}
	
	public boolean Checkifdatesareinrange(String FromDate,String ToDate)
	{
		DateTimeFormatter inputformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter tableformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate From = LocalDate.parse(FromDate,inputformatter);
		LocalDate To = LocalDate.parse(ToDate,inputformatter);
		boolean status=true;
		
		do {
		if(listofdates.isEmpty())
		{
			throw new AssertionError("No Records Found in the Table");
			
		}
		for(WebElement date:listofdates)
		{
			String receiveddate = date.getText().trim();
			LocalDate transactiondate = LocalDateTime.parse(receiveddate,tableformatter).toLocalDate();
			if(transactiondate.isBefore(From)||transactiondate.isAfter(To))
			{
				System.out.println("Invalid Date "+receiveddate);
				status=false;
			}
		}
		
		
		}
		while(GoToNextPage());
		
		return status;
		
	}
	
	public boolean GoToNextPage()
	{
		String Receivedatt = Nextbutton.getAttribute("class");
		if(!Receivedatt.contains("disabled"))
		{
			Nextbutton.click();
			wait.waitforElementsToBeVisible(listofdates);
			return true;
		}
		
		return false;
		
	}
	
	public String getValidationMessageofFromDateField()
	{
		return FromDateinputfield.getAttribute("validationMessage");
	}
	
	public String getValidationMessageofToDateField()
	{
		return ToDateinputfield.getAttribute("validationMessage");
	}
	
	
	public boolean CheckWorkingofSearchFieldByStatus(String SearchStatusText)
	{
		
		do {
		
			wait.waitforElementsToBeVisible(Transactionrows);
			if(Transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:Transactionrows)
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
	
	
	public boolean CheckWorkingofSearchFieldByTransactionNumber(String SearchTransactionNumberText)
	{
		long Transactionnumberinput = Long.parseLong(SearchTransactionNumberText);
		do {
			wait.waitforElementsToBeVisible(Transactionrows);
			if(Transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:Transactionrows)
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
			wait.waitforElementsToBeVisible(Transactionrows);
			if(Transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:Transactionrows)
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
			wait.waitforElementsToBeVisible(Transactionrows);
			if(Transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:Transactionrows)
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
		
			wait.waitforElementsToBeVisible(Transactionrows);
			if(Transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:Transactionrows)
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
	
	
	public boolean CheckWorkingofSearchFieldByTransactionDate(String SearchTransactionDateText)
	{
		
		do {
		
			wait.waitforElementsToBeVisible(Transactionrows);
			if(Transactionrows.isEmpty())
			{
				System.out.println("No Records Found");
				return false;
			}
			
		for(WebElement transactionrow:Transactionrows)
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
	     int actualCount = Transactionrows.size();
	     return actualCount>0 && actualCount<=ExpectedCount;
	}
	
	public void ClickonTransactionNumberHeader()
	{
		TransactionNumberheader.click();
	}
	
	public void DoubleClickonTransactionNumberHeader()
	{
		
		act.doubleClick(TransactionNumberheader).perform();
		
	}
	
	public void ClickonReceivedorSentAccountnumberheader()
	{
		ReceivedorSentAccountnumberheader.click();
	}
	
	public void DoubleClickonReceivedorSentAccountnumberheader()
	{
		
		act.doubleClick(ReceivedorSentAccountnumberheader).perform();
		
	}
	
	public void ClickonAmountheader()
	{
		Amountheader.click();
	}
	
	public void DoubleClickonAmountheader()
	{
		
		act.doubleClick(Amountheader).perform();
		
	}
	
	public void ClickonTransactionTypeheader()
	{
		TransactionTypeheader.click();
	}
	
	public void DoubleClickonTransactionTypeheader()
	{
		
		act.doubleClick(TransactionTypeheader).perform();
		
	}
	
	public void ClickonTransactionDateheader()
	{
		TransactionDateheader.click();
	}
	
	public void DoubleClickonTransactionDateheader()
	{
		
		act.doubleClick(TransactionDateheader).perform();
		
	}
	
	public void ClickonStatusheader()
	{
		Statusheader.click();
	}
	
	public void DoubleClickonStatusheader()
	{
		
		act.doubleClick(Statusheader).perform();
		
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
			ReceivedorSentAccountNumberList.add(Long.parseLong(receivedorsentaccount.getText().trim()));
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
	

}
