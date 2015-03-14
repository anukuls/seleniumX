package testScripts;

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

public class Grid_POC {
	
	static public WebDriver driver;
    public String URL, Node;
    protected ThreadLocal<RemoteWebDriver> threadDriver = null;
    
    @Parameters("browser")
    @BeforeTest
    public static void launchapp() throws MalformedURLException
    {
       String URL = "http://www.google.com";
       
//       if (browser.equalsIgnoreCase("firefox"))
//       {
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
//       }
    }
    
    public static void main(String[] args) {
    	try {
			launchapp();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
