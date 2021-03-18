package com.hospital.qa.testcases;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.hospital.qa.pages.CorporateWellnessPage;
import com.hospital.qa.pages.DiagnosticPage;
import com.hospital.qa.pages.HomePage;
import com.hospital.qa.util.DriverSetup;
import com.hospital.qa.util.ExcelUtil;

public class HospitalTest extends DriverSetup {
	public static Logger log = LogManager.getLogger(DriverSetup.class.getName());
	
	HomePage base;
	DiagnosticPage cities;
	CorporateWellnessPage cw;
	
	 {
		
	}
	 //***Initialization
	@BeforeTest
	public void setUp() {
		initialization();
		DriverSetup.setProp();
		
	}
	@Test(priority = 0)
	public void testCase() {

		try {
			log.debug("Driver is initialized");
			log.debug("opened the website");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	@Test(priority = 1, dependsOnMethods = "testCase")
	public void displayHospitals() {
		try {
			base = new HomePage(driver, prop);
			Thread.sleep(1000);
			base.locationHospital("location_xpath", "location");
			Thread.sleep(500);
			base.locationHospital("hospital_xpath", "hos");
			log.debug("entered hospital in textbox ");

			//***clicked on 'open 24/7' checkbox 
			
			clicker("checkbox_xpath");
			log.debug("clicked on 'open 24/7' checkbox");

			base.drop("dropdown_xpath", "parking_xpath");
			log.debug("click on drop down");

			base.noOfPages("page_xpath", "pageStart", "pageEnd");
			ArrayList<String> HosRat1 = base.HosRat;
			ExcelUtil.writeExcelAllDEtails("Hospitals With Ratings", HosRat1);
			log.debug(" Hospital names with rating  displayed on hospitals.xlsx");
		} catch (Exception e) {
			
		}
	}
	
		@Test(priority = 2, dependsOnMethods = "displayHospitals")
		public void goToTopCity() {

		//	logger = report.createTest("Display top cities");
			try {
			
				clicker("dia_xpath");
				//***Clicked on  diagnostics

				cities = new DiagnosticPage(driver, prop);
				String data = cities.getTopCites("cities_xpath");
				ExcelUtil.writeExcelAllDEtailscity("Top cities", data);
				log.debug("Top cities are displayed in top cities ");

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 3)
		public void clickCorporateWellness() {
			try {
				clicker("bangalore_xpath");
				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");
			    cw = new CorporateWellnessPage(driver, prop);
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");
				cw.enterText("name_Id", "name");
				log.debug("Entered valid name");
				cw.enterText("organisation_id", "org_name");
				log.debug("Entered valid organization name");
				cw.enterText("mail_id", "email");
				log.debug("Entered invalid email");
				cw.enterText("phone_id", "phone_no");
				log.debug("Entered valid phone number");
				clicker("confirm_id");
				log.debug("Clicked on submit button");

				ArrayList<String> cc = cw.alertMsg();
				Iterator<String> itr = cc.iterator();
				
				if (itr.hasNext()) {
					ExcelUtil.writeExcelAlertMsg("alert", itr.next());
				}
				log.debug("The alert message is displayed in AlertMSG.xlsx");
				
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}

		@Test(priority = 4)
		public void InvalidCorporateWellness() {
			testCase();
			try {

				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "invalidName");
				log.debug("Entered valid name");

				cw.enterText("organisation_id", "invalidorg_name");
				log.debug("Entered valid organization name");

				cw.enterText("mail_id", "email");
				log.debug("Entered invalid email");

				cw.enterText("phone_id", "invalidphone_no");
				log.debug("Entered valid phone number");

				log.debug("Clicked on submit button");
				cw.accept();

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 5)
		public void nullInvalidForm() {
			testCase();
			try {

				clicker("Arrow_xpath");
				clicker("corporate_xpath");
			    cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "null");
				log.debug("Entered null name");

				cw.enterText("organisation_id", "null");
				log.debug("Entered null organization name");

				cw.enterText("mail_id", "null");
				log.debug("Entered null email");

				cw.enterText("phone_id", "null");
				log.debug("Entered null phone number");

				clicker("confirm_id");
				log.debug("Clicked on submit button");

				cw.accept();
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 6)
		public void InvalidNameInCw() {
			testCase();
			try {

				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "numname");
				log.debug("Entered invalid name");

				cw.enterText("organisation_id", "org_name");
				log.debug("Entered valid organization name");

				cw.enterText("mail_id", "mail");
				log.debug("Entered valid email");

				cw.enterText("phone_id", "phone_no");
				log.debug("Entered valid phone number");
				clicker("confirm_id");
				log.debug("Clicked on submit button");
				try {
					Thread.sleep(300);
				} catch (Exception e) {

				}
				cw.accept();

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 7)
		public void InvalidOrgNameCw() {
			testCase();
		
			try {

				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");

			    cw = new CorporateWellnessPage(driver, prop);
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "name");
				log.debug("Entered valid name");

				cw.enterText("organisation_id", "numorg");
				log.debug("Entered valid organization name");

				cw.enterText("mail_id", "mail");
				log.debug("Entered valid email");

				cw.enterText("phone_id", "phone_no");
				log.debug("Entered valid phone number");

				clicker("confirm_id");
				log.debug("Clicked on submit button");
				try {
					Thread.sleep(300);
				} catch (Exception e) {

				}
				cw.accept();

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}

		@Test(priority = 8)
		public void InvalidPhoneNoCw() {
			testCase();
			try {

				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");

			   cw = new CorporateWellnessPage(driver, prop);
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "name");
				log.debug("Entered valid name");

				cw.enterText("organisation_id", "org_name");
				log.debug("Entered valid organization name");

				cw.enterText("mail_id", "mail");
				log.debug("Entered valid email");

				cw.enterText("phone_id", "alphaphone");
				log.debug("Entered invalid phone number");

				clicker("confirm_id");
				log.debug("Clicked on submit button");
				cw.accept();

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 9)
		public void specCharNameInCw() {
			testCase();
			try {
				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "specialchar");
				log.debug("Entered valid name");

				cw.enterText("organisation_id", "org_name");
				log.debug("Entered valid organization name");

				cw.enterText("mail_id", "mail");
				log.debug("Entered valid email");

				cw.enterText("phone_id", "phone_no");
				log.debug("Entered valid phone number");

				clicker("confirm_id");
				log.debug("Clicked on submit button");
				try {
					Thread.sleep(300);
				} catch (Exception e) {

				}
				cw.accept();

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 10)
		public void specOrgNameCw() {
			testCase();
			try {

				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");

			   cw = new CorporateWellnessPage(driver, prop);
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "name");
				log.debug("Entered valid name");

				cw.enterText("organisation_id", "specialchar");
				log.debug("Entered valid organization name");

				cw.enterText("mail_id", "mail");
				log.debug("Entered valid email");

				cw.enterText("phone_id", "phone_no");
				log.debug("Entered valid phone number");

				clicker("confirm_id");
				log.debug("Clicked on submit button");
				try {
					Thread.sleep(300);
				} catch (Exception e) {

				}

				cw.accept();

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 11)
		public void charPhoneNo() {
			testCase();
			try {

				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "name");
				log.debug("Entered valid name");

				cw.enterText("organisation_id", "org_name");
				log.debug("Entered valid organization name");

				cw.enterText("mail_id", "mail");
				log.debug("Entered valid email");

				cw.enterText("phone_id", "charphone");
				log.debug("Entered invalid phone number");

				clicker("confirm_id");
				log.debug("Clicked on submit button");

				cw.accept();

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 12)
		public void alphaNumericPhoneNo() {
			testCase();
			try {

				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "name");
				log.debug("Entered valid name");

				cw.enterText("organisation_id", "org_name");
				log.debug("Entered valid organization name");

				cw.enterText("mail_id", "mail");
				log.debug("Entered valid email");

				cw.enterText("phone_id", "mixofapha");
				log.debug("Entered invalid phone number");

				clicker("confirm_id");
				log.debug("Clicked on submit button");

				cw.accept();

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}

		@Test(priority = 13)
		public void wrongFormatPhoneNo() {
			testCase();
			try {

				clicker("Arrow_xpath");
				clicker("corporate_xpath");
				log.debug("Clicked on corporate wellness");
				cw.windowsHandle();
				log.debug("Clicked on corporate wellness form");

				cw.enterText("name_Id", "name");
				log.debug("Entered valid name");

				cw.enterText("organisation_id", "org_name");
				log.debug("Entered valid organization name");

				cw.enterText("mail_id", "mail");
				log.debug("Entered valid email");

				cw.enterText("phone_id", "wrongnumber");
				log.debug("Entered invalid phone number");

				clicker("confirm_id");
				log.debug("Clicked on submit button");
				cw.accept();

			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}

		@Test(priority = 14)
		public void checkTitle() {
			try {
				
				base.checkTitle("symbol");
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 15)
		public void invalidDisplayLoc() {
			testCase();
		
			try {
				base.locationHospital("location_xpath", "location");
				Thread.sleep(1000);
				base.locationHospital("hospital_xpath", "hos");
				log.debug("entered hospital in textbox ");

			} catch (Exception e) {
				reportFail(e.getMessage());

			}
		}
		@Test(priority = 16)
		public void invalidHospitalName() {
			testCase();
			try {
				Thread.sleep(1000);
				base.locationHospital("location_xpath", "invalidLoc");
				Thread.sleep(1000);
				base.locationHospital("hospital_xpath", "invalidHos");
				log.debug("entered hospital in textbox ");

			} catch (Exception e) {
				reportFail(e.getMessage());

			}
		}
		@Test(priority = 17)
		public void testHospitalTextbox() {
			try {
				testCase();
				base.checks("check");
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 18)
		public void testCheckBox() {
			try {
				Thread.sleep(500);
				base.locationHospital("hospital_xpath", "hos");
				base.checkboxText("checkbox");
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 19)
		public void testDropdownText() {
			try {
				base.dropdownText("droptext");
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		@Test(priority = 20)
		public void hasParkingTest() {
			try {
				base.hasParkingText("hasparking");
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
		public void reportFail(String message) {
			// TODO Auto-generated method stub
			System.out.println(message);
		}
	
	@AfterTest
		public void closeBrower() {
			cw.closeBrowser();
		}

		@AfterMethod
		public void flush() {
			report.flush();

	}
}
