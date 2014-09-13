<%@ page language="java" contentType="text/html; charset=GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />


<style type='text/css'>
#container {
	position: absolute;
	font-size: 14px;
	width: 1000px;
	top: 0px;
	z-index: 50;
	margin: 0px;
}

.test_content {
	margin: 0px;
	z-index: 90;
	position: absolute;
	width: 1000px;
	top: 540px;
	z-index: 2;
}

.scroller_anchor {
	height: 0px;
	margin: 0;
	padding: 0;
	z-index: 90;
}

.scroller {
	background: #999999;
	border: 1px solid #CCC;
	margin: 0 0 0px;
	z-index: 90;
	height: 30px;
	font-size: 18px;
	font-weight: bold;
	text-align: center;
	width: 1000px;
}

#boutique {
	height: 500px;
	width: 1000px;
	position: relative;
	left: 0px;
	top: 0px;
	margin: 0 0 10px 0;
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
	border: 1px solid #AAAAAA;
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
	border: 1px solid #AAAAAA;
	position: absolute;
	left: 740px;
	top: 255px;
	z-index: 6;
}

.slide_title {
	font-size: 12pt;
	text-align: left;
	COLOR: #000000;
	font-family: ΢���ź�;
	margin: 2px 8px 2px 8px;
}

.slide_info {
	font-size: 10pt;
	text-align: left;
	COLOR: #000000;
	font-family: ���Ŀ���;
	margin: 0px 8px 2px 8px;
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
	font-family: ΢���ź�;
}
</style>

<script type='text/javascript'>
	//<![CDATA[ 

	// This function will be executed when the user scrolls the page.
	$(window).scroll(
			function(e) {
				// Get the position of the location where the scroller starts.
				var scroller_anchor = $(".scroller_anchor").offset().top;

				// Check if the user has scrolled and the current position is after the scroller's start location and if its not already fixed at the top 
				if ($(this).scrollTop() >= (scroller_anchor - 100)
						&& $('.scroller').css('position') != 'fixed') { // Change the CSS of the scroller to hilight it and fix it at the top of the screen.
					$('.scroller').css({
						'COLOR' : '#FFFFFF',
						'background' : '#000066',
						'border' : '1px solid #000',
						'position' : 'fixed',
						'top' : '100px'
					});
					// Changing the height of the scroller anchor to that of scroller so that there is no change in the overall height of the page.
					$('.scroller_anchor').css('height', '30px');
				} else if ($(this).scrollTop() < (scroller_anchor - 100)
						&& $('.scroller').css('position') != 'relative') { // If the user has scrolled back to the location above the scroller anchor place it back into the content.

					// Change the height of the scroller anchor to 0 and now we will be adding the scroller back to the content.
					$('.scroller_anchor').css('height', '0px');

					// Change the CSS and put it back to its original position.
					$('.scroller').css({
						'background' : '#999999',
						'border' : '1px solid #CCC',
						'position' : 'relative',
						'top' : '-0px'
					});
				}
			});
	//]]>
</script>



<div id="container">

	<div id="boutique">
		<div id="boutique_main">
			<img src="/NGSCP/images/product/A0000001.png" />
			<div id="boutique_info_back">
				<div id="boutique_info">TOD'S
					�������������Ь�ĺͰ���Ʒ�ƣ��ر��Լ����ź�����һ��ġ�TOD'S ����Ь������������TOD'S
					�Ĵ����������Ƥ�ߵĴ��棬��ʮ��������ֻ��Ƥ�ס����ס��������moccasins��Ь��D Bag��Eight Bag
					����Ƥ���������������ļ�ȴ�������˴�����������Ħ�ɸ繫�������ա�ɳ��˹ͨ����ķ����˹�����缶�����¾��ǡ�����������ǻ��ǹ��壬TOD'S
					���ܹ��������������ֲ�ʧ��ݵ������ţ������ں����뻹��ŦԼ������ᣬTOD'S������ľ��ǡ���������ʵ�Ь�ӡ��롰��򵥵������Ƥ������</div>
			</div>
			<div id="more_id">
				<a href="/NGSCP/ET/Boutique/detail?product_id=A0000001">����...</a>
			</div>
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
			<div id="more_id"><a href="/NGSCP/ET/Boutique/detail?product_id=A0000002">����...</a></div>

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
			<div id="more_id"><a href="/NGSCP/ET/Boutique/detail?product_id=A0000003">����...</a></div>


		</div>
	</div>
	<!-- This div is used to indicate the original position of the scrollable fixed div. -->
	<div class="scroller_anchor"></div>
	<!-- This div will be displayed as fixed bar at the top of the page, when user scrolls -->
	<div class="scroller">
		<jsp:include page="/jsp/common/menue_L1.jsp" flush="true" /></div>
	<div class="test_content">
		<jsp:include page="/jsp/oversea/cmp01.jsp" flush="true" />

	</div>
</div>