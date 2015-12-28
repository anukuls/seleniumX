package utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;

public class Screenshot {
          
	static File screenshot = null;
	static String workingDir = System.getProperty("user.dir");
	public static final String chromedriver_location = workingDir+"\\drivers\\chromedriver.exe";
	
	public static void getScreenshotForFailure(WebDriver driver) {	
		System.setProperty("webdriver.chrome.driver", chromedriver_location);
		   WebDriver augmentedDriver = new Augmenter().augment(driver);
	       screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
		
	}
	
	public static File saveScreenshot(String dest_path) throws IOException{
		File finalscreenshot = new File(dest_path);
	    org.apache.commons.io.FileUtils.copyFile(screenshot, finalscreenshot);
	    return finalscreenshot;
	} 
}
