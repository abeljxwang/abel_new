<%@ page language="java" contentType="text/html; charset=GB2312"%>

<%@ page import="java.util.*,org.abel.webapp.db.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<link rel="StyleSheet" href="/ServiceConsole/theme/sermgr.css" type="text/css" />

<style>

  #formcont {
    background-color: #EEEEEE;
    width: 80%;
    height:50px;
    float: left;
    border-right: 1px dashed #DDDDDD;
    padding: 5px;
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







<table border="1" width="100%" bordercolorlight="#ffffff"
	cellspacing="0" cellpadding="0" bordercolor="#ffffff">
	<tr>
		<td width="5%" height=16></td>
		<td width="90%">数据库表名称： <%=sqltable%></td>
		<td width="5%"></td>
	</tr>
	<tr>
		<td width="5%" height="16"></td>
		<td width="90%" height="16" align=center>
		
				<div id="xsnazzy"><b class="xtop"><b class="xtb1"></b><b
			class="xtb2"></b><b class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<center>元数据信息:</center>
		</div>
		<div id="result" class="Cxboxcontent1">
		<div style="height: 30px; overflow: hidden; border: 1px solid #FFFFFF;">
		<form action="<portletAPI:createURI><portletAPI:URIAction name="queryM"/></portletAPI:createURI>">
		
				<%
					int sizea = cmd.size();
					for (int i = 0; i < sizea; i++) {
						DBField dbf = cmd.get(i);
						if (dbf.getview()) {
							out.println("" + dbf.getname() + "：");
							out.println("<input name=" + dbf.getfield()	+ ">");
						}
					}
				%>
				<INPUT class="ButtonType" type="submit" value=" 查询 "></form>
		</div>
		
		<table border="0" width="95%" cellspacing="1" cellpadding="0">
			<tr>
				<td bgcolor=000055>
				<table border="0" width="100%" cellspacing="1" cellpadding="0"
			bgcolor=ffffff>
			<tr><td align=right>
				<%
				String mname="";
				int page_c=1;
				int page_s=10;
				try{
				if(request.getParameter("page_s")!=null) page_s=Integer.parseInt(request.getParameter("page_s"));
				if(request.getParameter("page_c")!=null){
				}
				}catch(Exception ee){
					
				}
				
				if(request.getParameter("mname")!=null) mname=request.getParameter("mname");
				int size = org.abel.webapp.common.DataCacheManager.CountAllRows("metadata","mname",mname);
					out.println("共" + size + "条记录；");
					int page_len = size / page_s;
					if (size % page_s != 0)
						page_len = page_len + 1;
					out.println("分" + page_len + "页显示。");
					int current_pg = 1;
					for (int i = 1; i <= page_len && i <= current_pg + 10; i++) {
						out.print("<a href='queryM?mname="+mname+"&page_c="+i);
						if(page_s!=10) out.print("&page_s="+page_s);
						out.print("'>");
						out.print("[" + i + "]&nbsp;");
						out.print("</a>");

					}
				%>
				</td></tr>
				</table>

				<table border="0" width="100%" cellspacing="1" cellpadding="1">

<tr bgcolor=000055 height=22>
<td bgcolor=bbbbff align=right width=3%>SN</td>
<td bgcolor=bbbbff align=right width=10%>名称</td>
<td bgcolor=bbbbff align=right width=15%>中文名</td>
<td bgcolor=bbbbff align=right width=12%>类型</td>
<td bgcolor=bbbbff align=right width=5%>长度</td>
<td bgcolor=bbbbff align=right width=55%>描述</td>
</tr>

					<%
						// out.print("<tr bgcolor=000055 height=22>");
						// for (int i = 0; i < cmd.size(); i++) {
						//	out.print("<td bgcolor=bbbbff align=right><font color=000000>"
						//	+ cmd.get(i).getname() + "</font></td>");
						// }
						// out.print("</tr>");
						
						

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
				</td>
			</tr>
		</table>
		</div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>
		
		</td>
		<td width="5%" height="16"></td>
	</tr>
	<tr>
		<td width="5%" height="20"></td>
		<td width="90%" height="20"  border=1 bordercolor=000000>
		<form method="POST" action="<portletAPI:createURI><portletAPI:URIAction name="insertMD"/></portletAPI:createURI>">
				<table border=0 width=90% cellspacing="0" cellpadding="0" bgcolor=ffffff>
					<tr>
						<td></td>
						<td align=left>---</td>
						<td align=left>mname</td>
						<td align=left>mcname</td>
						<td align=left>mtype</td>
						<td align=left>mlength</td>
						<td align=right><INPUT class="ButtonType" type="submit" value=" 插入记录 "> &nbsp;</td>
					</tr>

					<tr>
						<td></td>
						<td align=left></td>
						<td align=left><input name="mname" size=15></td>
						<td align=left><input name="mcname" size=15></td>
						<td align=left><select name="mtype">
							<option value="S">S(tring)</option>
							<option value="I">I(nt)</option>
							<option value="F">F(loat)</option>
							<option value="DB">D(ouble)</option>
							<option value="D">D(ate)</option>
							<option value="T">T(ime)</option>
							<option value="DT">D(ate)T(ime)</option>
						</select></td>
						<td align=left><input name="mlength" size=15 value=20></td>

					</tr>
					<tr>
						<td colspan=8 align="right">
						MDESC: <input name="mdesc" size=75>
						</td>
					</tr>
				</table>
				</form>
		</td>
		<td width="5%" height="20"></td>
	</tr>
	<tr>
		<td width="5%" height="20"></td>
		<td width="90%" height="20"></td>
		<td width="5%" height="20"></td>
	</tr>
</table>

