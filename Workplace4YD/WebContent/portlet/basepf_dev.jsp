<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI" %>
<portletAPI:init/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%@ page session="false" buffer="none" %>


<STYLE type=text/css>
A.ButtonType {
	color: 000000;
	text-decoration: none;
	font-size: 12px
}

A.ButtonType:hover {
	color: red;
	text-decoration: none;
	font-size: 12px
}

TD {
	FONT-SIZE: 12px
}

.sp {
	BORDER-RIGHT: #000000 1px solid;
	BORDER-TOP: #000000 1px solid;
	BORDER-LEFT: #000000 1px solid;
	BORDER-BOTTOM: #000000 1px solid;
}

INPUT {
	FONT-SIZE: 9pt;
	HEIGHT: 22px;
	BORDER-RIGHT: #000000 1px solid;
	BORDER-TOP: #000000 1px solid;
	BORDER-LEFT: #000000 1px solid;
	BORDER-BOTTOM: #000000 1px solid;
	BACKGROUND-COLOR: #ffffff;
}

INPUT.ButtonType {
	FONT-SIZE: 9pt;
	HEIGHT: 22px;
	BORDER-RIGHT: #000000 1px solid;
	BORDER-TOP: #000000 1px solid;
	BORDER-LEFT: #000000 1px solid;
	BORDER-BOTTOM: #000000 1px solid;
	BACKGROUND-COLOR: #C0C0C0;
}

SELECT {
	FONT-SIZE: 9pt;
	HEIGHT: 22px;
}
</STYLE>

<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="GENERATOR" content="IBM WebSphere Studio">

<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>basepf.jsp</TITLE>
</HEAD>
<%
String FMFName=null,FMAName=null;
if(request.getAttribute("FMFName")!=null) FMFName=request.getAttribute("FMFName").toString();
if(request.getAttribute("FMAName")!=null) FMAName=request.getAttribute("FMAName").toString();

String currentjsp=null;
if(request.getAttribute("currentjsp")!=null) currentjsp=request.getAttribute("currentjsp").toString();
else currentjsp="/portlet/notfound.jsp";

String MRUNTIME="",MENDTIME=null;
if(request.getAttribute("MRUNTIME")!=null) MRUNTIME=request.getAttribute("MRUNTIME").toString();
if(request.getAttribute("MENDTIME")!=null) MENDTIME=request.getAttribute("MENDTIME").toString();
long jspstarttime=0;
try{
	jspstarttime=Long.parseLong(MENDTIME);
}catch(Exception e1){
	jspstarttime=0;
}

%>
<body>
<table border="1" width="100%" bordercolorlight="#000000"
	cellspacing="0" cellpadding="0" bordercolordark="#000000">
	<tr>
		<td width="90%" bgcolor="#505050" bordercolorlight="#505050"
			bordercolordark="#505050"><font color="#FFFFFF">&nbsp;&nbsp;
		Portlet测试框架: <%=FMFName%>:<%=FMAName%>: ===&raquo; <%=currentjsp%></font></td>
		<td width="10%" align="right" bgcolor="#505050"
			bordercolorlight="#505050" bordercolordark="#505050"><IMG border="0"
			src="<portletAPI:encodeURI path="/images/jsp/info.gif" />" width="16" height="14"><IMG border="0"
			src="<portletAPI:encodeURI path="/images/jsp/maximize.gif" />" width="16" height="14"></td>
	</tr>
	<tr>
		<td width="100%" colspan="2" height=100 bgcolor=ffffff
			bordercolorlight="#ffffff" bordercolordark="#ffffff"><!---
====================================== [start] ===========================================
--> <jsp:include page="<%=currentjsp%>" flush="true" /> <!---
======================================= [end] ============================================
--></td>
	</tr>
	
	<%
	long jspendtime=System.currentTimeMillis();
	%>
	<tr>
		<td bgcolor="#FF8888" bordercolorlight="#FF8888"
			bordercolordark="#000000"><font color="#FFFFFF">&nbsp;&nbsp; 模块运行时间：<%=MRUNTIME%>ms &nbsp;&nbsp;&nbsp;&nbsp;  JSP运行时间：<%=(jspendtime-jspstarttime)%>ms</font></td>
		<td align="right" bgcolor="#FF8888"
			bordercolorlight="#FF8888" bordercolordark="#000000">&nbsp;</td>
	</tr>
</table>
</body>
</html>
