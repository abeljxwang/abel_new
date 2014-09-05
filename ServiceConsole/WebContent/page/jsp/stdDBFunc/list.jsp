<%@ page 
language="java"
contentType="text/html; charset=GB2312"
%>
<%@ page import="java.util.*" %>
<%@ taglib uri='/WEB-INF/tld/portlet.tld' prefix='portletAPI'%>

<portletAPI:init />

<%
Vector tnbamev=new Vector();
if(request.getAttribute("tbname")!=null)
	tnbamev=(Vector)request.getAttribute("tbname");
%>

<table border="1" width="665" bordercolorlight="#808080" cellspacing="0" cellpadding="0" bordercolordark="#ffffff" >
	<TBODY>
		<TR>
			<TD height=23 colspan=5>&nbsp; 数据表维护</TD>

		</TR>
		<%
		for(int i=0;i<tnbamev.size();i++){
			out.println("<TR bgcolor=ddddff>"+
			"<TD>"+i+"</TD>"+
			"<TD>表名</TD>"+
			"<TD colspan=2>"+tnbamev.elementAt(i)+"(表结构)</TD>"+
			"<TD colspan=1 align=right>"+
			"-</TD>"+
			"</TR>");
			
			out.println("<TR>"+
			"<TD width=8% >&nbsp;</TD>"+
			"<TD width=23% >增加记录</TD>"+
			"<TD width=23% >删除记录</TD>"+
			"<TD width=23% >修改记录</a></TD>"+
			"<TD width=23% >查寻记录</a></TD>	</TR>");
			out.println("<TR><td height=3 colspan=5>&nbsp;</td></tr>");
		
		}
		
		%>		

	</TBODY>
</TABLE>


