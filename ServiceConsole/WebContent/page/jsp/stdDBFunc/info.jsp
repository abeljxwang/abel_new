
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
%>
<%@ page import="java.util.*" %>

<%
//some
Vector mdresult = new Vector();
String sqltable = null;

if (request.getAttribute("mdresult") != null)
	mdresult = (Vector) request.getAttribute("mdresult");
if(request.getAttribute("tbname") != null)
	sqltable=request.getAttribute("tbname")+"";
%>


<STYLE type=text/css>
td {
	FONT-SIZE: 12px;
	font-family: Arial;
}

INPUT {
	FONT-SIZE: 9pt;
	HEIGHT: 20px;
	BORDER-RIGHT: #000000 1px solid;
	BORDER-TOP: #000000 1px solid;
	BORDER-LEFT: #000000 1px solid;
	BORDER-BOTTOM: #000000 1px solid;
	BACKGROUND-COLOR: #ffffff;
}


</STYLE>

<center>
<table border="1" width="90%" cellspacing="0" cellpadding="0"
	bordercolor="#ffffff">
	<tr>
		<td bordercolor=000000>
		<table border=0 width=100%>
			<tr>
				<td>&nbsp;&nbsp;标准数据库操作--&gt;信息报告: --&gt;表名:<%=sqltable%></td>
			</tr>
			<tr>
				<td>
				<table border="0" width="100%" cellspacing="1" cellpadding="0"
					bgcolor=000055>
					<tr>
						<td bgcolor=000055>
						<table border="0" width="100%" cellspacing="1" cellpadding="1">
							<tr bgcolor=aaaaff>
								<td>SN</td>
								<td>Name</td>
								<td>Precision</td>
								<td>Type</td>
								<td>ClassName</td>

								<td>Lable</td>
								<td>Nullable</td>
							</tr>

							<%for (int i = 0; i < mdresult.size(); i++) {
	Properties pp = (Properties) mdresult.get(i);
	out.println("<tr  bgcolor=ffffff>");
	out.println("<td bgcolor=ddddff>" + i + "</td>");
	out.println("<td>" + pp.getProperty("Name") + "</td>");
	out.println("<td bgcolor=ddddff>" + pp.getProperty("Precision") + "</td>");
	out.println("<td>" + pp.getProperty("Type") + "</td>");
	out.println("<td bgcolor=ddddff>" + pp.getProperty("ClassName") + "</td>");
	out.println("<td>" + pp.getProperty("Lable") + "</td>");
	out.println("<td bgcolor=ddddff>" + pp.getProperty("Nullable") + "</td>");
	out.println("</tr>");
}%>
						</table>
						</td>
					</tr>
				</table>
				</td>

			</tr></table>
		</td>
	</tr>
</table>
</center>