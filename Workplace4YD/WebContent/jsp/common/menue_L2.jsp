<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<script type="text/javascript" src="<portletAPI:encodeURI path="/js/menu-for-applications.js" />"></script>
<script type="text/javascript" src="<portletAPI:encodeURI path="/js/shortcut.js" />"></script>

<script type="text/javascript">

shortcut.add("F7",function () {
	document.location.href ="<portletAPI:encodeURI path="/ET/View/" />";
},{
	'type':'keydown',
	'propagate':true,
	'target':document
});

shortcut.add("F8",function () {
	document.location.href ="<portletAPI:encodeURI path="/ET/View/queryM" />";
},{
	'type':'keydown',
	'propagate':true,
	'target':document
});

shortcut.add("F9",function () {
	document.location.href ="<portletAPI:encodeURI path="/ET/Indicator/queryI" />";
},{
	'type':'keydown',
	'propagate':true,
	'target':document
});

shortcut.add("F10",function () {
	document.location.href ="<portletAPI:encodeURI path="/ET/Catalog/queryC" />";
},{
	'type':'keydown',
	'propagate':true,
	'target':document
});
shortcut.add("F12",function () {
	document.location.href ="<portletAPI:encodeURI path="/ET/System/queryS" />";
},{
	'type':'keydown',
	'propagate':true,
	'target':document
});

</script>
<table cellpadding="0" cellspacing="0" border="0" width="99.5%" 	height="100%">
	<tr>
		<td>
		<div id="POCM">  </div>   
		</td>
	</tr>
</table>
<ul id="menuModel" style="display: none">
	<li id="100"><a href="<portletAPI:encodeURI path="/ET/View/" />">仪表盘(F7)</a>
	<ul width="150">
		<li id="1023"><a href="<portletAPI:encodeURI path="/ET/View/" />">分系统指标视图</a></li>
		<li id="1024"><a href="<portletAPI:encodeURI path="/ET/View/b" />">系统-对比分析</a></li>
		<li id="1025"><a href="<portletAPI:encodeURI path="/ET/View/" />">系统改进建议</a></li>	
		<li id="1026"><a href="<portletAPI:encodeURI path="/ET/View/c" />">Flow</a></li>			
	</ul>
	</li>
	<li id="50001" itemType="separator"></li>
	<li id="200"><a>系统评估(F8)</a>
	<ul width="150">
		<li id="2000"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=10">渠道/用户体验类</a></li>
		<li id="2001"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=20">整合/接口类</a></li>
		<li id="2002"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=30">基础交易类</a></li>	
		<li id="2003"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=40">业务创新/组合类</a></li>	
		<li id="2004"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=50">管理支撑类</a></li>			
	</ul>
	</li>	
	<li id="50002" itemType="separator"></li>
	<li id="300"><a>指标维护</a>
	<ul width="150">
		<li id="3023"><a href="<portletAPI:encodeURI path="/ET/Indicator/queryI" />">指标定义(F9)</a></li>
		<li id="30005" itemType="separator"></li>
		<li id="3024"><a href="<portletAPI:encodeURI path="/ET/Indicator/queryI2C" />">指标体系设置</a></li>
		<li id="3025"><a href="<portletAPI:encodeURI path="/ET/Indicator/queryI4S" />">系统指标概览</a></li>

	</ul>
	</li>
	<li id="50003" itemType="separator"></li>
	<li id="400"><a href="<portletAPI:encodeURI path="/ET/Catalog/queryC" />">分类管理</a>
	<ul width="150">
		<li id="4023"><a href="<portletAPI:encodeURI path="/ET/Catalog/queryC" />">指标分类(F10)</a></li>
		<li id="4024"><a href="<portletAPI:encodeURI path="/ET/Catalog/queryS" />">系统分类</a></li>
	</ul>
	</li>
	
	<li id="50004" itemType="separator"></li>	
	<li id="501"><a href="<portletAPI:encodeURI path="/ET/View/InfoQPD" />">基础数据</a>
	<ul width="150">
		<li id="5023"><a href="<portletAPI:encodeURI path="/ET/System/queryS" />">当前系统(F12)</a></li>
		<li id="5024"><a href="<portletAPI:encodeURI path="/ET/System/querySC" />">系统分类</a></li>
		<li id="50005" itemType="separator"></li>
		<li id="5027"><a>关键参数</a></li>	
	</ul>
	</li>
	<li id="50006" itemType="separator"></li>
	<li id="900"><a>Help</a>
	<ul width="150">
		<li id="9023"><a href="<portletAPI:encodeURI path="/ET/View/noFuncInfo" />">常见问题</a></li>
		<li id="9024"><a href="<portletAPI:encodeURI path="/ET/View/noFuncInfo" />">命令/功能检索</a></li>
		<li id="9025"><a href="<portletAPI:encodeURI path="/ET/View/noFuncInfo" />">一般教程</a></li>
	</ul>
	</li>
	<li id="50009" itemType="separator"></li>

</ul>


<script type="text/javascript">
var menuModel = new DHTMLSuite.menuModel();
menuModel.addItemsFromMarkup('menuModel');
menuModel.init();

var menuBar = new DHTMLSuite.menuBar();
menuBar.addMenuItems(menuModel);
menuBar.setTarget('POCM');
menuBar.init(); 

</script>