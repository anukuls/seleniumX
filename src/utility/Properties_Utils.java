package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

public class Properties_Utils {

	public static String get_property(String property_path, String property) {
		
		String prop_value = null;
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			String workingDir = System.getProperty("user.dir");
			String path = workingDir+"\\src\\config\\grid.properties";
			input = new FileInputStream(property_path);
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
//			System.out.println(prop.getProperty("hub"));
//			System.out.println(prop.getProperty("node1"));
//			System.out.println(prop.getProperty("node2"));
			prop_value = prop.getProperty(property);
	 
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
		
		return prop_value;
	}
	
	
	
}
