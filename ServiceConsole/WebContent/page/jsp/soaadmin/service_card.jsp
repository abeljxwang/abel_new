<%@ page language="java" contentType="text/html; charset=GB2312"%>

<%@ page
	import="java.util.*,org.abel.service.soa.message.*,org.abel.webapp.common.CoreDataCacheManager,java.text.DateFormat"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<link rel=Stylesheet href=/ServiceConsole/theme/stylesheet.css>
<script src="/ServiceConsole/js/jquery/jquery-1.10.2.js"></script>
<script src="/ServiceConsole/js/jquery/jquery.PrintArea.js"></script>

<script language=JavaScript type=text/javascript>
	


	
	printDivCSS = new String ('<META http-equiv="Content-Type" content="text/html; charset=GB2312"><link href="/ServiceConsole/theme/stylesheet_p.css" rel="stylesheet" type="text/css">')

    
	function printDiv(divId) {

	    window.frames["print_frame"].document.body.innerHTML=printDivCSS + document.getElementById(divId).innerHTML;
	    window.frames["print_frame"].window.focus();
	    window.frames["print_frame"].window.print();
	}
	
	function onSubmitForm() {

		document.inputform.action = "/ServiceConsole/ET/SOAAdmin/serviceCard";
		return true;
	}
</script>

<%
	String sid = "";
	request.getParameter("ServiceID");
	if (request.getParameter("ServiceID") != null)
		sid = request.getParameter("ServiceID");
	System.out.println(sid + "-------------------------");
	DescCacheManager dm = DescCacheManager.getInstance();
	ServiceDesc sdsc = dm.getServiceDesc(sid); //.getOperation("query_Order_Info").getInputName());
	Vector opkeys = new Vector();
	if (sdsc != null)
		opkeys = sdsc.getAllOperationKeys();
	Date now=new Date();
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	String now_str=df.format(now);

	//System.out.println(dm.getMessageDesc("E100001"));
%>


