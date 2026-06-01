package com.automation.bankms.qa.webtestcases.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.user.Dashboardpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.TransactionReportpage;
import com.automation.bankms.qa.utils.SortUtils;
import com.automation.bankms.qa.utils.WaitUtils;

public class TransactionReportTest extends TestBase {
	
	public Homepage hp;
	public Loginpage lp;
	public Dashboardpage dp;
	public TransactionReportpage tp;
	public WaitUtils wait;
	public SortUtils sort;
	
	@BeforeMethod
	public void Setup(ITestContext context)
	{
		Initialization();
		context.setAttribute("driver", DriverManager.getDriver());
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		sort=new SortUtils();
		hp=new Homepage();
		hp.clickonnewuserlink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp=new Loginpage();
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Payee / Beneficiary']"));
		dp=new Dashboardpage();
		dp.clickonreportlink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Transaction Report']"));
		tp=new TransactionReportpage();
		
	}
	
	@Test(priority=1)
	public void TC136_VerifyTransactionReportForValidDateRange()
	{
		tp.EnterFromDate("16-03-2026");
		tp.EnterToDate("27-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.Checkifdatesareinrange("16-03-2026","27-03-2026");
		Assert.assertTrue(true,"TC136 Failed,Transaction Data is not in the searched order");
	}
	
	@Test(priority=2)
	public void TC137_VerifyTransactionReportWithFromDateFieldEmpty()
	{
		tp.EnterToDate("27-03-2026");
		tp.ClickOnSubmitButton();
		String FromFieldValidationText = tp.getValidationMessageofFromDateField();
		Assert.assertEquals(FromFieldValidationText,"Please fill in this field.","TC137 failed,Transaction Data shown even when from field is empty with no validation error");
		
	}
	
	@Test(priority=3)
	public void TC138_VerifyTransactionReportWithToDateFieldEmpty()
	{
		tp.EnterFromDate("25-03-2026");
		tp.ClickOnSubmitButton();
		String FromFieldValidationText = tp.getValidationMessageofToDateField();
		Assert.assertEquals(FromFieldValidationText,"Please fill in this field.","TC138 failed,Transaction Data shown even when To field is empty with no validation error");
		
	}
	
	@Test(priority=4)
	public void TC139_VerifyTransactionReportWithFromandToDateFieldEmpty()
	{
		
		tp.ClickOnSubmitButton();
		String FromFieldValidationText = tp.getValidationMessageofFromDateField();
		Assert.assertEquals(FromFieldValidationText,"Please fill in this field.","TC139 failed,Transaction Data shown even when from & To fields are empty with no validation error");
		
	}
	
	@Test(priority=5)
	public void TC142_VerifyBehaviourofTransactionReportPageTable()
	{
		tp.EnterFromDate("16-03-2026");
		tp.EnterToDate("27-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));

		int totalcolumns = DriverManager.getDriver().findElements(By.xpath("//table[@id='dataTable']/thead/tr/th")).size();
		Assert.assertEquals(totalcolumns,7,"Headers count is mismatched");
		
		String []nameofheaders= {"S.No","Transaction Number","Received/Sent Account No","Amount","Transaction Type","Status","Txn Date"};
		for(int a=1;a<=totalcolumns;a++)
		{
			String tableheadertext = DriverManager.getDriver().findElement(By.xpath("//table[@id='dataTable']/thead/tr/th["+a+"]")).getText();
		
			Assert.assertEquals(tableheadertext, nameofheaders[a-1],"Headers mismatched at column "+a);
			
			
		}
		
		
	}
	
	@Test(priority=6)
	public void TC143A_VerifyBehaviourofSearchFieldByTransactionNumberinTransactionReportPageTable()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.SearchValueinInputBox("198396750195373");
        boolean receivedstatus = tp.CheckWorkingofSearchFieldByTransactionNumber("198396750195373");
        Assert.assertTrue(receivedstatus,"TC143A failed,Search results do not match expected Transaction Number");
		
		
		
	}
	
	@Test(priority=7)
	public void TC143B_VerifyBehaviourofSearchFieldByReceivedorSentAccountNumberinTransactionReportPageTable()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.SearchValueinInputBox("919376790");
        boolean receivedstatus = tp.CheckWorkingofSearchFieldByReceivedorSentAccountNumber("919376790");
        Assert.assertTrue(receivedstatus,"TC143B failed,Search results do not match expected Received or Sent Account Number");
		
		
		
	}
	
