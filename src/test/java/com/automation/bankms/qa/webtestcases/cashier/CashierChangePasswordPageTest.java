package com.automation.bankms.qa.webtestcases.cashier;

import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.cashier.CashierChangePasswordPage;
import com.automation.bankms.qa.pages.cashier.CashierDashboardPage;
import com.automation.bankms.qa.pages.cashier.CashierLoginPage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierChangePasswordPageTest extends TestBase {
	
	public Homepage hp;
	public CashierLoginPage clp;
	public CashierDashboardPage cdp;
	public CashierChangePasswordPage ccpp;
	public WaitUtils wait;
	public SoftAssert soft;
	protected static final Logger log=LogManagerUtil.getLogger(CashierChangePasswordPageTest.class);
	
	@BeforeMethod
	public void Setup(Method method,ITestContext context)
	{
		log.info("========= STARTING TEST: {} =========", method.getName());
		Initialization();
		context.setAttribute("driver", DriverManager.getDriver());
		log.info("Initializing Wait Utils");
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		log.info("Initializing Soft Assert");
		soft=new SoftAssert();
		log.info("Initializing Homepage");
		hp=new Homepage();
		log.info("Checking working of Cashier Login Link");
		hp.checkCashierLinkisClickable();
		log.info("Clicking on Cashier Login Link");
		hp.clickoncashierloginlink();
		log.info("Navigating to Cashier Login Page");
		clp=new CashierLoginPage();
		log.info("Waiting for Visiblity of Cashier Login Page");
		clp.waitForVisibilityofCashierLoginPage();
		log.info("Navigated to Cashier Login Page");
		log.info("Entering Employee ID");
		clp.enterEmployeeId("MM57315");
		log.info("Entered Employee ID");
		log.info("Entering Password");
		clp.enterPassword("Vikram@852");
		log.info("Entered Password");
		log.info("Clicking on Login Button");
		clp.clickOnLoginbutton();
		log.info("Clicked on Login Button");
		log.info("Navigated to Cashier Dashboard Page");
		cdp=new CashierDashboardPage();
		log.info("Waiting for successfull loading of Dashboard Page ");
		cdp.waitForLaunchOfDashboardPage();
		log.info("Dashboard Page Sucessfully loaded");
		cdp.clickonChangePasswordLink();
		log.info("Navigating to Change Password Page");
		ccpp=new CashierChangePasswordPage();
		
		
	}
	
	@Test(priority=1)
	public void TC376_verifyPasswordChangeBehaviourWithValidDetailsTest()
	{
		
		ccpp.enteringCurrentPassword("Vikram@852");
		log.info("Entered Current Password");
		ccpp.enteringNewPassword("Vikram@852");
		log.info("Entered New Password");
		ccpp.enteringConfirmPassword("Vikram@852");
		log.info("Entered Confirm Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		log.info("Checking if alert is Present");
		if(ccpp.checkalertispresent()==true)
		{
			log.info("Switching to Alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			log.info("Retrieving Confirmation Text from Alert");
			String confirmationtext = alt.getText();
			soft.assertEquals(confirmationtext,"Your password successully changed","TC 376 Failed,Failed to Change Password with Valid Details");
			soft.assertAll();
		}
	}
	
	@Test(priority=2)
	public void TC377_verifyPasswordChangeBehaviourWithSameDetailsasOriginalTest()
	{
		
		ccpp.enteringCurrentPassword("Vikram@852");
		log.info("Entered Current Password");
		ccpp.enteringNewPassword("Vikram@852");
		log.info("Entered New Password");
		ccpp.enteringConfirmPassword("Vikram@852");
		log.info("Entered Confirm Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		log.info("Checking if alert is Present");
		if(ccpp.checkalertispresent()==true)
		{
			log.info("Switching to Alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			log.info("Retrieving Confirmation Text from Alert");
			String confirmationtext = alt.getText();
			soft.assertEquals(confirmationtext,"New Password is Same as Current Password","TC 377 Failed,Password changed with new password same as old password");
			soft.assertAll();
		}
	}
	
	@Test(priority=3)
	public void TC378_verifyPasswordChangeWithInvalidDetailsTest()
	{
		
		ccpp.enteringCurrentPassword("CurrentPassword");
		log.info("Entered Current Password");
		ccpp.enteringNewPassword("NewPassword");
		log.info("Entered New Password");
		ccpp.enteringConfirmPassword("ConfirmPassword");
		log.info("Entered Confirm Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		log.info("Checking if alert is Present");
		if(ccpp.checkalertispresent()==true)
		{
			log.info("Switching to Alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			log.info("Retrieving Confirmation Text from Alert");
			String confirmationtext = alt.getText();
			soft.assertEquals(confirmationtext,"New Password and Confirm Password field does not match","TC 378 Failed,Password changed with invalid Details");
			soft.assertAll();
		}
	}
	
	@Test(priority=4)
	public void TC379_verifyPasswordChangeWithCurrentPasswordFieldEmptyTest()
	{
		
		ccpp.enteringNewPassword("Vikram@852");
		log.info("Entered New Password");
		ccpp.enteringConfirmPassword("Vikram@852");
		log.info("Entered Confirm Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		String validationmessage=ccpp.getValidationMessageofCurrentPasswordInputField();
		soft.assertEquals(validationmessage,"Please fill in this field.","TC 379 Failed,Password changed with current password field empty");
		soft.assertAll();
	}
	
	@Test(priority=5)
	public void TC380_verifyPasswordChangeWithNewPasswordFieldEmptyTest()
	{
		
		ccpp.enteringCurrentPassword("Vikram@852");
		log.info("Entered Current Password");
		ccpp.enteringConfirmPassword("Vikram@852");
		log.info("Entered Confirm Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		String validationmessage=ccpp.getValidationMessageofNewPasswordInputField();
		soft.assertEquals(validationmessage,"Please fill in this field.","TC 380 Failed,Password changed with new password field empty");
		soft.assertAll();
	}
	
	@Test(priority=6)
	public void TC381_verifyPasswordChangeWithConfirmPasswordFieldEmptyTest()
	{
		
		ccpp.enteringCurrentPassword("Vikram@852");
		log.info("Entered Current Password");
		ccpp.enteringNewPassword("Vikram@852");
		log.info("Entered New Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		String validationmessage=ccpp.getValidationMessageofConfirmPasswordInputField();
		soft.assertEquals(validationmessage,"Please fill in this field.","TC 381 Failed,Password changed with confirm password field empty");
		soft.assertAll();
	}
	
	@Test(priority=7)
	public void TC382_verifyPasswordChangeBehaviourWithDifferentNewAndConfirmPasswordTest()
	{
		
		ccpp.enteringCurrentPassword("Vikram@852");
		log.info("Entered Current Password");
		ccpp.enteringNewPassword("Shreyas@852");
		log.info("Entered New Password");
		ccpp.enteringConfirmPassword("Suryakumar@852");
		log.info("Entered Confirm Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		log.info("Checking if alert is Present");
		if(ccpp.checkalertispresent()==true)
		{
			log.info("Switching to Alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			log.info("Retrieving Confirmation Text from Alert");
			String confirmationtext = alt.getText();
			soft.assertEquals(confirmationtext,"New Password and Confirm Password field does not match","TC 382 Failed,Password changed with differeny new and confirm password.");
			soft.assertAll();
		}
	}
	
	
	@Test(priority=8)
	public void TC383_verifyPasswordChangeWithWrongCurrentPasswordTest()
	{
		
		ccpp.enteringCurrentPassword("Sikram@852");
		log.info("Entered Current Password");
		ccpp.enteringNewPassword("Shreyas@852");
		log.info("Entered New Password");
		ccpp.enteringConfirmPassword("Shreyas@852");
		log.info("Entered Confirm Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		log.info("Checking if alert is Present");
		if(ccpp.checkalertispresent()==true)
		{
			log.info("Switching to Alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			log.info("Retrieving Confirmation Text from Alert");
			String confirmationtext = alt.getText();
			soft.assertEquals(confirmationtext,"Your current password is wrong","TC 383 Failed,Password changed with wrong current password.");
			soft.assertAll();
		}
	}
	
	@Test(priority=9)
	public void TC396_verifyPasswordChangeBehaviourWithSQLInjectionTest()
	{
		
		ccpp.enteringCurrentPassword("Vikram@852");
		log.info("Entered Current Password");
		ccpp.enteringNewPassword("'1'='1");
		log.info("Entered New Password");
		ccpp.enteringConfirmPassword("'1'='1");
		log.info("Entered Confirm Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		log.info("Checking if alert is Present");
		if(ccpp.checkalertispresent()==true)
		{
			log.info("Switching to Alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			log.info("Retrieving Confirmation Text from Alert");
			String confirmationtext = alt.getText();
			soft.assertNotEquals(confirmationtext,"Your password successully changed","TC 396 Failed,Password changed with SQL Injection Attempt.");
			soft.assertAll();
		}
	}
	
	@Test(priority=10)
	public void TC399_verifyPasswordChangeBehaviourWithLeadingOrTrailingSpacesTest()
	{
		
		ccpp.enteringCurrentPassword("Vikram@852");
		log.info("Entered Current Password");
		ccpp.enteringNewPassword("  Vikram@  852  ");
		log.info("Entered New Password");
		ccpp.enteringConfirmPassword("  Vikram@  852  ");
		log.info("Entered Confirm Password");
		ccpp.clickonChangePasswordButton();
		log.info("Clicked on Change Password Button");
		
		log.info("Checking if alert is Present");
		if(ccpp.checkalertispresent()==true)
		{
			log.info("Switching to Alert");
			Alert alt = DriverManager.getDriver().switchTo().alert();
			log.info("Retrieving Confirmation Text from Alert");
			String confirmationtext = alt.getText();
			soft.assertEquals(confirmationtext,"Your password successully changed","TC 399 Failed,Password change Failed with Leading or Trailing Space.");
			soft.assertAll();
		}
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
