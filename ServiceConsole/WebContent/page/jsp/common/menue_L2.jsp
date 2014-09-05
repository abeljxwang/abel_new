
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI" %>

<portletAPI:init />

<HEAD>

	<script type="text/javascript" src="<portletAPI:encodeURI path="/js/menu-for-applications.js" />"></script>

</head>

<table cellpadding="0" cellspacing="1" border="0" width="943" height="100%">
<tr><td bgcolor="#1947a3" height=15>
<img src="<portletAPI:encodeURI path="/images/WOD-T.jpg" />"><a class="ename"></a></td></tr>
<tr><td><div id="POC"></div></td></tr>
</table>
<ul id="menuModel" style="display:none">
<li id="600"><a>类别管理(Catalog)</a>
	<ul width="200">
	<li id="6021"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/queryC" />">分类管理</a></li>
	<li id="6000" itemType="separator"></li>
	<li id="6022"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/queryCBM?catalogid=10" />">CBM</a></li>
	</ul>
</li>
<li id="50003" itemType="separator"></li>
<li id="200"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/querySLst" />">服务管理(Service)</a>
	<ul width="200">
	<li id="2021"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/querySLst" />">服务管理(Service)</a></li>
	<li id="2022"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/serviceCard" />">服务卡查阅</a></li>
	</ul>
</li>
<li id="50003" itemType="separator"></li>
<li id="300"><a>主数据管理(MDM)</a>
<ul width="200">
	<li id="3021"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/queryM_List" />">消息管理(Input/Output)</a></li>
	<li id="3022"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/queryME_List" />">实体元素(Entity/Element)</a></li>
	<li id="3023"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/queryM" />">元数据字典(MetaData)</a></li>	
	<li id="3000" itemType="separator"></li>
	<li id="3024"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/RDF" />">RDF*OWL</a></li>	
	</ul></li>
<li id="50003" itemType="separator"></li>
<li id="400"><a>Utility</a>
<ul width="200">
	<li id="4021"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/refresh" />">Refresh</a></li>

	<li id="4000" itemType="separator"></li>
	<li id="4024"><a href="<portletAPI:encodeURI path="/ET/SOAAdmin/RDF" />">RDF*OWL</a></li>	
	</ul></li>
<li id="50003" itemType="separator"></li>

</ul>


<script type="text/javascript">
var menuModel = new DHTMLSuite.menuModel();
menuModel.addItemsFromMarkup('menuModel');
menuModel.init();

var menuBar = new DHTMLSuite.menuBar();
menuBar.addMenuItems(menuModel);
menuBar.setTarget('POC');
menuBar.init(); 


</script>