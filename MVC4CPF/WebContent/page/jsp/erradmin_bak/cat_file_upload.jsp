
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ page import="org.abel.webapp.db.*"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<link rel="StyleSheet" href="/MVC4CPF/theme/errormgr.css" type="text/css" />

<SCRIPT language=JavaScript type=text/javascript>
   var http_request = false;
   function makeRequest(url) {
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
      http_request.open('GET', url, true);
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
                        cnt++;
                     }
                  }
                  addrow(arr);
                  // tmpstr=tmpstr+"</tr>";
               }
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
   			row.style.backgroundColor = "EFEFFF";
   			// row.style.class="xl28";
   		 for(var r=0;r<arr.length;r++){
 	           var cell = row.insertCell(r);
               cell.innerHTML = arr[r];
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
	
	
	function onSubmitForm()
	{
 	  document.inputform.action ="/MVC4CPF/ET/Console/addCat";
 	  document.inputform.submit();
	  return true;
	}
	function blankP()
	{
 	}		

</script>

<!--

.xl28
	{
	font-size:9.0pt;
	font-family:华文细黑;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	text-align:right;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#CCCCFF;
	mso-pattern:auto none;}
.xl29
	{
	font-size:10.0pt;
	font-family:华文楷体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#FEFEFF;
	mso-pattern:auto none;}

-->


<%
	EIPResultSet dbresult = null;
	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");
	}
%>

<%
	//Add Catalog Tree

	int setsize = dbresult.size();
	int tmp = 0;
	String forseletop = "";
	String forseletSop = "<option value='-1'>null</option>\n";
	//add zdy
	//System.out.println("result.size="+setsize);
	if (setsize > 0) {

		while (dbresult.next()) {
			tmp++;
			if (dbresult.getString(2) == null) {
				forseletop = forseletop + "<option value=\""
						+ dbresult.getString(1) + "\">"
						+ dbresult.getString(3) + "</option>\n";
			} else {
				if (dbresult.getString(1).equals("-1")) {
					forseletSop = forseletSop + "<option value=\""
							+ dbresult.getString(4) + "\">"
							+ dbresult.getString(3) + "</option>\n";
				} else {
					forseletop = forseletop + "<option value=\""
							+ dbresult.getString(1) + "\">"
							+ dbresult.getString(3) + "</option>\n";
				}
			}

		}
	}
%>
<center>
<table width=97%>
	<tr>
		<td valign=top width=40%>
		<div class="dtree" class="xboxcontent"><b class="xtop"><b
			class="xtb1"></b><b class="xtb2"></b><b class="xtb3"></b><b
			class="xtb4"></b></b>
		<div class="xboxcontent" align=center>分类信息批量导入</div>
		<div class="Cxboxcontent1">
		<center>请参照以下样例，以EXCEL方式组织内容，批量上传至系统，可以实现错误码信息的批量导入<br>
		<iframe frameborder=0 width=300 height=80 marginheight=1
			marginwidth=1 scrolling=no
			src="/MVC4CPF/page/jsp/erradmin/file_upload_section.jsp"></iframe>
		</center>
		
		</div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>



		<div><b class="xtop"><b class="xtb1"></b><b class="xtb2"></b><b
			class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<center>错误码/响应码分类信息样例及模板：</center>
		</div>
		<div class="Cxboxcontent1">
		<center>

		<table border=0 cellpadding=0 cellspacing=0 width=300>
			<tr>
				<td align=center colspan=2><IMG alt="" src="/MVC4CPF/images/exceltemplate.gif" border=0> <br>
				
				</td>
			</tr>
			<tr><td align=center>
			<a href="/MVC4CPF/template/Tmp.xls">模板下载：<br>				
			<IMG height=40 alt="" src="/MVC4CPF/images/files/xls.gif" width=40 border=0>
			</a>
			
			</td><td  align=center valign=middle><input type="image" src="/MVC4CPF/images/refresh.gif" onclick="makeRequest('/XMLEngine/XMLC/FileMgr/list/');"></td></tr>
		</table>
		</center>

		</div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>
		</td>

		<td valign=top width=57%>

		<div><b class="xtop"><b class="xtb1"></b><b class="xtb2"></b><b
			class="xtb3"></b><b class="xtb4"></b></b>
		<div class="xboxcontent">
		<center>已上传文件信息/列表：</center>
		</div>
		<div class="Cxboxcontent1">
		<center>

		<table border=0 cellpadding=0 cellspacing=0 width=400>
			<tr>
				<td align=center>
				<table width=100% border="0" cellspacing=1 cellpadding=1
					id="resultconyent" bgcolor="6699CC">
					<tr bgcolor=ddddff>
						<td width=5%></td>
						<td width=60%>文件</td>
						<td width=15%>SYS</td>
						<td width=20%>Date</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		
		===============
		
		</center>

		</div>
		<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
			class="xb2"></b><b class="xb1"></b></b></div>

		</td>

	</tr>
</table>

</center>

