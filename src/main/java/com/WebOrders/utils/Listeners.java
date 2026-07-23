package com.WebOrders.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.WebOrders.common.WebOrders_BasePage;

public class Listeners implements ITestListener {
	private ExtentReports extent = ExtentReportBase.CreateExtentReport("TestReport.html", "Firefox");
	private ExtentTest test;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	public WebDriver driver;
	WebOrders_BasePage basePage;

	public void onFinish(ITestContext context) {
		extent.flush();
	}

	public void onStart(ITestContext context) {
		// Any setup can be done here if needed
	}

	public void onTestFailure(ITestResult result) {
		test = extentTest.get();
		test.fail(result.getThrowable());

		// Capture screenshot on test failure
		try {
//            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			driver = WebOrders_BasePage.getDriver();
			basePage = new WebOrders_BasePage(driver);
			String screenshotPath = ExtentReportBase.getScreenhot(driver, result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			test.fail("Test failed, but unable to capture screenshot: " + e.getMessage());
		}
	}

	public void onTestSkipped(ITestResult result) {
	    // 1. Check if the test object was ever initialized
	    if (test == null) {
	        // 2. Create it just so we can log the skip reason to ExtentReports
	        test = extent.createTest(result.getMethod().getMethodName());
	    }
	    
	    // 3. Log the skip and the actual error that caused it
	    test.log(Status.SKIP, "Test Skipped");
	    test.log(Status.SKIP, result.getThrowable()); 
	}
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		test = extentTest.get();
		test.log(Status.PASS, "Test Successful");

		// Capture screenshot on test success
		try {
//            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			driver = WebOrders_BasePage.getDriver();
			basePage = new WebOrders_BasePage(driver);
			String screenshotPath = ExtentReportBase.getScreenhotSuccess(driver, result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			test.fail("Test passed, but unable to capture screenshot: " + e.getMessage());
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Optionally handle this scenario
	}
}