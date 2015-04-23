package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Google_Page {
	static WebElement element = null;
	public static WebDriverWait myWait;

	public static WebElement textfield_Search(WebDriver driver){
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		element = driver.findElement(By.name("q"));
		return element;
	}
	
	public static WebElement button_Search(WebDriver driver){
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnG")));
		element = driver.findElement(By.name("btnG"));
		return element;
	}
	
	public static WebElement link_Selenium(WebDriver driver){
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Selenium WebDriver")));
		element = driver.findElement(By.linkText("Selenium WebDriver"));
		return element;
	}
	
	public static WebElement body_SeleniumPage(WebDriver driver){
		element = driver.findElement(By.cssSelector("body"));
		return element;
	}

}