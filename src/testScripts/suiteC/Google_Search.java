package testScripts.suiteC;

import projectModule.Google_Actions;

import utility.Excel_Utils;
import utility.TestData_Loader;

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
	public Excel_Utils data;
	
	@Parameters({"browser"})
	@BeforeMethod
	public void preScript(String browser) throws Exception {
		DOMConfigurator.configure("log4j.xml");
		
		data = TestData_Loader.loadTestData(this);
		String url = data.getCellData(this, "URL");
		System.out.println("URL is: " + url);
		
		//TODO: 1. Get data from excel in this function, in beforeMethod, Test, AfterMethod
		//2. Get data from excel from any Module
		//3. Bind this class name with Testdata.xlsx
		
		Log.startTC();
		Log.info("Executing precondition...");
		
		System.out.println("browser is :" + browser);
		driver = Common_Actions.openBrowser(browser);
		driver.get(url);
	}
	
	@Test
	public void main() throws Exception {
		Log.info("Executing main method...");
		
		//Here goes the meat of your logic
		String search_string = data.getCellData(this, "Search_String");
		System.out.println("search string is: " + search_string);
		Google_Actions.googleSearch(driver, search_string);
	}
	
	@AfterMethod
	public void postScript() throws Exception {
		Log.info("Executing Postcondition...");
		
		//Here executes your post script
		
		Log.endTC();
	}
	

}
