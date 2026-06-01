package com.automation.bankms.qa.webtestcases.user;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.bankms.qa.base.TestBase;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.user.Dashboardpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.Managepayeeorbeneficiarypage;
import com.automation.bankms.qa.utils.SortUtils;
import com.automation.bankms.qa.utils.WaitUtils;

public class ManagePayeeorBeneficiaryTest extends TestBase {
	
	public Homepage hp;
	public Loginpage lp;
	public Dashboardpage dp;
	public Managepayeeorbeneficiarypage mp;
	public WaitUtils wait;
	public SortUtils sort;
	
	
	
	@BeforeMethod
	public void setup(ITestContext context)
	{
		Initialization();
		context.setAttribute("driver", DriverManager.getDriver());
		hp=new Homepage();
		lp=new Loginpage();
		dp=new Dashboardpage();
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		sort=new SortUtils();
		hp.clickonnewuserlink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Payee / Beneficiary']"));
		
	}
	
	@Test(priority=1)
	public void TC123_Verifyworkingofsearchfieldtest()
	{
		
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		boolean verificationstatus = mp.checkworkingofsearchpage("870644954");
		Assert.assertEquals(verificationstatus,true,"TC123_Failed,No such payee detail present");
		
	}
	
	@Test(priority=2)
	public void TC124_Verifyworkingofmanagepayeepagetest()
	{
		
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvibilityofmanagepayeepagetitle();
		mp.checkvibilityofmanagepayeepagetitle();
		mp.checkvisibilityofsearchinputfield();
		mp.checkvisibilityofdeletebutton();
		mp.checkvisibilityoftransferbutton();
		mp.checkvisibilityoftableheader();
		
	
	}
	
	@Test(priority=3)
	public void TC125_checksortingofnameofpayeeinascendingordertest()
	{
		
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvisibilityoftableheader();
		mp.clickonnameofpayeeheader();
		ArrayList<String> listofpayee = mp.getpayeenames();
		
		
		
		boolean sortingstatus = sort.islistsortedinascendingorder(listofpayee);
		Assert.assertTrue(sortingstatus, "TC125_Failed,sorting of email address is not in ascending order");
		
		
	}
	
	@Test(priority=4)
	public void TC126_checksortingofnameofpayeeindescendingordertest() throws InterruptedException
	{
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvisibilityoftableheader();
		mp.clickonnameofpayeeheader();
		mp.clickonnameofpayeeheader();
		Thread.sleep(1000);
		ArrayList<String> listofpayee = mp.getpayeenames();
		
		
		
		boolean sortingstatus = sort.islistsortedindescendingorder(listofpayee);
		Assert.assertTrue(sortingstatus, "TC126_Failed,sorting of email address is not in descending order");
		
		
	}
	
	@Test(priority=5)
	public void TC127_checksortingofemailofpayeeinascendingordertest()
	{
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvisibilityoftableheader();
		mp.clickonnameofpayeeheader();
		ArrayList<String> listofpayeeemails = mp.getpayeeemail();
		
		
		
		boolean sortingstatus = sort.islistsortedinascendingorder(listofpayeeemails);
		Assert.assertTrue(sortingstatus, "TC127_Failed,sorting of email address is not in ascending order");
		
		
	}
	
	@Test(priority=6)
	public void TC128_checksortingofemailofpayeeindescendingordertest() throws InterruptedException
	{
		
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvisibilityoftableheader();
		mp.clickonnameofpayeeheader();
		mp.clickonnameofpayeeheader();
		Thread.sleep(1000);
		ArrayList<String> listofpayeeemails = mp.getpayeeemail();
		
		
		
		boolean sortingstatus = sort.islistsortedindescendingorder(listofpayeeemails);
		Assert.assertTrue(sortingstatus, "TC128_Failed,sorting of email address is not in descending order");
		
		
	}
	
	@Test(priority=7)
	public void TC129_checksortingofmobilenumberofpayeeinascendingordertest()
	{
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvisibilityoftableheader();
		mp.clickonpayeesmobilenumberheader();
		ArrayList<Long> listofmobilenumber = mp.getpayeemobilenumber();
		
		
		boolean sortingstatus = sort.ismobilenumbersortedinascendingorder(listofmobilenumber);
		Assert.assertTrue(sortingstatus, "TC129_Failed,sorting of mobile number is not in ascending order");
		
		
	}
	
	@Test(priority=8)
	public void TC130_checksortingofmobilenumberofpayeeindescendingordertest()
	{
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvisibilityoftableheader();
		mp.clickonpayeesmobilenumberheader();
		mp.clickonpayeesmobilenumberheader();
		ArrayList<Long> listofmobilenumber = mp.getpayeemobilenumber();
		
		
		boolean sortingstatus = sort.ismobilenumbersortedindescendingorder(listofmobilenumber);
		Assert.assertTrue(sortingstatus, "TC130_Failed,sorting of mobile number is not in descending order");
		
		
	}
	
	@Test(priority=9)
	public void TC131_checksortingofaccountnumberofpayeeinascendingordertest()
	{
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvisibilityoftableheader();
		mp.clickonpayeesaccountnumberheader();
		ArrayList<Long> listofaccountnumber = mp.getpayeeaccountnumber();
		
		
		boolean sortingstatus = sort.ismobilenumbersortedinascendingorder(listofaccountnumber);
		Assert.assertTrue(sortingstatus, "TC131_Failed,sorting of account number is not in ascending order");
		
		
	}
	
	@Test(priority=10)
	public void TC132_checksortingofaccountnumberofpayeeindescendingordertest()
	{
		dp.clickonmanagepayeeorbenefeciarylink();
		mp=new Managepayeeorbeneficiarypage();
		mp.checkvisibilityoftableheader();
		mp.clickonpayeesaccountnumberheader();
		mp.clickonpayeesaccountnumberheader();
		ArrayList<Long> listofaccountnumber = mp.getpayeeaccountnumber();
		
		
		boolean sortingstatus = sort.ismobilenumbersortedindescendingorder(listofaccountnumber);
		Assert.assertTrue(sortingstatus, "TC132_Failed,sorting of account number is not in descending order");
		
		
	}
	
	@AfterMethod
	public void teardown()
	{
		DriverManager.getDriver().quit();
		DriverManager.unload();
		
	}
	
	
	
	
	

}
