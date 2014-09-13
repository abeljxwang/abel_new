<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ page import="java.util.*,org.abel.webapp.db.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<link rel="StyleSheet" href="/ServiceConsole/theme/sermgr.css"
	type="text/css" />
<style>
.head_title {
	font-size: 12pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: 微软雅黑;
	padding: 3px 15px;
}

.fieldName {
	FONT-SIZE: 8pt;
	COLOR: #222266;
	text-align: left;
	TEXT-TRANSFORM: capitalize
}

.text_page_info {
	font-size: 8pt;
	text-align: right;
	COLOR: #000000;
	padding: 1px 15px;
}

.text_table_info {
	font-size: 8pt;
	text-align: left;
	COLOR: #000000;
	padding: 1px 10px;
}

.box_header {
	background: #000066;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	height: 25px;
	font-size: 12pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: 微软雅黑;
	padding: 3px 15px;
}

.boxcontent_1 {
	padding: 3px 15px;
	display: block;
	background: #ffffff;
	border: 0 solid #000066;
	border-width: 1 1 1 1px;
	font-size: 10pt;
	text-align: center;
	COLOR: #000044;
	font-family: 微软雅黑;
	padding: 3px 15px;
	border-bottom-left-radius: 8px;
	border-bottom-right-radius: 8px;
}

.form_field_1 {
	padding: 0px;
	font-size: 10pt;
	text-align: left;
	margin: 1px;
	border-style: solid;
	border-width: 1px;
	border-color: #eeeeff;
	height: 18px;
	width: 100px;
	clear: both
}

.form_field_2 {
	padding: 0px;
	font-size: 10pt;
	text-align: left;
	margin: 1px;
	border-style: solid;
	border-width: 1px;
	border-color: #eeeeff;
	height: 18px;
	width: 330px;
	clear: both
}

.form_button {
	padding: 3px;
	font-size: 10pt;
	text-align: center;
	margin: 1px;
	border-style: solid;
	border-width: 1px;
	border-color: #eeeeff;
	height: 90px;
	width:90px;
	clear: both;
	COLOR: #FFFFFF;
	background: #333399;
	border-radius: 10px;
	font-size: 14pt;
	text-align: center;
	font-family: 微软雅黑;
}

.form_field_textarea {
	padding: 0px;
	font-size: 10pt;
	text-align: left;
	margin: 1px;
	border-style: solid;
	border-width: 1px;
	border-color: #9999FF;
	height: 50px;
	width: 600px;
	clear: both
}
</style>

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







