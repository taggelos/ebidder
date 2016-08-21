package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import db.CategoryDAO;
import entities.Category;

@ManagedBean(name="categories")
@RequestScoped
public class CategoryListBean {
	
	private DataModel<CategoryBean> categoryList;
	
    @ManagedProperty(value="#{categoryDAO}")
    private CategoryDAO categoryDAO;
	    
	 
    public DataModel<CategoryBean> getCategorylist()
    {
    	Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    	String value = params.get("action");
        if (categoryList == null && value!= null )
        {
        	List<Category> list;
        	list = categoryDAO.getCategories("all"); 
        	
            if (list !=null) {
                ArrayList<CategoryBean> cList = new ArrayList<CategoryBean>();

                for (Category u: list)
                {
                    CategoryBean cb = new CategoryBean();
                    cb.setName(u.getName());
                    cList.add(cb);
                }
                categoryList = new ListDataModel<CategoryBean>(cList);
            }
        }
        return categoryList;
    }

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}


	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	    
	 
}
