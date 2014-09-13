<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,com.ibm.ais.util.*"%>

<style type='text/css'>
.a_panel {
	padding: 4px;
	font-size: 16pt;
	COLOR: #eeFF00;
	font-family: 微软雅黑;
	margin: 10px;
}

.p-heading {
	padding: 4px;
	font-size: 12pt;
	COLOR: #ffffff;
	font-family: 微软雅黑;
	background: #6699CC;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	border-bottom-left-radius: 0px;
	border-bottom-right-radius: 0px;
}

.panel-body {

	font-size: 10pt;
	padding-left: 5px;
	COLOR: #333333;
	font-family: 楷体;
	background: #ffffff;
	border: 1px solid #888888;
	border-top-left-radius: 0px;
	border-top-right-radius: 0px;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
}

.a_td {

	font-size: 12pt;
	padding-left: 5px;
	COLOR: #333333;
	font-family: 楷体;
	border-bottom: 1px solid #888888;
}
th {

	font-size: 12pt;
	padding-left: 5px;
	COLOR: #333333;
	font-family: 微软雅黑;
	border-bottom: 1px solid #888888;
}
</style>


<%



	String ongoing_case = "/jsp/case/ongoing.jsp";
	String ongoing_client = "/jsp/case/ongoing-client.jsp";
	String ongoing_task = "/jsp/case/ongoing-task.jsp";
	String ongoing_money = "/jsp/case/ongoing-money.jsp";
	String ongoing_panel = "/jsp/case/panel.jsp";
%>

<table width=100% cellpadding="0" cellspacing="0" border="0">

<tr>
<td valign=top>

<table width=100% cellpadding="0" cellspacing="0" border="0">
<tr>
<td colspan=2><div><jsp:include page="<%=ongoing_case%>" flush="true" /></div></td>
</tr>
<tr>
<td  colspan=2><div><jsp:include page="<%=ongoing_task%>" flush="true" /></div></td>
</tr>
<tr>
<td><div><jsp:include page="<%=ongoing_client%>" flush="true" /></div></td><td><div><jsp:include page="<%=ongoing_money%>" flush="true" /></div></td>
</tr>
</table>

</td>

<td width=318 valign=top>
<div><jsp:include page="<%=ongoing_panel%>" flush="true" /></div>
</td>
</tr>
</table>



