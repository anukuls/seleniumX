package utility;

import java.io.File;
import java.util.ArrayList;


//http://stackoverflow.com/questions/13533479/how-to-find-sub-directories-in-a-directory-folder
//http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
public class Read_Files {
	
	public static ArrayList<String> listDirs(final File folder) {
		ArrayList<String> arrList = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
//	        	System.out.println(fileEntry.getName());
	        	arrList.add(fileEntry.getName());
	            listDirs(fileEntry);
	        } 
//	        else {
//	            System.out.println(fileEntry.getName());
//	        }
	    }
//	    System.out.println(arrList);
	    return arrList;
	}
	
	public static ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> arrList = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
//	        	System.out.println(fileEntry.getName());
	            listFilesForFolder(fileEntry);
	        } 
	        else {
//	            System.out.println(fileEntry.getName());
	            if (fileEntry.getName().equals("TestData.xlsx")) {
	            	//NOTE: Automation Manager shows TestData.xlsx file as well when a given suite is selected
	            	//We dont need to display that file
	            }
	            else
	            {
	            	arrList.add(fileEntry.getName());
	            }
	        }
	    }
//	    System.out.println(arrList);
	    return arrList;
	}
	
	public static ArrayList<String> readDir() {
		ArrayList<String> suitesArray = new ArrayList<String>(); 
		String workingDir = System.getProperty("user.dir");
	    final String path = workingDir+"\\src\\testScripts";
		final File folder = new File(path);
		suitesArray = listDirs(folder);
		return suitesArray;
	}
	
	public static ArrayList<String> readFilesForFolder(String folder_path) {
		ArrayList<String> scriptsArray = new ArrayList<String>(); 
//		String workingDir = System.getProperty("user.dir");
//	    final String path = workingDir+"\\src\\testScripts";
//		System.out.println(folder_path);
		final File folder = new File(folder_path);
		scriptsArray = listFilesForFolder(folder);
		return scriptsArray;
	}
	
	public static ArrayList<String> readBatchConfig() {
		ArrayList<String> batchArray = new ArrayList<String>(); 
		String workingDir = System.getProperty("user.dir");
	    final String path = workingDir+"\\src\\batchConfig";
	    final File folder = new File(path);
	    batchArray = listFilesForFolder(folder);
		return batchArray;
	}

}
