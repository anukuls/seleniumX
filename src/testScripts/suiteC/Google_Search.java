package testScripts.suiteC;

import projectModule.Google_Actions;

import org.openqa.selenium.WebDriver;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;

import utility.Log;
import utility.Common_Actions;

public class Google_Search {
	
	//Instance variable driver created to use this across all methods
	public WebDriver driver;
	
	@Parameters({"browser"})
	@BeforeMethod
	public void preScript(String browser) throws Exception {
		DOMConfigurator.configure("log4j.xml");
		
		Log.startTC();
		Log.info("Executing precondition...");
		
		System.out.println("browser is :" + browser);
		driver = Common_Actions.openBrowser(browser);
		driver.get("http://www.google.com");
	}
	
	@Test
	public void main() throws Exception {
		Log.info("Executing main method...");
		
		//Here goes the meat of your logic
		Google_Actions.googleSearch(driver);
	}
	
	@AfterMethod
	public void postScript() throws Exception {
		Log.info("Executing Postcondition...");
		
		//Here executes your post script
		
		Log.endTC();
	}
	

}
