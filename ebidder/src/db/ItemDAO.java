package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.mysql.jdbc.PreparedStatement;

import entities.Bid;
import entities.Category;
import entities.Item;
import entities.Seller;
import entities.User;
import ui.UserBean;

@ManagedBean(name = "itemDAO")
@SessionScoped
public class ItemDAO {

	@ManagedProperty(value = "#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;

	@SuppressWarnings("unchecked")
	public List<Item> getItems(String value, int first, int rows) {
		List<Item> items = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q;
		q = em.createNamedQuery("Item.findAll");

		if (first != 0 && rows != 0)
			q.setFirstResult(first).setMaxResults(rows);
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

	@ManagedProperty(value = "#{categoryDAO}")
	private CategoryDAO categoryDAO;

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@SuppressWarnings("unchecked")
	public List<Item> getMyItems(Seller user) {
		List<Item> items = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = null;
		q = em.createQuery("Select i from Item i where i.seller = :user ");
		q.setParameter("user", user);
		items = q.getResultList();
		tx.commit();
		em.close();
		return items;
	}

	@SuppressWarnings("unchecked")
	public List<Item> search(String field, String value) {
		if (field.equals("price")) {
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

		Query q = null;

		if (field.equals("all")) {
			q = em.createNamedQuery("Item.findAll");
		} else if (field.equals("category")) {
			q = em.createQuery("Select i from Item i where :category = ANY (Select c.name from i.categories c)");
			q.setParameter("category", value);
		} else if (field.equals("description")) {
			q = em.createQuery("Select i from Item i where i.description like :description ");
			q.setParameter("description", "%" + value + "%");
		} else if (field.equals("price")) {
			q = em.createQuery("Select i from Item i where i.currently = :price ");
			q.setParameter("price", Float.valueOf(value));
		} else if (field.equals("location")) {
			q = em.createQuery("Select i from Item i where :location = ANY (Select l.name from i.location l) ");
			q.setParameter("location", value);
		}

		if (q != null) {
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
			// em.persist(item);
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
			return retMessage;
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


	@ManagedProperty(value="#{userDAO}")
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@ManagedProperty(value = "#{user}")
	private UserBean my_user;

	public UserBean getMy_user() {
		return my_user;
	}

	public void setMy_user(UserBean my_user) {
		this.my_user = my_user;
	}

	
	
	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/
        

        return sortedMap;
    }
	
	public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value : " + entry.getValue());
        }
    }
	
	
	public List<Item> Recommended() {
		List<User> users =  userDAO.getUsers("all", 0, 0);
		Map<String, List<Integer>> map = new HashMap<>();
		for(User u : users) {
			List<Integer> x = new ArrayList<>();
			if ( u.getBidder()!=null ){
				for (Bid b : u.getBidder().getBids()) {
					x.add(b.getItem().getItemID());
				}
			}
			map.put(u.getUsername(), x);
		}

		User u =my_user.getCurrent();    //intersaction
		List<Integer> original = new ArrayList<>();
		if (u.getBidder()!=null)
			for (Bid b : u.getBidder().getBids()) {
				original.add(b.getItem().getItemID());
			}
		Map<String, Integer> counts = new HashMap<>();
		for (String name : map.keySet()){
			int count = 0;
			for (Integer i : map.get(name)){
				if(original.contains(i)){
					count++;
				}
			}
			counts.put(name, count);
		}
		
		counts = sortByValue(counts);   //sort by value
		// printMap(counts);
		
		List<String> same_users = new ArrayList<>(counts.keySet());
		same_users = same_users.subList(1, 6);    
		Map<Integer,Bid> items = new HashMap<>();
		for (String s : same_users) {
			User f = userDAO.find(s);
			if (f.getBidder()!=null){
				for (Bid b : f.getBidder().getBids()) {
					items.put(b.getItem().getItemID(),b);
				}
			}
		}
		if (u.getBidder()!=null)
			for (Bid b : u.getBidder().getBids()) {
				items.remove(b.getBidID());
			}
		List<Item> recomm_items = new ArrayList<>();
		for (Bid b : items.values()) {
			recomm_items.add(b.getItem());
		}
		return recomm_items;
		
	}

}
