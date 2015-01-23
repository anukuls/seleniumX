package testScripts;
import runManager.Testcase;
import projectModule.UserAccounts_Action;
import projectModule.Login_Action;
import projectModule.Home_Action;
import utility.Excel_Utils;
import utility.Log;
import pageObjects.UserAccounts_Page;
import org.openqa.selenium.WebDriver;
import utility.Common_Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.Reporter;

public class User_Accounts_POC implements Testcase {

	WebDriver driver = null;

	@BeforeMethod
	public void preScript() throws Exception{
		{
			try {
				Log.initializeLog();
				Log.startTC();
				driver = Common_Actions.openBrowser("");

				Excel_Utils excel;
				excel = new Excel_Utils();
				String url = null;
				url = excel.getCellData("TC_01", "URL");
				Common_Actions.launchApplication(driver, url);
				Login_Action.perform(driver);
				String page_title = Common_Actions.getTitle();
				Assert.assertEquals("Welcome", page_title);
				Reporter.log("Login Successfull.");
				Log.info("Login Successfull...");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Test
	public void main() {
		UserAccounts_Action.navigate(driver);
		Reporter.log("Navigate to Edit Users successful");
		Log.info("Navigate to Edit Users successful");
		String username = UserAccounts_Action.addUser(driver);
		String user_saved = UserAccounts_Page.text_userdetails(driver)
				.getText();
		Assert.assertTrue(user_saved.contains(username));
		Reporter.log("User Added Successfully");
		Log.info("User Added Successfully");
		UserAccounts_Page.link_users(driver).click();
		Common_Actions.search(username);
		String user_search_text = UserAccounts_Page.table_verifyUser(driver)
				.getText();
		Assert.assertTrue(user_search_text.contains(username));
		Reporter.log("User Searched Successfully.");
		Log.info("User Searched Successfully.");
	}

	@AfterMethod
	public void postScript() throws Exception {
		try {
			Home_Action.clickLinkSignout(driver);
			Common_Actions.closeBrowser();
			Log.endTC();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
