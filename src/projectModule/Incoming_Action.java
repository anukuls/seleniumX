package projectModule;


import java.util.Random;

import pageObjects.Routing_Page;
import pageObjects.Incoming_Page;
import pageObjects.UserAccounts_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class Incoming_Action {
	public static void navigate_incomingnumbers(WebDriver driver) {
		try {
			Routing_Page.link_Main_Menu_Routing(driver).click();
			Incoming_Page.Submenu_Incomingnumber(driver).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	public static void addincomingnumber(WebDriver driver)
	{
		
		int rannum = new Random().nextInt(1000);
		Incoming_Page.link_Addnewnumber(driver).click();
		WebDriverWait myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("numbers")));
		Incoming_Page.textfield_numbersToAdd(driver).sendKeys(Integer.toString(rannum));
		Select selectElement = new Select(
				Incoming_Page.selectlist_callcenter(driver));
		selectElement.selectByVisibleText("LUE");
		Select selectElement1 = new Select(
				Incoming_Page.selectlist_calltype(driver));
		selectElement1.selectByVisibleText("UPSELL - Live production call");
		
		Select selectElement2 = new Select(
				Incoming_Page.selectlist_campaign(driver));
		selectElement2.selectByVisibleText("test vandana");
		
		Select selectElement21 = new Select(
				Incoming_Page.selectlist_carrier(driver));
		selectElement21.selectByVisibleText("AT&T");
		
		Select selectElement211 = new Select(
				Incoming_Page.selectlist_incomingstatus(driver));
		selectElement211.selectByVisibleText("Active");
		
		Incoming_Page.radio_disabled(driver).click();
		Incoming_Page.radio_noTicket(driver).click();
		Incoming_Page.button_AddnewNumbers(driver).click();
	}
	
}

	
