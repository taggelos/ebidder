package jaxb.test;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="ItemXml")
public class ItemXml implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String name;
	
	private String description;
	
	@XmlElement(name="Name")
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}


	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Item [" + (name != null ? "name=" + name + ", " : "") + (description != null ? "description=" + description : "") + "]";
	}
}