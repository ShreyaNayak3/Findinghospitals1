package com.hospital.qa.pages;
import java.util.ArrayList;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import java.util.Iterator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.hospital.qa.util.DriverSetup;
import com.hospital.qa.util.ExtentReport;

public class HomePage extends DriverSetup {
	public WebDriver driver;
	public Properties prop;
	public String loc;

	public ArrayList<String> HosRat = new ArrayList<String>();

	ExtentReports report = ExtentReport.getReportInstance();

	public HomePage(WebDriver driver, Properties prop) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.prop = prop;

	}

	//*** Reusable class to select hospital and location 
	
	public void locationHospital(String xpathKey, String name) {
	
		System.out.println("hospital is entered");
		//***To select hospital from auto suggestion
		try {
			driver.findElement(By.xpath(prop.getProperty(xpathKey))).click();
			if (xpathKey.equalsIgnoreCase("location_xpath")) {
				driver.findElement(By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/span[2]/span")).click();
				
				
				
			}

			driver.findElement(By.xpath(prop.getProperty(xpathKey))).sendKeys((prop.getProperty(name)));

			if (xpathKey.equalsIgnoreCase("hospital_xpath")) {
				
			      Thread.sleep(500);
			     	driver.findElement(By.xpath(prop.getProperty(xpathKey))).sendKeys(Keys.ENTER);    	
			     	
			}
			
		} catch (Exception e) {
			//reportFail(e.getMessage());
		}
	}

	public void reportFail(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
		//Assert.fail();

	}

	//*** Select 24/7 checkbox
	public void checkbox(String xpath) {
		driver.findElement(By.xpath(prop.getProperty(xpath))).click();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//*** Select has parking checkbox
	public void drop(String xpath, String parking) {

		try {
		WebElement element = driver.findElement(By.xpath(prop.getProperty(xpath)));
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();		
		WebElement park = driver.findElement(By.xpath(prop.getProperty(parking)));
		wait.until(ExpectedConditions.elementToBeClickable(park));
		park.click();
		}
		catch(StaleElementReferenceException ex) {
			WebElement element = driver.findElement(By.xpath(prop.getProperty(xpath)));
			WebDriverWait wait = new WebDriverWait(driver,15);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			WebElement park = driver.findElement(By.xpath(prop.getProperty(parking)));
			wait.until(ExpectedConditions.elementToBeClickable(park));
			park.click();
		}

	}

	//*** display all hospitals with rating above 3.5
	
	public void displayHospitals(String xpath, String rate) {

		java.util.List<WebElement> rating = driver.findElements(By.xpath(prop.getProperty(rate)));

		java.util.List<WebElement> links = driver.findElements(By.xpath(prop.getProperty(xpath)));

		Iterator<WebElement> itr = rating.iterator();
		Iterator<WebElement> itr1 = links.iterator();
		while (itr1.hasNext() && itr.hasNext()) {

			String hos = ((WebElement) itr1.next()).getText();
			String rat = ((WebElement) itr.next()).getAttribute("title");
			if (Float.parseFloat(rat) > 3.5) {
				String data = hos + "-" + rat;
				HosRat.add(data);

			}

		}

	}

	//*** To iterate through all webelements in under the criteria given
	public void noOfPages(String key, String start, String end) {

		for (int i = Integer.parseInt(prop.getProperty(start)); i < Integer.parseInt(prop.getProperty(end)); i++) {
			String dynamic_xpath = prop.getProperty(key);
			dynamic_xpath = dynamic_xpath + i + "]/a";
			// System.out.println(dynamic_xpath);
			eleToClick(dynamic_xpath);

			try {
				Thread.sleep(1000);
			} catch (Exception e) {

			}
		}
	}

	public void checks(String key) {
		try {
			WebElement titles = driver.findElement(By.xpath(key));
			String text = titles.getAttribute("Placeholder");
			Assert.assertEquals("Search doctors, clinics, hospitals, etc.", text);
		} catch (Exception e) {

		}
	}

	public void checkTitle(String key) {
		try {
			WebElement title = driver.findElement(By.xpath(key));
			title.getText();
			Assert.assertEquals(
					"Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests ",
					title);
		} catch (Exception e) {
		}
	}

	public void checkboxText(String key) {
		try {
			WebElement title = driver.findElement(By.xpath(key));
			title.getText();
			Assert.assertEquals("Open 24X7 ", title);
		} catch (Exception e) {

		}
	}

	public void dropdownText(String key) {
		try {
			WebElement title = driver.findElement(By.xpath(key));
			title.getText();
			Assert.assertEquals("All Filters ", title);
		} catch (Exception e) {

		}
	}

	public void hasParkingText(String key) {
		try {
			WebElement title = driver.findElement(By.xpath(key));
			title.getText();
			Assert.assertEquals("Has Parking ", title);
		} catch (Exception e) {

		}
	}

	//*** To click on all page numbers
	public void eleToClick(String key) {
		driver.findElement(By.xpath(key)).click();
		displayHospitals("display_xpath", "rating_xpath");
	}

}
