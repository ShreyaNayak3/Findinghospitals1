package com.hospital.qa.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DriverSetup {
	public	static WebDriver driver;
	public static Properties prop;
	public ExtentReports report =ExtentReport.getReportInstance();
	public ExtentTest logger;
	public static String userdir = System.getProperty("user.dir");
	

public DriverSetup() {
        try {
     prop = new Properties();
     FileInputStream ip = new FileInputStream(userdir+"\\src\\main\\java\\com\\hospital\\config1\\congfig1.properties");
                    
             prop.load(ip);
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    }

public static void setProp() {
    
    try {
 prop = new Properties();
 FileInputStream ip = new FileInputStream(userdir+"\\src\\main\\java\\com\\hospital\\config1\\congfig1.properties");
                
         prop.load(ip);
} catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}


}   //***To launch application in chrome browser
    public static void initialization() {
        
    String browserName = prop.getProperty("browser");
        
        if(browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",userdir+"\\src\\main\\resources\\drivers\\chromedriver.exe");
            ChromeOptions options=new ChromeOptions();
            
           options.addArguments("--disable-notification");
            driver=new ChromeDriver();
          
            
        }
        //***To launch application in firefox browser
        else if(browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",userdir+"\\src\\main\\resources\\drivers\\geckodriver.exe");
            FirefoxOptions options=new FirefoxOptions ();
            options.setProfile(new FirefoxProfile());
            options.addPreference("dom.webnotifications.enabled", false);
            driver=new FirefoxDriver();
            
        }  
    
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
        
        driver.get(prop.getProperty("Url"));
        
		return;
    
}

public  void openUrl(String websiteUrlKey) {


driver.get(prop.getProperty(websiteUrlKey));
}

 public void clicker(String Key){
	 if(Key.endsWith("_xpath")){
  		driver.findElement(By.xpath(prop.getProperty(Key))).click();
  	
  }
	 else if(Key.endsWith("_id")){
	  		driver.findElement(By.id(prop.getProperty(Key))).click();
	  		
			}
	  
	 else if(Key.endsWith("_Linktext ")){
	  		driver.findElement(By.linkText(prop.getProperty(Key))).click();
	  		
	 }
	 
	 try {
			Thread.sleep(4000);
	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
 }
public void reportFail(String reportString) {
	System.out.println(reportString);
	logger.log(Status.FAIL,reportString);
		takeScreenShotOnFailure();
		//Assert.fail(reportString);
	}
	

	private void takeScreenShotOnFailure() {
		// TODO Auto-generated method stub
	}
	

	public void reportPass(String reportString) {
		logger.log(Status.PASS,reportString);
	}

}


