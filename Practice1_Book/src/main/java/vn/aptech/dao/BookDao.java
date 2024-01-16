package vn.aptech.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import vn.aptech.entity.Book;

public class BookDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testJpaPU");
	public List<Book> findAll(){
		EntityManager em = emf.createEntityManager();
		List<Book> result =  new ArrayList<>();
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("SELECT o FROM Book o");
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
	public boolean create(Book book){
		EntityManager em = emf.createEntityManager();
		boolean result = false;
		try {
			em.getTransaction().begin();
			em.persist(book);
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
	public Book findByCode(String code) {
		EntityManager em = emf.createEntityManager();
		Book result = new Book();
		try {
			em.getTransaction().begin();
			result = em.find(Book.class, code);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return result;
		
	}
	public boolean update(Book book) {
		EntityManager em = emf.createEntityManager();
		boolean result =false ;
		try {
			em.getTransaction().begin();
			em.merge(book);
			em.getTransaction().commit();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return result ;
	}
	public boolean delete(String code) {
		EntityManager em = emf.createEntityManager();
		boolean result =false ;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("DELETE FROM Book WHERE bookCode=:bookCode");
			q.setParameter("bookCode", code);
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
