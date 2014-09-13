
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />



<style type='text/css'>

.chart_p {
	padding: 4px;
width: 200px;
}
</style>

<div id="ongoing-task" class="a_panel">
					<div class="p-heading">我的个人仪表盘</div>
					<div class="panel-body">
	
					<div id="case-funnel" class="chart_p">
						案件管道在这里呈现</div>
					<div id="revenue-area" class="chart_p">
						营收地域分析在此显示</div>
				</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->

	<!-- FusionCharts -->
	<script type="text/javascript" src="<portletAPI:encodeURI path="/js/fusioncharts/fusioncharts.js" />"></script>
	<script type="text/javascript" 	src="<portletAPI:encodeURI path="/js/fusioncharts/fusioncharts.widgets.js" />"></script>
	<script type="text/javascript" 	src="<portletAPI:encodeURI path="/js/fusioncharts/fusioncharts.charts.js" />"></script>
	<script type="text/javascript" 	src="<portletAPI:encodeURI path="/js/fusioncharts/fusioncharts.maps.js" />"></script>
	<script type="text/javascript" 	src="<portletAPI:encodeURI path="/js/fusioncharts/maps/fusioncharts.hubei.js" />" charset="gbk"></script>
	<script type="text/javascript" 	src="<portletAPI:encodeURI path="/js/fusioncharts/maps/fusioncharts.world.js" /> "></script>
	<script type="text/javascript" 	src="<portletAPI:encodeURI path="/js/fusioncharts/fusioncharts.theme.fint.js" /> "></script>

	<!-- 加入本页面所需要的JS -->
	<script type="text/javascript" src="<portletAPI:encodeURI path="/js/console/console.js" />"></script>