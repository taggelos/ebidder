package db;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities.User;

@ManagedBean(name = "userDAO")
@SessionScoped
public class UserDAO {

	@ManagedProperty(value = "#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;

	@SuppressWarnings("unchecked")
	public List<User> getUsers(String value, int first, int rows) {
		List<User> users = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;
		if (value == "all") {
			q = em.createNamedQuery("User.findAll"); // Query q =
														// em.createQuery("Select
														// u from users u");
		} else if (value == "nopending") {
			q = em.createQuery("Select u from User u where u.pending!=1");
		} else {
			q = em.createQuery("Select u from User u where u.pending=1");
		}
		if (rows != 0 && first != 0)
			q.setFirstResult(first).setMaxResults(rows);
		users = q.getResultList();

		tx.commit();
		em.close();
		return users;
	}

	/*
	 * @SuppressWarnings("unchecked") public List<User> getItems(User user) {
	 * List<Item> users = null; EntityManager em =
	 * jpaResourceBean.getEMF().createEntityManager(); EntityTransaction tx =
	 * em.getTransaction(); tx.begin();
	 * 
	 * Query q; q = em.createQuery("Select u from user,item u");
	 * 
	 * users = q.getResultList();
	 * 
	 * tx.commit(); em.close(); return users; }
	 */

	@SuppressWarnings("unchecked")
	public User find(String username, String password) {
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		User user = null;
		Query q = em.createQuery("Select u from User u where u.username = :username");
		q.setParameter("username", username);
		List<User> users = q.getResultList();
		tx.commit();
		em.close();
		if (users != null && users.size() == 1) {
			user = users.get(0);
		}

		if (user == null || !user.getPassword().equals(password) || !user.getUsername().equals(username)) {
			return null;
		}

		return user;

	}

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

	public String insertUser(User user) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(user.getLocation());
			em.persist(user);
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

	public String remove(User user) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			if (!em.contains(user)) {
				user = em.merge(user);
			}

			em.remove(user);

			//em.remove(user);
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

	public void setJpaResourceBean(JPAResourceBean jpaResourceBean) {
		this.jpaResourceBean = jpaResourceBean;
	}

	public JPAResourceBean getJpaResourceBean() {
		return jpaResourceBean;
	}

	public User find(String username) {
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		User user = em.find( User.class,username);

		tx.commit();
		em.close();


		return user;
	}

}
