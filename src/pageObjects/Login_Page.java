package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login_Page {
	static WebElement element = null;

	public static WebElement textfield_Username(WebDriver driver){
		element = driver.findElement(By.name("username"));
		return element;
	}
	
	public static WebElement textfield_Password(WebDriver driver){
		element = driver.findElement(By.name("password"));
		return element;
	}
	
	public static WebElement button_SignIn(WebDriver driver){
		element = driver.findElement(By.name("button"));
		return element;
	}

}