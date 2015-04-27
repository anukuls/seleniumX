package testScripts.oldScripts;

import projectModule.Login_Action;
import utility.Log;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

import utility.Properties_Utils;

public class Login_Module_POC {

	public static void main(String[] args) {
		WebDriver driver = null;
		
		DOMConfigurator.configure("log4j.xml");
		
		Log.info("Starting login test case");
		// TODO Auto-generated method stub
		
		String workingDir = System.getProperty("user.dir");
		String path = workingDir+"\\src\\config\\testdata.properties";
		String username = Properties_Utils.get_property(path, "username"); 
		Login_Action.perform(driver, username, "password");
		
		Log.info("Login to application successful");
	}

}
