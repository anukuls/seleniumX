package projectModule;

import org.testng.Assert;
import pageObjects.Google_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Google_Actions {
	
	public static WebDriverWait myWait;
	
	public static void googleSearch(WebDriver driver, String search_str) {		
		Google_Page.textfield_Search(driver).sendKeys(search_str);
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnG")));
		Google_Page.button_Search(driver).click();
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(search_str)));
		Google_Page.link_Selenium(driver).click();
		String body_text = Google_Page.body_SeleniumPage(driver).getText();
		
		Assert.assertTrue(body_text.contains("Selenium is a suite of tools"));
	}

}
