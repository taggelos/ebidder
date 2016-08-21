package db;

import entities.Category;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
	
	 public void setJpaResourceBean(JPAResourceBean jpaResourceBean) {
	        this.jpaResourceBean = jpaResourceBean;
	    }

	    public JPAResourceBean getJpaResourceBean() {
	        return jpaResourceBean;
	    }

}
	
	