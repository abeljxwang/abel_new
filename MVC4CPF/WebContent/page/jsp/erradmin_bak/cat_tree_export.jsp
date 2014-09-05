<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ page import="org.abel.webapp.db.*"%>


<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />


<link rel="StyleSheet" href="/MVC4CPF/theme/errormgr.css"
	type="text/css" />

<link rel="StyleSheet" href="/MVC4CPF/theme/dtree.css"
	type="text/css" />
<script type="text/javascript" src="/MVC4CPF/js/dtree.js"></script>

<SCRIPT language=JavaScript type=text/javascript>

	function putValue(cid)
	{
              document.inputform.Sys_ID.value=cid;                               
	}	
	
	function onSubmitForm()
	{
 	  document.inputform.action ="/XMLEngine/FileDownLoad/FileMgr/down/";
 	  document.inputform.submit();
	  return true;
	}	
	function blankP()
	{
 	}	
	</script>
<%
	EIPResultSet dbresult = null;
	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");
	}
%>


<table>
	<tr>
		<td valign=top>
		<div class="dtree" style="width: 250; height: 160;"
			class="xboxcontent"><b class="xtop"><b class="xtb1"></b><b
			class="xtb2"></b><b class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent" align=right>分类信息：----------<a
			href="javascript: c.openAll();">open all</a> | <a
			href="javascript: c.closeAll();">close all</a></div>
		<div class="Cxboxcontent1"><script type="text/javascript">
		<!--

		c = new dTree('c');

		c.add(0,-1,'E_Code Directory');
<%
//Add Catalog Tree

	int setsize = dbresult.size();
	int tmp=0;
	String forseletop="";	
	//String forseletSop="<option value='-1'>null</option>\n";	
	//add zdy
	//System.out.println("result.size="+setsize);
	if (setsize > 0) {
	
		while (dbresult.next()) {
			tmp++;
			if(dbresult.getString(2)==null){
			   out.println("c.add("+dbresult.getString(1)+",0,'"+dbresult.getString(3)+"',");
			   out.print("\"javascript:putValue('"+dbresult.getString(1)+"');\"");
			   out.print(",'','','/MVC4CPF/images/tree/folder.gif'");
			   out.print(");");
			   //;");
			   
			   forseletop=forseletop+"<option value=\""+dbresult.getString(1)+"\">"+dbresult.getString(3)+"</option>\n";
			   //add zdy
				//System.out.println(tmp+"  if 1 dbresult.getString(1="+dbresult.getString(1)+" dbresult.getString(2)="+dbresult.getString(2)+ "   dbresult.getString(3)="+dbresult.getString(3));
			}
			else {
				
			}
			
		}
	}
		
    %>
		document.write(c);
		//-->
	</script></div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>
		</td>
		<td valign=top>
		<form name=inputform method=post>
		<div style="width: 450;"><b class="xtop"><b class="xtb1"></b><b
			class="xtb2"></b><b class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<center>分类信息导出：</center>
		</div>
		<div class="Cxboxcontent1">
		<table width=97%>
			<tr bgcolor=EFEFFF>
				<td width=80>系统名称：</td>
				<td><select name="Sys_ID" id="Sys_ID">
					<%=forseletop%>				
				</select> </td>
				<td></td>
			</tr>
		<tr>
				<td colspan=3 bgcolor=336699 height=1></td>

			</tr>
			<tr bgcolor=FFFFFF>
				<td></td>
				<td>
				<table>
					<tr>
						<td><IMG height=40 alt=""
							src="/MVC4CPF/images/files/xls.gif" width=40></td>
							<td><a href="javascript:onSubmitForm()">导出Excel文件</a></td>
					</tr>
					<tr>
					<td><IMG height=40 alt=""
							src="/MVC4CPF/images/files/xml.gif" width=40></td>
						<td>导出XML文件</td>
					</tr>
					<tr>
					<td><IMG height=40 alt=""
							src="/MVC4CPF/images/files/pdf.gif" width=40></td>
						<td>导出Acrobat文件</td>
					</tr>
					<tr>
					<td><IMG height=40 alt=""
							src="/MVC4CPF/images/files/txt.gif" width=40></td>
						<td>导出纯文本文件</td>
					</tr>

				</table>
				</td>
				<td></td>
			</tr>

		</table>

		</div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>
		</form>
		</td>

	</tr>
</table>

