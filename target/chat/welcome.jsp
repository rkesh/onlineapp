<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<jsp:useBean id="welcomeForm" scope="request" class="com.cisco.chat.actionform.welcomeActionForm"/>

<div align="center">
<h4>Online Chat Application</h4>
<html:form method="post" action = "/welcome" >
    Username : &nbsp;&nbsp;&nbsp;<html:text name="welcomeForm" property="username" />
        <br><br>
    Password : &nbsp;&nbsp;&nbsp;<html:password name="welcomeForm" property="password" />
        <br><br>
        <html:submit value="login" /><br><br><br>
        <a href="signup.jsp" target="_parent">signup</a>
</html:form>
<div>

 