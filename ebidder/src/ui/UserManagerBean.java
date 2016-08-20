package ui;

import db.UserDAO;
import entities.User;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;




@ManagedBean(name="manager")
@SessionScoped
//@ViewScoped
public class UserManagerBean {

    //private DataModel<UserBean> userList;

    @ManagedProperty(value="#{userDAO}")
    private UserDAO userDAO;
    
    
    private List<User> userList;
    
    private String dot ="";
    
	private User user;

    public void setUser(User user) {
		this.user = user;
	}
    
    boolean acceptButtonEnabled=false;
    public boolean getAcceptButtonEnabled() {
        return acceptButtonEnabled;
    }
    

    boolean declineButtonEnabled=false;
    public boolean getDeclineButtonEnabled() {
        return declineButtonEnabled;
    }
    
    boolean banButtonEnabled=false;
    public boolean getBanButtonEnabled() {
        return banButtonEnabled;
    }
    
    
	public List<User> getUserlist()
    {
    	Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    	String value = params.get("action");
    	
    	if( !dot.equals(value) && value!= null)  //dot used to recognise what we need
        {
        	//List<User> list;
        	System.out.println("here");
        	
        	if(value.equals("showall")){
        		userList = userDAO.getUsers("all"); 
        		dot = "showall";
        		acceptButtonEnabled = false;
        		declineButtonEnabled = false;
        		banButtonEnabled = false;
        	}
        	else if (value.equals("accepted")) {
        		userList = userDAO.getUsers("nopending");
        		dot = "accepted";
        		acceptButtonEnabled = false;
        		declineButtonEnabled = false;
        		banButtonEnabled = true;
        	}
        	else {
        		userList = userDAO.getUsers("pending");
        		dot = "pending";
        		acceptButtonEnabled = true;
        		declineButtonEnabled = true;
        		banButtonEnabled = false;
        	}
        }
        System.out.println(value);
        return userList;
    }

    public String edit(){
    	System.out.println("EDIT---CURRENT");
    	///////diaforetika koumpia gia kathena
    	dot = "";
		return "/restricted/edit";
    }
    
    
	public UserDAO getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	public String submit(){
    	System.out.println("SUBMIT---CURRENT");
	    System.out.println("EDIT---NEW");
	    dot = "";
    	userDAO.update(user);
    	return "/restricted/userlist";
    }
    
    public String accept(){
    	//dao update pending
    	user.setPending(0);
    	dot = "";
    	userDAO.update(user);
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful Accept"));
    	return "/restricted/userlist"; //msg ok?
    }
    
    public String decline(){
    	//same
    	dot = "";
    	userDAO.remove(user);
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful Operation"));
    	return "/restricted/userlist"; //msg ok?
    }

    
   public String goBack(){
	   dot = "";
	   return "/restricted/admin";
   }
	
	///////////////////////////////////////////////////
	

public int getUserID() {
    return user.getUserID();
  }

  public void setUserID(int userID) {
    user.setUserID(userID);
  }

    
    public String getCountry() {
    return user.getCountry();
  }

  public void setCountry(String country) {
    user.setCountry(country);
  }

  public String getEmail() {
    return user.getEmail();
  }

  public void setEmail(String email) {
    user.setEmail(email);
  }

  public String getLocation() {
    return user.getLocation();
  }

  public void setLocation(String location) {
    user.setLocation(location);
  }

  public String getName() {
    return user.getName();
  }

  public void setName(String name) {
    user.setName(name);
  }

  public String getPassword() {
    return user.getPassword();
  }

  public void setPassword(String password) {
    user.setPassword(password);
  }

  public String getPhone() {
    return user.getPhone();
  }

  public void setPhone(String phone) {
    user.setPhone(phone);
  }

  public int getRating_Bidder() {
    return user.getRating_Bidder();
  }

  public void setRating_Bidder(int rating_Bidder) {
    user.setRating_Bidder(rating_Bidder);
  }

  public int getRating_Seller() {
    return user.getRating_Seller();
  }

  public void setRating_Seller(int rating_Seller) {
    user.setRating_Seller(rating_Seller);
  }

  public String getSurname() {
    return user.getSurname();
  }

  public void setSurname(String surname) {
    user.setSurname(surname);
  }

  public String getTaxRegistrationNumber() {
    return user.getTaxRegistrationNumber();
  }

  public void setTaxRegistrationNumber(String taxRegistrationNumber) {
    user.setTaxRegistrationNumber(taxRegistrationNumber);
  }

  public String getUsername() {
    return user.getUsername();
  }

  public void setUsername(String username) {
    user.setUsername(username);
  }
    
    
    
}





