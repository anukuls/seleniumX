package utility;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import utility.Log;

/**
 * File_utils is the class containing different methods for various file operations.
 * It has the following methods:
 * readFile(string)
 * appendFile(string,string[])
 * deleteFile(String,File)
 * WriteFile(String, String)
 * copyFile(String,String)
 * moveFile(String,String)
 *
 */

public class File_Utils {
	
	/**
	* readFile is a method which reads content from the file 
	* It has the return type as string which returns the content of the file.
	* It takes one argument: file_path - path of the file to read.
	*  
	*/
	
	public static String readFile(String file_path) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader(file_path));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String content = sb.toString();
			Log.info(content);
			//br.close();
			return content;
		} finally {
			br.close();
		}

	}
	
	/**
	* appendFile is a method which writes content to the file and having return type as void.
	* It takes two arguments :
	* 1. file_path - path of the file in which we need to write 
	* 2. String arr[] - array containing the data to be written on the file. 
	* If the file already exists the it adds the content to the file 
	* else it creates a new file 
	* 
	*/

	public static void appendFile(String file_path, String arr[]) throws IOException{
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter(file_path,true));
			for(int i = 0; i < arr.length; i++) {
				String s;
				s = arr[i];
				bw.write(s);
				bw.newLine();
				bw.flush();
			}
		}  
		finally {
			bw.close();
		}

	}
	
	
	/**
	* deleteFile is a method which deletes the file.
	* It takes two arguments - file_path and file object.
	* It has return type as boolean which returns true if the file has been deleted successfully 
	* else returns false.
	* 
	*/
	
	public static boolean deleteFile(String file_path, File f) {
		Boolean File_Delete = false;
		try {
			if(f.exists())
			Log.info("file exist and now deleting it");
			File_Delete = f.delete();
		    } 
		catch(Exception e){
			Log.info(e.getMessage());
		          		  }
		return File_Delete;
	}

	/**
	* writeFile is a method which is used to write into the file having return type as void.
	* It takes two arguments ->
	*  1. file_path - path of the file in which we need to write 
	*  2. String arr[] - array containing the data to be written on the file. 
	* It checks if the file already exists or not and deletes the file if it already exists.
	* Then it writes the data to the file.
	* 
	*/
	
	public static void WriteFile(String file_path, String arr[]) throws IOException{
		//Boolean File_Delete = false;
		try{
			File f = new File(file_path);
			if(f.exists() && !f.isDirectory())
			{
				Boolean File_Delete = File_Utils.deleteFile(file_path,f);

				if(File_Delete == true)
				{
					try {
						File_Utils.appendFile(file_path, arr);
					    } 
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else{
				Log.info("file doesnot exist earlier, now creating a new file");
				File_Utils.appendFile(file_path, arr);
			    }
		   }
		  catch(IOException e){
		   Log.info(e.getMessage());
		    				  }
	  }

	/**
	* copyFile is a method which copy files from one location to another having return type as void..
	* It takes two arguments - source_path and destination_path
	* If the file at the destination_path already exists then it replace the file with the new one.
	* 
	*/
	
	public static void copyFile(String source_path, String destination_path) throws IOException{
		File source = new File(source_path);
		File destination = new File(destination_path);
		Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	
	/**
	* moveFile is a method which moves file from one location to another having return type as void.
	* It takes two arguments - source_path and destination_path
	* It deletes the target file after moving it to the destination_path.
	* 
	*/
	
	public static void moveFile(String source_path, String destination_path) throws IOException
	{
		File source = new File(source_path);
		File destination = new File(destination_path);
		Files.move(source.toPath(), destination.toPath(),StandardCopyOption.REPLACE_EXISTING);
	}
}


