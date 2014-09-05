<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI" %>
<portletAPI:init />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%@ page session="false" buffer="none" %>



<META http-equiv="Content-Type" content="text/html; charset=GB2312">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<portletAPI:encodeURI path="/theme/web.css" />"
	rel="stylesheet" type="text/css">
<TITLE>eBankingSystem Process Engine for 综合开户 POC</TITLE>
</HEAD>




<body leftmargin="0" topmargin="0">
<!---========================== [Header] ===================================-->

<table width="1000" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" align="center" width="100%" id="OuterTD">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			bgcolor="#ffffff"
			background="<portletAPI:encodeURI path="/images/TB1.gif" />">
			<tr>
				<td width="35%" valign="top" rowspan=3><IMG border="0"
					src="<portletAPI:encodeURI path="/images/Hlogo.gif" />" width="339"
					height="85"></td>
				<td width="35%" valign="top"></td>
				<td width="30%" valign="bottom">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top" align="right"><a class="ename">ABC</a>/<a
							class="dname">生产协调部</a></td>
						<td width="5" rowspan="3"></td>
					</tr>
					<tr>
						<td valign="top" align="right"><a class="dname">生产运行管理系统</a></td>
					</tr>
					<tr>
						<td valign="top" align="right" height="2"></td>
					</tr>
				</table>

				</td>
			</tr>
			<tr>
				<td valign="bottom" colspan=2><a class="cname">| 主页 | 生产作业 | 生产管理 |
				决策管理 | 统计分析 | 资料共享 | 外部协作</a></td>
			</tr>
			<tr>
				<td valign="bottom" colspan=2 height=3></td>
			</tr>
		</table>
		</td>
	</tr>
</table>




<%try {
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

	String currentjspright = null;

	if (request.getAttribute("currentjspright") != null)
		currentjspright = request.getAttribute("currentjspright").toString();
	else
		currentjspright = "/portlet/notfound.jsp";

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
%>

<table border="0" width="1000" align=center cellspacing="0"
	cellpadding="0">




	<!---========================== [View] ===================================-->

	<tr>
		<td width="100%" colspan="2" height=450 bgcolor=ffffff
			bordercolorlight="#ffffff" bordercolordark="#ffffff" border=0
			align=center valign=top cellspacing="0"
	cellpadding="0"><!---========================== [View start] ===================================-->



		<table height="100%" width="100%" cellpadding="0" cellspacing="0"
			border="0">
			<tr>
				<td height=1></td>
			</tr>
			<tr>
				<td valign=top><!---========================== [View: Left 1] ===================================-->


				<table height="400" width="744" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td height="15" width="100%" colspan="3" bgcolor="#ffffff">
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<td bgcolor="#5A5A5A" width="10%" height=100% valign="bottom"
								align=left><font class="FTitle"> &nbsp;工作平台</font></td>
							<td width=30%></td>
							<td width=60%></td>
						</table>
						</td>
					</tr>
					<tr>
						<td height="1" colspan="3" bgcolor="#8080C0"></td>
					</tr>
					<tr>
					
						<td height="12" colspan="3" class="tcolor"></td>
					</tr>
					<tr>
						<td width="12" class="tcolor"></td>
						
						<td width="720" bgcolor="#FFFFFF">
						
						<table width="100%" height="100%" cellpadding="0" cellspacing="0"
							class="groovedouble" border=0>
							<tr>
								<td width="100%">

								<table width="100%" height="20" cellpadding="0" cellspacing="1">
									<tr>
										<td valign=top height=12 class="tcolor"></td>
									</tr>
								</table>

								<table width="100%" height="100%" cellpadding="0"
									cellspacing="8">
									<tr>
										<td valign=top>
										
										<%
										System.out.println();
										%>
										<jsp:include page="<%=currentjsp%>"
											flush="true" /></td>
									</tr>
								</table>

								</td>
							</tr>
						</table>
						
						</td>
						<td width="12" class="tcolor"></td>
					</tr>
					<tr>
						<td height="12" width="100%" class="tcolor" colspan="3"></td>
					</tr>
					<tr>
						<td height="1" colspan="3" bgcolor="#ff80C0"></td>
					</tr>
				</table>



				</td>
			</tr>

			<tr>
				<td height=1 colspan=3></td>
			</tr>
		</table>
		
		<!--=======================================================================-->
		
		
	
		
		
		<!---========================== [View end] ===================================-->
		</td>
	</tr>


	<!---========================== [Foot] ===================================-->
	<%long jspendtime = System.currentTimeMillis();%>
	<tr>
		<td bgcolor="#88bbee" bordercolorlight="#88bbee"
			bordercolordark="#000000"><font color="#FFFFFF">&nbsp;&nbsp;<%=FMFName%>:<%=FMAName%>:
		===&raquo; <%=currentjsp%></font></td>
		<td bgcolor="#88bbee" bordercolorlight="#88bbee"
			bordercolordark="#000000"><font color="#FFFFFF">&nbsp;&nbsp; 运行时间,模块：<%=MRUNTIME%>ms
		&nbsp;JSP：<%=(jspendtime - jspstarttime)%>ms</font></td>
	</tr>
</table>

<%} catch (Exception e) {
%>


<table border="1" width=946 align=center bordercolorlight="#000000"
	cellspacing="0" cellpadding="0" bordercolordark="#000000">
	<tr>
		<td width="60%" bgcolor="#CCCCCC" bordercolorlight="#CCCCCC"
			bordercolordark="#CCCCCC"><font color="#222222">&nbsp;&nbsp;
		管理控制中心@IBM </font></td>
		<td width="40%" align="right" bgcolor="#CCCCCC"
			bordercolorlight="#CCCCCC" bordercolordark="#CCCCCC"><IMG border="0"
			src="<portletAPI:encodeURI path="/images/jsp/info.gif" />" width="16"
			height="14"><IMG border="0"
			src="<portletAPI:encodeURI path="/images/jsp/maximize.gif" />"
			width="16" height="14"></td>
	</tr>



	<!---========================== [View] ===================================-->

	<tr>
		<td width="100%" colspan="2" height=100 bgcolor=ffffff
			bordercolorlight="#ffffff" bordercolordark="#ffffff" border=0><!---========================== [View start] ===================================-->
		<%out.print(e.getMessage());
//System.err.println(e.getLocalizedMessage());
e.printStackTrace();%> <!---========================== [View end] ===================================-->
		</td>
	</tr>
</table>


<%}
%>

<table border="0" width=946 align=center bgColor=#cccccc>
	<TR>
		<TD ALIGN=CENTER>CopyRight IBM BCS</TD>
	</TR>
</TABLE>
</body>
</html>
