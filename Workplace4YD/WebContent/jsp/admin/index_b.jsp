<%@ page 
language="java"
contentType="text/html; charset=GB2312"
%>

<%@ page
	import="java.util.*,org.abel.webapp.db.*,org.abel.webapp.common.CoreDataCacheManager"%>
	
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<%
CoreDataCacheManager.refreshAllMessageDesc();
 %>