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

<script type="text/javascript">
	var http_request = false;

	function makeRequest(url, parameters) {
		http_request = false;
		if (window.XMLHttpRequest) { // Mozilla, Safari,...
			http_request = new XMLHttpRequest();
			if (http_request.overrideMimeType) {
				http_request.overrideMimeType('text/xml');
			}
		} else if (window.ActiveXObject) { // IE
			try {
				http_request = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					http_request = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
		if (!http_request) {
			alert('Cannot create XMLHTTP instance');
			return false;
		}
		http_request.onreadystatechange = processReqChange;
		http_request.open('GET', url + parameters, true);
		http_request.send(null);
	}

	function processReqChange() {
		// only if req shows "loaded"
		if (http_request.readyState == 4) {
			// only if "OK"
			if (http_request.status == 200) {
				// ...processing statements go here...

				// var tmpstr="";
				//var tmpstr = http_request.responseText;
				var xmldoc = http_request.responseXML;
				var root = xmldoc.getElementsByTagName('root').item(0);
				var arr = new Array(len);
				removeRow();
				for (var iNode = 0; iNode < root.childNodes.length; iNode++) {
					var node = root.childNodes.item(iNode);
					for (i = 0; i < node.childNodes.length; i++) {
						var sibl = node.childNodes.item(i);
						var len = parseInt(sibl.childNodes.length);

						var cnt = 0;
						// tmpstr=tmpstr+"<tr bgcolor=eeeeff>";
						for (x = 0; x < sibl.childNodes.length; x++) {
							var sibl2 = sibl.childNodes.item(x);
							var sibl3;
							if (sibl2.childNodes.length > 0) {
								sibl3 = sibl2.childNodes.item(0);
								if (sibl3.data != null)
									arr[cnt] = sibl3.data;
								else
									arr[cnt] = "N/A";
								// tmpstr=tmpstr+"<td>"+sibl3.data+" </td> ";

							} else
								arr[cnt] = "N/A";
							cnt++;
						}
						addrow(arr);
						// tmpstr=tmpstr+"</tr>";
					}

					// document.getElementById("Error_Code").value=arr[0];
				}

			} else {
				alert("There was a problem retrieving the XML data:\n"
						+ http_request.statusText);
			}
		}
	}

	function addrow(arr) {
		var tbl = document.getElementById("entityinfo");
		var lastRow = tbl.rows.length;
		var row = tbl.insertRow(lastRow);
		row.style.backgroundColor = "FFFFFF";
		for (var r = 0; r < arr.length - 1; r++) {
			var cell = row.insertCell(r);
			if (r == 1 || r == 2) {
				if (arr[r].length >= 20)
					cell.innerHTML = arr[r] + "...";
				else
					cell.innerHTML = arr[r];
			}

			else
				cell.innerHTML = arr[r];
		}

	}

	function removeRow() {
		var tb2 = document.getElementById("entityinfo");
		var rowlength = tb2.rows.length;
		for (var xi = 1; xi < rowlength; xi++) {
			var ind = rowlength - xi;
			tb2.deleteRow(ind);
		}
	}

	function getLen(str) {
		var totallength = 0;

		for (var i = 0; i < str.length; i++) {
			var intCode = str.charCodeAt(i);

			if (intCode >= 0 && intCode <= 128) {
				totallength = totallength + 1;
			} else {
				totallength = totallength + 2;
			}
		} //end for

		return totallength;

	}

	function onSubmitForm() {
		document.inputform.action = "/ErrorCodeMgr/ET/Console/addCat";
		document.inputform.submit();
		return true;
	}
	function blankP() {
	}

	function putValue1(vl1) {
		document.getElementById("SYS_ID").value = vl1;
	}

	function onSubmitForm() {
		document.inputform.action = "/ServiceConsole/ET/Console/queryErrorInfo";
		document.inputform.submit();
		return true;
	}
</script>



<%
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


<table border="0" width="100%" cellspacing="0" cellpadding="5">
	<tr>
		<td colspan=2>数据库表名称： <%=sqltable%></td>
	</tr>
	<tr>
		<td border=1 width=30% valign=top height=40>
			<!-- ---------for search------------ -->
			<div>
				<div class="box_header">
					<center>增加消息:</center>
				</div>
				
				<div id="result" class="boxcontent_1">
					<div>


						<form method="POST"
							action="<portletAPI:createURI><portletAPI:URIAction name="insertMsgElementBasic"/></portletAPI:createURI>">
							<table border=0 cellspacing="0" cellpadding="2" width=97%>
								<tr>
									<td align=left  class="fieldName">ENAME<br>
									<input type=hidden name="EID" size=15>
										<input name="ENAME" size=30  class="form_field_1"></td>

								</tr>
								<tr>
									<td align=left class="fieldName">CNAME<br><input name="CNAME" size=30  class="form_field_1"></td>

								</tr>
								<tr>
									<td align=left class="fieldName">TYPE<br><select name="TYPE"  class="form_field_1">
											<option value="MSG">输入输出</option>
									</select></td>

								</tr>
								<tr>
									<td valign=top class="fieldName">Desc:<br><textarea cols="35"
											rows="3" id="elementdesc" name="elementdesc" class="form_field_textarea"></textarea></td>
								</tr>

								<tr>
									<td align=left><INPUT class="ButtonType" type="submit"
										value=" 插入记录 " .form_field_textarea> &nbsp;</td>
								</tr>


							</table>
						</form>
					</div>
				</div>
		
					
					
			</div>

		</td>
		<td width="70%" height="16" align=center valign=top rowspan=2>

			<div>

				<div class="box_header">


					<table width="100%">
						<tr>
							<td align="left"><font class="head_title">输入输出消息:</font></td>
							<td align="right">
								<!-- ---------for search------------ -->
								<div>
									<form
										action="<portletAPI:createURI><portletAPI:URIAction name="queryM_List"/></portletAPI:createURI>">
										<table>
											<tr>
												<%
													int sizea = cmd.size();
													for (int i = 0; i < sizea; i++) {
														DBField dbf = cmd.get(i);
														if (dbf.getview()) {
															out.println("<td align=right>" + dbf.getname() + "：</td>");
															out.println("<td align=left><input name=" + dbf.getfield()
																	+ " size=15></td>");
														}
													}
												%>


												<td colspan=5 align="right"><INPUT class="ButtonType"
													type="submit" value=" 查询 " name="SaveAnswer">
													&nbsp;</td>

											</tr>
										</table>
									</form>
								</div>
							</td>
						</tr>
					</table>

				</div>

				<div class="boxcontent_1">

					<table border="0" width="97%" cellspacing="1" cellpadding="0"
						bgcolor=ffffff>
						<tr>
							<td align=right>
								<%
									String ENAME = "";
									int page_c = 1;
									int page_s = 10;
									try {
										if (request.getParameter("page_s") != null)
											page_s = Integer.parseInt(request.getParameter("page_s"));
										if (request.getParameter("page_c") != null) {
										}
									} catch (Exception ee) {

									}

									if (request.getParameter("ENAME") != null)
										ENAME = request.getParameter("ENAME");
									int size = org.abel.webapp.common.DataCacheManager.CountAllRows(
											"MSGELEMENT", "ENAME", ENAME);
									out.println("共" + size + "条记录；");
									int page_len = size / page_s;
									if (size % page_s != 0)
										page_len = page_len + 1;
									out.println("分" + page_len + "页显示。");
									int current_pg = 1;
									for (int i = 1; i <= page_len && i <= current_pg + 10; i++) {
										out.print("<a href='queryM_List?ENAME=" + ENAME + "&page_c="
												+ i);
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
					<table border="0" width="97%" cellspacing="1" cellpadding="1"
						bgcolor="6699CC">


						<%
							out.print("<tr bgcolor=000055 height=22>");
							for (int i = 0; i < cmd.size(); i++) {
								out.print("<td bgcolor=bbbbff align=right><font color=000000>"
										+ cmd.get(i).getname() + "</font></td>");
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
									out.print("<tr bgcolor=000055 height=19>");
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
											{
											if (i == 1)
												out.print("<a href=\"javascript:makeRequest('/ServiceConsole/XMLC/ServiceQuery/queryEProfile/', '?EID="
														+ dbresult.getString(i + 1) + "');\">");

											out.print(dbresult.getString(i + 1));

											if (i == 1)
												out.print("</a>");

										}
										else
											out.print("N/A");
										out.print("</font>");

									}
						%>
						<td bgcolor=ffffff><font size=1> <a
								href='<portletAPI:createURI><portletAPI:URIAction name="queryM_Detaile"/></portletAPI:createURI>EID=<%=dbresult.getString(2)%>'>*[+]*</a></font></td>
						<td bgcolor=ffffff><a
							href='<portletAPI:createURI><portletAPI:URIAction name="deleteM"/></portletAPI:createURI>EID=<%=dbresult.getString(2)%>'>*X*</a></td>

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

				</div>
				
			</div>
			<br>
			<div>

				<div class="box_header">
					<center>信息实体详细信息:</center>
				</div>
				<div id="result" class="boxcontent_1">
					<center>

						<table border="0" width="95%" cellspacing="1" cellpadding="0"
							bgcolor="AACCEE">


							<tr bgcolor=000055 height=20>
								<td bgcolor=EEEEff align=right><font color=000000>EID</font></td>
								<td bgcolor=EEEEff align=right><font color=000000>名称</font></td>
							</tr>
						</table>
						<table id="entityinfo" border="0" width="95%" cellspacing="1"
							cellpadding="0" bgcolor="AACCEE">

							<tr>
								<td bgcolor=EEEEff><font color=000000>序号</font></td>
								<td bgcolor=EEEEff><font color=000000>数据元</font></td>
								<td bgcolor=EEEEff><font color=000000>样本数据</font></td>
								<td bgcolor=EEEEff><font color=000000>子节点</font></td>
								<td bgcolor=EEEEff><font color=000000>重复</font></td>
								<td bgcolor=EEEEff><font color=000000>可空</font></td>
							</tr>

							<tr bgcolor=000055 height=20>
								<td bgcolor=FEFEff><font color=000000>&nbsp;</font>
								<td bgcolor=FEFEff><font color=000000>&nbsp;</font>
								<td bgcolor=FEFEff><font color=000000>&nbsp;</font>
								<td bgcolor=FEFEff><font color=000000>&nbsp;</font>
								<td bgcolor=FEFEff><font color=000000>&nbsp;</font>
								<td bgcolor=FEFEff><font color=000000>&nbsp;</font>
							</tr>
						</table>
					</center>

				</div>

			</div>

		</td>
	</tr>

</table>
