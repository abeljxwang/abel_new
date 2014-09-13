
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />



<style type='text/css'>
.panel {
	padding: 0px;
	COLOR: #eeFF00;
	font-family: 微软雅黑;
	margin-top: 0px;
	border-bottom: 2px solid #fefefe;
}

.l-heading {
	padding: 2px 6px;
	font-size: 12pt;
	COLOR: #ffffff;
	font-family: 微软雅黑;
	background: #f7942f;
	border-top-left-radius:5px; 
	border-top-right-radius:0px; 
	border-bottom-left-radius:0px; 
	border-bottom-right-radius:0px; 
	margin-top: 1px;
}

.l-body {
	padding: 4px;
	font-size: 11pt;
	padding-right: 5px;
	COLOR: #333333;
	font-family: 楷体;
	background: #ffffff;
	border-left: 3px solid #f7942f;
border-bottom: 3px solid #f7942f;
}
</style>

<div class="panel">
	<div class="l-heading">
		最近项目
	</div>
	<div class="l-body">
		<ui>
		<li><a class="case">案件--宜昌兴发集团有限责任公司申行五亿元人民币非公开定向债务融资工具专项法律服务</a></li>
		<li><a class="case">案件--武汉市水务集团发行10亿元融资债券提供专项法律服务</a></li>
		<li><a class="user">客户--二汽集团</a></li>
		<li><a class="case">案件--武汉市水务集团发行10亿元融资债券提供专项法律服务</a></li>
		<li><a class="opp">商业机会--湖北省建设银行与山峡集团发生贷款纠纷</a></li>
		</ui>
	</div>
</div>

<div class="l_panel">
						<div class="l-heading">新建
						</div>
						<div class="l-body">
							<select class="select">
								<option selected="selected">--</option>
								<option>案件</option>
								<option>客户</option>
								<option>商业机会</option>
								<option>工作计划</option>
							</select>
						</div>
					</div>
