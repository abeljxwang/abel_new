<%@ page language="java" contentType="text/html; GBK" pageEncoding="GBK"%>

<%@ page import="java.util.*,org.abel.webapp.db.*,org.abel.webapp.common.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />


<link rel="StyleSheet" href="/ServiceConsole/theme/cbm.css" type="text/css" />


<%
	EIPResultSet dbresult = null;

	if (request.getAttribute("dbresult") != null) {
		dbresult = (EIPResultSet) request.getAttribute("dbresult");
	}
	Vector domain = new Vector();
	Vector catlist = new Vector();
	CbmCatProfile cptm;
	//catalogid,up_catalog,catalogname,catalogesc,biz_layer,biz_chain_id
	int tmp = 0;
	while (dbresult.next()) {
		cptm = new CbmCatProfile();
		//System.out.println(dbresult.getString(1)+"  "+dbresult.getString(2)+"  "+dbresult.getString(3)+"  "+dbresult.getString(4)+"  "+dbresult.getString(5)+"  "+dbresult.getString(6)+"  ");
		cptm.catalogid = dbresult.getString(1);
		cptm.up_catalog = dbresult.getString(2);
		cptm.catalogname = dbresult.getString(3);
		cptm.catalogesc = dbresult.getString(4);
		cptm.biz_layer = dbresult.getString(5);
		cptm.biz_chain_id = dbresult.getString(6);
		if (cptm.biz_chain_id.equals("B"))
			domain.add(cptm);
		else
			catlist.add(cptm);
	}
	
	//System.out.println("D: "+domain.size());
	//System.out.println(catlist.size());
%>




<table width="100%" height="300" border=0 cellpadding=0 cellspacing=0>
	<tr>
		<td align=center valign="bottom" height=50 style="font-size: 16pt; text-align: center; font-family: 微软雅黑; padding: 6px 6px;">CBM of Suning OverSea Business</td>
	</tr>
	<tr>
		<td align=center>


	<table border=0 cellpadding=0 cellspacing=0 >
		<tr>
			<td></td>
			<%
				for (int i = 0; i < domain.size(); i++) {
					
					CbmCatProfile bd = (CbmCatProfile) domain.elementAt(i);
					out.println("<td>");
					out.println("<div class=\"BD\">" + bd.catalogname + "</div>");
					out.println("</td>");

				}
			%>

		</tr>

		<tr>
			<td bgcolor="#006600" class="v_cat"> 决策
			</td>
			<%
				for (int i = 0; i < domain.size(); i++) {
					CbmCatProfile bd = (CbmCatProfile) domain.elementAt(i);
					String up = bd.catalogid;
					out
							.println("<td bgcolor=\"#DDFFDD\" class=\"cat_spac\" valign=top>");

					for (int j = 0; j < catlist.size(); j++) {
						CbmCatProfile cttmp = (CbmCatProfile) catlist.elementAt(j);
						if (cttmp.up_catalog.equals(up)
								&& cttmp.biz_layer.equals("0")) {
							out.println("<div class=\"FA\">" + cttmp.catalogname
									+ "</div>");
						}
					}

					out.println("&nbsp;</td>");

				}
			%>

		</tr>
		<tr>
			<td bgcolor="#aa4400" class="v_cat">管理</td>
			
			<%
				for (int i = 0; i < domain.size(); i++) {
					CbmCatProfile bd = (CbmCatProfile) domain.elementAt(i);
					String up = bd.catalogid;
					out
							.println("<td bgcolor=\"#FFEEDD\" class=\"cat_spac\"  valign=top>");

					for (int j = 0; j < catlist.size(); j++) {
						CbmCatProfile cttmp = (CbmCatProfile) catlist.elementAt(j);
						if (cttmp.up_catalog.equals(up)
								&& cttmp.biz_layer.equals("1")) {
							out.println("<div class=\"FA\">" + cttmp.catalogname
									+ "</div>");
						}
					}
					out.println("&nbsp;</td>");

				}
			%>

		</tr>
		<tr>
			<td bgcolor="#000066" class="v_cat">作业</td>
			<%
				for (int i = 0; i < domain.size(); i++) {
					CbmCatProfile bd = (CbmCatProfile) domain.elementAt(i);
					String up = bd.catalogid;
					out
							.println("<td bgcolor=\"#EEEEFF\" class=\"cat_spac\"  valign=top>");

					for (int j = 0; j < catlist.size(); j++) {
						CbmCatProfile cttmp = (CbmCatProfile) catlist.elementAt(j);
						if (cttmp.up_catalog.equals(up)
								&& cttmp.biz_layer.equals("2")) {
							out.println("<div class=\"FA\">" + cttmp.catalogname
									+ "</div>");
						}
					}

					out.println("&nbsp;</td>");

				}
			%>

		</tr>


	</table>


	</td>
	</tr>
</table>

