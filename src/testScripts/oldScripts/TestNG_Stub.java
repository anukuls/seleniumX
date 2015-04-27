package testScripts.oldScripts;

import org.openqa.selenium.WebDriver;

import utility.Common_Actions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import projectModule.Login_Action;
import runManager.Testcase;
import utility.Log;

public class TestNG_Stub extends Testcase {
	
	//Instance variable driver created to use this across all methods
	public WebDriver driver;
	
	@BeforeMethod
	public void preScript() throws Exception {
		//Initialize Logger
		Log.initializeLog();
		Log.startTC();
		
		driver = Common_Actions.openBrowser("chrome");
		Common_Actions.launchApplication(driver, "https://qev147.qa1.liveops.com");
//		Login_Action.perform(driver);
	}

	@Test
	public void main() throws Exception {
		// TODO Auto-generated method stub
		Log.info("executing main logic...");
		
	}

	@AfterMethod
	public void postScript() throws Exception {
		// TODO Auto-generated method stub
		Common_Actions.closeBrowser();
		Log.endTC();
	}

}
