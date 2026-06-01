package com.automation.bankms.qa.pages.cashier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.asserts.SoftAssert;

import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class CashierDashboardPage {
	
	By dashboardpagetitle=By.xpath("//h1[text()='Dashboard']");
	By dashboardlink=By.xpath("//ul[@id=\"accordionSidebar\"]/li[1]/a");
	By accountholderslink=By.xpath("//ul[@id=\"accordionSidebar\"]/li[2]/a");
	By searchaccountlink=By.xpath("//ul[@id=\"accordionSidebar\"]/li[3]/a");
	By reportlink=By.xpath("//ul[@id=\"accordionSidebar\"]/li[4]/a");
	By transactionhistoryreportlink=By.xpath("//ul[@id=\"accordionSidebar\"]/li[4]/div/div/a");
	By generatetransactionreportlink=By.xpath("(//a[@href='txn-report.php'])[2]");
	By usernamedropdown=By.id("userDropdown");
	By userprofilelink=By.xpath("//div[@aria-labelledby='userDropdown']/a[1]");
	By changepasswordlink=By.xpath("//div[@aria-labelledby='userDropdown']/a[2]");
	By logoutlink=By.xpath("//div[@aria-labelledby='userDropdown']/a[3]");
	By confirmlogoutlink=By.xpath("(//a[@href='logout.php'])[2]");
	By accountholdertext=By.xpath("(//div[@class='col-xl-6 col-md-6 mb-4'])[1]/a/div/div/div/div//div[1]");
	By accountholdertotalcount=By.xpath("(//div[@class='col-xl-6 col-md-6 mb-4'])[1]/a/div/div/div/div//div[2]");
	By totaldepositamounttext=By.xpath("(//div[@class='col-xl-6 col-md-6 mb-4'])[2]/div/div/div/div[1]/div[1]");
	By totaldepositamountvalue=By.xpath("(//div[@class='col-xl-6 col-md-6 mb-4'])[2]/div/div/div/div[1]/div[2]/div/div/text()");
	
   WebDriver driver;
   protected static final Logger log=LogManagerUtil.getLogger(CashierDashboardPage.class);
   WaitUtils wait;
   SoftAssert soft;
	
	public CashierDashboardPage()
	{
		driver=DriverManager.getDriver();
		wait=new WaitUtils(driver, 20000);
		soft=new SoftAssert();
	}
	
	public String getTitleofDashboardPage()
	{
		log.info("Checking title of dashboard page");
		return driver.findElement(dashboardpagetitle).getText();
		
	}
	
	public CashierAccountHoldersPage clickOnAccountHoldersLink()
	{
		log.info("Clicking on Account Holders Link");
		driver.findElement(accountholderslink).click();
		log.info("navigating to account holders page");
		return new CashierAccountHoldersPage();
	}
	
	public CashierDashboardPage clickOnDashboardLink()
	{
		log.info("Clicking on Dashboard Link");
		driver.findElement(dashboardlink).click();
		log.info("navigating to Dashboard page");
		return new CashierDashboardPage();
	}
	
	public void waitForLaunchOfDashboardPage()
	{
		log.info("Checking the dashboard page title is displayed");
		boolean Dashboardtitlestatus = driver.findElement(dashboardpagetitle).isDisplayed();
		soft.assertTrue(Dashboardtitlestatus,"Dashboard title not present on dashboard page");
		log.info("waiting for account holders link to be clickable");
		wait.waitforElementToBeClickable(accountholderslink);
		log.info("waiting for dashboard link to be clickable");
		wait.waitforElementToBeClickable(dashboardlink);
		log.info("waiting for search account link to be clickable");
		wait.waitforElementToBeClickable(searchaccountlink);
		log.info("waiting for report link to be clickable");
		wait.waitforElementToBeClickable(reportlink);
		log.info("waiting for transaction history report link to be clickable");
		log.info("Clicking on report Link");
		driver.findElement(reportlink).click();
		wait.waitforElementToBeClickable(transactionhistoryreportlink);
		log.info("waiting for generate transaction report link to be clickable");
		wait.waitforElementToBeClickable(generatetransactionreportlink);
		log.info("Clicking on User Profile Name");
		driver.findElement(usernamedropdown).click();
		log.info("waiting for user profile link to be clickable");
		wait.waitforElementToBeClickable(userprofilelink);
		log.info("waiting for change password link to be clickable");
		wait.waitforElementToBeClickable(changepasswordlink);
		log.info("waiting for Logout link to be clickable");
		wait.waitforElementToBeClickable(logoutlink);
		soft.assertAll();
		
		log.info("Dashboard Page is Launched Successfully");
	}

	public CashierSearchAccountHoldersPage clickOnSearchAccountHolderLink()
	{
		log.info("Clicking on Search Account Holder Link");
		driver.findElement(searchaccountlink).click();
		log.info("navigating to Search Account Holder page");
		return new CashierSearchAccountHoldersPage();
	}
	
}
