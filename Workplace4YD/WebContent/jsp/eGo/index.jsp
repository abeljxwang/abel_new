<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<style type="text/css">
.TitleC {
	font-size: 16pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: 微软雅黑;
}

.messageC {
	font-size: 13pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: 华文楷体;
}

</style>

<script type="text/javascript">

var loadedobjects=""
var rootdomain="http://"+window.location.hostname

function ajaxpage(url, containerid){
var page_request = false
if (window.XMLHttpRequest) // if Mozilla, Safari etc
page_request = new XMLHttpRequest()
else if (window.ActiveXObject){ // if IE
try {
page_request = new ActiveXObject("Msxml2.XMLHTTP")
} 
catch (e){
try{
page_request = new ActiveXObject("Microsoft.XMLHTTP")
}
catch (e){}
}
}
else
return false
page_request.onreadystatechange=function(){
loadpage(page_request, containerid)
}
page_request.open('GET', url, true)
page_request.send(null)
}

function loadpage(page_request, containerid){
if (page_request.readyState == 4 && (page_request.status==200 || window.location.href.indexOf("http")==-1))
document.getElementById(containerid).innerHTML=page_request.responseText
}

function loadobjs(){
if (!document.getElementById)
return
for (i=0; i<arguments.length; i++){
var file=arguments[i]
var fileref=""
if (loadedobjects.indexOf(file)==-1){ //Check to see if this object has not already been added to page before proceeding
if (file.indexOf(".js")!=-1){ //If object is a js file
fileref=document.createElement('script')
fileref.setAttribute("type","text/javascript");
fileref.setAttribute("src", file);
}
else if (file.indexOf(".css")!=-1){ //If object is a css file
fileref=document.createElement("link")
fileref.setAttribute("rel", "stylesheet");
fileref.setAttribute("type", "text/css");
fileref.setAttribute("href", file);
}
}
if (fileref!=""){
document.getElementsByTagName("head").item(0).appendChild(fileref)
loadedobjects+=file+" " //Remember this object as being already added to page
}
}
}


ajaxpage('/NGSCP/myacount.html', 'my_acount');
ajaxpage('/NGSCP/myorder.html', 'my_order');
ajaxpage('/NGSCP/smartSearch.html', 'smart_search');
</script>

<table cellSpacing=0 cellPadding=0 bgcolor="#FFFFFF" height=100%
	width=100%>
	<tr>
		<td valign=middle align=left>
			<table cellSpacing=0 cellPadding=0 bgcolor="#FFFFFF">
				<tr>
					<td height=20 bgcolor="#FFFFFF" colspan=11></td>
				</tr>
				<tr>
					<td height=150 bgcolor="#FAA619" colspan=3>
						<div id="smart_search" class="smart_search">智能搜索</div>
					</td>
					<td height=150 width=5></td>
					<td width=150 bgcolor="#00A29D" rowspan=3>促销</td>
					<td width=5></td>
					<td width=150 height=150 bgcolor="#D63300">
						<div id="my_acount" class="my_acount">我的帐户</div>
					</td>
					<td height=150 width=5></td>
					<td width=150 height=150 bgcolor="#004D4D">
						<div id="my_order" class="my_order">我的订单</div>
					</td>
					<td height=150 width=5></td>
					<td width=150 height=150 bgcolor="#D6DAFD">包裹跟踪</td>
				</tr>
				<tr>
					<td height=5 colspan=11></td>
				</tr>
				<tr>
					<td width=150 height=150 bgcolor="#FFFFFF"></td>
					<td height=150 width=5></td>
					<td width=150 height=150 bgcolor="#FFFFFF"></td>
					<td height=150 width=5></td>
					<td height=150 width=5></td>
					<td width=150 height=150 bgcolor="#B2DE8C">社交</td>
					<td height=150 width=5></td>
					<td width=150 height=150 bgcolor="#FF9966" colspan=3>随便逛逛</td>
				</tr>
			</table>
		<td>
		<td valign=top align=right><img
			src="<portletAPI:encodeURI path="/images/AppStore_1.png" />"
			onClick="onSubmitForm()"></td>
	</tr>
</table>