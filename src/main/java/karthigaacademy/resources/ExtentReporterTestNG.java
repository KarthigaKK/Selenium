package karthigaacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNG {

	public static ExtentReports getReporterObject()
	{
		String filepath=System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(filepath);
		reporter.config().setDocumentTitle("Products Purchase Ecommerce Website");
		reporter.config().setReportName("Automation Practise");
		
		ExtentReports report=new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Karthiga");
		return report;
		
	}
}
