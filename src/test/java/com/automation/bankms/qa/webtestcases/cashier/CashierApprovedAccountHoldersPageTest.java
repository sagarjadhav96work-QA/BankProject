package com.automation.bankms.qa.webtestcases.cashier;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.config.ConfigReader;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.cashier.CashierAccountHoldersPage;
import com.automation.bankms.qa.pages.cashier.CashierDashboardPage;
import com.automation.bankms.qa.pages.cashier.CashierLoginPage;
import com.automation.bankms.qa.pages.cashier.CashierUserDetailsPage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.SortUtils;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierApprovedAccountHoldersPageTest extends TestBase {
	public Homepage hp;
	public CashierLoginPage clp;
	public CashierDashboardPage cdp;
	public CashierAccountHoldersPage cahp;
	public CashierUserDetailsPage cudp;
	protected static final Logger log=LogManagerUtil.getLogger(CashierApprovedAccountHoldersPageTest.class);
	public SoftAssert soft;
	public WaitUtils wait;
	public SortUtils sort;
	
	
	@BeforeMethod
	public void setup(Method method,ITestContext context)
	{
		log.info("========= STARTING TEST: {} =========", method.getName());
		Initialization();
		context.setAttribute("driver", DriverManager.getDriver());
		log.info("Initializing Assert");
		soft=new SoftAssert();
		log.info("Initializing Waits");
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		sort=new SortUtils();
		hp=new Homepage();
		log.info("Clicking on Cashier Login Link");
		hp.clickoncashierloginlink();
		log.info("Navigating to Cashier Login Page");
		clp=new CashierLoginPage();
		clp.waitForVisibilityofCashierLoginPage();
		log.info("Cashier Login page is successfully loaded");
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeid"));
		log.info("Entered Employee ID");
		clp.enterPassword(ConfigReader.getProperty("cashierpassword"));
		log.info("Entered Password");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		log.info("Navigating to Cashier Dashboard Page");
		cdp=new CashierDashboardPage();
		cdp.waitForLaunchOfDashboardPage();
		log.info("Clicking on Account Holders Link");
		cdp.clickOnAccountHoldersLink();
		
		
		
	}
	
	@Test(priority=1)
	public void TC295_verifyNavigationofApprovedAccountHoldersPageTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "TC 295 Failed,Approved Account Holder page table is not loaded successfully");
		soft.assertAll();
	}
	
	@Test(priority=2)
	public void TC297_verifyBehaviourofShowEntriesDropdownOnApprovedAccountHoldersPageTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String searchentryvalue="50";
		log.info("Selecting search entry value from dropdown");
		cahp.selectsearchentriesvalue(searchentryvalue);
		int TotalEntries = cahp.checkingNoofEntriesinSinglePage();
		log.info("Total Entries on the Page are {}",TotalEntries);
		soft.assertTrue(TotalEntries>0&&TotalEntries<=Integer.parseInt(searchentryvalue), "TC 297 Failed,Number of Approved account Holders shown on page are less than zero or more than "+searchentryvalue+" ");
		soft.assertAll();
	}
	
	@Test(priority=3)
	public void TC298A_verifyBehaviourofSearchBoxInputFieldonAccountHoldersPagewithNameTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputName="Manish Gupta";
		log.info("Entering Name in Search Input Field");
		cahp.enterInputinSearchField(InputName);
		log.info("Entered name in search input field {}",InputName);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByName(InputName);
		soft.assertTrue(statusofsearchfield,"TC 298A Failed,Search behaviour with Name Failed");
		
		soft.assertAll();
	}
	
	@Test(priority=4)
	public void TC298B_verifyBehaviourofSearchBoxInputFieldonAccountHoldersPagewithMobileNumberTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputMobileNumber = "4564564561";
		log.info("Entering Mobile Number in Search Input Field");
		cahp.enterInputinSearchField(InputMobileNumber);
		log.info("Entered Mobile Number in search input field {}",InputMobileNumber);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByMobileNumber(InputMobileNumber);
		soft.assertTrue(statusofsearchfield,"TC 298B Failed,Search behaviour with Mobile Number Failed");
		
		soft.assertAll();
	}
	
	@Test(priority=5)
	public void TC298C_verifyBehaviourofSearchBoxInputFieldonAccountHoldersPagewithEmailAddressTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputEmailAddress = "anujk30@gmail.com";
		log.info("Entering Email Address in Search Input Field");
		cahp.enterInputinSearchField(InputEmailAddress);
		log.info("Entered Email Address in search input field {}",InputEmailAddress);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByEmail(InputEmailAddress);
		soft.assertTrue(statusofsearchfield,"TC 298C Failed,Search behaviour with Email Address Failed");
		
		soft.assertAll();
	}
	
	@Test(priority=6)
	public void TC298D_verifyBehaviourofSearchBoxInputFieldonAccountHoldersPagewithAccountNumberTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputAccountNumber = "585403830";
		log.info("Entering Account Number in Search Input Field");
		cahp.enterInputinSearchField(InputAccountNumber);
		log.info("Entered Account Number in search input field {}",InputAccountNumber);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByAccountNumber(InputAccountNumber);
		soft.assertTrue(statusofsearchfield,"TC 298D Failed,Search behaviour with Account Number Failed");
		
		soft.assertAll();
	}
	
	
	@Test(priority=7)
	public void TC298E_verifyBehaviourofSearchBoxInputFieldonAccountHoldersPagewithAccountUserIDNumberTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputAccountUserIDNumber = "264602756";
		log.info("Entering Account User ID Number in Search Input Field");
		cahp.enterInputinSearchField(InputAccountUserIDNumber);
		log.info("Entered Account User ID Number in search input field {}",InputAccountUserIDNumber);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByAccountUserIDNumber(InputAccountUserIDNumber);
		soft.assertTrue(statusofsearchfield,"TC 298E Failed,Search behaviour with Account User ID Number Failed");
		
		soft.assertAll();
	}
	
	@Test(priority=8)
	public void TC298F_verifyBehaviourofSearchBoxInputFieldonAccountHoldersPagewithStatusTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputStatus = "Approved";
		log.info("Entering Status Value in Search Input Field");
		cahp.enterInputinSearchField(InputStatus);
		log.info("Entered Status Value in search input field {}",InputStatus);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByStatus(InputStatus);
		soft.assertTrue(statusofsearchfield,"TC 298F Failed,Search behaviour with Status value Failed");
		
		soft.assertAll();
	}
	
	@Test(priority=9)
	public void TC298G_verifyBehaviourofSearchBoxInputFieldonAccountHoldersPagewithActionTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputAction = "View";
		log.info("Entering Action Value in Search Input Field");
		cahp.enterInputinSearchField(InputAction);
		log.info("Entered Action Value in search input field {}",InputAction);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByAction(InputAction);
		soft.assertTrue(statusofsearchfield,"TC 298G Failed,Search behaviour with Action value Failed");
		
		soft.assertAll();
	}
	
	@Test(priority=10)
	public void TC299_verifyPaginationofAccountHoldersPageTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Checking working of Pagination");	
		log.info("Clicking on Next Page");
		while(cahp.goToNextPage());
		log.info("Navigated to last Page Successfully");
		log.info("Clicking on Previous Page");
		while(cahp.goToPreviousPage());
		log.info("Navigated to First Page Successfully");
		soft.assertAll();
	}
	
	
	@Test(priority=11)
	public void TC300A_verifyBehaviourofAccountHoldersPageTableAfterSortingNameinAscendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Clicking on Name Header");
		cahp.clickonNameHeader();
		log.info("Clicked on Name Header");
		log.info("Collecting list of Name from all account holders");
		ArrayList<String> Actualtotallistofnames=new ArrayList<String>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<String> UINameList = cahp.getNameListofAccountHolders();
			Actualtotallistofnames.addAll(UINameList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of Name from all account holders");
		log.info("Checking sorting of names in ascending order");
		boolean statusofsortbyname = sort.islistsortedinascendingorder(Actualtotallistofnames);
		soft.assertTrue(statusofsortbyname, "TC 300A Failed,List of Names are not Sorted in Ascending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=12)
	public void TC300B_verifyBehaviourofAccountHoldersPageTableAfterSortingMobileNumberinAscendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Clicking on Mobile Number Header");
		cahp.clickonMobileNumberHeader();
		log.info("Clicked on Mobile Number Header");
		log.info("Collecting list of Mobile Number from all account holders");
		ArrayList<Long> Actualtotallistofmobilenumbers=new ArrayList<Long>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<Long> UIMobileNumberList = cahp.getMobileNumberListofAccountHolders();
			Actualtotallistofmobilenumbers.addAll(UIMobileNumberList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of Mobile Number from all account holders");
		log.info("Checking sorting of Mobile Number in ascending order");
		boolean statusofsortbymobilenumber = sort.ismobilenumbersortedinascendingorder(Actualtotallistofmobilenumbers);
		soft.assertTrue(statusofsortbymobilenumber, "TC 300B Failed,List of Mobile Numbers are not Sorted in Ascending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=13)
	public void TC300C_verifyBehaviourofAccountHoldersPageTableAfterSortingEmailinAscendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Clicking on Email Header");
		cahp.clickonEmailHeader();
		log.info("Clicked on Email Header");
		log.info("Collecting list of Email from all account holders");
		
		ArrayList<String> Actualtotallistofemails=new ArrayList<String>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<String> UIEmailList = cahp.getEmailListofAccountHolders();
			Actualtotallistofemails.addAll(UIEmailList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of Email from all account holders");
		log.info("Checking sorting of Email in ascending order");
		boolean statusofsortbyemail = sort.islistsortedinascendingorder(Actualtotallistofemails);
		soft.assertTrue(statusofsortbyemail, "TC 300C Failed,List of Emails are not Sorted in Ascending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=14)
	public void TC300D_verifyBehaviourofAccountHoldersPageTableAfterSortingAccountNumberinAscendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Clicking on Account Number Header");
		cahp.clickonAccountNumberHeader();
		log.info("Clicked on Account Number Header");
		log.info("Collecting list of Account Number from all account holders");
		
		ArrayList<Long> Actualtotallistofaccountnumbers=new ArrayList<Long>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<Long> UIAccountNumberList = cahp.getAccountNumberListofAccountHolders();
			Actualtotallistofaccountnumbers.addAll(UIAccountNumberList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of Account Number from all account holders");
		log.info("Checking sorting of Account Number in ascending order");
		boolean statusofsortbyaccountnumber = sort.ismobilenumbersortedinascendingorder(Actualtotallistofaccountnumbers);
		soft.assertTrue(statusofsortbyaccountnumber, "TC 300D Failed,List of Account numbers are not Sorted in Ascending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=15)
	public void TC300E_verifyBehaviourofAccountHoldersPageTableAfterSortingUserIDinAscendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Clicking on UserID Header");
		cahp.clickonAccountUserIDNumberHeader();
		log.info("Clicked on UserID Header");
		log.info("Collecting list of UserID from all account holders");
		
		ArrayList<Long> Actualtotallistofuserid=new ArrayList<Long>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<Long> UIUserIDList = cahp.getUserIDListofAccountHolders();
			Actualtotallistofuserid.addAll(UIUserIDList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of UserID from all account holders");
		log.info("Checking sorting of UserID in ascending order");
		boolean statusofsortbyuserid = sort.ismobilenumbersortedinascendingorder(Actualtotallistofuserid);
		soft.assertTrue(statusofsortbyuserid, "TC 300E Failed,List of User ID are not Sorted in Ascending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=16)
	public void TC301A_verifyBehaviourofAccountHoldersPageTableAfterSortingNameinDescendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Double Clicking on Name Header");
		cahp.doubleClickonNameHeader();
		log.info("Clicked on Name Header");
		log.info("Collecting list of Name from all account holders");
		ArrayList<String> Actualtotallistofnames=new ArrayList<String>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<String> UINameList = cahp.getNameListofAccountHolders();
			Actualtotallistofnames.addAll(UINameList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of Name from all account holders");
		log.info("Checking sorting of names in descending order");
		boolean statusofsortbyname = sort.islistsortedindescendingorder(Actualtotallistofnames);
		soft.assertTrue(statusofsortbyname, "TC 301A Failed,List of Names are not Sorted in Descending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=17)
	public void TC301B_verifyBehaviourofAccountHoldersPageTableAfterSortingMobileNumberinDescendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Double Clicking on Mobile Number Header");
		cahp.doubleClickonMobileNumberHeader();
		log.info("Double Clicked on Mobile Number Header");
		log.info("Collecting list of Mobile Number from all account holders");
		ArrayList<Long> Actualtotallistofmobilenumbers=new ArrayList<Long>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<Long> UIMobileNumberList = cahp.getMobileNumberListofAccountHolders();
			Actualtotallistofmobilenumbers.addAll(UIMobileNumberList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of Mobile Number from all account holders");
		log.info("Checking sorting of Mobile Number in descending order");
		boolean statusofsortbymobilenumber = sort.ismobilenumbersortedindescendingorder(Actualtotallistofmobilenumbers);
		soft.assertTrue(statusofsortbymobilenumber, "TC 301B Failed,List of Mobile Numbers are not Sorted in Descending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=18)
	public void TC301C_verifyBehaviourofAccountHoldersPageTableAfterSortingEmailinDescendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Double Clicking on Email Header");
		cahp.doubleClickonEmailHeader();
		log.info("Double Clicked on Email Header");
		log.info("Collecting list of Email from all account holders");
		
		ArrayList<String> Actualtotallistofemails=new ArrayList<String>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<String> UIEmailList = cahp.getEmailListofAccountHolders();
			Actualtotallistofemails.addAll(UIEmailList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of Email from all account holders");
		log.info("Checking sorting of Email in descending order");
		boolean statusofsortbyemail = sort.islistsortedindescendingorder(Actualtotallistofemails);
		soft.assertTrue(statusofsortbyemail, "TC 301C Failed,List of Emails are not Sorted in Descending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=19)
	public void TC301D_verifyBehaviourofAccountHoldersPageTableAfterSortingAccountNumberinDescendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Double Clicking on Account Number Header");
		cahp.doubleClickonAccountNumberHeader();
		log.info("Double Clicked on Account Number Header");
		log.info("Collecting list of Account Number from all account holders");
		
		ArrayList<Long> Actualtotallistofaccountnumbers=new ArrayList<Long>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<Long> UIAccountNumberList = cahp.getAccountNumberListofAccountHolders();
			Actualtotallistofaccountnumbers.addAll(UIAccountNumberList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of Account Number from all account holders");
		log.info("Checking sorting of Account Number in descending order");
		boolean statusofsortbyaccountnumber = sort.ismobilenumbersortedindescendingorder(Actualtotallistofaccountnumbers);
		soft.assertTrue(statusofsortbyaccountnumber, "TC 301D Failed,List of Account numbers are not Sorted in Descending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=20)
	public void TC301E_verifyBehaviourofAccountHoldersPageTableAfterSortingUserIDinDescendingOrderTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Double Clicking on UserID Header");
		cahp.doubleClickonAccountUserIDNumberHeader();
		log.info("Double Clicked on UserID Header");
		log.info("Collecting list of UserID from all account holders");
		
		ArrayList<Long> Actualtotallistofuserid=new ArrayList<Long>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<Long> UIUserIDList = cahp.getUserIDListofAccountHolders();
			Actualtotallistofuserid.addAll(UIUserIDList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of UserID from all account holders");
		log.info("Checking sorting of UserID in descending order");
		boolean statusofsortbyuserid = sort.ismobilenumbersortedindescendingorder(Actualtotallistofuserid);
		soft.assertTrue(statusofsortbyuserid, "TC 300E Failed,List of User ID are not Sorted in Descending Order");
		
		soft.assertAll();
	}
	
	@Test(priority=21)
	public void TC302_verifyBehaviourofSQLInjectioninSearchBoxInputFieldofAccountHoldersPageTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputName="'' OR '1'='1'";
		log.info("Entering SQL Value in Search Input Field");
		cahp.enterInputinSearchField(InputName);
		log.info("Entered SQL Value in search input field {}",InputName);
		cahp.waitforAccountHoldersPageTableToLoad();
		String resultofSQLsearch = cahp.checkWorkingofSearchInputFieldBySQLInput();
		soft.assertEquals(resultofSQLsearch,"No matching records found","TC 302 Failed,SQL injection not handled properly,No matching records found message not displayed");
		
		soft.assertAll();
	}
	
	@Test(priority=22)
	public void TC303_verifyPaginationonLastPageofAccountHoldersTableTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Clicking on the next button and navigating on Last Page");
		while(cahp.goToNextPage());
		log.info("Navigated on Last Page");
		log.info("Checking attribute of last page ");
		String attributeoflastpage = cahp.getAttributeofLastPage();
		soft.assertTrue(attributeoflastpage.contains("disabled"), "TC 303 Failed,Next Button was not Disabled on Last Page.");
		soft.assertAll();
	}
	
	@Test(priority=23)
	public void TC304_verifyPaginationonResetAfterChangingEntriesonAccountHoldersTableTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		log.info("Clicking on the next button");
		cahp.clickOnNextButton();
		log.info("Waiting for account holder table to load");
		cahp.waitforAccountHoldersPageTableToLoad();
		log.info("Again Clicking on the next button");
		cahp.clickOnNextButton();
		log.info("Waiting for account holder table to load");
		cahp.waitforAccountHoldersPageTableToLoad();
		log.info("Navigated to Page Number--> {}",cahp.getNumberOfCurrentPage());
		String searchentryvalue="50";
		log.info("Selecting search entry value from dropdown");
		cahp.selectsearchentriesvalue(searchentryvalue);
		log.info("Selected search entry value is {}",searchentryvalue);
		log.info("Waiting for account holder table to load");
		cahp.waitforAccountHoldersPageTableToLoad();
		String Numberofcurrentpageafterselectingentries = cahp.getNumberOfCurrentPage();
		Assert.assertEquals(Integer.parseInt(Numberofcurrentpageafterselectingentries),1,"TC 304 Failed,Cashier is not redirected to First Page after Page Refresh");
		soft.assertAll();
		
		
	}
	
	@Test(priority=24)
	public void TC307_verifyBehaviourofSearchBoxFieldofAccountHoldersPagewithSpecialCharactersInputTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputName="Vikram@852";
		log.info("Entering Value with Special Character in Search Input Field");
		cahp.enterInputinSearchField(InputName);
		log.info("Entered Value with Special Character in search input field {}",InputName);
		cahp.waitforAccountHoldersPageTableToLoad();
		String resultofsearch = cahp.checkWorkingofSearchInputFieldByInputWithSpecialCharacters();
		soft.assertEquals(resultofsearch,"No matching records found","TC 307 Failed,Special Characters are not handled properly,No matching records found message not displayed");
		
		soft.assertAll();
	}
	
	@Test(priority=25)
	public void TC308_verifyBehaviourofSearchBoxFieldofAccountHoldersPagewithLongInputStringTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputName="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUV";
		log.info("Entering Long Input Value in Search Input Field");
		cahp.enterInputinSearchField(InputName);
		log.info("Entered Long Input Value in search input field {}",InputName);
		cahp.waitforAccountHoldersPageTableToLoad();
		String resultofsearch = cahp.checkWorkingofSearchInputFieldByInputWithSpecialCharacters();
		soft.assertEquals(resultofsearch,"No matching records found","TC 308 Failed,Long Input is not handled properly,No matching records found message not displayed");
		
		soft.assertAll();
	}
	
	@Test(priority=26)
	public void TC309_verifyBehaviourofSearchBoxFieldofAccountHoldersPagewithEmptyInputStringTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputName="";
		log.info("Entering Empty Input Value in Search Input Field");
		cahp.enterInputinSearchField(InputName);
		log.info("Entered Empty Input Value in search input field {}",InputName);
		cahp.waitforAccountHoldersPageTableToLoad();
		int numberofentries = cahp.checkingNoofEntriesinSinglePage();
		
		soft.assertTrue(numberofentries>0 && numberofentries<=10,"TC 309 Failed,Empty Input is not handled properly,default account holder list was not shown");
		
		soft.assertAll();
	}
	
	@Test(priority=27)
	public void TC310_verifySortingAfterPaginationinAccountHoldersPageTableTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputStatus="Approved";
		log.info("Entering Status Value in Search Input Field");
		cahp.enterInputinSearchField(InputStatus);
		log.info("Entered Status Value in search input field {}",InputStatus);
		
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByStatus(InputStatus);
		soft.assertTrue(statusofsearchfield,"Search behaviour with Status value Failed");
		log.info("Clicking on Name Header");
		cahp.clickonNameHeader();
		log.info("Clicked on Name Header");
		log.info("Collecting list of Name from all account holders");
		ArrayList<String> Actualtotallistofnames=new ArrayList<String>();
		do {
			cahp.waitforAccountHoldersPageTableToLoad();
			ArrayList<String> UINameList = cahp.getNameListofAccountHolders();
			Actualtotallistofnames.addAll(UINameList);
		
		}
		while(cahp.goToNextPage());
		log.info("Collected list of Name from all account holders");
		log.info("Checking sorting of names in ascending order");
		boolean statusofsortbyname = sort.islistsortedinascendingorder(Actualtotallistofnames);
		soft.assertTrue(statusofsortbyname, "TC 310 Failed,List of Names are not Sorted in Ascending Order accross data set");
		
		
		
		soft.assertAll();
	}
	
	@Test(priority=28)
	public void TC311_verifyChangeinEntriesafterPageRefreshinAccountHoldersPageTableTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputName="Vikram Joshi";
		log.info("Entering Name in Search Input Field");
		cahp.enterInputinSearchField(InputName);
		log.info("Entered name in search input field {}",InputName);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByName(InputName);
		soft.assertTrue(statusofsearchfield,"Search behaviour with Name Failed");
		log.info("Refreshing the WebPage");
		cahp.RefreshingWebPage();
		log.info("Waiting for Account Holders Table to Load");
		cahp.waitforAccountHoldersPageTableToLoad();
        int numberofentries = cahp.checkingNoofEntriesinSinglePage();
		soft.assertTrue(numberofentries>0 && numberofentries<=10,"TC 311 Failed,Entries not resetted to default after page refresh");
		
		soft.assertAll();
	}
	
	@Test(priority=29)
	public void TC312_verifyFunctionalityofViewButtonTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputName="Vikram Joshi";
		log.info("Entering Name in Search Input Field");
		cahp.enterInputinSearchField(InputName);
		log.info("Entered name in search input field {}",InputName);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByName(InputName);
		soft.assertTrue(statusofsearchfield,"Search behaviour with Name Failed");
		log.info("Clicking On View Button");
		cahp.clickOnViewButton();
		log.info("Navigating to Cashier User Details Page");
		cudp=new CashierUserDetailsPage();
		log.info("Checking Title of User Details Page");
		soft.assertEquals(cudp.getTitleofUserDetailsPage(),"Details of User","TC 312 Failed,View Button is not Working,user was not Redirected to User Details Page");
		
		
		soft.assertAll();
	}
	
	
	@Test(priority=30)
	public void TC313_verifyBackNavigationFromViewPageTest()
	{
		log.info("Navigating to Approved account holders page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Approved Account Holders Page is Navigated Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus, "Approved Account Holder page table is not loaded successfully");
		String InputName="Vikram Joshi";
		log.info("Entering Name in Search Input Field");
		cahp.enterInputinSearchField(InputName);
		log.info("Entered name in search input field {}",InputName);
		log.info("Checking the search value is shown in account holder row");
		boolean statusofsearchfield = cahp.checkWorkingofSearchInputFieldByName(InputName);
		soft.assertTrue(statusofsearchfield,"Search behaviour with Name Failed");
		log.info("Clicking On View Button");
		cahp.clickOnViewButton();
		log.info("Navigating to Cashier User Details Page");
		cudp=new CashierUserDetailsPage();
		log.info("Checking Title of User Details Page");
		soft.assertEquals(cudp.getTitleofUserDetailsPage(),"Details of User","View Button is not Working,user was not Redirected to User Details Page");
		log.info("Clicking on Back Button of Browser");
		cudp.clickOnBackButton();
		log.info("Navigating back to Cashier Account Holders Page");
		cahp=new CashierAccountHoldersPage();
		log.info("Checking Cashier is Redirected to Account Holders Page Successfully");
		cahp.checkLaunchofAccountHolderspage();
		boolean accountholderspagetablestatus1 = cahp.checkAccountHoldersPageTableisLoaded();
		log.info("Account Holders Page Table is loaded successfully");
		soft.assertTrue(accountholderspagetablestatus1, "Approved Account Holder page table is not loaded successfully");
		log.info("Checking Title of Account Holders Page");
		String titleofaccountholderspage = cahp.getTitleofAccountHoldersPage();
		soft.assertEquals(titleofaccountholderspage,"Details of Account Holders","TC 313 Failed,Cashier Was Not Navigated Back to Account Holder Page from View Page");
		
		soft.assertAll();
	}
	
	@AfterMethod
	public void Teardown(Method method)
	{
		log.info("Browser Closed");
		DriverManager.getDriver().quit();
		DriverManager.unload();
		log.info("========= ENDING TEST: {} =========", method.getName());
	}
	
	
	
	
	

}
