package ui;

import db.ImageDAO;
import db.MessageDAO;
import db.UserDAO;
import entities.Category;
import entities.Location;
import entities.Message;
import entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;


@ManagedBean(name="message")
@ViewScoped
public class MessageBean {

	@ManagedProperty(value = "#{user}")
	private UserBean my_user;
	
	@ManagedProperty(value = "#{messageDAO}")
	private MessageDAO messageDAO;

	private User receiver;
	private String text="";
	
	private List<Message> conversationMessages = new ArrayList<Message>();
	
	// Functions
	public String send_message()
	{
		// +Elegxos
		
		Message message= new Message();
		message.setUser1(getSender());
		message.setUser2(getReceiver());
		message.setTime(new Date());
		message.setText(text);
		
		messageDAO.insertMessage(message);
		text="";		
		return null;
	}
	
    public String go_to_message_page()
    {    
    	return "/restricted/message";
    }
	
	
	// Getters Setters
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
	public User getSender() {
		return my_user.getCurrent();
	}
	
	public List<Message> getConversationMessages() {
		conversationMessages=messageDAO.getMesages(getSender(),getReceiver());
		return  conversationMessages;
	}

	public void setConversationMessages(List<Message> conversationMessages) {
		this.conversationMessages = conversationMessages;
	}
	
	// Proswrino
	public User getReceiver() {
		//receiver=my_user.getCurrent();
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
       
}