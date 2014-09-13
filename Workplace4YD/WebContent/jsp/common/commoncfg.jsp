<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=GBK"
pageEncoding="GBK"
%>
<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<TITLE>commoncfg.jsp</TITLE>
</HEAD>
<BODY>
<P>

<table border=1><tr>
<td>Key</td><td>Value</td>
</tr>
<%
if(request.getAttribute("commoncfg")!=null){
java.util.Properties commoncfg=(java.util.Properties)request.getAttribute("commoncfg");
java.util.Enumeration keys=commoncfg.keys();
while(keys.hasMoreElements()){
String name=keys.nextElement().toString();
out.println("<tr><td>"+name+"</td><td>"+commoncfg.getProperty(name)+"</td></tr>");
}


}

%>
</table>

</BODY>
</HTML>
