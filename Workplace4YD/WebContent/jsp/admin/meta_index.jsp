<%@ page language="java" contentType="text/html; charset=GB2312"%>

<%@ page import="java.util.*,org.abel.webapp.db.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<link rel="StyleSheet" href="/Workplace4YD/theme/sermgr.css"
	type="text/css" />

<style>
.head_title {
	font-size: 12pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: ΢���ź�;
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
	font-family: ΢���ź�;
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
	font-family: ΢���ź�;
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
	width: 200px;
	clear: both
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
	width: 200px;
	clear: both
}

</style>


<%
	String baseurl = null;
	String sqltable = null;
	EIPResultSet dbresult = null;
	CmdMetaData cmd = null;

	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");
		if (request.getAttribute("metadata") != null)
			cmd = (CmdMetaData) request.getAttribute("metadata");
		if (request.getAttribute("sqltable") != null)
			sqltable = (String) request.getAttribute("sqltable");
		if (request.getAttribute("baseurl") != null)
			baseurl = (String) request.getAttribute("baseurl");
	}
%>



<table width="100%" border=0 cellpadding=4>
	<tr>
		<td colspan=2>���ݿ�����ƣ� <%=sqltable%></td>
	</tr>
	<tr>
		<td align=center width="70%" valign=top>

			<div>

				<div class="box_header">
				<table width="100%"><tr><td align="left"><font class="head_title">UserBasicInfo:</font></td>
				<td align="right">
				<div>
						<form
							action="<portletAPI:createURI><portletAPI:URIAction name="View"/></portletAPI:createURI>">

							<%
								int sizea = cmd.size();
								for (int i = 0; i < sizea; i++) {
									DBField dbf = cmd.get(i);
									if (dbf.getview()) {
										out.println("" + dbf.getname() + "��");
										out.println("<input name='" + dbf.getfield() + "'>");
									}
								}
							%>
							<INPUT class="ButtonType" type="submit" value=" ��ѯ ">
						</form>
						</td></tr></table>
					</div>
					
					</div>
				<div id="result" class="boxcontent_1">
					

					<div class="text_page_info">
						<%
							String mname = "";
							int page_c = 1;
							int page_s = 10;
							try {
								if (request.getParameter("page_s") != null)
									page_s = Integer.parseInt(request.getParameter("page_s"));
								if (request.getParameter("page_c") != null) {
								}
							} catch (Exception ee) {

							}

							if (request.getParameter("name") != null)
								mname = request.getParameter("name");
							int size = org.abel.webapp.common.DataCacheManager.CountAllRows(
									"userbasicinfo", "name", mname);
							out.println("��" + size + "����¼��");
							int page_len = size / page_s;
							if (size % page_s != 0)
								page_len = page_len + 1;
							out.println("��" + page_len + "ҳ��ʾ��");
							int current_pg = 1;
							for (int i = 1; i <= page_len && i <= current_pg + 10; i++) {
								out.print("<a href='queryM?mname=" + mname + "&page_c=" + i);
								if (page_s != 10)
									out.print("&page_s=" + page_s);
								out.print("'>");
								out.print("[" + i + "]&nbsp;");
								out.print("</a>");

							}
						%>

					</div>
					<div class="text_table_info">
						<table border="0" width="100%" cellspacing="1" cellpadding="0"
								bgcolor=6699CC>
							<tr bgcolor=000055 height=22 class="box_header">
							<td width=3%>SN</td>
								<td width=12%>����</td>
								<td width=15%>������</td>
								<td width=10%>����</td>
								<td width=10%>����</td>
								<td width=20%>��Ч��</td>
								<td width=25%>��ϵ�绰</td>
							</tr>

							<%
								int setsize = dbresult.size();
								if (setsize > 0) {
									int ii = 0;

									while (dbresult.next()) {
										ii++;
										out.print("<tr bgcolor=000055 height=20>");
										int moii = ii % 2;

										//if(ket)

										for (int i = 0; i < cmd.size(); i++) {
											out.print("<td class='form_field_1' ");
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

										out.print("</tr>");

									}
								} else {
									out.print("<tr bgcolor=000055 height=22>");

									out.print("<td bgcolor=bbbbff align=center><font color=000000> û�м�¼</font></td>");
									//System.out.println(cmd.get(i).getname()+"-----");
									out.print("<td bgcolor=eeeeee></td>");
									out.print("</tr>");

								}
							%>

						</table>
						<div></div>
		</td>
		

		<td valign=top>
			<div class="box_header">UserBasicInfo Maintain:</div>
			<div id="result" class="boxcontent_1">
				<form method="POST" action="<portletAPI:createURI><portletAPI:URIAction name="Add"/></portletAPI:createURI>">
					<table border=0 width=100% cellspacing="0" cellpadding="0">
						<tr>
							<td></td>
						<tr></tr>
						<td align=left class="fieldName">user_id<br> <input name="user_id" size=15 class="form_field_1"></td>
						<tr></tr>
						<tr>
						<td align=left class="fieldName">name<br>  <input name="name" size=15 class="form_field_1"></td>
						</tr>
						<tr>
						<td align=left class="fieldName">cname<br>  <input name="cname" size=15 class="form_field_1"></td>
						</tr>
						
						<tr>
						<td align=left class="fieldName">GENDER<br>  <select name="GENDER" class="form_field_1">
								<option value="0">��</option>
								<option value="1">Ů</option>
								<option value="0">����</option>
						</select></td></tr>
						<tr>
						<td align=left class="fieldName">VALIDFOR<br><input name="VALIDFOR" size=15 class="form_field_1"> </td>
						</tr>
						<tr>
						<td align="left" class="fieldName">company:<br>  <input name="company" size=15 class="form_field_1">
						</td>
						</tr>
						<tr>
						<td align="left" class="fieldName">M_PHOME<br>  <input name="M_PHOME" size=15 class="form_field_1">
						</td>
						</tr>
						<tr>
						<td align=right>
						<INPUT class="ButtonType" type="submit"
							value=" �����¼ "> </td>
						</tr>
						


					</table>
				</form>
			</div>
		</td>

	</tr>

</table>

