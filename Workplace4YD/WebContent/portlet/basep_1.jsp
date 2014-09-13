<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ page session="false" buffer="none"%>
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<portletAPI:encodeURI path="/theme/web.css" />"
	rel="stylesheet" type="text/css">
<TITLE>POC</TITLE>
</HEAD>




<body leftmargin="0" topmargin="0">
<!---========================== [Header: Start] ===================================-->

<TABLE cellSpacing=0 cellPadding=0 width=1001 border=0>
	<TR>
		<TD id=OuterTD vAlign=top valign=middle width="100%">
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgcolor="#068282" border=0>
			<TR>
				<TD vAlign=bottom width=300><IMG height=46
					src="<portletAPI:encodeURI path="/images/logo_cpf.gif" />"
					width=276 border=0></TD>
				<TD width="500" valign=bottom>
				<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
					bgcolor="#068282" border=0>
					<TR>
						<TD vAlign=bottom><font color=FFFF66>&nbsp;&nbsp;[Home]&nbsp;
						[Registration]&nbsp; [Login/Out]&nbsp;[Feedback]&nbsp; [Help]</font></TD>
					</tr>
					<tr>
						<TD width="50%" valign=bottom bgcolor=999999></TD>
					</tr>
					<tr>
						<td vAlign=bottom><font color=FFFFFF class="M1Title">| 结算 | 信贷 | 资金 | 决策分析 | 风险 | 审计 | 客户管理 |</font></TD>
					</TR>
				</TABLE>
				</TD>
				<TD vAlign=bottom width="200">&nbsp;</TD>
			</TR>
			<TR>
				<TD vAlign=bottom colSpan=3 bgcolor="#EEB405" height=1></TD>
			</TR>
			<TR>
				<TD vAlign=bottom colspan=3>

				<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" border=0>
					<TR>

						<td>
						<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
							<TR>
								<td valign=bottom>&nbsp;<IMG
									src="<portletAPI:encodeURI path="/images/searchB.gif" />"
									border=0><br>
								</TD>
							</tr>
							<TR>
								<td bgcolor="#10A6A6">&nbsp;&nbsp;<input type=text
									class="queryINPUT"><input type=button value=" >> "
									class="queryINPUT"></TD>
							</tr>
						</TABLE>
						</TD>
						<td width=1 bgcolor="#CCCCCC"></TD>
						<td>
						<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
							<TR>
								<td valign=bottom>&nbsp;<IMG
									src="<portletAPI:encodeURI path="/images/searchB.gif" />"
									border=0><br>
								</TD>
							</tr>
							<TR>
								<td bgcolor="#10A6A6">&nbsp;&nbsp;<input type=text
									class="queryINPUT"><input type=text class="queryINPUT"><input
									type=button value=" >> " class="queryINPUT"></TD>
							</tr>
						</TABLE>
						</TD>
						<td width=1 bgcolor="#CCCCCC"></TD>
						<td>
						<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
							<TR>
								<td valign=bottom>&nbsp;<IMG
									src="<portletAPI:encodeURI path="/images/searchB.gif" />"
									border=0><br>
								</TD>
							</tr>
							<TR>
								<td bgcolor="#10A6A6">&nbsp;&nbsp;<input type=text
									class="queryINPUT"><input type=text class="queryINPUT">
									<BUTTON class="icon_search" type=submit></BUTTON></TD>
							</tr>
						</TABLE>
						</TD>
					</tr>
				</TABLE>

				</TD>
			</TR>
		</TABLE>
		</TD>
	</TR>
</TABLE>
<!---========================== [Header: End] ===================================-->


