package testScripts;


import projectModule.Incoming_Action;
import projectModule.Login_Action;
import projectModule.Home_Action;
import utility.Common_Actions;
import utility.Log;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

import utility.Common_Actions;
public class Incoming_POC {

	public static void main(String[] args) throws Exception {
		WebDriver driver=null;
//		Common_Actions action;
//		action = new Common_Actions();
		driver = Common_Actions.openBrowser("Firefox");
		Common_Actions.launchApplication(driver, "https://qev189.qa1.liveops.com");
//		Login_Action.perform(driver);
		Incoming_Action.navigate_incomingnumbers(driver);
		Incoming_Action.addincomingnumber(driver);

	}

}
