package projectModule;

//import pageObjects.Login_Page;
import pageObjects.Routing_Page;
//import pageObjects.UserAccounts_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;

import java.text.*;
import java.util.*;



public class Add_Campaign_Action {
	
	
	public static void navigate(WebDriver driver) {
    
		Routing_Page.link_Main_Menu_Routing(driver).click();
		Routing_Page.link_Sub_Menu_Campaign(driver).click();
}
	
	public static void Add_New_Campaign(WebDriver driver) {
		
		Add_Campaign_Action camp_obj = new Add_Campaign_Action();
	
		Routing_Page.Button_Add_New_Campaign(driver).click();
		String campaign_name = camp_obj.get_campaign_name();
		Routing_Page.TextBox_Campaign_Name(driver).clear();
		Routing_Page.TextBox_Campaign_Name(driver).sendKeys(campaign_name);
		Select selectElement = new Select(Routing_Page.SelectList_Program(driver));
		selectElement.selectByValue("38216");
		Routing_Page.CheckBox_Routing_Class(driver).click();
		Routing_Page.Button_Submit(driver).click();
		WebDriverWait wait = new WebDriverWait(driver,15);
		//wait.until(ExpectedConditions.presenceOfElementLocated(Routing_Page.Label_AddCampaign(driver)));
		//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='-1']/table/tbody/tr[30]/td[1]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='-1']/table/tbody/tr[30]/td[1]")));		
	
	}
	
	public String get_campaign_name()
	{
		String final_camp_name;
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyHH:mm");
		Date dateobj = new Date();
		String curr_date = df.format(dateobj).replaceAll("[:/]","");
		final_camp_name = "Std_IVR_campaign_" + curr_date;
		return final_camp_name;
	}
	
	public static void Add_Pool(WebDriver driver) {
		Routing_Page.Tab_AddPool_Campaign(driver).click();
		Select selectElement = new Select(Routing_Page.SelectList_PoolList(driver));
		selectElement.selectByValue("383");
		
	}
	
	public static void Add_Offer(WebDriver driver) {
	Routing_Page.Tab_AddOffer_Campaign(driver).click();
	Routing_Page.link_AddNewOffer_Campaign(driver).click();
	Select selectElement = new Select(Routing_Page.SelectList_OfferList(driver));
	selectElement.selectByValue("36674");
	WebDriverWait wait = new WebDriverWait(driver, 150);
	WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit_button_id")));
	e1.click();
	try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
}

