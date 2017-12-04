package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Report;

@Repository
public class ReportDAO {
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	
	public String addReport(Report mreport) {
		try {
			// history date
			Calendar calendar = Calendar.getInstance();
			java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime()
					.getTime());
			// get new id
			PrepareStat = Conn.prepareStatement("select max(RID) max_id from report");
			ResultSet RSidMax = PrepareStat.executeQuery();
			int new_id = 0;
			if (RSidMax.next()) {
				if (RSidMax.getString("max_id")!= null)
				new_id = RSidMax.getInt("max_id");
			}
			new_id++;
			String id = "RID"+("00000" + Integer.toString(new_id) ).substring(Integer.toString(new_id).length());
			// insert to db
			String insertQueryStatement = "INSERT  INTO  report  VALUES  (?,?,?,?,?,?)";
			PrepareStat = Conn.prepareStatement(insertQueryStatement);
			PrepareStat.setString(1, id);
			PrepareStat.setString(2, mreport.getRcontent());
			PrepareStat.setString(3, mreport.getRuid());
			PrepareStat.setInt(4, mreport.getRtype());
			PrepareStat.setDate(5, ourJavaDateObject);
			PrepareStat.setString(6, mreport.getRrefid());
			// execute insert SQL statement
			PrepareStat.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
	}

}
