package testScripts.oldScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

import java.lang.*;

//import com.thoughtworks.selenium.Wait;

import utility.Common_Actions;
import utility.Log;
import utility.Popup_Utils;

public class Demo_Popup {

	/**
	 * @param args
	 * @throws Exception
	 */

	static WebDriver driver = null;

	@BeforeMethod
	public void preScript() throws Exception {
		Log.initializeLog();
		Log.startTC();
		driver = Common_Actions.openBrowser("chrome");
	}

	@Test
	public static void main() throws Exception {
		// TODO Auto-generated method stub

		// For alert box:

		
/*		  driver.get("http://www.w3schools.com/js/tryit.asp?filename=tryjs_alert"); 
		  Reporter.log("Page opens up correctly");
		  Log.info("Page opens up correctly");
		  driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='iframeResult']"))); 
		  driver.findElement(By.tagName("button")).click(); 
		  Reporter.log("alert is present"); Popup.acceptAlert(driver);*/
		 

		// Popup.dismiss(driver);
		// Popup.acceptAlert(driver);

		// Getting alert text
		 //String alert_text = Popup.getText(driver);
		 //System.out.println("alert text is: "+alert_text);

		// For confirm box:

		/*
		 * driver.get("http://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm"
		 * ); driver.switchTo().frame(driver.findElement(By.xpath(
		 * "//*[@id='iframeResult']")));
		 * driver.findElement(By.tagName("button")).click();
		 */
		// Popup.acceptAlert(driver);
		// Popup.dismiss(driver);
		// String alert_text = Popup.getText(driver);
		// System.out.println("alert text is: "+alert_text);

		// For Prompt Box

		/*
		 * driver.get("http://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt"
		 * ); driver.switchTo().frame(driver.findElement(By.xpath(
		 * "//*[@id='iframeResult']")));
		 * driver.findElement(By.tagName("button")).click();
		 */
		// Popup.dismiss(driver); String alert_text = Popup.getText(driver);
		// System.out.println("alert text is: "+alert_text);

		// Popup.dismiss(driver);
		// Popup.sendText("Ekta Arora", driver);
		// Popup.acceptAlert(driver);

	}

	@AfterMethod
	public void postScript() {
		Common_Actions.closeBrowser();
		Log.endTC();
	}

}
