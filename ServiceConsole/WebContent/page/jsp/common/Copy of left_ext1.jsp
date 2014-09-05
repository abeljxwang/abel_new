
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<link rel="StyleSheet" href="/ServiceConsole/theme/dtree.css" type="text/css" />
<script type="text/javascript" src="/ServiceConsole/js/dtree.js"></script>
	
<div class="dtree">

	<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>

	<script type="text/javascript">
		<!--

		d = new dTree('d');

		d.add(0,-1,'Service Directory');
		d.add(1,0,'经营计划');
		d.add(103,1,'记录作业任务','queryS_Detaile?serviceid=Well_Mgr');
		d.add(104,1,'制定年度计划','example01.html');
		d.add(105,1,'年度计划执行总结','example01.html');
		d.add(106,1,'经营考核','example01.html');
		d.add(2,0,'生产调度','example01.html');
		d.add(201,2,'生产信息收集','example01.html');
		d.add(202,2,'日常工作协调','example01.html');
		d.add(203,2,'车辆协调','example01.html');
		d.add(204,2,'工作检查/追踪','example01.html');
		d.add(202,2,'处理突发事件','example01.html');
		d.add(203,2,'危险品管理','example01.html');
		d.add(204,2,'生产统计','example01.html');		

		d.add(4,0,'资源管理','example01.html');
		d.add(401,4,'人员管理','example01.html');
		d.add(402,4,'装(设)备管理','example01.html');
		d.add(403,4,'物资管理','example01.html');
		d.add(404,4,'结算核算管理','example01.html');
		

		d.add(7,0,'统计分析','example01.html');
		d.add(701,7,'设备使用信息汇总','example01.html');
		d.add(702,7,'人员工时统计','example01.html');
		d.add(703,7,'物资使用信息汇总','example01.html');
		d.add(704,7,'生产统计','example01.html');
		d.add(705,7,'工程技术统计','example01.html');




		d.add(8,705,'Node 1.2','example01.html');
		d.add(9,0,'工程技术文档管理','example01.html','Pictures I\'ve taken over the years','','','/ServiceConsole/images/tree/imgfolder.gif');
		d.add(901,9,'提交文档','example01.html');
		d.add(902,9,'收集技术资料','example01.html');
		d.add(903,9,'文档归还','example01.html');
		d.add(904,9,'文档审核','example01.html');
		d.add(905,9,'文档归档保存','example01.html');
		d.add(906,9,'文档销毁','example01.html');
		d.add(907,9,'文档传送','example01.html');
		d.add(908,9,'文档借阅','example01.html');		
		d.add(909,9,'费用核算','example01.html');	
	
		d.add(10,909,'The trip to Iceland','example01.html','Pictures of Gullfoss and Geysir');
		d.add(11,909,'Mom\'s birthday','example01.html');
		d.add(12,0,'项目管理','example01.html','','','/ServiceConsole/images/tree/trash.gif');
		d.add(1201,12,'制定项目规划','example01.html');
		d.add(1202,12,'物资准备计划','example01.html');
		d.add(1203,12,'设备计划','example01.html');
		d.add(1204,12,'人员安排计划','example01.html');
		d.add(1205,12,'进行项目成本估算','example01.html');
		d.add(1206,12,'签定项目合同','example01.html');
		d.add(1207,12,'启动项目','example01.html');
		d.add(1208,12,'项目实施','example01.html');		
		d.add(1209,12,'成本核算','example01.html');	
		d.add(12010,12,'计划追踪','example01.html');		
		d.add(12011,12,'合同调整','example01.html');			
		document.write(d);

		//-->
	</script>

</div>

