package testScripts.oldScripts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DB_Utils;

public class DB_Utils_POC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String hostname = "qevc1.qa1.liveops.com";
			String database = "ccconf";
//			String url = "jdbc:mysql://" + hostname + ":3306/" + database;
//			System.out.println(url);
			String user = "ccconf";
			String password = "ccconf_dbctlusr";
			String sql = "select phonenum from rep where rep_id=22918";
			Connection conn = DB_Utils.connectDB(hostname, user, password, database);
			
			ResultSet rs = DB_Utils.executeQuery(conn, sql);
			
			while(rs.next()){
	           //Retrieve by column name
	           String phone  = rs.getString("phonenum");
		         
		       //Display values
		       System.out.println("Phone: " + phone);
			}
			
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			DB_Utils.closeDB();
		}
	}

}
