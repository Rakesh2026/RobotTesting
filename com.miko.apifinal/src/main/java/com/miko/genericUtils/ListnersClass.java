package com.miko.genericUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
/**
 * This class used to handle extentReport
 * @author Rakesh
 */


public class ListnersClass implements ITestListener {
	 
	public static ExtentReports reports;
   public static ExtentTest test;
    
	public void onTestStart(ITestResult result) {
		
		
	}
    
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,result.getMethod().getMethodName()+" is passed");
		
	}
    
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getThrowable());
		test.log(Status.FAIL, result.getMethod().getMethodName()+"is Failed");
		
		
		
	
	}
    
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,result.getMethod().getMethodName()+" SKIPPED");
	}
    
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
    
	public void onStart(ITestContext context) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(dtf.format(now));  
		
		ExtentHtmlReporter reporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReports/ExtentReports"+dtf.format(now)+".html");
		reporter.config().setDocumentTitle("Report");
		
		reports=new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("BuildNo", "5.1");
		reports.setSystemInfo("Env", "Pre-Prod");
		//reports.setSystemInfo("Platform", PropertyFileUtil.getPropertiesData("PlatForm_Name"));
		//reports.setSystemInfo("Device", PropertyFileUtil.getPropertiesData("Device_Name"));
		//reports.setSystemInfo("Version", PropertyFileUtil.getPropertiesData("Platform_Version"));
	}
	
	
	public void onFinish(ITestContext context) {
		reports.flush();
		
	}

}
