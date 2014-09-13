<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.io.*"%>

<%
	Exception eee = null;
	ByteArrayOutputStream buf = new ByteArrayOutputStream();
	if (request.getAttribute("ERROR_MSG") != null) {
		eee = (Exception) request.getAttribute("ERROR_MSG");

		eee.printStackTrace(new PrintStream(buf));

	}
%>


<table width="100%" height="300" bgcolor="#dddddd" border="0"
	cellpadding="0" cellspacing="0">
	<tr bgcolor="#dddddd">
		<td width="100%" valign="top" height="35">

		<table border="0" width="100%" height=25 name="header"
			bgcolor="#ff8888" cellpadding="0" cellspacing="0">
			<tr>
				<TD>&nbsp;&nbsp;<a class="rtitle">错误信息：</a></TD>
				<TD></TD>
				<TD class="cat02" align=right>发生时间：<%=new java.util.Date()%>
				&nbsp;&nbsp;</TD>
			</tr>
		</table>

		</td>
	</tr>

	<tr bgcolor="#dddddd">
		<td bgcolor="#ffffff" valign="top" align="right">

		<table width="95%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<TD colspan="4" bgcolor="#e0e0e0" align=left>&nbsp;<font
					color=FF000000>Error Message:<br>
				<%
				out.print(eee.getMessage());
				%>
				</font>
				<hr size=1 width=90%>
				</TD>
			</tr>
			<tr>
				<td bgcolor="#e0e0e0"><font color=220000> <%
 out.print(buf.toString());
 %>
				</font></td>
			</tr>
		</table>




		</td>
	</tr>


	<tr>
		<td height="30" width="100%" bgcolor="#dddddd"></td>
	</tr>
</table>