<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>英达律师案件管理系统</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/theme.css" rel="stylesheet">


<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="header-bar container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">英达案件管理</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">首页</a></li>
					<li><a href="#about">案件</a></li>
					<li><a href="#contact">客户</a></li>
					<li><a href="#contact">财务</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">潜在客户 <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
					<li><a href="#contact">工作计划</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a> <select class="btn btn-primary ">
								<option>主任律师</option>
								<option>合伙人</option>
						</select>
					</a></li>
					<li><a href="#">设置</a></li>
					<li><a href="#">消息<span class="navbar-unread">1</span></a></li>
					<li><a href="#">帮助</a></li>
				</ul>
				<!-- 				<form class="navbar-form navbar-right"> -->
				<!-- 					<input type="text" class="form-control" placeholder="Search..."> -->
				<!-- 				</form> -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<div class="panel panel-bulletin">
						<div class="panel-heading">
							<h3 class="panel-title">律所动态</h3>
						</div>
						<div class="panel-body sidebar-bulletin">
							<a href="http://yingdalaw.com/newsdetail2.php?id=353">英达律师被武汉市委宣传部授予“武汉精神优秀践行者”荣誉称号</a>

						</div>
					</div>
					<div class="panel panel-oper">
						<div class="panel-heading">
							<h3 class="panel-title">搜索</h3>
						</div>
						<div class="panel-body">
							<form class=" ">
								<select class="select">
									<option selected="selected">ALL</option>
									<option>案件</option>
									<option>客户</option>
									<option>商业机会</option>
								</select> <input type="text" class="form-control search"
									placeholder="Search...">
							</form>
						</div>
					</div>
					<div class="panel panel-oper">
						<div class="panel-heading">
							<h3 class="panel-title">新建</h3>
						</div>
						<div class="panel-body">
							<select class="select">
								<option selected="selected">--</option>
								<option>案件</option>
								<option>客户</option>
								<option>商业机会</option>
								<option>工作计划</option>
							</select>
						</div>
					</div>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">最近项目</h3>
						</div>
						<div class="panel-body sidebar-message">
							<ui>
							<li><a href="http://yingdalaw.com/case.php" class="case">案件--宜昌兴发集团有限责任公司申行五亿元人民币非公开定向债务融资工具专项法律服务</a></li>
							<li><a href="http://yingdalaw.com/case.php" class="case">案件--武汉市水务集团发行10亿元融资债券提供专项法律服务</a></li>
							<li><a href="http://yingdalaw.com/case.php" class="user">客户--二汽集团</a></li>
							<li><a href="http://yingdalaw.com/case.php" class="case">案件--武汉市水务集团发行10亿元融资债券提供专项法律服务</a></li>
							<li><a href="http://yingdalaw.com/case.php" class="opp">商业机会--湖北省建设银行与山峡集团发生贷款纠纷</a></li>
							</ui>
						</div>
					</div>
				</ul>
			</div>


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div id="ongoing-case" class="col-xs-6 col-sm-8 box ">
					<h2 class="sub-header header">进行中的案件</h2>
					<div class="table-responsive">
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>案件名称</th>
									<th>案件类型</th>
									<th>客户名称</th>
									<th>委托范围</th>
									<th>案件状态</th>
									<th>代理何方</th>
									<th>收案日期</th>
									<th>受理机构</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>李某离婚案</td>
									<td>民事</td>
									<td>李某</td>
									<td>一审</td>
									<td>已受理</td>
									<td>被告</td>
									<td>2014年5月1日</td>
									<td>洪山区法院</td>
								</tr>
								<tr>
									<td>李某离婚案</td>
									<td>民事</td>
									<td>李某</td>
									<td>一审</td>
									<td>已受理</td>
									<td>被告</td>
									<td>2014年5月1日</td>
									<td>洪山区法院</td>
								</tr>
								<tr>
									<td>李某离婚案</td>
									<td>民事</td>
									<td>李某</td>
									<td>一审</td>
									<td><a class="warning icon-in-table" ><span
											class="glyphicon glyphicon-warning-sign"></span></a>等待结案</td>
									<td>被告</td>
									<td>2014年5月1日</td>
									<td>洪山区法院</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div id="ongoing-client" class="col-xs-6 col-sm-4 box">
					<h2 class="sub-header header">我的客户</h2>
					<div class="table-responsive">
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>客户名称</th>
									<th>客户属性</th>
									<th>所属行业</th>
									<th>联系人</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>李某</td>
									<td>个人客户</td>
									<td></td>
									<td>李某</td>
								</tr>
								<tr>
									<td>李某</td>
									<td>个人客户</td>
									<td></td>
									<td>李某</td>
								</tr>
								<tr>
									<td>李某</td>
									<td>个人客户</td>
									<td></td>
									<td>李某</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<div id="ongoing-task" class=" col-sm-7 box">
					<h2 class="sub-header">当前待办</h2>
					<div class="table-responsive">
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>完成日期</th>
									<th>完成状态</th>
									<th>主题</th>
									<th>相关人员</th>
									<th>相关事项</th>
									<th>客户名称</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>2014年5月2日</td>
									<td>未开始</td>
									<td>打电话给李某</td>
									<td>李某</td>
									<td>李某离婚案</td>
									<td>李某</td>
								</tr>
								<tr>
									<td>2014年5月2日</td>
									<td>未开始</td>
									<td>打电话给李某</td>
									<td>李某</td>
									<td>李某离婚案</td>
									<td>李某</td>
								</tr>
								<tr>
									<td>2014年5月2日</td>
									<td>未开始</td>
									<td>打电话给李某</td>
									<td>李某</td>
									<td>李某离婚案</td>
									<td>李某</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
				<div id="ongoing-money" class=" col-sm-5 box">
					<h2 class="sub-header">进账详情</h2>
					<div class="table-responsive">
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>案件</th>
									<th>进账</th>
									<th>状态</th>
									<th>计提时间</th>
									<th>实收时间</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>XX代理案</td>
									<td>3500</td>
									<td>计提</td>
									<td>2014-06-12</td>
									<td></td>
								</tr>
								<tr>
									<td>XX案</td>
									<td>3500</td>
									<td>到账</td>
									<td>2014-06-15</td>
									<td>2014-08-22</td>
								</tr>
								<tr>
									<td>XX代理案</td>
									<td>3500</td>
									<td>计提</td>
									<td>2014-06-12</td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>


				<h1 class="page-header"></h1>
				<h2 class="sub-header">我的个人仪表盘</h2>
				<div class="row placeholders">
					<div id="case-funnel" class="col-xs-6 col-sm-3 placeholder">
						案件管道在这里呈现</div>
					<div id="revenue-time" class="col-xs-6 col-sm-5 placeholder">
						应收柱状图在此显示</div>
					<div id="revenue-area" class="col-xs-6 col-sm-4 placeholder">
						营收地域分析在此显示</div>
				</div>


			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery-1.7.2.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="../../assets/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	<!-- FusionCharts -->
	<script type="text/javascript" src="js/fusioncharts/fusioncharts.js"></script>
	<script type="text/javascript"
		src="js/fusioncharts/fusioncharts.widgets.js"></script>
	<script type="text/javascript"
		src="js/fusioncharts/fusioncharts.charts.js"></script>
	<script type="text/javascript"
		src="js/fusioncharts/fusioncharts.maps.js"></script>
	<script type="text/javascript"
		src="js/fusioncharts/maps/fusioncharts.hubei.js" charset="gbk"></script>
	<script type="text/javascript"
		src="js/fusioncharts/maps/fusioncharts.world.js "></script>
	<script type="text/javascript"
		src="js/fusioncharts/fusioncharts.theme.fint.js "></script>

	<!-- 加入本页面所需要的JS -->
	<script type="text/javascript" src="js/console/console.js"></script>
</body>

</html>
