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
			<TD height=23 colspan=5>&nbsp; ���ݱ�ά��</TD>

		</TR>
		<%
		for(int i=0;i<tnbamev.size();i++){
			out.println("<TR bgcolor=ddddff>"+
			"<TD>"+i+"</TD>"+
			"<TD>����</TD>"+
			"<TD colspan=2>"+tnbamev.elementAt(i)+"(��ṹ)</TD>"+
			"<TD colspan=1 align=right>"+
			"-</TD>"+
			"</TR>");
			
			out.println("<TR>"+
			"<TD width=8% >&nbsp;</TD>"+
			"<TD width=23% >���Ӽ�¼</TD>"+
			"<TD width=23% >ɾ����¼</TD>"+
			"<TD width=23% >�޸ļ�¼</a></TD>"+
			"<TD width=23% >��Ѱ��¼</a></TD>	</TR>");
			out.println("<TR><td height=3 colspan=5>&nbsp;</td></tr>");
		
		}
		
		%>		

	</TBODY>
</TABLE>