<table border="0" width="100%" bordercolorlight="#ffffff"
	cellspacing="0" cellpadding="10">
	<tr>
		<td width="5%" height=16></td>
		<td width="90%">数据库表名称： <%=sqltable%></td>
		<td width="5%"></td>
	</tr>
	<tr>
		<td></td>
		<td border=1>
			<!-- ---------for search------------ -->

			<div>

				<div class="box_header">
					<center>Service List:</center>
				</div>
				<div id="result" class="boxcontent_1">
					<div
						style="height: 30px; overflow: hidden; border: 1px solid #FFFFFF;">

						<form method="post"
							action="<portletAPI:createURI><portletAPI:URIAction name="querySLst"/></portletAPI:createURI>">
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
									<td colspan=5 align="right"><INPUT class="ButtonType"
										type="submit" value=" 查询 " name="SaveAnswer"> &nbsp;</td>

								</tr>
							</table>
						</form>
					</div>



					<div>
						<center>
							<table border="0" width="97%" cellspacing="1" cellpadding="0"
								bgcolor=ffffff>
								<tr>
									<td align=right>
										<%
											String serviceid = "";
											int page_c = 1;
											int page_s = 10;
											try {
												if (request.getParameter("page_s") != null)
													page_s = Integer.parseInt(request.getParameter("page_s"));
												if (request.getParameter("page_c") != null) {
												}
											} catch (Exception ee) {

											}

											if (request.getParameter("serviceid") != null)
												serviceid = request.getParameter("serviceid");
											int size = org.abel.webapp.common.DataCacheManager.CountAllRows(
													"servicelist", "serviceid", serviceid);
											out.println("共" + size + "条记录；");
											int page_len = size / page_s;
											if (size % page_s != 0)
												page_len = page_len + 1;
											out.println("分" + page_len + "页显示。");
											int current_pg = 1;
											for (int i = 1; i <= page_len && i <= current_pg + 10; i++) {
												out.print("<a href='querySLst?serviceid=" + serviceid
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
							<table border="0" width="97%" cellspacing="1" cellpadding="0"
								bgcolor=6699CC>


								<%
									out.print("<tr bgcolor=000055  height=22>");
									for (int i = 0; i < cmd.size(); i++) {
										out.print("<td bgcolor=bbbbff align=right><font color=000000>"
												+ cmd.get(i).getname() + "&nbsp;</font></td>");
									}
									for (int i = 0; i < 4; i++) {
										out.print("<td bgcolor=bbbbff align=right></td>");
									}
									out.print("</tr>");
									String message = null;
									int setsize = dbresult.size();
									if (setsize > 0) {
										int ii = 0;
										String keytmp = null;
										while (dbresult.next()) {
											ii++;
											if (ii >= 15) {

												message = "记录太多请缩小查询范围";
												break;
											}
											out.print("<tr bgcolor=000055 height=20>");
											int moii = ii % 2;
											keytmp = dbresult.getString(1);
											//if(ket)

											for (int i = 0; i < cmd.size(); i++) {
												out.print("<td ");
												if (moii == 0)
													out.print(" bgcolor=bbbbff");
												else
													out.print(" bgcolor=eeeeff");
												out.print("><font color=000000>");
												if (dbresult.getString(i + 1) != null)
													out.print("&nbsp;"+dbresult.getString(i + 1));
												else
													out.print("N/A");
												out.print("</font>");

											}
								%>
								<td bgcolor=ffffff><font size=1> <a
										href="<portletAPI:createURI><portletAPI:URIAction name="queryS_Detaile"/></portletAPI:createURI>serviceid=<%=dbresult.getString(2)%>">&nbsp;*[+]*</a></font></td>
								<td bgcolor=ffffff><a
									href="<portletAPI:createURI><portletAPI:URIAction name="delete"/></portletAPI:createURI>serviceid=<%=dbresult.getString(2)%>">&nbsp;*X*</a></td>
								<td bgcolor=ffffff><a
									href="<portletAPI:createURI><portletAPI:URIAction name="queryS4Up"/></portletAPI:createURI>serviceid=<%=dbresult.getString(2)%>">&nbsp;*U*</a></td>
								<td bgcolor=ffffff><a
									href="<portletAPI:createURI><portletAPI:URIAction name="serviceCard"/></portletAPI:createURI>ServiceID=<%=dbresult.getString(2)%>">&nbsp;*SCD*</a></td>
								
								<%
									out.print("</tr>");

										}
									} else {
										out.print("<tr bgcolor=000055 height=22>");
										out.print("<td bgcolor=eeeeee >-</td>");
										out.print("<td bgcolor=bbbbff align=center><font color=000000> 没有记录</font></td>");
										//System.out.println(cmd.get(i).getname()+"-----");
										out.print("<td bgcolor=eeeeee></td>");
										out.print("</tr>");

									}
								%>

							</table>
						</center>
					</div>
				</div>
			</div>

		</td>
		<td width="5%" height="16"></td>
	</tr>

	<tr>
		<td></td>
		<td>
			<!-- ---------for search------------ -->
			<div>

				<div class="box_header">
					<center>Service Add:</center>
				</div>
				<div id="result" class="boxcontent_1">
					<div style="height: 120px; overflow: hidden; border: 1px solid #FFFFFF;">

						<form method="post"
							action="<portletAPI:createURI><portletAPI:URIAction name="insertServ"/></portletAPI:createURI>">
<table width=100%><tr><td align=center> 
<table border=0 width=95% cellspacing="0" cellpadding="0">
								<tr>

									<td align=left class="fieldName" width=18%>ServiceID,Name</td>
									<td align=left class="fieldName" width=18%>中文名/Servicename,</td>
									<td align=left class="fieldName" width=50%>endpoint</td>
									<td align=right rowspan=4 width=14%><INPUT  class="form_button" type="submit"
										value=" 提交 "> &nbsp;</td>
								</tr>
								<tr>

									<td align=left><input name="ServiceID" size=15 class="form_field_1"></td>
									<td align=left><input name="Servicename" size=15 class="form_field_1"></td>
									<td align=left><input name="endpoint" size=50 class="form_field_2"></td>

								</tr>
							
								
								<tr>

									<td align=left  class="fieldName">servdesc</td>
									<td align=left></td>
									<td align=left></td>
									<td align=right></td>
								</tr>
								<tr>

									<td align=left colspan=3><textarea cols="100" rows="3"
											id="servdesc" name="servdesc" class="form_field_textarea"></textarea></td>
								</tr>


							</table>
</td></tr></table>
							
						</form>
					</div>
				</div>
				<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
					class="xb2"></b><b class="xb1"></b></b>
			</div>
		</td>
		<td></td>
	</tr>
</table>

