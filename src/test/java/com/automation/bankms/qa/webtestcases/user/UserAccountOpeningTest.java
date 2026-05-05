package com.automation.bankms.qa.webtestcases.user;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.config.ConfigReader;
import com.automation.bankms.qa.pages.user.Dashboardpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.Useraccountopeningpage;
import com.automation.bankms.qa.utils.WaitUtils;

public class UserAccountOpeningTest extends TestBase {

	public Homepage hp;
	public Loginpage lp;
	public Dashboardpage dp;
	public Useraccountopeningpage uaop;
	public WaitUtils ut;
	
	
	
	@BeforeMethod
	public void setup(ITestContext context)
	{
		Initialization();
		context.setAttribute("driver", driver);
		hp=new Homepage(driver);
		ut=new WaitUtils(driver, 20000);
		hp.clickonnewuserlink();
		ut.waitforElementToBeVisible(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp=new Loginpage(driver);
		
	}
	
	@Test
	public void TC050_verifyuserisabletocreatenewuseraccount()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		ut.waitforElementToBeVisible(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage(driver);
		dp.clickonaccountopeninglink();
		ut.waitforElementToBeVisible(By.xpath("//h6[text()='Account Opening Details']"));
		uaop=new Useraccountopeningpage(driver);
		uaop.selectaadharcardfromdropdown();
		uaop.enteraddressproofnumber("181952729042");
		uaop.uploadaddressproof(ConfigReader.getProperty("aadharpath"));
		uaop.uploadpancard(ConfigReader.getProperty("pancardpath"));
		uaop.enterpancardnumber("PVWQJ2903C");
		uaop.enteraddress("Mumbai");
		uaop.selectdateofbirth("21-02-1990");
		uaop.clickontermsandconditioncheckbox();
		uaop.clickonaccountopeningsubmitbutton();
		Alert alt = driver.switchTo().alert();
		String accountsubmissionverificationtext = alt.getText();
		Assert.assertEquals(accountsubmissionverificationtext,"Details succesfully submitted.","TC050 failed,user is unable to send a new account opening request");
		alt.accept();
		
	}
	
	@Test
	public void TC051_verifyuserisunabletocreatenewuseraccountwithallfieldsempty()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		ut.waitforElementToBeVisible(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage(driver);
		dp.clickonaccountopeninglink();
		ut.waitforElementToBeVisible(By.xpath("//h6[text()='Account Opening Details']"));
		uaop=new Useraccountopeningpage(driver);
		uaop.clickonaccountopeningsubmitbutton();
		
		String validationtext = uaop.getAddressProofValidationMessage();
		Assert.assertEquals(validationtext,"Please fill in this field.","TC051 failed,validation text not matched");
		
	}
	
	@Test
	public void TC052_verifyuserisunabletosubmitopeningrequestwithunsupportedformatinaddressprooffield()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		ut.waitforElementToBeVisible(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage(driver);
		dp.clickonaccountopeninglink();
		ut.waitforElementToBeVisible(By.xpath("//h6[text()='Account Opening Details']"));
		uaop=new Useraccountopeningpage(driver);
		uaop.selectaadharcardfromdropdown();
		uaop.enteraddressproofnumber("181952729042");
		uaop.uploadaddressproof(ConfigReader.getProperty("unsupportedaadharpath"));
		uaop.uploadpancard(ConfigReader.getProperty("pancardpath"));
		uaop.enterpancardnumber("PVWQJ2903KF");
		uaop.enteraddress("Mumbai");
		uaop.selectdateofbirth("21-02-1990");
		uaop.clickontermsandconditioncheckbox();
		uaop.clickonaccountopeningsubmitbutton();

		Alert alt = driver.switchTo().alert();
		String verificationtext = alt.getText();
		Assert.assertEquals(verificationtext,"Address Proof Image has Invalid format. Only jpg / jpeg/ png /gif / pdf format allowed","TC 052 Failed,user account opening request is sent");
		alt.accept();
		alt.accept();	
		Assert.fail("TC 052 failed as even after validation of unsupported file the account request form is submitted");
		
	}
	
	@Test
	public void TC052_verifyuserisunabletosubmitopeningrequestwithunsupportedformatinpancardproofield() 
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		ut.waitforElementToBeVisible(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage(driver);
		dp.clickonaccountopeninglink();
		ut.waitforElementToBeVisible(By.xpath("//h6[text()='Account Opening Details']"));
		uaop=new Useraccountopeningpage(driver);
		uaop.selectaadharcardfromdropdown();
		uaop.enteraddressproofnumber("181952729042");
		uaop.uploadaddressproof(ConfigReader.getProperty("aadharpath"));
		uaop.uploadpancard(ConfigReader.getProperty("unsupportedpancardpath"));
		uaop.enterpancardnumber("PVWQJ2903KE");
		uaop.enteraddress("Mumbai");
		uaop.selectdateofbirth("21-02-1990");
		uaop.clickontermsandconditioncheckbox();
		uaop.clickonaccountopeningsubmitbutton();
		
		Alert alt = driver.switchTo().alert();
		String verificationtext = alt.getText();
		Assert.assertEquals(verificationtext,"Pan Card Image has Invalid format. Only jpg / jpeg/ png /gif / pdf format allowed","TC 053 Failed,user account opening request is sent");
		alt.accept();
		
		
	}
	
	@Test
	public void TC075_verifypagerefreshafterfillingalldetails()
	{
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		ut.waitforElementToBeVisible(By.xpath("//h1[text()='Dashboard']"));
		dp=new Dashboardpage(driver);
		dp.clickonaccountopeninglink();
		ut.waitforElementToBeVisible(By.xpath("//h6[text()='Account Opening Details']"));
		uaop=new Useraccountopeningpage(driver);
		uaop.selectaadharcardfromdropdown();
		uaop.enteraddressproofnumber("181952729042");
		uaop.uploadaddressproof(ConfigReader.getProperty("aadharpath"));
		uaop.uploadpancard(ConfigReader.getProperty("pancardpath"));
		uaop.enterpancardnumber("PVWQJ2903C");
		uaop.enteraddress("Mumbai");
		uaop.selectdateofbirth("21-02-1990");
		uaop.clickontermsandconditioncheckbox();
		driver.navigate().refresh();
		uaop = new Useraccountopeningpage(driver); 
		ut.waitforElementToBeVisible(By.id("addpidnum"));
		String extractedtext = driver.findElement(By.id("addpidnum")).getAttribute("value");
		Assert.assertTrue(extractedtext.isEmpty(),"TC075 failed,field is not empty after refresh");
		
		
	}
	
	
	
	
	
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	
	
	
	
}
