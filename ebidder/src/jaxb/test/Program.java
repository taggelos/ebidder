package jaxb.test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

//The XMLRootElement annotation tells JAXB what is the
//element local name it needs to look for.
@XmlRootElement(name="program")
public class Program {

	private String executable;
	private String directory;
	
	//The adapter tells JAXB to collapse white space. This is usually
	//quite good to use for Strings.
	//The XmlElement annotation tells JAXB what's the
	//actual name of the element inside <program>
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name="executable")
	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}
	
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name="directory")
	public String getDirectory() {
		return directory;
	}
	
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	@Override
	public String toString() {
		return "Program [" + (executable != null ? "executable=" + executable + ", " : "") + (directory != null ? "directory=" + directory : "") + "]";
	}
}
