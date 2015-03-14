package testScripts.SuiteGrid;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import utility.Common_Actions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import projectModule.Login_Action;
import runManager.Testcase;
import utility.Log;

public class Grid_Stub implements Testcase {
	
	//Instance variable driver created to use this across all methods
	public WebDriver driver;
	
	@BeforeMethod
	public void preScript() throws Exception {
		//Initialize Logger
		Log.initializeLog();
		Log.startTC();

		String node = "http://10.13.14.14:5555/wd/hub";
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setBrowserName("firefox");
		
		driver = new RemoteWebDriver(new URL(node), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.navigate().to("http://www.google.com");
		driver.manage().window().maximize();
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
