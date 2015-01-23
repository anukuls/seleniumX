package projectModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Random;

import pageObjects.UserAccounts_Page;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//import pageObjects.Home_Page;
//import pageObjects.UserAccounts_Page;

public class UserAccounts_Action {
	public static void navigate(WebDriver driver) {
		try {
			UserAccounts_Page.navigate_Menu_User_Accounts(driver).click();
			UserAccounts_Page.sub_Menu_Edit_Groups(driver).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static String addUser(WebDriver driver) {
		// Select selectElement=null;
		String fname = null;
		try {
			
			//int rannum = new Random().nextInt(100000);
			DateFormat df = new SimpleDateFormat("dd/MM/yyHH:mm");
			Date dateobj = new Date();
			String curr_date = df.format(dateobj).replaceAll("[:/]","");
			fname = "User" + curr_date;
			UserAccounts_Page.button_Add_New_User(driver).click();
			UserAccounts_Page.textfield_FirstName(driver).sendKeys(fname);
			UserAccounts_Page.textfield_LastName(driver).sendKeys("lname");
			UserAccounts_Page.textfield_UserName(driver).sendKeys(fname);
			UserAccounts_Page.textfield_WorkPhone(driver).sendKeys(
					"14083556083");
			UserAccounts_Page.tab_AccountStatus(driver).click();
			UserAccounts_Page.textfield_TemporaryPswd1(driver).sendKeys(
					"info123$");
			UserAccounts_Page.textfield_TemporaryPswd2(driver).sendKeys(
					"info123$");
			UserAccounts_Page.checkbox_Groups(driver).click();
			UserAccounts_Page.radio_Trained(driver).click();
			UserAccounts_Page.radio_Rehired(driver).click();
			Select selectElement = new Select(
					UserAccounts_Page.selectlist_AccountStatus(driver));
			selectElement.selectByValue("0");
			UserAccounts_Page.button_SaveChanges(driver).click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return fname;

	}

}
