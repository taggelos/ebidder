package jaxb.test;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Note that the element is all up-case. The Java class does not
//have to have exactly the same name as the element. It is
//enough that you create the right mapping. JAXB automatic
//bindings will not do that for you.
@XmlRootElement(name="PROGRAMS")
public class Programs {

	private List<Program> programs;

	@XmlElement(name = "program")
	public List<Program> getPrograms() {
		if (null==programs) {
			programs = new ArrayList<Program>();
		}
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	@Override
	public String toString() {
		return "Programs [" + (programs != null ? "programs=" + programs : "") + "]";
	}
}
