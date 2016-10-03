package ui;

import db.MessageDAO;
import entities.Message;
import entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


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
		// +Elegxos text
		
		Message message= new Message();
		//TODO
		//message.setUser1(getSender());
		//message.setUser2(getReceiver());
		message.setSeller(getSender().getSeller());
		message.setBidder(getReceiver().getBidder());
		
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
		User sender = getSender();
		User receiver = getReceiver();
		sender.getSeller().getMessages();
		receiver.getBidder().getMessages();
		conversationMessages=messageDAO.getMessages(getSender(),getReceiver());
		return  conversationMessages;
	}

	public void setConversationMessages(List<Message> conversationMessages) {
		this.conversationMessages = conversationMessages;
	}
	
	// Proswrino
	public User getReceiver() {
		receiver=my_user.getCurrent(); //afton p prepei (ap prohgumenh selida)
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