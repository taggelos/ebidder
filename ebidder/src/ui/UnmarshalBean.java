package ui;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
//import java.util.List;
import java.io.InputStream;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import db.ItemDAO;
import entities.Bid;
import entities.Item;
import jaxb.ItemXml;
import jaxb.ItemsXml;


@ManagedBean(name = "unmarshal")

@RequestScoped

public class UnmarshalBean {
	//private static List<ItemsXml> list;

	private Part file;
	 private String fileContent;
	 
	  public String upload() {
	    try {

			System.out.println("as345345345345a");
	      fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
	    } catch (IOException e) {
	      // Error handling
	    	return null;
	    }

		System.out.println("6776766776da777sa");
	    return null;
	  }
	 
	  public Part getFile() {
	    return file;
	  }
	 
	  public void setFile(Part file) {
	    this.file = file;
	  }


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
		/*ItemsXml items = (ItemsXml)unmarshaller.unmarshal(new FileReader("testxml.xml")); */
		//items.setItems(); //set every itemxml as entity item
		ItemsXml items = (ItemsXml)unmarshaller.unmarshal(file.getInputStream()); 
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		//System.out.println(items);
		//ItemDAO itemDAO = new ItemDAO();
		for (ItemXml i : items.getItems()){
			//i.getItem();
			System.out.println(i);
			Item item = i.getItem();
			if (item.getBids()!=null)
			for (Bid b: item.getBids()){
				System.out.println("--------------------"+b.getBidder().getUserUsername());
			}
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

		return "/html/commnon.xhtml";
	}
	
	/*
	public void save() {
		
	    try (InputStream input = file.getInputStream()) {
	        Files.copy(input, new File(uploads, filename).toPath());
	    }
	    catch (IOException e) {
	        // Show faces message?
	    	FacesContext context = FacesContext.getCurrentInstance();
	    	context.addMessage(null, new FacesMessage("OOPSS"));
	    }
	}*/
	
}
