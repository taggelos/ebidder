package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int imageID;

	private byte[] image;

	//bi-directional many-to-one association to Item
	@ManyToOne
	private Item item;

	public Image() {
	}

	public int getImageID() {
		return this.imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}