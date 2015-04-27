package testScripts.oldScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utility.Log;
import utility.Project_Constants;

public class TestNG_POC {
	
	//Instance variable driver created to use this across all methods
	public WebDriver driver;
	
	@BeforeMethod
	public void preScript() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		
		Log.startTC();
		Log.info("Executing precondition...");
		
		String chromedriver_path = Project_Constants.chromedriver_location;
		System.setProperty("webdriver.chrome.driver", chromedriver_path);
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		
		//Add precondition to get the driver object here
	}
	
	@Test
	public void main() throws Exception {
		Log.info("Executing main method...");
		
		//Here goes the meat of your logic
	}
	
	@AfterMethod
	public void postScript() throws Exception {
		Log.info("Executing Postcondition...");
		
		//Here executes your post script
		
		Log.endTC();
	}
	

}
