package com.cisco.chat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cisco.action.db.chatCommonDAO;
import com.cisco.chat.actionform.AjaxActionForm;
//one small comment



public class AjaxAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxActionForm aform = (AjaxActionForm) form;
		if("addChatMessage".equalsIgnoreCase(aform.getAddChatMessage())){
        	String sender=request.getParameter("sender");
        	String reciever=request.getParameter("reciever");
        	String message=request.getParameter("message");
        	String result=chatCommonDAO.insertChatMessages(sender,reciever,message);
        	System.out.println("ajax result"+result);
        	aform.setAddChatMessage(result.trim());
        }
		if("loadMessages".equalsIgnoreCase(aform.getLoadMessages())){
		   	String sender=request.getParameter("sender");
        	String reciever=request.getParameter("reciever");
        	String result = chatCommonDAO.getChatMessages(sender,reciever);
        	System.out.println("ajax result"+result);
        	aform.setAddChatMessage(result.trim());
		}
		return mapping.findForward("ajaxResponse");
	
	}
}
