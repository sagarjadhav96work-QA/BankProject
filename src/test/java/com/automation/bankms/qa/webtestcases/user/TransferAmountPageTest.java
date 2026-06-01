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
import com.automation.bankms.qa.pages.user.Dashboardpage;
import com.automation.bankms.qa.pages.user.Homepage;
import com.automation.bankms.qa.pages.user.Loginpage;
import com.automation.bankms.qa.pages.user.Managepayeeorbeneficiarypage;
import com.automation.bankms.qa.pages.user.TransferAmountpage;
import com.automation.bankms.qa.utils.WaitUtils;

public class TransferAmountPageTest extends TestBase{
	
	public Homepage hp;
	public Loginpage lp;
	public Dashboardpage dp;
	public Managepayeeorbeneficiarypage mp;
	public TransferAmountpage tp;
	public WaitUtils wait;

	
	@BeforeMethod
	public void Setup(ITestContext context)
	{
		Initialization();
		context.setAttribute("driver", DriverManager.getDriver());
		hp=new Homepage();
		lp=new Loginpage();
		dp=new Dashboardpage();
		mp=new Managepayeeorbeneficiarypage();
		
		wait=new WaitUtils(DriverManager.getDriver(), 20000);
		hp.clickonnewuserlink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='e-Banking System | User Login']"));
		lp.entervalidemailid();
		lp.entervalidpassword();
		lp.clickonloginbutton();
		wait.waitforElementToBeVisible(By.xpath("//span[text()='Payee / Beneficiary']"));
		dp.clickonmanagepayeeorbenefeciarylink();
		wait.waitforElementToBeVisible(By.xpath("//h1[text()='Manage Payee']"));
	
		
		
	}
	
	@Test(priority=1)
	public void TC108_VerifyValidTransferAmountToPayeeTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.EnterTransferAmount("3000");
		tp.ClickonSubmitButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String confirmationtext = alt.getText();
		Assert.assertEquals(confirmationtext, "Transaction Details has been updated","TC_108 Failed,Unable to transfer Amount to Payee");
		alt.accept();
	}
	
	@Test(priority=2)
	public void TC109_VerifyInvalidTransferAmountMoreThanAvailableBalanceToPayeeTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.EnterTransferAmount("80000");
		tp.ClickonSubmitButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String confirmationtext = alt.getText();
		Assert.assertEquals(confirmationtext, "Insufficient amount in account","TC_109 Failed,Amount to Payee Transferred Even when balance was insufficient");
		alt.accept();
	}
	
	@Test(priority=3)
	public void TC110_VerifyInvalidTransferToPayeeWithAmountFieldEmptyTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.ClickonSubmitButton();
		String validationmessage = tp.getValidationMessageofAmountField();
		Assert.assertEquals(validationmessage,"Please fill in this field.","TC110 Failed, Amount was Transferred to Payee Even when Amount in Amount Field was Empty");
	
	}
	
	@Test(priority=4)
	public void TC111_VerifyInvalidNegativeTransferAmountToPayeeTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.EnterTransferAmount("-500");
		tp.ClickonSubmitButton();
		String validationmessage = tp.getValidationMessageofAmountField();
		Assert.assertEquals(validationmessage,"Please match the format requested.","TC111 Failed, Amount was Transferred to Payee Even when Amount in Amount Field was negative");
	}
	
	@Test(priority=5)
	public void TC112_VerifyInvalidTransferAmountInWordsToPayeeTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.EnterTransferAmount("Five Hundred");
		tp.ClickonSubmitButton();
		String validationmessage = tp.getValidationMessageofAmountField();
		Assert.assertEquals(validationmessage,"Please match the format requested.","TC112 Failed, Amount was Transferred to Payee Even when Amount in Amount Field was in words");
	}
	
	@Test(priority=6)
	public void TC113_VerifyInvalidTransferAmountToPayeeTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.EnterTransferAmount("0");
		tp.ClickonSubmitButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String confirmationtext = alt.getText();
		Assert.assertEquals(confirmationtext,"Amount must be greater than zero","TC113 Failed, Amount was Transferred to Payee Even when Amount in Amount Field was 0");
	}
	
	@Test(priority=7)
	public void TC114_VerifyTransferAmountPageRefreshBehaviourTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.EnterTransferAmount("200");
		DriverManager.getDriver().navigate().refresh();
		wait.waitforElementToBeVisible(By.name("amount"));
		String validationmessage = tp.getValuePresentinAmountField();
		Assert.assertTrue(validationmessage.isEmpty(),"TC114 Failed,Values present in Fields were not refreshed");
	}
	
	@Test(priority=8)
	public void TC115_VerifyInvalidSQLInjectionTransferAmountToPayeeTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.EnterTransferAmount("'1'='1");
		tp.ClickonSubmitButton();
		String validationmessage = tp.getValidationMessageofAmountField();
		Assert.assertEquals(validationmessage,"Please match the format requested.","TC115 Failed, Amount was Transferred to Payee Even when Amount in Amount Field was incorrect");
	}
	
	@Test(priority=9)
	public void TC116_VerifyInvalidDecimalTransferAmountToPayeeTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.EnterTransferAmount("9.234");
		tp.ClickonSubmitButton();
		String validationmessage = tp.getValidationMessageofAmountField();
		Assert.assertEquals(validationmessage,"Please match the format requested.","TC116 Failed, Amount was Transferred to Payee Even when Amount in Amount Field was in Decimal Format");
	}
	
	@Test(priority=10)
	public void TC116_VerifyMinimumTransferAmountToPayeeTest()
	{
		mp.Searchpayee("Pooja Kulkarni");
		wait.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		mp.clickontransferbutton();
		wait.waitforElementToBeVisible(By.xpath("//h3[text()='Transfer Amount']"));
		tp=new TransferAmountpage();
		tp.EnterTransferAmount("1");
		tp.ClickonSubmitButton();
		Alert alt = DriverManager.getDriver().switchTo().alert();
		String confirmationtext = alt.getText();
		Assert.assertEquals(confirmationtext, "Transaction Details has been updated","TC_116 Failed,Unable to transfer minimum Amount value to Payee");
		alt.accept();
	}
	

	@AfterMethod
	public void Teardown()
	{
		DriverManager.getDriver().quit();
		DriverManager.unload();
	}
	
	
	
	

}
