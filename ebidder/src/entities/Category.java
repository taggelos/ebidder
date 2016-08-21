package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String name;

	//bi-directional many-to-one association to ItemHasCategory
	@OneToMany(mappedBy="category")
	private List<ItemHasCategory> itemHasCategories;

	//bi-directional many-to-many association to Item
	@ManyToMany(mappedBy="categories")
	private List<Item> items;

	public Category() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ItemHasCategory> getItemHasCategories() {
		return this.itemHasCategories;
	}

	public void setItemHasCategories(List<ItemHasCategory> itemHasCategories) {
		this.itemHasCategories = itemHasCategories;
	}

	public ItemHasCategory addItemHasCategory(ItemHasCategory itemHasCategory) {
		getItemHasCategories().add(itemHasCategory);
		itemHasCategory.setCategory(this);

		return itemHasCategory;
	}

	public ItemHasCategory removeItemHasCategory(ItemHasCategory itemHasCategory) {
		getItemHasCategories().remove(itemHasCategory);
		itemHasCategory.setCategory(null);

		return itemHasCategory;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}