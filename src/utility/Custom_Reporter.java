package utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.stream.Location;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamException;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class Custom_Reporter implements IReporter {
	
//	File fd = new File("");
	
//	private final XMLReporter xmlReporter = new XMLReporter() {
//		
//		@Override
//		public void report(String message, String errorType,
//				Object relatedInformation, Location location)
//				throws XMLStreamException {
//			// TODO Auto-generated method stub
//			
//		}
//	}; 

    String date;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
    String folderNameWithTimeStamp = df.format(new Date());
    String currentDir = System.getProperty("user.dir") + "//Reports//";
    String finalPath = currentDir + folderNameWithTimeStamp;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		// TODO Auto-generated method stub
		System.out.println(currentDir);
		File dir = new File(finalPath);
	    if (!dir.exists()) {
	    	dir.mkdirs();
	    }
	    
	    try {
	        Thread.sleep(5000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
		TestNG.getDefault().setOutputDirectory(finalPath);
        TestNG.getDefault().setXmlSuites(xmlSuites);
        
        try {
	        Thread.sleep(5000);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
        
        
        
        System.out.println("report generated successfully...");
        
	}
	
	

}
