package ui;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import db.ItemDAO;
import db.UserDAO;
import entities.Category;
import entities.Item;
import entities.Photo;



@ManagedBean(name="my_item_list")
@SessionScoped
public class MyItemListBean {	
	
    @ManagedProperty(value="#{user}")
    private UserBean my_user;

	private  List<Item> all_my_items = new ArrayList<Item>();
	
	private boolean can_change =false;
	private Item item_for_change;

	@ManagedProperty(value="#{itemDAO}")
    private ItemDAO itemDAO;
	
    @ManagedProperty(value="#{userDAO}")
    private UserDAO userDAO;

	private String current_category;
	private Category category_for_delete;
	private Part current_image_part;	
	private MyImage my_image_for_delete;
	private List<MyImage> my_images = new ArrayList<MyImage>();
	private Photo saved_image_for_delete;

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

	
	// Functions    
	public String update_item() throws ParseException
	{
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm" );
		String str_started= getStarted_year()+"-"+getStarted_month()+"-"+getStarted_day()+" "+getStarted_hour()+":"+getStarted_minute();	
		Date started_date =  df.parse(str_started);  	    	
		String str_ends= getEnds_year()+"-"+getEnds_month()+"-"+getEnds_day()+" "+getEnds_hour()+":"+getEnds_minute();	
	    Date ends_date =  df.parse(str_ends);
	    item_for_change.setStarted(null);
	    item_for_change.setEnds(null);
	    item_for_change.setStarted(started_date);
	    item_for_change.setEnds(ends_date);
		item_for_change.setCurrently(item_for_change.getFirst_Bid());				
		
		List<Photo> temp_images_list = new ArrayList<Photo>();
		Photo temp_image;
		for (MyImage my_image : my_images) {
			temp_image= new Photo();
			temp_image.setItem(item_for_change);
			temp_image.setPhoto(my_image.getImage());
			temp_images_list.add(temp_image);
		}
		temp_images_list.addAll(item_for_change.getPhotos());
		item_for_change.setPhotos(temp_images_list);
		
		String message = itemDAO.insertItem(item_for_change);
		if (!message.equals("ok")) { 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
			return null;
		};
    	return "/restricted/manage";
    }
	
	public String add_category() {
		Category temp= new Category(); temp.setName(current_category);
		List<Category> temp_cat_list=new ArrayList<Category>();
		temp_cat_list=item_for_change.getCategories();
		if (temp_cat_list.contains(temp)) {
			// Emfanise minuma stin html
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have already put this category"));
			return null;
		}

		temp_cat_list.add(temp);
		item_for_change.setCategories(temp_cat_list);
		return null;
	}
	
	public String delete_category() {
		List<Category> temp_cat_list= new ArrayList<Category>();
		temp_cat_list= item_for_change.getCategories();
		temp_cat_list.remove(category_for_delete);
		item_for_change.setCategories(temp_cat_list);
		return null;
	}
	
	public String add_image() throws IOException
	{		
		MyImage myimage = new MyImage();
		myimage.setFilename(current_image_part.getSubmittedFileName());

		InputStream input = current_image_part.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[16777215];
		for (int length = 0; (length = input.read(buffer)) > 0;)
			output.write(buffer, 0, length);
		myimage.setImage(output.toByteArray());
		my_images.add(myimage);
		
		return null;
	}
	
	public String delete_image()
	{
		my_images.remove(my_image_for_delete);
		return null;
	}
	
	public String delete_saved_image()
	{
		List<Photo> temp_img_list= new ArrayList<Photo>();
		temp_img_list= item_for_change.getPhotos();
		temp_img_list.remove(saved_image_for_delete);
		item_for_change.setPhotos(temp_img_list);
		return null;
	}
	
	public List<Item> getAll_my_items() {
		
		all_my_items= itemDAO.getMyItems(my_user.getCurrent().getSeller()); 
		return all_my_items;
	}

	public void setAll_my_items(List<Item> all_my_items) {
		this.all_my_items = all_my_items;
	}
	
	
    public String more_item()
    {    
    	return "/html/details";
    }
	   
    public String edit_item()
    {    
    	return "/restricted/edit_item";
    }
    
