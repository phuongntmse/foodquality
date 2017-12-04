package fpt.k9.foodquality.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.Rate;

@Repository
public class RateDAO {
	static Connection Conn = JDBC.makeJDBCConnection();
	static PreparedStatement PrepareStat = null;

	public Boolean addRate(Rate mrate) {
		try {
			// check UID and PID in Rate
			PrepareStat = Conn
					.prepareStatement("select * from rate where UID like '"
							+ mrate.getRateuid() + "' and PID like '"
							+ mrate.getRatepid() + "'");
			ResultSet rs = PrepareStat.executeQuery();
			if (rs.next()) {
				PrepareStat = Conn.prepareStatement("update rate set Point = "
						+ mrate.getRatepoint() + " where UID like '"
						+ mrate.getRateuid() + "' and PID like '"
						+ mrate.getRatepid() + "'");
				PrepareStat.executeUpdate();
				return true;
			} else {
				// get new id
				PrepareStat = Conn
						.prepareStatement("select right(max(RateID),5) max_id from rate");
				ResultSet RSidMax = PrepareStat.executeQuery();
				int new_id = 0;
				if (RSidMax.next()) {
					if (RSidMax.getString("max_id") != null)
						new_id = Integer.parseInt(RSidMax.getString("max_id"));
				}
				new_id++;
				String id = "RateID"
						+ ("00000" + Integer.toString(new_id))
								.substring(Integer.toString(new_id).length());
				// insert to db
				String insertQueryStatement = "INSERT  INTO  rate  VALUES  (?,?,?,?,?)";
				PrepareStat = Conn.prepareStatement(insertQueryStatement);
				PrepareStat.setString(1, id);
				PrepareStat.setString(2, mrate.getRateuid());
				PrepareStat.setInt(3, mrate.getRatepoint());
				PrepareStat.setString(4, mrate.getRatepid());
				PrepareStat.setDate(5, mrate.getRatedate());

				// execute insert SQL statement
				PrepareStat.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Float> getAVGRate(String PID, String UID) {
		List<Float> list = new ArrayList<Float>();
		try {
			// makeJDBCConnection();
			String getQueryStatement = "SELECT AVG(Point) avg_point FROM rate where PID like '"
					+ PID + "' group by PID";
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			ResultSet RSidMax = PrepareStat.executeQuery();
			if (RSidMax.next()) {
				list.add(Float.parseFloat(RSidMax.getString("avg_point")));
			}
			getQueryStatement = "select * from rate where UID like '"
							+ UID + "' and PID like '"
							+ PID + "'";
			PrepareStat = Conn.prepareStatement(getQueryStatement);
			RSidMax = PrepareStat.executeQuery();
			if (RSidMax.next()) {
				list.add((float)RSidMax.getInt("Point"));
			}
			else
			{
				list.add((float)0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
