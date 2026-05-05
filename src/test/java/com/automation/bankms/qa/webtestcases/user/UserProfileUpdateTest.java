package com.automation.bankms.qa.webtestcases.user;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.pages.user.Dashboardpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.UserProfileUpdatePage;
import com.automation.bankms.qa.utils.CommonUtils;
import com.automation.bankms.qa.utils.WaitUtils;

public class UserProfileUpdateTest extends TestBase{
	
	public Homepage hp;
	public Loginpage lp;
	public Dashboardpage dp;
	public UserProfileUpdatePage up;
	public WaitUtils wait;
	public SoftAssert soft;
	public CommonUtils commonutils;
	

	
	@BeforeMethod
	public void Setup(ITestContext context)
	{
		Initialization();
		context.setAttribute("driver", driver);
		hp=new Homepage(driver);
		lp=new Loginpage(driver);
		dp=new Dashboardpage(driver);
		up=new UserProfileUpdatePage(driver);
		wait=new WaitUtils(driver, 20000);
		soft=new SoftAssert();
		commonutils=new CommonUtils();
		hp.clickonnewuserlink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Dashboard']"));
		dp.clickonuserinfolink();
		dp.clickonuserprofilelink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Profile']"));
		
	}
	
	@Test(priority=1)
	public void TC167_verifyUserSuccessfullyUpdateProfileDetailsTest()
	{
		String FirstName="Sikram";
		String LastName="Batra";
		String MobileNumber="8329181829";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		Alert Alt = driver.switchTo().alert();
		String successconfirmationText = Alt.getText();
		Alt.accept();
		Assert.assertEquals(successconfirmationText,"Profile has been updated","TC167 Failed,User Profile Updation Failed ");
		Assert.assertEquals(FirstName+" "+LastName,dp.getNameofUserProfile(),"Updated Profile Name Not Updated on Dashboard");
	}
	
	@Test(priority=2)
	public void TC168_verifyUserEmailAddressandRegistrationDateFieldsareUneditableandReadOnlyTest()
	{
		soft.assertTrue(up.checkEmailAddressFieldisReadOnly(),"TC168 Failed,Email Address Field in Editable");
		soft.assertTrue(up.checkRegistrationDateisReadOnly(),"TC168 Failed,Registration Field is Editable");
		soft.assertAll();
		
	}
	
	@Test(priority=3)
	public void TC169_verifyProfileUpdateFailsWithInvalidDetailsTest()
	{
		String FirstName="First Name";
		String LastName="Last Name";
		String MobileNumber="Mobile Num";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		String mobilenumbervalidationtext = up.getValidationMessageofMobileNumberField();
		Assert.assertEquals(mobilenumbervalidationtext,"Please match the format requested.","TC169 Failed,User Profile was updated with invalid details");
	}
	
	@Test(priority=4)
	public void TC170_verifyProfileUpdateFailsWithFirstNameFieldEmptyTest()
	{
		
		String LastName="Batra";
		String MobileNumber="8219281922";
		up.clearUserFirstName();
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		String firstnamevalidationtext = up.getValidationMessageofFirstNameField();
		Assert.assertEquals(firstnamevalidationtext,"Please fill in this field.","TC170 Failed,User Profile was updated with First Name Field Empty");
	}
	
	@Test(priority=5)
	public void TC171_verifyProfileUpdateFailsWithLastNameFieldEmptyTest()
	{
		
		String FirstName="Shriram";
		String MobileNumber="8219281922";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		String lastnamevalidationtext = up.getValidationMessageofLastNameField();
		Assert.assertEquals(lastnamevalidationtext,"Please fill in this field.","TC171 Failed,User Profile was updated with Last Name Field Empty");
	}
	
	@Test(priority=6)
	public void TC172_verifyProfileUpdateFailsWithMobileNumberFieldEmptyTest()
	{
		
		String FirstName="Shriram";
		String LastName="Nene";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.clickonUpdateButton();
		String Mobilevalidationtext = up.getValidationMessageofMobileNumberField();
		Assert.assertEquals(Mobilevalidationtext,"Please fill in this field.","TC172 Failed,User Profile was updated with Mobile Number Field Empty");
	}
	
