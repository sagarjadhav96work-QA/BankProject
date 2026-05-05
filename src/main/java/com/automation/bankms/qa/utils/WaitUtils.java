package com.automation.bankms.qa.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	public WaitUtils(WebDriver driver,int timeinmilliseconds)
	{
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofMillis(timeinmilliseconds));
	}

	public WebElement waitforElementToBeClickable(By Locator)
	{
		return wait.until(ExpectedConditions.elementToBeClickable(Locator));
		
	}
	
	public Boolean waitforElementToBeSelected(By Locator)
	{
		return wait.until(ExpectedConditions.elementToBeSelected(Locator));
	}
	
	public WebElement waitforElementToBeVisible(By Locator)
	{
		return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}
	
	public WebElement waitforElementToBePresent(By Locator)
	{
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
	}
	
	public void waitForElementToDisappear(By locator)
	{
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitforElementsToBeVisible(List<WebElement> elements)
	{
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public Alert waitforAlert()
	{
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitForNewWindowToOpen(int currentWindowCount)
	{
	    wait.until(driver -> driver.getWindowHandles().size() > currentWindowCount);
	}
	
	
	
}
