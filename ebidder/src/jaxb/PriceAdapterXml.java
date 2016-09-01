package jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PriceAdapterXml extends XmlAdapter<String, Float> {

	@Override
    public String marshal(Float v) throws Exception {
			return "$" + String.format("%.2f", v);
    }

    @Override
    public Float unmarshal(String v) throws Exception {
       return new Float(v.substring(1));
    }

}