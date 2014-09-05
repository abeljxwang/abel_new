<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<%@ page import="java.util.*,org.abel.webapp.db.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<link href="/ServiceConsole/theme/jquery-ui.css" rel="stylesheet"
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

.form_field_mini {
	padding: 0px;
	font-size: 10pt;
	text-align: left;
	margin: 1px;
	border-style: solid;
	border-width: 1px;
	border-color: #eeeeff;
	height: 18px;
	width: 80px;
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


<script src="/ServiceConsole/js/jquery/jquery-1.10.2.js"></script>
<script src="/ServiceConsole/js/jquery/jquery-ui-1.10.4.js"></script>

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



<script>
	$(function() {
		function log( message ) {
			$( "<div>" ).text( message ).prependTo( "#log" );
			$( "#log" ).scrollTop( 0 );
		}

		$( "#MNAME" ).autocomplete({
			source: "<%=request.getContextPath()%>/AutoCP/?type=metadata",
			minLength: 2,
			focus: function( event, ui ) {
		        $( "#MNAME" ).val( ui.item.label );
		        return false;
		      },
			select: function( event, ui ) {				
			        $( "#MNAME" ).val( ui.item.value );
			        return false;				
			}
		})
		.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
			return $( "<li>" )
			.append( "<a class='text_page_info'>" + item.label + "(ID: " + item.id + ")</a>" )
			.appendTo( ul );
			};
			
		
			$( "#SUBEID" ).autocomplete({
				source: "<%=request.getContextPath()%>/AutoCP/?type=entity",
				minLength: 2,
				focus: function( event, ui ) {
			        $( "#SUBEID" ).val( ui.item.label );
			        return false;
			      },
				select: function( event, ui ) {				
				        $( "#SUBEID" ).val( ui.item.id );
				        return false;				
				}
			})
			.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
				return $( "<li>" )
				.append( "<a class='text_page_info'>" + item.label + "(ID: " + item.id + ")</a>" )
				.appendTo( ul );
				};	
			
	});
</script>


<script language="javascript" type="text/javascript">
	function onSubmitFormE() {

		//document.ead.action ="/ServiceConsole/ET/SOAAdmin/insertMsgStrucE";
		var selObj = document.addee.SUBEID.value;
		var posi = selObj.indexOf("|");
		var stringa = selObj.substring(0, posi);
		//document.write(stringa);
		document.addee.SUBEID.value = stringa;
		document.addee.submit();
		return true;
	}
</script>





