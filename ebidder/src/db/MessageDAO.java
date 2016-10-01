package db;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities.Message;
import entities.User;

@ManagedBean(name="messageDAO")
@SessionScoped
public class MessageDAO {
	
	@ManagedProperty(value="#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;
	
	@SuppressWarnings("unchecked")
	public List<Message> getMesages(User sender,User receiver) {
		List<Message> messages = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;		
		q = em.createQuery("Select m from Message m where m.user1 = :from and m.user2 = :to order by m.time "); 
		q.setParameter("from", sender);
		q.setParameter("to", receiver);
		
		messages = q.getResultList();

		tx.commit();
		em.close();
		return messages; 
	}
		
	public String insertMessage(Message message) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			
			em.persist(message);			
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
		
	@SuppressWarnings("unchecked")
	public List<Message> getConversations(User user,boolean incoming) {
		List<Message> messages = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;
		if (incoming)
		{
			q = em.createQuery("select m from Message m where m.user2 = :user group by m.user1 having MAX(m.time)>0 "); 
			q.setParameter("user", user);
		}
		else
		{
			q = em.createQuery("Select m from Message m where m.user1 = :user group by m.user1 having MAX(m.time)>0 ");
			q.setParameter("user", user);
		}
		
		messages = q.getResultList();

		tx.commit();
		em.close();
		return messages; 
	}
	
	
	public int getNumOfUnread(User user) {
		int messages;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;
		//Allakse tin erwtisi etsi wste na perneis ton arithmo twn adiavastwm munhmatwn
		//q = em.createQuery("select m from Message m where m.user2 = :user and m.unread=true group by m.user1 having MAX(m.time)>0 "); 
		//q.setParameter("user", user);	
		//messages = q.getResultList();
		
		messages= 7; // gia test. tha fugei
		
		tx.commit();
		em.close();
		return messages; 
	}
	
	// prepei na diagrafei ola ta munimata metaksu autwn twn duo
	public void delete_conversation(User user1,User user2) {
		
	}
	
	public void setJpaResourceBean(JPAResourceBean jpaResourceBean) {
	       this.jpaResourceBean = jpaResourceBean;
	   }
	
	public JPAResourceBean getJpaResourceBean() {
	       return jpaResourceBean;
	   }

}


