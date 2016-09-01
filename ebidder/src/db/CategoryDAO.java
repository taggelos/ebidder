package db;

import entities.Category;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@ManagedBean(name="categoryDAO")
@SessionScoped
public class CategoryDAO {

	@ManagedProperty(value="#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;
	
	@SuppressWarnings("unchecked")
	public List<Category> getCategories(String value) {
		List<Category> categories = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;
		q = em.createNamedQuery("Category.findAll"); //Query q = em.createQuery("Select i from category i");
	
		categories = q.getResultList();

		tx.commit();
		em.close();
		return categories;
	}
	
	public String insertCategory(Category category) {
		System.out.println(category.getName());
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			//if (!exist(category)){
				System.out.println("Aerrrraaa");
				em.persist(category);
				em.flush();  
				tx.commit();
			//}
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
	public boolean exist(Category category) {
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select c from category c where c.Name = :name ");
		q.setParameter("name", category.getName());
		List<Category> result = q.getResultList();
		tx.commit();
		em.close();
		if (result!=null)
			return result.size()>0;
		else
			return false;
	}
	
	
	public void setJpaResourceBean(JPAResourceBean jpaResourceBean) {
		this.jpaResourceBean = jpaResourceBean;
	}
	
	public JPAResourceBean getJpaResourceBean() {
		return jpaResourceBean;
	}

}
	
	