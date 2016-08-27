package db;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.ServletContext;

import entities.Category;
import entities.Image;
import entities.ImagePK;
import entities.Item;

@ManagedBean(name="imageDAO")
@SessionScoped
public class ImageDAO {

	@ManagedProperty(value="#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;		
	
	public ImageDAO()
	{
		ServletContext servletContext = (ServletContext) FacesContext
		        .getCurrentInstance().getExternalContext().getContext();
		servletContext.setAttribute("imageDAO", this);
	}

	public String insertImage(Image image) {
		
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {		
			em.persist(image);			
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
	
	
	public Image findImage(int id) throws PersistenceException {
		Image image = new Image();

		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		image= em.find(Image.class,id);
		em.flush();  
		tx.commit();		
		em.close();

		return image;

	}
	
	

	
	 public void setJpaResourceBean(JPAResourceBean jpaResourceBean) {
	        this.jpaResourceBean = jpaResourceBean;
	    }
	
	    public JPAResourceBean getJpaResourceBean() {
	        return jpaResourceBean;
	    }

}
