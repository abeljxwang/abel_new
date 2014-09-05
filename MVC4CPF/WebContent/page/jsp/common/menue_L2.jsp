<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<script type="text/javascript" src="<portletAPI:encodeURI path="/js/menu-for-applications.js" />"></script>


<table cellpadding="0" cellspacing="1" border="0" width="100%" 	height="100%">
	<tr>
		<td>
		<div id="POCM"></div>
		</td>
	</tr>
</table>
<ul id="menuModel" style="display: none">
	<li id="100"><a href="<portletAPI:encodeURI path="/ET/View/" />">银行存款录入(F7)</a>
	<ul width="150">
		<li id="1023"><a href="<portletAPI:encodeURI path="/ET/View/" />">查询</a></li>
		<li id="10005" itemType="separator"></li>
		<li id="1024"><a href="<portletAPI:encodeURI path="/ET/View/b" />">录入</a></li>
		<li id="1031"><a href="<portletAPI:encodeURI path="/ET/View/" />">复核</a></li>	
	</ul>
	</li>
	<li id="50001" itemType="separator"></li>
	<li id="200"><a>银行存款复核(F8)</a>
	<ul width="150">
		<li id="2023"><a href="<portletAPI:encodeURI path="/ET/View/queryErrorInfo" />">查询</a></li>
		<li id="20005" itemType="separator"></li>
		<li id="2024"><a href="<portletAPI:encodeURI path="/ET/View/exportErrorInfo" />">录入</a></li>
		<li id="2031"><a href="<portletAPI:encodeURI path="/ET/View/queryCatTree" />">复核</a></li>	
	</ul>
	</li>	
	<li id="50002" itemType="separator"></li>
	<li id="300"><a>内部转账录入(F9)</a>
	<ul width="150">
		<li id="3023"><a
			href="<portletAPI:encodeURI path="/ET/Console/addErrorInfoForm" />">查询</a></li>
					<li id="30005" itemType="separator"></li>
		<li id="3024"><a
			href="<portletAPI:encodeURI path="/ET/Console/queryErrorInfo" />">录入</a></li>
		<li id="3025"><a
			href="<portletAPI:encodeURI path="/ET/Console/upErrorInfoForm" />">复核</a></li>

	</ul>
	</li>
	<li id="50003" itemType="separator"></li>
	<li id="400"><a>内部转账录入(F10)</a>
	<ul width="150">
		<li id="4023"><a
			href="<portletAPI:encodeURI path="/ET/Console/addErrorInfoForm" />">查询</a></li>
					<li id="40005" itemType="separator"></li>
		<li id="4024"><a
			href="<portletAPI:encodeURI path="/ET/Console/queryErrorInfo" />">录入</a></li>
		<li id="4025"><a
			href="<portletAPI:encodeURI path="/ET/Console/upErrorInfoForm" />">复核</a></li>

	</ul>
	</li>
	<li id="50004" itemType="separator"></li>	
	<li id="501"><a>现金(F11)</a>
	<ul width="150">
		<li id="5023"><a href="<portletAPI:encodeURI path="/ET/View/querySystemCodeInfo" />">查询</a></li>
		<li id="50005" itemType="separator"></li>
		<li id="5024"><a href="<portletAPI:encodeURI path="/ET/View/queryOtherCodeInfo" />">录入</a></li>
		<li id="5025"><a href="<portletAPI:encodeURI path="/ET/View/queryOtherCodeInfo" />">复核</a></li>
		
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