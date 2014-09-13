<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<style type='text/css'>
.menue_s {
	font-family: "微软雅黑";
	font-size: 12pt;
	COLOR: #FFFFFF;
	text-align: center;
	width: 100px;
	border-right: 0.5px solid #AAAAAA;
	margin: 2px 2px;
}
.roundedCorners {
    border: 1px solid #888888;
    border-radius: 8px;
    font-size: 12pt;
    box-shadow: 0 0 2px blue;
    margin: 2px 4px;
    height: 25px;
    width: 200px;
    padding-left: 10px;
    
}


</style>

<table cellpadding="0" cellspacing="0" border="0" width="90%" height="100%">
	<tr>
		<td class="menue_s">
		最新
		</td>
		<td  class="menue_s">
		最热门
		</td>
		<td class="menue_s">
		I want
		</td>
		<td class="menue_s">
		I have
		</td>
		<td class="menue_s">
		SNS热点
		</td>
		<td class="menue_s">
		拼团
		</td>
		<td class="menue_s">
		我的海购
		</td>
		<td>		<input name=searchkey class="roundedCorners">
		</td>
	</tr>
</table>
