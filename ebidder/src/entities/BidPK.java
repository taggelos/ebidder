package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the bid database table.
 * 
 */
@Embeddable
public class BidPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String name;

	@Column(insertable=false, updatable=false)
	private int items_ItemID;

	public BidPK() {
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItems_ItemID() {
		return this.items_ItemID;
	}
	public void setItems_ItemID(int items_ItemID) {
		this.items_ItemID = items_ItemID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BidPK)) {
			return false;
		}
		BidPK castOther = (BidPK)other;
		return 
			this.name.equals(castOther.name)
			&& (this.items_ItemID == castOther.items_ItemID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.name.hashCode();
		hash = hash * prime + this.items_ItemID;
		
		return hash;
	}
}