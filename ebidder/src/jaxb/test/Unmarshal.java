package jaxb.test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;



public class Unmarshal {
	private static List<ItemsXml> list;

	public static void main(String args[]) throws Exception {
		JAXBContext context = JAXBContext.newInstance(ItemsXml.class); //1
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ItemsXml item = (ItemsXml)unmarshaller.unmarshal(new FileReader("./src/jaxb/test/testxml.xml")); //2
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