<%
		try {

		String top_left_jsp = "/page/jsp/common/top_left_jsp.jsp";
		String menueL2_jsp = "/page/jsp/common/menue_L2.jsp";
		String taskmgrjsp = "/page/jsp/common/task_mgr.jsp";
		String left_ext_jsp = "/page/jsp/common/left_ext.jsp";
		String reference_ext_jsp = "/page/jsp/common/reference.jsp";
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
%>

<!---========================== [View start] ===================================-->
<TABLE border=0 cellSpacing=0 cellPadding=0 width="1001" border=0 height="450" bgcolor="#FFFFFF">
	<TR>
		<TD valign=top width="245">

		<table width="245" height="100%" border="0" cellpadding="0" cellspacing="0">
			<jsp:include page="<%=top_left_jsp%>" flush="true" />
			<tr>
				<td bgcolor="FFFFFF"></td>
				<td bgcolor="EEEEFF" valign="top">
				<!---========================== [View:TASK] ===================================-->
				<jsp:include page="<%=taskmgrjsp%>" flush="true" /> 
				<!---========================== [View:LEFT_EXT] ===================================-->
				</td>
			</tr>

		</table>
		</td>
		<td width="*" bgcolor="#FFFFFF" valign="top">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		<td valign=top>
		<div class="workplaceSt">
			<div  class="workplaceCn">
			<table width=731  border="0" cellpadding="0" cellspacing="0"><tr><td>
			<jsp:include page="<%=menueL2_jsp%>" flush="true" />
			<div>
							<!---========================== [View:WorkPlace:refrence] ===================================-->
				<jsp:include page="<%=currentjsp%>" flush="true" />

			</div>
			</td></tr></table>
				
		

		
		</div>
		</div>	
		</td></tr>
		<tr><td valign=top height=1 bgcolor="FFB20C"></td></tr>
		<tr><td valign=top  height=2 bgcolor="FFFFFF"></td></tr>
		
		
				<tr>
		<td valign=top>

			
			<div>
							<!---========================== [View:WorkPlace:refrence] ===================================-->
				
				
				<FIELDSET>

			<div  class="workplaceCn">
<jsp:include page="<%=reference_ext_jsp%>" flush="true" />

</div>
</FIELDSET> 
				
			</div>
			
		

		

		</td></tr>
		<tr><td valign=top height=1 bgcolor="FFB20C"></td></tr>
		<tr><td valign=top  height=1 bgcolor="FFFFFF"></td></tr>
		</table>
		
	


		</td>
	</tr>
</TABLE>



<!---========================== [Foot] ===================================-->
<TABLE width=1001 border="0" cellSpacing=1 cellPadding=1 bgcolor="#EEB405">
	<%
	long jspendtime = System.currentTimeMillis();
	%>
	<tr>
		<td  bgcolor="#068282">
		<font color="#FFFFFF">&nbsp;&nbsp;<%=FMFName%>:<%=FMAName%>:===&raquo; <%=currentjsp%></font></td>
		<td  bgcolor="#068282"><font color="#FFFFFF">&nbsp;&nbsp;运行时间,模块：<%=MRUNTIME%>ms &nbsp;JSP：<%=(jspendtime - jspstarttime)%>ms</font></td>
		<TD align=right  bgcolor="#068282">CopyRight IBM GBS</TD>
	</TR>
</TABLE>






<%
} catch (Exception e) {
%>

<!---========================== [View : Exception] ===================================-->

<table border="1" width=946 align=center bordercolorlight="#000000"
	cellspacing="0" cellpadding="0" bordercolordark="#000000">
	<tr>
		<td width="60%" bgcolor="#CCCCCC" bordercolorlight="#CCCCCC"
			bordercolordark="#CCCCCC"><font color="#222222">&nbsp;&nbsp;
		管理控制中心@IBM </font></td>
		<td width="40%" align="right" bgcolor="#CCCCCC"
			bordercolorlight="#CCCCCC" bordercolordark="#CCCCCC"><IMG
			border="0" src="<portletAPI:encodeURI path="/images/jsp/info.gif" />"
			width="16" height="14"><IMG border="0"
			src="<portletAPI:encodeURI path="/images/jsp/maximize.gif" />"
			width="16" height="14"></td>
	</tr>



	<tr>
		<td width="100%" colspan="2" height=100 bgcolor=ffffff
			bordercolorlight="#ffffff" bordercolordark="#ffffff" border=0>
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
