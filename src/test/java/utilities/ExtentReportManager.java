package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter; 
	public ExtentReports extent; 
	public ExtentTest test; 
	
	String repName;
	
	public void onStart(ITestContext context) {
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter =new ExtentSparkReporter(".\\reports\\"+repName);  //specify the location of report
		
		sparkReporter.config().setDocumentTitle("Automation Report"); //title of the report
		sparkReporter.config().setReportName("Functional Testing"); //name of the report
		sparkReporter.config().setTheme(Theme.DARK); 
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer name", "localhost");
		extent.setSystemInfo("Application", "openCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Enivroment", "QA");
		extent.setSystemInfo("Tester name", "Preema"); //extent.setSystemInfo("Tester name", System.getProperty("user.name"));
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
	
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); 
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test case PASSED is: "+result.getName()); 
	  }
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); 
		test.assignCategory(result.getMethod().getGroups()); 
		
		test.log(Status.FAIL, "Test case FAILED is: "+result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
			
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
		
	  }
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); 
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case SKIPPED is: "+result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext context) {
		 extent.flush(); // all the details will be written in the report
		 
		 /*
		  // To open the report automatically
		 String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport=new File(pathOfExtentReport);
		 
		 try {
			 Desktop.getDesktop().browse(extentReport.toURI());
			 
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
*/
}

	
	

}
