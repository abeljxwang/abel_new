<%@ page 
language="java"
contentType="text/html; charset=GB2312"
%>

<%@ page import="java.util.*,org.abel.webapp.db.*" %>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<%
	//String webappbase="/WebA2Z/EipTest";
	int colomsize=5;
	String sqltable=null;
	String baseurl=null;
	EIPResultSet dbresult=null;
	CmdMetaData cmd=null;
	

	if(request.getAttribute("dbresult")!=null) {
		dbresult=(EIPResultSet)request.getAttribute("dbresult");
		//fieldname=(java.util.Vector)rp.getRResult("fieldname");
		//if(rp.getRResult("fieldlength")!=null) fiellength=(java.util.Vector)rp.getRResult("fieldlength");
		if(request.getAttribute("metadata")!=null) cmd=(CmdMetaData)request.getAttribute("metadata");
		if(request.getAttribute("sqltable")!=null) sqltable=(String)request.getAttribute("sqltable");
		if(request.getAttribute("baseurl")!=null) baseurl=(String)request.getAttribute("baseurl");
	}
	
	
	
%>
		<form action="<portletAPI:createURI>
<portletAPI:URIAction name="queryMD"/>
</portletAPI:createURI>">

<table border="1" width="665" bordercolorlight="#808080" cellspacing="0"
	cellpadding="0" bordercolordark="#ffffff">
	<tr>
		<td bgcolor="#808080" height="11"><font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;标准数据库操作--&gt;查询细节</font></td>
		<td bgcolor="#808080" height="11" align="right">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2">

		<table border="1" width="100%" bordercolorlight="#ffffff"
			cellspacing="0" cellpadding="0" bordercolor="#ffffff">
			<tr>
				<td width="5%" height=16></td>
				<td width="90%">数据库表名称： <%=sqltable%></td>
				<td width="5%"></td>
			</tr>
			<tr>
				<td></td>
				<td border=1 bordercolor=000000>
				<table border=0 width=90%>
					<tr>
						<%
					int sizea=cmd.size();
					for(int i=0; i<sizea;i++){
						DBField dbf=cmd.get(i);
						if(dbf.getview()){
						out.println("<td></td>");
						out.println("<td align=right>"+dbf.getname()+"：</td>");
						out.println("<td align=left><input name="+dbf.getfield()+" value=></td>");
						//out.println("<td></td>");
						}
					}
					
					%>
					</tr>
					<tr>
						<td></td>
						<td colspan=5 align="right"><INPUT class="ButtonType"
							type="submit" value=" 查询 " name="SaveAnswer"> &nbsp;</td>
						<td></td>
					</tr>
				</table>
				</td>
				<td></td>
			</tr>

			<tr>
				<td width="5%" height="16"></td>
				<td width="90%" height="16" bgcolor=000088 align=center>
				<table border="0" width="100%" cellspacing="1" cellpadding="0"
					bgcolor=000055>
					<tr>
						<td bgcolor=000055>
						<table border="0" width="100%" cellspacing="1" cellpadding="1">


							<%
							out.print("<tr bgcolor=000055 height=22>");		
									out.print("<td bgcolor=eeeeee > SN </td>");
							for(int i=0;i<cmd.size();i++){
									out.print("<td bgcolor=bbbbff align=right><font color=000000>"+cmd.get(i).getname()+"</font></td>");										
									}
									out.print("</tr>");	
									
							
							  	int setsize=dbresult.size();
								if(setsize>0){
								int ii=0;
								while (dbresult.next()) {
								ii++;
								out.print("<tr bgcolor=000055 height=22>");	
								out.print("<td bgcolor=eeeeee >-"+ii+"</td>");	
								int moii=ii%2;
								for(int i=0;i<cmd.size();i++){
								
									out.print("<td ");
									if(moii==0) out.print(" bgcolor=bbbbff");
									else  out.print(" bgcolor=eeeeff");
									out.print("><font color=000000>");
									if(dbresult.getString(i+1)!=null) out.print(dbresult.getString(i+1));
									else out.print("N/A");
									out.print("</font></td>");	
									}
									out.print("</tr>");	
									}
									}
									else{
									out.print("<tr bgcolor=000055 height=22>");		
									out.print("<td bgcolor=eeeeee >-</td>");							
									out.print("<td bgcolor=bbbbff align=center><font color=000000> 没有记录</font></td>");
									//System.out.println(cmd.get(i).getname()+"-----");
									out.print("<td bgcolor=eeeeee></td>");	
									out.print("</tr>");	
									
									}
									
								%>






						</table>
						</td>
					</tr>
				</table>
				</td>
				<td width="5%" height="16"></td>
			</tr>
			<tr>
				<td width="5%" height="20"></td>
				<td width="90%" height="20">
				
				
				</td>
				<td width="5%" height="20"></td>
			</tr>
			<tr>
				<td width="5%" height="20"></td>
				<td width="90%" height="20"></td>
				<td width="5%" height="20"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
