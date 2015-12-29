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
	
	private static ArrayList<String> classNames;

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
	
//	public static void getFailureMethods( List<XmlSuite> xmlSuites, List<ISuite> suites, 
//	           String outputDirectory ) {
//
//	   String date;
//	   DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
//	   String folderNameWithTimeStamp = df.format(new Date());
//	   String currentDir = System.getProperty("user.dir") + "//Reports//";
//	   String finalPath = currentDir + folderNameWithTimeStamp;
//	   Collection<ITestNGMethod> failed_tests = null;
//	   Set<ITestResult> all_failed_tests = null; 
//	   ArrayList<String> classNames;
//	    
//       //Iterating over each suite included in the test
//       for (ISuite suite : suites) {
//       //Following code gets the suite name
//       String suiteName = suite.getName();
//       //Getting the results for the said suite
//       Map<String, ISuiteResult> suiteResults = suite.getResults();
//       for ( ISuiteResult sr : suiteResults.values() ) {
//          ITestContext tc = sr.getTestContext();
//          all_failed_tests = tc.getFailedTests().getAllResults();
//          classNames = groupResults(all_failed_tests);
//          System.out.println(classNames);
//        }
//        CustomReport cr = new CustomReport();
//        
//        System.out.println("Output report path is: " + finalPath);
//        cr.generateReport( xmlSuites, suites, finalPath );
//      }  
//   }
	
	public static ArrayList<String> getFailureClasses() {
		return classNames;
	}
	
	public static ArrayList groupResults(Set<ITestResult> results) {
        List<ClassResult> classResults = Lists.newArrayList();
        ArrayList<String> classNames = new ArrayList<>(); 
        if (!results.isEmpty()) {
            List<MethodResult> resultsPerClass = Lists.newArrayList();
            List<ITestResult> resultsPerMethod = Lists.newArrayList();

            List<ITestResult> resultsList = Lists.newArrayList(results);
//            Collections.sort(resultsList, RESULT_COMPARATOR);
            Iterator<ITestResult> resultsIterator = resultsList.iterator();
            assert resultsIterator.hasNext();

            ITestResult result = resultsIterator.next();
            resultsPerMethod.add(result);

            String previousClassName = result.getTestClass().getName();
            classNames.add(previousClassName);
//            System.out.println("previousClassName :" + previousClassName);
            String previousMethodName = result.getMethod().getMethodName();
//            System.out.println("previousMethodName :" + previousMethodName);
            while (resultsIterator.hasNext()) {
                result = resultsIterator.next();

                String className = result.getTestClass().getName();
//                System.out.println("className :" + className);
                if (!previousClassName.equals(className)) {
                    // Different class implies different method
                    assert !resultsPerMethod.isEmpty();
                    resultsPerClass.add(new MethodResult(resultsPerMethod));
                    resultsPerMethod = Lists.newArrayList();

                    assert !resultsPerClass.isEmpty();
                    classResults.add(new ClassResult(previousClassName,
                            resultsPerClass));
                    resultsPerClass = Lists.newArrayList();

                    previousClassName = className;
                    previousMethodName = result.getMethod().getMethodName();
//                    System.out.println("previousMethodName1 :" + previousMethodName);
                } else {
                    String methodName = result.getMethod().getMethodName();
                    if (!previousMethodName.equals(methodName)) {
                        assert !resultsPerMethod.isEmpty();
                        resultsPerClass.add(new MethodResult(resultsPerMethod));
                        resultsPerMethod = Lists.newArrayList();

                        previousMethodName = methodName;
                    }
                }
                resultsPerMethod.add(result);
            }
            assert !resultsPerMethod.isEmpty();
            resultsPerClass.add(new MethodResult(resultsPerMethod));
            assert !resultsPerClass.isEmpty();
            classResults.add(new ClassResult(previousClassName,
                    resultsPerClass));
        }
        return classNames;
    }
}
