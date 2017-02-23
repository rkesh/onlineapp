<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<jsp:useBean id="signupform" scope="request" class="com.cisco.chat.actionform.signupActionForm"/>
<div align="center">
<h4>Create an Account in Online application system for better Communication<h4>
<html:form method="post" action = "/signup" >
    Username : &nbsp;&nbsp;<html:text name="signupform" property="username" />
        <br><br>
    Password : &nbsp;&nbsp;<html:password name="signupform" property="password" />
        <br><br>
     email : &nbsp;&nbsp;<html:text name="signupform" property="email" />
        <br><br>
    FirstName : &nbsp;&nbsp;<html:password name="signupform" property="fname" />
        <br><br>
        LastName : &nbsp;&nbsp;<html:password name="signupform" property="lname" />
        <br><br>
        <html:submit value="signup" />
</html:form>
</div>
 