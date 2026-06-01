package com.automation.bankms.qa.webtestcases.user;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.user.Addpayeeorbeneficiarypage;
import com.automation.bankms.qa.pages.user.Dashboardpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.Managepayeeorbeneficiarypage;
import com.automation.bankms.qa.utils.WaitUtils;

public class AddPayeeorBeneficiaryTest extends TestBase{
	public Homepage hp;
	public Loginpage lp;
	public Dashboardpage dp;
	public Addpayeeorbeneficiarypage ap;
	public WaitUtils wait;
	public Managepayeeorbeneficiarypage mp;
	
	
	@BeforeMethod
	public void setup(ITestContext context)
	{
		Initialization();
		context.setAttribute("driver", DriverManager.getDriver());
		hp=new Homepage();
		lp=new Loginpage();
		
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		hp.clickonnewuserlink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Payee / Beneficiary']"));
		
	}
	
	@Test(priority=1)
	public void TC081_addnewpayeewithcorrectdetailstest()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.enteraccountnumber("870644954");
		ap.enterconfirmaccountnumber("870644954");
		ap.enteraccountholdername("Pooja Kulkarni");
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String verificationtext = alt.getText();
		Assert.assertEquals(verificationtext,"Payee / beneficiary Account detail has been added.","TC081 failed,payee not added successfully");
		alt.accept();
		mp=new Managepayeeorbeneficiarypage();
		Boolean status = mp.checkentrypresentinsidetable("Pooja Kulkarni");
		Assert.assertEquals(status, true,"TC 081 Failed,Payee not added Sucessfully in manage payee list");
		
		
		
		
	}
	
	@Test(priority=2)
	public void TC082_addnewpayeewithnodetailsininputfieldtest()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		String accountnumbervalidationmessage = ap.getaccountnumbervalidationmessage();
		
		Assert.assertEquals(accountnumbervalidationmessage,"Please fill in this field.", "TC082 failed,payee added with no details");
		
		
	}
	
	@Test(priority=3)
	public void TC083_verifypagerefreshbehaviourafteraddingalldetailsinaddpayeepagetest()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		
		ap=new Addpayeeorbeneficiarypage();
		ap.enteraccountnumber("870644954");
		ap.enterconfirmaccountnumber("870644954");
		ap.enteraccountholdername("Pooja Kulkarni");
		DriverManager.getDriver().navigate().refresh();
		String verificationtext = DriverManager.getDriver().findElement(By.id("accountnumber")).getAttribute("value");
		Assert.assertEquals(verificationtext.isEmpty(),true,"TC 083 failed,Field values are not vanished after page refresh");
		
		
		
		
	}
	
	@Test(priority=4)
	public void TC084_addnewpayeewithincorrectdetailstest()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.enteraccountnumber("Account number");
		ap.enterconfirmaccountnumber("confirm account number");
		ap.enteraccountholdername("account holder name");
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String verificationtext = alt.getText();
		Assert.assertEquals(verificationtext,"Both Account number does not match","TC084 failed,payee added successfully with incorrect details");
		alt.accept();
	
	}
	
	@Test(priority=5)
	public void TC085_unabletoaddnewpayeewithdifferentaccountnumber()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.enteraccountnumber("12345678901");
		ap.enterconfirmaccountnumber("90909090909");
		ap.enteraccountholdername("Akash Rathi");
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String verificationtext = alt.getText();
		Assert.assertEquals(verificationtext,"Both Account number does not match","TC085 failed,payee added successfully with different numbers in account number fields");
		alt.accept();
	
	}
	
	@Test(priority=6)
	public void TC086_unabletoaddnewpayeewithincorrectaccountnumber()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.enteraccountnumber("12345678901");
		ap.enterconfirmaccountnumber("12345678901");
		ap.enteraccountholdername("Akash  Rathi");
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String verificationtext = alt.getText();
		Assert.assertEquals(verificationtext,"Invalid Account Number. Please try again","TC086 failed,payee added successfully with incorrect account number");
		alt.accept();
	
	}
	
	@Test(priority=7)
	public void TC087_unabletoaddnewpayeewithalreadyaddedpayee()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.enteraccountnumber("870644954");
		ap.enterconfirmaccountnumber("870644954");
		ap.enteraccountholdername("Pooja Kulkarni");
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String verificationtext = alt.getText();
		Assert.assertEquals(verificationtext,"Account Number Already Added","TC087 failed,payee added successfully with already added account number");
		alt.accept();
	
	}
	
	@Test(priority=8)
	public void TC088_userisunabletoaddnewPayeewithAccountnumberFieldemptytest()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.enterconfirmaccountnumber("870644954");
		ap.enteraccountholdername("Pooja Kulkarni");
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		String verificationtext = DriverManager.getDriver().findElement(By.id("accountnumber")).getAttribute("validationMessage");
		Assert.assertEquals(verificationtext,"Please fill in this field.","TC 088 failed, payee is added with account number field empty");
		
		
		
		
	}
	
	
	@Test(priority=9)
	public void TC089_userisunabletoaddnewPayeewithconfirmAccountnumberFieldemptytest()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.enteraccountnumber("870644954");
		ap.enteraccountholdername("Pooja Kulkarni");
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		String verificationtext = DriverManager.getDriver().findElement(By.id("conaccountnumber")).getAttribute("validationMessage");
		Assert.assertEquals(verificationtext,"Please fill in this field.","TC 089 failed, payee is added with confirm account number field empty");
		
		
		
		
	}
	
	@Test(priority=10)
	public void TC090_userisunabletoaddnewPayeewithAccountholdernameFieldemptytest()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.enteraccountnumber("870644954");
		ap.enterconfirmaccountnumber("870644954");
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		String verificationtext = DriverManager.getDriver().findElement(By.id("acountholdername")).getAttribute("validationMessage");
		Assert.assertEquals(verificationtext,"Please fill in this field.","TC 090 failed, payee is added with account holder name field empty");
		
		
		
		
	}
	
	@Test(priority=11)
	public void TC091_unabletoaddnewpayeewithaccountopeningrequestunapprovedtest()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//h6[text()='Payee /  Beneficiary']"));
		ap=new Addpayeeorbeneficiarypage();
		String accountverificationtext = ap.getunapprovedaccountrequesttext();
		Assert.assertEquals(accountverificationtext,"Your account not approve yet, After approval you can add payee/beneficiary.","TC091 failed,Add payee fields displayed even when account request is not approved.");
		
		
		
	
	}
	

	
	@Test(priority=12)
	public void TC106_verifyaddpayeeSQLinjectiontest()
	{
		dp=new Dashboardpage();
		dp.clickonaddpayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Account Number']"));
		ap=new Addpayeeorbeneficiarypage();
		ap.enteraccountnumber("'1'='1");
		ap.enterconfirmaccountnumber("'1'='1");
		ap.enteraccountholdername("Shamsher Shaikh");
		ap.clickonaddpayeeorbeneficiarysubmitbutton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String verificationtext = alt.getText();
		Assert.assertEquals(verificationtext,"Invalid Account Number. Please try again","TC106 failed,payee added successfully with incorrect SQL injection details");
		alt.accept();
	
	}
	
	
	@AfterMethod
	public void teardown()
	{
		DriverManager.getDriver().quit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
