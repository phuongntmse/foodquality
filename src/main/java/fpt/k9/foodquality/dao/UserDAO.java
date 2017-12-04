package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.User;

@Repository
public class UserDAO{
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	
	
	public String registerUser (User nuser)
	{
		try
		{
		//get new id
		PrepareStat = Conn.prepareStatement("select right(max(UID),5) max_id from user");
		ResultSet RSidMax = PrepareStat.executeQuery();
		int new_id=0;
		if (RSidMax.next()) {
			if (RSidMax.getString("max_id")!= null)
			new_id = Integer.parseInt(RSidMax.getString("max_id"));  
		}
		new_id++;
		String id = "UID"+("00000" + Integer.toString(new_id) ).substring(Integer.toString(new_id).length());
		
		//insert to db
		String insertQueryStatement = "INSERT  INTO  user  VALUES  (?,?,?,?,?,?,?,?,?)";
			PrepareStat = Conn.prepareStatement(insertQueryStatement);
		PrepareStat.setString(1, id);
		PrepareStat.setString(2, nuser.getUname());
		PrepareStat.setString(3, nuser.getUacc());
		PrepareStat.setString(4, nuser.getUpass());
		PrepareStat.setString(5, nuser.getUmail());
		PrepareStat.setString(6, nuser.getUadd());
		PrepareStat.setString(7, nuser.getUphone());
		PrepareStat.setString(8, nuser.getUimage());
		PrepareStat.setInt(9, 0);

		// execute insert SQL statement
		PrepareStat.executeUpdate();
		return "Register success";
		}
		catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage().toString();
		}
	}
	public List<Object> getUserbyusn(String usn,String password) 
	{
		List<Object> list = null;
		User result = null;
		try {
			String getQueryStatement = "SELECT * FROM user where `UserName` like '"+usn+"' and `Password` like '"+password+"'";
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result = new User();
				result.setUid(rs.getString("UID"));
				result.setUname(rs.getString("FullName"));
				result.setUacc(rs.getString("UserName"));
				result.setUpass(rs.getString("Password"));
				result.setUmail(rs.getString("Email"));
				result.setUadd(rs.getString("Address"));
				result.setUphone(rs.getString("Phone"));
				result.setUimage(rs.getString("Avatar"));
				result.setUrole(rs.getInt("Role"));
				list = new ArrayList<Object>();
				list.add(result);
			}
			if (list!=null)
			{
				if (result.getUrole()==1)
				{
					ProducerDAO prd = new ProducerDAO();
					list.add(prd.getProducerbyUID(result.getUid()));
				}
				if (result.getUrole()==2)
				{
					TransporterDAO prd = new TransporterDAO();
					list.add(prd.getTranspỏterbyUID(result.getUid()));
				}
				if (result.getUrole()==3)
				{
					SellerDAO prd = new SellerDAO();
					list.add(prd.getSellerbyUID(result.getUid()));
				}
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 
	}
	public User getUserbyName(String usn) 
	{
		User result = null;
		try {
			String getQueryStatement = "SELECT * FROM user where `Username` like '"+usn+"'";
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result = new User();
				result.setUid(rs.getString("UID"));
				result.setUname(rs.getString("FullName"));
				result.setUacc(rs.getString("UserName"));
				result.setUpass(rs.getString("Password"));
				result.setUmail(rs.getString("Email"));
				result.setUadd(rs.getString("Address"));
				result.setUphone(rs.getString("Phone"));
				result.setUimage(rs.getString("Avatar"));
				result.setUrole(rs.getInt("Role"));
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
	public String changpwd(User user)
	{
		String result = null;
		try {
			PrepareStat = Conn.prepareStatement("update user set `Password` = '"+user.getUpass()+"' where `UID` like '"+user.getUid()+"'");
			PrepareStat.executeUpdate();
			result  = "Change pwd success";
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Error";
		}
		return result; 
	}
	
	public String updateProfile(User user)
	{
		String result = null;
		try {
			PrepareStat = Conn.prepareStatement("update user set `FullName` = '"+user.getUname()+
					"', `Address` = '"+user.getUadd()+
					"', `Phone` = '"+user.getUphone()+
					"', `Email` = '"+user.getUmail()+
					"' where `UID` like '"+user.getUid()+"'");
			PrepareStat.executeUpdate();
			result  = "Update success";
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Error";
		}
		return result; 
	}
	
	public String checkMail(String email, String UID)
	{
		try {
			PrepareStat = Conn.prepareStatement("SELECT * FROM user where `Email` like '"+email+"'");
			ResultSet rs = PrepareStat.executeQuery();
			if (rs.next()) 
				{
					if (rs.getString("UID").equals(UID)) return "OK";
					else return "This email exist!";
				}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
		return "OK"; 
	}
}
