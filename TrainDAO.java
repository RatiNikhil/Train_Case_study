package trainticketapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TrainDAO {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost:3306/traininfo?autoReconnect=true&useSSL=false";
	String USERNAME = "root";
	String PASSWORD = "Rn@123";
	Train train;

	public void trainDetails() {
		try {


			Class.forName(DRIVER_NAME);

			Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("Connected");



			Statement stmt = con.createStatement();
			ResultSet rsDetails = stmt.executeQuery("Select * from TRAINS");
			while (rsDetails.next()) {
				System.out.println("Train No : " + rsDetails.getInt(1) + " Train Name : " + rsDetails.getString(2)
				+ " Source : " + rsDetails.getString(3) + " Destination : " + rsDetails.getString(4)
				+ " Price : " + rsDetails.getInt(5) + "\n");
			}
			con.close();

		} catch (Exception e) {
			System.out.println("Not found");
		}
	}

	public Train findTrain(int trainNo) 
	{
		try
		{

			// Step 1
			Class.forName(DRIVER_NAME);

			// Step 2
			Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("Connected");

			// Step 3
			PreparedStatement ps = con.prepareStatement("SELECT * FROM TRAINS WHERE TRAIN_NO = " + trainNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				train = new Train(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5));
			}

			con.close();

		} 
		catch (Exception e) 
		{
			System.out.println("Not found");
		}
		return train;
	}

}