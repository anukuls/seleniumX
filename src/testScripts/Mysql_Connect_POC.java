package testScripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import projectModule.Home_Action;
import projectModule.Login_Action;
import utility.Common_Actions;
import utility.Excel_Utils;
import utility.Log;
import utility.Mysql_Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql_Connect_POC {
	WebDriver driver = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@BeforeMethod
	public void preScript() throws Exception {
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
	public void main() throws SQLException {
		try {
			String masterdb = "qev188.qa1.liveops.com";
			String database = "ccconf";
			String url = "jdbc:mysql://" + masterdb + ":3306/" + database;
			System.out.println(url);
			String user = "ccconf";
			String password = "ccconf_dbctlusr";
			Connection conn = Mysql_Connector.getDBConn(url, user, password);
			pst = conn
					.prepareStatement("Select * from rep where username='Cindy3' AND (disabled != 0 OR termination_id != 0)");
			rs = pst.executeQuery();
			if (rs.next()) {
				pst.executeUpdate("update rep set disabled=0, termination_id=0 where username='Cindy3'");
				System.out.println("PASSED");
				Log.info("Mysql Update Successfull");
			}
			Reporter.log("Mysql Connection made Successfully.");
			Log.info("Mysql Connection made Successfully");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			Mysql_Connector.closeConnection();
		}

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
