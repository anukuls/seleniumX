package projectModule;

import pageObjects.Login_Page;
import utility.Excel_Utils;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class Login_Action {

	public static void perform(WebDriver driver, String username, String password ) {
		//WebDriver driver = new FirefoxDriver();
		Excel_Utils excel;
		try {
			excel = new Excel_Utils();

			//String url = excel.getCellData("TC_01", "URL");
			String user = excel.getCellData("TC_01", "Username");
			String pass = excel.getCellData("TC_01", "Password");
			
			//driver.navigate().to(url);
			Login_Page.textfield_Username(driver).sendKeys(username);
			Login_Page.textfield_Password(driver).sendKeys(password);
			Login_Page.button_SignIn(driver).click();
			// driver.close();
			//return driver;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
		}
	}

}
