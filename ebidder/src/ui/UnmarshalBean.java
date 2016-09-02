package ui;

import java.io.FileReader;
//import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import db.ItemDAO;
import entities.Item;
import jaxb.ItemXml;
import jaxb.ItemsXml;


@ManagedBean(name = "unmarshal")

@RequestScoped

public class UnmarshalBean {
	//private static List<ItemsXml> list;

	@ManagedProperty(value="#{itemDAO}")
	    private ItemDAO itemDAO;
	
	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public String unmarshalXml() throws Exception {
		JAXBContext context = JAXBContext.newInstance(ItemsXml.class); 
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ItemsXml items = (ItemsXml)unmarshaller.unmarshal(new FileReader("testxml.xml")); 
		//items.setItems(); //set every itemxml as entity item
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		System.out.println(items);
		//ItemDAO itemDAO = new ItemDAO();
		for (ItemXml i : items.getItems()){
			//i.getItem();
			//System.out.println(i);
			Item item = i.getItem();
			itemDAO.insertItem(item);
		}
		
		/*
		ArrayList<ItemXml> uList = new ArrayList<ItemXml>();
		list = null;
		for (ItemXml u: list)
        {
			ItemXml it = new ItemXml();
            it.setDescription(u.getDescription());
            it.setName(u.getName());
            uList.add(it);
        }*/

		return "/html/commnon";
	}
}
