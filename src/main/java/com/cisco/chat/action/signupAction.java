package com.cisco.chat.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cisco.action.db.chatCommonDAO;
import com.cisco.chat.actionform.signupActionForm;

public class signupAction extends Action {
	private static final Logger logger = Logger.getLogger(welcomeAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String signupSuccess = "";
		chatCommonDAO ccd = new chatCommonDAO();
		signupActionForm signupform = (signupActionForm) form;
		System.out.println("chat User is signing up");
		String username = signupform.getUsername();
		String password = signupform.getPassword();
		String email = signupform.getEmail();
		String fname =signupform.getFname();
		String lname = signupform.getLname();
		System.out.println("User Details are==>"+username+":"+password+":"+email+":"+fname+":"+lname);
		HashMap<String,String> hm_user = new HashMap<String,String>();
		hm_user.put("username",username);
		hm_user.put("password",password);
		hm_user.put("email",email);
		hm_user.put("fname",fname);
		hm_user.put("lname",lname);
		signupSuccess = chatCommonDAO.signupchatUser(hm_user);
		// check if the user is valid user or not
		if ("Yes".equalsIgnoreCase(signupSuccess)) {
			return mapping.findForward("welcome");
		} else {
			return mapping.findForward("signup");
		}
	}
}