<table border=0 cellpadding=2 cellspacing=2 width=100%>
	<tr>
		<td align=right>  <a href="javascript:printDiv('myPrintArea')">Print >>>> </a><br></td></tr>
		<tr><td align=center>
			<div id="myPrintArea">
			<table width=100%>
			<tr>
			<td width=100 align=center>
			
			

				<table border=0 cellpadding=2 cellspacing=0 width=880>
					<tr height=30>
						<td colspan=7 align=center valign=middle style="font-size: 16pt; text-align: center; font-family: 微软雅黑; padding: 6px 6px;">
						功能(服务)设计卡	（Service Card）</td>
					</tr>
					<tr>
						<td  align=right colspan=7 class=now_str><%=now_str %></td>
					</tr>
					<tr style='mso-height-source: userset; height: 2.75pt'>
						<td height=5 colspan=7 style='height: 2pt; mso-ignore: colspan'></td>
					</tr>
					
					<tr height=23 style='height: 17.25pt'>
						<td class=xl96 colspan=3 width=160 style='height: 17.25pt; border-left: 1pt solid black; border-right: 1pt solid black'>ServiceName:</td>
						<td class=xl96 width=160  style='border-left: 0pt solid black;border-right: 1pt solid black'>名称/Name</td>
						<td class=xl96 colspan=3 width=555 style='border-left: 0pt solid black;border-right: 1pt solid black'>服务描述/Description</td>
					</tr>
					<tr height=21 style='height: 35pt'>

						<td class=xl105 valign=top colspan=3 style='background:#ffffff;border-bottom: 1pt solid black;border-right: 1pt solid black; border-left: 1pt solid black; border-top: 0pt'>&nbsp;
							<%
								if (sdsc != null)
									out.print(sdsc.getName());
							%>
						</td>

						<td class=xl106 valign=top colspan=1
							style='border-bottom: 1pt solid black'>&nbsp; 
							<%
							if (sdsc != null)
								out.print(sdsc.getChineseName());
							%>
						</td>

						<td colspan=3 class=xl106 valign=top
							style='border-right: 1pt solid black; border-bottom: 1pt solid black'>
							<%
								if (sdsc != null)
									out.print(sdsc.getDesc());
							%>
						</td>
					</tr>

					<tr class=xl95 valign=bottom>

						<td class=xl111></td>
						<td class=xl96 colspan=6 style='border-top: 0pt; border-left: 0pt; border-right: 1pt solid black '>&nbsp;Operations</td>
					</tr>
					
					<!-- ----for Message operation round process start----- -->
					<%!public String tag_a = "&nbsp;&nbsp;";
						public String tag_b = "&nbsp;";
						String printNameLine(OperationDesc op,ServiceDesc sdsc) {
							String result = "";
							result = "<tr height=5>"
				+"<td style='border-top: 0pt; border-left: 1pt solid black;'></td><td colspan=6 height=5 style='border-top: 0pt;border-right: 1pt solid black '>　</td>"
				+ "</tr>"; 
		result = result
				+"<tr height=20>"
				+ "<td class=xl115 >&Oslash;</td>"
				+ "<td colspan=2 class=xl105 style='background:#CCEEFF' > &nbsp;"
				+ op.getOperationName() + "</td>" 
				+ "<td class=xl105 style='border-right:0pt' >&nbsp; "
				+  "</td>" 
				+ "<td class=xl105 colspan=3 style='border-right: 1pt solid black;font-family:sans-serif;font-size:8.0pt; font-weight:400;' align=right> <a href='/ServiceConsole/ET/SOAAdmin/ReqResRef?opid="
				+op.getOperationName()+"&sid="
				+sdsc.getName()
				+"'>View the Message Sample  >>[JSON]</a>&nbsp;&nbsp; 　</td>"
				+ "</tr>"; 
		return result;
	}

	String printOPInput(OperationDesc op, DescCacheManager dm) {
		tag_b = "|-";
		String inputid = op.getInputName();
		ElementDesc input = dm.getMessageDesc(inputid);
		String resulttitle = "<tr>\n"
				+ " <td class=xl119 width=15>　</td>\n"
				+ " <td class=xl120 width=55>　</td>\n"
				+ " <td class=xl121 width=80>input</td>\n"
				+ " <td class=xl122>记录/Item</td>\n"
				+ " <td class=xl122>记录/Item</td>\n"
				+ " <td class=xl122>样本数据/Sample Data</td>\n"
				+ " <td class=xl123>Note</td>\n"
				 + "</tr>";
		return resulttitle + printElement(input, dm, tag_b);
	}

	String printOPOutput(OperationDesc op, DescCacheManager dm) {
		tag_b = "|-";
		String outputid = op.getOutputName();
		ElementDesc output = dm.getMessageDesc(outputid);
		String resulttitle = "<tr class=xl41 height=15 style='height:11.25pt'>\n"
				+ " <td class=xl119>　</td>\n"
				+ " <td class=xl120>　</td>\n"
				+ " <td class=xl121>output</td>\n"
				+ " <td class=xl122>记录/Item</td>\n"
				+ " <td class=xl122>记录/Item</td>\n"
				+ " <td class=xl122>样本数据/Sample Data</td>\n"
				+ " <td class=xl123>Note</td>\n"
				+ "</tr>";
		return resulttitle + printElement(output, dm, tag_b);
	}

	String printElement(ElementDesc elemt, DescCacheManager dm, String tag) {
		StringBuffer result = new StringBuffer();
		for (int j = 0; j < elemt.getMetaAcount(); j++) {
			MetaDesc tmpmtdsc = elemt.getMetaDesc(j);
			if (!tmpmtdsc.isSubEle())
				result.append("<tr class=xl41 height=15 style='height:11.25pt'>\n"
						+ " 	<td class=xl119>　</td>\n"
						+ "   <td class=xl120>　</td>\n"
						+ "   <td class=xl124>　</td>\n"
						+ "   <td class=xl125>"
						+ tag
						+ tmpmtdsc.getCN_Name()
						+ "</td>\n"
						+ "   <td class=xl43>"
						+ tag
						+ tmpmtdsc.getName()
						+ "</td>\n"
						+ "   <td class=xl43>&nbsp;"
						+ tmpmtdsc.getDF_Value()
						+ "</td>\n"
						+ "   <td class=xl126>&nbsp;"
						+ tmpmtdsc.getType()+"/"+tmpmtdsc.getRpt()
								+ "</td>\n" +  " </tr>");

			else {
				ElementDesc tmpe = dm.getMessageDesc(tmpmtdsc.getName());
				result.append("<tr class=xl41 height=15 style='height:11.25pt'>\n"
						+ " 	<td class=xl119>　</td>\n"
						+ "   <td class=xl120>　</td>\n"
						+ "   <td class=xl124>　</td>\n"
						+ "   <td class=xl125>"
						+ tag
						+ tmpe.getName()
						+ "</td>\n"
						+ "   <td class=xl43>"
						+ tag
						+ tmpe.getName()
						+ "</td>\n"
						+ "   <td class=xl43> --- </td>\n"
						+ "   <td class=xl126>"
						+ tmpmtdsc.getName()+"/"+tmpmtdsc.getRpt()
						+ "</td>\n" +  " </tr>");
				tag_b = tag_a + tag_b + "";
				String tmp = printElement(tmpe, dm, tag_b);
				result.append(tmp);

			}
		}

		return result.toString();

	}

	String printEndString() {

		String resulttitle = "<tr>\n"
				+ " 	<td class=xl119>　</td>\n"
				+ "   <td class=xl120 style='border-bottom:1.0pt solid #6666BB;'>　</td>\n"
				+ "   <td class=xl124 style='border-bottom:1.0pt solid #6666BB;'>　</td>\n"
						+ "   <td class=xl1251 colspan=4> </td>\n"
				+ " </tr>";
		return resulttitle;

	}%>


					<%
						String tmpkey = null;
						OperationDesc opdsctmp = null;

						for (int i = 0; i < opkeys.size(); i++) {
							tmpkey = opkeys.elementAt(i) + "";
							opdsctmp = sdsc.getOperation(tmpkey);
							out.println(printNameLine(opdsctmp,sdsc));
							out.println(printOPInput(opdsctmp, dm));
							
							out.println(printOPOutput(opdsctmp, dm));
							out.println(printEndString());

						}
					%>



					<!-- ----for Message operation round process end ----- -->

					<tr>
						<td class=xl150 colspan=7></td>

					</tr>
					<tr class=xl95 height=9
						style='mso-height-source: userset; height: 6.75pt'>
						<td colspan=7 class=xl116 style='mso-ignore: colspan'></td>

					</tr>
					<tr>
						<td class=xl155></td>
						<td class=xl157>状态</td>
						<td class=xl157 colspan=2>考核点</td>
						<td class=xl157>审核人</td>
						<td class=xl157>时间</td>
						<td class=xl158>说明</td>

					</tr>
					<tr>
						<td class=xl159></td>
						<td class=xl164>[&nbsp;&nbsp;&nbsp; ]</td>
						<td class=xl166 colspan=2 >建立元数据 </td>
						<td class=xl166></td>
						<td class=xl166></td>
						<td class=xl167></td>

					</tr>
					<tr>
						<td class=xl159></td>
						<td class=xl164>[<span
							style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp; </span>]</td>
						<td class=xl166 colspan=2>录入样本数据
						</td>
						<td class=xl166></td>
						<td class=xl166></td>
						<td class=xl167></td>

					</tr>
					<tr>
						<td class=xl159></td>
						<td class=xl164>[<span
							style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp; </span>]</td>
						<td class=xl166 colspan=2>数据库表创建<span
							style='mso-spacerun: yes'>&nbsp;</span></td>
						<td class=xl166></td>
						<td class=xl166></td>
						<td class=xl167></td>

					</tr>
					<tr>
						<td class=xl159></td>
						<td class=xl164>[<span
							style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp; </span>]</td>
						<td class=xl166 colspan=2>UI完成<span
							style='mso-spacerun: yes'>&nbsp;</span></td>
						<td class=xl166></td>
						<td class=xl166></td>
						<td class=xl167></td>

					</tr>
					<tr class=xl145 height=17 style='height: 12.75pt'>
						<td class=xl159></td>
						<td class=xl164>[<span
							style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp; </span>]</td>
						<td class=xl166 colspan=2>功能完成
						</td>
						<td class=xl166></td>
						<td class=xl166></td>
						<td class=xl167></td>

					</tr>
					<tr class=xl145 height=23
						style='mso-height-source: userset; height: 17.25pt'>
						<td class=xl172></td>
						<td class=xl166 style="border-bottom:1.0pt solid windowtext;">[<span
							style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp; </span>]</td>
						<td class=xl174 colspan=2>最终审核</td>

						<td class=xl166 style="border-bottom:1.0pt solid windowtext;"></td>
						<td class=xl166 style="border-bottom:1.0pt solid windowtext;"></td>
						<td class=xl167 style="border-bottom:1.0pt solid windowtext;"></td>

					</tr>
				</table>
				</td>
			</tr>
			</table>
			</div></td>
	</tr>
</table>
<iframe name="print_frame" width="0" height="0" frameborder="0" src="about:blank"></iframe>

<table width=100%><tr><td align=center style="font-size: 10pt; text-align: center; font-family: 微软雅黑; padding: 6px 6px;">
<form name=inputform method=post action="/ServiceConsole/ET/SOAAdmin/serviceCard">
	查询:[ <input name=ServiceID id=ServiceID type=text size=20 style="border-left:0pt solid #DDDDDD;border-right:0pt solid #DDDDDD;border-top:0pt solid #DDDDDD;border-bottom:1pt solid #999999;background:#FEFEff">] 
	<input type="submit" value="CK" style="font-size: 10pt; text-align: center; font-family: 微软雅黑; padding: 1px 20px; height: 16pt; border-radius: 8pt"/>
</form>
</td></tr></table>

