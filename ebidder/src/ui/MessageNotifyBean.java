package ui;

import db.MessageDAO;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="messagenotify")
@SessionScoped
public class MessageNotifyBean {
	
	private int numofunread;
	
	@ManagedProperty(value = "#{user}")
	private UserBean my_user;

	@ManagedProperty(value = "#{messageDAO}")
	private MessageDAO messageDAO;

	
	// Functions
	public String go_to_conversations()
	{
		return "/restricted/conversation_list";
	}
	
	// Getters & Setters
	public int getNumofunread() {
		numofunread= messageDAO.getNumOfUnread(my_user.getCurrent());
		return numofunread;
	}

	public void setNumofunread(int numofunread) {
		this.numofunread = numofunread;
	}
	
	public UserBean getMy_user() {
		return my_user;
	}

	public void setMy_user(UserBean my_user) {
		this.my_user = my_user;
	}

	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	
}
