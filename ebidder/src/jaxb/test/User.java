package jaxb.test;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="user")
public class User {

	private String name;
	private Integer accountNumber;
	private String userName;
	private String command;
	private Boolean superUser;
	private Date lastLogin;
	private Programs programs;

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="accountNumber")
	public Integer getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name="username")
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	//As you can see, the member name does not have to correspond
	//to the actual element name, as long as you have the mapping
	//sorted.
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name="COMMAND")
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}

	@XmlElement(name="superUser")
	public Boolean getSuperUser() {
		return superUser;
	}
	
	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}

	@XmlElement(name="lastLogin")
	public Date getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@XmlElement(name="PROGRAMS")
	public Programs getPrograms() {
		return programs;
	}
	
	public void setPrograms(Programs programs) {
		this.programs = programs;
	}
	
	@Override
	public String toString() {
		return "User ["
	+ (name != null ? "name=" + name + ", " : "")
	+ (accountNumber != null ? "accountNumber=" + accountNumber
	+ ", " : "")
	+ (userName != null ? "userName=" + userName + ", " : "")
	+ (command != null ? "command=" + command + ", " : "")
	+ (superUser != null ? "superUser=" + superUser + ", " : "")
	+ (lastLogin != null ? "lastLogin=" + lastLogin + ", " : "")
	+ (programs != null ? "programs=" + programs : "") + "]";
	}
}
