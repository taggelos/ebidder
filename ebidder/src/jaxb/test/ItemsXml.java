package jaxb.test;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



import java.util.ArrayList;

import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Items")
public class ItemsXml implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<ItemXml> items;
	
	@XmlElement(name = "Item")
	public List<ItemXml> getBids() {
		if (null==items) {
			items = new ArrayList<ItemXml>();
		}
		return items;
	}

	public void setPrograms(List<ItemXml> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Items [" + (items != null ? "items=" + items : "") + "]";
	}
}