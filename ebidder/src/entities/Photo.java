package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@NamedQuery(name="Photo.findAll", query="SELECT p FROM Photo p")
@XmlAccessorType(XmlAccessType.NONE)
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int imageID;

	private byte[] photo;

	//bi-directional many-to-one association to Item
	@ManyToOne
	private Item item;

	public Photo() {
	}

	public int getImageID() {
		return this.imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] image) {
		this.photo = image;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}