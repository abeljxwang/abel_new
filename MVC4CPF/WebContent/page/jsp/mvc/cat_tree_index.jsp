<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ page
	import="org.abel.webapp.db.*"%>


<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<link rel="StyleSheet" href="/MVC4CPF/theme/dtree.css"
	type="text/css" />
<link rel="StyleSheet" href="/MVC4CPF/theme/errormgr.css"
	type="text/css" />
<script type="text/javascript" src="/MVC4CPF/js/dtree.js"></script>

<SCRIPT language=JavaScript type=text/javascript>
   var http_request = false;
   function makeRequestP(url) {
      var parameters1="?Sys_ID="+document.getElementById("SYS_ID").value;
      parameters1=parameters1+"&Error_Code="+document.getElementById("Error_Code").value;
		// testP.innerText=parameters1;
	makeRequest(url, parameters1);
   }
   
   
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
            } catch (e) {}
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
                        if (sibl3.data!=null) arr[cnt] = sibl3.data;   
                        else arr[cnt] = "N/A";   
                        // tmpstr=tmpstr+"<td>"+sibl3.data+" </td> ";
                        
                     }
                     else arr[cnt] = "N/A";  
                     cnt++; 
                  }
                  addrow(arr);
                  // tmpstr=tmpstr+"</tr>";
               }
               
               document.getElementById("Error_Code").value=arr[0];
            }   
			
        } else {
            alert("There was a problem retrieving the XML data:\n" + http_request.statusText);
        }
    }
}

	function addrow(arr) {
   		 var tbl = document.getElementById("resultconyent");
   		   var lastRow = tbl.rows.length;
   		   var row = tbl.insertRow(lastRow);
   			row.style.backgroundColor = "FFFFFF";
   		 for(var r=0;r<arr.length-1;r++){
 	           var cell = row.insertCell(r);
               if(r==1||r==2){
               if(arr[r].length >= 20) cell.innerHTML = arr[r]+"...";
               else cell.innerHTML = arr[r];
               }
              
               else cell.innerHTML = arr[r];
 	           }
 	           
	} 	
	
	function removeRow() {
	var tb2 = document.getElementById("resultconyent");
	var rowlength=tb2.rows.length;
	 for (var xi = 1; xi < rowlength; xi++) {
	       var ind=rowlength-xi;
   			tb2.deleteRow(ind);
       }
	}
	
function  getLen(str) {
  var totallength=0;
 
  for (var i=0;i<str.length;i++)
  {
   var intCode=str.charCodeAt(i);
 
   if (intCode>=0&&intCode<=128) {
    totallength=totallength+1; //非中文单个字符长度加 1
   }
   else {
    totallength=totallength+2; //中文字符长度则加 2
   }
  } //end for
 
  return totallength;
 
}
	
	function onSubmitForm()
	{
 	  document.inputform.action ="/MVC4CPF/ET/Console/addCat";
 	  document.inputform.submit();
	  return true;
	}
	function blankP()
	{
 	}
 	
 	
 	function putValue1(vl1){
 	document.getElementById("SYS_ID").value=vl1;
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

		c.add(0,-01,'E_Code Directory');
<%
//Add Catalog Tree

	int setsize = dbresult.size();
	int tmp=0;
	//add zdy
	//System.out.println("result.size="+setsize);
	if (setsize > 0) {
	
		while (dbresult.next()) {
			tmp++;
		
			if(dbresult.getString(2)==null){
				out.println("c.add("+dbresult.getString(1)+",0,'"+dbresult.getString(3)+"',");
				out.print("\"javascript:makeRequest('/XMLEngine/XMLC/User_Mgr/list/', '?Sys_ID="+dbresult.getString(1)+"');putValue1('"+dbresult.getString(1)+"')\"");
			   out.print(",'','','/MVC4CPF/images/tree/folder.gif'");
			   out.print(");");
			   //;");

			}
				
			else {
			if(dbresult.getString(1).equals("-1")){
			}
			else{
			   out.println("c.add("+dbresult.getString(1)+","+dbresult.getString(2)+",'"+dbresult.getString(3)+"',");
			   out.print("\"javascript:makeRequest('/XMLEngine/XMLC/User_Mgr/listbyCat/', '?CAT_ID="+dbresult.getString(1)+"');\"");
			   out.print(",'','','/MVC4CPF/images/tree/folder.gif'");
			   out.print(");\n");
			}}
			
		}
	}
	
	
%>
		document.write(c);
		//-->
	</script></div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>
			
					<div id="testP">
		<center></center>
		</div>
			
		</td>
		<td valign=top>

		<div style="width: 450;"><b class="xtop"><b class="xtb1"></b><b
			class="xtb2"></b><b class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<center>分系统错误码/响应码检索</center>
		</div>
		<div class="Cxboxcontent1" id="result">
		<center>
		<table width=99% bgcolor=ddeeff cellspacing=0 cellpadding=0>
			<tr><td>
				<a href="javascript:makeRequestP('/XMLEngine/XMLC/User_Mgr/list/');">next Page</a>
				<input type=hidden name="SYS_ID" id="SYS_ID" value="0700"><input type=hidden name="Error_Code" id="Error_Code">
			</td></tr>
			<tr>
				<td bgcolor=88bbff>
				<table width=100% border="0" cellspacing=1 cellpadding=1
					id="resultconyent">
					<tr bgcolor=ddddff>
						<td width=10%>CODE</td>
						<td width=40%>说明</td>
						<td width=36%>操作建议</td>
						<td width=10%>SYS</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</center>
			<center><br>
		<br>
		请选择左侧系统，所选择系统的相关错误码将在此显示。<br>
		</center>

		</div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>





		</td>

	</tr>
</table>
