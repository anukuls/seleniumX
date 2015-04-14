package projectModule;

import pageObjects.Google_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Google_Actions {
	
	public static WebDriverWait myWait;
	
	public static void googleSearch(WebDriver driver) {
		Google_Page.textfield_Search(driver).sendKeys("selenium webdriver");
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnG")));
		Google_Page.button_Search(driver).click();
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Selenium WebDriver")));
		Google_Page.link_Selenium(driver).click();
		String body_text = Google_Page.body_SeleniumPage(driver).getText();
		
		if(body_text.contains("Selenium is a suite of tools")) {
			System.out.println("Selenium text is present");
		}
		else
		{
			System.out.println("Selenium text is not present");
		}
	}

}
