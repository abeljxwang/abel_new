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
	font-family: ΢���ź�;
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
	font-family: ΢���ź�;
	margin: 2px 8px 2px 8px;
}

.slide_info{
	font-size: 10pt;
	text-align: left;
	COLOR: #000000;
	font-family: ���Ŀ���;
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
	font-family: ΢���ź�;
}


</style>


</head>

<div id="boutique">
	<div id="boutique_main">
		<img src="/NGSCP/images/product/tods.png" />
		<div id="boutique_info_back">
			<div id="boutique_info">TOD'S �������������Ь�ĺͰ���Ʒ�ƣ��ر��Լ����ź�����һ��ġ�TOD'S
				����Ь������������TOD'S �Ĵ����������Ƥ�ߵĴ��棬��ʮ��������ֻ��Ƥ�ס����ס��������moccasins��Ь��D
				Bag��Eight Bag
				����Ƥ���������������ļ�ȴ�������˴�����������Ħ�ɸ繫�������ա�ɳ��˹ͨ����ķ����˹�����缶�����¾��ǡ�����������ǻ��ǹ��壬TOD'S
				���ܹ��������������ֲ�ʧ��ݵ������ţ������ں����뻹��ŦԼ������ᣬTOD'S������ľ��ǡ���������ʵ�Ь�ӡ��롰��򵥵������Ƥ������</div>
		</div>
		<div id="more_id"><a href="<portletAPI:createURI><portletAPI:URIAction name="detail"/>product_id=A0000001</portletAPI:createURI>">����...</a></div>
	</div>


	<div id="boutique_slide_1">
		<img src="/NGSCP/images/product/balenciaga.jpg" />

		<div id="boutique_slide_back">
			<table cellpadding="0" cellspacing="0" width=100%>
				<tr>
					<td><font class="slide_title">��������</font></td>
				</tr>
				<tr>
					<td><font class="slide_info">����ŷ�޵ķ�װƷ��</font></td>
				</tr>
			</table>
		</div>
		<div id="more_id">����...</div>

	</div>

	<div id="boutique_slide_2">
		<img src="/NGSCP/images/product/opus-wine-c.jpg" />
		<div id="boutique_slide_back">
			<table cellpadding="0" cellspacing="0" width=100%>
				<tr>
					<td><font class="slide_title">Opus One</font></td>
				</tr>
				<tr>
					<td><font class="slide_info">����֪�����</font></td>
				</tr>
			</table>
		</div>
		<div id="more_id">����...</div>


	</div>


</div>


