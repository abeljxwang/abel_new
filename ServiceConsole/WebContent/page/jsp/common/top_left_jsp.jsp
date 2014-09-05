
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%@ page import="com.cnpc.oms.poc.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI" %>

<portletAPI:init />

<%
//System.out.println(session.getAttribute("user_profile"));
System.out.println(session.getAttribute("ID"));
UserProfile up=new UserProfile();
//up.
String uid="";
//if(session.getAttribute("user_profile")!=null)
//	up=(UserProfile)session.getAttribute("user_profile");
	
if(up!=null)
	uid=up.getUserID();

if(uid==null) uid="12345678";

%>

 	 <tr>
	   	<TD vAlign=top height=120 width=15 bgcolor="ABCDEF"></td>		
    	
    	<td width="230" bgcolor="#FFFFFF"><img src="<portletAPI:encodeURI path="/images/back_1.jpg" />" height="120"></td> </tr>
	 
	 
	 <tr>
	    <td bgcolor="DDDDFF" height="15"></td>
		<td bgcolor="6677FF"><A class=snname>SN£º<%=uid%></a></td>
	 </tr>
