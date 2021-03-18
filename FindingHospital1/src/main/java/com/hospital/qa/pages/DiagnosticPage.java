package com.hospital.qa.pages;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hospital.qa.util.DriverSetup;

public class DiagnosticPage extends  DriverSetup {
	
	public WebDriver driver;
	public Properties prop;
	public ArrayList<String> topcities = new ArrayList<String>();
	
	public DiagnosticPage(WebDriver driver, Properties prop) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.prop=prop;
	}
	
//***To Open DiagnosticPage
	
public void openDiagnosticspage(String xpath){
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);
		driver.findElement(By.xpath(prop.getProperty(xpath))).click();
	}
	public String  getTopCites(String xpath) {
		String get=driver.findElement(By.xpath(prop.getProperty(xpath))).getText();
		
		return get;
	
	}

}


