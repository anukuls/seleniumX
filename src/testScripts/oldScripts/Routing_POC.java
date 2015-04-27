package testScripts.oldScripts;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;



//import pageObjects.Login_Page;
//import pageObjects.Routing_Page;
import projectModule.Add_Campaign_Action;
import projectModule.Home_Action;
import projectModule.Login_Action;
import utility.Common_Actions;
import utility.Excel_Utils;
import utility.Log;

import org.testng.Assert;


public class Routing_POC {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = null;
		DOMConfigurator.configure("log4j.xml");
		Log.info("Starting login test case");
		
		try {
		driver = Common_Actions.openBrowser("Chrome");
		Excel_Utils excel;
		excel = new Excel_Utils();
		String url = null;
		url = excel.getCellData("TC_01", "URL");
		Common_Actions.launchApplication(driver, url);
//		Login_Action.perform(driver);
		String page_title = Common_Actions.getTitle();
		Assert.assertEquals("Welcome", page_title);
		Log.info("Login Successfull...");;
		
	    Add_Campaign_Action.navigate(driver);
	    Log.info("Navigating to routing page successful");
	    Add_Campaign_Action.Add_New_Campaign(driver);
	    Log.info("successful creation of new campaign");
	    Add_Campaign_Action.Add_Pool(driver);
	    Log.info("successful Addition of pool");
	    Add_Campaign_Action.Add_Offer(driver);
	    Log.info("successful addition of new offer");
	    Home_Action.clickLinkSignout(driver);
	    Log.info("Sign out from application successful");
	    Common_Actions.closeBrowser();
		}catch (Exception e)
		{			
		e.printStackTrace();
		}
	
  }

}
