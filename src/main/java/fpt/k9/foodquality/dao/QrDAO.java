package fpt.k9.foodquality.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Product;
import fpt.k9.foodquality.model.QRCode;
import fpt.k9.foodquality.others.UploadImage;

@Repository
public class QrDAO {
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;

	public List<Object> getQR(String qr) {
		List<Object> list = new ArrayList<Object>();
		QRCode myQR = new QRCode();
		try {
			String getQueryStatement = "SELECT * FROM qrcode where QID like '"
					+ qr + "'";

			PrepareStat = Conn.prepareStatement(getQueryStatement);
			// Execute the Query, and get a java ResultSet
			ResultSet rs = PrepareStat.executeQuery();

			// Let's iterate through the java ResultSet
			while (rs.next()) {
				myQR.setQid(qr);
				myQR.setQrefID(rs.getString("ReferenceID"));
				myQR.setQimage(rs.getString("Image"));
			}
			list.add(myQR);
			if (myQR.getQrefID() != "") {
				if (myQR.getQrefID().substring(0, 3).equalsIgnoreCase("PID")) {
					ProductDAO pr = new ProductDAO();
					Product myproduct = pr.getProductbyID(myQR.getQrefID());
					list.add(myproduct);
					ProducerDAO producer = new ProducerDAO();
					list.add(producer.getProducerbyID(myproduct.getPproid()));
					SellerDAO seller = new SellerDAO();
					list.add(seller.getSellerbyID(myproduct.getPsid()));
					TransporterDAO transporter = new TransporterDAO();
					list.add(transporter.getTransbyID(myproduct.getPtid()));
					FeedbackDAO feedback = new FeedbackDAO();
					list.add(feedback.getFeedbackbyPID(myQR.getQrefID()));
				}
				if (myQR.getQrefID().substring(0, 3).equalsIgnoreCase("Pro")) {
					ProducerDAO producer = new ProducerDAO();
					list.add(producer.getProducerbyID(myQR.getQrefID()));
				}
				if (myQR.getQrefID().substring(0, 3).equalsIgnoreCase("SID")) {
					SellerDAO seller = new SellerDAO();
					list.add(seller.getSellerbyID(myQR.getQrefID()));
				}
				if (myQR.getQrefID().substring(0, 3).equalsIgnoreCase("TID")) {
					TransporterDAO transporter = new TransporterDAO();
					list.add(transporter.getTransbyID(myQR.getQrefID()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String addQR(String RefID) {
		try {
			// get new id
			PrepareStat = Conn
					.prepareStatement("select right(max(QID),5) max_id from qrcode");
			ResultSet RSidMax = PrepareStat.executeQuery();
			int new_id = 0;
			if (RSidMax.next()) {
				if (RSidMax.getString("max_id") != null)
					new_id = Integer.parseInt(RSidMax.getString("max_id"));
			}
			new_id++;

			String id = "QID"
					+ ("00000" + Integer.toString(new_id)).substring(Integer
							.toString(new_id).length());

			// get link image
			String result = UploadImage.uploadImage(id);
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(result);
			String data = json.get("data").toString();
			json = (JSONObject) parser.parse(data);
			String image = json.get("link").toString();
			// insert to db
			String insertQueryStatement = "INSERT  INTO  qrcode  VALUES  (?,?,?)";
			PrepareStat = Conn.prepareStatement(insertQueryStatement);
			PrepareStat.setString(1, id);
			PrepareStat.setString(2, RefID);
			PrepareStat.setString(3, image);
			PrepareStat.executeUpdate();
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

}
