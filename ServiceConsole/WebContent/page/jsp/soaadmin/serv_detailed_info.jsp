<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ page
	import="java.util.*,org.abel.webapp.db.*,org.abel.webapp.common.CoreDataCacheManager"%>
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

<SCRIPT language=JavaScript type=text/javascript>
	function onSubmitForm() {

		document.inputform.action = "/ServiceConsole/ET/SOAAdmin/addOPMSG";
		document.inputform.submit();
		return true;
	}

	function selectVtoInput(value1, value2, value3) {
		document.inputform.operation_name.value = value1;
		document.inputform.input.text = value2;
		var selObj = document.getElementById('input');
		var i;
		//var count = 0;
		for (i = 0; i < selObj.options.length; i++) {
			if (selObj.options[i].value == value2) {
				selObj.selectedIndex = i;
				//count++;
				//return;
			}
		}
		var selObj1 = document.getElementById('output');

		//var count = 0;
		var j;
		for (j = 0; j < selObj1.options.length; j++) {
			if (selObj1.options[j].value == value3) {
				selObj1.selectedIndex = j;
				//count++;
				return;
			}
		}

	}

	function selectOption(valuein) {
		var strtmp = valuein
				.substring(valuein.indexOf("/") + 1, valuein.length);
		document.inputform.operation_name.value = strtmp;

		var selObj = document.getElementById('output');
		var i;
		//var count = 0;
		for (i = 0; i < selObj.options.length; i++) {
			if (selObj.options[i].text.indexOf(strtmp) > 0) {
				selObj.selectedIndex = i;
				//count++;
				return;
			}
		}
	}
