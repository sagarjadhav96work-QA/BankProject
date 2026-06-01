package com.automation.bankms.qa.webtestcases.cashier;

import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
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
import com.automation.bankms.qa.pages.cashier.CashierDashboardPage;
import com.automation.bankms.qa.pages.cashier.CashierForgotPasswordPage;
import com.automation.bankms.qa.pages.cashier.CashierLoginPage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.utils.CommonUtils;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierLoginPageTest extends TestBase{
	
	public Homepage hp;
	public CashierLoginPage clp;
	public CashierDashboardPage cdp;
	public CashierForgotPasswordPage fp;
	 protected static final Logger log=LogManagerUtil.getLogger(CashierLoginPageTest.class);
	public SoftAssert soft;
	public WaitUtils wait;
	
	@BeforeMethod()
	
	public void Setup(Method method,ITestContext context)
	{
		log.info("========= STARTING TEST: {} =========", method.getName());
		Initialization();
		soft=new SoftAssert();
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		context.setAttribute("driver", DriverManager.getDriver());
		hp=new Homepage();
		log.info("Clicking on Cashier Login Page");
		hp.clickoncashierloginlink();
		log.info("Navigating to Cashier Login Page");
		clp=new CashierLoginPage();
		clp.waitForVisibilityofCashierLoginPage();
		log.info("Navigated to Cashier Login Page");
		
	}
	
	@Test(priority=1)
	public void TC248_verifyCashierLoginWithValidCredentials()
	{
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeid"));
		log.info("Employee id Entered");
		clp.enterPassword(ConfigReader.getProperty("cashierpassword"));
		log.info("Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		cdp=new CashierDashboardPage();
		String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
		log.info("extracted title text from dashboard Page");
		Assert.assertEquals(dashboardpageconfirmationtext,"Dashboard","TC 248 Failed,Cashier Login with Valid Credentials Failed");
		log.info("Cashier has accessed the Dashboard Page");
		
		
	}
	
	@Test(priority=2)
	public void TC249_verifyCashierLoginFailWithInvalidCredentials()
	{
		String Employeeid=CommonUtils.generateRandomEmployeeID();
		String Password=CommonUtils.generateRandomPassword(8);
		clp.enterEmployeeId(Employeeid);
		log.info("Invalid Employee id Entered");
		clp.enterPassword(Password);
		log.info("Invalid Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		if(clp.checkalertispresent()==true)
		{
			log.info("Presence of Alert is confirmed");
			log.info("Switching focus to alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			String alertconfirmationtext = alt.getText();
			log.info("extracted text from alert : {}",alertconfirmationtext);
			soft.assertEquals(alertconfirmationtext,"Invalid Details","TC 249 Failed,Alert Text is not matching,Cashier Login was successfull with invalid credentials");
			alt.accept();
			log.info("Clicked on accept button of alert");
		}
		else
		{
			
			cdp=new CashierDashboardPage();
			String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
			log.info("extracted title text from dashboard Page");
			soft.assertEquals(dashboardpageconfirmationtext,"Dashboard","TC 249 Failed,Cashier Login was successfull with invalid credentials");
			log.info("Cashier has accessed the Dashboard Page");
			
		}
		
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=3)
	public void TC250_verifyCashierLoginFailWithInvalidPassword()
	{
		
		String Password=CommonUtils.generateRandomPassword(8);
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeid"));
		log.info("Valid Employee id Entered");
		clp.enterPassword(Password);
		log.info("Invalid Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		if(clp.checkalertispresent()==true)
		{
			log.info("Presence of Alert is confirmed");
			log.info("Switching focus to alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			String alertconfirmationtext = alt.getText();
			log.info("extracted text from alert : {}",alertconfirmationtext);
			soft.assertEquals(alertconfirmationtext,"Invalid Details","TC 250 Failed,Alert Text is not matching,Cashier Login was successfull with invalid Password");
			alt.accept();
			log.info("Clicked on accept button of alert");
		}
		else
		{
			
			cdp=new CashierDashboardPage();
			String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
			log.info("extracted title text from dashboard Page");
			soft.assertEquals(dashboardpageconfirmationtext,"Dashboard","TC 250 Failed,Cashier Login was successfull with invalid Password");
			log.info("Cashier has accessed the Dashboard Page");
			
		}
		
		soft.assertAll();
		
		
	}
	
	@Test(priority=4)
	public void TC251_verifyCashierLoginBehaviourwithEmployeeIdFieldEmpty()
	{
		
		
		log.info("Kept Employee id Field Empty");
		clp.enterPassword(ConfigReader.getProperty("cashierpassword"));
		log.info("Valid Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		String validationtext = clp.getValidationTextofEmployeeIDField();
		log.info("Checked Employee Id Field Validation Message ==> {}",validationtext);
		Assert.assertEquals(validationtext,"Please fill in this field.","TC 251 Failed,Cashier Login was successfull with Empty Employee Id field,no proper error validation message");
		
		
		
		
		
	}
	
	@Test(priority=5)
	public void TC252_verifyCashierLoginBehaviourwithPasswordFieldEmpty()
	{
	
		
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeid"));
		log.info("Valid Employee Id Entered");
		log.info("Kept Password Field Empty");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		String validationtext = clp.getValidationTextofPasswordField();
		log.info("Checked Password Field Validation Message ==> {}",validationtext);
		Assert.assertEquals(validationtext,"Please fill in this field.","TC 252 Failed,Cashier Login was successfull with Empty Password field,no proper error validation message");
		
		
		
		
		
	}
	
	@Test(priority=6)
	public void TC253_verifyCashierLoginBehaviourwithMinimumRequiredinputinEmployeeidField()
	{
	
		
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeidwithminimuminputcharacter"));
		log.info("Employee Id Entered with one input character");
		clp.enterPassword(ConfigReader.getProperty("cashierpasswordforempidwithminimuminputcharacter"));
		log.info("Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		cdp=new CashierDashboardPage();
		String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
		log.info("extracted title text from dashboard Page==> {}",dashboardpageconfirmationtext);
		Assert.assertEquals(dashboardpageconfirmationtext,"Dashboard","TC 253 Failed,Cashier Login with Minimum Input Character in Employee Id Failed");
		log.info("Cashier has accessed the Dashboard Page");
		
		
		
	}
	
	@Test(priority=7)
	public void TC254_verifyCashierLoginBehaviourwithMaximumRequiredinputinEmployeeidField()
	{
	
		
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeidwithmaximuminputcharacter"));
		log.info("Employee Id Entered with more than 10 input character");
		clp.enterPassword(ConfigReader.getProperty("cashierpasswordforempidwithmaximuminputcharacter"));
		log.info("Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		if(clp.checkalertispresent()==true)
		{
			log.info("Presence of Alert is confirmed");
			log.info("Switching focus to alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			String alertconfirmationtext = alt.getText();
			soft.assertEquals(alertconfirmationtext,"Employee Id should be less than 10 or equal to 10 characters");
			alt.accept();
			log.info("Clicked on accept button of alert");
		}
		else
		{
		cdp=new CashierDashboardPage();
		String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
		log.info("extracted title text from dashboard Page==> {}",dashboardpageconfirmationtext);
		soft.fail("TC254 Failed: Login succeeded with invalid Employee ID (>10 chars)");
		log.info("Cashier has accessed the Dashboard Page");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=8)
	public void TC255_verifyCashierLoginBehaviourwithMinimumRequiredinputinPasswordField()
	{
	
		
		clp.enterEmployeeId(ConfigReader.getProperty("cashierempidforminimumpasswordinputcharacter"));
		log.info("Employee Id Entered ");
		clp.enterPassword(ConfigReader.getProperty("cashierpasswordwithminimuminputcharacter"));
		log.info("Password Entered with minimum input character");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		cdp=new CashierDashboardPage();
		String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
		log.info("extracted title text from dashboard Page==> {}",dashboardpageconfirmationtext);
		Assert.assertEquals(dashboardpageconfirmationtext,"Dashboard","TC 255 Failed,Cashier Login with Minimum Input Character in Password field Failed");
		log.info("Cashier has accessed the Dashboard Page");
		
		
		
	}
	
	@Test(priority=9)
	public void TC256_verifyCashierLoginBehaviourwithMaximumRequiredinputinPasswordField()
	{
	
		
		clp.enterEmployeeId(ConfigReader.getProperty("cashierempidformaximumpasswordinputcharacter"));
		log.info("Employee Id Entered");
		clp.enterPassword(ConfigReader.getProperty("cashierpasswordwithmaximuminputcharacter"));
		log.info("Password Entered with more than 10 input character");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		if(clp.checkalertispresent()==true)
		{
			log.info("Presence of Alert is confirmed");
			log.info("Switching focus to alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			String alertconfirmationtext = alt.getText();
			soft.assertEquals(alertconfirmationtext,"Password should be less than 10 or equal to 10 characters");
			alt.accept();
			log.info("Clicked on accept button of alert");
		}
		else
		{
		cdp=new CashierDashboardPage();
		String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
		log.info("extracted title text from dashboard Page==> {}",dashboardpageconfirmationtext);
		soft.fail("TC256 Failed: Login succeeded with invalid Password (>10 chars)");
		log.info("Cashier has accessed the Dashboard Page");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=10)
	public void TC257_verifyCashierLoginBehaviourwithSpecialCharactersinEmployeeIdField()
	{
	
		
		clp.enterEmployeeId(ConfigReader.getProperty("cashierempidwithspecialcharacterinemployeeidfield"));
		log.info("Employee Id Entered with Special Characters in it");
		clp.enterPassword(ConfigReader.getProperty("cashierpasswordwithspecialcharacterinemployeeidfield"));
		log.info("Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		if(clp.checkalertispresent()==true)
		{
			log.info("Presence of Alert is confirmed");
			log.info("Switching focus to alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			String alertconfirmationtext = alt.getText();
			soft.assertEquals(alertconfirmationtext,"Unsupported Format");
			alt.accept();
			log.info("Clicked on accept button of alert");
		}
		else
		{
		cdp=new CashierDashboardPage();
		String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
		log.info("extracted title text from dashboard Page==> {}",dashboardpageconfirmationtext);
		soft.fail("TC257 Failed: Login succeeded with Special Characters in Employee Id Field");
		log.info("Cashier has accessed the Dashboard Page");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=11)
	public void TC258_verifyCashierLoginBehaviourwithSpecialCharactersinPasswordField()
	{
	
		
		clp.enterEmployeeId(ConfigReader.getProperty("cashierempidwithspecialcharacterinpasswordfield"));
		log.info("Employee Id Entered");
		clp.enterPassword(ConfigReader.getProperty("cashierpasswordwithspecialcharacterinpasswordfield"));
		log.info("Password Entered with Special characters in it");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		
		cdp=new CashierDashboardPage();
		String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
		log.info("extracted title text from dashboard Page==> {}",dashboardpageconfirmationtext);
		Assert.assertEquals(dashboardpageconfirmationtext, "Dashboard","TC 258 Failed,Cashier Login Failed with Special Characters in Password Field");
		log.info("Cashier has accessed the Dashboard Page");
		
		
		
	}
	
	@Test(priority=12)
	public void TC259_verifyEmployeeIdFieldCaseSensitivity()
	{
	
		String Employeeid=ConfigReader.getProperty("cashieremployeeid");
		
		clp.enterEmployeeId(Employeeid.toLowerCase());
		log.info("Employee Id Entered in Lower Case");
		clp.enterPassword(ConfigReader.getProperty("cashierpassword"));
		log.info("Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		
		if(clp.checkalertispresent()==true)
		{
			log.info("Presence of Alert is confirmed");
			log.info("Switching focus to alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			String alertconfirmationtext = alt.getText();
			log.info("extracted text from alert : {}",alertconfirmationtext);
			soft.assertEquals(alertconfirmationtext,"Invalid Details","TC 259 Failed,Alert Text is not matching,Cashier Login was successfull with LowerCase Employee Id Value");
			alt.accept();
			log.info("Clicked on accept button of alert");
		}
		else
		{
			
			cdp=new CashierDashboardPage();
			String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
			log.info("extracted title text from dashboard Page ==> {}",dashboardpageconfirmationtext);
			soft.fail("TC 259 Failed,Cashier Login was successfull with LowerCase Employee Id Value");
			log.info("Cashier has accessed the Dashboard Page");
			
		}
		
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=13)
	public void TC260_verifyPasswordFieldCaseSensitivity()
	{
	
		String password=ConfigReader.getProperty("cashierpassword");
		
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeid"));
		log.info("Employee Id Entered");
		clp.enterPassword(password.toLowerCase());
		log.info("Password Entered in Lower Case");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		
		if(clp.checkalertispresent()==true)
		{
			log.info("Presence of Alert is confirmed");
			log.info("Switching focus to alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			String alertconfirmationtext = alt.getText();
			log.info("extracted text from alert : {}",alertconfirmationtext);
			soft.assertEquals(alertconfirmationtext,"Invalid Details","TC 260 Failed,Alert Text is not matching,Cashier Login was successfull with LowerCase Password Value");
			alt.accept();
			log.info("Clicked on accept button of alert");
		}
		else
		{
			
			cdp=new CashierDashboardPage();
			String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
			log.info("extracted title text from dashboard Page ==> {}",dashboardpageconfirmationtext);
			soft.fail("TC 259 Failed,Cashier Login was successfull with LowerCase Value");
			log.info("Cashier has accessed the Dashboard Page");
			
		}
		
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=14)
	public void TC261_verifyNavigationAndWorkingofCashierLoginPage()
	{
		log.info("Checking Employee id field is Enabled");
		soft.assertTrue(clp.checkEmployeeIdInputFieldisEnabled(), "Employee ID field is not enabled");
		log.info("Checked Employee id field is Enabled");
		log.info("Checking Password field is Enabled");
		soft.assertTrue(clp.checkPasswordInputFieldisEnabled(),"Password field is not enabled");
		log.info("Checked Password field is Enabled");
		soft.assertAll();
		
	}
	
	@Test(priority=15)
	public void TC262_verifyNavigationAndWorkingofForgotPasswordLink()
	{
		log.info("Checking Employee id field is Enabled");
		soft.assertTrue(clp.checkEmployeeIdInputFieldisEnabled(), "Employee ID field is not enabled");
		log.info("Checked Employee id field is Enabled");
		log.info("Checking Password field is Enabled");
		soft.assertTrue(clp.checkPasswordInputFieldisEnabled(),"Password field is not enabled");
		log.info("Checked Password field is Enabled");
		log.info("Checking navigation and working of Forgot Password Link");
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		fp=new CashierForgotPasswordPage();
		soft.assertTrue(fp.checkEmailAddressInputFieldIsEnabled(),"Email Address Field is not Enabled");
		log.info("Email Address Field is Enabled");
		soft.assertTrue(fp.checkMobileNumberInputFieldIsEnabled(),"Mobile Number Field is not Enabled");
		log.info("Mobile Number Field is Enabled");
		soft.assertTrue(fp.checkNewPasswordInputFieldIsEnabled(),"New Password Field is not Enabled");
		log.info("New Password Field is Enabled");
		soft.assertTrue(fp.checkConfirmPasswordInputFieldIsEnabled(),"Confirm Password Field is not Enabled");
		log.info("Confirm Password Field is Enabled");
		log.info("Forgot Password Page Navigation is successfull");
		soft.assertAll();
		
	}
	
	@Test(priority=16)
	public void  TC263_verifyNavigationAndWorkingofHomePageLink()
	{
		log.info("Checking Employee id field is Enabled");
		soft.assertTrue(clp.checkEmployeeIdInputFieldisEnabled(), "Employee ID field is not enabled");
		log.info("Checked Employee id field is Enabled");
		log.info("Checking Password field is Enabled");
		soft.assertTrue(clp.checkPasswordInputFieldisEnabled(),"Password field is not enabled");
		log.info("Checked Password field is Enabled");
		log.info("Checking navigation and working of Homepage Link");
		log.info("Clicking on Homepage Link");
		clp.clickOnHomepagelink();
		hp=new Homepage();
		log.info("Checking New User Link is Clickable");
		soft.assertTrue(hp.checkNewuserLinkisClickable(), "New User Link is Not Clickable");
		log.info("Checking Cashier Link is Clickable");
		soft.assertTrue(hp.checkCashierLinkisClickable(), "Cashier Link is Not Clickable");
		log.info("Checking Admin Link is Clickable");
		soft.assertTrue(hp.checkAdminLinkisClickable(), "Admin Link is Not Clickable");
		
		log.info("Checking application title displayed on Homepage");
		soft.assertEquals(hp.getTitleofHomepage(),"e-Banking System","Application Title not displayed on Homepage");
		log.info("HomePage Navigation is successfull");
		soft.assertAll();
		
	}
	
	@Test(priority=17)
	public void TC264_verifyCashierLoginWithSQLInjectioninEmployeeIDfield()
	{
		String Employeeid="' OR '1'='1";
		clp.enterEmployeeId(Employeeid);
		log.info("Employee id Entered");
		clp.enterPassword(ConfigReader.getProperty("cashierpassword"));
		log.info("Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		
		if(clp.checkalertispresent()==true)
		{
			log.info("Presence of Alert is confirmed");
			log.info("Switching focus to alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			String alertconfirmationtext = alt.getText();
			log.info("extracted text from alert : {}",alertconfirmationtext);
			soft.assertEquals(alertconfirmationtext,"Invalid Details","TC 264 Failed,Alert Text is not matching,Cashier Login was successfull with SQL injection attempt in Employee ID field");
			alt.accept();
			log.info("Clicked on accept button of alert");
		}
		else
		{
			
			cdp=new CashierDashboardPage();
			String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
			log.info("extracted title text from dashboard Page");
			soft.assertEquals(dashboardpageconfirmationtext,"Dashboard","TC 264 Failed,Cashier Login was successfull with SQL injection attempt in Employee ID field");
			log.info("Cashier has accessed the Dashboard Page");
			
		}
		
		
	}
	
	@Test(priority=18)
	public void TC265_verifyCashierLoginWithSQLInjectioninPasswordfield()
	{
		String Password="admin' --";
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeid"));
		log.info("Employee id Entered");
		clp.enterPassword(Password);
		log.info("Password Entered");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		
		if(clp.checkalertispresent()==true)
		{
			log.info("Presence of Alert is confirmed");
			log.info("Switching focus to alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			String alertconfirmationtext = alt.getText();
			log.info("extracted text from alert : {}",alertconfirmationtext);
			soft.assertEquals(alertconfirmationtext,"Invalid Details","TC 265 Failed,Alert Text is not matching,Cashier Login was successfull with SQL injection attempt in Password field");
			alt.accept();
			log.info("Clicked on accept button of alert");
		}
		else
		{
			
			cdp=new CashierDashboardPage();
			String dashboardpageconfirmationtext = cdp.getTitleofDashboardPage();
			log.info("extracted title text from dashboard Page");
			soft.assertEquals(dashboardpageconfirmationtext,"Dashboard","TC 265 Failed,Cashier Login was successfull with SQL injection attempt in Password field");
			log.info("Cashier has accessed the Dashboard Page");
			
		}
		
		
	}
	
	
	
	
	
	@AfterMethod()
	public void Teardown(Method method)
	{
		log.info("Browser Closed");
		DriverManager.getDriver().quit();
		DriverManager.unload();
		log.info("========= ENDING TEST: {} =========", method.getName());
	}
	
	
	

}
