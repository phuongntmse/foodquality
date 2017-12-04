package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Seller;

@Repository
public class SellerDAO {
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	public Seller getSellerbyID(String sid) {
		Seller result = new Seller();
		try {
			String getQueryStatement = "SELECT * FROM seller where SID like '"+sid+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setSid(sid);
				result.setSuid(rs.getString("UID"));
				result.setSinfor(rs.getString("SellerInfo"));
				result.setSimage(rs.getString("Image"));
				result.setStax_code(rs.getString("TaxCode"));
				result.setSphone(rs.getString("Phone"));
				result.setSadd(rs.getString("Address"));
				result.setSmail(rs.getString("Email"));
				result.setSweb(rs.getString("Website"));
				result.setSstatus(rs.getInt("Status"));
			}
			getQueryStatement = "SELECT * FROM qrcode where ReferenceID like '"+sid+"'";
			 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setSqrimage(rs.getString("Image"));
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	public Seller getSellerbyUID(String uid) {
		Seller result = new Seller();
		try {
			String getQueryStatement = "SELECT * FROM seller where UID like '"+uid+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setSid(rs.getString("SID"));
				result.setSuid(rs.getString("UID"));
				result.setSinfor(rs.getString("SellerInfo"));
				result.setSimage(rs.getString("Image"));
				result.setStax_code(rs.getString("TaxCode"));
				result.setSphone(rs.getString("Phone"));
				result.setSadd(rs.getString("Address"));
				result.setSmail(rs.getString("Email"));
				result.setSweb(rs.getString("Website"));
				result.setSstatus(rs.getInt("Status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	public List<Seller> getAllSeller() {
		List<Seller> list = new ArrayList<Seller>();
		Seller result = new Seller();
		try {
			String getQueryStatement = "SELECT * FROM seller where Status = 1"; 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result = new Seller();
				result.setSid(rs.getString("SID"));
				result.setSuid(rs.getString("UID"));
				result.setSinfor(rs.getString("SellerInfo"));
				result.setSimage(rs.getString("Image"));
				result.setStax_code(rs.getString("TaxCode"));
				result.setSphone(rs.getString("Phone"));
				result.setSadd(rs.getString("Address"));
				result.setSmail(rs.getString("Email"));
				result.setSweb(rs.getString("Website"));
				result.setSstatus(rs.getInt("Status"));
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	public String addSeller (Seller record)
	{
		try {
			//get new id
			PrepareStat = Conn.prepareStatement("select right(max(SID),5) max_id from seller");
			ResultSet RSidMax = PrepareStat.executeQuery();
			int new_id=0;
			if (RSidMax.next()) {
				if (RSidMax.getString("max_id")!= null)
				new_id = Integer.parseInt(RSidMax.getString("max_id"));  
			}
			new_id++;
			
			String id = "SID"+("00000" + Integer.toString(new_id) ).substring(Integer.toString(new_id).length());
			
			//insert to db
			String insertQueryStatement = "INSERT  INTO  seller  VALUES  (?,?,?,?,?,?,?,?,?,?)";
			PrepareStat = Conn.prepareStatement(insertQueryStatement);
			PrepareStat.setString(1, id);
			PrepareStat.setString(2, record.getSuid());
			PrepareStat.setString(3, record.getSinfor());
			PrepareStat.setString(4, record.getSimage());
			PrepareStat.setString(5, record.getStax_code());
			PrepareStat.setString(6, record.getSphone());
			PrepareStat.setString(7, record.getSadd());
			PrepareStat.setString(8, record.getSmail());
			PrepareStat.setString(9, record.getSweb());
			PrepareStat.setInt(10,record.getSstatus());
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
