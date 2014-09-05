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




<body topmargin="0">
	<!---========================== [Header] ===================================-->

	<TABLE cellSpacing=0 cellPadding=0 width=100% border=0>
		<TR>
			<TD vAlign=top align=center width="100%">
				<TABLE cellSpacing=0 cellPadding=0 width="1200"
					background="<portletAPI:encodeURI path="/images/TB1.gif" />"
					border=0>
					<TR>
						<TD vAlign=top width="35%" rowSpan=3><IMG height=60
							src="<portletAPI:encodeURI path="/images/Hlogo.gif" />" width=339
							border=0></TD>
						<TD vAlign=top width="35%"></TD>
						<TD vAlign=bottom width="30%">
							<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
								<TR>
									<TD vAlign=top align=right><img
										src="<portletAPI:encodeURI path="/images/CompanyInfo.jpg" />"></TD>
								</TR>
								<TR>
									<TD vAlign=top align=right height=2></TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
					<TR>
						<TD vAlign=bottom colSpan=2></TD>
					</TR>
					<TR>
						<TD vAlign=bottom colSpan=2 height=3></TD>
					</TR>
				</TABLE>
		</TD>
		</TR>
	</TABLE>



	<%
		try {

			String top_left_jsp = "/page/jsp/common/top_left_jsp.jsp";
			String menueL2_jsp = "/page/jsp/common/menue_L2.jsp";
			String taskmgrjsp = "/page/jsp/common/task_mgr.jsp";
			String left_ext_jsp = "/page/jsp/common/left_ext.jsp";
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


	<TABLE cellSpacing=0 cellPadding=0 width=100% border=0>
		<TR>
			<TD vAlign=top align=center width="100%">
	<TABLE cellSpacing=0 cellPadding=0 width=1200 border=0 height="400" bgcolor="#BB99EE">
		<TR>
			<TD valign=top width="245">
				<!---========================== [View start] ===================================-->
				<table width="245" height="100%" border="0" cellpadding="0"
					cellspacing="0">
					<jsp:include page="<%=top_left_jsp%>" flush="true" />
					<tr>
						<td bgcolor="FFFFFF"></td>
						<td bgcolor="EEEEFF" valign="top">
							<!---========================== [View:TASK] ===================================-->
							<jsp:include page="<%=taskmgrjsp%>" flush="true" /> 
							<!---========================== [View:LEFT_EXT] ===================================-->	
							<!--  jsp:include page="<%=left_ext_jsp%>" flush="true" /-->
						</td>
					</tr>

				</table>
			</td>
			<td width="955" bgcolor="#CCCCCC" valign="top">
				<!---========================== [View:WorkPlace] ===================================-->
				<table cellpadding="0" cellspacing="1" border="0" width="100%"
					height="100%">
					<tr>
						<td bgcolor="#003399" colspan="8" height=16><jsp:include
								page="<%=menueL2_jsp%>" flush="true" /></td>
						<td bgcolor="#7f99cc"></td>

					</tr>
					<tr>
						<td bgcolor="#FFFFFF" colspan="8" valign="top"><jsp:include
								page="<%=currentjsp%>" flush="true" />
					</tr>
				</table>


			</td>
		</tr>
	</TABLE>
	</TD></TR></TABLE>



	<!---========================== [Foot] ===================================-->
		<TABLE cellSpacing=0 cellPadding=0 width=100% border=0>
		<TR>
			<TD vAlign=top align=center width="100%">
	<TABLE width=1200 bgColor=#cccccc border="0">
		<%
			long jspendtime = System.currentTimeMillis();
		%>
		<tr>
			<td bgcolor="#88bbee" bordercolorlight="#88bbee"
				bordercolordark="#000000"><font color="#FFFFFF">&nbsp;&nbsp;<%=FMFName%>:<%=FMAName%>:
					===&raquo; <%=currentjsp%></font></td>
			<td bgcolor="#88bbee" bordercolorlight="#88bbee"
				bordercolordark="#000000"><font color="#FFFFFF">&nbsp;&nbsp;
					运行时间,模块：<%=MRUNTIME%>ms &nbsp;JSP：<%=(jspendtime - jspstarttime)%>ms
			</font></td>
		</tr>
		<TR>
			<TD align=center colspan=2>CopyRight Suning Commerce R&D USA</TD>
		</TR>
	</TABLE>

</TD></TR></TABLE>




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
				border="0"
				src="<portletAPI:encodeURI path="/images/jsp/info.gif" />"
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
