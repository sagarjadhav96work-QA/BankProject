package com.automation.bankms.qa.webtestcases.cashier;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.config.ConfigReader;
import com.automation.bankms.qa.pages.cashier.CashierDashboardPage;
import com.automation.bankms.qa.pages.cashier.CashierLoginPage;
import com.automation.bankms.qa.pages.cashier.CashierSearchAccountHoldersPage;
import com.automation.bankms.qa.pages.cashier.CashierTransactionHistoryPage;
import com.automation.bankms.qa.pages.cashier.CashierUserDetailsPage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.utils.WaitUtils;
import com.automation.bankms.qa.utils.WindowUtils;

public class CashierSearchAccountHolderPageTest extends TestBase {
	
	public Homepage hp;
	public CashierLoginPage clp;
	public CashierDashboardPage cdp;
	public CashierSearchAccountHoldersPage csahp;
	public CashierUserDetailsPage cudp;
	public CashierTransactionHistoryPage cthp;
	public WaitUtils wait;
	public WindowUtils window;
	
	public SoftAssert soft;
	
	@BeforeMethod
	public void Setup(Method method,ITestContext context)
	{
		
		
		log.info("========= STARTING TEST: {} =========", method.getName());
		Initialization();
		context.setAttribute("driver", driver);
		log.info("Initializing Assert");
		soft=new SoftAssert();
		log.info("Initializing Waits");
		wait=new WaitUtils(driver, 20000);
		log.info("Initializing Window Utils");
		window=new WindowUtils(driver);
		hp=new Homepage(driver);
		log.info("Clicking on Cashier Login Link");
		hp.clickoncashierloginlink();
		log.info("Navigating to Cashier Login Page");
		clp=new CashierLoginPage(driver);
		clp.waitForVisibilityofCashierLoginPage();
		log.info("Cashier Login page is successfully loaded");
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeid"));
		log.info("Entered Employee ID");
		clp.enterPassword(ConfigReader.getProperty("cashierpassword"));
		log.info("Entered Password");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		log.info("Navigating to Cashier Dashboard Page");
		cdp=new CashierDashboardPage(driver);
		cdp.waitForLaunchOfDashboardPage();
		cdp.clickOnSearchAccountHolderLink();
		log.info("Clicked on Search Account Holder Link");
		log.info("Navigating to Cashier Search Account Holder Page");
		csahp=new CashierSearchAccountHoldersPage(driver);
		
	}
	
	@Test(priority=1)
	public void TC315_verifySearchUsingValidAccountHolderNameTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername=ConfigReader.getProperty("accountholdername");
		log.info("Entering Account Holder Name in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(ConfigReader.getProperty("accountholdername"));
		log.info("Entered Account Holder Name in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("TC 315 Failed,Unable to Search User with Valid Account Holder Name");
		}
		else
		{
			boolean statusofsearchfield = csahp.checkWorkingofSearchFieldByName(Accountholdername);
			soft.assertTrue(statusofsearchfield,"TC 315 Failed,Account Holders Name does not match searched name ");
		}
		soft.assertAll();
	}
	
	@Test(priority=2)
	public void TC316_verifyPartialNameSearchTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername=ConfigReader.getProperty("accountholdername");
		log.info("Entering Partial Account Holder Name in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(ConfigReader.getProperty("accountholderpartialname"));
		log.info("Entered Partial Account Holder Name in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("TC 316 Failed,Unable to Search User with Partial Account Holder Name");
		}
		else
		{
			boolean statusofsearchfield = csahp.checkWorkingofSearchFieldByName(Accountholdername);
			soft.assertTrue(statusofsearchfield,"TC 316 Failed,Account Holders Name does not match Partial searched name ");
		}
		soft.assertAll();
	}
	
	@Test(priority=3)
	public void TC317_verifySearchwithAccountNumberTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdernumber=ConfigReader.getProperty("accountholderaccountnumber");
		log.info("Entering Account Holder Account Number in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(ConfigReader.getProperty("accountholderaccountnumber"));
		log.info("Entered Account Holder Account Number in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("TC 317 Failed,Unable to Search User with Partial Account Holder Account Number");
		}
		else
		{
			boolean statusofsearchfield = csahp.checkWorkingofSearchFieldByAccountNumber(Accountholdernumber);
			soft.assertTrue(statusofsearchfield,"TC 317 Failed,Unable to Search User with Account Holder Account Number");
		}
		soft.assertAll();
	}
	
