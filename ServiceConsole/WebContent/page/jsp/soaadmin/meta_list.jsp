
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ page import="java.util.*,org.abel.webapp.db.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<link rel="StyleSheet" href="/ServiceConsole/theme/tb1.css" type="text/css" />
<script type="text/javascript" src="/ServiceConsole/js/tb1.js"></script>

<%
	//String webappbase="/WebA2Z/EipTest";
	String baseurl = null;
	String sqltable = null;
	EIPResultSet dbresult = null;
	CmdMetaData cmd = null;

	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");
		//fieldname=(java.util.Vector)rp.getRResult("fieldname");
		//if(rp.getRResult("fieldlength")!=null) fiellength=(java.util.Vector)rp.getRResult("fieldlength");
		if (request.getAttribute("metadata") != null)
			cmd = (CmdMetaData) request.getAttribute("metadata");
		if (request.getAttribute("sqltable") != null)
			sqltable = (String) request.getAttribute("sqltable");
		if (request.getAttribute("baseurl") != null)
			baseurl = (String) request.getAttribute("baseurl");
	}
%>



	<form
	action="<portletAPI:createURI>
<portletAPI:URIAction name="queryM"/>
</portletAPI:createURI>">
<table border=0 width=90%>

	<tr>

		<%
			int sizea = cmd.size();
			for (int i = 0; i < sizea; i++) {
				DBField dbf = cmd.get(i);
				if (dbf.getview()) {
					out.println("<td></td>");
					out.println("<td align=right>" + dbf.getname() + "：</td>");
					out.println("<td align=left><input name=" + dbf.getfield()
					+ " value=></td>");
					//out.println("<td></td>");
				}
			}
		%>
		<td></td>
		<td colspan=5 align="right"><INPUT class="ButtonType"
			type="submit" value=" 查询 " name="SaveAnswer"> &nbsp;</td>
		<td></td>
		
	</tr>
	
</table>
</form>





<table>
	<tr>
		<td valign=top>
		<div class="widget_tableDiv">
		<table id="myTable">
			<thead>
			<tr>
			<%	
for (int i = 0; i < 6; i++) {
		out.print("<td>"+ cmd.get(i).getname() + "</td>");
	}
%>
				</tr>
			</thead>
			<tbody class="scrollingContent">
<%
	int setsize = dbresult.size();
	if (setsize > 0) {
		int ii = 0;

		while (dbresult.next()) {
			ii++;
			out.print("<tr>");
			for (int i = 0; i <6; i++) {
			out.print("<td>");
			if (dbresult.getString(i + 1) != null)
				out.print(dbresult.getString(i + 1));
			else
				out.print("N/A");
			out.print("</td>");
			}
			out.print("</tr>");

		}
	} else {
		out.print("<tr bgcolor=000055 height=22>");
		out.print("<td bgcolor=eeeeee >-</td>");
		out
		.print("<td bgcolor=bbbbff align=center><font color=000000> 没有记录</font></td>");
		//System.out.println(cmd.get(i).getname()+"-----");
		out.print("<td bgcolor=eeeeee></td>");
		out.print("</tr>");

	}
%>

			</tbody>
		</table>
		</div>




		</td>
	</tr>
</table>
<script type="text/javascript">
initTableWidget('myTable',700,250,Array('N','S',false,'S','N',false,false));
</script>


