package utility;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql_Connector {

	static Connection conn = null;
	static Statement stmt = null;

	//static String url = "jdbc:mysql://qev188.qa1.liveops.com:3306/ccconf";
	//static String user = "ccconf";
	//static String password = "ccconf_dbctlusr";

	public static Connection getDBConn(String url, String user, String password) {
		// Register JDBC driver
	/*	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

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

	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}