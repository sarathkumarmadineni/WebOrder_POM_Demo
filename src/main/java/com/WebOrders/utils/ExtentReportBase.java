package com.WebOrders.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportBase {
	
	 static ExtentReports extent;
	    static ExtentSparkReporter reporter;

	    public static ExtentReports ExtentReportGenerator() {
	        reporter = new ExtentSparkReporter("./ExtentReport/SingleReport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(reporter);

	        extent.setSystemInfo("OS", System.getProperty("os.name"));
	        extent.setSystemInfo("Browser", "Chrome");

	        reporter.config().setDocumentTitle("Consolidate ExtentReport Result");
	        reporter.config().setTheme(Theme.DARK);
	        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	        return extent;
	    }

	    public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
	        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String destination = System.getProperty("user.dir") + "/Screenshot_fail/" + screenshotName + dateName + ".png";
	        File finalDestination = new File(destination);
	        FileUtils.copyFile(source, finalDestination);
	        return destination;
	    }

	    public static String getScreenhotSuccess(WebDriver driver, String screenshotName) throws Exception {
	        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String destination = System.getProperty("user.dir") + "/Screenshot_success/" + screenshotName + dateName + ".png";
	        File finalDestination = new File(destination);
	        FileUtils.copyFile(source, finalDestination);
	        return destination;
	    }

	    public static ExtentReports CreateExtentReport(String reportName, String browser) {
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./ExtentReport/" + reportName);
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);

	        extent.setSystemInfo("OS", System.getProperty("os.name"));
	        extent.setSystemInfo("Browser", browser);

	        htmlReporter.config().setDocumentTitle("Regression Test");
	        htmlReporter.config().setReportName(reportName);
	        htmlReporter.config().setTheme(Theme.DARK);
	        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	        
	        return extent;
	    }


}
