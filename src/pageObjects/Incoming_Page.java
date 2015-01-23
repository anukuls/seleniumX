package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Incoming_Page {
	static WebElement element = null;
	public static WebElement Submenu_Incomingnumber(WebDriver driver){
		try {
		element =driver.findElement(By.linkText("Incoming #s"));
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return element;
	}




public static WebElement link_Addnewnumber(WebDriver driver){
	try {
	element =driver.findElement(By.linkText("Add New Numbers"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}


public static WebElement textfield_numbersToAdd(WebDriver driver){
	try {
	element =driver.findElement(By.id("numbers"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}


public static WebElement selectlist_campaign(WebDriver driver){
	try {
	element =driver.findElement(By.id("campaign_id"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}

public static WebElement selectlist_callcenter(WebDriver driver){
	try {
	element =driver.findElement(By.id("callcenter_id"));	
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}

public static WebElement selectlist_calltype(WebDriver driver){
	try {
	element =driver.findElement(By.name("calltype_id"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}

public static WebElement radio_disabled(WebDriver driver){
	try {
	element =driver.findElement(By.xpath("//*[@id='bd']/div[2]/form/table/tbody/tr/td/table/tbody/tr[5]/td[2]/label[2]/input"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}

public static WebElement selectlist_carrier(WebDriver driver){
	try {
	element =driver.findElement(By.name("carrier_id"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}

public static WebElement selectlist_incomingtype(WebDriver driver){
	try {
	element =driver.findElement(By.name("incomingtype_id"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}


public static WebElement selectlist_owner(WebDriver driver){
	try {
	element =driver.findElement(By.name("owner_id"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}


public static WebElement selectlist_incomingstatus(WebDriver driver){
	try {
	element =driver.findElement(By.name("incomingstatus_id"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}

public static WebElement radio_noTicket(WebDriver driver){
	try {
	element =driver.findElement(By.xpath("//*[@id='no_ticket']"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}


public static WebElement button_AddnewNumbers(WebDriver driver){
	try {
	element =driver.findElement(By.name("add_numbers"));
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return element;
}



}
