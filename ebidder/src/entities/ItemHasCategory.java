package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item_has_category database table.
 * 
 */
@Entity
@Table(name="item_has_category")
@NamedQuery(name="ItemHasCategory.findAll", query="SELECT i FROM ItemHasCategory i")
public class ItemHasCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemHasCategoryPK id;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public ItemHasCategory() {
	}

	public ItemHasCategoryPK getId() {
		return this.id;
	}

	public void setId(ItemHasCategoryPK id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}