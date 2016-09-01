package ui;

import java.io.File;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import jaxb.ItemXml;


@ManagedBean(name = "marshal")

@ViewScoped

public class MarshalBean {
	public String marshalXml()
	{
		ItemXml item = new ItemXml();
		item.setName("opps");
		item.setDescription("pls work");
		try {
			//Marshalling
			File file = new File("axne.xml");
			if(file.exists() && !file.isDirectory()) { 
			    // do something
				System.out.println("AXNE ALIVE");
			}
			/*   
			String absolutePath = file.getAbsolutePath();   //the path of the xml file that will be created
    	    System.out.println("File path : " + absolutePath);

    	    String filePath = absolutePath.
    	    	     substring(0,absolutePath.lastIndexOf(File.separator));

    	    System.out.println("File path : " + filePath);
    	    */ 
			JAXBContext jaxbContext = JAXBContext.newInstance(ItemXml.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			//jaxbMarshaller.setProperty("jaxb.encoding", "Unicode"); //special signs if dont work
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(item, file);
			jaxbMarshaller.marshal(item, System.out);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return "/html/commnon";
	}
}
