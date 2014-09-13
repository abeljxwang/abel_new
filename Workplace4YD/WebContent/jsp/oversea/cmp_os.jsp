<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<style type="text/css">

#butt {
	
	width: 245px;
	border: 1px solid #AAAAAA;
	position: relative;
	left: 0px;
	top: 0px;
	margin:5px 0px 0px 0px;
	z-index: 1;
}

#butt0 {
	font-family: "华文楷体";
	font-size: 14pt;
	text-align: center;
	height:20px;
	width: 60px;	
	position: absolute;
  	top: 0px;
  	right: 0;
	z-index: 7;
	margin:0px 0px;
    background-color:#FFFFFF;
    opacity:0.6;
    filter:alpha(opacity=60); /* For IE8 and earlier */

}
#prod_name {
	font-family: "微软雅黑";
	font-size: 10pt;
	text-align: center;
	height: 40px;
	width: 245px;
	position: : absolute;
	left: 0px;
	top: 245px;
	z-index: 3;
	margin:0px 0px;
    opacity:0.8;
    filter:alpha(opacity=80); /* For IE8 and earlier */
    border-bottom: 0.5px solid #AAAAAA;
}

#prod_pub {
	position: relative;
	left: 0px;
	top: 15x;
	margin:2px 5px;
	z-index: 4;
}

#msg_follow {
	position: relative;
	left: 0 px;
	font-family: "微软雅黑";
	font-size: 10pt;
	z-index: 55;
	margin:0px 5px;
	vertical-align:top;
}
</style>
</head>


<div id="butt">
	<div><img src="/NGSCP/images/product/opus1.jpg" width="245" />
	<div id="butt0">精品</div>
	<div id="prod_name">Opus One<br>作品一号	</div>
	</div>
	<div id="prod_pub">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td width=80><img src="/NGSCP/images/social/icon.png"
					width="60" /></td>
				<td><a class="STYLE4">用户姓名</a><br>
				<a class="STYLE5">19：00发布</a></td>
			</tr>
			<tr>
				<td colspan=2>
					<img src="/NGSCP/images/social/sina.png" width="20" /> 
					<img src="/NGSCP/images/social/facebook.png" width="20" /> 
					<img src="/NGSCP/images/social/email.png" width="20" />
				</td>
			</tr>
		</table>
	</div>
	<div id="msg_follow">
	<jsp:include page="/jsp/comments/cmp_msg.jsp" flush="true" />
	</div>
</div>


