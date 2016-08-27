package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the image database table.
 * 
 */
@Embeddable
public class ImagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int imageID;

	@Column(insertable=false, updatable=false)
	private int item_ItemID;

	public ImagePK() {
	}
	public int getImageID() {
		return this.imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public int getItem_ItemID() {
		return this.item_ItemID;
	}
	public void setItem_ItemID(int item_ItemID) {
		this.item_ItemID = item_ItemID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ImagePK)) {
			return false;
		}
		ImagePK castOther = (ImagePK)other;
		return 
			(this.imageID == castOther.imageID)
			&& (this.item_ItemID == castOther.item_ItemID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.imageID;
		hash = hash * prime + this.item_ItemID;
		
		return hash;
	}
}