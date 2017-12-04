package fpt.k9.foodquality.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Producer;
import fpt.k9.foodquality.model.RegisterPTS;
import fpt.k9.foodquality.model.Seller;
import fpt.k9.foodquality.model.Transporter;

@Repository
public class RegisterPTS_DAO {
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;
	
	public List<RegisterPTS> getAllRegister()
	{
		List<RegisterPTS> list = new ArrayList<RegisterPTS>();
		RegisterPTS record = new RegisterPTS();
		try
		{
			//makeJDBCConnection();
			String getQueryStatement = "SELECT * FROM register";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				record = new RegisterPTS();
				record.setRegisterID(rs.getString("RegisterID"));
				record.setRegisterType(rs.getInt("Type"));
				record.setRegisterUid(rs.getString("UID"));
				record.setRegisterName(rs.getString("Name"));
				record.setRegisterImage(rs.getString("Image"));
				record.setRegisterTax(rs.getString("TaxCode"));
				record.setRegisterphone(rs.getString("Phone"));
				record.setRegisteradd(rs.getString("Address"));
				record.setRegistermail(rs.getString("Email"));
				record.setRegisteroweb(rs.getString("Website"));
				list.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 	
	}
	
	public List<RegisterPTS> getRegisterbyType(int type)
	{
		List<RegisterPTS> list = new ArrayList<RegisterPTS>();
		RegisterPTS record = new RegisterPTS();
		try
		{
			//makeJDBCConnection();
			String getQueryStatement = "SELECT * FROM register where Type = "+type;
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				record = new RegisterPTS();
				record.setRegisterID(rs.getString("RegisterID"));
				record.setRegisterType(rs.getInt("Type"));
				record.setRegisterUid(rs.getString("UID"));
				record.setRegisterName(rs.getString("Name"));
				record.setRegisterImage(rs.getString("Image"));
				record.setRegisterTax(rs.getString("TaxCode"));
				record.setRegisterphone(rs.getString("Phone"));
				record.setRegisteradd(rs.getString("Address"));
				record.setRegistermail(rs.getString("Email"));
				record.setRegisteroweb(rs.getString("Website"));
				list.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public RegisterPTS getRegisterbyID(String id)
	{
		RegisterPTS record = new RegisterPTS();
		try
		{
			String getQueryStatement = "SELECT * FROM register where RegisterID like '"+id+"'";
 
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();
 
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				record = new RegisterPTS();
				record.setRegisterID(rs.getString("RegisterID"));
				record.setRegisterType(rs.getInt("Type"));
				record.setRegisterUid(rs.getString("UID"));
				record.setRegisterName(rs.getString("Name"));
				record.setRegisterImage(rs.getString("Image"));
				record.setRegisterTax(rs.getString("TaxCode"));
				record.setRegisterphone(rs.getString("Phone"));
				record.setRegisteradd(rs.getString("Address"));
				record.setRegistermail(rs.getString("Email"));
				record.setRegisteroweb(rs.getString("Website"));
			}

			getQueryStatement = "SELECT `FullName` FROM `user` where `UID` like '"+record.getRegisterUid()+"'";
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			rs = PrepareStat.executeQuery();
			while (rs.next())
			{
				record.setRegisterUid(rs.getString("FullName"));
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}
	
	public String addRegister(RegisterPTS record)
	{
		try
		{
		//get new id
		PrepareStat = Conn.prepareStatement("select right(max(RegisterID),5) max_id from register");
		ResultSet RSidMax = PrepareStat.executeQuery();
		int new_id=0;
		if (RSidMax.next()) {
			if (RSidMax.getString("max_id")!= null)
			new_id = Integer.parseInt(RSidMax.getString("max_id"));  
		}
		new_id++;
		
		String id = "ID"+("00000" + Integer.toString(new_id) ).substring(Integer.toString(new_id).length());
		
		//insert to db
		String insertQueryStatement = "INSERT  INTO  register  VALUES  (?,?,?,?,?,?,?,?,?,?)";
		PrepareStat = Conn.prepareStatement(insertQueryStatement);
		PrepareStat.setString(1, id);
		PrepareStat.setInt(2, record.getRegisterType());
		PrepareStat.setString(3, record.getRegisterUid());
		PrepareStat.setString(4, record.getRegisterName());
		PrepareStat.setString(5, record.getRegisterImage());
		PrepareStat.setString(6, record.getRegisterTax());
		PrepareStat.setString(7, record.getRegisterphone());
		PrepareStat.setString(8, record.getRegisteradd());
		PrepareStat.setString(9, record.getRegistermail());
		PrepareStat.setString(10, record.getRegisteroweb());
		// execute insert SQL statement
		PrepareStat.executeUpdate();
		return "Success";
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String ApproveRegister(String RegisterID)
	{
		try
		{
			PrepareStat = Conn.prepareStatement("SELECT * FROM register where `RegisterID` like '"+RegisterID+"'");
			ResultSet rs = PrepareStat.executeQuery();
			RegisterPTS record = new RegisterPTS();
			String result="";
			while (rs.next()) {
				record = new RegisterPTS();
				record.setRegisterID(rs.getString("RegisterID"));
				record.setRegisterType(rs.getInt("Type"));
				record.setRegisterUid(rs.getString("UID"));
				record.setRegisterName(rs.getString("Name"));
				record.setRegisterImage(rs.getString("Image"));
				record.setRegisterTax(rs.getString("TaxCode"));
				record.setRegisterphone(rs.getString("Phone"));
				record.setRegisteradd(rs.getString("Address"));
				record.setRegistermail(rs.getString("Email"));
				record.setRegisteroweb(rs.getString("Website"));
			}
			//add this record to approve table
			if (record.getRegisterID()==null) return "This Register record doesn't exist!";
			if (record.getRegisterType()==1)
			{
				Producer pro = new Producer("ProID",record.getRegisterUid(),record.getRegisterName(),record.getRegisterImage(),record.getRegisterTax(),record.getRegisterphone(),
						record.getRegisteradd(),record.getRegistermail(),record.getRegisteroweb(),1);
				ProducerDAO proDAO = new ProducerDAO();
				result = proDAO.addProducer(pro);
			}
			if (record.getRegisterType()==2)
			{
				Transporter t = new Transporter("TID",record.getRegisterUid(),record.getRegisterName(),record.getRegisterImage(),record.getRegisterTax(),record.getRegisterphone(),
						record.getRegisteradd(),record.getRegistermail(),record.getRegisteroweb(),1);
				TransporterDAO tDAO = new TransporterDAO();
				result = tDAO.addTransporter(t);
			}
			if (record.getRegisterType()==3)
			{
				Seller s = new Seller("ProID",record.getRegisterUid(),record.getRegisterName(),record.getRegisterImage(),record.getRegisterTax(),record.getRegisterphone(),
						record.getRegisteradd(),record.getRegistermail(),record.getRegisteroweb(),1);
				SellerDAO sDAO = new SellerDAO();
				result = sDAO.addSeller(s);
			}
			if (result!="Success") return "Can not approve this record!";
			//remove record
			PrepareStat = Conn.prepareStatement("DELETE FROM register where `RegisterID` = '"+RegisterID+"'");
			PrepareStat.executeUpdate();
			//update role
			PrepareStat = Conn.prepareStatement("UPDATE `user` SET `Role` = '"+record.getRegisterType()+"' where `UID` = '"+record.getRegisterUid()+"'");
			PrepareStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}

		return "Approve success!";
	}
	public String checkUser(String uid)
	{
		try {
			PrepareStat = Conn.prepareStatement("SELECT * FROM register where `UID` like '"+uid+"'");
			ResultSet rs = PrepareStat.executeQuery();
			if (rs.next()) return "You only have to register once!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
		return "OK"; 
	}
	
	public String checkTax(String Taxcode)
	{
		try {
			PrepareStat = Conn.prepareStatement("SELECT * FROM register where `TaxCode` like '"+Taxcode+"'");
			ResultSet rs = PrepareStat.executeQuery();
			if (rs.next()) return "Tax code has been used";
			PrepareStat = Conn.prepareStatement("SELECT * FROM producer where `TaxCode` like '"+Taxcode+"'");
			rs = PrepareStat.executeQuery();
			if (rs.next()) return "Tax code has been used";
			PrepareStat = Conn.prepareStatement("SELECT * FROM transporter where `TaxCode` like '"+Taxcode+"'");
			rs = PrepareStat.executeQuery();
			if (rs.next()) return "Tax code has been used";
			PrepareStat = Conn.prepareStatement("SELECT * FROM seller where `TaxCode` like '"+Taxcode+"'");
			rs = PrepareStat.executeQuery();
			if (rs.next()) return "Tax code has been used";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
		return "OK"; 
	}
}
