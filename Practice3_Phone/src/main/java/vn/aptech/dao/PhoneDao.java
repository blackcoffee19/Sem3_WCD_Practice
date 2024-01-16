package vn.aptech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.aptech.entity.Phone;

public class PhoneDao {
	private Connection connectDb () throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/practice";
		return DriverManager.getConnection(url, "root","1313");
	}
	public List<Phone> findAll(){
		List<Phone> result =  new ArrayList<>();
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM phone");
			ResultSet rs =stm.executeQuery();
			while(rs.next()) {
				Phone phone = new Phone();
				phone.setPId(rs.getInt(1));
				phone.setName(rs.getString(2));
				phone.setPrice(rs.getInt(3));
				phone.setImage(rs.getString(4));
				result.add(phone);
			}
			rs.close();
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean create(Phone phone){
		boolean result = false;
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("INSERT INTO phone (name,price,image) VALUES (?,?,?)");
			stm.setString(1, phone.getName());
			stm.setInt(2, phone.getPrice());
			stm.setString(3, phone.getImage());
			result = stm.executeUpdate() >0;
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	public Phone findById(int id) {
		Phone result = new Phone();
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM phone WHERE pId=?");
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			while(res.next()) {
				result.setPId(res.getInt(1));
				result.setName(res.getString(2));
				result.setPrice(res.getInt(3));
				result.setImage(res.getString(4));
			}
			res.close();
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public boolean update(Phone phone) {
		boolean result =false ;
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("UPDATE phone SET name=?,price=?,image=? WHERE pId=?");
			stm.setString(1, phone.getName());
			stm.setInt(2, phone.getPrice());
			stm.setString(3, phone.getImage());
			stm.setInt(4, phone.getPId());
			result = stm.executeUpdate()>0;
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result ;
	}
	public boolean delete(int id) {
		boolean result =false ;
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("DELETE FROM phone WHERE pId=?");
			stm.setInt(1, id);
			int num = stm.executeUpdate();
			result = num>0;
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result ;
	}
}
