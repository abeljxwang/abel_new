
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<%@ page import="java.util.*,com.ibm.ais.util.*"%>
<portletAPI:init />

<style type='text/css'>
.snname {
	height: 24px;
	bottom: 0;
	background: #f7942f;
	text-align: left;
	font-size: 10pt;
	COLOR: #FFFFFF;
	border-top: 1px solid #f7d48f;
	border-bottom: 2px solid #fefefe;
	padding-left: 5px;
}


</style>

<%
UserProfile up = new UserProfile();
MenueElement me = new MenueElement();
if(session!=null){
	//session.removeAttribute("user_profile");
	if(session.getAttribute("user_profile")!=null){
		
		up=(UserProfile)session.getAttribute("user_profile");
		if(up!=null&&up.getAllMenueElement().size()>0)
		me=(MenueElement)up.getAllMenueElement().firstElement();
	}
	
	
	}


	
	String uid = "";
	

		uid = up.getUserID();
%>



<table cellSpacing=0 cellPadding=0 width="100%" border=0 height=100%>
<tr>

	<td bgcolor="#FFFFFF"><img
		src="<portletAPI:encodeURI path="/images/CriminalLaw.jpg" />"
		width="250"></td>
</tr>


<tr>

	<td bgcolor="#6699CC" class=snname> SN：<%=uid%></td>
</tr>
</table>