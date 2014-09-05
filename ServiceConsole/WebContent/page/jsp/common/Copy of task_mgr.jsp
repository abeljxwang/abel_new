
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

	<link rel="STYLESHEET" type="text/css" href="/ServiceConsole/theme/dhtmlXTree.css">
	<script  src="/ServiceConsole/js/tree/dhtmlXCommon.js"></script>
	<script  src="/ServiceConsole/js/tree/dhtmlXTree.js"></script>

 <div id="treeboxbox_tree1" style="width:230; height:260;background-color:#f5f5f5;border :1px solid Silver;; overflow:auto;"/>
<script>
		tree=new dhtmlXTreeObject("treeboxbox_tree1","100%","100%",0);
	tree.setImagePath("/ServiceConsole/images/imgs/");
	tree.loadXML("/ServiceConsole/xml/tree_b.xml");
   </script>


<!---========================== [View:TASK END] ===================================-->