	@Test(priority=8)
	public void TC143C_VerifyBehaviourofSearchFieldByAmountinTransactionReportPageTable()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.SearchValueinInputBox("20000");
        boolean receivedstatus = tp.CheckWorkingofSearchFieldByAmount("20000");
        Assert.assertTrue(receivedstatus,"TC143C failed,Search results do not match expected Amount Value");
		
		
		
	}
	
	@Test(priority=9)
	public void TC143D_VerifyBehaviourofSearchFieldByTranactionTypeinTransactionReportPageTable()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.SearchValueinInputBox("Initial Amount");
        boolean receivedstatus = tp.CheckWorkingofSearchFieldByTransactionType("Initial Amount");
        Assert.assertTrue(receivedstatus,"TC143D failed,Search results do not match expected Transaction Type");
		
		
		
	}
	
	
	@Test(priority=10)
	public void TC143E_VerifyBehaviourofSearchFieldByStatusinTransactionReportPageTable()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.SearchValueinInputBox("Debit");
        boolean receivedstatus = tp.CheckWorkingofSearchFieldByStatus("Debit");
        Assert.assertTrue(receivedstatus,"TC143E failed,Search results do not match expected type");
		
		
		
	}
	
	@Test(priority=11)
	public void TC143F_VerifyBehaviourofSearchFieldByTransactionDateinTransactionReportPageTable()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.SearchValueinInputBox("2026-03-25");
        boolean receivedstatus = tp.CheckWorkingofSearchFieldByTransactionDate("2026-03-25");
        Assert.assertTrue(receivedstatus,"TC143F failed,Search results do not match expected Transaction Date");
		
		
		
	}
	
	@Test(priority=12)
	public void TC144_VerifyBehaviourofShowEntriesinTransactionReportPageTable()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		String ExpectedEntries="100";
		tp.SelectNoofEntries(ExpectedEntries);
		int ExpectedEntriesinint = Integer.parseInt(ExpectedEntries);
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
	    boolean receivedentriesresult = tp.ValidateEntriesCount(ExpectedEntriesinint);
		Assert.assertTrue(receivedentriesresult,"TC144 Failed,Entries Do not match as per selected");

		
	}
	
	@Test(priority=13)
	public void TC145A_VerifyBehaviourofTransactionReportPageTableAfterSortingTransactionNumberinAscendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.ClickonTransactionNumberHeader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofTransactionNumber());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<Long> Checkinglistinascending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistinascending);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC145A Failed,Transaction Numbers are not Sorted in Ascending order");
		
		
	}
	
	@Test(priority=14)
	public void TC145B_VerifyBehaviourofTransactionReportPageTableAfterSortingReceivedorSentAccountNumberinAscendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.ClickonReceivedorSentAccountnumberheader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofReceivedorSentAccountNumber());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<Long> Checkinglistinascending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistinascending);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC145B Failed,Transaction Received or Sent AccountNumbers are not Sorted in Ascending order");
		
		
	}
	
	@Test(priority=15)
	public void TC145C_VerifyBehaviourofTransactionReportPageTableAfterSortingAmountinAscendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.ClickonAmountheader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofAmount());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<Long> Checkinglistinascending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistinascending);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC145C Failed,Amounts are not Sorted in Ascending order");
		
		
	}
	
	@Test(priority=16)
	public void TC145D_VerifyBehaviourofTransactionReportPageTableAfterSortingTransactionTypeinAscendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.ClickonTransactionTypeheader();
		
		ArrayList<String>allvalues=new ArrayList<String>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofTransactionType());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<String> Checkinglistinascending=new ArrayList<String>(allvalues);
		Collections.sort(Checkinglistinascending,String.CASE_INSENSITIVE_ORDER);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC145D Failed,Transaction type are not Sorted in Ascending order");
		
		
	}
	
	@Test(priority=17)
	public void TC145E_VerifyBehaviourofTransactionReportPageTableAfterSortingStatusinAscendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.ClickonStatusheader();
		ArrayList<String>allvalues=new ArrayList<String>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofStatus());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<String> Checkinglistinascending=new ArrayList<String>(allvalues);
		Collections.sort(Checkinglistinascending,String.CASE_INSENSITIVE_ORDER);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC145E Failed,Status are not Sorted in Ascending order");
		
		
	}
	
	@Test(priority=18)
	public void TC145F_VerifyBehaviourofTransactionReportPageTableAfterSortingTransactionDateTimeinAscendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.ClickonTransactionDateheader();
		ArrayList<LocalDateTime>allvalues=new ArrayList<LocalDateTime>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofTransactionDate());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<LocalDateTime> Checkinglistinascending=new ArrayList<LocalDateTime>(allvalues);
		Collections.sort(Checkinglistinascending);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC145F Failed,Transaction Dates are not Sorted in Ascending order");
		
		
	}
	
	@Test(priority=19)
	public void TC146A_VerifyBehaviourofTransactionReportPageTableAfterSortingTransactionNumberinDescendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.DoubleClickonTransactionNumberHeader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofTransactionNumber());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<Long> Checkinglistindescending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistindescending,Collections.reverseOrder());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC146A Failed,Transaction Numbers are not Sorting in Descending order");
		
		
	}
	
	@Test(priority=20)
	public void TC146B_VerifyBehaviourofTransactionReportPageTableAfterSortingReceivedorSentAccountNumberinDescendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.DoubleClickonReceivedorSentAccountnumberheader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofReceivedorSentAccountNumber());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<Long> Checkinglistindescending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistindescending,Collections.reverseOrder());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC146B Failed,Transaction Received or Sent AccountNumbers are not Sorted in Descending order");
		
		
	}
	
	@Test(priority=21)
	public void TC146C_VerifyBehaviourofTransactionReportPageTableAfterSortingAmountinDescendingOrder()
	{
		
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.DoubleClickonAmountheader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofAmount());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<Long> Checkinglistindescending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistindescending,Collections.reverseOrder());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC146C Failed,Amounts are not Sorted in Descending order");
		
		
	}
	
	@Test(priority=22)
	public void TC146D_VerifyBehaviourofTransactionReportPageTableAfterSortingTransactionTypeinDescendingOrder() 
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.DoubleClickonTransactionTypeheader();
		
		ArrayList<String>allvalues=new ArrayList<String>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofTransactionType());
		
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<String> Checkinglistindescending=new ArrayList<String>(allvalues);
		Collections.sort(Checkinglistindescending,String.CASE_INSENSITIVE_ORDER.reversed());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC146D Failed,Transaction type are not Sorted in Descending order");
		
		
	}
	
	@Test(priority=23)
	public void TC146E_VerifyBehaviourofTransactionReportPageTableAfterSortingStatusinDescendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.DoubleClickonStatusheader();
		ArrayList<String>allvalues=new ArrayList<String>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofStatus());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<String> Checkinglistindescending=new ArrayList<String>(allvalues);
		Collections.sort(Checkinglistindescending,String.CASE_INSENSITIVE_ORDER.reversed());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC146E Failed,Status are not Sorted in Descending order");
		
		
	}
	
	@Test(priority=24)
	public void TC146F_VerifyBehaviourofTransactionReportPageTableAfterSortingTransactionDateTimeinDescendingOrder()
	{
		tp.EnterFromDate("10-03-2026");
		tp.EnterToDate("29-03-2026");
		tp.ClickOnSubmitButton();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		tp.DoubleClickonTransactionDateheader();
		ArrayList<LocalDateTime>allvalues=new ArrayList<LocalDateTime>();
		
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(tp.ListofTransactionDate());
		
		}
		while(tp.GoToNextPage());
		
		ArrayList<LocalDateTime> Checkinglistindescending=new ArrayList<LocalDateTime>(allvalues);
		Collections.sort(Checkinglistindescending,Collections.reverseOrder());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC146F Failed,Transaction Dates are not Sorted in Descending order");
		
		
	}
	
	
	
	
	@AfterMethod
	public void Teardown()
	{
		DriverManager.getDriver().quit();
		DriverManager.unload();
	}
	

}
