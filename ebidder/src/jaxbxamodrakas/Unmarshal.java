package jaxbxamodrakas;

import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class Unmarshal {
	public static void main(String args[]) throws Exception {
		JAXBContext context = JAXBContext.newInstance(User.class); //1
		Unmarshaller unmarshaller = context.createUnmarshaller();
		User user = (User)unmarshaller.unmarshal(new FileReader("./src/jaxbxamodrakas/testxml.xml")); //2
		System.out.println(user); //3
	}
}
