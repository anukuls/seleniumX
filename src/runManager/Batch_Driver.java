package runManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import utility.Properties_Utils;
import utility.CustomReportListener;

public class Batch_Driver {
	
	public static void executeTestNGSuite(String suitepath) {
		TestNG testng = new TestNG();
	    List<String> suites = Lists.newArrayList();
	    suites.add(suitepath);
	    testng.setTestSuites(suites);
	    List<Class> listenerClass = new ArrayList<Class>();
		listenerClass.add(utility.CustomReportListener.class);
		testng.setListenerClasses(listenerClass);
	    testng.run();
	}
	
	// Pass the output report path, parse and get the failures, and return the 
	//failure classes to be rerun
	public static ArrayList<String> getFailures() {
		ArrayList<String> getFailureClassNames = new ArrayList<>();
		getFailureClassNames = CustomReportListener.getFailureClasses();
		System.out.println("Failure Class Names are : " + getFailureClassNames);
		return getFailureClassNames;
	}	
	
	public static TestNG formTestNGFailureCases(ArrayList<String> failureArray) {
		TestNG myTestNG = new TestNG();
		
		//Create an instance of XML Suite and assign a name for it.
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("Test Suites");
		
		//<suite>
		
		//Create a list of XmlTests and add the Xmltest you created earlier to it.
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		
//		for (String suite : failureArray) {
			
			//Create an instance of XmlTest and assign a name for it.
		XmlTest myTest = new XmlTest(mySuite);
		myTest.setName("suite1");
			
		Map<String,String> testngParams = new HashMap<String,String> ();
			
		//TODO: access browser param from the original testng xml, hard coding for now
		String browser = "chrome";
//			if (browser == "") {
//				browser = "firefox";
//			}
			
		System.out.println("Executing test case on :" + browser);
			
		testngParams.put("browser", browser);
		myTest.setParameters(testngParams);
			
		//<suite>
		//   <test>
			
		List<XmlClass> myClasses = new ArrayList<XmlClass>();			
		for (String script : failureArray) {		
			System.out.println("Script name is: " + script);
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
//		}
		
		//add the list of tests to your Suite.
		mySuite.setTests(myTests);
		
		//Add the suite to the list of suites.
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);
		
		System.out.println(mySuites);
		
//				List<Class> listenerClass = new ArrayList<Class>();
//				listenerClass.add(utility.Custom_Reporter.class);
//						
//				myTestNG.setListenerClasses(listenerClass);
//				myTestNG.addListener(listenerClass);
				
		//Set the list of Suites to the testNG object you created earlier.
		myTestNG.setXmlSuites(mySuites);
		
		return myTestNG;
	}
	
	public static void rerunFailures(ArrayList<String> failures) {
		System.out.println("Rerunning Failures");
		TestNG myTestNGSuites = formTestNGFailureCases(failures);
		//TODO: Need to create a new report after rerunning failures, merge original with the rerun report
		List<Class> listenerClass = new ArrayList<Class>();
		listenerClass.add(utility.CustomReportListener.class);
		myTestNGSuites.setListenerClasses(listenerClass);
		myTestNGSuites.run();
	}
	
	public static void main(String suite_path) {
		// TODO Auto-generated method stub
		executeTestNGSuite(suite_path);   
		String workingDir = System.getProperty("user.dir");
		String path = workingDir+"\\src\\config\\batch_driver_config.properties";
		String rerun = Properties_Utils.get_property(path,"rerun");
		Integer rerun_count = Integer.valueOf(rerun);
		ArrayList<String> failures = getFailures();
		
		//TODO: Handle the skipped cases as failures too
		if (failures.isEmpty()) {
			System.out.println("No Failures have been found!!");
		}
		else
		{
			if (rerun_count > 1) {
				rerunFailures(failures);
			}
		}	
	}
	
}
