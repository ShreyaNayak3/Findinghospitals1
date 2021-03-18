package com.hospital.qa.pages;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hospital.qa.util.DriverSetup;

public class  CorporateWellnessPage extends  DriverSetup {
	public WebDriver driver;
	public Properties prop;

	public CorporateWellnessPage(WebDriver driver2, Properties prop2) {
		// TODO Auto-generated constructor stub
		this.driver=driver2;
		this.prop=prop2;
	}

	public void enterText(String ID,String value) {
       
		try {
			 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.findElement(By.id(prop.getProperty(ID))).sendKeys((prop.getProperty(value)));
			 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
	}

	//*** Reusable method used to click any WebElement
	
	public void clickElement(String ID) {
		try {
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.id(prop.getProperty(ID))).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void clickElement1(String Linktext) {
		try {
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.linkText(prop.getProperty(Linktext))).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void windowsHandle() {
		try {
		Set<String> windowsId = driver.getWindowHandles();
		
	Iterator<String> itr = windowsId.iterator();
	String Practo = itr.next();
	String CW = itr.next();
	
	driver.switchTo().window(CW);
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	}

	public ArrayList<String> alertMsg() {
		ArrayList<String> AlertMsg = new ArrayList<String>();
		try {
			Alert alert = driver.switchTo().alert();
			AlertMsg.add(alert.getText());	
			
			 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			alert.accept();
		}
		catch(Exception e) {
			
		}

		return AlertMsg;
	}
   public void accept () {
	   try {
		   Alert alert = driver.switchTo().alert();
			alert.accept();
	   }
	   catch(Exception e) {
		   
	   }
	 
   }

	//****Reusable method used to click the element using xpath 
   
	public void clickElementByXpath(String xpath) {
		try {
			driver.findElement(By.xpath(prop.getProperty(xpath))).click();
			 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	//**** method to quit browser

	public void closeBrowser()

	{
		
		driver.quit();
	}
}

