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
import entities.Seller;
import entities.User;

@ManagedBean(name="messageDAO")
@SessionScoped
public class MessageDAO {
	
	@ManagedProperty(value="#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(User sender,User receiver) {
		List<Message> messages = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;		
		q = em.createQuery("Select m from Message m where m.seller = :from and m.bidder = :to order by m.time "); 
		q.setParameter("from", sender.getSeller());
		q.setParameter("to", receiver.getBidder());
		
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
		String username = user.getName();
		Query q;
		if (incoming)
		{
			q = em.createQuery("select m from Message m where m.bidder.userUsername = :user group by m.seller.userUsername having MAX(m.time)>0 "); 
			q.setParameter("user", username);
		}
		else
		{
			Seller s = user.getSeller();
			q = em.createQuery("Select m from Message m where m.seller = :s group by m.seller having MAX(m.time)>0 ");
			q.setParameter("s", s);
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
		//q = em.createQuery("select count(*) from Message m where m.seller = :user and m.unread=true group by m.bidder "); 
		//q.setParameter("user", user);	
		//messages = (int) q.getResultList().get(0);
		
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


