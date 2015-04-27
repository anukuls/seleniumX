package testScripts.oldScripts;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;
//import java.util.Set;

public class Properties_POC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			String workingDir = System.getProperty("user.dir");
			String path = workingDir+"\\src\\config\\runConfig.properties";
			input = new FileInputStream(path);
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
//			System.out.println(prop.getProperty("hub"));
//			System.out.println(prop.getProperty("node1"));
//			System.out.println(prop.getProperty("node2"));
			System.out.println(prop.getProperty("10.32.14.15"));
			System.out.println(prop.keySet());
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
