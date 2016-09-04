package ui;

import db.UserDAO;
import entities.Location;
import entities.User;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;


@ManagedBean(name="user")
@SessionScoped
public class UserBean {

    
    //private int id;
	private int userID;
    private String name;
    private String surname;
    private String password;
    private String username;
    
    private User current;

	private String country;
	private String email;
	private String location;
	private String phone;
	private int rating_Bidder;
	private int rating_Seller;
	@Column(name="tax_registration_number")
	private String taxRegistrationNumber;

    
    
    
    @ManagedProperty(value="#{userDAO}")
    private UserDAO userDAO;
    
    public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public String register()
    {
		return "register_user";
    }
	
	public String guest()
    {
		return "/html/all_items";
    }
	
	public String registerUser()
    {
        FacesContext context = FacesContext.getCurrentInstance();  
        User user =  new User();
        user.setName(name);
	    user.setSurname(surname);
	    user.setUsername(username);
	    user.setPassword(password);
	    Location location = new Location();
	    // TODO
	    //location.setLatitude(latitude);
	    //location.setLatitude(latitude);
	    location.setName(this.location);
	    user.setLocation(location);
	    user.setCountry(country);
	    user.setEmail(email);
	    user.setTaxRegistrationNumber(taxRegistrationNumber);
	    user.setPhone(phone);
        user.setPending(1); //user is pending
       
        String message = userDAO.insertUser(user);
        
        if (!message.equals("ok"))
        {
            context.addMessage(null, new FacesMessage(message));
        }
        if (context.getMessageList().size() > 0)
            return null;
        else 
            return "login"; // return sto login page
    }
    
    public String login() {
         current = userDAO.find(username, password);
         
         if(current==null){
        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unknown login, try again"));
             return username = password = null;
         }
         if(current.getAdmin()==1 && current.getPending()==0){
        	 return "/restricted/admin";
         }
         else if(current.getPending()==0){
             System.out.println(username);
             System.out.println(current);
            return "/restricted/welcome";  //prepei na allaksei sth selida plohghshs
        }
         else {  //pending users
        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have not been registered yet!"));
             return username = password = null;
         }
    }
    
    public String logout() {
    	System.out.println("LOGOUT");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/html/login?faces-redirect=true";
    }
    
    public boolean isLoggedIn() {
        return current != null;
    } 
    
    public String blue(){
    	System.out.println("EDIT---CURRENT");
    	//System.out.println(current.getUserID());
	   // System.out.println(current.getUsername());
		return "/restricted/edit";
    }
    
    public String submit(){
    	System.out.println("SUBMIT---CURRENT");
    	System.out.println(current.getUserID());
	   System.out.println(current.getUsername());
	    
    	User user =  current;
    	//user.setUserID(userID);
        user.setName(name);
	    user.setSurname(surname);
	    user.setUsername(username);
	    user.setPassword(password);Location location = new Location();
	    // TODO
	    //location.setLatitude(latitude);
	    //location.setLatitude(latitude);
	    location.setName(this.location);
	    user.setLocation(location);
	    user.setCountry(country);
	    user.setEmail(email);
	    user.setTaxRegistrationNumber(taxRegistrationNumber);
	    user.setPhone(phone);
	    
	    System.out.println("EDIT---NEW");
	    System.out.println(current.getUserID());
	    System.out.println(current.getUsername());

	    
    	userDAO.update(user);
    	
    	//userDAO.update(user);
    	return "/restricted/userlist";
    }
    /*
    public String accept(){
    	//dao update pending
    	return "/restricted/admin"; //msg ok?
    }
    
    public String decline(){
    	//same
    	return "/restricted/admin"; //msg ok?
    }
    */
    
    //////////////////
    public String show(){
    	return "/restricted/userlist";
    }
    
    
    public String pendinglist(){
    	return "/restricted/pendinglist";
    }
    
    public String acceptedlist(){
    	return "/restricted/acceptedlist";
    }
    //////////////////action="#{users.show('showall')}
    
    public boolean getLoginButtonEnabled() {
    	if (current!= null){
    		return false;
    	}
    	return true;
	}
    
    
    
    /*functions*/
    
    public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

    
    public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRating_Bidder() {
		return this.rating_Bidder;
	}

	public void setRating_Bidder(int rating_Bidder) {
		this.rating_Bidder = rating_Bidder;
	}

	public int getRating_Seller() {
		return this.rating_Seller;
	}

	public void setRating_Seller(int rating_Seller) {
		this.rating_Seller = rating_Seller;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTaxRegistrationNumber() {
		return this.taxRegistrationNumber;
	}

	public void setTaxRegistrationNumber(String taxRegistrationNumber) {
		this.taxRegistrationNumber = taxRegistrationNumber;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
    public User getCurrent() {
		return current;
	}
       
}