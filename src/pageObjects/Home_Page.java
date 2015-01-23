package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home_Page {

	static WebElement element = null;

	public static WebElement link_Signout(WebDriver driver) {
		element = driver.findElement(By.linkText("Sign Out"));
		return element;
	}

}
