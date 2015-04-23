package testScripts;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import projectModule.Add_Campaign_Action;
import projectModule.Home_Action;
import projectModule.Login_Action;
import utility.Common_Actions;
import utility.Excel_Utils;
import utility.Log;

import org.testng.Assert;
import org.testng.Reporter;


public class TestNG_Routing_POC {
	//Instance variable driver created to use this across all methods
	//Common_Actions action = new Common_Actions();
	
		public WebDriver driver = null;
		
		@BeforeMethod
			public void Login() {
				{
					try {
						DOMConfigurator.configure("log4j.xml");
						driver = Common_Actions.openBrowser("Chrome");

						Excel_Utils excel;
						excel = new Excel_Utils();
						String url = null;
						url = excel.getCellData("TC_01", "URL");
						Common_Actions.launchApplication(driver, url);
//						Login_Action.perform(driver);
						String page_title = Common_Actions.getTitle();
						Assert.assertEquals("Welcome", page_title);
						Log.info("Login Successfull...");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}

		
		
		@Test
		public void main() throws Exception {
			Log.info("Executing main method...");
			Add_Campaign_Action.navigate(driver);
		
			String camp_landingpg_xpath = driver.findElement(By.xpath("//*[@id='bd']/div[2]/h1")).getText();
			Assert.assertTrue(camp_landingpg_xpath.contains("Campaigns: All"));
		    if(camp_landingpg_xpath.contains("Campaigns: All"))
		    {
		    	Reporter.log("Navigating to Campaign page successful");
		    }
   /*       else
		    {
		    	Reporter.log("Could not navigate to Campaign Page.");
		    }
		    
		Log.info("Navigating to campaign page successful");*/
		
		Add_Campaign_Action.Add_New_Campaign(driver);
		String camp_pg_xpath = driver.findElement(By.xpath("//*[@id='bd']/div[2]/h1")).getText();
		Assert.assertTrue(camp_pg_xpath.contains("Campaign Details:"));
	    if(camp_pg_xpath.contains("Campaign Details:"))
	    {
	    	Reporter.log("successful creation of new campaign");
	    }
/*	    else
	    {
	    	Reporter.log("Could not create a new campaign.");
	    }
		Log.info("successful creation of new campaign");*/
		
		Add_Campaign_Action.Add_Pool(driver);
		Log.info("successful Addition of pool");
		Add_Campaign_Action.Add_Offer(driver);
		String offer_pg_src = driver.getPageSource();
		Assert.assertTrue(offer_pg_src.contains("Your changes have been saved."));
	    if(offer_pg_src.contains("Your changes have been saved."))
	    {
	    	Reporter.log("offer and pool has been added to campaign successfully");
	    }
/*	    else
	    {
	    	Reporter.log("Could add offer and pool to the campaign");
	    }
		Log.info("successful addition of new offer");*/
	    
		
		Home_Action.clickLinkSignout(driver);
		String sign_out_text = driver.findElement(By.xpath("//*[@id='bd']/center/div/table/tbody/tr[2]/td/center/p")).getText();
		Assert.assertTrue(sign_out_text.contains("Thanks for visiting. You are now signed out"));
	    if(sign_out_text.contains("Thanks for visiting. You are now signed out"))
	    {
	    	Reporter.log("Sign out from application successful");
	    }
	    /*else
	    {
	    	Reporter.log("Could add offer and pool to the campaign");
	    }
		Log.info("Sign out from application successful"); */
		}
		
		@AfterMethod
		public void postScript() throws Exception {
			Log.info("Executing Postcondition...");
			
			try {
				Common_Actions.closeBrowser();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Log.endTC();
		}

}
