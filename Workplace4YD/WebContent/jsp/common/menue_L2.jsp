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
	<li id="100"><a href="<portletAPI:encodeURI path="/ET/View/" />">�Ǳ���(F7)</a>
	<ul width="150">
		<li id="1023"><a href="<portletAPI:encodeURI path="/ET/View/" />">��ϵͳָ����ͼ</a></li>
		<li id="1024"><a href="<portletAPI:encodeURI path="/ET/View/b" />">ϵͳ-�Աȷ���</a></li>
		<li id="1025"><a href="<portletAPI:encodeURI path="/ET/View/" />">ϵͳ�Ľ�����</a></li>	
		<li id="1026"><a href="<portletAPI:encodeURI path="/ET/View/c" />">Flow</a></li>			
	</ul>
	</li>
	<li id="50001" itemType="separator"></li>
	<li id="200"><a>ϵͳ����(F8)</a>
	<ul width="150">
		<li id="2000"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=10">����/�û�������</a></li>
		<li id="2001"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=20">����/�ӿ���</a></li>
		<li id="2002"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=30">����������</a></li>	
		<li id="2003"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=40">ҵ����/�����</a></li>	
		<li id="2004"><a href="<portletAPI:encodeURI path="/ET/Assesment/queryI4S" />?supcatid=50">����֧����</a></li>			
	</ul>
	</li>	
	<li id="50002" itemType="separator"></li>
	<li id="300"><a>ָ��ά��</a>
	<ul width="150">
		<li id="3023"><a href="<portletAPI:encodeURI path="/ET/Indicator/queryI" />">ָ�궨��(F9)</a></li>
		<li id="30005" itemType="separator"></li>
		<li id="3024"><a href="<portletAPI:encodeURI path="/ET/Indicator/queryI2C" />">ָ����ϵ����</a></li>
		<li id="3025"><a href="<portletAPI:encodeURI path="/ET/Indicator/queryI4S" />">ϵͳָ�����</a></li>

	</ul>
	</li>
	<li id="50003" itemType="separator"></li>
	<li id="400"><a href="<portletAPI:encodeURI path="/ET/Catalog/queryC" />">�������</a>
	<ul width="150">
		<li id="4023"><a href="<portletAPI:encodeURI path="/ET/Catalog/queryC" />">ָ�����(F10)</a></li>
		<li id="4024"><a href="<portletAPI:encodeURI path="/ET/Catalog/queryS" />">ϵͳ����</a></li>
	</ul>
	</li>
	
	<li id="50004" itemType="separator"></li>	
	<li id="501"><a href="<portletAPI:encodeURI path="/ET/View/InfoQPD" />">��������</a>
	<ul width="150">
		<li id="5023"><a href="<portletAPI:encodeURI path="/ET/System/queryS" />">��ǰϵͳ(F12)</a></li>
		<li id="5024"><a href="<portletAPI:encodeURI path="/ET/System/querySC" />">ϵͳ����</a></li>
		<li id="50005" itemType="separator"></li>
		<li id="5027"><a>�ؼ�����</a></li>	
	</ul>
	</li>
	<li id="50006" itemType="separator"></li>
	<li id="900"><a>Help</a>
	<ul width="150">
		<li id="9023"><a href="<portletAPI:encodeURI path="/ET/View/noFuncInfo" />">��������</a></li>
		<li id="9024"><a href="<portletAPI:encodeURI path="/ET/View/noFuncInfo" />">����/���ܼ���</a></li>
		<li id="9025"><a href="<portletAPI:encodeURI path="/ET/View/noFuncInfo" />">һ��̳�</a></li>
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