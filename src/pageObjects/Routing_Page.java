package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Routing_Page {
	
	static WebElement element = null;
	
	public static WebElement link_Signout(WebDriver driver){
		try{
		element =driver.findElement(By.linkText("Sign Out"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	
	}
	
	public static WebElement link_Main_Menu_Routing(WebDriver driver){
		try {
		element = driver.findElement(By.linkText("Routing"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}
	
	public static WebElement link_Sub_Menu_Campaign(WebDriver driver){
		try{
	    element = driver.findElement(By.linkText("Campaigns"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return element;
	}
	
	public static WebElement Button_Add_New_Campaign(WebDriver driver){
		try {
		element = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[1]/table[2]/tbody/tr[1]/td/input[1]"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}
	
	public static WebElement TextBox_Campaign_Name(WebDriver driver){
		try{
	    element = driver.findElement(By.name("description"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return element;
	}
	
	public static WebElement CheckBox_Routing_Class(WebDriver driver){
		try{
		element = driver.findElement(By.xpath("//*[@id='tabcontent:tabpanel:general']/table/tbody/tr[2]/td[2]/label[1]/input"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;			 
	}

	public static WebElement SelectList_Program(WebDriver driver){
		try{
	    element = driver.findElement(By.name("program_id"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return element;
	}
	
	public static WebElement Button_Submit(WebDriver driver){
		try{
		element = driver.findElement(By.id("submit_button_id"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}
	
	public static WebElement Label_AddCampaign(WebDriver driver){
		try{
		element = driver.findElement(By.xpath("//*[@id='-1']/table/tbody/tr[30]/td[1]"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}
	
	public static WebElement Tab_AddPool_Campaign(WebDriver driver){
		try{
	   element = driver.findElement(By.id("tabmid:tabpanel:campaign_pools"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return element;
	
	}
	
	public static WebElement SelectList_PoolList(WebDriver driver){
		try{
		   //element = driver.findElement(By.className("edittable-selectbinding"));
		   element = driver.findElement(By.xpath("//*[@id='pools:edittable:tbody']/tr/td[2]/select"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return element;
		
		}
	
	public static WebElement Tab_AddOffer_Campaign(WebDriver driver){
		try{
		   element = driver.findElement(By.id("tabmid:tabpanel:offers"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return element;
		
		}
	
	public static WebElement link_AddNewOffer_Campaign(WebDriver driver){
		try{
	    element = driver.findElement(By.xpath("//*[@id='-1']/a/strong"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return element;
	}
	
	public static WebElement SelectList_OfferList(WebDriver driver){
		try{
		   element = driver.findElement(By.xpath("//*[@id='offers:edittable:tbody']/tr/td[2]/select"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return element;
		
		}
}
