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

	@Column(insertable=false, updatable=false)
	private int item_ItemID;

	@Column(insertable=false, updatable=false)
	private int user_UserID;

	public BidPK() {
	}
	public int getItem_ItemID() {
		return this.item_ItemID;
	}
	public void setItem_ItemID(int item_ItemID) {
		this.item_ItemID = item_ItemID;
	}
	public int getUser_UserID() {
		return this.user_UserID;
	}
	public void setUser_UserID(int user_UserID) {
		this.user_UserID = user_UserID;
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
			(this.item_ItemID == castOther.item_ItemID)
			&& (this.user_UserID == castOther.user_UserID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.item_ItemID;
		hash = hash * prime + this.user_UserID;
		
		return hash;
	}
}