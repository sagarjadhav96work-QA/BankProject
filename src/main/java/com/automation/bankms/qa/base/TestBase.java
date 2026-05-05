package com.automation.bankms.qa.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;

import com.automation.bankms.qa.config.ConfigReader;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

public class TestBase {
	
	
	public WebDriver driver;
	public WaitUtils ut;
	protected static final Logger log=LogManagerUtil.getLogger(TestBase.class);
	
	
	public void Initialization() 
	{
		log.info("Launching Browser");
		String browsername=ConfigReader.getProperty("Browser");
		
		if(browsername.equals("chrome"))
		{
			
			driver=new ChromeDriver();
		}
		else if(browsername.equals("edge"))
		{
			
			driver=new EdgeDriver();
		}
		else if(browsername.equals("firefox"))
		{
			
			driver=new FirefoxDriver();
		}
		else
		{
			 System.out.println("Invalid Browser Name");
		}
		
		log.info("Maximizing window");
		driver.manage().window().maximize();
		log.info("deleting all cookies");
		driver.manage().deleteAllCookies();
		log.info("launching URL");
		driver.get(ConfigReader.getProperty("URL"));
		
		ut=new WaitUtils(driver, 20000);
		
		ut.waitForElementToDisappear(By.id("overlayer"));
		
		ut.waitforElementToBeClickable(By.xpath("(//a[text()='User/Account Holder'])[2]"));
		log.info("Homepage Launched");
	
		
		
	}

}