    public String delete_item()
    {
    	itemDAO.remove(item_for_change);
    	return null;
    }


// Getters and Setters    
	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	public boolean isCan_change(String started,String numofbid) throws ParseException {
		Timestamp current_time =new Timestamp(System.currentTimeMillis());
		DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);  
		//DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");   
		Date item_started_time = format.parse(started);
		int numofbids= Integer.valueOf(numofbid);
		can_change= ( item_started_time.compareTo(current_time) >= 0 && numofbids < 1 ); 
		return can_change;
	}

	public void setCan_change(boolean can_change) {
		this.can_change = can_change;
	}

	public Item getItem_for_change() {
		return item_for_change;
	}

	public void setItem_for_change(Item item_for_change) {
		this.item_for_change = item_for_change;
	}

	public UserBean getMy_user() {
		return my_user;
	}

	public void setMy_user(UserBean my_user) {
		this.my_user = my_user;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public String getCurrent_category() {
		return current_category;
	}

	public void setCurrent_category(String current_category) {
		this.current_category = current_category;
	}
	  
	public Part getCurrent_image_part() {
		return current_image_part;
	}

	public void setCurrent_image_part(Part current_image_part) {
		this.current_image_part = current_image_part;
	}
	
	public MyImage getMy_image_for_delete() {
		return my_image_for_delete;
	}

	public void setMy_image_for_delete(MyImage my_image_for_delete) {
		this.my_image_for_delete = my_image_for_delete;
	}

	public List<MyImage> getMy_images() {
		return my_images;
	}

	public void setMy_images(List<MyImage> my_images) {
		this.my_images = my_images;
	}
	
	public Photo getSaved_image_for_delete() {
		return saved_image_for_delete;
	}

	public void setSaved_image_for_delete(Photo saved_image_for_delete) {
		this.saved_image_for_delete = saved_image_for_delete;
	}
	
    public Category getCategory_for_delete() {
		return category_for_delete;
	}

	public void setCategory_for_delete(Category category_for_delete) {
		this.category_for_delete = category_for_delete;
	}
	
	@SuppressWarnings("deprecation")
	public String getStarted_day() {
		return String.valueOf(item_for_change.getStarted().getDate());
	}
	
	@SuppressWarnings("deprecation")
	public void setStarted_day(String started_day) {
		item_for_change.getStarted().setDate(Integer.valueOf(started_day));
	}

	@SuppressWarnings("deprecation")
	public String getStarted_month() {
		return String.valueOf(item_for_change.getStarted().getMonth()+1);
	}

	@SuppressWarnings("deprecation")
	public void setStarted_month(String started_month) {
		item_for_change.getStarted().setMonth(Integer.valueOf(started_month)-1);
	}

	@SuppressWarnings("deprecation")
	public String getStarted_year() {
		return String.valueOf(item_for_change.getStarted().getYear()+1900);
	}

	@SuppressWarnings("deprecation")
	public void setStarted_year(String started_year) {
		item_for_change.getStarted().setYear(Integer.valueOf(started_year)-1900);
	}

	@SuppressWarnings("deprecation")
	public String getStarted_hour() {
		return String.valueOf(item_for_change.getStarted().getHours());
		
	}

	@SuppressWarnings("deprecation")
	public void setStarted_hour(String started_hour) {
		item_for_change.getStarted().setHours(Integer.valueOf(started_hour));
	}

	@SuppressWarnings("deprecation")
	public String getStarted_minute() {
		return String.valueOf(item_for_change.getStarted().getMinutes());	
	}

	@SuppressWarnings("deprecation")
	public void setStarted_minute(String started_minute) {
		item_for_change.getStarted().setMinutes(Integer.valueOf(started_minute));
	}

	@SuppressWarnings("deprecation")
	public String getEnds_day() {
		return String.valueOf(item_for_change.getEnds().getDate());
	}

	@SuppressWarnings("deprecation")
	public void setEnds_day(String ends_day) {
		item_for_change.getEnds().setDate(Integer.valueOf(ends_day));
	}

	@SuppressWarnings("deprecation")
	public String getEnds_month() {
		return String.valueOf(item_for_change.getEnds().getMonth()+1);
	}

	@SuppressWarnings("deprecation")
	public void setEnds_month(String ends_month) {
		item_for_change.getEnds().setMonth(Integer.valueOf(ends_month)-1);
	}

	@SuppressWarnings("deprecation")
	public String getEnds_year() {
		return String.valueOf(item_for_change.getEnds().getYear()+1900);
	}

	@SuppressWarnings("deprecation")
	public void setEnds_year(String ends_year) {
		item_for_change.getEnds().setYear(Integer.valueOf(ends_year)-1900);
	}

	@SuppressWarnings("deprecation")
	public String getEnds_hour() {
		return String.valueOf(item_for_change.getEnds().getHours());		
	}

	@SuppressWarnings("deprecation")
	public void setEnds_hour(String ends_hour) {
		item_for_change.getEnds().setHours(Integer.valueOf(ends_hour));
	}

	@SuppressWarnings("deprecation")
	public String getEnds_minute() {
		return String.valueOf(item_for_change.getEnds().getMinutes());		
	}

	@SuppressWarnings("deprecation")
	public void setEnds_minute(String ends_minute) {
		item_for_change.getEnds().setMinutes(Integer.valueOf(ends_minute));
	}

}
