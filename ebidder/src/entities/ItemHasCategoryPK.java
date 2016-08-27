package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the item_has_category database table.
 * 
 */
@Embeddable
public class ItemHasCategoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int item_ItemID;

	@Column(insertable=false, updatable=false)
	private String category_Name;

	public ItemHasCategoryPK() {
	}
	public int getItem_ItemID() {
		return this.item_ItemID;
	}
	public void setItem_ItemID(int item_ItemID) {
		this.item_ItemID = item_ItemID;
	}
	public String getCategory_Name() {
		return this.category_Name;
	}
	public void setCategory_Name(String category_Name) {
		this.category_Name = category_Name;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemHasCategoryPK)) {
			return false;
		}
		ItemHasCategoryPK castOther = (ItemHasCategoryPK)other;
		return 
			(this.item_ItemID == castOther.item_ItemID)
			&& this.category_Name.equals(castOther.category_Name);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.item_ItemID;
		hash = hash * prime + this.category_Name.hashCode();
		
		return hash;
	}
}