<%@ page language="java" contentType="text/html; charset=GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<script type="text/javascript" src="<portletAPI:encodeURI path="/js/switchcontent.js" />"> </script>
<script type="text/javascript" src="<portletAPI:encodeURI path="/js/switchicon.js" />"> </script>


<style>
<!--

.thd1
	{	
	border-right:none;
	border-bottom:.5pt solid #999999;
	border-left:none;
	border-top:.5pt solid #000000;
	padding: 1px;
	font-size: 10pt;
	text-align: left;
	background-color: #EEEEFF;
	COLOR: #000000;
	}

.x227
	{mso-style-parent:style0;
	color:black;
	font-size: 10pt;
	font-family:华文仿宋;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	text-align:right;
	background:white;
	mso-pattern:auto none;}
.x228
	{mso-style-parent:style0;
	color:black;
	font-size: 10pt;
	font-family:华文仿宋;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	background:white;
	mso-pattern:auto none;}

.Qinput {
	border-right:none;
	border-bottom:.5pt solid #000000;
	border-left:none;
	border-top:none;
	padding: 1px;
	font-size: 10pt;
	text-align: left;
	background-color: #EEEEFF;
	COLOR: #000000;
}

/*Default style for SPAN icons. Edit if desired: */

.iconspan{
float: right;
margin: 3px;
cursor:hand;
cursor:pointer;
font-weight: bold;
}

/*CSS used to style the examples. Remove if desired: */

.eg-bar{
background-color: #EEF5D3;
font-weight: bold;
border: 1px solid black;
padding: 3px;
}

div.eg-bar{
width: 500px;
}

.icongroup1{
width: 100%;
}


-->
</style>


<table border=0 cellpadding=0 cellspacing=0 width=100%>
 <tr>
  <td class=x227 width=80>业务类型:</td>
  <td class=x228 width=120 align=left><input type=text size=15 class="Qinput"></td>
  <td class=x227 width=80>业务来源:</td>
  <td class=x228 width=120><input type=text size=15 class="Qinput"></td>
    <td class=x227 width=80>业务状态:</td>
  <td class=x228 width=120  align=right><input type=text size=15 class="Qinput"></td>
  <td class=x227 width=20><span><img src="<portletAPI:encodeURI path="/images/icon/search.gif" />"></span></td>
  <td class=x227 width=><span id="faqtable2-title" class="iconspan"></span></td>
 </tr>
 </table>
<div id="faqtable2" class="icongroup2">
<table border=0 cellpadding=0 cellspacing=0 width=100%>
 <tr>
  <td class=x227 width=80>提交日期:</td>
  <td class=x228 width=120><input type=text size=15 class="Qinput"></td>
  <td class=x227 width=80>受理日期:</td>
  <td class=x228 width=120><input type=text size=15 class="Qinput"></td>
  <td class=x227 width=80>预约日期:<span  style='mso-spacerun:yes'>&nbsp;</span></td>
  <td class=x228 align=right width=120><input type=text size=15 class="Qinput"></td>
  <td class=x228 width=>-</td>
 </tr> 
 <tr>
  <td class=x227>付款账号:</td>
  <td class=x228 colspan=2><input type=text size=27 class="Qinput"></td>
  <td class=x227>收款账号:&nbsp;</td>
  <td class=x228 colspan=2 align=right><input type=text size=27 class="Qinput"></td>
  <td class=x228 width=>-</td>
 </tr>
 </table>
			</div>


<script type="text/javascript">

var faqtable=new switchicon("icongroup2", "div") //Limit scanning of switch contents to just "div" elements
faqtable.setHeader('[恢复]', '[高级查询]') //Set header HTML
faqtable.collapsePrevious(false) //Allow more than 1 content to be open simultanously
//faqtable.setPersist(true, 7) //Enable persistence to remember last switch content states for 7 days
faqtable.setPersist(false)
faqtable.init()

</script>


<table border=0 cellpadding=1 cellspacing=0 width=100% bgcolor=FAFAFA>
<tr class="thd1">
<td class="thd1" width="5%"> FD1</td>
<td class="thd1" width="5%"> FD2</td>
<td class="thd1" width="10%"> FD3</td>
<td class="thd1" width="20%"> FD4</td>
<td class="thd1" width="30%"> FD5</td>
<td class="thd1" width="20%"> FD6</td>
<td class="thd1" width="5%"> FD7</td>
<td class="thd1" width="5%"> FD8</td>
</tr>

<%


int b=0;
for(int i=0;i<15;i++)
{
	b=i%2;
	if(b==1)
	out.println("<tr  bgcolor=#E3EEF7>");
	else
		out.println("<tr>");
	
	for(int j=0;j<8;j++){
		if(j==0) out.println("<td>&nbsp;&nbsp;"+i*j+"</td>");
		else if(j==4) out.println("<td>&nbsp;&nbsp;"+i*j+"Tue Apr 08 13:07:06 CST 2008 </td>");
		else out.println("<td>"+i*j+"</td>");
		
	}
	out.println("</tr>");
		
}

%>


</table>
