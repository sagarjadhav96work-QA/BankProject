package com.automation.bankms.qa.webtestcases.cashier;

import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierForgotPasswordPageTest extends TestBase {
	
	public Homepage hp;
	public CashierLoginPage clp;
	public CashierForgotPasswordPage cfpp;
	public CashierDashboardPage cdp;
	public WaitUtils wait;
	public SoftAssert soft;
	
	
	protected final static Logger log=LogManagerUtil.getLogger(CashierForgotPasswordPageTest.class);
	
	@BeforeMethod
	public void Setup(Method method,ITestContext context)
	{
		log.info("========= STARTING TEST: {} =========", method.getName());
		Initialization();
		context.setAttribute("driver", DriverManager.getDriver());
		log.info("Initializing Wait Utils");
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		log.info("Initializing Soft Asssert");
		soft=new SoftAssert();
		log.info("Initializing Home Page");
		hp=new Homepage();
		log.info("Checking New User Link is Clickable");
		soft.assertTrue(hp.checkNewuserLinkisClickable(), "New User Link is Not Clickable");
		log.info("Checking Cashier Link is Clickable");
		soft.assertTrue(hp.checkCashierLinkisClickable(), "Cashier Link is Not Clickable");
		log.info("Checking Admin Link is Clickable");
		soft.assertTrue(hp.checkAdminLinkisClickable(), "Admin Link is Not Clickable");
		log.info("Clicking on Cashier Login Link");
		hp.clickoncashierloginlink();
		log.info("Navigating to Cashier Login Page");
		clp=new CashierLoginPage();
		log.info("Waiting for Visibility of Elements on Cashier Login Page");
		clp.waitForVisibilityofCashierLoginPage();
		log.info("Sucessfully Navigated to Cashier Login Page");
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=1)
	public void TC352_verifyCashierisAbleToResetPasswordwithValidDetailsTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     String alerttext = alt.getText();
		     soft.assertEquals(alerttext,"Your Password succesfully changed","TC 352 Failed,Password Reset With Valid Details Failed");
		     alt.accept();
		}
		soft.assertAll();
		
	}
	
	@Test(priority=2)
	public void TC353_verifyErrorMessageKeepingEmailAddressFieldEmptyTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		    Alert alt = DriverManager.getDriver().switchTo().alert();
		    alt.accept();
		     Assert.fail("TC 353 Failed,Password Resetted With Email Address Field Empty");
		     
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofEmailAddressField();
			log.info("The Validation Message is {}",validationmessage);
			soft.assertEquals(validationmessage,"Please fill in this field.","TC 353 Failed,Password Resetted With Email Address Field Empty.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=3)
	public void TC354_verifyErrorMessageEnteringEmailAddressinInvalidFormatEmptyTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress("vikramjoshi209gmail.com");
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     alt.accept();
		     Assert.fail("TC 354 Failed,Password Resetted With Invalid Email Address Format");
		     
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofEmailAddressField();
			log.info("The Validation Message is {}",validationmessage);
			soft.assertTrue(validationmessage.contains("Please include an '@' in the email address."),"TC 354 Failed,Password Resetted With Invalid Email Address Format.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=4)
	public void TC355_verifyErrorMessageKeepingMobileFieldEmptyTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     alt.accept();
		     Assert.fail("TC 355 Failed,Password Resetted With Mobile Number Field Empty");
		    
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofMobileNumberField();
			log.info("The Validation Message is {}",validationmessage);
			soft.assertEquals(validationmessage,"Please fill in this field.","TC 355 Failed,Password Resetted With Mobile Number Field Empty.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=5)
	public void TC356_verifyErrorMessageEnteringMobileNumberinIncorrectFormatTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber("abcdefghij");
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     alt.accept();
		     Assert.fail("TC 356 Failed,Password Resetted With Invalid Mobile Number Format");
		     
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofMobileNumberField();
			log.info("The Validation Message is {}",validationmessage);
			soft.assertEquals(validationmessage,"Please match the format requested.","TC 356 Failed,Password Resetted With Invalid Mobile Number Format.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=6)
	public void TC357_verifyErrorMessageEnteringInvalidEmailAddressTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress("vikramjoshi2091315@gmail.com");
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			 log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     String alerttext = alt.getText();
		     soft.assertEquals(alerttext,"Email id or Mobile no is invalid","TC 357 Failed,Password Resetted With Invalid Email Address");
		     alt.accept();
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofEmailAddressField();
			log.info("The Validation Message is {}",validationmessage);
			soft.assertEquals(validationmessage,"Email id or Mobile no is invalid","TC 357 Failed,Password Resetted With Invalid Email Address.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=7)
	public void TC358_verifyErrorMessageEnteringInvalidMobileNumberTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber("123456789");
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     String alerttext = alt.getText();
		     soft.assertEquals(alerttext,"Email id or Mobile no is invalid","TC 358 Failed,Password Resetted With Invalid Mobile Number");
		     alt.accept();
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofMobileNumberField();
			log.info("The Validation Message is {}",validationmessage);
			soft.assertEquals(validationmessage,"Please match the format requested.","TC 358 Failed,Password Resetted With Invalid Mobile Number.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=8)
	public void TC359_verifyErrorMessageEnteringDifferentPasswordinNewandConfirmPasswordFieldTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashieroriginalpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     String alerttext = alt.getText();
		     soft.assertEquals(alerttext,"New Password and Confirm Password Field do not match  !!","TC 359 Failed,Password Resetted With Different Passwords in Confirm and New Password Fields");
		     alt.accept();
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofEmailAddressField();
			log.info("The Validation Message is {}",validationmessage);
			soft.assertEquals(validationmessage,"Email id or Mobile no is invalid","TC 359 Failed,Password Resetted With Different Passwords in Confirm and New Password Fields.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=9)
	public void TC360_verifyErrorMessageKeepingNewPasswordFieldEmptyTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashieroriginalpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     alt.accept();
		     Assert.fail("TC 360 Failed,Password Resetted With New Password Field Empty");
		     alt.accept();
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofNewPasswordField();
			soft.assertEquals(validationmessage,"Please fill in this field.","TC 360 Failed,Password Resetted With New Password Field Empty.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=10)
	public void TC361_verifyErrorMessageKeepingConfirmPasswordFieldEmptyTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     alt.accept();
		     Assert.fail("TC 361 Failed,Password Resetted With Confirm Password Field Empty");
		     alt.accept();
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofConfirmPasswordField();
			soft.assertEquals(validationmessage,"Please fill in this field.","TC 361 Failed,Password Resetted With Confirm Password Field Empty.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=11)
	public void TC362_verifyCashierisAbleToResetPasswordwithLeadingorTrailingSpacesinPasswordFieldTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		String PasswordwithSpaces="  Sagar@19  ";
		cfpp.enterNewPassword(PasswordwithSpaces);
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(PasswordwithSpaces);
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     String alerttext = alt.getText();
		     soft.assertEquals(alerttext,"Your Password succesfully changed","Password Reset With Leading or Trailing Spaces in Password field Failed");
		     alt.accept();
		}
		cfpp.waitForLoadingofForgotPasswordPage();
		cfpp.clickonBackToHomePageLink();
		log.info("Clicked on Back to HomePage Link");
        wait.waitForElementToDisappear(By.id("overlayer"));
		log.info("Checking Cashier Link is Clickable");
		soft.assertTrue(hp.checkCashierLinkisClickable(), "Cashier Link is Not Clickable");
		hp.clickoncashierloginlink();
		log.info("Navigating to Cashier Login Page");
		log.info("Waiting for Visibility of Elements on Cashier Login Page");
		clp.waitForVisibilityofCashierLoginPage();
		log.info("Sucessfully Navigated to Cashier Login Page");
		clp.enterEmployeeId(ConfigReader.getProperty("cashieremployeeid"));
		log.info("Entered Employee ID");
		clp.enterPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Entered Password");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		if(clp.checkalertispresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     String alerttext = alt.getText();
		     soft.assertFalse(alerttext.contains("Invalid Details"),"TC 362 Failed,Cashier Login Failed,Password Not Auto Trimmed ");
		     alt.accept();
		}
		else
		{
		cdp=new CashierDashboardPage();
		String titleofdashboardpage = cdp.getTitleofDashboardPage();
		soft.assertEquals(titleofdashboardpage, "Dashboard","TC 362 Failed,Cashier Login Failed,Password Not Auto Trimmed ");
		}
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=12)
	public void TC363_verifyCashierisAbleToResetPasswordwithLongInputPasswordTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashierlonginputpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashierlonginputpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     String alerttext = alt.getText();
		     soft.assertEquals(alerttext,"Your Password succesfully changed","TC 363 Failed,Password Reset With Long Input Failed");
		     alt.accept();
		}
		soft.assertAll();
		
	}
	
	@Test(priority=13)
	public void TC364_verifyCashierisAbleToResetPasswordwithSpecialCharactersinPasswordTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword("Ab@x#K!p$Q");
		log.info("New Password Entered");
		cfpp.enterConfirmPassword("Ab@x#K!p$Q");
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     String alerttext = alt.getText();
		     soft.assertEquals(alerttext,"Your Password succesfully changed","TC 364 Failed,Password Reset With Special Characters Failed");
		     alt.accept();
		}
		soft.assertAll();
		
	}
	
	@Test(priority=14)
	public void TC367_verifySQLInjectionAttemptThroughEmailAddressFieldTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress("' OR 1=1--");
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     alt.accept();
		     Assert.fail("TC 367 Failed,Password Resetted With SQL injection In Email Address Field");
		     
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofEmailAddressField();
			log.info("The Validation Message is {}",validationmessage);
			soft.assertTrue(validationmessage.contains("Please include an '@' in the email address."),"TC 367 Failed,Password Resetted With SQL injection In Email Address Field.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=15)
	public void TC368_verifySQLInjectionAttemptThroughMobileNumberFieldTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber("' OR '1'='1");
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashiernewchangedpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     alt.accept();
		     Assert.fail("TC 368 Failed,Password Resetted With SQL injection In Mobile Number Field");
		     
		}
		else
		{
			String validationmessage = cfpp.getValidationMessageofMobileNumberField();
			log.info("The Validation Message is {}",validationmessage);
			soft.assertEquals(validationmessage,"Please match the format requested.","TC 368 Failed,Password Resetted With SQL injection In Mobile Number Field.No Validation Message Found");
		}
		soft.assertAll();
		
	}
	
	@Test(priority=16)
	public void TC374_verifyNavigationandLoadingofForgotPasswordPageTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		log.info("Clicking on Forgot Password Link");
		cfpp.clickonForgotPasswordLink();
		log.info("Waiting for elements to be visible on forgot password page");
		cfpp.waitForLoadingofForgotPasswordPage();
		String forgotpasswordpagetitle1 = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle1, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		soft.assertAll();
		
	}
	
	@Test(priority=17)
	public void TC375_verifyNavigationandLoadingofBacktoHomePageLinkTest()
	{
		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		log.info("Clicking on Back to Homepage Link");
		cfpp.clickonBackToHomePageLink();
		wait.waitForElementToDisappear(By.id("overlayer"));
		String titleofHomePage = hp.getTitleofHomepage();
		log.info("Checking title of Homepage");
		soft.assertEquals(titleofHomePage,"e-Banking System","HomePage Title does not match");
		log.info("Checking New User Link is Clickable");
		soft.assertTrue(hp.checkNewuserLinkisClickable(), "New User Link is Not Clickable");
		log.info("Checking Cashier Link is Clickable");
		soft.assertTrue(hp.checkCashierLinkisClickable(), "Cashier Link is Not Clickable");
		log.info("Checking Admin Link is Clickable");
		soft.assertTrue(hp.checkAdminLinkisClickable(), "Admin Link is Not Clickable");
		soft.assertAll();
		
	}
	
	
	@Test
	public void resetToOriginalPasswordTest()
	{

		log.info("Clicking on Forgot Password Link");
		clp.clickOnForgotPasswordLink();
		log.info("Navigating to Forgot Password Page");
		cfpp=new CashierForgotPasswordPage();
		String forgotpasswordpagetitle = cfpp.getTitleofForgotPasswordPage();
		soft.assertEquals(forgotpasswordpagetitle, "Forgot Password!","Forgot Password tile not Present");
		log.info("Checking Input Fields are Enabled");
		cfpp.checkEmailAddressInputFieldIsEnabled();
		cfpp.checkMobileNumberInputFieldIsEnabled();
		cfpp.checkNewPasswordInputFieldIsEnabled();
		cfpp.checkConfirmPasswordInputFieldIsEnabled();
		log.info("Forgot Password Page is Successfully Loaded");
		cfpp.enterEmailAddress(ConfigReader.getProperty("cashieremailaddress"));
		log.info("Email Address Entered");
		cfpp.enterMobileNumber(ConfigReader.getProperty("cashiermobilenumber"));
		log.info("Mobile Number Entered");
		cfpp.enterNewPassword(ConfigReader.getProperty("cashieroriginalpassword"));
		log.info("New Password Entered");
		cfpp.enterConfirmPassword(ConfigReader.getProperty("cashieroriginalpassword"));
		log.info("Confirm Password Entered");
		cfpp.clickonResetButton();
		log.info("Clicked on Reset Button");
		log.info("Waiting For Presence of Alert");
		if(cfpp.checkAlertisPresent()==true)
		{
			log.info("Switching to Alert");
		     Alert alt = DriverManager.getDriver().switchTo().alert();
		     alt.accept();
		}
	}
	
	
	
	@AfterMethod
	public void TearDown(Method method)
	{
		log.info("Browser Closed");
		DriverManager.getDriver().quit();
		DriverManager.unload();
		log.info("========= ENDING TEST: {} =========", method.getName());
	}

}
