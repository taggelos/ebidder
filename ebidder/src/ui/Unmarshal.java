package ui;

import java.io.FileReader;
//import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import db.ItemDAO;
import jaxb.ItemXml;
import jaxb.ItemsXml;


@ManagedBean(name = "unmarshal")

@SessionScoped

public class Unmarshal {
	//private static List<ItemsXml> list;

	@ManagedProperty(value="#{itemDAO}")
	    private ItemDAO itemDAO;
	
	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public String mainXml() throws Exception {
		JAXBContext context = JAXBContext.newInstance(ItemsXml.class); //1
		Unmarshaller unmarshaller = context.createUnmarshaller();
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		ItemsXml item = (ItemsXml)unmarshaller.unmarshal(new FileReader("testxml.xml")); //2
		//System.out.println(item); //3
		item.setItems();
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		for (ItemXml i : item.getItems()){
			i.getItem();
			System.out.println(i);
			//ItemDAO itemDAO = new ItemDAO();
			//itemDAO.insertItem(i.getItem());
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

		System.out.println("fffffffffffffffffhhhhh");
		return "/html/commnon";
	}
}
