package vn.aptech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.aptech.entity.Book;

public class BookDao {
	private Connection connectDb () throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/practice";
		return DriverManager.getConnection(url, "root","1313");
	}
	public List<Book> findAll(){
		List<Book> result =  new ArrayList<>();
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM books");
			ResultSet rs =stm.executeQuery();
			while(rs.next()) {
				Book bk = new Book();
				bk.setBookCode(rs.getString(1));
				bk.setTitle(rs.getString(2));
				bk.setPrice(rs.getInt(3));
				bk.setPublisher(rs.getString(4));
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
	public boolean create(Book book){
		boolean result = false;
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("INSERT INTO books (bookCode,title,price,publisher) VALUES (?,?,?,?)");
			stm.setString(1,book.getBookCode());
			stm.setString(2, book.getTitle());
			stm.setInt(3, book.getPrice());
			stm.setString(4, book.getPublisher());
			result = stm.executeUpdate() >0;
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	public Book findByCode(String code) {
		Book result = new Book();
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM books WHERE bookCode=?");
			stm.setString(1, code);
			ResultSet res = stm.executeQuery();
			while(res.next()) {
				result.setBookCode(res.getString(1));
				result.setTitle(res.getString(2));
				result.setPrice(res.getInt(3));
				result.setPublisher(res.getString(4));
			}
			res.close();
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public boolean update(Book book) {
		boolean result =false ;
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("UPDATE books SET title=?,price=?,publisher=?WHERE bookCode=?");
			stm.setString(1, book.getTitle());
			stm.setInt(2, book.getPrice());
			stm.setString(3, book.getPublisher());
			stm.setString(4, book.getBookCode());
			result = stm.executeUpdate()>0;
			stm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result ;
	}
	public boolean delete(String code) {
		boolean result =false ;
		try {
			Connection con = connectDb();
			PreparedStatement stm = con.prepareStatement("DELETE FROM books WHERE bookCode=?");
			stm.setString(1, code);
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
