package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import utility.CustomReport;

public class CustomReportListener implements IReporter {

	@Override
    public void generateReport( List<XmlSuite> xmlSuites, List<ISuite> suites, 
           String outputDirectory ) {

	   String date;
	   DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
	   String folderNameWithTimeStamp = df.format(new Date());
	   String currentDir = System.getProperty("user.dir") + "//Reports//";
	   String finalPath = currentDir + folderNameWithTimeStamp;
	    
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
        }
        CustomReport cr = new CustomReport();
        
        System.out.println("Output report path is: " + finalPath);
        cr.generateReport( xmlSuites, suites, finalPath );
      }  
   }

	   
}
