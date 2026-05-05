package com.automation.bankms.qa.pages.user;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.bankms.qa.utils.WaitUtils;

public class Dashboardpage {
	
	
	@FindBy(xpath="//div[text()='e-Banking ']")private WebElement applicationtitle;
	@FindBy(xpath ="//h1[text()='Dashboard']")private WebElement dashboardpagetitle;
	@FindBy(xpath="//div[contains(@class,'alert-danger')]")private WebElement newaccountopeningalert;
	@FindBy(xpath="(//a[@href='report.php'])[2]")private WebElement generatereportlink;
	@FindBy(xpath="(//a[@href='dashboard.php'])[2]")private WebElement dashboardlink;
	@FindBy(xpath="//span[text()='Account Openning']")private WebElement accountopeninglink;
	@FindBy(xpath="//span[text()='Payee / Beneficiary']")private WebElement payeeorbeneficiarylink;
	@FindBy(xpath="//a[text()='Add']")private WebElement addpayeeorbeneficiarylink;
	@FindBy(xpath="//a[text()='Manage']")private WebElement managepayeeorbeneficiarylink;
	@FindBy(xpath="//span[text()='Transaction History']")private WebElement transactionhistorylink;
	@FindBy(xpath="//span[text()='Report']")private WebElement reportlink;
	@FindBy(xpath="//a[@id='userDropdown']")private WebElement userinfolink;
	@FindBy(xpath="//a[@href='profile.php']")private WebElement userprofilelink;
	@FindBy(xpath="//a[@href='change-password.php']")private WebElement changepasswordlink;
	@FindBy(xpath="(//a[@href='logout.php'])[1]")private WebElement logoutlink;
	@FindBy(xpath="(//a[@href='logout.php'])[2]")private WebElement logoutconfirmationbutton;
	@FindBy(xpath="//span[text()='New Request']")private WebElement accountopeningconfirmationtext;
	@FindBy(xpath="//a[@id='userDropdown']/span")private WebElement userprofilename;
	@FindBy(xpath="(//div[@class='col mr-2'])[1]/div[1]")private WebElement availablebalancetext;
	@FindBy(xpath = "(//div[@class='col mr-2'])[1]/div[2]")private WebElement availablebalanceamount;
	@FindBy(xpath="(//div[@class='col mr-2'])[2]/div[1]")private WebElement managepayeeorbeneficiarytext;
	@FindBy(xpath="(//div[@class='col mr-2'])[2]/div[2]")private WebElement managepayeeorbeneficiarycount;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th")private List<WebElement> listofheaders;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr")private List<WebElement>transactionrecords;
	
    WaitUtils wait;
	WebDriver driver;
	
	public Dashboardpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		wait=new WaitUtils(driver, 20000);
	}
	
	
	public String verifyvisibilityofdashboardpagetitle()
	{
		return dashboardpagetitle.getText();
	}
	
	public String verifyvisibilityofapplicationpagetitle()
	{
		return applicationtitle.getText();
	}
	
	public Useraccountopeningpage clickonaccountopeninglink()
	{
		accountopeninglink.click();
		return new Useraccountopeningpage(driver);
	}
	
	public String checknewuseralert()
	{
		return newaccountopeningalert.getText();
	}

	public Loginpage clickonlogoutbutton()
	{
		logoutlink.click();
		wait.waitforElementToBeClickable(By.xpath("(//a[@href='logout.php'])[2]"));
		logoutconfirmationbutton.click();
		return new Loginpage(driver);
	}
	
	public Dashboardpage clickondashboardlink()
	{
		dashboardlink.click();
		return new Dashboardpage(driver);
		
	}
	
	public void clickontransactionhistorylink()
	{
		transactionhistorylink.click();
		
	}
	
	public TransactionReportpage clickonreportlink()
	{
		reportlink.click();
		return new TransactionReportpage(driver);
	}
	
	public void clickonuserinfolink()
	{
		userinfolink.click();
	}
	
	public UserProfileUpdatePage clickonuserprofilelink()
	{
		userprofilelink.click();
		return new UserProfileUpdatePage(driver);
	}
	
	public ChangePasswordPage clickonchangepasswordlink()
	{
		changepasswordlink.click();
		return new ChangePasswordPage(driver);
	}
	
	public String getaccountopeningconfirmationtext()
	{
		return accountopeningconfirmationtext.getText();
	}
	
	public Addpayeeorbeneficiarypage clickonaddpayeeorbenefeciarylink()
	{
		payeeorbeneficiarylink.click();
		wait.waitforElementToBeClickable(By.xpath("//a[text()='Add']"));
		
		addpayeeorbeneficiarylink.click();
		return new Addpayeeorbeneficiarypage(driver);
		
	}
	
	public Managepayeeorbeneficiarypage clickonmanagepayeeorbenefeciarylink()
	{
		payeeorbeneficiarylink.click();
		wait.waitforElementToBeClickable(By.xpath("//a[text()='Manage']"));
		managepayeeorbeneficiarylink.click();
		return new Managepayeeorbeneficiarypage(driver);
	}
	
	public String getNameofUserProfile()
	{
		return userprofilename.getText();
	}
	
	public boolean checkGenerateReportLinkisEnabled()
	{
		return generatereportlink.isEnabled();
		
	}
	
	public boolean checkSidePanelLinksareEnabled()
	{
		return dashboardlink.isDisplayed()&&accountopeninglink.isDisplayed()
				&&payeeorbeneficiarylink.isDisplayed()
				&&transactionhistorylink.isDisplayed()&&reportlink.isDisplayed();
		
	}
	
	public String checkAvailableBalanceTextPresent()
	{
		return availablebalancetext.getText();
	}
	
	public boolean checkAvailableBalanceAmount()
	{
		int Amountininteger = Integer.parseInt(availablebalanceamount.getText());
		if (Amountininteger>=0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String checkManagePayeeorBeneficiaryTextPresent()
	{
	               return  managepayeeorbeneficiarytext.getText();	
	}
	
	public boolean checkManagePayeeorBeneficiaryCount()
	{
		int payeeorBeneficiarycount = Integer.parseInt(managepayeeorbeneficiarycount.getText());
		if(payeeorBeneficiarycount>=0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkRecentTransactionTableHeaderareVisible()
	{
		ArrayList<String> list=new ArrayList<String>();
		for(WebElement headername:listofheaders)
		{
			list.add(headername.getText());
		}
		
		if(list.contains("S.No")&&list.contains("Transaction Number")&&list.contains("Received/Sent Account No")
				&&list.contains("Amount")&&list.contains("Transaction Type")&&list.contains("Status")&&list.contains("Txn Date"))
		{
			return true;
		}
		else
		{
			return false;
		}

	

	}
	
	public boolean checkTransactionCount()
	{
		int totaltransactionrecordscount = transactionrecords.size();
		if(totaltransactionrecordscount>=0&&totaltransactionrecordscount<=20)
		{
			return true;
		}
		else
		{
			System.out.println("The Transaction Counts are "+totaltransactionrecordscount);
			return false;
		}
	}
}
