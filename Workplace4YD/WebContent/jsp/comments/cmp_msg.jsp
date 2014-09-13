<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

</head>
<style type="text/css">
.TitleC {
	font-size: 16pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: 微软雅黑;
}

.messageC {
	font-size: 13pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: 华文楷体;
}

#comments_main {
	font-family: "华文楷体";
	font-size: 12pt;
	text-align: center;
	position: relative;
	z-index: 50;
	margin:0px 0px;
}

#comments_follow {
	font-family: "微软雅黑";
	font-size: 10pt;
	width: 100%;
	z-index: 56;
	margin: 5px 0px;
	border-top: 1px solid #DDDDFF;
}
.msg_pub_name {
	font-family: "微软雅黑";
	font-size: 10pt;
	text-align: left;
	margin:5px 5px;
	}
	
.msg_pub_info {
	font-family: "华文仿宋";
	font-size: 10pt;
	text-align: left;
	margin: 0px 5px;
	}

</style>

<div id="comments_main">

	<div id="comments_follow">
		<table cellpadding="2" cellspacing="0" border="0" width="100%">
			<tr>
				<td width=40 valign=top><img src="/NGSCP/images/social/icon.png"
					width="40" /></td>
				<td valign=top><a class="msg_pub_name" >用户姓名</a><br>
				<a class="msg_pub_info">19：00发 布发布 发布 发布发发布 发布 发布发 发布 发布 发布发 发布 发布 发布发  布发布发布</a></td>
			</tr>
			

		</table>
	</div>
	
	<div id="comments_follow"><table cellpadding="2" cellspacing="0" border="0" width="100%">
			<tr>
				<td width=40><img src="/NGSCP/images/social/icon.png"
					width="40" /></td>
				<td><a class="msg_pub_name">用户姓名</a><br>
				<a class="msg_pub_info">19：00发 布发布 发布 发布发 布发布发布</a></td>
			</tr>
			

		</table></div>
	
	<div id="comments_follow">follow up</div>

</div>