package com.automation.bankms.qa.webtestcases.user;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.user.ChangePasswordPage;
import com.automation.bankms.qa.pages.user.Dashboardpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.utils.WaitUtils;

public class ChangePasswordPageTest extends TestBase {

	public Homepage hp;
	public Loginpage lp;
	public Dashboardpage dp;
	public ChangePasswordPage cpp;
	public WaitUtils wait;
	public SoftAssert soft;
	
	
	
	@BeforeMethod
	public void Setup(ITestContext context)
	{
		Initialization();
		context.setAttribute("driver",DriverManager.getDriver());
		hp=new Homepage();
		lp=new Loginpage();
		dp=new Dashboardpage();
		
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		soft=new SoftAssert();
		hp.clickonnewuserlink();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='Dashboard']"));
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
	    wait.waitforElementToBeClickable(By.xpath("//a[@href='change-password.php']"));
	    dp.clickonchangepasswordlink();
	    wait.waitforElementToBeVisible(By.xpath("//h3[text()='Change Password']"));
	    cpp=new ChangePasswordPage();
		
	
				
	}
	
	@Test(priority=1)
	public void TC203_verifyPasswordChangeBehaviourWithValidDetailsTest()
	{
		String CurrentPassword="Vikram@852";
		String NewPassword="Sagar@19";
		cpp.enterCurrentPassword(CurrentPassword);
		cpp.enterNewPassword(NewPassword);
		cpp.enterConfirmPassword(NewPassword);
		cpp.clickOnChangeButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String passwordchangeconfirmationtext = alt.getText();
		soft.assertEquals(passwordchangeconfirmationtext,"Your password successfully changed","TC 203 Failed,Password not changed successfully");
		soft.assertAll();
		alt.accept();
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		
	}
	
	@Test(priority=2)
	public void TC204_verifyPasswordChangeBehaviourWithSameDetailsasOriginalTest()
	{
		String CurrentPassword="Vikram@852";
		String NewPassword="Vikram@852";
		cpp.enterCurrentPassword(CurrentPassword);
		cpp.enterNewPassword(NewPassword);
		cpp.enterConfirmPassword(NewPassword);
		cpp.clickOnChangeButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String passwordchangeconfirmationtext = alt.getText();
		soft.assertEquals(passwordchangeconfirmationtext,"New Password is Same as Current Password","TC 204 Failed,No alert found,Password changed successfully");
		alt.accept();
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		soft.assertAll();
		
	}
	
	@Test(priority=3)
	public void TC205_verifyPasswordChangeBehaviourWithInvalidDetailsTest()
	{
		String CurrentPassword="CurrentPassword";
		String NewPassword="NewPassword";
		String ConfirmPassword="ConfirmPassword";
		
		cpp.enterCurrentPassword(CurrentPassword);
		cpp.enterNewPassword(NewPassword);
		cpp.enterConfirmPassword(ConfirmPassword);
		cpp.clickOnChangeButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String passwordchangealerttext = alt.getText();
		soft.assertEquals(passwordchangealerttext,"New Password and Confirm Password field does not match","TC 205 Failed,No alert found,Password changed successfully");
		alt.accept();
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		soft.assertAll();
		
	}
	
	@Test(priority=4)
	public void TC206_verifyPasswordChangeBehaviourWithCurrentPasswordFieldEmptyTest()
	{
		
		String NewPassword="Vikram@852";
		String ConfirmPassword="Sagar@19";
		
		
		cpp.enterNewPassword(NewPassword);
		cpp.enterConfirmPassword(ConfirmPassword);
		cpp.clickOnChangeButton();
		String Validationtextofcurrentpasswordfield = cpp.getValidationTextofCurrentPasswordInputField();
		soft.assertEquals(Validationtextofcurrentpasswordfield,"Please fill in this field.","TC 206 Failed,No Validation alert found on current password input field");
		
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		soft.assertAll();
		
	}
	
	@Test(priority=5)
	public void TC207_verifyPasswordChangeBehaviourWithNewPasswordFieldEmptyTest()
	{
		String CurrentPassword="Vikram@852";
		
		String ConfirmPassword="Sagar@19";
		
		cpp.enterCurrentPassword(CurrentPassword);
		
		cpp.enterConfirmPassword(ConfirmPassword);
		cpp.clickOnChangeButton();
		String Validationtextofnewpasswordfield = cpp.getValidationTextofNewPasswordInputField();
		soft.assertEquals(Validationtextofnewpasswordfield,"Please fill in this field.","TC 207 Failed,No Validation alert found on new password input field");
		
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		soft.assertAll();
		
	}
	
	@Test(priority=6)
	public void TC208_verifyPasswordChangeBehaviourWithConfirmPasswordFieldEmptyTest()
	{
		String CurrentPassword="Vikram@852";
		String NewPassword="Sagar@19";
		
		cpp.enterCurrentPassword(CurrentPassword);
		cpp.enterNewPassword(NewPassword);
		
		cpp.clickOnChangeButton();
		String Validationtextofconfirmpasswordfield = cpp.getValidationTextofConfirmPasswordInputField();
		soft.assertEquals(Validationtextofconfirmpasswordfield,"Please fill in this field.","TC 208 Failed,No Validation alert found on confirm password input field");
		
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		soft.assertAll();
		
	}
	
	@Test(priority=7)
	public void TC209_verifyPasswordChangeBehaviourWithDifferentNewandConfirmPasswordTest()
	{
		String CurrentPassword="Vikram@852";
		String NewPassword="Sagar@19";
		String ConfirmPassword="Sagar@1922";
		
		cpp.enterCurrentPassword(CurrentPassword);
		cpp.enterNewPassword(NewPassword);
		cpp.enterConfirmPassword(ConfirmPassword);
		cpp.clickOnChangeButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String passwordchangealerttext = alt.getText();
		soft.assertEquals(passwordchangealerttext,"New Password and Confirm Password field does not match","TC 209 Failed,No alert found,Password changed successfully");
		alt.accept();
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		soft.assertAll();
		
	}
	
	@Test(priority=8)
	public void TC210_verifyPasswordChangeBehaviourWithWrongCurrentPasswordTest()
	{
		
		String CurrentPassword="Vikram@8522";
		String NewPassword="Sagar@19";
		String ConfirmPassword="Sagar@19";
		
		cpp.enterCurrentPassword(CurrentPassword);
		cpp.enterNewPassword(NewPassword);
		cpp.enterConfirmPassword(ConfirmPassword);
		cpp.clickOnChangeButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String passwordchangealerttext = alt.getText();
		soft.assertEquals(passwordchangealerttext,"Your current password is wrong","TC 210 Failed,No alert found,Password changed successfully with Wrong Password");
		alt.accept();
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		soft.assertAll();
		
	}
	
	@Test(priority=9)
	public void TC212_verifyPasswordChangePageBehaviourAfterPageRefreshTest()
	{
		String CurrentPassword="Vikram@8522";
		String NewPassword="Sagar@19";
		String ConfirmPassword="Sagar@19";
		
		cpp.enterCurrentPassword(CurrentPassword);
		cpp.enterNewPassword(NewPassword);
		cpp.enterConfirmPassword(ConfirmPassword);
		cpp.clickOnRefreshButton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Change Password']"));
		String valueofcurrentpasswordfield = cpp.getValuepresentinCurrentPasswordInputField();
		
		soft.assertTrue(valueofcurrentpasswordfield.isEmpty(), "Current Password Input Field is not Empty after Refresh");
		String valueofnewpasswordfield = cpp.getValuepresentinNewPasswordInputField();
		soft.assertTrue(valueofnewpasswordfield.isEmpty(),"New Password Input Field is not Empty after Refresh");
		String valueofconfirmpasswordfield = cpp.getValuepresentinConfirmPasswordInputField();
		soft.assertTrue(valueofconfirmpasswordfield.isEmpty(), "Confirm Password Input Field is not Empty after Refresh");
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		soft.assertAll();
		
	}
	
	@Test(priority=10)
	public void TC223_verifyPasswordChangeBehaviourWithSQLInjectionTest()
	{
		String CurrentPassword="Vikram@852";
		String NewPassword="' OR '1'='1";
		String ConfirmPassword="' OR '1'='1";
		
		cpp.enterCurrentPassword(CurrentPassword);
		cpp.enterNewPassword(NewPassword);
		cpp.enterConfirmPassword(ConfirmPassword);
		cpp.clickOnChangeButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String passwordchangealerttext = alt.getText();
		soft.assertEquals(passwordchangealerttext,"Invalid Input","TC 223 Failed,No error alert found,Password changed successfully with SQL Input");
		alt.accept();
		
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		soft.assertAll();
		
	}
	
	@Test(priority=11)
	public void TC226_verifyPasswordChangeBehaviourWithLeadingorTrailingSpacesinChangePasswordTest()
	{
		String CurrentPassword="Vikram@852";
		String NewPassword=" Sa   gar  @19  ";
		String ConfirmPassword=" Sa   gar  @19  ";
		String TrimmedChangedPassword="Sagar@19";
		
		cpp.enterCurrentPassword(CurrentPassword);
		cpp.enterNewPassword(NewPassword);
		cpp.enterConfirmPassword(ConfirmPassword);
		cpp.clickOnChangeButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String passwordchangealerttext = alt.getText();
		soft.assertEquals(passwordchangealerttext,"Your password successfully changed","Password not changed successfully with leading or trailing spaces");
		alt.accept();
		
		
		wait.waitforElementToBeClickable(By.id("userDropdown"));
		dp.clickonuserinfolink();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[1]"));
		dp.clickonlogoutbutton();
		wait.waitforElementToBePresent(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp.entervalidemailid();
		lp.entervalidpasswordafterpasswordchange(TrimmedChangedPassword);
		lp.clickonloginbutton();
		
		Alert alt1 = DriverManager.getDriver().switchTo().alert();
		String passwordchangealerttext1 = alt1.getText();
		soft.assertNotEquals(passwordchangealerttext1,"Invalid Details","DEFECT: Application did not trim spaces. Login failed with trimmed password");
		alt1.accept();
		
		
		soft.assertAll();
		
	}
	
	@AfterMethod
	public void Teardown()
	{
		DriverManager.getDriver().quit();
		DriverManager.unload();
	}
	
	
	
	
	
	
}