	@Test(priority=7)
	public void TC175_verifyProfileUpdateFailsWithAlphabetsinMobileNumberFieldTest()
	{
		String FirstName="Shriram";
		String LastName="Nene";
		String MobileNumber="83291Shrim";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		String mobilenumbervalidationtext = up.getValidationMessageofMobileNumberField();
		Assert.assertEquals(mobilenumbervalidationtext,"Please match the format requested.","TC175 Failed,User Profile was updated with alphabets in Mobile Number field");
	}
	
	@Test(priority=8)
	public void TC176_verifyProfileUpdateFailsWithSpacesinMobileNumberFieldTest()
	{
		String FirstName="Shriram";
		String LastName="Nene";
		String MobileNumber="  81 92 1";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		String mobilenumbervalidationtext = up.getValidationMessageofMobileNumberField();
		Assert.assertEquals(mobilenumbervalidationtext,"Please match the format requested.","TC176 Failed,User Profile was updated with Spaces in Mobile Number field");
		
	}
	
	@Test(priority=9)
	public void TC177_verifyProfileUpdateFailsWithLessThanTenDigitsinMobileNumberFieldTest()
	{
		String FirstName="Shriram";
		String LastName="Nene";
		String MobileNumber="819";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		
		if(commonutils.checkIfAlertIsPresent(driver)==true)
		{
			Alert alt = driver.switchTo().alert();
			String alertvalidationtext = alt.getText();
			alt.accept();
			Assert.fail("Alert Found "+alertvalidationtext+" TC177 Failed,User Profile was updated with Less than ten digits in Mobile Number field");
			
		}
		
		else
		{
		
		String mobilenumbervalidationtext = up.getValidationMessageofMobileNumberField();
		Assert.assertEquals(mobilenumbervalidationtext,"Please match the format requested.","TC177 Failed,User Profile was updated with Less than ten digits in Mobile Number field");
		}
	}
	
	@Test(priority=10)
	public void TC178_verifyMobileNumberAutoTrimToTenDigitsAndProfileUpdatesSuccessfullyTest()
	{
		String FirstName="Shriram";
		String LastName="Nene";
		String MobileNumber="8198329827323";
		String ExpectedMobileNumber=MobileNumber.substring(0, 10);
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		Assert.assertEquals(up.getUpdatedMobileNumber().length(),10,"Mobile Number not Auto Trimmed to Ten digits");
		up.clickonUpdateButton();
		

		if(commonutils.checkIfAlertIsPresent(driver)==true)
		{
			Alert alt = driver.switchTo().alert();
			String alertvalidationtext = alt.getText();
			alt.accept();
			Assert.assertTrue(alertvalidationtext.contains("Profile has been updated"), "Profile Not Updated Successfully with more than 10 digits");
			
		}
		
		else
		{
		    Assert.fail("Alert Not Present,TC 178 Failed,Expected Alert not Present");
		
		}
		
		String ActualMobileNumber = up.getUpdatedMobileNumber();
		Assert.assertEquals(ActualMobileNumber, ExpectedMobileNumber,"Mobile Number not trimmed to 10 digits");
	}
	
	
	@Test(priority=11)
	public void TC179_verifySizeValidationofFirstNameFieldWithOnlyoneAlphabetTest()
	{
		String FirstName="S";
		String LastName="Batra";
		String MobileNumber="8329181829";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		Alert Alt = driver.switchTo().alert();
		String successconfirmationText = Alt.getText();
		Alt.accept();
		Assert.assertEquals(successconfirmationText,"Profile has been updated","TC179 Failed,User Profile Updation Failed with one alphabet in First Name Field");
		Assert.assertEquals(FirstName+" "+LastName,dp.getNameofUserProfile(),"Updated Profile Name Not Updated on Dashboard");
	}
	
