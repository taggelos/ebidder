package ui;

import db.CategoryDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;



@ManagedBean(name="category")
@SessionScoped
public class CategoryBean {

	private String name;    
    

	@ManagedProperty(value="#{categoryDAO}")
    private CategoryDAO categoryDAO;
    

	// Getters and Setters
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}


       
}