</script>


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
	cellspacing="0" cellpadding="10" bordercolor="#ffffff">
	<tr>
		<td colspan=2>数据库表名称： <%=sqltable%></td>
	</tr>
	<tr>




		<td width="60%" height="16" align=center rowspan=2 valign=top>
			<table border="0" width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td valign=top>

						<div>

							<div class="box_header">
								<center>服务结构信息:</center>
							</div>
							<div id="result" class="boxcontent_1">
								<table width="97%" cellspacing="1" cellpadding="0">
									<%
										Properties elements = CoreDataCacheManager.getAllMessageDesc();
										String opstr = "";
										String opstr_resp = "";

										String key = null, value = null;
										Enumeration keys = elements.keys();

										while (keys.hasMoreElements()) {
											key = keys.nextElement() + "";
											value = elements.getProperty(key);
											if (value.endsWith("response"))
												opstr_resp = opstr_resp + "\r<option value=\"" + key
														+ "\">" + key + "/" + value + "</option>";
											else {
												//if(value.endsWith("response"))	
												opstr = opstr + "\r<option value=\"" + key + "\">" + key
														+ "/" + value + "</option>";
											}
										}

										String servn = null;

										int setsize = dbresult.size();
										if (setsize > 0) {
											if (dbresult.next()) {
												servn = dbresult.getString(1);
												for (int i = 0; i < cmd.size() - 2; i++) {

													out.print("<tr>");

													//out.print("<tr bgcolor=000055 height=22>");
													out.print("<td ");

													if (i >= 4)
														out.print(" bgcolor=bbbbff ");
													else
														out.print(" bgcolor=6699CC");

													out.print(" align=right>");
													out.print("<font color=000000>" + cmd.get(i).getname()
															+ "</font></td>");

													if (i >= 4)
														out.print("<td bgcolor=ffffff class=xl31><font color=000000>");
													else
														out.print("<td bgcolor=ffffff class=xl30><font color=000000>");
													if (dbresult.getString(i + 1) != null) {
														if (i >= 5)
															out.print(dbresult.getString(i + 3) + " [");
														out.print(dbresult.getString(i + 1));
														if (i >= 5)
															out.print("]");
													} else
														out.print("N/A");
													if (i == 4)
														out.print("<a href=\"javascript:selectVtoInput('"
																+ dbresult.getString(5) + "','"
																+ dbresult.getString(6) + "','"
																+ dbresult.getString(7) + "');\"> i </a>");
													if (i == 4)
														out.print("<a href='delOP?serviceid="
																+ dbresult.getString(1)
																+ "&operation_name="
																+ dbresult.getString(5)
																+ "'> ****[x]**** </a>");
													if (i == 5)
														out.print("<a href='queryM_Detaile?EID="
																+ dbresult.getString(6) + "'> *[+]* </a>");
													if (i == 6)
														out.print("<a href='queryM_Detaile?EID="
																+ dbresult.getString(7) + "'> *[+]* </a>");

													out.print("</font></td>");

												}

												out.print("</tr>");
											}

											while (dbresult.next()) {
												out.print("<tr bgcolor=005555>");
												out.print("<td bgcolor=336699 height=1 colspan=2></td>");
												out.print("</tr>");
												for (int i = 4; i < cmd.size() - 2; i++) {
													out.print("<tr bgcolor=000055>");
													out.print("<td bgcolor=bbbbff align=right><font color=000000>"
															+ cmd.get(i).getname() + "</font></td>");
													out.print("<td bgcolor=ffffff class=xl31><font color=000000>");

													if (dbresult.getString(i + 1) != null) {
														if (i >= 5)
															out.print(dbresult.getString(i + 3) + " [");
														out.print(dbresult.getString(i + 1));
														if (i >= 5)
															out.print("]");
													} else
														out.print("N/A");
													if (i == 4)
														out.print("<a href=\"javascript:selectVtoInput('"
																+ dbresult.getString(5) + "','"
																+ dbresult.getString(6) + "','"
																+ dbresult.getString(7) + "');\"> i </a>");
													if (i == 4)
														out.print("<a href='delOP?serviceid="
																+ dbresult.getString(1)
																+ "&operation_name="
																+ dbresult.getString(5)
																+ "'> ****[x]**** </a>");
													if (i == 5)
														out.print("<a href='queryM_Detaile?EID="
																+ dbresult.getString(6) + "'> *[+]* </a>");
													if (i == 6)
														out.print("<a href='queryM_Detaile?EID="
																+ dbresult.getString(7) + "'> *[+]* </a>");

													out.print("</font></td>");

												}

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
							</div>
							<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
								class="xb2"></b><b class="xb1"></b></b>
						</div>
					</td>
					<td></td>
				</tr>


			</table>
		</td>

			<td border=1 width=40% valign=top>
			<!-- ---------for search------------ -->

			<div>

				<div class="box_header">
					<center>创建服务操作[Operation]信息::</center>
				</div>
				<div id="result" class="boxcontent_1">
					<div
						style="height: 150px; overflow: hidden; border: 1px solid #FFFFFF;">

						<form name=inputform method=post
							action="<portletAPI:createURI><portletAPI:URIAction name="insertServOperation"/></portletAPI:createURI>">

							<table border=0 width=90% cellspacing="1" cellpadding="1">
								<tr>
									<td align=left bgcolor=bbbbff>ServiceID,</td>
									<td align=left><%=servn%><input type=hidden
										name="serviceid" size=15 value="<%=servn%>"></td>
								</tr>
								<tr>
									<td align=left bgcolor=bbbbff>operation_name</td>
									<td align=left><input name="operation_name" size=15></td>
									<td align=right><INPUT class="ButtonType" type="image"
										src="/ServiceConsole/images/quickinsert.gif"
										onclick="onSubmitForm();"></td>
								</tr>
								<tr>
									<td align=left bgcolor=bbbbff>input</td>
									<td align=left colspan=2><select name="input" id=input
										onChange='selectOption(this.options[this.options.selectedIndex].text)'
										Onfocus='selectOption(this.options[this.options.selectedIndex].text)'>
											<%=opstr%></select></td>
								</tr>
								<tr>
									<td align=left bgcolor=bbbbff>output</td>
									<td align=left colspan=2><select name="output" id=output>
											<%=opstr_resp%></select></td>
								</tr>

								<tr>
									<td align=left></td>
									<td align=right><INPUT class="ButtonType" type="submit"
										value=" 插入记录 "></td>

								</tr>
							</table>
						</form>
					</div>
				</div>
				<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
					class="xb2"></b><b class="xb1"></b></b>
			</div>

		</td>
	</tr>
</table>

