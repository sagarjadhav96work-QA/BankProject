package com.automation.bankms.qa.webtestcases.user;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.user.Forgotpasswordpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.Registrationpage;

public class LoginPageTest extends TestBase{
	
	public Homepage hp;
	public Loginpage lp;
	public Forgotpasswordpage fp;
	public Registrationpage rp;

	
	
	@BeforeMethod
	public void Setup(ITestContext context) 
	{
		Initialization();
		context.setAttribute("driver", DriverManager.getDriver());
	
	}
	
	@Test(priority=0)
	public void TC031_loginwithvalidcredentialstest()
	{
		hp=new Homepage();
		hp.clickonnewuserlink();
		lp=new Loginpage();
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();

		
	}
	
	
	@Test(priority=1,dataProvider = "UserLoginData",dataProviderClass = com.automation.bankms.qa.dataproviders.Logindataprovider.class)
	public void TC031_dataDriverloginwithvalidcredentialstest(String Email,String Password)
	{
		hp=new Homepage();
		hp.clickonnewuserlink();
		lp=new Loginpage();
		lp.dataDrivenLoginofUser(Email, Password);
		lp.clickonloginbutton();

		
	}
	
	@Test(priority=2)
	public void TC036_loginwithinvalidcredentialstest()
	{
		hp=new Homepage();
		hp.clickonnewuserlink();
		lp=new Loginpage();
		lp.enterinvalidemailaddress();
		lp.enterinvalidpassword();
		lp.clickonloginbutton();
		Alert alt=DriverManager.getDriver().switchTo().alert();
		String responsetext = alt.getText();
		Assert.assertEquals(responsetext, "Invalid Details", "TC036 failed,user logged in with invalid credentials");
		alt.accept();
		
	}
	
	@Test(priority=3)
	public void TC037_loginwithnocredentialstest()
	{
		hp=new Homepage();
		hp.clickonnewuserlink();
		lp=new Loginpage();
		lp.clickonloginbutton();
		String pagetitle = lp.checkloginpagetitle();
		Assert.assertEquals(pagetitle,"e-Banking System | User Login","TC037 Failed,User is logged in with no credentials");
	}
	
	@Test(priority=4)
	public void TC039_verifySQLinjectionduringlogintest()
	{
		hp=new Homepage();
		hp.clickonnewuserlink();
		lp=new Loginpage();
		lp.entersqlvalidationemail();
		lp.enterinvalidpassword();
		String pagetitle = lp.checkloginpagetitle();
		Assert.assertEquals(pagetitle,"e-Banking System | User Login","TC039 Failed,User is logged in with SQL injection credentials");

	}
	
	@Test(priority=5)
	public void TC038_verifyaccountlockafterthreefailedattemptstest()
	{
		hp=new Homepage();
		lp=new Loginpage();
		hp.clickonnewuserlink();
		for(int a=0;a<=2;a++)
		{
			
			lp.entervalidemailid();
			lp.enterinvalidpassword();
			lp.clickonloginbutton();
			Alert alt=DriverManager.getDriver().switchTo().alert();
			String alertmessage = alt.getText();
			Assert.assertEquals(alertmessage,"Invalid Details");
			alt.accept();
		}
		
		
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		
	}
	
	@Test(priority=6)
	public void TC045_verifyworkingofforgotpasswordlinktest()
	{
		hp=new Homepage();
		hp.clickonnewuserlink();
		lp=new Loginpage();
		lp.clickonforgotpasswordlink();
		fp=new Forgotpasswordpage();
		String forgotpasswordpagetitle = fp.checkForgotPasswordPageTitle();
		Assert.assertEquals(forgotpasswordpagetitle, "Forgot Password!","TC045 Failed, unable to land on forgot password page");
		
		
	}
	
	@Test(priority=7)
	public void TC046_verifyworkingofcreateanaccountlinktest()
	{
		hp=new Homepage();
		hp.clickonnewuserlink();
		lp=new Loginpage();
		lp.clickoncreateanaccountlink();
		rp=new Registrationpage();
		String registrationpagetext = rp.checkworkingofregistrationpage();
		Assert.assertEquals(registrationpagetext,"e-Banking | User Create an Account!","TC046 Failed, unable to land on Registration page");
		
		
	}
	
	
	@AfterMethod
	public void Teardown()
	{
		DriverManager.getDriver().quit();
		DriverManager.unload();
	}
	
	
	
	
	
	
}
