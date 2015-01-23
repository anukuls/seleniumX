    package testScripts;
    
    import pageObjects.Login_Page;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.firefox.FirefoxDriver;
    
    public class Login_POC {
    
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    WebDriver driver = new FirefoxDriver();
            driver.get("http://qevc2.qa1.liveops.com");
            Login_Page.textfield_Username(driver).sendKeys("SmokeLOStaff1");
            Login_Page.textfield_Password(driver).sendKeys("password");
            Login_Page.button_SignIn(driver).click();
            driver.close();
    }
    
    }
    
    