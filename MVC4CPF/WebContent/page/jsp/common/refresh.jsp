<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>

<%@ page import="org.abel.webapp.configure.*"%>


<table width="600" height="300" bgcolor="#ffffff" border="0"
	cellpadding="0" cellspacing="0">
	<tr>
		<td width="100%" bgcolor="#6699cc" valign="top" height="50">

		<table border="0" width="100%" bgcolor="#99ddff" cellpadding="0"
			cellspacing="0">
			<tr>
				<TD>&nbsp;&nbsp;<a class="rtitle">系统配置刷新：</a></TD>
				<TD></TD>
				<TD class="cat02">刷新时间：<%=new java.util.Date()%> &nbsp;&nbsp;</TD>
			</tr>
			<tr valign="top">
				<TD colspan="3" height="1">
				<hr size="1" width="100%">
				</TD>
			</tr>
		</table>

		</td>
	</tr>

	<tr>
		<td bgcolor="#ffffff" valign="top" align="right">

		<table width="95%" cellpadding="0" cellspacing="1" border="1">
			<tr>
				<TD colspan="4">&nbsp;Message:</TD>
			</tr>
			<tr>
				<TD colspan="4">&nbsp; <%
 	EIPConfig ecfg = null;
 	if (request.getAttribute("eipconfig") != null)
 		ecfg = (EIPConfig) request.getAttribute("eipconfig");
 	Hashtable fcs = ecfg.getCfg();
 	Enumeration ekeys = fcs.keys();
 	while (ekeys.hasMoreElements()) {
 		FunctionCfg fctmp = (FunctionCfg) fcs.get(ekeys.nextElement());
 		Properties pp = fctmp.getProperties();
 		Enumeration pkeys = pp.keys();
 		while (pkeys.hasMoreElements()) {
 			String oname = (String) pkeys.nextElement();
 			out.println(oname + "  : " + pp.get(oname));
 			out.println("<br>");

 		}
 		out.println("<hr size=1>");

 	}
 %>
				</TD>
			</tr>
			<tr>
				<td>Refresh Complete</td>
			</tr>
		</table>




		</td>
	</tr>


	<tr>
		<td height="30" width="100%">


		<TABLE width="100%" height="100%" bgColor=#99ddff border=0
			cellpadding="0" cellspacing="0">


			<TR>
				<TD></TD>
				<TD></TD>
				<TD></TD>
			</TR>
		</TABLE>

		</td>
	</tr>
</table>