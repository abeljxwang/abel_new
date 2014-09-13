<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ page import="java.util.*,org.abel.webapp.db.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<style type="text/css">
#boutique_detail {
	width: 900px;
	position: relative;
	left: 0px;
	top: 0px;
	margin-left: auto;
	margin-right: auto;
}

#prod_desc {
	position: relative;
	left: 0px;
	top: 0px;
	margin: 20px 50px;
}

#boutique_social {
	position: relative;
	left: 0px;
	top: 0px;
	margin: 20px 50px;
}

#boutique {
	height: 500px;
	width: 1000px;
	position: relative;
	left: 0px;
	top: 0px;
	z-index: 8;
}

#boutique_main {
	height: 500px;
	width: 730px;
	border: 1px solid #AAAAAA;
	position: absolute;
	left: 0px;
	top: 0px;
	z-index: 2;
}

#boutique_info_back {
	height: 120px;
	width: 730px;
	background-color: #FFFFFF;
	opacity: 0.8;
	filter: alpha(opacity = 80); /* For IE8 and earlier */
	position: relative;
	left: 0px;
	top: -120px;
	z-index: 3;
}

.boutique_info {
	position: relative;
	top: 10px;
	font-size: 10pt;
	text-align: left;
	COLOR: #000000;
	font-family: 微软雅黑;
	margin: 10px 15px;
}

#boutique_slide_3 {
	height: 500px;
	width: 260px;
	border: 1px solid #AAAAAA;
	position: absolute;
	left: 740px;
	top: 0px;
	z-index: 12;
}

#boutique_slide_back {
	height: 50px;
	width: 260px;
	background-color: #FFFFFF;
	opacity: 0.8;
	filter: alpha(opacity = 80); /* For IE8 and earlier */
	position: absolute;
	left: 0px;
	bottom: 0px;
	z-index: 10;
}

.slide_title {
	font-size: 12pt;
	text-align: left;
	COLOR: #000000;
	font-family: 微软雅黑;
	margin: 2px 8px 2px 8px;
}

.slide_info {
	font-size: 10pt;
	text-align: left;
	COLOR: #000000;
	font-family: 华文楷体;
	margin: 0px 8px 2px 8px;
}

.social_icon {
	margin: 5px 8px 2px 8px;
}

.comments_follow_b {
	font-family: "微软雅黑";
	font-size: 10pt;
	margin: 8px 8px;
	border-top: 1px solid #DDDDFF;
}

#more_id {
	position: absolute;
	height: 15px;
	width: 50px;
	bottom: 5px;
	right: 5px;
	z-index: 1000;
	text-align: right;
	font-size: 10pt;
	COLOR: #DD0000;
	font-family: 微软雅黑;
}

.prod_Title_B {
	font-size: 18pt;
	text-align: left;
	COLOR: #000000;
	font-family: 微软雅黑;
	margin: 0px 8px 2px 8px;
}

.btnTest {
	background-image: -moz-linear-gradient(top, #ffffff, #dbdbdb);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #ffffff),
		color-stop(1, #dbdbdb));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='#ffffff',
		EndColorStr='#dbdbdb');
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='#ffffff',
		EndColorStr='#dbdbdb')";
	border: 1px solid #fff;
	-moz-box-shadow: 0px 0px 4px rgba(0, 0, 0, 0.4);
	-webkit-box-shadow: 0px 0px 4px rgba(0, 0, 0, 0.4);
	box-shadow: 0px 0px 4px rgba(0, 0, 0, 0.4);
	border-radius: 18px;
	-webkit-border-radius: 18px;
	-moz-border-radius: 18px;
	padding: 5px 15px;
	text-decoration: none;
	text-shadow: #fff 0 1px 0;
	float: left;
	margin-right: 15px;
	margin-bottom: 15px;
	display: block;
	color: #597390;
	line-height: 24px;
	font-size: 16px;
	font-weight: bold;
	font-family: 微软雅黑;
}

.textarea_s {
overflow:hidden;
	width: 700px;
	height: 120px;
	border: 1px solid #778899;
	padding: 5px;
	font-family: "微软雅黑";
	font-size: 10pt;
	margin: 8px 50px;
	maxlength:"50";
}
</style>

<%
	//String webappbase="/WebA2Z/EipTest";
	String baseurl = null;
	String sqltable = null;
	EIPResultSet dbresult = null;
	CmdMetaData cmd = null;
	//product_id,product_name,product_summmary,price,prod_sale_name
	String prd_id=null;
	String prd_name=null;
	String prd_desc=null;
	String prd_price=null;
	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");
		while (dbresult.next()) {
			prd_id=dbresult.getString(1);
			prd_name=dbresult.getString(2);
			prd_desc=dbresult.getString(3);
			prd_price=dbresult.getString(4);
		}
		
		//System.out.println(prd_id+":"+prd_name+":"+prd_desc+":"+prd_price);
			
	}
