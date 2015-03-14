package testScripts.SuiteGrid;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import utility.Common_Actions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import projectModule.Login_Action;
import runManager.Testcase;
import utility.Log;

public class Grid_Stub implements Testcase {
	
	//Instance variable driver created to use this across all methods
	public WebDriver driver;
	public String URL, Node;
    protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@Parameters("browser")
	@BeforeMethod
	public void preScript() throws MalformedURLException {
		//Initialize Logger
		Log.initializeLog();
		Log.startTC();

		String URL = "http://www.google.com";
		System.out.println(" Executing on FireFox");
        String Node = "http://10.32.14.15:5555/wd/hub";
        DesiredCapabilities cap = DesiredCapabilities.firefox();
        cap.setBrowserName("firefox");
        
        driver = new RemoteWebDriver(new URL(Node), cap);
        // Puts an Implicit wait, Will wait for 10 seconds before throwing exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        // Launch website
        driver.navigate().to(URL);
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
		driver.quit();
		Log.endTC();
	}

}
