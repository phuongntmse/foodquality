package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.History;

@Repository
public class HistoryDAO {
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	
	public History getHistorybyUID(String uid)
	{
		History result = new History();
		try {
			//makeJDBCConnection();
			String getQueryStatement = "SELECT * FROM history where history.UID like '"+uid+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result = new History();
				result.setHuid(uid);
				result.setHcontent(rs.getString("Content"));
				result.setHcount(rs.getInt("Count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String addHistory(History newh)
	{
		History oldh = getHistorybyUID(newh.getHuid());
		if (oldh.getHcount()==10)
		{
			oldh.setHcontent(oldh.getHcontent().substring(oldh.getHcontent().indexOf("},")+2));
			oldh.setHcount(oldh.getHcount()-1);
		}
		if (oldh.getHcount()!=0)
		{
			oldh.setHcontent(oldh.getHcontent().concat(","));
		}
		newh.setHcontent(oldh.getHcontent().concat(newh.getHcontent()));
		newh.setHcount(oldh.getHcount()+1);
		try
		{
			//delete old row
			String deleteQuery = "DELETE FROM history where history.UID like '"+newh.getHuid()+"'";
			PrepareStat = Conn.prepareStatement(deleteQuery);
			PrepareStat.executeUpdate();
			//insert new row
			String insertQueryStatement = "INSERT  INTO  history  VALUES  (?,?,?)";
			PrepareStat = Conn.prepareStatement(insertQueryStatement);
			PrepareStat.setString(1, newh.getHuid());
			PrepareStat.setString(2, newh.getHcontent());
			PrepareStat.setInt(3, newh.getHcount());
			// execute insert SQL statement
			PrepareStat.executeUpdate();
			return "OK";
		}
		catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage().toString();
		}
	}
}
