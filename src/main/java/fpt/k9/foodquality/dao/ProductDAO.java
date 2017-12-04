package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Product;

@Repository
public class ProductDAO{
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	
	
	public String addProduct (Product prd)
	{
		try {
			//get new id
			PrepareStat = Conn.prepareStatement("select right(max(PID),5) max_id from product");
			ResultSet RSidMax = PrepareStat.executeQuery();
			int new_id=0;
			if (RSidMax.next()) {
				if (RSidMax.getString("max_id")!= null)
				new_id = Integer.parseInt(RSidMax.getString("max_id"));  
			}
			new_id++;
			
			String id = "PID"+("00000" + Integer.toString(new_id) ).substring(Integer.toString(new_id).length());
			
			//insert to db
			String insertQueryStatement = "INSERT  INTO  product  VALUES  (?,?,?,?,?,?,?,?,?,?,?)";
 			PrepareStat = Conn.prepareStatement(insertQueryStatement);
			PrepareStat.setString(1, id);
			PrepareStat.setString(2, prd.getPname());
			PrepareStat.setString(3, prd.getPtype());
			PrepareStat.setDate(4, prd.getPmdate());
			PrepareStat.setDate(5, prd.getPedate());
			PrepareStat.setFloat(6, prd.getPprice());
			PrepareStat.setString(7, prd.getPimage());
			PrepareStat.setInt(8, prd.getPstatus());
			PrepareStat.setString(9, prd.getPproid());
			PrepareStat.setString(10, prd.getPsid());
			PrepareStat.setString(11, prd.getPtid());
 
			// execute insert SQL statement
			PrepareStat.executeUpdate();
			//add to qrcode
			QrDAO qdao = new QrDAO();
			return qdao.addQR(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
	}
 
	public Product getProductbyID(String pid) {
		Product result = new Product();
		try {
			String getQueryStatement = "SELECT * FROM product where PID like '"+pid+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result.setPid(pid);
				result.setPname(rs.getString("Name"));
				result.setPtype(rs.getString("Type"));
				result.setPedate(rs.getDate("EDate"));
				result.setPmdate(rs.getDate("MDate"));
				result.setPprice(rs.getInt("Price"));
				result.setPimage(rs.getString("Image"));
				result.setPstatus(rs.getInt("Status"));
				result.setPproid(rs.getString("ProID"));
				result.setPsid(rs.getString("SID"));
				result.setPtid(rs.getString("TID"));
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	public List<Product> searchProducts(String findw) {
		List<Product> list = new ArrayList<Product>();
		Product result = new Product();
		try {
			//makeJDBCConnection();
			String getQueryStatement = "SELECT * FROM product where Name like '%"+findw+"%'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result = new Product();
				result.setPid(rs.getString("PID"));
				result.setPname(rs.getString("Name"));
				result.setPtype(rs.getString("Type"));
				result.setPedate(rs.getDate("EDate"));
				result.setPmdate(rs.getDate("MDate"));
				result.setPprice(rs.getInt("Price"));
				result.setPimage(rs.getString("Image"));
				result.setPstatus(rs.getInt("Status"));
				result.setPproid(rs.getString("ProID"));
				result.setPsid(rs.getString("SID"));
				result.setPtid(rs.getString("TID"));
				list.add(result);
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	public List<Product> getProductsbyRefID(String mtype,String proID) {
		List<Product> list = new ArrayList<Product>();
		Product result = new Product();
		try {
			//makeJDBCConnection();
			String getQueryStatement = "SELECT * FROM product where "+mtype+" like '"+proID+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				result = new Product();
				result.setPid(rs.getString("PID"));
				result.setPname(rs.getString("Name"));
				result.setPtype(rs.getString("Type"));
				result.setPedate(rs.getDate("EDate"));
				result.setPmdate(rs.getDate("MDate"));
				result.setPprice(rs.getInt("Price"));
				result.setPimage(rs.getString("Image"));
				result.setPstatus(rs.getInt("Status"));
				result.setPproid(rs.getString("ProID"));
				result.setPsid(rs.getString("SID"));
				result.setPtid(rs.getString("TID"));
				list.add(result);
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 
	}
}