package db;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities.Item;
import entities.Seller;
import entities.User;

@ManagedBean(name="itemDAO")
@SessionScoped
public class ItemDAO {

	@ManagedProperty(value="#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;
	
	@SuppressWarnings("unchecked")
	public List<Item> getItems(String value,int first,int rows) {
		List<Item> items = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;
		q = em.createNamedQuery("Item.findAll");
		
		if( first!=0 && rows!=0) q.setFirstResult(first).setMaxResults(rows);
		items = q.getResultList();
		
		tx.commit();
		em.close();
		return items;
	}


	@SuppressWarnings("unchecked")
	public List<Item> getItems2(String value) {
		List<Item> items = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;
		q = em.createNamedQuery("Item.findAll");
		
		items = q.getResultList();
		
		tx.commit();
		em.close();
		return items;
	}
	
	
	// prepei na svistei
	public String update(Item item) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(item);
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
	
	
	@ManagedProperty(value="#{categoryDAO}")
    private CategoryDAO categoryDAO;
	
	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}


	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@SuppressWarnings("unchecked")
	public List<Item> getMyItems(Seller user)
	{		
		List<Item> items = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q=null;		
		q = em.createQuery("Select i from Item i where i.seller = :user "); 
		q.setParameter("user", user);
		items = q.getResultList();
		tx.commit();
		em.close();
		return items;		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Item> search(String field,String value)
	{
		if ( field.equals("price")  )
		{
	        try {
	        	Float.valueOf(value);
	        } catch (NumberFormatException e) {
	            return null;
	        }
		}
		
		List<Item> items = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q=null;
		
		if (field.equals("all"))
		{
			q = em.createNamedQuery("Item.findAll");
		}
		else if (field.equals("category"))
		{
			q = em.createQuery("Select i from Item i where :category = ANY (Select c.name from i.categories c)"); 
			q.setParameter("category", value);
		}
		else if (field.equals("description"))
		{
			q = em.createQuery("Select i from Item i where i.description like :description "); 
			q.setParameter("description", "%"+value+"%");
		}
		else if (field.equals("price"))
		{			
			q = em.createQuery("Select i from Item i where i.currently = :price "); 
			q.setParameter("price", Float.valueOf(value));
		}
		else if (field.equals("location"))
		{
			q = em.createQuery("Select i from Item i where :location = ANY (Select l.name from i.location l) "); 
			q.setParameter("location", value);
		}

		if (q!=null)
		{
			items = q.getResultList();
		}
		
		tx.commit();
		em.close();
		return items;		
	}
	
	
	public String insertItem(Item item) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			System.out.println(item.getStarted());
			//em.persist(item);
			em.merge(item.getLocation());	
			em.merge(item);			
			em.flush(); 
			
			tx.commit();
			retMessage = "ok";
			return retMessage; 
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
			retMessage = e.getMessage();
			return retMessage ;
		} finally {
			em.close();
		}
	}
	
	
	
	public String remove(Item item) {
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			item = em.merge(item);
			em.remove(item);
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
	    
	    

	    private List<String> usernames = new ArrayList<String>();
	    private List<Integer> itemids = new ArrayList<Integer>();
	    private List<Item> recommended = new ArrayList<Item>();
	    
	    
	    

}



