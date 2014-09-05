
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />


<%
	String auto_result=null;


	if (request.getAttribute("result_au") != null) {
		auto_result = request.getAttribute("result_au")+"";
	}
%>

<%=auto_result %>>

