package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserAccounts_Page {
	static WebElement element = null;
	public static WebElement navigate_Menu_User_Accounts(WebDriver driver){
		element =driver.findElement(By.linkText("User Accounts"));
		return element;
	}
	
	public static WebElement sub_Menu_Edit_Groups(WebDriver driver){
		element=driver.findElement(By.linkText("Edit Users"));
		return element;
	}
	
	public static WebElement button_Add_New_User(WebDriver driver){
		element=driver.findElement(By.xpath("//*[@id='list-container:search']/tr[1]/td/input[1]"));
		return element;
		
	}

	public static WebElement textfield_FirstName(WebDriver driver){
		element=driver.findElement(By.name("firstname"));
		return element;	
	}
	public static WebElement textfield_LastName(WebDriver driver){
		element=driver.findElement(By.name("lastname"));
		return element;	
		
	}
	public static WebElement textfield_UserName(WebDriver driver){
		element=driver.findElement(By.name("username"));
		return element;	
		
	}
	
	public static WebElement textfield_WorkPhone(WebDriver driver){
		element=driver.findElement(By.name("phonenum"));
		return element;	
		
	}
	
	public static WebElement tab_AccountStatus(WebDriver driver){
		element=driver.findElement(By.id("tabmid:tabpanel:accountstatus"));
		return element;		
	}
	
	public static WebElement textfield_TemporaryPswd1(WebDriver driver){
		element=driver.findElement(By.name("newpassword1"));
		return element;		
	}
	
	public static WebElement textfield_TemporaryPswd2(WebDriver driver){
		element=driver.findElement(By.name("newpassword2"));
		return element;		
	}
	
	public static WebElement checkbox_Groups(WebDriver driver){
		element=driver.findElement(By.xpath("//*[@id='-1']/table/tbody/tr[10]/td[2]/label[7]/input"));
		return element;
		
	}
	
	public static WebElement radio_Trained(WebDriver driver){
		element=driver.findElement(By.name("trained"));
		return element;		
	}
	
	public static WebElement selectlist_AccountStatus(WebDriver driver){
		element=driver.findElement(By.name("disable_reason_id"));
		return element;		
	}
	
	public static WebElement radio_Rehired(WebDriver driver){
		element=driver.findElement(By.name("rehire"));
		return element;		
	}
	
	public static WebElement button_SaveChanges(WebDriver driver){
		element=driver.findElement(By.name("-save"));
		return element;		
	}
	
	public static WebElement text_userdetails(WebDriver driver){
		element=driver.findElement(By.xpath("//*[@id='bd']/div[2]/div[2]/span"));
		return element;
	}
	
	public static WebElement link_users(WebDriver driver){
		element=driver.findElement(By.linkText("Users"));
		return element;
	}
	
	public static WebElement table_verifyUser(WebDriver driver){
		element =driver.findElement(By.xpath("//*[@id='list-container:search']/tr[3]/td[3]"));
		return element;
	}
}
