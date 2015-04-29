package utility;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Properties_Utils;
import pageObjects.Common_Page;

public class Common_Actions {
	public static WebDriver driver = null;
	Excel_Utils excel;
	public String URL, Node;
	static String workingDir = System.getProperty("user.dir");
	public static final String chromedriver_location = workingDir+"\\drivers\\chromedriver.exe";
	public static final String iedriver_location = workingDir+"\\drivers\\IEDriverServer.exe";

	public static WebDriver openBrowser(String browser_name) throws Exception {
		try {
			switch (browser_name.toLowerCase()){
			
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", chromedriver_location);
				driver = new ChromeDriver();
				break;
			default:
				System.setProperty("webdriver.ie.driver", iedriver_location);
				driver = new InternetExplorerDriver();
			}
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;

		} catch (Exception e) {
			throw (e);
		}

	}
	
	public static WebDriver openRemoteBrowser(String browser_name, String node, String port) throws Exception {
		String Node = "http://" + node + ":" + port + "/wd/hub";
		
		try {
			switch (browser_name.toLowerCase()){
			
			case "firefox":								
		        DesiredCapabilities firecap = DesiredCapabilities.firefox();
		        firecap.setBrowserName(browser_name);		        
		        driver = new RemoteWebDriver(new URL(Node), firecap);
				break;
			case "chrome":
				System.out.println("chrome driver location is : " + chromedriver_location);
//				System.setProperty("webdriver.chrome.driver", chromedriver_location);
				System.setProperty("webdriver.chrome.driver", "C:/Users/Smoke/git/seleniumX/drivers/chromedriver.exe");
				DesiredCapabilities chromecap = DesiredCapabilities.chrome();
				
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
//				options.BinaryLocation = 
				
				chromecap.setCapability(ChromeOptions.CAPABILITY, options);
				chromecap.setBrowserName(browser_name);
				driver = new RemoteWebDriver(new URL(Node), chromecap);
				break;
			default:
				System.setProperty("webdriver.ie.driver", iedriver_location);
				driver = new InternetExplorerDriver();
			}
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;

		} catch (Exception e) {
			throw (e);
		}

	}

	public static void launchApplication(WebDriver driver,String url) {
		try {
			
			driver.navigate().to(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void sync_search() {
		try {
			int count = 1;
			Thread.sleep(1000);
			while (Common_Page.text_result(driver).getText()
					.contains("Retrieving Results")) {
				Thread.sleep(1000);
				count += 1;
				if (count > 180) {
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean search(String text) {
		boolean searchExistsFlag = false;
		try {
			WebDriverWait myWait = new WebDriverWait(driver, 30);
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By
					.name("qsearch:search:name")));
			if (text != null) {
				if (Common_Page.textfield_search(driver).isDisplayed()) {
					Common_Page.textfield_search(driver).sendKeys(text);
					sync_search();
					searchExistsFlag = true;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchExistsFlag;

	}
	
	public static String getTitle(){
		String page_title=driver.getTitle();
		return page_title;
		
	}
	
	private static String getSystemIPAddress() {
		// TODO Auto-generated method stub
		String ipAddr = null;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ipAddr = addr.getHostAddress();
			System.out.println(ipAddr);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ipAddr;
	}

}
