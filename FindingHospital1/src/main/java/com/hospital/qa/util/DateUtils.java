package com.hospital.qa.util;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class DateUtils {
	public DateUtils(WebDriver driver, Properties prop) {
		// TODO Auto-generated constructor stub
	}

	public static String getTimeStamp() {
		Date date=new Date();
		return date.toString().replaceAll(":","_").replaceAll(" ","_");
	}
}
