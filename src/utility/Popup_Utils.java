package utility;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Popup_Utils {

	public static Alert alert;

	public static void dismiss(WebDriver driver) throws NoAlertPresentException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
				throw new NoAlertPresentException("No Alert is present");
			} else {
				alert = driver.switchTo().alert();
				alert.dismiss();
			}
		} catch (NoAlertPresentException e) {
			System.out.println("No Alert is present");
			e.printStackTrace();
		}

	}

	public static void acceptAlert(WebDriver driver)
			throws NoAlertPresentException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
				throw new NoAlertPresentException("No Alert is present");
			} else {
				alert = driver.switchTo().alert();
				alert.accept();
			}
		} catch (NoAlertPresentException e) {
			System.out.println("No Alert is present");
			e.printStackTrace();
		}
	}

	public static String getText(WebDriver driver)
			throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
				throw new NoAlertPresentException("No Alert is present");
			} else {
				alert = driver.switchTo().alert();	
			}
			return (alert.getText());
		}
		catch (Exception e){
		System.out.println("No Alert is present");
		e.printStackTrace();
		}
		return null;
		
	}

	public static void sendText(String alert_text, WebDriver driver)
			throws NoAlertPresentException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
				throw new NoAlertPresentException("No Alert is present");
			} else {
				alert = driver.switchTo().alert();
				alert.sendKeys(alert_text);
			}
		} catch (NoAlertPresentException e) {
			System.out.println("No Alert is present");
			e.printStackTrace();
		}
	}

}