<table border="0" width="100%" cellspacing="0" cellpadding="2">
	<tr>
		<td width="5%" height=16></td>
		<td width="90%">数据库表名称： <%=sqltable%></td>
		<td width="5%"></td>
	</tr>
	<tr>
		<td></td>
		<td border=1 bordercolor=ffffff>

			<div>
				<div class="box_header">
					<table width="100%">
						<tr>
							<td align="left"><font class="head_title">消息结构信息:</font></td>
							<td align="right">
								<!-- ---------for search------------ -->

								<form name="ead" type=POST
									daction="<portletAPI:createURI><portletAPI:URIAction name="queryM_Detaile"/></portletAPI:createURI>">
									<table border=0>
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
							</td>
						</tr>
					</table>


				</div>
				<div id="result" class="boxcontent_1">

					<center>
						<table border="0" width="98%" cellspacing="1" cellpadding="0"
							bgcolor=6699CC>


							<%
								String eid_tmp = null;
								out.print("<tr bgcolor=000055 height=20>");
								out.print("<td bgcolor=eeeeee > SN </td>");
								for (int i = 0; i < cmd.size(); i++) {
									out.print("<td bgcolor=bbbbff align=right><font color=000000>"
											+ cmd.get(i).getname() + "</font></td>");
								}
								out.print("</tr>");

								int setsize = dbresult.size();
								if (setsize > 0) {
									int ii = 0;
									String keytmp = null;
									int rntmp = 0;
									while (dbresult.next()) {
										eid_tmp = dbresult.getString(1);
										ii++;
										out.print("<tr bgcolor=000055 height=20>");
										out.print("<td bgcolor=eeeeee >-</td>");
										int moii = ii % 2;
										keytmp = dbresult.getString(1);
										//if(ket)
										if (ii == 1) {
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
										} else {
											out.print("<td bgcolor=ffffff>- </td><td bgcolor=ffffff>- </td>");
											for (int i = 2; i < cmd.size(); i++) {
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
										}
							%>
							<td bgcolor=ffffff><font size=1> <a
									href='<portletAPI:createURI><portletAPI:URIAction name="delEM"/></portletAPI:createURI>
				sn=<%=dbresult.getString(3)%> &EID=<%=dbresult.getString(1)%>'>*X*</a></td>

							<%
								out.print("</tr>");

									}
								} else {
									out.print("<tr bgcolor=000055 height=20>");
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

			</div> <br>
		</td>
		<td></td>
	</tr>

	<tr>
		<td></td>
		<td bgcolor=ffffff valign=top>
			<table width=100%>
				<tr>
					<td width=48%>
						<div>
							<div class="box_header">
								<center>增加数据项:</center>
							</div>
							<div class="boxcontent_1">


								<form method="post" name="addform"
									action="<portletAPI:createURI><portletAPI:URIAction name="insertMsgStruc"/></portletAPI:createURI>">
									<table border=0 width=90% cellspacing="0" cellpadding="0">
										<tr>

											<td align=left class="fieldName" colspan=2>EID: <%
												if (eid_tmp == null)
													eid_tmp = request.getParameter("EID");
											%> <%=eid_tmp%><input type=hidden name="EID" size=15
												value="<%=eid_tmp%>">
											</td>

										</tr>
										<tr>
											<td align=left class="fieldName" colspan=2>
												<div class="ui-widget">
													<label for="MNAME">MNAME: </label><br> <input
														id="MNAME" name="MNAME" size=18 class="form_field_1">
												</div>
											</td>
											<input type="hidden" name="SUBFLAG" value="0">
										</tr>
										<tr>
											<td align=left class="fieldName" colspan=2>DF_VALUE<br>
												<input name="DF_VALUE" size=10 class="form_field_1">
											</td>
										</tr>
										<tr>
											<td align=left class="fieldName">RPTTIME <br> <input
												name="RPTTIME" size=3 value=1 class="form_field_mini">
											</td>

											<td align=left class="fieldName">ISNULL<br> <select
												name="ISNULL" class="form_field_mini">
													<option value="1">可空</option>
													<option value="0">不可空</option>
											</select>
											</td>
										</tr>

										<tr>
											<td align="leftr" colspan=2><INPUT class="form_field_1"
												type="submit" value="  插入记录     "></td>
										</tr>
									</table>
								</form>

							</div>
						</div>



					</td>
					<td width=4%></td>
					<td width=48% valign="top" align="right">
						<div>
							<div class="box_header">
								<center>增加子节点:</center>
							</div>
							<div class="boxcontent_1">

								<form method="post" name="addee"
									action="<portletAPI:createURI><portletAPI:URIAction name="insertMsgStrucE"/></portletAPI:createURI>">
									<table border=0 width=90% cellspacing="0" cellpadding="0"
										bgcolor=ffffff>
										<tr>
											<td align=left class="fieldName" COLSPAN=2>EID: <%
												if (eid_tmp == null)
													eid_tmp = request.getParameter("EID");
											%> <%=eid_tmp%><input type=hidden name="EID" size=15
												value="<%=eid_tmp%>">
											</td>
										</tr>

										<tr>
											<td align=left class="fieldName" COLSPAN=2>SUBEID<br>

												<div class="ui-widget">
													<label for="SUBEID">SUBEID: </label><br> <input
														name="SUBEID" id="SUBEID" class="form_field_1">
												</div>
											</td>


										</tr>

										<input type="hidden" name="DF_VALUE" size=10>
										<input type="hidden" name="SUBFLAG" value="1">

										<tr>
											<td align=left class="fieldName">RPTTIME <br> <input
												name="RPTTIME" size=3 value=1 class="form_field_mini">
											</td>

											<td align=left class="fieldName">ISNULL<br> <select
												name="ISNULL" class="form_field_mini">
													<option value="1">可空</option>
													<option value="0">不可空</option>
											</select>
											</td>
										</tr>

										<tr>
											<td align=center colspan=2><INPUT class="ButtonType"
												type="submit" value=" 插入记录 "> &nbsp;</td>
										</tr>
									</table>
								</form>


							</div>

						</div>
					</td>
				</tr>
			</table>


		</td>
		<td></td>
	</tr>

</table>
