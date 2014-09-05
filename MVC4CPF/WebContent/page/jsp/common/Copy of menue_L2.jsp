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
	<li id="100"><a>���д��¼��(F7)</a>
	<ul width="150">
		<li id="1023"><a href="<portletAPI:encodeURI path="/ET/View/queryErrorInfo" />">��ѯ</a></li>
		<li id="10005" itemType="separator"></li>
		<li id="1024"><a href="<portletAPI:encodeURI path="/ET/View/exportErrorInfo" />">¼��</a></li>
		<li id="1031"><a href="<portletAPI:encodeURI path="/ET/View/queryCatTree" />">����</a></li>	
	</ul>
	</li>
	<li id="50001" itemType="separator"></li>
	<li id="200"><a>���д���(F8)</a>
	<ul width="150">
		<li id="2023"><a href="<portletAPI:encodeURI path="/ET/View/queryErrorInfo" />">��ѯ</a></li>
		<li id="20005" itemType="separator"></li>
		<li id="2024"><a href="<portletAPI:encodeURI path="/ET/View/exportErrorInfo" />">¼��</a></li>
		<li id="2031"><a href="<portletAPI:encodeURI path="/ET/View/queryCatTree" />">����</a></li>	
	</ul>
	</li>	
	<li id="50002" itemType="separator"></li>
	<li id="300"><a>�ڲ�ת��¼��(F9)</a>
	<ul width="150">
		<li id="3023"><a
			href="<portletAPI:encodeURI path="/ET/Console/addErrorInfoForm" />">��ѯ</a></li>
					<li id="30005" itemType="separator"></li>
		<li id="3024"><a
			href="<portletAPI:encodeURI path="/ET/Console/queryErrorInfo" />">¼��</a></li>
		<li id="3025"><a
			href="<portletAPI:encodeURI path="/ET/Console/upErrorInfoForm" />">����</a></li>

	</ul>
	</li>
	<li id="50003" itemType="separator"></li>
	<li id="400"><a>�ڲ�ת��¼��(F10)</a>
	<ul width="150">
		<li id="4023"><a
			href="<portletAPI:encodeURI path="/ET/Console/addErrorInfoForm" />">��ѯ</a></li>
					<li id="40005" itemType="separator"></li>
		<li id="4024"><a
			href="<portletAPI:encodeURI path="/ET/Console/queryErrorInfo" />">¼��</a></li>
		<li id="4025"><a
			href="<portletAPI:encodeURI path="/ET/Console/upErrorInfoForm" />">����</a></li>

	</ul>
	</li>
	<li id="50004" itemType="separator"></li>	
	<li id="501"><a>�ֽ�(F11)</a>
	<ul width="150">
		<li id="5023"><a href="<portletAPI:encodeURI path="/ET/View/querySystemCodeInfo" />">��ѯ</a></li>
		<li id="50005" itemType="separator"></li>
		<li id="5024"><a href="<portletAPI:encodeURI path="/ET/View/queryOtherCodeInfo" />">¼��</a></li>
		<li id="5025"><a href="<portletAPI:encodeURI path="/ET/View/queryOtherCodeInfo" />">����</a></li>
		
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