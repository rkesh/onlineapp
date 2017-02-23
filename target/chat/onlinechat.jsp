<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<link href="Onlinechat.css" type="text/css" rel="stylesheet">
<link href="bootstrap.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="onlinechat.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<jsp:useBean id="welcomeForm" scope="request" class="com.cisco.chat.actionform.welcomeActionForm"/>
<input type="hidden" name="loggedinuser" value="<%=request.getAttribute("loggedinuser") %>" id="loggedinuser"/>
<div class="chatarea">
<div style=" width: 20%; height: 660px; float: left;">>
		<logic:present name="olList">
		<b>Online User</b><br /> 
 				<logic:iterate id="user" name="olList">
                        <a href="#" onclick="popup('<bean:write name='user'/>')" id="<bean:write name='user'/>" class="chatuser" value="<bean:write name='user'/>"><bean:write name="user"/></a><br>
				</logic:iterate>
		</logic:present>
		</div>	
	</div>

<div id = "chatbox" class="container" style="display:none">
<div class="col-md-4">
    	    <div id="chatpanel" class="panel panel-primary">
            </div>
</div>

         <div class="input-group">
             <input type="text" id="message" class="form-control">
                <span class="input-group-btn">
              <button class="btn btn-default" type="button" onclick="sendmessages()">Send</button>
                  </span>
                </div>
                <div id="reciever"></div>
</div>
