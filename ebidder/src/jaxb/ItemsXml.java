package jaxb;

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


	public void setItems() {    
		for (ItemXml i : items){
			i.setItem();
		}
	}
	
	public List<ItemXml> getItems (){
		return items;
	}
	/*
	public List<Item> itemXmlToItem(List<ItemXml> itemxml){
		List<Item> items = new ArrayList<>();
		for (ItemXml i: itemxml)
        {            
			items.add(i.getItem());
        }
 		return items;
	}*/
	
	@Override
	public String toString() {
		return "Items " + "\n" + (items != null ?  items : "");
	}
}