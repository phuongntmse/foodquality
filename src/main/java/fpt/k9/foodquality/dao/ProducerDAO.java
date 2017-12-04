package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Producer;

@Repository
public class ProducerDAO {
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	
	public Producer getProducerbyID(String proid)
	{
		Producer result = new Producer();
		try {
			String getQueryStatement = "SELECT * FROM producer where ProID like '"+proid+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setProid(proid);
				result.setProuid(rs.getString("UID"));
				result.setProinfo(rs.getString("ProInfo"));
				result.setProimage(rs.getString("Image"));
				result.setProtax_code(rs.getString("TaxCode"));
				result.setProphone(rs.getString("Phone"));
				result.setProadd(rs.getString("Address"));
				result.setPromail(rs.getString("Email"));
				result.setProweb(rs.getString("Website"));
				result.setProstatus(rs.getInt("Status"));
			}
			getQueryStatement = "SELECT * FROM qrcode where ReferenceID like '"+proid+"'";
			 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setProqrimage(rs.getString("Image"));
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	public Producer getProducerbyUID(String uid)
	{
		Producer result = new Producer();
		try {
			String getQueryStatement = "SELECT * FROM producer where UID like '"+uid+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setProid(rs.getString("ProID"));
				result.setProuid(rs.getString("UID"));
				result.setProinfo(rs.getString("ProInfo"));
				result.setProimage(rs.getString("Image"));
				result.setProtax_code(rs.getString("TaxCode"));
				result.setProphone(rs.getString("Phone"));
				result.setProadd(rs.getString("Address"));
				result.setPromail(rs.getString("Email"));
				result.setProweb(rs.getString("Website"));
				result.setProstatus(rs.getInt("Status"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	public String addProducer (Producer record)
	{
		try {
			//get new id
			PrepareStat = Conn.prepareStatement("select right(max(ProID),5) max_id from producer");
			ResultSet RSidMax = PrepareStat.executeQuery();
			int new_id=0;
			if (RSidMax.next()) {
				if (RSidMax.getString("max_id")!= null)
				new_id = Integer.parseInt(RSidMax.getString("max_id"));  
			}
			new_id++;
			
			String id = "ProID"+("00000" + Integer.toString(new_id) ).substring(Integer.toString(new_id).length());
			
			//insert to db
			String insertQueryStatement = "INSERT  INTO  producer  VALUES  (?,?,?,?,?,?,?,?,?,?)";
			PrepareStat = Conn.prepareStatement(insertQueryStatement);
			PrepareStat.setString(1, id);
			PrepareStat.setString(2, record.getProuid());
			PrepareStat.setString(3, record.getProinfo());
			PrepareStat.setString(4, record.getProimage());
			PrepareStat.setString(5, record.getProtax_code());
			PrepareStat.setString(6, record.getProphone());
			PrepareStat.setString(7, record.getProadd());
			PrepareStat.setString(8, record.getPromail());
			PrepareStat.setString(9, record.getProweb());
			PrepareStat.setInt(10,record.getProstatus());
			// execute insert SQL statement
			PrepareStat.executeUpdate();
			
			//add to qrcode
			QrDAO qdao = new QrDAO();
			return qdao.addQR(id);
			} catch (SQLException e) {
				e.printStackTrace();
				return e.getMessage();
			}
	}
		
}
