package com.automation.bankms.qa.webtestcases.user;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.config.ConfigReader;
import com.automation.bankms.qa.pages.user.Forgotpasswordpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.utils.WaitUtils;

public class ForgotPasswordPageTest extends TestBase {
	
	public Homepage hp;
	public Loginpage lp;
	public Forgotpasswordpage fp;
	
	public WaitUtils wait;
	public SoftAssert soft;
	
	
	
	@BeforeMethod
	public void Setup(ITestContext context)
	{
		
		Initialization();
		context.setAttribute("driver", driver);
		hp=new Homepage(driver);
		lp=new Loginpage(driver);
		
		wait=new WaitUtils(driver, 20000);
		soft=new SoftAssert();
		hp.clickonnewuserlink();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp.clickonforgotpasswordlink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Forgot Password!']"));
		fp=new Forgotpasswordpage(driver);
		
	}
	
	@Test(priority=1)
	public void TC227_verifyUserIsAbleToResetPasswordSuccessfullyWithValidDetailsTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("123");
		fp.enterNewPassword("Vikram@852");
		fp.enterConfirmPassword("Vikram@852");
		fp.clickOnResetButton();
		Alert alt = driver.switchTo().alert();
		String resetpasswordalertmessage = alt.getText();
		soft.assertEquals(resetpasswordalertmessage, "Your Password succesfully changed","TC227 Failed,Reset Password Failed");
		alt.accept();
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=2)
	public void TC228_verifyUserIsUnableToResetPasswordWithEmailAddressFieldEmptyTest()
	{
		
		fp.enterMobileNumber("123");
		fp.enterNewPassword("Vikram@852");
		fp.enterConfirmPassword("Vikram@852");
		fp.clickOnResetButton();
		String validationmessageofemailaddressfield = fp.getValidationMessageofEmailAddressInputField();
		soft.assertEquals(validationmessageofemailaddressfield, "Please fill in this field.","TC228 Failed,Password resetted with Email Id Field Empty");
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=3)
	public void TC229_verifyUserIsUnableToResetPasswordWithMobileNumberFieldEmptyTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterNewPassword("Vikram@852");
		fp.enterConfirmPassword("Vikram@852");
		fp.clickOnResetButton();
		String validationmessageofmobilenumberfield = fp.getValidationMessageofMobileNumberInputField();
		soft.assertEquals(validationmessageofmobilenumberfield, "Please fill in this field.","TC229 Failed,Password resetted with Mobile Number Field Empty");
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=4)
	public void TC230_verifyUserIsUnableToResetPasswordWithInvalidMobileNumberFormatTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("abcdefghij");
		fp.enterNewPassword("Vikram@852");
		fp.enterConfirmPassword("Vikram@852");
		fp.clickOnResetButton();
		String validationmessageofmobilenumberfield = fp.getValidationMessageofMobileNumberInputField();
		soft.assertEquals(validationmessageofmobilenumberfield, "Please match the format requested.","TC230 Failed,Password resetted with Invalid Mobile Number Format");
		soft.assertAll();
	}
	
	@Test(priority=5)
	public void TC231_verifyUserIsUnableToResetPasswordWithInvalidEmailAddressTest()
	{
		fp.enterEmailAddress("wrong@email.com");
		fp.enterMobileNumber("123");
		fp.enterNewPassword("Vikram@852");
		fp.enterConfirmPassword("Vikram@852");
		fp.clickOnResetButton();
		Alert alt = driver.switchTo().alert();
		String resetpasswordalertmessage = alt.getText();
		soft.assertEquals(resetpasswordalertmessage, "Email id or Mobile no is invalid","TC231 Failed,Password resetted with Incorrect Email Address");
		alt.accept();
		soft.assertAll();
		
	}
	
	@Test(priority=6)
	public void TC232_verifyUserIsUnableToResetPasswordWithInvalidMobileNumberTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("1234556");
		fp.enterNewPassword("Vikram@852");
		fp.enterConfirmPassword("Vikram@852");
		fp.clickOnResetButton();
		Alert alt = driver.switchTo().alert();
		String resetpasswordalertmessage = alt.getText();
		soft.assertEquals(resetpasswordalertmessage, "Email id or Mobile no is invalid","TC232 Failed,Password resetted with Incorrect Mobile Number");
		alt.accept();
		soft.assertAll();
		
	}
	
	@Test(priority=7)
	public void TC233_verifyUserIsUnableToResetPasswordWithMismatchinPasswordFieldsTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("123");
		fp.enterNewPassword("Vikram@852");
		fp.enterConfirmPassword("Vikram@85222");
		fp.clickOnResetButton();
		Alert alt = driver.switchTo().alert();
		String resetpasswordalertmessage = alt.getText();
		soft.assertEquals(resetpasswordalertmessage, "New Password and Confirm Password Field do not match  !!","TC233 Failed,Password resetted with Mismatch in New and Confirm Password");
		alt.accept();
		soft.assertAll();
		
	}
	
	@Test(priority=8)
	public void TC234_verifyUserIsUnableToResetPasswordWithNewPasswordFieldEmptyTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("123");
		
		fp.enterConfirmPassword("Vikram@852");
		fp.clickOnResetButton();
		String validationmessageofnewpasswordfield = fp.getValidationMessageofNewPasswordInputField();
		soft.assertEquals(validationmessageofnewpasswordfield, "Please fill in this field.","TC234 Failed,Password resetted with New Password Field Empty");
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=9)
	public void TC235_verifyUserIsUnableToResetPasswordWithConfirmPasswordFieldEmptyTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("123");
		fp.enterNewPassword("Vikram@852");
		
		fp.clickOnResetButton();
		String validationmessageofconfirmpasswordfield = fp.getValidationMessageofConfirmPasswordInputField();
		soft.assertEquals(validationmessageofconfirmpasswordfield, "Please fill in this field.","TC235 Failed,Password resetted with Confirm Password Field Empty");
		soft.assertAll();
		
		
		
	}
	
	
	@Test(priority=10)
	public void TC236_verifyForgotPasswordPageBehaviourWithLeadingorTrailingSpacesinNewPassowrd()
	{
		String TrimmedChangedPassword="Sagar@19";
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("123");
		fp.enterNewPassword("  Sa g ar@  19  ");
		fp.enterConfirmPassword("  Sa g ar@  19  ");
		fp.clickOnResetButton();
		Alert alt = driver.switchTo().alert();
		String resetpasswordalertmessage = alt.getText();
		soft.assertEquals(resetpasswordalertmessage, "Your Password succesfully changed","Password reset failed with leading or trailing spaces in new and confirm password field");
		alt.accept();
		
		wait.waitforElementToBeClickable(By.xpath("//a[text()='Back to Home Page']"));
		fp.clickOnBackToHomePageLink();
		wait.waitForElementToDisappear(By.id("overlayer"));
		wait.waitforElementToBeClickable(By.xpath("(//a[text()='User/Account Holder'])[2]"));
		
		
		hp.clickonnewuserlink();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp.entervalidemailid();
		lp.entervalidpasswordafterpasswordchange(TrimmedChangedPassword);
		lp.clickonloginbutton();
		
		Alert alt1 = driver.switchTo().alert();
		String passwordchangealerttext1 = alt1.getText();
		soft.assertNotEquals(passwordchangealerttext1,"Invalid Details","TC236 Failed,DEFECT: Application did not trim spaces. Login failed with trimmed password");
		alt1.accept();
		
		
		soft.assertAll();
		
	}
	
	@Test(priority=11)
	public void TC237_verifySystemHandlesLongInputValueinNewPasswordFieldTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("123");
		fp.enterNewPassword("abcdefghijklmnopqrstuvwxyz12345678900987654321ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz12345678900987654321ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		fp.enterConfirmPassword("abcdefghijklmnopqrstuvwxyz12345678900987654321ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz12345678900987654321ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		fp.clickOnResetButton();
		Alert alt = driver.switchTo().alert();
		String resetpasswordalertmessage = alt.getText();
		soft.assertEquals(resetpasswordalertmessage, "Your Password succesfully changed","TC237 Failed,Reset Password Failed,Long Input Not Handled Properly");
		alt.accept();
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=12)
	public void TC238_verifySystemAcceptsPasswordChangewithSpecialCharactersTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("123");
		fp.enterNewPassword("Vikram@852#");
		fp.enterConfirmPassword("Vikram@852#");
		fp.clickOnResetButton();
		Alert alt = driver.switchTo().alert();
		String resetpasswordalertmessage = alt.getText();
		soft.assertEquals(resetpasswordalertmessage, "Your Password succesfully changed","TC238 Failed,Reset Password Failed,Special Characters not accepted in new and confirm password fields");
		alt.accept();
		soft.assertAll();
		
		
		
	}
	
	@Test(priority=13)
	public void TC241_verifySystemPreventsSQLInjectionThroughEmailFieldTest()
	{
		fp.enterEmailAddress("' OR '1'='1");
		fp.enterMobileNumber("123");
		fp.enterNewPassword("Vikram@852#");
		fp.enterConfirmPassword("Vikram@852#");
		fp.clickOnResetButton();
		String validationmessageofemailaddressfield = fp.getValidationMessageofEmailAddressInputField();
		System.out.println(validationmessageofemailaddressfield);
		soft.assertEquals(validationmessageofemailaddressfield,"Please include an '@' in the email address. '' OR '1'='1' is missing an '@'.","TC241 Failed,Password resetted with SQL Injection in Email Address Field");
		soft.assertAll();
		
		
		
		
	}
	
	@Test(priority=14)
	public void TC242_verifySystemPreventsSQLInjectionThroughMobileNumberFieldTest()
	{
		fp.enterEmailAddress(ConfigReader.getProperty("emailaddress"));
		fp.enterMobileNumber("' OR '1'='1");
		fp.enterNewPassword("Vikram@852#");
		fp.enterConfirmPassword("Vikram@852#");
		fp.clickOnResetButton();
		String validationmessageofmobilenumberfield = fp.getValidationMessageofMobileNumberInputField();
		soft.assertEquals(validationmessageofmobilenumberfield, "Please match the format requested.","TC242 Failed,Password resetted with SQL Injection in Mobile Number Field");
		soft.assertAll();
		
		
		
		
	}
	
	@AfterMethod
	public void Teardown()
	{
		driver.quit();
	}
	
	
	

}
