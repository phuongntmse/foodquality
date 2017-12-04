package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Transporter;

@Repository
public class TransporterDAO {
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	public Transporter getTransbyID(String tid) {
		Transporter result = new Transporter();
		try {
			String getQueryStatement = "SELECT * FROM transporter where TID like '"+tid+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setTid(tid);
				result.setTuid(rs.getString("UID"));
				result.setTinfor(rs.getString("TranInfo"));
				result.setTimage(rs.getString("Image"));
				result.setTtax_code(rs.getString("TaxCode"));
				result.setTphone(rs.getString("Phone"));
				result.setTadd(rs.getString("Address"));
				result.setTmail(rs.getString("Email"));
				result.setTweb(rs.getString("Website"));
				result.setTstatus(rs.getInt("Status"));
			}
			getQueryStatement = "SELECT * FROM qrcode where ReferenceID like '"+tid+"'";
			 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setTqrimage(rs.getString("Image"));
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	public Transporter getTranspỏterbyUID(String uid) {
		Transporter result = new Transporter();
		try {
			String getQueryStatement = "SELECT * FROM transporter where UID like '"+uid+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setTid(rs.getString("TID"));
				result.setTuid(rs.getString("UID"));
				result.setTinfor(rs.getString("TranInfo"));
				result.setTimage(rs.getString("Image"));
				result.setTtax_code(rs.getString("TaxCode"));
				result.setTphone(rs.getString("Phone"));
				result.setTadd(rs.getString("Address"));
				result.setTmail(rs.getString("Email"));
				result.setTweb(rs.getString("Website"));
				result.setTstatus(rs.getInt("Status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	public List<Transporter> getAllTrans() {
		List<Transporter> list = new ArrayList<Transporter>();
		Transporter result = new Transporter();
		try {
			String getQueryStatement = "SELECT * FROM transporter where Status = 1";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result = new Transporter();
				result.setTid(rs.getString("TID"));
				result.setTuid(rs.getString("UID"));
				result.setTinfor(rs.getString("TranInfo"));
				result.setTimage(rs.getString("Image"));
				result.setTtax_code(rs.getString("TaxCode"));
				result.setTphone(rs.getString("Phone"));
				result.setTadd(rs.getString("Address"));
				result.setTmail(rs.getString("Email"));
				result.setTweb(rs.getString("Website"));
				result.setTstatus(rs.getInt("Status"));
				list.add(result);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	public String addTransporter (Transporter record)
	{
		try {
			//get new id
			PrepareStat = Conn.prepareStatement("select right(max(TID),5) max_id from transporter");
			ResultSet RSidMax = PrepareStat.executeQuery();
			int new_id=0;
			if (RSidMax.next()) {
				if (RSidMax.getString("max_id")!= null)
				new_id = Integer.parseInt(RSidMax.getString("max_id"));  
			}
			new_id++;
			
			String id = "TID"+("00000" + Integer.toString(new_id) ).substring(Integer.toString(new_id).length());
			
			//insert to db
			String insertQueryStatement = "INSERT  INTO  transporter  VALUES  (?,?,?,?,?,?,?,?,?,?)";
			PrepareStat = Conn.prepareStatement(insertQueryStatement);
			PrepareStat.setString(1, id);
			PrepareStat.setString(2, record.getTuid());
			PrepareStat.setString(3, record.getTinfor());
			PrepareStat.setString(4, record.getTimage());
			PrepareStat.setString(5, record.getTtax_code());
			PrepareStat.setString(6, record.getTphone());
			PrepareStat.setString(7, record.getTadd());
			PrepareStat.setString(8, record.getTmail());
			PrepareStat.setString(9, record.getTweb());
			PrepareStat.setInt(10,record.getTstatus());
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
