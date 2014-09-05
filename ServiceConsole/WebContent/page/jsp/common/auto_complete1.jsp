<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<%@ page import="java.util.*,org.abel.webapp.db.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<%
	EIPResultSet dbresult = null;
	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");

	}

	StringBuffer sb = new StringBuffer();
	int setsize = dbresult.size();
	int current_posi=0;
	if (setsize > 0) {

		while (dbresult.next()) {
		if(current_posi>0)	out.print(";");
		out.print(dbresult.getString(1)+"( "+dbresult.getString(2)+")");
		
		current_posi++;
		}
	} else {
		out.print("");

	}
%>


