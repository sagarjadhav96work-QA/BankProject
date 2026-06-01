package com.automation.bankms.qa.webtestcases.user;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.user.Addpayeeorbeneficiarypage;
import com.automation.bankms.qa.pages.user.ChangePasswordPage;
import com.automation.bankms.qa.pages.user.Dashboardpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.Managepayeeorbeneficiarypage;
import com.automation.bankms.qa.pages.user.TransactionHistorypage;
import com.automation.bankms.qa.pages.user.TransactionReportpage;
import com.automation.bankms.qa.pages.user.UserProfileUpdatePage;
import com.automation.bankms.qa.pages.user.Useraccountopeningpage;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;


public class DashboardPageTest extends TestBase {
	
	public Homepage hp;
	public Loginpage lp;
	public Dashboardpage dp;
	public Useraccountopeningpage uaop;
	public Addpayeeorbeneficiarypage ap;
	public TransactionHistorypage thp;
	public TransactionReportpage trp;
	public UserProfileUpdatePage upup;
	public Managepayeeorbeneficiarypage mp;
	public ChangePasswordPage cpp;
	public WaitUtils wait;
	public SoftAssert soft;
	protected static final Logger Log=LogManagerUtil.getLogger(DashboardPageTest.class);
	
	
	
	@BeforeMethod
	public void Setup(Method method,ITestContext context)
	{
		log.info("========= STARTING TEST: {} =========", method.getName());
		
		Initialization();
		context.setAttribute("driver", DriverManager.getDriver());
		hp=new Homepage();
		lp=new Loginpage();
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		soft=new SoftAssert();
		
		hp.clickonnewuserlink();
		log.info("clicked on new user link");
		wait.waitforElementToBePresent(By.xpath("//h1[text()='e-Banking System | User Login']"));
		log.info("Login page loaded successfully");
	}
	
	@Test(priority=1)
	public void TC190_verifyDashboardBehaviourofUserWhoseAccountOpeningRequestinAccepted()
	{
		lp.entervalidemailid();
		log.info("Entered Valid Email Id");
		lp.entervalidpassword();
		log.info("Entered Valid Password");
		lp.clickonloginbutton();
		log.info("Clicked on Login Button");
        dp=new Dashboardpage();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		String dashboardpagetitle = dp.verifyvisibilityofdashboardpagetitle();
		soft.assertEquals(dashboardpagetitle,"Dashboard","Dashboard page title not present on dashboard page");
		log.info("Checked Dashboard page title is present");
		String userprofilename = dp.getNameofUserProfile();
		soft.assertTrue(userprofilename.length()>0,"User Profile Name not present on dashboard page");
		log.info("Checked User's Profile Name is present");
		soft.assertTrue(dp.checkGenerateReportLinkisEnabled(),"Generate Report link is not present on dashboard");
		log.info("Checked Generate Report Link is present");
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		log.info("Checked Side Panel Links are present");
		soft.assertEquals(dp.checkAvailableBalanceTextPresent(),"AVAILABLE BALANCE","Available balance Text is not Present on dashboard");
		log.info("Checked Available balance title is present");
		soft.assertTrue(dp.checkAvailableBalanceAmount(),"Available Balance amount is not present or negative");
		log.info("Checked Available balance amount");
		soft.assertEquals(dp.checkManagePayeeorBeneficiaryTextPresent(),"MANAGE PAYEE / BENEFICIARIES","Manage payee or beneficiary text is not present on dashboard");
		log.info("Checked Manage Payee or beneficiary title is present");
		soft.assertTrue(dp.checkManagePayeeorBeneficiaryCount(),"Manage payee or Beneficiary count is not present or negative");
		log.info("Checked Manage Payee count");
		soft.assertTrue(dp.checkRecentTransactionTableHeaderareVisible(),"Transaction Table Headers are not Shown correctly");
		soft.assertTrue(dp.checkTransactionCount(),"Recent Transaction count is negative or greater than 20");
		log.info("Check Recent Transaction Count");
		soft.assertAll();
	}
	
	
	@Test(priority=2)
	public void TC191_verifyDashboardBehaviourofUserWhoseAccountOpeningRequestinNotAccepted()
	{
		lp.entervalidemailidwithaccountnotopened();
		lp.entervalidpasswordwithaccountnotopened();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		String dashboardpagetitle = dp.verifyvisibilityofdashboardpagetitle();
		soft.assertEquals(dashboardpagetitle,"Dashboard","Dashboard page title not present on dashboard page");
		String userprofilename = dp.getNameofUserProfile();
		soft.assertTrue(userprofilename.length()>0,"User Profile Name not present on dashboard page");
		soft.assertTrue(dp.checkGenerateReportLinkisEnabled(),"Generate Report link is not present on dashboard");
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		soft.assertTrue(dp.checkRecentTransactionTableHeaderareVisible(),"Transaction Table Headers are not Shown correctly");
		soft.assertTrue(dp.checkTransactionCount(),"Recent Transaction count is negative or greater than 20");
		soft.assertEquals(dp.checknewuseralert(), "Alert ! New User, Account not opend yet","New User Alert not present on dashboard page");
		soft.assertAll();
	}
	
