package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

	static Connection Conn = null;
	
	public static Connection  makeJDBCConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
 
		try {
			Conn = DriverManager.getConnection("jdbc:mysql://mysql6.gear.host:3306/foodquality", "foodquality", "12345678@Ab");
			if (Conn != null) {
				System.out.println("Connect Successful!");
				return Conn;
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException e) {
			System.out.println("MySQL Connection Failed!");
			e.printStackTrace();
		}
		return Conn;
	}
}
