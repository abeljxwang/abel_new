<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<%@ page import="java.util.*,com.ibm.ais.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>




<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<portletAPI:encodeURI path="/theme/web.css" />"
	rel="stylesheet" type="text/css">



<style type='text/css'>
body {
	background: #dddddd;
}

#frame_main {
	position: absolute;
	font-family: "微软雅黑";
	font-size: 10pt;
	width: 90%;
	height: 100%;
	min-width: 800px;
	left: 5%;
	top: 0px;
	z-index: 1;
	border: 1px solid #CCC;
	filter: alpha(opacity = 95);
	opacity: 0.95;
	padding-top: 0;
}

#header_m {
	position: fixed;
	height: 100px;
	min-width: 800px;
	left: 5%;
	z-index: 100;
	top: 0px;
	margin: 0px 0px;
	background: #999999;
	border-bottom: 4px solid #f7942f;
	margin-right: 5%;
	background: url(<portletAPI:encodeURI path = "/images/topbg.jpg" />)
		no-repeat 0px 0px;
}

#left_area {
	position: absolute;
	top: 100px;
	width: 250px;
	max-width: 250px;
	margin: 0px;
	border: 0px solid #aaaaaa;
}




#main_area {

	width: auto;
	z-index: 9;
	border: 0px solid #AAAAAA;
	 margin-left: 250px;
	 margin-top: 100px;
}

#menue_p {

	width: auto;
	height: 30px;
	font-size: 14pt;
	min-width: 800px;
	right: 5%;
	z-index: 200;
	top: 100px;
	background: #999999;
	border-bottom: 2px solid #f7942f;

}

.menue_plus {
 position: absolute;
 with:50px;
	top: 100px;
	right:10px;
	margin-top: 8px;
	margin-right: 10px;
}

#work_page {
	width: auto;
	height: auto;
	top: 30px;
	left: 0;
	
	margin: 0px;

	overflow: auto;
	border: 12px solid #f7942f;
	background: #FEFEFE;
	padding: 0px;
}


.hd {
	padding: 0px;
	font-size: 16pt;
	COLOR: #eeFF00;
	font-family: 微软雅黑;
}

.hd01 {
	padding: 0px;
	font-size: 14pt;
	COLOR: #ffffff;
	font-family: 微软雅黑;
}

.hd02 {
	padding-top: 0px;
	font-size: 11pt;
	padding-right: 5px;
	COLOR: #ffffff;
	font-family: 微软雅黑;
}






.test_content {
	margin: 0px;
	z-index: 90;
	position: absolute;
	width: 1000px;
	top: 640px;
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

#bottom_info_m {
	position: fixed;
	height: 15px;
	width: 90%;
	left: 5.0%;
	bottom: 0;
	background: #113355;
	text-align: left;
	z-index: 220;
}

.bottom_info_c {
	font-size: 8pt;
	COLOR: #FFFFFF;
}

#bottom_tag_c {
	position: absolute;
	bottom: 0;
	right: 20px;
	height: 60px;
	width: 60px;
	-moz-border-radius-topright: 30px 30px;
	border-top-right-radius: 30px 30px;
	-moz-border-radius-topleft: 30px 30px;
	border-top-left-radius: 30px 30px;
	background: #113355;
	COLOR: #FFFFFF;
	z-index: 202;
	text-align: center;
}
</style>


<TITLE>POC</TITLE>

</HEAD>

