package com.cisco.chat.actionform;

import org.apache.struts.action.ActionForm;

public class AjaxActionForm extends ActionForm{
	private String loadMessages;
	private String addChatMessage;
	public String getLoadMessages() {
		return loadMessages;
	}
	public void setLoadMessages(String loadMessages) {
		this.loadMessages = loadMessages;
	}
	public String getAddChatMessage() {
		return addChatMessage;
	}
	public void setAddChatMessage(String addChatMessage) {
		this.addChatMessage = addChatMessage;
	}

}
