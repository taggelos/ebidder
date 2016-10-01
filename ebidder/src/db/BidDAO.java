package db;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import entities.Bid;

@ManagedBean(name="bidDAO")
@SessionScoped
public class BidDAO {

	@ManagedProperty(value="#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;
			
	public String update(Bid bid) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(bid);
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

}

