package com.automation.bankms.qa.utils;

import java.util.Set;
import org.openqa.selenium.WebDriver;

public class WindowUtils {

    WebDriver driver;

    public WindowUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToNewWindow() {
        String parentWindow = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void switchToParentWindow(String parentWindow) {
        driver.switchTo().window(parentWindow);
    }
}