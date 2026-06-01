package com.automation.bankms.qa.pages.user;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.utils.WaitUtils;

public class Managepayeeorbeneficiarypage {
	
	@FindBy(xpath="//h1[text()='Manage Payee']") private WebElement managepayeepagetitle;
	@FindBy(xpath="//input[@type='search']")private WebElement searchinputbox;
	@FindBy(xpath="(//a[text()='Delete'])[1]")private WebElement Deletebutton;
	@FindBy(xpath="(//a[text()='Transfer'])[1]")private WebElement Transferbutton;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[1]")private WebElement SrNoheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[2]")private WebElement nameofpayeeheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[3]")private WebElement mobilenumberheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[4]")private WebElement emailaddressheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[5]")private WebElement accountnumberheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[6]")private WebElement creationdateheader;
	@FindBy(xpath="//table[@id='dataTable']/thead/tr/th[7]")private WebElement actionheader;
	@FindBy(xpath="//table[@id='dataTable']/tbody/tr/td[2]")private List<WebElement> getpayeenames;
	
	 WaitUtils utils;
	 WebDriver driver;
	
	public Managepayeeorbeneficiarypage()
	{
		driver=DriverManager.getDriver();
		PageFactory.initElements(driver, this);
		utils=new WaitUtils(driver, 2000);
	}
	
	public boolean checkentrypresentinsidetable(String payeename)
	{
		int totalrows = driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr")).size();
		//int totalcolumns = driver.findElements(By.xpath("//table[@id='dataTable']/thead/tr/th")).size();
		
		
		for(int a=1;a<=totalrows;a++)
		{
			
				String payeetext = driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr["+a+"]/td[2]")).getText();
				if(payeetext.equalsIgnoreCase(payeename))
				{
					return true;
				}
				
				

			
		}
		
		return false;
	}
	
	public boolean checkworkingofsearchpage(String Enterdetailofpayee)
	{
		
		searchinputbox.sendKeys(Enterdetailofpayee);
		utils=new WaitUtils(driver, 20000);
		utils.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/tbody/tr"));
		int totalrows=driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr")).size();
		int totalcolumns=driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr[1]/td")).size();
		boolean status=false;
		for(int a=1;a<=totalrows;a++)
		{
			for(int b=1;b<=totalcolumns;b++)
			{
			String verificationtext = driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr["+a+"]/td["+b+"]")).getText();
			if(verificationtext.equalsIgnoreCase(Enterdetailofpayee))
			{
				status=true;
			}
			}
		}
		
		
		return status;
	}
	
	public void checkvibilityofmanagepayeepagetitle()
	{
		utils.waitforElementToBeVisible(By.xpath("//h1[text()='Manage Payee']"));
	
		
	}
	
	public String gettextofmanagepayeepagetitle()
	{
		return managepayeepagetitle.getText();
	
		
	}
	
	public void checkvisibilityofsearchinputfield()
	{
		utils.waitforElementToBeClickable(By.xpath("//input[@type='search']"));
	}
	
	public void checkvisibilityofdeletebutton()
	{
		utils.waitforElementToBeClickable(By.xpath("(//a[text()='Delete'])[1]"));
	}
	
	public void checkvisibilityoftransferbutton()
	{
		utils.waitforElementToBeClickable(By.xpath("(//a[text()='Transfer'])[1]"));
	}
	
	public void checkvisibilityoftableheader()
	{
		utils.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/thead/tr/th[1]"));
		utils.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/thead/tr/th[2]"));
		utils.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/thead/tr/th[3]"));
		utils.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/thead/tr/th[4]"));
		utils.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/thead/tr/th[5]"));
		utils.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/thead/tr/th[6]"));
		utils.waitforElementToBeVisible(By.xpath("//table[@id='dataTable']/thead/tr/th[7]"));
		
		
	}
	
	public void clickonnameofpayeeheader()
	{
		nameofpayeeheader.click();
	}
	
	public void clickonpayeesmobilenumberheader()
	{
		mobilenumberheader.click();
	}
	
	public void clickonpayeesaccountnumberheader()
	{
		accountnumberheader.click();
	}
	
	public ArrayList<String> getpayeenames()
	{
		ArrayList<String> al=new ArrayList<String>();
		 List<WebElement> listofelements = driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr/td[2]"));
		for(WebElement element:listofelements)
		{
			String retrievedelement = element.getText();
			al.add(retrievedelement);
		}
		
		return al;
	}
	
	
	public ArrayList<String> getpayeeemail()
	{
		ArrayList<String> al=new ArrayList<String>();
		 List<WebElement> listofelements = driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr/td[4]"));
		for(WebElement element:listofelements)
		{
			String retrievedelement = element.getText();
			al.add(retrievedelement);
		}
		
		return al;
	}
	
	public ArrayList<Long> getpayeemobilenumber()
	{
		ArrayList<Long> al=new ArrayList<Long>();
		 List<WebElement> listofelements = driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr/td[3]"));
		for(WebElement element:listofelements)
		{
			Long retrievedelement = Long.parseLong(element.getText());
			al.add(retrievedelement);
		}
		
		return al;
	}
	
	public ArrayList<Long> getpayeeaccountnumber()
	{
		ArrayList<Long> al=new ArrayList<Long>();
		 List<WebElement> listofelements = driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr/td[5]"));
		for(WebElement element:listofelements)
		{
			Long retrievedelement = Long.parseLong(element.getText());
			al.add(retrievedelement);
		}
		
		return al;
	}
	
	public TransferAmountpage clickontransferbutton()
	{
		Transferbutton.click();
		return new TransferAmountpage();
	}
	
	public void Searchpayee(String payeename)
	{
		searchinputbox.sendKeys(payeename);
	}
	
	
	
	

}