	@Test(priority=12)
	public void TC180_verifySizeValidationofFirstNameFieldWithMoreThanHundredAlphabetsTest()
	{
		String FirstName="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String LastName="Batra";
		String MobileNumber="8329181829";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		Alert Alt = driver.switchTo().alert();
		String successconfirmationText = Alt.getText();
		Alt.accept();
		Assert.assertFalse(successconfirmationText.contains("Profile has been updated"),"TC180 Failed,User Profile Updated with More than hundred alphabets in First Name Field");
		
	}
	
	
	@Test(priority=13)
	public void TC181_verifySizeValidationofLastNameFieldWithOnlyoneAlphabetTest()
	{
		String FirstName="Shreeram";
		String LastName="B";
		String MobileNumber="8329181829";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		Alert Alt = driver.switchTo().alert();
		String successconfirmationText = Alt.getText();
		Alt.accept();
		Assert.assertEquals(successconfirmationText,"Profile has been updated","TC181 Failed,User Profile Updation Failed with one alphabet in Last Name Field");
		Assert.assertEquals(FirstName+" "+LastName,dp.getNameofUserProfile(),"Updated Profile Name Not Updated on Dashboard");
	}
	
	@Test(priority=14)
	public void TC182_verifySizeValidationofLastNameFieldWithMoreThanHundredAlphabetsTest()
	{
		String FirstName="Shreeram";
		String LastName="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String MobileNumber="8329181829";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		Alert Alt = driver.switchTo().alert();
		String successconfirmationText = Alt.getText();
		Alt.accept();
		Assert.assertFalse(successconfirmationText.contains("Profile has been updated"),"TC182 Failed,User Profile Updated with More than hundred alphabets in Last Name Field");
		
	}
	
	@Test(priority=15)
	public void TC187_verifySQLInjectionTestinFirstNameField()
	{
		String FirstName="' OR '1'='1";
		String LastName="Batra";
		String MobileNumber="8329181829";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		if(commonutils.checkIfAlertIsPresent(driver)==true)
		{
		Alert Alt = driver.switchTo().alert();
		String alertconfirmationtext = Alt.getText();
		Alt.accept();
		if(alertconfirmationtext.contains("Profile has been updated")){
	    Assert.fail("TC187 Failed,User Profile is Updated with SQL Injection Value in First Name Field");
		}
		}
		
		else
		{
			String validationMessage = up.getValidationMessageofFirstNameField();
			Assert.assertTrue(validationMessage!=null && !validationMessage.isEmpty(),"TC187 Failed,User Profile is Updated with SQL Injection Value in First Name Field");
		}
		
		}
	
	
	@Test(priority=16)
	public void TC188_verifySQLInjectionTestinLastNameField()
	{
		String FirstName="Vikram";
		String LastName="' OR '1'='1";
		String MobileNumber="8329181829";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		if(commonutils.checkIfAlertIsPresent(driver)==true)
		{
		Alert Alt = driver.switchTo().alert();
		String alertconfirmationtext = Alt.getText();
		Alt.accept();
		if(alertconfirmationtext.contains("Profile has been updated")){
	    Assert.fail("TC188 Failed,User Profile is Updated with SQL Injection Value in Last Name Field");
		}
		}
		
		else
		{
			String validationMessage = up.getValidationMessageofLastNameField();
			Assert.assertTrue(validationMessage!=null && !validationMessage.isEmpty(),"TC188 Failed,User Profile is Updated with SQL Injection Value in Last Name Field");
		}
		
		}
	
	@Test(priority=17)
	public void TC189_verifySQLInjectionTestinMobileNumberField()
	{
		String FirstName="Vikram";
		String LastName="Batra";
		String MobileNumber="' OR '1'='1";
		up.clearUserFirstName();
		up.updateUserFirstName(FirstName);
		up.clearUserLastName();
		up.updateUserLastName(LastName);
		up.clearUserMobileNumber();
		up.enterMobileNumber(MobileNumber);
		up.clickonUpdateButton();
		if(commonutils.checkIfAlertIsPresent(driver)==true)
		{
		Alert Alt = driver.switchTo().alert();
		String alertconfirmationtext = Alt.getText();
		Alt.accept();
		if(alertconfirmationtext.contains("Profile has been updated")){
	    Assert.fail("TC188 Failed,User Profile is Updated with SQL Injection Value in Mobile Number Field");
		}
		}
		
		else
		{
			String validationMessage = up.getValidationMessageofMobileNumberField();
			Assert.assertEquals(validationMessage, "Please match the format requested.","TC188 Failed,User Profile is Updated with SQL Injection Value in Mobile Number Field");
		}
		
		}
	
	@AfterMethod
	public void Teardown()
	{
		driver.quit();
	}
	
	
	
	

}
