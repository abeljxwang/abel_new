
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
	<link rel="STYLESHEET" type="text/css" href="/ServiceConsole/theme/css_dhtmlsuite/dhtmlXTabbar.css">
	<script  src="/ServiceConsole/js/dhtmlx/dhtmlXCommon.js"></script>
	<script  src="/ServiceConsole/js/dhtmlx/dhtmlXTabbar.js"></script>
	<script  src="/ServiceConsole/js/dhtmlx/dhtmlXTabbar_start.js"></script>


	
<div id="a_tabbar" style="width:230; height:210;"/>		</div>
	<script>
           	tabbar=new dhtmlXTabBar("a_tabbar","top");
            tabbar.setImagePath("/ServiceConsole/images/imgs/");
 			tabbar.setHrefMode("ajax-html");
			tabbar.setSkinColors("#FCFBFC","#F4F3EE");
			tabbar.addTab("a1","ToDo:´ý°ì","60px");
			tabbar.addTab("a2","History","60px");
			tabbar.setContentHref("a1","/ServiceConsole/page/jsp/common/tskljsp.jsp");
			tabbar.setContentHref("a2","/ServiceConsole/page/jsp/common/test.jsp");
			tabbar.setTabActive("a1");
			tabbar.enableAutoSize(false,false);
	</script>
<!---========================== [View:TASK END] ===================================-->