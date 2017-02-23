package com.cisco.chat.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cisco.chat.actionform.welcomeActionForm;
import com.cisco.action.db.chatCommonDAO;


public class welcomeAction extends Action {
	private static final Logger logger = Logger.getLogger(welcomeAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String isValidUser = "";
		chatCommonDAO ccd = new chatCommonDAO();
		ArrayList<String> olList = new ArrayList<String>();
		welcomeActionForm welcomeform = (welcomeActionForm) form;
		System.out.println("hello Chat Clinet");
		String username = welcomeform.getUsername();
		String password = welcomeform.getPassword();
		isValidUser = chatCommonDAO.validatelogintoonline(username, password);
		// check if the user is valid user or not
		if ("Y".equalsIgnoreCase(isValidUser)) {
			olList = (ArrayList<String>) chatCommonDAO.getOnlineUsers(username);
			request.setAttribute("olList", olList);
			request.setAttribute("loggedinuser",username);
			return mapping.findForward("onlinechat");
		} else {
			return mapping.findForward("welcome");
		}
	}
}
