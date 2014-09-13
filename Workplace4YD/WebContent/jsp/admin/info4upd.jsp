
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<%@ page import="java.util.*"%>
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
	//msg

	String sqltable = null;
	EIPResultSet dbresult = new EIPResultSet();

	CmdMetaData cmd = new CmdMetaData();
	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");
		if (request.getAttribute("metadata") != null)
			cmd = (CmdMetaData) request.getAttribute("metadata");
		if (request.getAttribute("sqltable") != null)
			sqltable = (String) request.getAttribute("sqltable");

	}
%>



<table border="0" width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td width="5%" height=16></td>
		<td width="90%">数据库表名称： <%=sqltable%></td>
		<td width="5%"></td>
	</tr>
	<tr>
		<td></td>
		<td border=1 bordercolor=000000>
			<!-- ---------for search------------ -->
			<div>

				<div class="box_header">
					<center>服务详细信息修改:</center>
				</div>
				<div id="result" class="boxcontent_1">
				
					<center>
						<div
							style="height: 180px; overflow: hidden; border: 1px solid #FFFFFF;">

							<%
								int sizea = cmd.size();
								String[] kar1 = new String[sizea];
								String[] var1 = new String[sizea];
								if (dbresult.next())
									System.out.println("-------------");

								for (int i = 0; i < sizea; i++) {
									DBField dbf = cmd.get(i);
									if (dbf.getview()) {
										kar1[i] = dbf.getfield();
										var1[i] = dbresult.getString(i + 1);
									}
								}
							%>
							<form method="post"
								action="<portletAPI:createURI><portletAPI:URIAction name="updateS"/></portletAPI:createURI>">

								<table border=0 width=90% cellspacing="0" cellpadding="0">
									<tr>
										<td align=left>ID/Name</td>
										<td align=left>中文名</td>
									</tr>
									<tr>
										<td align=left><input name="<%=kar1[0]%>"
											value='<%=var1[0]%>' size=30></td>
										<td align=left><input name="<%=kar1[1]%>"
											value='<%=var1[1]%>' size=45></td>

									</tr>
									<tr>
										<td align=left>ENDPOINT：</td>
									</tr>
									<tr>
										<td colspan=2><input name=<%=kar1[2]%>
											value='<%=var1[2]%>' size=80></td>

									</tr>
									<tr>
										<td align=left>DESC：</td>
									</tr>
									<tr>
										<td colspan=2><textarea cols="90" rows="3"
												id="elementdesc" name="<%=kar1[3]%>"><%=var1[3]%></textarea></td>

									</tr>

									<tr>
										<td></td>
										<td><INPUT class="ButtonType" type="submit"
											value=" UPDATE " name="SaveAnswer">
											&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</tr>

								</table>
							</form>
						</div>
					</center>


				</div>
				<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
					class="xb2"></b><b class="xb1"></b></b>
			</div>
		</td>
		<td></td>
	</tr>


	<tr>
		<td width="5%" height="20"></td>
		<td width="90%" height="20"></td>
		<td width="5%" height="20"></td>
	</tr>
	<tr>
		<td width="5%" height="20"></td>
		<td width="90%" height="20"></td>
		<td width="5%" height="20"></td>
	</tr>
</table>