	@Test(priority=3)
	public void TC192_verifyWorkingofDashboardLink()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		dp.clickondashboardlink();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		String dashboardpagetitle = dp.verifyvisibilityofdashboardpagetitle();
		soft.assertEquals(dashboardpagetitle,"Dashboard","TC191 Failed, Dashboard page link not working properly");
		soft.assertAll();
	}
	
	@Test(priority=4)
	public void TC193_verifyWorkingofAccountOpeningLink()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		dp.clickonaccountopeninglink();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Account Details']"));
		uaop=new Useraccountopeningpage();
		uaop.getAccountDetailsTitleText();
		soft.assertEquals(uaop.getAccountDetailsTitleText(),"Account Details","TC 193 Failed,Account Opening Link not Working Properly");
		soft.assertAll();
		
	}
	
	@Test(priority=5)
	public void TC194_verifyWorkingofPayeeorBeneficiaryLink()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Add Payee / beneficiary']"));
		ap=new Addpayeeorbeneficiarypage();
		soft.assertEquals(ap.getAddPayeeorBeneficiaryTitletext(),"Add Payee / beneficiary","TC 194 Failed,Add Payee or Beneficiary Link not Working Properly");
		
		
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvibilityofmanagepayeepagetitle();
		soft.assertEquals(mp.gettextofmanagepayeepagetitle(),"Manage Payee","TC 194 Failed,Manage Payee or Beneficiary Link not Working Properly");
		soft.assertAll();
		
	}
	
	@Test(priority=6)
	public void TC195_verifyWorkingofTransactionHistoryLink()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		dp.clickontransactionhistorylink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Transaction History']"));
		thp=new TransactionHistorypage();
		soft.assertEquals(thp.getTitleofTransactionHistoryPage(),"Transaction History","TC 195 Failed,Transaction History Link not Working Properly");
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=7)
	public void TC196_verifyWorkingofReportLink()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		dp.clickonreportlink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Transaction Report']"));
		trp=new TransactionReportpage();
		soft.assertEquals(trp.getTransactionReportPageTitle(),"Transaction Report","TC 196 Failed,Report Link not Working Properly");
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=8)
	public void TC197_verifyWorkingofUserProfileLink()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("//a[@href='profile.php']"));
		dp.clickonuserprofilelink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Profile']"));
		upup=new UserProfileUpdatePage();
		soft.assertEquals(upup.getProfileUpdatePageTitle(),"Profile","TC 197 Failed,User Profile Link not Working Properly");
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=9)
	public void TC198_verifyWorkingofChangePasswordPage()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("//a[@href='change-password.php']"));
		dp.clickonchangepasswordlink();
		
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Change Password']"));
		cpp=new ChangePasswordPage();
		soft.assertEquals(cpp.getTitleTextofChangePasswordPage(),"Change Password","TC 198 Failed,Change Password link not Working Properly");
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=10)
	public void TC199_verifyWorkingofLogOutButton()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		soft.assertTrue(dp.checkSidePanelLinksareEnabled(),"Links in side panel are not present on dashboard");
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='e-Banking System | User Login']"));
		soft.assertEquals(lp.checkloginpagetitle(),"e-Banking System | User Login","TC 199 Failed,LogOut Button not Working Properly");
		soft.assertAll();
		
		
		
	}
	
	
	@Test(priority=11)
	public void TC200_verifyRecentTransactionHistoryTableBehaviour()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage();
		String dashboardpagetitle = dp.verifyvisibilityofdashboardpagetitle();
		soft.assertEquals(dashboardpagetitle,"Dashboard","Dashboard page title not present on dashboard page");
		soft.assertTrue(dp.checkRecentTransactionTableHeaderareVisible(),"TC 200 Failed,Transaction Table Headers are not Shown correctly");
		soft.assertTrue(dp.checkTransactionCount(),"TC 200 Failed,Recent Transaction count is negative or greater than 20");
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