<%
	try {

		String top_left_jsp = "/jsp/common/top_left_jsp.jsp";
		String menueL1_jsp = "/jsp/common/menue_L2_1.jsp";
		String taskmgrjsp = "/jsp/common/task_mgr.jsp";
		String left_ext_jsp = "/jsp/common/left_ext.jsp";
		String FMFName = null, FMAName = null;
		if (request.getAttribute("FMFName") != null)
			FMFName = request.getAttribute("FMFName").toString();
		if (request.getAttribute("FMAName") != null)
			FMAName = request.getAttribute("FMAName").toString();

		String currentjsp = null;
		if (request.getAttribute("currentjsp") != null)
			currentjsp = request.getAttribute("currentjsp").toString();
		else
			currentjsp = "/portlet/notfound.jsp";

		//String currentjspright = null;

		//if (request.getAttribute("currentjspright") != null)
		//	currentjspright = request.getAttribute("currentjspright").toString();
		//else
		//	currentjspright = "/portlet/notfound.jsp";

		String MRUNTIME = "", MENDTIME = null;
		if (request.getAttribute("MRUNTIME") != null)
			MRUNTIME = request.getAttribute("MRUNTIME").toString();
		if (request.getAttribute("MENDTIME") != null)
			MENDTIME = request.getAttribute("MENDTIME").toString();
		long jspstarttime = 0;
		try {
			jspstarttime = Long.parseLong(MENDTIME);
		} catch (Exception e1) {
			jspstarttime = 0;
		}

		com.ibm.ais.util.UserProfile up = new com.ibm.ais.util.UserProfile();
		MenueElement me = new MenueElement();
		if (session != null) {
			//session.removeAttribute("user_profile");
			if (session.getAttribute("user_profile") != null) {

				up = (UserProfile) session.getAttribute("user_profile");
				if (up != null && up.getAllMenueElement().size() > 0)
					me = (MenueElement) up.getAllMenueElement()
							.firstElement();
			}

		}
%>


<body>
	<div id="frame_main">
		<!---========================== [Header] ===================================-->
		<div id="header_m">
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 height=100%>
				<TR>
					<TD vAlign="bottom" width="20%" align="center"><IMG
						src="<portletAPI:encodeURI path="/images/logo.png" />" border=0></TD>
					<TD vAlign=top width="2%"></TD>
					<TD vAlign=bottom>
						<TABLE width="100%" border=0 height=98% cellSpacing=0
							cellPadding=0>
							<TR>
								<TD vAlign=top align=right class="hd02" colspan=2>欢迎您，<%=up.getUserID()%>
									| 我的工作台| 服务中心 | 登出
								</TD>
							</TR>
							<TR>
								<TD vAlign=middle align=left class="hd01">首页 | 案件 | 客户 | 财务
									| 潜在客户| 工作计划</TD>

								<TD align=right valign=bottom class="hd">案件综合管理</TD>
							</TR>

						</TABLE>
					</TD>
				</TR>
			</TABLE>
		</div>
		<div >




			<!---========================== [View:WorkPlace] ===================================-->


			<div id="left_area" valign=top>


				<div><jsp:include page="<%=top_left_jsp%>" flush="true" /></div>


				<div><jsp:include page="<%=left_ext_jsp%>" flush="true" /></div>
				<div><jsp:include page="<%=taskmgrjsp%>" flush="true" /></div>
			</div>
			<div></div>


			<div id="main_area" valign="top">
				
				<div id="work_page">
				<div id="menue_p"><jsp:include page="<%=menueL1_jsp%>"	flush="true" /></div>
				
				<jsp:include page="<%=currentjsp%>" flush="true" />	</div>
			</div>



		</div>

		<!---========================== [Foot] ===================================-->

	</div>
	<div id="bottom_info_m">


		<%
			long jspendtime = System.currentTimeMillis();
		%>

		<font class="bottom_info_c"><%=FMFName%>:<%=FMAName%>: ===
			&raquo; <%=currentjsp%> &nbsp;&nbsp; 运行时间,模块： <%=MRUNTIME%> ms
			&nbsp;JSP：<%=(jspendtime - jspstarttime)%>ms CopyRight@</font>

		<div id="bottom_tag_c">
			<br>Top
		</div>
	</div>
	<%
		} catch (Exception e) {
	%>
	<!---========================== [View : Exception] ===================================-->

	<table border="1" width=946 align=center cellspacing="0"
		cellpadding="0">
		<tr>
			<td width="60%" bgcolor="#CCCCCC"><font color="#222222">&nbsp;&nbsp;
					管理控制中心@IBM </font></td>
			<td width="40%" align="right" bgcolor="#CCCCCC"><IMG border="0"
				src="<portletAPI:encodeURI path="/images/jsp/info.gif" />"
				width="16" height="14"><IMG border="0"
				src="<portletAPI:encodeURI path="/images/jsp/maximize.gif" />"
				width="16" height="14"></td>
		</tr>



		<tr>
			<td width="100%" colspan="2" height=100 bgcolor=ffffff>
				<%
					out.print(e.getMessage());
						//System.err.println(e.getLocalizedMessage());
						e.printStackTrace();
				%>
			</td>
		</tr>
	</table>
	<%
		}
	%>

</BODY>
</HTML>
