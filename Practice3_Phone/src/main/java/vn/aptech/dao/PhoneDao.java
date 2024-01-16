package vn.aptech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import vn.aptech.entity.Phone;

public class PhoneDao {
	EntityManagerFactory emf =Persistence.createEntityManagerFactory("phoneJPA");
	/*
	 * private Connection connectDb () throws ClassNotFoundException, SQLException{
	 * Class.forName("com.mysql.cj.jdbc.Driver"); String url =
	 * "jdbc:mysql://localhost:3306/practice"; return
	 * DriverManager.getConnection(url, "root","1313"); }
	 */
	public List<Phone> findAll(){
		EntityManager em = emf.createEntityManager();
		List<Phone> result =  new ArrayList<>();
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("SELECT o FROM Phone o");
			if(q.getResultList()!=null) {
				result.addAll(q.getResultList());			
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return result;
	}
	public boolean create(Phone phone){
		EntityManager em = emf.createEntityManager();
		boolean result = false;
		try {
			em.getTransaction().begin();
			em.persist(phone);
			em.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			result = false;
		}finally {
			em.close();
		}
		return result;
	}
	public Phone findById(int id) {
		EntityManager em = emf.createEntityManager();
		Phone result = new Phone();
		try {
			em.getTransaction().begin();
			result = em.find(Phone.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return result;
		
	}
	public boolean update(Phone phone) {
		EntityManager em = emf.createEntityManager();
		boolean result =false ;
		try {
			em.getTransaction().begin();
			em.merge(phone);
			em.getTransaction().commit();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			result =false;
		}finally {
			em.close();
		}
		return result ;
	}
	public boolean delete(int id) {
		EntityManager em = emf.createEntityManager();
		boolean result =false ;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("DELETE FROM Phone WHERE pId=:id");
			q.setParameter("id", id);
			result = q.executeUpdate()>0? true:false;
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return result ;
	}
}
