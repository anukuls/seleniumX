package pageObjects;

import utility.Element_Not_Found_Exception;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Google_Page {
	static WebElement element = null;
	public static WebDriverWait myWait;

	public static WebElement textfield_Search(WebDriver driver) throws Exception{
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		try
		{
			element = driver.findElement(By.name("q"));
		}
		catch (NoSuchElementException e) 
		{
			throw new Element_Not_Found_Exception("Unable to locate Search text field", e);
		}
		
		return element;
	}
	
	public static WebElement button_Search(WebDriver driver) throws Exception{
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnG")));
		try
		{
			element = driver.findElement(By.name("btnG"));
		}
		catch (NoSuchElementException e) 
		{
			throw new Element_Not_Found_Exception("Unable to locate Search button", e);
		}
		
		return element;
	}
	
	public static WebElement link_Selenium(WebDriver driver) throws Exception{
		myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Selenium WebDriver")));
		try
		{
			element = driver.findElement(By.linkText("Selenium WebDriver"));
		}
		catch (NoSuchElementException e) 
		{
			throw new Element_Not_Found_Exception("Unable to locate Selenium Webdriver link", e);
		}
		
		return element;
	}
	
	public static WebElement body_SeleniumPage(WebDriver driver){
		element = driver.findElement(By.cssSelector("body"));
		return element;
	}

}