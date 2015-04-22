package utility;

import utility.Excel_Utils;

public class TestData_Loader {
	
	public static Excel_Utils loadTestData(Object obj) throws Exception {
		
		//TODO: This is where we will bind the testdata for a given class which is under execution
		String canonical_class_name = obj.getClass().getCanonicalName();
		
		String test_path = getTestDataPath(canonical_class_name);
		Excel_Utils excel = new Excel_Utils(test_path, "Sheet1");
		
		return excel;
	}
	
	private static String getTestDataPath(String class_name) {
		String testdata_path = null;
		String workingDir = System.getProperty("user.dir");
		
		System.out.println("canonical class name is: " + class_name);
		
		String[] class_name_splitter = class_name.split("\\.");
		System.out.println("split class name is: " + class_name_splitter);
		
		testdata_path = workingDir + "\\src\\" + class_name_splitter[0] + "\\" + class_name_splitter[1] + "\\TestData.xlsx";
		System.out.println("Test data path is: " + testdata_path);
		
		return testdata_path;				
	} 
	
	private static void setCurrentRow(String class_name) {
		//TODO: Let's see if we need this method or not!!
		//This is what requires two arrays mapped into a hash (header array and the function under 
		//execution row)
	}
	
}
