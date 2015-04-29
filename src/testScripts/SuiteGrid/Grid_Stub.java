package testScripts.SuiteGrid;

//import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import projectModule.Login_Action;
import runManager.Testcase;
import utility.Common_Actions;
import utility.Excel_Utils;
import utility.Log;
import utility.TestData_Loader;

public class Grid_Stub extends Testcase {
	
	//Instance variable driver created to use this across all methods
	public WebDriver driver;
//	public DesiredCapabilities cap;
	public String URL, Node;
	public Excel_Utils data;
    protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@Parameters({"browser","port","node"})
	@BeforeMethod
	public void preScript(String browser, String port, String node) throws Exception {

		data = TestData_Loader.loadTestData(this);
		String URL = data.getCellData(this, "URL");
		
		driver = Common_Actions.openRemoteBrowser(browser, port, node);
		//TODO: Node url to be formed at runtime, this information would be read from the @Parameters tag of the testng.xml
//        String Node = "http://10.32.14.15:5555/wd/hub";
//        String Node = "http://" + node + ":" + port + "/wd/hub";
//        System.out.println("Node URL is: " + Node);
//        System.out.println("Browser of execution is: " + browser);
        
//        if(browser.equals("firefox")) 
//        {
//        	DesiredCapabilities cap = DesiredCapabilities.firefox(); 
//        	cap.setBrowserName(browser);
//        }
//        else
//        {
//        	DesiredCapabilities cap = DesiredCapabilities.chrome();
//        }
        
//        cap.setBrowserName("firefox");
        
        
//        driver = new RemoteWebDriver(new URL(Node), cap);
        // Puts an Implicit wait, Will wait for 10 seconds before throwing exception
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        // Launch website
        driver.navigate().to(URL);
//        driver.manage().window().maximize();
	}

	@Test
	public void main() throws Exception {
		
		String username = data.getCellData(this, "Username");
		String password = data.getCellData(this, "Password");
		
		Login_Action.perform(driver, username, password);
	}

	@AfterMethod
	public void postScript() throws Exception {
//		driver.quit();
	}

}
