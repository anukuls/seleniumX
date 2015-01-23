package utility;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

//Creating logger methods for all the log levels.  These methods will be
//available throughout the code base by placing "import utility.Log" in your
//respective code
public class Log {
	
	private static Logger Log = Logger.getLogger(Log.class.getName());
	
	public static void initializeLog() {
		DOMConfigurator.configure("log4j.xml");
	}
	
	//Implement delimiters to separate start and end test cases, and start of
	//the next case
	public static void startTC() {
		Log.info("****************************************************************************************");
		 
		Log.info("****************************************************************************************");
	 
		Log.info("======== Starting Execution of Test case ========");
	 
		Log.info("****************************************************************************************");
	 
		Log.info("****************************************************************************************");
	}
	
	public static void endTC() {
		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
		 
		Log.info("X");
	 
		Log.info("X");
	 
		Log.info("X");
	 
		Log.info("X");	
	}

	public static void info(String msg) {
		Log.info(msg);
	}
	
	public static void debug(String msg) {
		Log.debug(msg);
	}
	
	public static void warn(String msg) {
		Log.warn(msg);
	}
	
	public static void fatal(String msg) {
		Log.fatal(msg);
	}
	
	public static void error(String msg) {
		Log.error(msg);
	}
	
	//TODO: Add method to flush log file after "x" number of lines get accumulated
	//in the logfile.log
	
	
	
}
