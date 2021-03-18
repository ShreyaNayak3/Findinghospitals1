package com.hospital.qa.util;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReport {

public static ExtentReports report;
	
	public void ExtentReportManager(WebDriver driver, Properties prop) {
	// TODO Auto-generated constructor stub
}

	public static ExtentReports getReportInstance() {
		
		if(report==null) {
			String reportName=DateUtils.getTimeStamp()+".html";
			System.out.println("Report Name: "+reportName);
			ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\"+reportName);
			
			report=new ExtentReports();
			report.attachReporter(htmlReporter);
			
			
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "QE&A");
			report.setSystemInfo("Team Name", "Error 404");
			report.setSystemInfo("Event Name", "Hackathon");
			
			
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle("Automation testing in practo.com");
			htmlReporter.config().setReportName("Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd,yyyy  hh:mm:ss");
		}
		return report;
		
	}
}
