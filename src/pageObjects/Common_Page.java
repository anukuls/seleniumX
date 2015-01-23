package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Common_Page {
	static WebElement element = null;
	public static WebElement textfield_search(WebDriver driver){
		element=driver.findElement(By.name("qsearch:search:name"));
		return element;
	}
	
	public static WebElement text_result(WebDriver driver){
		element=driver.findElement(By.xpath("//*[@id='searchlist:status:search']"));
		return element;
	}
}
