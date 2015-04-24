package projectModule;

import org.testng.Assert;

import pageObjects.Google_Page;
import utility.Element_Not_Found_Exception;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Google_Actions {
	
	public static WebDriverWait myWait;
	
	public static void googleSearch(WebDriver driver, String search_str) throws Exception {		
		Google_Page.textfield_Search(driver).sendKeys(search_str);
		Google_Page.button_Search(driver).click();
		Google_Page.link_Selenium(driver).click();
		String body_text = Google_Page.body_SeleniumPage(driver).getText();
		
		Assert.assertTrue(body_text.contains("Selenium is a suite of tools"));
	}

}
