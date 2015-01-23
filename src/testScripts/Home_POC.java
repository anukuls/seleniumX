package testScripts;

import projectModule.Home_Action;
import projectModule.Login_Action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home_POC {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = null;
		Login_Action.perform(driver);
		WebDriverWait myWait = new WebDriverWait(driver, 30);
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By
				.linkText("Sign Out")));
		//myWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
		Home_Action.clickLinkSignout(driver);

	}
}