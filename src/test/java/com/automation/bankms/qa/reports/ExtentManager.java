package com.automation.bankms.qa.reports;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String path = System.getProperty("user.dir") + "/Reports/ExtentReport.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(path);

            // 🔥 UI Customization
            spark.config().setReportName("BankMS Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");
            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // 🔥 System Info (VERY IMPORTANT)
            extent.setSystemInfo("Project", "BankMS");
            extent.setSystemInfo("Tester", "Sagar Jadhav");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }

        return extent;
    }
}