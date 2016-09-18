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


@ManagedBean(name="conversation_list")
@ViewScoped
public class ConversationListBean {

	private boolean incoming=true;
	private List<Message> conversationList= new ArrayList<Message>();
	
	private Message conversation_for_delete;
	
	@ManagedProperty(value = "#{messageDAO}")
	private MessageDAO messageDAO;
	
	@ManagedProperty(value = "#{user}")
	private UserBean my_user;

	// Functions 	
	public String setIncoming()
	{
		incoming=true;
		return null;
	}
	public String setOutcoming()
	{
		incoming=false;
		return null;
	}
	
	public String see_all()
	{
		return "/restricted/message";
	}
	
	public String delete()
	{
		messageDAO.delete_conversation(conversation_for_delete.getUser1(), conversation_for_delete.getUser2());
		return null;
	}
	
	// Getters & Setters
	public boolean isIncoming() {
		return incoming;
	}
	public void setIncoming(boolean incoming) {
		this.incoming = incoming;
	}
	public List<Message> getConversationList() {
		conversationList= messageDAO.getConversations(my_user.getCurrent(),incoming);
		return conversationList;
	}
	public void setConversationList(List<Message> conversationList) {
		this.conversationList = conversationList;
	}
	
	public Message getConversation_for_delete() {
		return conversation_for_delete;
	}
	public void setConversation_for_delete(Message conversation_for_delete) {
		this.conversation_for_delete = conversation_for_delete;
	}

	public MessageDAO getMessageDAO() {
		return messageDAO;
	}
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	
	public UserBean getMy_user() {
		return my_user;
	}
	public void setMy_user(UserBean my_user) {
		this.my_user = my_user;
	}
}