<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<jsp:useBean id="ajaxActionForm" scope="request" class="com.cisco.chat.actionform.AjaxActionForm"/>

<logic:present name="ajaxActionForm" property="loadMessages">
</logic:present>

<logic:present name="ajaxActionForm" property="addChatMessage">
<jsp:getProperty name="ajaxActionForm" property="addChatMessage"/>
</logic:present>