	@Test(priority=4)
	public void TC318_verifySearchwithMobileNumberTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdermobilenumber=ConfigReader.getProperty("accountholdermobilenumber");
		log.info("Entering Account Holder Mobile Number in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(ConfigReader.getProperty("accountholdermobilenumber"));
		log.info("Entered Account Holder Mobile Number in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("TC 318 Failed,Unable to Search User with  Account Holder Mobile Number");
		}
		else
		{
			boolean statusofsearchfield = csahp.checkWorkingofSearchFieldByMobileNumber(Accountholdermobilenumber);
			soft.assertTrue(statusofsearchfield,"TC 318 Failed,Unable to Search User with  Account Holder Mobile Number");
		}
		soft.assertAll();
	}
	
	@Test(priority=5)
	public void TC319_verifyTableDisplayAllRequiredColumnsTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		log.info("Entering Account Holder Mobile Number in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(ConfigReader.getProperty("accountholdermobilenumber"));
		log.info("Entered Account Holder Mobile Number in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		soft.assertAll();
	}
	
	@Test(priority=6)
	public void TC320_verifyStatusBadgeValuesTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername="user";
		log.info("Entering Account Holder Name in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdername);
		log.info("Entered Account Holder Name in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("TC 320 Failed,Unable to Search User with Valid Account Holder Name");
		}
		else
		{
			boolean resultofstatusbadge = csahp.checkStatusBadge();
			soft.assertTrue(resultofstatusbadge,"TC 320 Failed,Status Value displays results other than Approved/Rejected/New Request");
		}
		soft.assertAll();
	}
	
	@Test(priority=7)
	public void TC321_verifyViewButtonFunctionalityTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername=ConfigReader.getProperty("accountholderpartialname");
		log.info("Entering Account Holder Name in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdername);
		log.info("Entered Account Holder Name in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("Unable to Search User with Valid Account Holder Name");
		}
		else
		{
			String parentwindow = driver.getWindowHandle();
			int currentwindowcount = driver.getWindowHandles().size();
			csahp.checkpresenceofViewButton();
			log.info("View Button is Present in Action Header Column");
			csahp.clickOnViewButton();
			log.info("Clicked on View Button");
			log.info("Waiting for window to open");
			wait.waitForNewWindowToOpen(currentwindowcount);
			log.info("Switching to New Window");
			window.switchToNewWindow();
			cudp=new CashierUserDetailsPage(driver);
			log.info("Navigating to Cashier User Details Page");
			String titleofuserdetailspage = cudp.getTitleofUserDetailsPage();
			soft.assertEquals(titleofuserdetailspage,"Details of User","TC 321 Failed,Cashier is not Navigated to User Details Page");
			window.switchToParentWindow(parentwindow);
		}
		soft.assertAll();
	}
	
	
	@Test(priority=8)
	public void TC322_verifyTransactionHistoryButtonFunctionalityTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername=ConfigReader.getProperty("accountholderpartialname");
		log.info("Entering Account Holder Name in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdername);
		log.info("Entered Account Holder Name in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("Unable to Search User with Valid Account Holder Name");
		}
		else
		{
			String parentwindow = driver.getWindowHandle();
			int currentwindowcount = driver.getWindowHandles().size();
			csahp.checkpresenceofTransactionHistoryButton();;
			log.info("Transaction History Button is Present in Action Header Column");
			csahp.clickOnTransactionHistoryButton();
			log.info("Clicked on Transaction History Button");
			log.info("Waiting for window to open");
			wait.waitForNewWindowToOpen(currentwindowcount);
			log.info("Switching to New Window");
			window.switchToNewWindow();
			cthp=new CashierTransactionHistoryPage(driver);
			log.info("Navigating to Cashier Transaction History Page");
			String titleoftransactionhistorypage = cthp.getTitleofTransactionHistoryPage();
			soft.assertEquals(titleoftransactionhistorypage,"Transaction Details","TC 322 Failed,Cashier is not Navigated to User Transaction History Page");
			window.switchToParentWindow(parentwindow);
		}
		soft.assertAll();
	}
	
	
	@Test(priority=9)
	public void TC323_VerifyNoResultMessageTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername=ConfigReader.getProperty("accountholdername");
		log.info("Entering Invalid Account Holder Name in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(ConfigReader.getProperty("accountholdername"));
		log.info("Entered Invalid Account Holder Name in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("TC 323 Failed,Message No Results Found is not displayed");
		}
		else
		{
			boolean statusofsearchfield = csahp.checkWorkingofSearchFieldByName(Accountholdername);
			soft.assertTrue(statusofsearchfield,"TC 323 Failed,Account Holder Name does not match searched value.");
		}
		soft.assertAll();
		
	}
	

	@Test(priority=10)
	public void TC324_VerifyBehaviourofEmptySearchTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		log.info("Did not Enter any Account Number/Mobile Number/Name in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		log.info("Checking Validation Message is Present");
		String ValidationMessage = csahp.getValidationMessageofSearchInputField();
		soft.assertEquals(ValidationMessage,"Please fill in this field.","TC 324 Failed,No Validation Message Found,Empty Search not Handled Properly");
		soft.assertAll();
		
	}
	
	@Test(priority=11)
	public void TC325_VerifySearchWithSpecialCharactersTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername="@#$%";
		log.info("Entering Special Characters in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdername);
		log.info("Entered Special Characters in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		soft.assertEquals(totalnumberofaccountholderspresent,0,"TC 325 Failed,Special Character search was not Handled properly");
		soft.assertAll();
		
	}
	

	@Test(priority=12)
	public void TC326_VerifySystemAgainstSQLInjectionTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername="' OR 1=1 --";
		log.info("Entering SQL input in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdername);
		log.info("Entered SQL input in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		soft.assertEquals(totalnumberofaccountholderspresent,0,"TC 326 Failed,SQL Injection search was not Handled properly,unauthorised data was exposed");
		soft.assertAll();
		
	}
	
	@Test(priority=13)
	public void TC331_VerifySearchResponseTimeTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		long starttime = System.currentTimeMillis();
		;
		log.info("Entering Partial Account Holder Name in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(ConfigReader.getProperty("accountholderpartialname"));
		log.info("Entered Partial Account Holder Name in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		long endtime = System.currentTimeMillis();
		long SLA=endtime-starttime;
		log.info("The Response Time is {}",SLA);
		soft.assertTrue(SLA<2000, "TC 331 Failed,SLA is more than 2 Seconds");
		
		soft.assertAll();
		
	}
	
	@Test(priority=14)
	public void TC335_VerifyLongInputHandlingTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername="A1b2C3d4E5f6G7h8I9j0K1l2M3n4O5p6Q7r8S9t0U1v2W3x4Y5z6A7b8C9d0E1f2G3h4I5j6K7l8M9n0O1p2Q3r4S5t6U7v8W9x0Y1z2A3b4C5d6E7f8G9h0I1j2K3l4M5n6O7p8Q9r0S1t2U3v4W5x6Y7z8A9b0C1d2E3f4G5h6I7j8K9l0M1n2O3p4Q5r6S7t8U9v0W1x2Y3z4";
		log.info("Entering Long input in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdername);
		log.info("Entered Long input in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		soft.assertEquals(totalnumberofaccountholderspresent,0,"TC 335 Failed,long input search was not Handled properly");
		soft.assertAll();
		
	}
	
	@Test(priority=15)
	public void TC336_VerifyTrimmingofInputTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername="  Manish  ";
		log.info("Entering  Partial Account Holder Name in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdername);
		log.info("Entered Partial Account Holder Name in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("TC 336 Failed,Input data not trimmed and desired account holder details not shown");
		}
		else
		{
			boolean statusofsearchfield = csahp.checkWorkingofSearchFieldByName(Accountholdername);
			soft.assertTrue(statusofsearchfield,"TC 336 Failed,Account Holders Name does not match Partial searched name ");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=16)
	public void TC337_VerifyCaseInsensitiveSearchTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername=ConfigReader.getProperty("accountholderpartialname");
		String Accountholdernameinlowercase=Accountholdername.toLowerCase();
		log.info("Entering  Partial Account Holder Name in LowerCase in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdernameinlowercase);
		log.info("Entered Partial Account Holder Name in LowerCase Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("TC 337 Failed,Account Search is case Sensitive");
		}
		else
		{
			boolean statusofsearchfield = csahp.checkWorkingofSearchFieldByName(Accountholdername);
			soft.assertTrue(statusofsearchfield,"TC 337 Failed,Account Holders Name does not match Partial searched name ");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=17)
	public void TC339_VerifyAlphaNumbericInputSearchTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername="Manish Gupta 1233211230";
		
		log.info("Entering Alpha Numeric Data in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdername);
		log.info("Entered Alpha Numeric Data in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		soft.assertEquals(totalnumberofaccountholderspresent,0,"TC 339 Failed,Alpha Numeric search was not Handled properly,Incorrect Data is Displayed");
		soft.assertAll();
		
	}
	
	@Test(priority=18)
	public void TC340_VerifyInvalidMobileNumberInputSearchTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdermobilenumber="1233211230ABCS#";
		log.info("Entering Invalid Account Holder Mobile Number in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdermobilenumber);
		log.info("Entered Account Holder Mobile Number in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		soft.assertEquals(totalnumberofaccountholderspresent,0,"TC 340 Failed,Invalid Mobile Number search was not Handled properly,Incorrect Data is Displayed");
		soft.assertAll();
		
	}
	
	@Test(priority=19)
	public void TC344_VerifySearchwithBlankSpacesTest()
	{

		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdername="   ";
		log.info("Entering Blank Spaces in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdername);
		log.info("Entered Blank Spaces input in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		soft.assertEquals(totalnumberofaccountholderspresent,0,"TC 344 Failed,Blank Spaces search was not Handled properly");
		soft.assertAll();
	}
	
	@Test(priority=20)
	public void TC345_verifyClearingSearchResetResultTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		log.info("Entering Account Holder Name in Search Input Field");
		csahp.enterNameOrAccountnumberOrMobilenumber(ConfigReader.getProperty("accountholdername"));
		log.info("Entered Account Holder Name in Search Input Field");
		log.info("Clearing Account Holder Name from Search Input Field");
		csahp.clearNameOrAccountnumberOrMobilenumber();
		log.info("Cleared Account Holder Name from Search Input Field");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		log.info("Checking Validation Message is Present");
		String ValidationMessage = csahp.getValidationMessageofSearchInputField();
		soft.assertEquals(ValidationMessage,"Please fill in this field.","TC 345 Failed,No Validation Message Found,Cleared Search was not Handled Properly");
		soft.assertAll();
	}
	
	@Test(priority=21)
	public void TC348_verifyHandlingofLeadingZerosTest()
	{
		log.info("Checking Navigation of Search Account Holders Page");
		csahp.checkingPresenceOfElementsonSearchAccountHolderPage();
		log.info("Cashier is successfully navigated to Search Account Holders Page");
		String Accountholdernumber="000147569176";
		log.info("Entering Account Holder Account Number in Search input field");
		csahp.enterNameOrAccountnumberOrMobilenumber(Accountholdernumber);
		log.info("Entered Account Holder Account Number in Search input field ");
		csahp.clickOnSearchButton();
		log.info("Clicked on Search Button");
		csahp.checkPresenceofSearchAccountHolderPageTable();
		int totalnumberofaccountholderspresent = csahp.checkNumberofAccountHoldersPresent();
		log.info("The Total Number of Account Holders displayed in table are {}",totalnumberofaccountholderspresent);
		if(totalnumberofaccountholderspresent==0)
		{
			Assert.fail("TC 348 Failed,Leading Zeros were not handled properly,Desired results were not shown");
		}
		else
		{
			boolean statusofsearchfield = csahp.checkWorkingofSearchFieldByAccountNumber(Accountholdernumber);
			soft.assertTrue(statusofsearchfield,"TC 338 Failed,results shown does not match expected results");
		}
		soft.assertAll();
	}
	
	
	
	
	@AfterMethod
	public void Teardown(Method method)
	{
		log.info("Browser Closed");
		driver.quit();
		log.info("========= ENDING TEST: {} =========", method.getName());
	}
	
	
	
	
	
	
	

}
