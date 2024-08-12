package rsa.pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rsa.ExtentReporter;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent=ExtentReporter.getReports();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();// for parallel mode
	@Override
	public void onTestStart(ITestResult result) {
	 test=extent.createTest(result.getMethod().getMethodName());
	 extentTest.set(test);
	 
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, "Passed");
		extentTest.get().log(Status.PASS, "Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, "Failed");
		//test.fail(result.getThrowable());
        extentTest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		String filePath = null;
		try {
			filePath=takeScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		 extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
