package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Google_Page {
	static WebElement element = null;

	public static WebElement textfield_Search(WebDriver driver){
		element = driver.findElement(By.name("q"));
		return element;
	}
	
	public static WebElement button_Search(WebDriver driver){
		element = driver.findElement(By.name("btnG"));
		return element;
	}
	
	public static WebElement link_Selenium(WebDriver driver){
		element = driver.findElement(By.linkText("Selenium WebDriver"));
		return element;
	}
	
	public static WebElement body_SeleniumPage(WebDriver driver){
		element = driver.findElement(By.cssSelector("body"));
		return element;
	}

}