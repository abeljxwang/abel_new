
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ page import="com.ibm.ais.util.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>

<portletAPI:init />

<%
	UserProfile up = new UserProfile();
	String uid = "";
	try {
		if (session.getAttribute("user_profile") != null)
			up = (UserProfile) session.getAttribute("user_profile");
		else
			up = null;
	} catch (Exception e) {
		e.printStackTrace();

	}

	if (up != null && up.getUserID() != null)
		uid = up.getUserID();

	if (uid == null)
		uid = "12345678";
%>

<tr>
	<TD vAlign=top height=120 width=15 bgcolor="99CCCC"></td>

	<td width="230" bgcolor="#FFFFFF"><img
		src="<portletAPI:encodeURI path="/images/free_mvc.jpg" />"
		height="120"></td>
</tr>


<tr>
	<td bgcolor="DDDDFF" height="15"></td>
	<td bgcolor="99CCCC"><A class=snname>SN£º<%=uid%></a></td>
</tr>
