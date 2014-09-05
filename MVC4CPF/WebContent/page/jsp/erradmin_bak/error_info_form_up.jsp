<%@ page language="java" contentType="text/html; charset=GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />



<%@ page import="com.ibm.ais.util.*,org.abel.webapp.db.*"%>

<link rel="StyleSheet" href="/MVC4CPF/theme/errormgr.css"
	type="text/css" />

<script type="text/javascript"
	src="/MVC4CPF/js/error_mgr/errormgr_2.js"></script>
	
<script type="text/javascript">
	function onSubmitFormUU()
	{

 	  document.inputform.action ="/MVC4CPF/ET/Console/upErrorInfoForm";
 	  document.inputform.submit();
	  return true;
	}

</script>
<style>
</style>


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


<table width=100% border=0>
	<tr>
		<td width=40% valign=top>
		<div id="xsnazzy"><b class="xtop"><b class="xtb1"></b><b
			class="xtb2"></b><b class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<center>错误码/响应码列表:</center>
		</div>
		<div class="Cxboxcontent1">
		<center>
		<table border="0" width="95%" cellspacing="1" cellpadding="0"
			bgcolor=ffffff>
			<tr>
				<td align=right>
				<%
					String Error_Code = "";
					String Sys_ID = "";
					//int page_c=1;
					int page_s = 10;
					int current_pg = 1;
					try {
						if (request.getParameter("page_s") != null)
							page_s = Integer.parseInt(request.getParameter("page_s"));
						if (request.getParameter("page_c") != null) {
							current_pg=Integer.parseInt(request.getParameter("page_c"));
						}
					} catch (Exception ee) {

					}

					if (request.getParameter("mname") != null)
						Error_Code = request.getParameter("mname");
					if (request.getParameter("Sys_ID") != null)
						Sys_ID = request.getParameter("Sys_ID");

					int size = DataDAO.getERecAmount(Error_Code, Sys_ID);
					out.println("共" + size + "条；");
					int page_len = size / page_s;
					if (size % page_s != 0)
						page_len = page_len + 1;
					out.println("分" + page_len + "页.当前第:"+current_pg+"页<br>");
					int pgtmpstart=1;
					if( (current_pg-5) <=0) pgtmpstart=1;
					else pgtmpstart=current_pg-5;
					int pgtmpend=page_len;
					if((pgtmpstart+9) <=page_len) pgtmpend=pgtmpstart+9;
					if(pgtmpend<=10) pgtmpstart=1;
					
					for (int i = pgtmpstart; i <= page_len && i <= pgtmpend; i++) {
						out.print("<a href='upErrorInfoForm?Error_Code=" + Error_Code
								+ "&Sys_ID=" + Sys_ID + "&page_c=" + i);
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

		<table border="1" width="95%" cellspacing="0" cellpadding="0">


			<%
				out.print("<tr bgcolor=000055 height=18>");
				for (int i = 0; i < cmd.size(); i++) {
					out.print("<td bgcolor=bbbbff align=left><font color=000000>"
							+ cmd.get(i).getname() + "</font></td>");
				}
				out.print("</tr>");
				

				int setsize = dbresult.size();
				if (setsize > 0) {
					int ii = 0;
					//String message = null;
					//String keytmp = null;
					while (dbresult.next()) {
						ii++;
						if (ii >= 15) {

							//message = "记录太多请缩小查询范围";
							break;
						}
						out.print("<tr bgcolor=000055>");
						int moii = ii % 2;
						//keytmp = dbresult.getString(1);
						//if(ket)

						for (int i = 0; i < cmd.size(); i++) {
							out.print("<td ");
							if (moii == 0)
								out.print(" bgcolor=bbbbff");
							else
								out.print(" bgcolor=eeeeff");
							out.print("><font color=000000>");
							if (dbresult.getString(i + 1) != null) {

								if (i == 0)
									out
											.print("<a href=\"javascript:makeRequest('/XMLEngine/XMLC/User_Mgr/op1/', '?Error_Code="
													+ dbresult.getString(i + 1)
													+ "');\">"
													+ dbresult.getString(i + 1)
													+ "</a></font></td>");

								else
									out.print(dbresult.getString(i + 1));
							} else
								out.print("N/A");
							out.print("</font>");

						}

						out.print("</tr>");

					}
				} else {
					out.print("<tr bgcolor=000055>");
					out.print("<td bgcolor=eeeeee >-</td>");
					out
							.print("<td bgcolor=bbbbff align=center><font color=000000> 没有记录</font></td>");
					out.print("<td bgcolor=eeeeee></td>");
					out.print("</tr>");

				}
			%>

		</table></center>
		</div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>

		<form name=inputform method=post>
		<div id="xsnazzy"><b class="xtop"><b class="xtb1"></b><b
			class="xtb2"></b><b class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<center>查询:</center>
		</div>
		<div class="Cxboxcontent1">
		<table width=95%>
		<tr><td>
		System: <select name="Sys_ID">
			<option value="0700">EAIH</option>
			<option value="0250">ECTIP</option>
			<option value="0140">CCI</option>
			<option value="0141">CSR</option>
			<option value="0400">Long-NS</option>
			<option value="0020">CCBSS</option>
			<option value="0420">CCFS</option>
			<option value="0130">IBS</option>
			<option value="0010">CCBS</option>
			<option value="0260">ECIF</option>
			<option value="0150">VSS</option>
			<option value="9999">通用</option>
			<option value="">-ALL-</option>
		</select>
		</td><td>
<script type="text/javascript">
	  document.inputform.Sys_ID.value ="<%=Sys_ID%>";	
</script>
		 Code: <input type="text" name="Error_Code" size=15> 
		 </td></tr>
		 <tr><td></td><td>
		 <input
			value="  query   " type="button" onclick="onSubmitFormUU()" />
			</td></tr></table>	
			
			</div>

		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>
		</form>
		
		<div id="testpan"></div>
		
		</td>
		<td width=58% valign=top>
		<form name="errorinfoform" id="errorinfoform" method=post
			action="<portletAPI:createURI><portletAPI:URIAction name="addErrorInfo"/></portletAPI:createURI>">

		<div id="xsnazzy"><b class="xtop"><b class="xtb1"></b><b
			class="xtb2"></b><b class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<center>错误码/响应码详细信息:</center>
		</div>
		<div class="Cxboxcontent1">
		<center><!-- ----Error_Code,Error_Msg,Suggestion,Error_Level, Error_Cat, Error_Cat_Sub,Sys_ID----- -->
		<table border=0 width=95% cellspacing="0" cellpadding="0">
			<tr>
				<td align=left colspan=4>Error_Code: <input type=text name="Error_Code4I"
					size=15 disabled><input type=hidden name="Error_Code" size=15>				
					</td>

			</tr>
			<tr>
				<td colspan=4 height=1 bgcolor=336699></td>
			</tr>
			<tr>
				<td align=left>Sys_ID</td>
				<td align=left>Level</td>
				<td align=left>Error_Cat</td>
				<td align=left>Error_Cat_Sub</td>
			</tr>

			<tr>
				<td align=left><select name="Sys_ID" id="Sys_ID_1"
					onChange="selectChainRequest('/XMLEngine/XMLC/SysCatCode/errorcat/',this.value,'1')">
					<option value="0700">EAIH</option>
					<option value="0250">ECTIP</option>
					<option value="0140">CCI</option>
					<option value="0141">CSR</option>
					<option value="0400">Long-NS</option>
					<option value="0020">CCBSS</option>
					<option value="0420">CCFS</option>
					<option value="0130">IBS</option>
					<option value="0010">CCBS</option>
					<option value="0260">ECIF</option>
					<option value="0150">VSS</option>
					<option value="9999">通用</option>
					<option value="">----</option>
				</select></td>
				<td align=left><select name="Error_Level" id="Error_Level">
					<option value="0">E</option>
					<option value="1">W</option>
					<option value="2">I</option>
					<option value="3">B</option>
					<option value="4">其它</option>
					<option value="5">-----</option>
				</select></td>
				<td align=left><select name="Error_Cat" id="Error_Cat" onChange="selectChainRequest('/XMLEngine/XMLC/SysCatCode/errorcat/',this.value,'2')">
					<option value="">N/A</option>
				</select></td>
				<td align=left>
				<select name="Error_Cat_Sub" id="Error_Cat_Sub">
					<option value="">N/A</option>
				</select>
				</td>

			</tr>
			<tr>
				<td align=left>Error_Msg</td>
				<td align=left></td>
				<td align=left></td>
				<td align=right></td>
			</tr>
			<tr>
				<td align=left colspan=4><textarea cols="60" rows="5"
					id="Error_Msg" name="Error_Msg"></textarea></td>
			</tr>
			<tr>
				<td align=left>Suggestion</td>
				<td align=left></td>
				<td align=left></td>
				<td align=right></td>
			</tr>
			<tr>
				<td align=left colspan=4><textarea cols="60" rows="5"
					id="Suggestion" name="Suggestion"></textarea></td>
			</tr>

			<tr>
				<td align=center colspan=4><INPUT class="ButtonType"
					type="submit" value=" 插入记录 "> &nbsp; <INPUT
					class="ButtonType" type="button" onclick="onSubmitFormUpdate();"
					value=" 修改记录 "> &nbsp; <INPUT class="ButtonType"
					type="button" onclick="onSubmitFormDel();" value=" 删除记录 ">
				&nbsp;</td>
			</tr>
		</table>




		</center>
		</div>


		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>

		</form>


		</td>
		<td width=2% valign=top></td>
	</tr>
</table>








