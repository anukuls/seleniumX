package testScripts;
import java.io.IOException;
import utility.File_Utils;
import org.apache.log4j.xml.DOMConfigurator;

public class File_POC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DOMConfigurator.configure("log4j.xml");
		try {
			String path = "C:\\Users\\ekta.arora\\Desktop\\test3.txt";
			String destination_path = "C:\\Users\\ekta.arora\\Desktop\\test2.txt";
			String test_arr[] = {"lp", "infogain", "kalkaji", "New Delhi"};
			String move_path = "D:\\laptop_backup\\D drive\\new.txt";
			//File_Utils.WriteFile(path, test_arr);
			
			File_Utils.WriteFile(path,test_arr);	
			File_Utils.readFile(path);
			File_Utils.copyFile(path,destination_path);
			File_Utils.readFile(destination_path);
			File_Utils.moveFile(destination_path,move_path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
