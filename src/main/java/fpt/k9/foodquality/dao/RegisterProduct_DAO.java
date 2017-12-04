package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Producer;
import fpt.k9.foodquality.model.Product;
import fpt.k9.foodquality.model.RegisterProduct;
import fpt.k9.foodquality.model.Seller;
import fpt.k9.foodquality.model.Transporter;
@Repository
public class RegisterProduct_DAO {

	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	
	public String addRegister(RegisterProduct record)
	{
		try
		{
		//get new id
		PrepareStat = Conn.prepareStatement("select right(max(ID),5) max_id from register_product");
		ResultSet RSidMax = PrepareStat.executeQuery();
		int new_id=0;
		if (RSidMax.next()) {
			if (RSidMax.getString("max_id")!= null)
			new_id = Integer.parseInt(RSidMax.getString("max_id"));  
		}
		new_id++;
		
		String id = "ID"+("00000" + Integer.toString(new_id) ).substring(Integer.toString(new_id).length());
		
		String getQueryStatement = "SELECT ProID FROM producer where UID like '"+record.getRpproid()+"'";
		PrepareStat = Conn.prepareStatement(getQueryStatement);
		ResultSet rs = PrepareStat.executeQuery();
		String proID="";
		while (rs.next()) {
			proID = rs.getString("ProID");
		}
		
		//insert to db
		String insertQueryStatement = "INSERT  INTO  register_product  VALUES  (?,?,?,?,?,?,?,?,?,?)";
		PrepareStat = Conn.prepareStatement(insertQueryStatement);
		PrepareStat.setString(1, id);
		PrepareStat.setString(2, record.getRpname());
		PrepareStat.setString(3, record.getRptype());
		PrepareStat.setDate(4, record.getRpmdate());
		PrepareStat.setDate(5, record.getRpedate());
		PrepareStat.setInt(6, record.getRpprice());
		PrepareStat.setString(7, record.getRpimage());
		PrepareStat.setString(8, proID);
		PrepareStat.setString(9, record.getRpsid());
		PrepareStat.setString(10, record.getRptid());
		// execute insert SQL statement
		PrepareStat.executeUpdate();
		return "Success";
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public List<RegisterProduct> getAllRegister()
	{
		List<RegisterProduct> list = new ArrayList<RegisterProduct>();
		RegisterProduct record = new RegisterProduct();
		try
		{
			//makeJDBCConnection();
			String getQueryStatement = "SELECT * FROM register_product";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				record = new RegisterProduct();
				record.setRpid(rs.getString("ID"));
				record.setRpname(rs.getString("Name"));
				record.setRptype(rs.getString("Type"));
				record.setRpmdate(rs.getDate("MDate"));
				record.setRpedate(rs.getDate("EDate"));
				record.setRpprice(rs.getInt("Price"));
				record.setRpimage(rs.getString("Image"));
				record.setRpproid(rs.getString("ProID"));
				record.setRpsid(rs.getString("SID"));
				record.setRptid(rs.getString("TID"));
				list.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 	
	}
	
	public RegisterProduct getRegisterbyID(String id)
	{
		RegisterProduct record = new RegisterProduct();
		try
		{
			//makeJDBCConnection();
			String getQueryStatement = "SELECT * FROM register_product where ID like '"+id+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				record = new RegisterProduct();
				record.setRpid(rs.getString("ID"));
				record.setRpname(rs.getString("Name"));
				record.setRptype(rs.getString("Type"));
				record.setRpmdate(rs.getDate("MDate"));
				record.setRpedate(rs.getDate("EDate"));
				record.setRpprice(rs.getInt("Price"));
				record.setRpimage(rs.getString("Image"));
				record.setRpproid(rs.getString("ProID"));
				record.setRpsid(rs.getString("SID"));
				record.setRptid(rs.getString("TID"));
			}
			
			ProducerDAO producer = new ProducerDAO();
			Producer prd = producer.getProducerbyID(record.getRpproid());
			record.setRpproid(prd.getProinfo());
			TransporterDAO transporter = new TransporterDAO();
			Transporter trans = transporter.getTransbyID(record.getRptid());
			record.setRptid(trans.getTinfor());
			SellerDAO seller = new SellerDAO();
			Seller sell = seller.getSellerbyID(record.getRpsid());
			record.setRpsid(sell.getSinfor());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return record; 	
	}
	
	public String ApproveProduct(String ID)
	{
		try
		{
			PrepareStat = Conn.prepareStatement("SELECT * FROM register_product where `ID` like '"+ID+"'");
			ResultSet rs = PrepareStat.executeQuery();
			RegisterProduct record = new RegisterProduct();
			String result="";
			while (rs.next()) {
				record = new RegisterProduct();
				record.setRpid(rs.getString("ID"));
				record.setRpname(rs.getString("Name"));
				record.setRptype(rs.getString("Type"));
				record.setRpmdate(rs.getDate("MDate"));
				record.setRpedate(rs.getDate("EDate"));
				record.setRpprice(rs.getInt("Price"));
				record.setRpimage(rs.getString("Image"));
				record.setRpproid(rs.getString("ProID"));
				record.setRpsid(rs.getString("SID"));
				record.setRptid(rs.getString("TID"));
			}
			//add this record to approve table
			if (record.getRpid()==null) return "This Register record doesn't exist!";
			Product prd = new Product("PIF", record.getRpname(), record.getRptype(), record.getRpedate(), record.getRpmdate(), record.getRpprice(), 
					record.getRpimage(), 1, record.getRpproid(), record.getRpsid(), record.getRptid());
			ProductDAO pdao = new ProductDAO();
			result = pdao.addProduct(prd);
			if (result!="Success")  return "Can not approve this record!";
			//remove record
			PrepareStat = Conn.prepareStatement("DELETE FROM register_product where `ID` = '"+ID+"'");
			PrepareStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
		return "Approve success!";
	}
}
