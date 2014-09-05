<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ page
	import="org.abel.webapp.db.*"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<link rel="StyleSheet" href="/MVC4CPF/theme/errormgr.css"
	type="text/css" />

<link rel="StyleSheet" href="/MVC4CPF/theme/dtree.css"
	type="text/css" />
<script type="text/javascript" src="/MVC4CPF/js/dtree.js"></script>

<SCRIPT language=JavaScript type=text/javascript>

	function putValue(cname,cid)
	{
              document.inputform.catalogname.value=cname;
              // document.inputform.catalogname2.value=cname;              
              document.inputform.catalogid.value=cid;  
              // document.inputform.catalogid2.value=cid;             
                   
	}	
	
	
	function selectOption(valuein)
	{
		var selObj = document.getElementById('up_catalog');
		var i;
  		//var count = 0;
  		for (i=0; i<selObj.options.length; i++) {
    		if (selObj.options[i].value==valuein) {
     		 selObj.selectedIndex = i;
      		//count++;
      		return;
    		}
  		}	
	}
	
	
	function onSubmitFormUpdate()
	{
 	  document.inputform.action ="/MVC4CPF/ET/Console/updateC";
 	  document.inputform.submit();
	  return true;
	}	

	function onSubmitFormDel()
	{
 	  document.inputform.action ="/MVC4CPF/ET/Console/delC";
 	  document.inputform.submit();
	  return true;
	}		
	
	
	function onSubmitForm()
	{
 	  document.inputform.action ="/MVC4CPF/ET/Console/addCat";
 	  document.inputform.submit();
	  return true;
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
			href="javascript: c.closeAll();">close all</a>&nbsp;&nbsp;</div>
		<div class="Cxboxcontent1"><script type="text/javascript">
		<!--

		c = new dTree('c');

		c.add(0,-1,'E_Code Directory');
<%
//Add Catalog Tree

	int setsize = dbresult.size();
	int tmp=0;
	String forseletop="";	
	// String forseletSop="<option value='-1'>null</option>\n";	
	//add zdy
	System.out.println("result.size="+setsize);
	if (setsize > 0) {
	
		while (dbresult.next()) {
			tmp++;
			if(dbresult.getString(2)==null){
			   out.println("c.add("+dbresult.getString(1)+",0,'"+dbresult.getString(3)+"',");
			   out.print("\"javascript:putValue('"+dbresult.getString(3)+"','"+dbresult.getString(1)+"');selectOption('"+dbresult.getString(2)+"');\"");
			   out.print(",'','','/MVC4CPF/images/tree/folder.gif'");
			   out.print(");");
			   //;");
			   forseletop=forseletop+"<option value=\""+dbresult.getString(1).trim()+"\">"+dbresult.getString(3)+"</option>\n";
			}
			else {
			if(dbresult.getString(1).equals("-1")){
				   // out.println("c.add("+dbresult.getString(2)+tmp+","+dbresult.getString(2)+",'"+dbresult.getString(3)+"',");
				   // out.print("\"javascript:selectOption('"+dbresult.getString(2)+"');\"");
				   // out.print(");");
				   // forseletSop=forseletSop+"<option value=\""+dbresult.getString(4)+"\">"+dbresult.getString(3)+"</option>\n";
	
			}
			else{
			   out.println("c.add("+dbresult.getString(1)+","+dbresult.getString(2)+",'"+dbresult.getString(3)+"',");
			   out.print("\"javascript:putValue('"+dbresult.getString(3)+"','"+dbresult.getString(1).trim()+"');selectOption('"+dbresult.getString(2).trim()+"');\"");
			   out.print(",'','','/MVC4CPF/images/tree/folder.gif'");
			   out.print(");\n");
			   forseletop=forseletop+"<option value=\""+dbresult.getString(1).trim()+"\">"+dbresult.getString(3)+"</option>\n";

			}}
			
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
		<center>分类信息管理</center>
		</div>
		<div class="Cxboxcontent1">
		<table width=95%>
			<tr>
				<td bgcolor=EEEEFF>父类名称：</td>
				<td><select name="up_catalog" id="up_catalog">
					<option value="null">root</option>
					<%=forseletop%>
				</select> <input name=ele2 id=ele2 type=hidden></td>
				<td></td>
			</tr>
			<tr><td height=1 bgcolor=336699 colspan=3></td></tr>
			<tr>
				<td valign=top  bgcolor=EEEEFF>类别管理</td>
				<td>子类别名称:<br>
				<input name=catalogname id=catalogname type=text><input
					name=catalogid id=catalogid type=hidden><br>
				<input type="button" value="修改分组" onclick="onSubmitFormUpdate();" />
				<input type="button" value="删除分组" onclick="onSubmitFormDel();" /> <input
					type="button" value="添加分组" onclick="onSubmitForm();" /> <br>

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

