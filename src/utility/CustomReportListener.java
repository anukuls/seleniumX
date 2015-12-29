package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.ITestNGMethod;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite;

import utility.CustomReport;
import utility.CustomReport.ClassResult;
import utility.CustomReport.MethodResult;

public class CustomReportListener implements IReporter {
	
	private static ArrayList<String> classNames = new ArrayList<>();

	@Override
    public void generateReport( List<XmlSuite> xmlSuites, List<ISuite> suites, 
           String outputDirectory ) {

	   String date;
	   DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
	   String folderNameWithTimeStamp = df.format(new Date());
	   String currentDir = System.getProperty("user.dir") + "//Reports//";
	   String finalPath = currentDir + folderNameWithTimeStamp;
	   Set<ITestResult> all_failed_tests = null; 
	   
	    
       //Iterating over each suite included in the test
       for (ISuite suite : suites) {
       //Following code gets the suite name
       String suiteName = suite.getName();
       //Getting the results for the said suite
       Map<String, ISuiteResult> suiteResults = suite.getResults();
       for ( ISuiteResult sr : suiteResults.values() ) {
          ITestContext tc = sr.getTestContext();
          System.out.println("Passed tests for suite '" + suiteName + "' is:" +
               tc.getPassedTests().getAllResults().size());
          all_failed_tests = tc.getFailedTests().getAllResults();
          classNames = groupResults(all_failed_tests);
        }
        CustomReport cr = new CustomReport();
        
        System.out.println("Output report path is: " + finalPath);
        cr.generateReport( xmlSuites, suites, finalPath );
      }  
   }
	
   public static ArrayList<String> getFailureClasses() {
		return classNames;
   }
	
   public static ArrayList groupResults(Set<ITestResult> results) {
        if (!results.isEmpty()) {

            Iterator<ITestResult> resultsIterator = results.iterator();

            while (resultsIterator.hasNext()) {
            	ITestResult result = resultsIterator.next();

                String className = result.getTestClass().getName();
                classNames.add(className);
            }
        }
        return classNames;
    }
	
}
