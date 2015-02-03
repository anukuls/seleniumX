package runManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utility.Custom_Reporter;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;
import java.util.Properties;

public class Base_Driver {
	
	String[] scriptsArr;
	static Properties mailServerProperties;
//	static Session getMailSession;
//	static MimeMessage generateMailMessage;
	
	public static TestNG createTestNGXML(HashMap<String, ArrayList<String>> suites_arr) {
		
		System.out.println(suites_arr);
		
		//Create an instance on TestNG
		TestNG myTestNG = new TestNG();
		
		//Create an instance of XML Suite and assign a name for it.
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("Test Suites");
		
		//<suite>
		
		//Create a list of XmlTests and add the Xmltest you created earlier to it.
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		
		for (String suite : suites_arr.keySet()) {
			
			//Create an instance of XmlTest and assign a name for it.
			XmlTest myTest = new XmlTest(mySuite);
			myTest.setName(suite);
			
			//<suite>
			//   <test>
			
			List<XmlClass> myClasses = new ArrayList<XmlClass>();			
			for (String script : suites_arr.get(suite)) {				
				//Create a list which can contain the classes that you want to run.				
				myClasses.add(new XmlClass(script));
			}
			
			//<suite>
			//   <test>
			//      <classes>
			//			<class>testScripts.SuiteC.TestNG_POC</class>
			//			<class>testScripts.SuiteC.TestNG_POC2</class>
			//      </classes>
			
			//Assign that to the XmlTest Object created earlier.
			myTest.setXmlClasses(myClasses);
			
			//<suite>
			//   <test>
			//      <classes>
			//			<class>testScripts.SuiteC.TestNG_POC</class>
			//			<class>testScripts.SuiteC.TestNG_POC2</class>
			//      </classes>
			//	</test>
			
			myTests.add(myTest);
		}
		
		//add the list of tests to your Suite.
		mySuite.setTests(myTests);
		
		//Add the suite to the list of suites.
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);
		
		System.out.println(mySuites);
		
		List<Class> listenerClass = new ArrayList<Class>();
		listenerClass.add(utility.Custom_Reporter.class);
				
		myTestNG.setListenerClasses(listenerClass);
				
		//Set the list of Suites to the testNG object you created earlier.
		myTestNG.setXmlSuites(mySuites);
		
		return myTestNG;
	}
	
	public static void executeSuite(HashMap<String, ArrayList<String>> suites_array) {
		TestNG myTestNGSuites = createTestNGXML(suites_array);		
		//invoke run() - this will run your testng xml as a total suite.
		myTestNGSuites.run();		
	}
	
	//TODO: to read sender's list from an xml file
//	public static void sendEmail() throws AddressException, MessagingException {
//		System.out.println("\n 1st ===> setup Mail Server Properties..");
//		mailServerProperties = System.getProperties();
//		mailServerProperties.put("mail.smtp.port", "587");
//		mailServerProperties.put("mail.smtp.auth", "true");
//		mailServerProperties.put("mail.smtp.starttls.enable", "true");
//		System.out.println("Mail Server Properties have been setup successfully..");
//		
//		System.out.println("\n\n 2nd ===> get Mail Session..");
////		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
//		getMailSession = Session.getDefaultInstance(mailServerProperties, new javax.mail.Authenticator(){
//			protected PasswordAuthentication getPasswordAuthentication() {
//	            return new PasswordAuthentication(
//	                "anukul.singhal@gmail.com", "");// Specify the Username and the PassWord
//	        }
//		});
//		generateMailMessage = new MimeMessage(getMailSession);
//		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("sanukul@liveops.com"));
//		generateMailMessage.setSubject("Greetings from Slayer..");
//		String emailBody = "Test email by Slayer. " + "<br><br> Regards, <br>Slayer";
//		generateMailMessage.setContent(emailBody, "text/html");
//		System.out.println("Mail Session has been created successfully..");
//		
//		System.out.println("\n\n 3rd ===> Get Session and Send mail");
//		Transport transport = getMailSession.getTransport("smtp");
//		transport.connect("smtp.gmail.com", 587, "anukul.singhal@gmail.com", "");
//		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
//		transport.close();
//	}

	public static void main(HashMap<String, ArrayList<String>> args) {
		// TODO Auto-generated method stub
		
		//TODO: to fetch this ArrayList as cmd line argument from Automation Manager
		HashMap<String, ArrayList<String>> suite_script_hash = args;
		
//		HashMap<String, String[]> suite_script_hash = new HashMap<String, String[]>();
//		String[] test = {"testScripts.suiteC.TestNG_POC", "testScripts.suiteC.TestNG_POC2"};
//		String[] test1 = {"testScripts.suiteD.TestNG_Stub"};
//		suite_script_hash.put("SuiteC", test);
//		suite_script_hash.put("SuiteD", test1);
//		createTestNGXML(suite_script_hash);
		
//		System.out.println(suite_script_hash);
		executeSuite(suite_script_hash);
//		sendEmail();
	}

}
