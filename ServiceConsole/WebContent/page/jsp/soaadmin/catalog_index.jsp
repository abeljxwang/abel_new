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
	border: 1px solid #000066;
	border-width: 1 1 1 1px;
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
	text-align: left;
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
	
<link rel="StyleSheet" href="/ServiceConsole/theme/dtree.css"
	type="text/css" />
<script type="text/javascript" src="/ServiceConsole/js/dtree.js"></script>

<SCRIPT language=JavaScript type=text/javascript>

	function putValue(value1,value2)
	{
              document.inputform.catalogname.value=value1;
              document.inputform.catalogid.value=value2;
	}
	function putValue(cname,cid,servname,servid)
	{
              document.inputform.catalogname.value=cname;
              document.inputform.catalogid.value=cid;             
              document.inputform.servicename.value=servname;
              document.inputform.serviceid.value=servid;
              document.inputform.ServiceID.value= servid;  //
              if(servid==""){
              document.inputform.service_s.value= "";
              
              }              
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
	
	function selectSOption(valuein)
	{
		var selObj = document.getElementById('service_s');
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
	
	function onSubmitForm()
	{

 	  document.inputform.action ="/ServiceConsole/ET/SOAAdmin/addC";
 	  document.inputform.submit();
	  return true;
	}	
	function addS2C()
	{
   
 	  document.inputform.action ="/ServiceConsole/ET/SOAAdmin/addS2C";
 	  document.inputform.submit();
	  return true;
	}
		
	//add by zdy 20070807
	
	function delS2C()
	{
 	  document.inputform.action ="/ServiceConsole/ET/SOAAdmin/delS2C";
 	  document.inputform.submit();
	  return true;
	}
	
	function info2C()	
	{
 	  document.inputform.action ="/ServiceConsole/ET/SOAAdmin/serviceCard";
 	  // SOAAdmin/serviceCard?ServiceID=User_Authorization
 	   
 	  
 	  document.inputform.submit();
	  return true;
	}
	
	function onSubmitFormDel()
	{

 	  document.inputform.action ="/ServiceConsole/ET/SOAAdmin/delC";
 	  document.inputform.submit();
	  return true;
	}	
	
	function onSubmitFormUpdate()
	{
 	  document.inputform.action ="/ServiceConsole/ET/SOAAdmin/updateC";
 	  document.inputform.submit();
	  return true;
	}	
    //end add zdy 
	
	</script>
<%
	EIPResultSet dbresult = null;

	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");
	}
%>


<table width=100% bordercolorlight="#ffffff" cellspacing="0" cellpadding="10">
	<tr>
		<td valign=top width=250>
		<div>

				<div class="box_header">
					<center>分类列表:</center>
				</div>
				<div id="result" class="boxcontent_1">
		



		<div class="dtree"
			style="width: 230; height: 160; border-bottom: 0px solid #FFFFFF;">
		<a href="javascript: c.openAll();">open all</a> | <a
			href="javascript: c.closeAll();">close all</a> <script
			type="text/javascript">
		<!--

		c = new dTree('c');

		c.add(0,-1,'Service Directory');
<%
//Add Catalog Tree

	int setsize = dbresult.size();
	int tmp=0;
	String forseletop="";	
	String forseletSop="<option value='-1'>null</option>\n";	
	//add zdy
	System.out.println("result.size="+setsize);
	if (setsize > 0) {
	
		while (dbresult.next()) {
			tmp++;
			if(dbresult.getString(2)==null){
			   out.println("c.add("+dbresult.getString(1)+",0,'"+dbresult.getString(3)+"',");
			   out.print("\"javascript:selectSOption('-1');putValue('"+dbresult.getString(3)+"','"+dbresult.getString(1)+"','','');selectOption('"+dbresult.getString(2)+"');\"");
			   out.print(");");
			   //;");
			   forseletop=forseletop+"<option value=\""+dbresult.getString(1)+"\">"+dbresult.getString(3)+"</option>\n";
			   //add zdy
				//System.out.println(tmp+"  if 1 dbresult.getString(1="+dbresult.getString(1)+" dbresult.getString(2)="+dbresult.getString(2)+ "   dbresult.getString(3)="+dbresult.getString(3));
			}
			else {
			if(dbresult.getString(1).equals("-1")){
				   out.println("c.add("+dbresult.getString(2)+tmp+","+dbresult.getString(2)+",'"+dbresult.getString(3)+"',");
				   out.print("\"javascript:selectSOption('"+dbresult.getString(4)+"');putValue('','','"+dbresult.getString(3)+"','"+dbresult.getString(4)+"');selectOption('"+dbresult.getString(2)+"');\"");
				   out.print(");");
				   forseletSop=forseletSop+"<option value=\""+dbresult.getString(4)+"\">"+dbresult.getString(3)+"</option>\n";
				   //add zdy
					//System.out.println(tmp+"  if 2 dbresult.getString(1="+dbresult.getString(1)+" dbresult.getString(2)="+dbresult.getString(2)+ "   dbresult.getString(3)="+dbresult.getString(3)+" dbresult.getString(4)="+dbresult.getString(4));
	
			}
			else{
			   out.println("c.add("+dbresult.getString(1)+","+dbresult.getString(2)+",'"+dbresult.getString(3)+"',");
			   out.print("\"javascript:selectSOption('-1');putValue('"+dbresult.getString(3)+"','"+dbresult.getString(1)+"','','');selectOption('"+dbresult.getString(2)+"');\"");
			   out.print(",'','','/ServiceConsole/images/tree/folder.gif'");
			   out.print(");\n");
			   forseletop=forseletop+"<option value=\""+dbresult.getString(1)+"\">"+dbresult.getString(3)+"</option>\n";
			   //add zdy
				//System.out.println(tmp+"  if 3 dbresult.getString(1="+dbresult.getString(1)+" dbresult.getString(2)="+dbresult.getString(2)+ "   dbresult.getString(3)="+dbresult.getString(3));

			}}
			
		}
	}
		
%>
		document.write(c);
		//-->
	</script></div>

		</div>
		</div>


		</td>
		<td valign=top width=500>
		<div>

				<div class="box_header">
					<center>分类列表:</center>
				</div>
				<div id="result" class="boxcontent_1">
				
		
		
		<form name=inputform method=post>
		<div class="dtree"
			style="width: 98% height:160; border-bottom: 0px solid #FFFFFF;">
		<table width=95%>
			<tr bgcolor=DDDDFF>
				<td>父类名称：</td>
				<td><select name="up_catalog" id="up_catalog">
					<option value="null">root</option>
					<%=forseletop%>
				</select> <input name=ele2 id=ele2 type=hidden size=20></td>
				<td></td>
			</tr>
			<tr bgcolor=DDFFDD>
				<td valign=top>类别管理</td>
				<td>子类别名称:<br>
				<input name=catalogname id=catalogname type=text size=20><input
					name=catalogid id=catalogid type=hidden size=20>
					<select name="biz_layer">
							<option value="0">战略</option>
							<option value="1">业务管理</option>
							<option value="2">业务执行</option>
						</select>
					<select name="biz_chain_id">
							<option value="1">C</option>
							<option value="0">B</option>
						</select>
					<br>
					<textarea cols="50" rows="3"
					id="catalogesc" name="catalogesc"></textarea>
					
					<br>
				<input type="button" value="修改分组" onclick="onSubmitFormUpdate();" />
				<input type="button" value="删除分组" onclick="onSubmitFormDel();" /> <input
					type="button" value="添加分组" onclick="onSubmitForm();" /> <br>
				<p>
				</td>
				<td></td>
			</tr>
			<tr>
				<td valign=top>服务分类</td>
				<td>服务名称: <input name=servicename id=servicename type=hidden
					size=20><input name=serviceid id=serviceid type=hidden
					size=20><br>
				<select name="service_s" id="service_s">
					<option value="">---请选择---</option>
					<%
						Properties elements = CoreDataCacheManager.getAllServices();
						String opstr = "";

						String key = null, value = null;
						Enumeration keys = elements.keys();

						while (keys.hasMoreElements()) {
							key = keys.nextElement() + "";
							value = elements.getProperty(key);
							opstr = opstr + "\r<option value=\"" + key + "\">" + key + "/"
									+ value + "</option>";
						}
					%>
					<%=opstr%>>
				</select></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" value="添加服务" onclick="addS2C();" /> <input
					type="button" value="删除服务" onclick="delS2C();" /> <input
					name=ServiceID id=ServiceID type=hidden size=20> <input
					type="button" value="服务详细信息" onclick="info2C();" /></td>
			</tr>
		</table>


		<br>
		</div>
		</form>
		<br>
		</div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>

		</td>

	</tr>
</table>


