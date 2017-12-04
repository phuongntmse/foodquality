package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Feedback;
@Repository
public class FeedbackDAO {
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	
	public Boolean addFeedback (Feedback fb)
	{
		try {
			//get new id
			PrepareStat = Conn.prepareStatement("select right(max(FID),5) max_id from feedback");
			ResultSet RSidMax = PrepareStat.executeQuery();
			int new_id=0;
			if (RSidMax.next()) {
				if (RSidMax.getString("max_id")!= null)
				new_id = Integer.parseInt(RSidMax.getString("max_id")); 
			}
			new_id++;
			String fid = "FID"+("00000" + Integer.toString(new_id) ).substring(Integer.toString(new_id).length());
			
			//insert to db
			String insertQueryStatement = "INSERT  INTO  feedback  VALUES  (?,?,?,?,?,?)";
 			PrepareStat = Conn.prepareStatement(insertQueryStatement);
			PrepareStat.setString(1, fid);
			PrepareStat.setString(2, fb.getFuid());
			PrepareStat.setString(3, fb.getFpid());
			PrepareStat.setInt(4, fb.getFtype());
			PrepareStat.setString(5, fb.getFcontent());
			PrepareStat.setDate(6, fb.getFdate());
			
			// execute insert SQL statement
			PrepareStat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Feedback> getFeedbackbyPID(String pid)
	{
		List<Feedback> list = new ArrayList<Feedback>();
		Feedback result = new Feedback();
		try {
			//makeJDBCConnection();
			String getQueryStatement = "SELECT * FROM feedback left join user on feedback.UID = user.UID "
					+ "where feedback.PID like '"+pid+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result = new Feedback();
				result.setFid(rs.getString("feedback.FID"));
				result.setFpid(rs.getString("feedback.PID"));
				result.setFuid(rs.getString("feedback.UID"));
				result.setFcontent(rs.getString("feedback.Content"));
				result.setFtype(rs.getInt("feedback.Type"));
				result.setFdate(rs.getDate("feedback.Date"));
				result.setFusername(rs.getString("user.FullName"));
				result.setFuseravatar(rs.getString("user.Avatar"));
				list.add(result);
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 
	}

}
