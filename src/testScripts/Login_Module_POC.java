package testScripts;

import projectModule.Login_Action;
import utility.Log;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

public class Login_Module_POC {

	public static void main(String[] args) {
		WebDriver driver = null;
		
		DOMConfigurator.configure("log4j.xml");
		
		Log.info("Starting login test case");
		// TODO Auto-generated method stub
		Login_Action.perform(driver);
		
		Log.info("Login to application successful");
	}

}
