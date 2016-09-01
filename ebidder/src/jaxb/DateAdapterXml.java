package jaxb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapterXml extends XmlAdapter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd-yy HH:mm:ss", Locale.ENGLISH);

    @Override
    public String marshal(Date v) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.format(v);
        }
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.parse(v);
        }
    }

}