package com.automation.bankms.qa.webtestcases.user;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.Registrationpage;

public class RegistrationPageTest extends TestBase{
	
	public Homepage hp;
	public Loginpage lp;
	public Registrationpage rp;
	
	
	
	@BeforeMethod
	public void Setup(ITestContext context)
	{
		
		Initialization();
		context.setAttribute("driver", driver);
		

	}
	
	@Test(priority=1)
	public void TC001_userregistrationwithvaliddetails(){
		hp=new Homepage(driver);
		hp.clickonnewuserlink();
		lp=new Loginpage(driver);
		lp.clickoncreateanaccountlink();
		rp=new Registrationpage(driver);
		rp.createanewaccount("Calesh", "gonsalver", "caleshg.m@gmail.com", "9219092911","calesh@12");
		Alert alt=driver.switchTo().alert();
		String successtext = alt.getText();
		Assert.assertEquals(successtext,"You have successfully registered with us","TC_001 Failed,Registration Failed");
		alt.accept();
	
		
		
	}
	
	@Test(priority=5,dataProvider = "UserRegistrationData",dataProviderClass = com.automation.bankms.qa.dataproviders.Registrationdataprovider.class)
	public void TC001_Datadrivenuserregistrationwithvaliddetails(String FirstName,String LastName,String EmailAddress,String Mobilenumber,String Password)
	{
		hp=new Homepage(driver);
		hp.clickonnewuserlink();
		lp=new Loginpage(driver);
		lp.clickoncreateanaccountlink();
		rp=new Registrationpage(driver);
		rp.createanewaccount(FirstName, LastName, EmailAddress,Mobilenumber, Password);
		Alert alt=driver.switchTo().alert();
		String successtext = alt.getText();
		Assert.assertEquals(successtext,"You have successfully registered with us","TC_001 Failed,Registration Failed");
		alt.accept();
	}
	
	
	
	@Test(priority=2)
	public void TC002_workingofregistrationpage()
	{
		hp=new Homepage(driver);
		hp.clickonnewuserlink();
		lp=new Loginpage(driver);
		lp.clickoncreateanaccountlink();
		rp=new Registrationpage(driver);
		String checkedregistrationtitle = rp.checkworkingofregistrationpage();
		Assert.assertEquals(checkedregistrationtitle, "e-Banking | User Create an Account!","TC_002 Failed,Title incorrect");
		
	}
	
	@Test(priority=3)
	public void TC010_userregistrationwithstrongpassword()
	{
		hp=new Homepage(driver);
		hp.clickonnewuserlink();
		lp=new Loginpage(driver);
		lp.clickoncreateanaccountlink();
		rp=new Registrationpage(driver);
		rp.createanewaccount("Alexander", "mourinho", "Alexm484@gmail.com", "9219092911","Alex!@323_*");
		Alert alt=driver.switchTo().alert();
		String successtext = alt.getText();
		Assert.assertEquals(successtext,"You have successfully registered with us","TC_010 Failed,Registration Failed");
		alt.accept();
		
	}
	
	@Test(priority=4)
	public void TC013_userregistrationwithlessthantendigitinmobilenumberfieldtest()
	{
		hp=new Homepage(driver);
		hp.clickonnewuserlink();
		lp=new Loginpage(driver);
		lp.clickoncreateanaccountlink();
		rp=new Registrationpage(driver);
		rp.createanewaccount("Sikander", "Ferguson", "SikanderF183@gmail.com", "92","Alex!@323_*");
		Alert alt=driver.switchTo().alert();
		String successtext = alt.getText();
		Assert.assertEquals(successtext,"Mobile number must be numeric and 10 digits ","TC_013 Failed,incorrect error message");
		alt.accept();
		
	}
	
	
	
	
	
	
	
	@AfterMethod
	public void Teardown()
	{
		driver.quit();
	}
	
	
	
	
	
	
	

}
