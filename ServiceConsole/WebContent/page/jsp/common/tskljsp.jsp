<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>



<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<%@page import="java.text.SimpleDateFormat"%>
<portletAPI:init />


<%
	//TaskManager tm = new TaskManager(
	//		"jdbc:db2://localhost:50000/COMMONDB");
	//Vector tasks = tm.getAllScheduleByRole("abelwang");
	//System.out.println(tasks.size());
%>


<table cellpadding="0" cellspacing="1" border="0" width="100%">
	<tr>
		<td bgcolor="#CCCCFF" colspan="3" valign="top">
		<table width="100%" cellpadding="0" cellspacing="1" border="0"
			width="100%">
			<tr>
				<td width="40" bgcolor="#EEEEEE" align="right">8:00&nbsp;</td>
				<td bgcolor="#CCCCFF">Service Publish</td>
			</tr>
			<%
				//DateFormat dateOnly = DateFormat.getDateInstance(DateFormat.LONG);
				/*
				SimpleDateFormat f = new SimpleDateFormat("HH:00");

				for (int i = 0; i < tasks.size(); i++) {
					Timestamp tmpd = null;
					TaskSchedule tsk = (TaskSchedule) tasks.elementAt(i);
					out.print("<tr><td bgcolor=\"#EEEEEE\" align=\"right\">");
					tmpd = (Timestamp) tsk.getO("scheduletime");
					out.print(f.format(tmpd));
					//tmpd.
					out.print("&nbsp;</td> <td bgcolor=\"#FFFFFF\">&nbsp;");
					out.print(tsk.get("taskname"));
					out.print("</td></tr>");
				}
				*/
			%>
			<tr>
				<td bgcolor="#FFEEEE" align="right">9:00&nbsp;</td>
				<td bgcolor="#FFFFFF">&nbsp;</td>
			</tr>
			<tr>
				<td bgcolor="#FFEEEE" align="right">10:00&nbsp;</td>
				<td bgcolor="#FFFFFF">&nbsp;</td>
			</tr>
			<tr>
				<td bgcolor="#FFEEEE" align="right">11:00&nbsp;</td>
				<td bgcolor="#FFFFFF">&nbsp;</td>
			</tr>
			<tr>
				<td bgcolor="#FFEEEE" align="right">12:00&nbsp;</td>
				<td bgcolor="#FFFFFF">&nbsp;</td>
			</tr>
			<tr>
				<td bgcolor="#FFEEEE" align="right">13:00&nbsp;</td>
				<td bgcolor="#FFFFFF">&nbsp;</td>
			</tr>

			<tr>
				<td bgcolor="#FFEEEE" align="right">18:00&nbsp;</td>
				<td bgcolor="#FFFFFF">&nbsp;</td>
			</tr>
		</table>

		</td>
	</tr>
</table>