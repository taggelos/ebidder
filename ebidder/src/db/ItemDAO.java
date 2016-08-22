package db;

import entities.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@ManagedBean(name="itemDAO")
@SessionScoped
public class ItemDAO {

	@ManagedProperty(value="#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;
	
	@SuppressWarnings("unchecked")
	public List<Item> getItems(String value) {
		List<Item> items = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;
		q = em.createNamedQuery("Item.findAll"); //Query q = em.createQuery("Select i from item i");
	
		items = q.getResultList();

		tx.commit();
		em.close();
		return items;
	}
	
/*	
	@SuppressWarnings("unchecked")
	public User find(String username, String password) {
		User user = null;

		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select u from User u where u.username = :username and u.password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		List<User> users = q.getResultList();
		tx.commit();
		em.close();

		if (users != null && users.size() == 1) {
			user = users.get(0);
		}

		return user;

	}
*/
/*	
	public String update(User user) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(user);
			em.flush();  
			tx.commit();
			retMessage = "ok";
			return retMessage;
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
			retMessage = e.getMessage();
			return retMessage;
		} finally {
			em.close();
		}
	}
	*/
	
	public String insertItem(Item item) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(item);
			em.flush();  
			tx.commit();
			retMessage = "ok";
			return retMessage;
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
			retMessage = e.getMessage();
			return retMessage;
		} finally {
			em.close();
		}
	}
	/*
	public String remove(User user) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			user = em.merge(user);
			em.remove(user);
			em.flush();  
			tx.commit();
			retMessage = "ok";
			return retMessage;
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
			retMessage = e.getMessage();
			return retMessage;
		} finally {
			em.close();
		}
	}
	*/
	
	 public void setJpaResourceBean(JPAResourceBean jpaResourceBean) {
	        this.jpaResourceBean = jpaResourceBean;
	    }

	    public JPAResourceBean getJpaResourceBean() {
	        return jpaResourceBean;
	    }

}

