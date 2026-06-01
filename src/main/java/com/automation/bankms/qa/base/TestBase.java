package com.automation.bankms.qa.base;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;

import com.automation.bankms.qa.config.ConfigReader;
import com.automation.bankms.qa.driver.DriverManager;
import com.automation.bankms.qa.utils.LogManagerUtil;
import com.automation.bankms.qa.utils.WaitUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	
	
	public WaitUtils ut;
	protected static final Logger log=LogManagerUtil.getLogger(TestBase.class);
	
	
	public void Initialization() 
	{
		log.info("Launching Browser");
	    
	
		String browsername=ConfigReader.getProperty("Browser");
		
	
		if(browsername.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			DriverManager.setDriver(new ChromeDriver());
		}
		else if(browsername.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver","C:\\Drivers\\edgedriver\\msedgedriver.exe");//this changes are done as out test cases where not running in edge browser due to
			//edge was not downloaded by selenium,so we manually downloaded it stored in above path and informed selenium about it using system.setproperty()
			DriverManager.setDriver(new EdgeDriver());
		}
		else if(browsername.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			DriverManager.setDriver(new FirefoxDriver());
		}
		else
		{
			 System.out.println("Invalid Browser Name");
		}
		
	    
		
		log.info("Maximizing window");
		DriverManager.getDriver().manage().window().maximize();
		log.info("deleting all cookies");
		DriverManager.getDriver().manage().deleteAllCookies();
		log.info("launching URL");
		DriverManager.getDriver().get(ConfigReader.getProperty("URL"));
		
		ut=new WaitUtils(DriverManager.getDriver(), 20000);
		
		ut.waitForElementToDisappear(By.id("overlayer"));
		
		ut.waitforElementToBeClickable(By.xpath("(//a[text()='User/Account Holder'])[2]"));
		log.info("Homepage Launched");
	
		
		
	}
	
	public void tearDown()
	{
		if(DriverManager.getDriver()!=null)
		{
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}

}
