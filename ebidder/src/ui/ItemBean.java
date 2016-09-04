package ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import db.ImageDAO;
import db.ItemDAO;
import entities.Category;
import entities.Image;
import entities.Item;
import entities.Location;

@ManagedBean(name = "item")
@ViewScoped
public class ItemBean {

	private String name;
	private List<Category> categories = new ArrayList<Category>();
	private Float buy_price;
	private Float first_bid;
	private String loc_name;
	private Float latitude;
	private Float longitude;
	private String country;
	private Timestamp started = new Timestamp(0);
	private Timestamp ends = new Timestamp(0);
	private String description;
	private List<MyImage> my_images = new ArrayList<MyImage>();

	private Float amount;

	private String current_category;
	private Category category_for_delete;
	private Part current_image_part;
	private Image current_image = new Image();
	private MyImage my_image_for_delete;

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

	@ManagedProperty(value = "#{itemDAO}")
	private ItemDAO itemDAO;

	@ManagedProperty(value = "#{imageDAO}")
	private ImageDAO imageDAO;

	@ManagedProperty(value = "#{user}")
	private UserBean my_user;

	private List<Item> all_my_items = new ArrayList<Item>();

	/* Pages */
	public String manage() {
		return "/restricted/manage";
	}

	public String all_items() {
		return "/restricted/all_items";
	}

	public String create() {
		return "/restricted/create_item";
	}

	public String edit() {
		return "/restricted/edit_item";
	}

	/* Operations */
	@SuppressWarnings("deprecation")
	public String create_item() throws IOException {

		// Thelei ki allous elegxous stis times twn pediwn
		started.setDate(Integer.valueOf(started_day));
		started.setMonth(Integer.valueOf(started_month));
		started.setYear(Integer.valueOf(started_year) - 1900);
		started.setHours(Integer.valueOf(started_hour));
		started.setMinutes(Integer.valueOf(started_minute));

		ends.setDate(Integer.valueOf(ends_day));
		ends.setMonth(Integer.valueOf(ends_month));
		ends.setYear(Integer.valueOf(ends_year) - 1900);
		ends.setHours(Integer.valueOf(ends_hour));
		ends.setMinutes(Integer.valueOf(ends_minute));

		FacesContext context = FacesContext.getCurrentInstance();
		Item item = new Item();
		item.setName(name);
		item.setCategories(categories);
		item.setCurrently(first_bid);
		item.setBuy_Price(buy_price);
		item.setFirst_Bid(first_bid);
		Location location = new Location();
		location.setName(loc_name);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		item.setLocation(location);
		item.setCountry(country);
		item.setStarted(started);
		item.setEnds(ends);
		item.setUser(my_user.getCurrent());
		item.setDescription(description);

		List<Image> temp_images_list = new ArrayList<Image>();
		Image temp_image;
		for (MyImage my_image : my_images) {
			temp_image= new Image();
			temp_image.setItem(item);
			temp_image.setImage(my_image.getImage());
			temp_images_list.add(temp_image);
		}
		item.setImages(temp_images_list);

		String[] result = itemDAO.insertItem(item);
		/*
		 * if (!message.equals("ok")) { context.addMessage(null, new
		 * FacesMessage(message)); } if (context.getMessageList().size() > 0)
		 * return null; else return "/restricted/manage";
		 */
		return null;
	}

	public String add_category() {
		Category temp= new Category(); temp.setName(current_category);
		if (categories.contains(temp)) {
			// Emfanise minuma stin html
			return null;
		}
		
		Category category = new Category();
		category.setName(current_category);
		categories.add(category);
		return null;
	}

	public String delete_category() {
		categories.remove(category_for_delete);
		return null;
	}

	public String add_image() throws IOException {
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

	public String delete_image() {
		my_images.remove(my_image_for_delete);
		return null;
	}

	/* Getters and Setters */

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

	public Float getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(Float buy_price) {
		this.buy_price = buy_price;
	}

	public Float getFirst_bid() {
		return first_bid;
	}

	public void setFirst_bid(Float first_bid) {
		this.first_bid = first_bid;
	}
	
	public String getLoc_name() {
		return loc_name;
	}

	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
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

	public List<MyImage> getMy_images() {
		return my_images;
	}

	public void setMy_images(List<MyImage> my_images) {
		this.my_images = my_images;
	}

	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
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

	public Part getCurrent_image_part() {
		return current_image_part;
	}

	public void setCurrent_image_part(Part current_image_part) {
		this.current_image_part = current_image_part;
	}

	public Image getCurrent_image() {
		return current_image;
	}

	public void setCurrent_image(Image current_image) {
		this.current_image = current_image;
	}

	public MyImage getImage_part_for_delete() {
		return my_image_for_delete;
	}

	public void setImage_part_for_delete(MyImage my_image_for_delete) {
		this.my_image_for_delete = my_image_for_delete;
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

	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}

	public UserBean getMy_user() {
		return my_user;
	}

	public void setMy_user(UserBean my_user) {
		this.my_user = my_user;
	}

}