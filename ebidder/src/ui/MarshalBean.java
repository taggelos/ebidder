package ui;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import db.ItemDAO;
import entities.Item;
import jaxb.ItemXml;
import jaxb.ItemsXml;


@ManagedBean(name = "marshal")

@ViewScoped

public class MarshalBean {
	
	
	@ManagedProperty(value = "#{itemDAO}")
	private ItemDAO itemDAO;

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	
	public String marshalXml()
	{
		try{	
		//Marshalling
			
			/*   
			String absolutePath = file.getAbsolutePath();   //the path of the xml file that will be created
    	    System.out.println("File path : " + absolutePath);

    	    String filePath = absolutePath.
    	    	     substring(0,absolutePath.lastIndexOf(File.separator));

    	    System.out.println("File path : " + filePath);
    	    */ 

			ItemsXml items = new ItemsXml();
			List<Item>itemlist = itemDAO.getItems2("All") ;
			if(itemlist==null) System.out.println("AAAAAAAA");
			for(Item item: itemlist){
				ItemXml itemxml = new ItemXml();
				itemxml.setItem(item);
				items.getItems().add(itemxml);
			}
			
			JAXBContext jaxbContext = JAXBContext.newInstance(ItemsXml.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			//jaxbMarshaller.setProperty("jaxb.encoding", "Unicode"); //special signs if dont work
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			FacesContext fc = FacesContext.getCurrentInstance();
		    ExternalContext ec = fc.getExternalContext();
			
			ec.responseReset();
			String fileName="marshal.xml";
			ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			
			OutputStream output = ec.getResponseOutputStream();
			jaxbMarshaller.marshal(items, output);

			fc.responseComplete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return "/html/commnon";
	}	
}
