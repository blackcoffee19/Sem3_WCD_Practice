package vn.aptech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.aptech.entity.Glass;

public class GlassDao {
	private Connection connectDb () throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/practice";
		return DriverManager.getConnection(url, "root","1313");
	}
	public List<Glass> findAll(){
		List<Glass> result =  new ArrayList<>();
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM glass");
			ResultSet rs =stm.executeQuery();
			while(rs.next()) {
				Glass bk = new Glass();
				bk.setGlassId(rs.getInt(1));
				bk.setName(rs.getString(2));
				bk.setPrice(rs.getInt(3));
				bk.setImage(rs.getString(4));
				result.add(bk);
			}
			rs.close();
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean create(Glass glass){
		boolean result = false;
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("INSERT INTO glass (name,price,image) VALUES (?,?,?)");
			stm.setString(1, glass.getName());
			stm.setInt(2, glass.getPrice());
			stm.setString(3, glass.getImage());
			result = stm.executeUpdate() >0;
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	public Glass findById(int id) {
		Glass result = new Glass();
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM glass WHERE glassId=?");
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			while(res.next()) {
				result.setGlassId(res.getInt(1));
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
	public boolean update(Glass glass) {
		boolean result =false ;
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("UPDATE glass SET name=?,price=?,image=? WHERE glassId=?");
			stm.setString(1, glass.getName());
			stm.setInt(2, glass.getPrice());
			stm.setString(3, glass.getImage());
			stm.setInt(4, glass.getGlassId());
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
			PreparedStatement stm = con.prepareStatement("DELETE FROM glass WHERE glassId=?");
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
