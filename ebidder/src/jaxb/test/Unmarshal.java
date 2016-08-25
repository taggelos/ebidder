package jaxb.test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;



public class Unmarshal {
	private static List<ItemXml> list;

	public static void main(String args[]) throws Exception {
		JAXBContext context = JAXBContext.newInstance(ItemXml.class); //1
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ItemXml item = (ItemXml)unmarshaller.unmarshal(new FileReader("./src/jaxb/test/testxml.xml")); //2
		System.out.println(item); //3
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
	}
}
