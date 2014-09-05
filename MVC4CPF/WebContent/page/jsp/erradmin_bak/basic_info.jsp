<%@ page language="java" contentType="text/html; charset=GB2312"%>

<%@ page import="org.abel.webapp.db.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<link rel="StyleSheet" href="/MVC4CPF/theme/errormgr.css"
	type="text/css" />

<%
	EIPResultSet dbresult = null;
	CmdMetaData cmd = null;

	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");
		//fieldname=(java.util.Vector)rp.getRResult("fieldname");
		//if(rp.getRResult("fieldlength")!=null) fiellength=(java.util.Vector)rp.getRResult("fieldlength");
		if (request.getAttribute("metadata") != null)
			cmd = (CmdMetaData) request.getAttribute("metadata");
	}
%>
<center>
<table border="0" width="90%"  cellspacing="0" cellpadding="0" bordercolor="#ffffff">
	<tr>
		<td><!-- ---------for search------------ -->
		<form
			action="<portletAPI:createURI><portletAPI:URIAction name="querySystemCodeInfo"/></portletAPI:createURI>">

		<div id="xsnazzy"><b class="xtop"><b class="xtb1"></b><b
			class="xtb2"></b><b class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<center>查询列表:</center>
		</div>
		<div class="Cxboxcontent1">
		<center>
		<table border=0 width=95%>

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
		</center>
		</div>

		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>
		</form>
		</td>
	</tr>

	<tr>
		<td>
		<div id="xsnazzy"><b class="xtop"><b class="xtb1"></b><b
			class="xtb2"></b><b class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<table border="0" width="98%" cellspacing="1" cellpadding="0">
			<tr>
				<td align=right>
				<%
					String mname = "";
					//int page_c = 1;
					int page_s = 10;
					try {
						if (request.getParameter("page_s") != null)
							page_s = Integer.parseInt(request.getParameter("page_s"));
						if (request.getParameter("page_c") != null) {
						}
					} catch (Exception ee) {

					}

					if (request.getParameter("Sys_ID") != null)
						mname = request.getParameter("Sys_ID");
					int size = org.abel.webapp.common.DataCacheManager.CountAllRows(
							"systemrec", "Sys_ID", mname);
					out.println("共" + size + "条记录；");
					int page_len = size / page_s;
					if (size % page_s != 0)
						page_len = page_len + 1;
					out.println("分" + page_len + "页显示。");
					int current_pg = 1;
					for (int i = 1; i <= page_len && i <= current_pg + 10; i++) {
						out.print("<a href='querySystemCodeInfo?Sys_ID=" + mname
								+ "&page_c=" + i);
						if (page_s != 10)
							out.print("&page_s=" + page_s);
						out.print("'>");
						out.print("[" + i + "]&nbsp;");
						out.print("</a>");

					}
				%>
				</td>
			</tr>
		</table>
		</div>
		<div class="Cxboxcontent1">
		<center>
		<table border="0" width="98%" cellspacing="1" cellpadding="1">


			<%
				out.print("<tr bgcolor=000055 height=22>");
				for (int i = 0; i < cmd.size(); i++) {
					out.print("<td bgcolor=bbbbff align=right><font color=000000>"
							+ cmd.get(i).getname() + "</font></td>");
				}
				out.print("</tr>");

				int setsize = dbresult.size();
				if (setsize > 0) {
					int ii = 0;

					while (dbresult.next()) {
						ii++;
						out.print("<tr bgcolor=000055 height=18>");
						int moii = ii % 2;

						//if(ket)

						for (int i = 0; i < cmd.size(); i++) {
							out.print("<td ");
							if (moii == 0)
								out.print(" bgcolor=bbbbff");
							else
								out.print(" bgcolor=eeeeff");
							out.print("><font color=000000>");
							if (dbresult.getString(i + 1) != null)
								out.print(dbresult.getString(i + 1));
							else
								out.print("N/A");
							out.print("</font>");

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

		</table>
		</center>
		</div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>

		</td>
	</tr>
</table>
</center>
