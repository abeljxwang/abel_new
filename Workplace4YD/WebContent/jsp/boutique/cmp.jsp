<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<style type="text/css">


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
	border: 0.5px solid #AAAAAA;
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

#boutique_info {
	position: relative;
	top: 10px;
	font-size: 10pt;
	text-align: left;
	COLOR: #000000;
	font-family: 微软雅黑;
	margin: 10px 15px;
}

#boutique_slide_1 {
	height: 245px;
	width: 260px;
	border: 0.5px solid #AAAAAA;
	position: absolute;
	left: 740px;
	top: 0px;
	z-index: 8;
}

#boutique_slide_back {
	height: 50px;
	width: 260px;
	background-color: #FFFFFF;
	opacity: 0.8;
	filter: alpha(opacity = 80); /* For IE8 and earlier */
	position: relative;
	left: 0px;
	top: -50px;
	z-index: 10;
}

#boutique_slide_2 {
	height: 245px;
	width: 260px;
	border: 0.5px solid #AAAAAA;
	position: absolute;
	left: 740px;
	top: 255px;
	z-index: 6;
}
.slide_title{
	font-size: 12pt;
	text-align: left;
	COLOR: #000000;
	font-family: 微软雅黑;
	margin: 2px 8px 2px 8px;
}

.slide_info{
	font-size: 10pt;
	text-align: left;
	COLOR: #000000;
	font-family: 华文楷体;
	margin:  0px 8px 2px 8px;
}



#more_id {
	position: absolute;
	height: 15px;
	width: 50px;
    bottom: 5px;
    right:5px;
	z-index: 1000;
	text-align: right;
	font-size: 10pt;
	COLOR: #DD0000;
	font-family: 微软雅黑;
}


</style>


</head>

<div id="boutique">
	<div id="boutique_main">
		<img src="/NGSCP/images/product/tods.png" />
		<div id="boutique_info_back">
			<div id="boutique_info">TOD'S 是意大利著名的鞋履和包包品牌，特别以集优雅和舒适一身的“TOD'S
				豆豆鞋”闻名于世。TOD'S 的传奇是意大利皮具的传奇，二十年来他们只有皮底、胶底、软底三款moccasins便鞋及D
				Bag、Eight Bag
				两款皮包。但就是这样的简单却吸引到了戴安娜王妃、摩纳哥公主卡洛琳、沙朗斯通、汤姆汉克斯等世界级的名媛巨星。不管面对明星还是贵族，TOD'S
				都能够让人贴近生活又不失身份地舒适着；不管在好莱坞还是纽约上流社会，TOD'S所代表的就是“最顶级又舒适的鞋子”与“最简单但经典的皮包”。</div>
		</div>
		<div id="more_id"><a href="<portletAPI:createURI><portletAPI:URIAction name="detail"/>product_id=A0000001</portletAPI:createURI>">更多...</a></div>
	</div>


	<div id="boutique_slide_1">
		<img src="/NGSCP/images/product/balenciaga.jpg" />

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

	<div id="boutique_slide_2">
		<img src="/NGSCP/images/product/opus-wine-c.jpg" />
		<div id="boutique_slide_back">
			<table cellpadding="0" cellspacing="0" width=100%>
				<tr>
					<td><font class="slide_title">Opus One</font></td>
				</tr>
				<tr>
					<td><font class="slide_info">加州知名红酒</font></td>
				</tr>
			</table>
		</div>
		<div id="more_id">更多...</div>


	</div>


</div>


