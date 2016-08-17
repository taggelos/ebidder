package ui;

import db.UserDAO;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;



@ManagedBean(name="users")
@RequestScoped
public class UserListBean {

    private DataModel<UserBean> userList;
   // private DataModel<UserBean> pendingList;
    //private DataModel<UserBean> noPendingList;
    
    @ManagedProperty(value="#{userDAO}")
    private UserDAO userDAO;
    
    
    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	private String value;
    
    public DataModel<UserBean> getUserlist()
    {
        if (userList == null && value!= null )
        {
        	List<User> list;
        	if(value.equals("showall")){
        		list = userDAO.getUsers("all"); 
        	}
        	else if (value.equals("acceptedlist")) {
        		list = userDAO.getUsers("nopending");
        	}
        	else {
        		list = userDAO.getUsers("pending");
        	}
        	
            if (list !=null) {
                ArrayList<UserBean> uList = new ArrayList<UserBean>();

                for (User u: list)
                {
                    UserBean ub = new UserBean();
                    ub.setName(u.getName());
                    ub.setSurname(u.getSurname());
                    ub.setUsername(u.getUsername());
                    ub.setUserID(u.getUserID());
                    
                    ub.setLocation(u.getLocation());
                    ub.setCountry(u.getCountry());
                    ub.setEmail(u.getEmail());
            	    ub.setTaxRegistrationNumber(u.getTaxRegistrationNumber());
            	    ub.setPhone(u.getPhone());
            	    
                    uList.add(ub);
                }
                userList = new ListDataModel<UserBean>(uList);
            }
        }
        System.out.println(this.value);
        return userList;
    }

    public String show(String value){
    	setValue(value);
    	//System.out.println(this.value);
    	return "/restricted/userlist";	
    }
    
	public UserDAO getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
    
    
}

