<%@ page language="java" contentType="text/html; charset=GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<style>
<!--
.rptTitle {
	border-right: none;
	border-bottom: 1pt solid #AAAAAA;
	border-left: none;
	border-top: none;
	padding: 0px;
	font-size: 12pt;
	text-align: left;
	background-color: #FFFFFF;
	COLOR: #000000;
	font-family: 华文楷体;
	padding: 2px;
	margin-right: 3px;
}

.rptText {
	color: black;
	font-size: 10pt;
	font-family: 华文仿宋;
	text-align: left;
	padding: 2px;
	margin-right: 3px;
}


-->
</style>
<script type="text/javascript" src='<portletAPI:encodeURI path="/js/amradar/swfobject.js" />'></script>
<script language="JavaScript" src="<portletAPI:encodeURI path='/js/charts/FusionCharts.js' />"></script>





<table border=0 cellpadding=1 cellspacing=0 width=100% bgcolor=FFFFFF>
<tr>
<td width="95%" bgcolor="#FFFFFF" valign=top> 

<div id="pie"></div>               

<script type="text/javascript">
var so1 = new SWFObject("<portletAPI:encodeURI path="/images/amradar/ampie.swf" />", "ampie", "280", "300", "8", "#FFFFFF");
so1.addVariable("path", "/xml/")                  
so1.addVariable("settings_file", escape("<portletAPI:encodeURI path='/xml/pie_set.xml' />"));
so1.addVariable('data_file', escape("<portletAPI:encodeURI path='/xml/pie_data.xml' />"));               					
so1.addVariable("preloader_color", "#999999");
so1.addParam("wmode", "opaque");
so1.write("pie");
</script>

<li>接入支持基本要求，在多协议支持方面存在不足</li>
<li>不支持n:n的路由，调度能力</li>
<li>接出方面功能单一</li>

 </td>
</tr>
</table>
