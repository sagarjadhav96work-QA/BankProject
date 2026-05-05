package com.automation.bankms.qa.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.bankms.qa.reports.ExtentManager;
import com.automation.bankms.qa.reports.ExtentTestManager;
import com.automation.bankms.qa.utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = (WebDriver)result.getTestContext().getAttribute("driver"); // 🔥 YOUR DRIVER

        String path = ScreenshotUtils.takeScreenshot(driver, result.getName());

        ExtentTestManager.getTest().fail(result.getThrowable());

        try {
            ExtentTestManager.getTest()
                    .addScreenCaptureFromPath(path, "Failure Screenshot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // VERY IMPORTANT
    }
}