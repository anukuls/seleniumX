package utility;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Utils {

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs;

	public static Connection connectDB(String hostname, String user, String password, String db) {
		// Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://" + hostname + ":3306/" + db;

		// Open a connection
		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}
	
	public static ResultSet executeQuery(Connection conn, String sql) {
		System.out.println("Creating statement...");
	      try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//        String sql = "SELECT id, first, last, age FROM Registration";
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	    return rs;
	}
	
	public static void closeDB() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}