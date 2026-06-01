package com.automation.bankms.qa.pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;


import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.pages.admin.Adminloginpage;
import com.automation.bankms.qa.pages.cashier.CashierLoginPage;

public class Homepage  {
	
	@FindBy(xpath="(//a[text()='User/Account Holder'])[2]")private WebElement newuserlink;
    @FindBy(xpath="(//a[text()='Cashier'])[2]")private WebElement cashierloginlink;
    @FindBy(xpath="(//a[text()='Admin'])[2]")private WebElement adminloginlink;
    
    By newuserlink1=By.xpath("(//a[text()='User/Account Holder'])[2]");
    By cashierloginlink1=By.xpath("(//a[text()='Cashier'])[2]");
    By adminloginlink1=By.xpath("(//a[text()='Admin'])[2]");
    By applicationtitle=By.xpath("(//p[text()='e-Banking System'])[3]");
	WebDriver driver;
	WaitUtils wait;
	protected static final Logger log=LogManagerUtil.getLogger(Homepage.class);
    
    public Homepage()
    {
    	driver=DriverManager.getDriver();
    	PageFactory.initElements(driver, this);
    	
    	wait=new WaitUtils(driver, 20000);
    }
    
    public Loginpage clickonnewuserlink()
    {
    	newuserlink.click();
    	return new Loginpage();
    }
    
    public CashierLoginPage clickoncashierloginlink()
    {
    	cashierloginlink.click();
    	return new CashierLoginPage();
    }
    
    public Adminloginpage clickonadminloginlink()
    {
    	adminloginlink.click();
    	return new Adminloginpage();
    }
    
    public String getTitleofHomepage()
    {
    	return driver.findElement(applicationtitle).getText();
    }
    
    public boolean checkNewuserLinkisClickable()
    {
    	try {
    	log.info("Checking New User Link is Visible");
    	wait.waitforElementToBeVisible(newuserlink1);
    	log.info("New User Link is Visible");
    	log.info("Checking New User Link is Clickable");
    	wait.waitforElementToBeClickable(newuserlink1);
    	log.info("New User Link is Clickable");
    	return true;
    	}
    	catch(Exception E)
    	{
    		log.info("The Exception is {}",E.getMessage());
    		return false;
    	}
    	
    }
    
    public boolean checkCashierLinkisClickable()
    {
    	try {
    	log.info("Checking Cashier Link is Visible");
    	wait.waitforElementToBeVisible(cashierloginlink1);
    	log.info("Cashier link is Visible");
    	log.info("Checking Cashier Link is Clickable");
    	wait.waitforElementToBeClickable(cashierloginlink1);
    	log.info("Cashier Link is Clickable");
    	return true;
    	}
    	catch(Exception E)
    	{
    		log.info("The Exception is {}",E.getMessage());
    		return false;
    	}
    	
    }
    
    public boolean checkAdminLinkisClickable()
    {
    	try {
    	log.info("Checking Admin Link is Visible");
    	wait.waitforElementToBeVisible(adminloginlink1);
    	log.info(" Admin Link is Visible");
    	log.info("Checking Admin Link is Clickable");
    	wait.waitforElementToBeClickable(adminloginlink1);
    	log.info("Admin Link is Clickable");
    	return true;
        }
    	catch(Exception E)
    	{
    		log.info("The Exception is {}",E.getMessage());
    		return false;
    	}
    	
    }
    
 
	
	
	
	
	
	
	

}
