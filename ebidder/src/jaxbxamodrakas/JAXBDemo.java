package jaxbxamodrakas;

//import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class JAXBDemo {
	public static void main(String ar[])
	{
		Customer customer = new Customer();
		customer.setId(100);
		customer.setName("Sumeet");
		customer.setAge(22);
		try {
			//Marshalling
//			File file = new File("customer.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(customer, System.out);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
