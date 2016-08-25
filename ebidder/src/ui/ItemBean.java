package ui;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import db.CategoryDAO;
import db.ItemDAO;
import db.UserDAO;
import entities.BidPK;
import entities.Category;
import entities.Item;
import entities.User;

@ManagedBean(name="item")
@ViewScoped
public class ItemBean {
    
	private BidPK id;

	private String name;
	private List<Category> categories = new ArrayList<Category>();
	private int buy_price;
	private int first_bid;
	private float latitude;
	private float longitude;
	private String country;
	private Timestamp started= new Timestamp(0); 
	private Timestamp ends=new Timestamp(0);
	private String  description;
	private String images; //tha allaksei se lista apo fotos

	private int amount;  

	private String current_category;
	private Category category_for_delete;
	
	private String started_day;
	private String started_month;
	private String started_year;
	private String started_hour;
	private String started_minute;
	
	private String ends_day;
	private String ends_month;
	private String ends_year;
	private String ends_hour;
	private String ends_minute;
	
	@ManagedProperty(value="#{itemDAO}")
    private ItemDAO itemDAO;
	
    @ManagedProperty(value="#{user}")
    private UserBean my_user;

	private  List<Item> all_my_items =  new ArrayList<Item>();

	
	/* Pages */
    public String manage(){
    	return "/restricted/manage";
    }
    
    public String all_items(){
    	return "/restricted/all_items";
    }
    
    public String create(){
    	return "/restricted/create_item";
    }
    
    public String edit(){
    	return "/restricted/edit_item";
    }
    
    /* Operations */
	@SuppressWarnings("deprecation")
	public String create_item(){
    	
		// Thelei ki allous elegxous stis times twn pediwn
    	started.setDate(Integer.valueOf(started_day)); 
    	started.setMonth(Integer.valueOf(started_month));
    	started.setYear(Integer.valueOf(started_year)-1900);   	
    	started.setHours(Integer.valueOf(started_hour));
    	started.setMinutes(Integer.valueOf(started_minute));
    	
    	ends.setDate(Integer.valueOf(ends_day));
    	ends.setMonth(Integer.valueOf(ends_month));
    	ends.setYear(Integer.valueOf(ends_year)-1900);
    	ends.setHours(Integer.valueOf(ends_hour));
    	ends.setMinutes(Integer.valueOf(ends_minute)); 
    	
    	FacesContext context = FacesContext.getCurrentInstance(); 
    	Item item =  new Item();
    	item.setName(name);
    	item.setCategories(categories);
    	item.setCurrently(first_bid);
    	item.setBuy_Price(buy_price);
    	item.setFirst_Bid(first_bid);
    	item.setLatitude(latitude);
    	item.setLongitude(longitude);
    	item.setCountry(country);
    	item.setStarted(started);
    	item.setEnds(ends);
    	item.setUser(my_user.getCurrent());
    	item.setDescription(description);
    	//item.setImages(images);
    	
    	String message = itemDAO.insertItem(item);
    	    	
    	/*
    	if (!message.equals("ok"))
        {
            context.addMessage(null, new FacesMessage(message));
        }
        if (context.getMessageList().size() > 0)
            return null;
        else 
        	return "/restricted/manage";  
        	*/
    	return null;
    }	
    
    public String add()
    {
    	Category category= new Category();
    	category.setName(current_category);
    	categories.add(category);
    	return null;
    }
    
    public String delete_category()
    {
    	categories.remove(category_for_delete);
    	return null;
    }
    
    /* Getters and Setters */
    public BidPK getId() {
		return this.id;
	}

	public void setId(BidPK id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public int getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}

	public int getFirst_bid() {
		return first_bid;
	}

	public void setFirst_bid(int first_bid) {
		this.first_bid = first_bid;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getStarted() {
		return started;
	}

	public void setStarted(Timestamp started) {
		this.started = started;
	}

	public Timestamp getEnds() {
		return ends;
	}

	public void setEnds(Timestamp ends) {
		this.ends = ends;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getCurrent_category() {
		return current_category;
	}

	public void setCurrent_category(String current_category) {
		this.current_category = current_category;
	}

    public Category getCategory_for_delete() {
		return category_for_delete;
	}

	public void setCategory_for_delete(Category category_for_delete) {
		this.category_for_delete = category_for_delete;
	}
		
	public String getStarted_day() {
		return started_day;
	}
	
	public void setStarted_day(String started_day) {
		this.started_day = started_day;
	}

	public String getStarted_month() {
		return started_month;
	}

	public void setStarted_month(String started_month) {
		this.started_month = started_month;
	}

	public String getStarted_year() {
		return started_year;
	}

	public void setStarted_year(String started_year) {
		this.started_year = started_year;
	}

	public String getStarted_hour() {
		return started_hour;
	}

	public void setStarted_hour(String started_hour) {
		this.started_hour = started_hour;
	}

	public String getStarted_minute() {
		return started_minute;
	}

	public void setStarted_minute(String started_minute) {
		this.started_minute = started_minute;
	}

	public String getEnds_day() {
		return ends_day;
	}

	public void setEnds_day(String ends_day) {
		this.ends_day = ends_day;
	}

	public String getEnds_month() {
		return ends_month;
	}

	public void setEnds_month(String ends_month) {
		this.ends_month = ends_month;
	}

	public String getEnds_year() {
		return ends_year;
	}

	public void setEnds_year(String ends_year) {
		this.ends_year = ends_year;
	}

	public String getEnds_hour() {
		return ends_hour;
	}

	public void setEnds_hour(String ends_hour) {
		this.ends_hour = ends_hour;
	}

	public String getEnds_minute() {
		return ends_minute;
	}

	public void setEnds_minute(String ends_minute) {
		this.ends_minute = ends_minute;
	}	

	public List<Item> getAll_my_items() {
		return all_my_items;
	}

	public void setAll_my_items(List<Item> all_my_items) {
		this.all_my_items = all_my_items;
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	public UserBean getMy_user() {
		return my_user;
	}

	public void setMy_user(UserBean my_user) {
		this.my_user = my_user;
	}
	
}