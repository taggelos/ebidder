package ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import db.ItemDAO;
import db.UserDAO;
import entities.BidPK;
import entities.Category;
import entities.Item;
import entities.User;

@ManagedBean(name="item")
@RequestScoped
public class ItemBean {
    
	private BidPK id;

	private String name;
	private List<Category> categories;
	
	private List<String> cate;
	
	public List<String> getCate() {
		return cate;
	}

	public void setCate(List<String> cate) {
		this.cate = cate;
	}

	private int buy_price;
	private int first_bid;
	private float latitude;
	private float longitude;
	private String country;
	private Date started;  
	private Date ends;  
	private String  description;
	private String images; //tha allaksei se lista apo fotos

	private int amount;  

	private String current_category;
	
	
    @ManagedProperty(value="#{itemDAO}")
    private ItemDAO itemDAO;
	

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	/* Pages */
    public String manage(){
    	return "/restricted/manage";
    }
    
    public String create(){
    	return "/restricted/create_item";
    }
    
    public String edit(){
    	return "/restricted/edit_item";
    }
    
    /* Operations */
    public String create_item(){
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
    	//item.seller()..getClass()..
    	item.setDescription(description);
    	//item.setImages(images);
    	
   /* 	
    	String message = itemDAO.insertItem(item);
    	
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
    	/*Category category= new Category();
    	category.setName(current_category);
    	Add_Category(category);
    	return null; */	
    	
    	String category="mpika";
    	cate.add(category);
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

	void Add_Category(Category category)
	{
		categories.add(category);
	}
	
	public List<Category> getCategory() {
		return categories;
	}

	public void setCategory(List<Category> categories) {
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

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
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

}