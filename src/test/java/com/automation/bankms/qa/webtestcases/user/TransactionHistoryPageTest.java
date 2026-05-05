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
import com.automation.bankms.qa.pages.user.Dashboardpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.TransactionHistorypage;
import com.automation.bankms.qa.utils.SortUtils;
import com.automation.bankms.qa.utils.WaitUtils;

public class TransactionHistoryPageTest extends TestBase {
	
	public Homepage hp;
	public Loginpage lp;
	public Dashboardpage dp;
	public TransactionHistorypage thp;
	public WaitUtils wait;
	public SortUtils sort;
	
	
	@BeforeMethod
	public void Setup(ITestContext context)
	{
		Initialization();
		context.setAttribute("driver", driver);
		wait=new WaitUtils(driver, 2000);
		sort=new SortUtils();
		hp=new Homepage(driver);
		hp.clickonnewuserlink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp=new Loginpage(driver);
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage(driver);
		dp.clickontransactionhistorylink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Transaction History']"));
		thp=new TransactionHistorypage(driver);
		
	}
	
	@Test
	public void TC151_VerifyPaginationofTransactionHistoryPage()
	{
		int pagecount=1;
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		
		
		
		while(thp.Checknextbuttonisenabled())
		{
			String Firstrowdata = thp.getfirstrowdata();
			thp.clickonnextbutton();
			wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
			String Nextrowdata = thp.getfirstrowdata();
			Assert.assertNotEquals(Firstrowdata, Nextrowdata,"Pagination not done as data is same on both rows,Page where pagination issue is "+pagecount);
			pagecount++;
		}
			
		
		Assert.assertFalse(thp.Checknextbuttonisenabled(),"TC_151 Next Button is still enabled on next page");
		System.out.println("Total pages navigated "+pagecount);
	}
	
	
	@Test()
	public void TC152_VerifyBehaviourofTransactionHistoryPageTable()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));

		int totalcolumns = driver.findElements(By.xpath("//table[@id='dataTable']/thead/tr/th")).size();
		Assert.assertEquals(totalcolumns,7,"Headers count is mismatched");
		
		String []nameofheaders= {"S.No","Transaction Number","Received/Sent Account No","Amount","Transaction Type","Status","Txn Date"};
		for(int a=1;a<=totalcolumns;a++)
		{
			String tableheadertext = driver.findElement(By.xpath("//table[@id='dataTable']/thead/tr/th["+a+"]")).getText();
		
			Assert.assertEquals(tableheadertext, nameofheaders[a-1],"Headers mismatched at column "+a);
			
			
		}
		
		
	}
	
	
	
	
	@Test()
	public void TC153A_VerifyBehaviourofSearchFieldByTransactionNumberinTransactionHistoryPageTable()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("198396750195373");
        boolean receivedstatus = thp.CheckWorkingofSearchFieldByTransactionNumber("198396750195373");
        Assert.assertTrue(receivedstatus,"TC153A failed,Search results do not match expected Transaction Number");
		
		
		
	}
	
	@Test()
	public void TC153B_VerifyBehaviourofSearchFieldByReceivedorSentAccountNumberinTransactionHistoryPageTable()
	{
	
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("919376790");
        boolean receivedstatus = thp.CheckWorkingofSearchFieldByReceivedorSentAccountNumber("919376790");
        Assert.assertTrue(receivedstatus,"TC153B failed,Search results do not match expected Received or Sent Account Number");
		
		
		
	}
	

	@Test()
	public void TC153C_VerifyBehaviourofSearchFieldByAmountinTransactionHistoryPageTable()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("20000");
        boolean receivedstatus = thp.CheckWorkingofSearchFieldByAmount("20000");
        Assert.assertTrue(receivedstatus,"TC153C failed,Search results do not match expected Amount Value");
		
		
		
	}
	
	@Test()
	public void TC153D_VerifyBehaviourofSearchFieldByTranactionTypeinTransactionHistoryPageTable()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("Initial Amount");
        boolean receivedstatus = thp.CheckWorkingofSearchFieldByTransactionType("Initial Amount");
        Assert.assertTrue(receivedstatus,"TC153D failed,Search results do not match expected Transaction Type");
		
		
		
	}
	
	
	@Test()
	public void TC153E_VerifyBehaviourofSearchFieldByStatusinTransactionHistoryPageTable()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("Debit");
        boolean receivedstatus = thp.CheckWorkingofSearchFieldByStatus("Debit");
        Assert.assertTrue(receivedstatus,"TC153E failed,Search results do not match expected type");
		
		
		
	}
	
	@Test()
	public void TC153F_VerifyBehaviourofSearchFieldByTransactionDateinTransactionHistoryPageTable()
	{
	
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("2026-03-25");
        boolean receivedstatus = thp.CheckWorkingofSearchFieldByTransactionDate("2026-03-25");
        Assert.assertTrue(receivedstatus,"TC153F failed,Search results do not match expected Transaction Date");
		
		
		
	}
	
	@Test()
	public void TC154_VerifyBehaviourofShowEntriesinTransactionHistoryPageTable()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		String ExpectedEntries="25";
		thp.selectentriesfromdropdown(ExpectedEntries);
		int ExpectedEntriesinint = Integer.parseInt(ExpectedEntries);
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
	    boolean receivedentriesresult = thp.ValidateEntriesCount(ExpectedEntriesinint);
		Assert.assertTrue(receivedentriesresult,"TC154 Failed,Entries Do not match as per selected");

		
	}
	
	@Test()
	public void TC155A_VerifyBehaviourofTransactionHistoryPageTableAfterSortingTransactionNumberinAscendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.ClickonTransactionNumberHeader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofTransactionNumber());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<Long> Checkinglistinascending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistinascending);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC155A Failed,Transaction Numbers are not Sorted in Ascending order");
		
		
	}
	
	@Test()
	public void TC155B_VerifyBehaviourofTransactionHistoryPageTableAfterSortingReceivedorSentAccountNumberinAscendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.ClickonReceivedorSentAccountnumberheader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofReceivedorSentAccountNumber());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<Long> Checkinglistinascending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistinascending);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC155B Failed,Transaction Received or Sent AccountNumbers are not Sorted in Ascending order");
		
		
	}
	
	@Test()
	public void TC155C_VerifyBehaviourofTransactionHistoryPageTableAfterSortingAmountinAscendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.ClickonAmountheader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofAmount());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<Long> Checkinglistinascending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistinascending);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC155C Failed,Amounts are not Sorted in Ascending order");
		
		
	}
	
	@Test()
	public void TC155D_VerifyBehaviourofTransactionHistoryPageTableAfterSortingTransactionTypeinAscendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.ClickonTransactionTypeheader();
		
		ArrayList<String>allvalues=new ArrayList<String>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofTransactionType());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<String> Checkinglistinascending=new ArrayList<String>(allvalues);
		Collections.sort(Checkinglistinascending,String.CASE_INSENSITIVE_ORDER);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC155D Failed,Transaction type are not Sorted in Ascending order");
		
		
	}
	
	@Test()
	public void TC155E_VerifyBehaviourofTransactionHistoryPageTableAfterSortingStatusinAscendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.ClickonStatusheader();
		ArrayList<String>allvalues=new ArrayList<String>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofStatus());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<String> Checkinglistinascending=new ArrayList<String>(allvalues);
		Collections.sort(Checkinglistinascending,String.CASE_INSENSITIVE_ORDER);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC155E Failed,Status are not Sorted in Ascending order");
		
		
	}
	
	@Test()
	public void TC155F_VerifyBehaviourofTransactionReportPageTableAfterSortingTransactionDateTimeinAscendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.ClickonTransactionDateheader();
		ArrayList<LocalDateTime>allvalues=new ArrayList<LocalDateTime>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofTransactionDate());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<LocalDateTime> Checkinglistinascending=new ArrayList<LocalDateTime>(allvalues);
		Collections.sort(Checkinglistinascending);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC155F Failed,Transaction Dates are not Sorted in Ascending order");
		
		
	}
	
	@Test()
	public void TC156A_VerifyBehaviourofTransactionHistoryPageTableAfterSortingTransactionNumberinDescendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.DoubleClickonTransactionNumberHeader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofTransactionNumber());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<Long> Checkinglistindescending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistindescending,Collections.reverseOrder());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC156A Failed,Transaction Numbers are not Sorting in Descending order");
		
		
	}
	
	@Test()
	public void TC156B_VerifyBehaviourofTransactionHistoryPageTableAfterSortingReceivedorSentAccountNumberinDescendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.DoubleClickonReceivedorSentAccountnumberheader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofReceivedorSentAccountNumber());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<Long> Checkinglistindescending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistindescending,Collections.reverseOrder());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC156B Failed,Transaction Received or Sent AccountNumbers are not Sorted in Descending order");
		
		
	}
	
	@Test()
	public void TC156C_VerifyBehaviourofTransactionHistoryPageTableAfterSortingAmountinDescendingOrder()
	{
		
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.DoubleClickonAmountheader();
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofAmount());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<Long> Checkinglistindescending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistindescending,Collections.reverseOrder());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC156C Failed,Amounts are not Sorted in Descending order");
		
		
	}
	
	@Test()
	public void TC156D_VerifyBehaviourofTransactionHistoryPageTableAfterSortingTransactionTypeinDescendingOrder() 
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.DoubleClickonTransactionTypeheader();
		
		ArrayList<String>allvalues=new ArrayList<String>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofTransactionType());
		
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<String> Checkinglistindescending=new ArrayList<String>(allvalues);
		Collections.sort(Checkinglistindescending,String.CASE_INSENSITIVE_ORDER.reversed());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC156D Failed,Transaction type are not Sorted in Descending order");
		
		
	}
	
	@Test()
	public void TC156E_VerifyBehaviourofTransactionHistoryPageTableAfterSortingStatusinDescendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.DoubleClickonStatusheader();
		ArrayList<String>allvalues=new ArrayList<String>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofStatus());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<String> Checkinglistindescending=new ArrayList<String>(allvalues);
		Collections.sort(Checkinglistindescending,String.CASE_INSENSITIVE_ORDER.reversed());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC156E Failed,Status are not Sorted in Descending order");
		
		
	}
	
	@Test()
	public void TC156F_VerifyBehaviourofTransactionReportPageTableAfterSortingTransactionDateTimeinDescendingOrder()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.DoubleClickonTransactionDateheader();
		ArrayList<LocalDateTime>allvalues=new ArrayList<LocalDateTime>();
		
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofTransactionDate());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<LocalDateTime> Checkinglistindescending=new ArrayList<LocalDateTime>(allvalues);
		Collections.sort(Checkinglistindescending,Collections.reverseOrder());
		
		Assert.assertEquals(allvalues, Checkinglistindescending, "TC156F Failed,Transaction Dates are not Sorted in Descending order");
		
		
	}
	
	@Test()
	public void TC157_VerifyBehaviourofSearchFieldByInvalidValueinTransactionHistoryPageTable()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("Shahrukh khan");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		String InvalidSearchoutput = thp.gettextforinvalidsearchinput();
		Assert.assertEquals(InvalidSearchoutput,"No matching records found","TC157 Failed,Record Found for Invalid Search,No proper Error validation Displayed");
		
	}
	
	@Test()
	public void TC158_VerifySQLInjectionTestinSearchField()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("' OR '1'='1");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		String SQLSearchoutput = thp.gettextforinvalidsearchinput();
		Assert.assertEquals(SQLSearchoutput,"No matching records found","TC158 Failed,Record Found for SQL Injection Search,No proper Error validation Displayed");
		
	}
	
	@Test
	public void TC159_VerifyPaginationofLastPage()
	{
		do
		{
			wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		}
		
		while(thp.GoToNextPage());
		String ClassAttributeofnextbuttononlastpage = driver.findElement(By.id("dataTable_next")).getAttribute("class");
		Assert.assertTrue(ClassAttributeofnextbuttononlastpage.contains("disabled"),"TC 159 Failed,Next Button is Not Disabled on Last Page");
	}
	
	@Test()
	public void TC163_VerifyBehaviourofSearchFieldByEnteringSpecialCharacters()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("Sh@hrukh_Kh@n$@!");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		String InvalidSearchoutput = thp.gettextforinvalidsearchinput();
		Assert.assertEquals(InvalidSearchoutput,"No matching records found","TC163 Failed,Record Found for Invalid Search with Special Characters,No proper Error validation Displayed");
		
	}
	
	@Test()
	public void TC164_VerifySearchWithLongInputString()
	{
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.searchtransactionrecord("630584701195175AbcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnoPQRSTUVWXYZabcd");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		String InvalidSearchoutput = thp.gettextforinvalidsearchinput();
		Assert.assertEquals(InvalidSearchoutput,"No matching records found","TC164 Failed,Record Found for Invalid Search with Long Input,No proper Error validation Displayed");
		
	}
	
	@Test()
	public void TC165_VerifySortingAfterPagination()
	{
		
		do {
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		}
		while(thp.GoToNextPage());
		thp.ClickonTransactionNumberHeader();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		
		
		ArrayList<Long>allvalues=new ArrayList<Long>();
		
		do {
		
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		allvalues.addAll(thp.ListofTransactionNumber());
		
		}
		while(thp.GoToNextPage());
		
		ArrayList<Long> Checkinglistinascending=new ArrayList<Long>(allvalues);
		Collections.sort(Checkinglistinascending);
		
		Assert.assertEquals(allvalues, Checkinglistinascending, "TC165 Failed,Transaction Numbers are not Sorted in Ascending order after Pagination");
		
	}
	
	@Test
	public void TC166_VerifyChangeinEntriesAfterRefresh()
	{
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		thp.selectentriesfromdropdown("50");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		Assert.assertEquals(thp.getselectedentries(), "50","Dropdown selected entries not changed properly");
		thp.refreshage();
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']"));
		Assert.assertEquals(thp.getselectedentries(), "10","TC166 Failed,No of Entries not resetted to default after refresh");
		
	}
	
	@AfterMethod
	public void Teardown()
	{
		driver.quit();
	}
	
	
	
	
	
	
	
	

}
