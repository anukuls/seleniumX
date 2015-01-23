package projectModule;

import pageObjects.Home_Page;

import org.openqa.selenium.WebDriver;

public class Home_Action {
	public static void clickLinkSignout(WebDriver driver) {
		Home_Page.link_Signout(driver).click();
	}

}