%>

<div id="boutique">
	<div id="boutique_main">
		<img src="/NGSCP/images/product/<%=prd_id%>.png" />
		<div id="boutique_info_back">
			<div class="boutique_info">
				<font class="slide_title"><%=prd_name%><br></font><%=prd_desc%> 。
			</div>
		</div>
		<div id="more_id">
			<a
				href="<portletAPI:createURI><portletAPI:URIAction name="detail"/>product_id=A0000001</portletAPI:createURI>">更多...</a>
		</div>
	</div>


	<div id="boutique_slide_3">
		<img src="/NGSCP/images/product/aaa.jpg" />

		<div id="boutique_slide_back">
			<table cellpadding="0" cellspacing="0" width=100%>
				<tr>
					<td><font class="slide_title">巴黎世家</font></td>
				</tr>
				<tr>
					<td><font class="slide_info">来自欧洲的服装品牌</font></td>
				</tr>
			</table>
		</div>
		<div id="more_id">更多...</div>

	</div>
</div>
<div id="boutique_detail">
	<div id="prod_desc">
		<table cellpadding="0" cellspacing="0" width=100%>
			<tr>
				<td><font class="prod_Title_B">产品名称：<%=prd_name%></font></td>
				<td align=right><font class="prod_Title_B" >价格: $<%=prd_price%></font></td>
			</tr>
			<tr>
				<td colspan=2><font class="boutique_info">价格自欧洲的服装自欧洲的服装自欧洲的服装自欧洲的服装自欧洲的服装自欧洲的服装自欧洲的服装自欧洲的服装自欧洲的服装自欧洲的服装自欧洲的服装自欧洲的服装</font></td>
			</tr>
		</table>
	</div>
	<div id="prod_desc">
		<table cellpadding="0" cellspacing="0" width=80%>
			<tr>
				<td><font><a class="btnTest" href="#"><span>
								加入我的关注</span></a></font></td>
				<td><a class="btnTest" href="#"><span> 放入购物车</span></a></td>
				<td><a class="btnTest" href="#"><span> 限量抢购</span></a></td>
			</tr>

		</table>
	</div>
	<div id="boutique_social">
		<table cellpadding="0" cellspacing="0" width=100%>
			<tr>
				<td><font class="">关注该产品的朋友： </font></td>
			</tr>
			<tr>
				<td class="comments_follow_b"><font class=""><img
						src="/NGSCP/images/social/icon2.png" width=50 class="social_icon" />
						<img src="/NGSCP/images/social/icon2.png" width=50
						class="social_icon" /> <img src="/NGSCP/images/social/icon2.png"
						width=50 class="social_icon" /> <img
						src="/NGSCP/images/social/icon2.png" width=50 class="social_icon" />
						<img src="/NGSCP/images/social/icon2.png" width=50
						class="social_icon" /> <img src="/NGSCP/images/social/icon2.png"
						width=50 class="social_icon" /> <img
						src="/NGSCP/images/social/icon2.png" width=50 class="social_icon" />
						<img src="/NGSCP/images/social/icon2.png" width=50
						class="social_icon" /> <img src="/NGSCP/images/social/icon2.png"
						width=50 class="social_icon" /> <img
						src="/NGSCP/images/social/icon2.png" width=50 class="social_icon" />
				</font></td>
			</tr>
		</table>
		<table cellpadding="0" cellspacing="0" width=100% border=0>

			<tr>
				<td class="comments_follow_b" width=10%><img
					src="/NGSCP/images/social/icon2.png" width=50 class="social_icon" /></td>
				<td class="comments_follow_b" width=90% valign=top>kufh lkwqf 欧洲的服装自欧洲的服装自欧洲的服装自欧洲的</td>
			</tr>
			<tr>
				<td class="comments_follow_b" width=10%><img
					src="/NGSCP/images/social/icon2.png" width=50 class="social_icon" /></td>
				<td class="comments_follow_b" width=90% valign=top>kufh lkwqf 欧洲的服装自欧洲的服装自欧洲的服装自欧洲的</td>
			</tr>
			<tr>
				<td class="comments_follow_b" width=10%><img
					src="/NGSCP/images/social/icon2.png" width=50 class="social_icon" /></td>
				<td class="comments_follow_b" width=90% valign=top>kufh lkwqf 欧洲的服装自欧洲的服装自欧洲的服装自欧洲的</td>
			</tr>
		</table>
		<table cellpadding="0" cellspacing="0" width=100% border=0>

			<tr>
				<td class="comments_follow_b" valign=top>
				<textarea name="styled-textarea" class="textarea_s" onfocus="this.value=''; " >Enter your comment here...</textarea>

</td>
			</tr>
			
		</table>
		<br><br>
	</div>


</div>




