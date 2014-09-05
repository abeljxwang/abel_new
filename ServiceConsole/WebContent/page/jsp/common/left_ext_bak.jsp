
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI" %>

<portletAPI:init />


<style type="text/css">
.showstate { /*Definition for state toggling image */
	cursor: hand;
	cursor: pointer;
	float: right;
	margin-top: 2px;
	margin-right: 3px;
}

.headers {
	width: 230px;
	font-size: 100%;
	font-weight: bold;
	border: 1px solid black;
	background-color: #003399;
}

.switchcontent {
	width: 230px;
	border: 1px solid black;
	border-top-width: 0;
}
</style>

<script type="text/javascript">

/***********************************************
* Switch Content script II- ? Dynamic Drive (www.dynamicdrive.com)
* This notice must stay intact for legal use. Last updated April 2nd, 2005.
* Visit http://www.dynamicdrive.com/ for full source code
***********************************************/

var enablepersist="on" //Enable saving state of content structure using session cookies? (on/off)
var memoryduration="7" //persistence in # of days

var contractsymbol='<portletAPI:encodeURI path="/images/minus.gif" />' //Path to image to represent contract state.
var expandsymbol='<portletAPI:encodeURI path="/images/plus.gif" />' //Path to image to represent expand state.

/////No need to edit beyond here //////////////////////////

function getElementbyClass(rootobj, classname){
var temparray=new Array()
var inc=0
var rootlength=rootobj.length
for (i=0; i<rootlength; i++){
if (rootobj[i].className==classname)
temparray[inc++]=rootobj[i]
}
return temparray
}

function sweeptoggle(ec){
var inc=0
while (ccollect[inc]){
ccollect[inc].style.display=(ec=="contract")? "none" : ""
inc++
}
revivestatus()
}


function expandcontent(curobj, cid){
if (ccollect.length>0){
document.getElementById(cid).style.display=(document.getElementById(cid).style.display!="none")? "none" : ""
curobj.src=(document.getElementById(cid).style.display=="none")? expandsymbol : contractsymbol
}
}

function revivecontent(){
selectedItem=getselectedItem()
selectedComponents=selectedItem.split("|")
for (i=0; i<selectedComponents.length-1; i++)
document.getElementById(selectedComponents[i]).style.display="none"
}

function revivestatus(){
var inc=0
while (statecollect[inc]){
if (ccollect[inc].style.display=="none")
statecollect[inc].src=expandsymbol
else
statecollect[inc].src=contractsymbol
inc++
}
}

function get_cookie(Name) { 
var search = Name + "="
var returnvalue = "";
if (document.cookie.length > 0) {
offset = document.cookie.indexOf(search)
if (offset != -1) { 
offset += search.length
end = document.cookie.indexOf(";", offset);
if (end == -1) end = document.cookie.length;
returnvalue=unescape(document.cookie.substring(offset, end))
}
}
return returnvalue;
}

function getselectedItem(){
if (get_cookie(window.location.pathname) != ""){
selectedItem=get_cookie(window.location.pathname)
return selectedItem
}
else
return ""
}

function saveswitchstate(){
var inc=0, selectedItem=""
while (ccollect[inc]){
if (ccollect[inc].style.display=="none")
selectedItem+=ccollect[inc].id+"|"
inc++
}
if (get_cookie(window.location.pathname)!=selectedItem){ //only update cookie if current states differ from cookie's
var expireDate = new Date()
expireDate.setDate(expireDate.getDate()+parseInt(memoryduration))
document.cookie = window.location.pathname+"="+selectedItem+";path=/;expires=" + expireDate.toGMTString()
}
}

function do_onload(){
uniqueidn=window.location.pathname+"firsttimeload"
var alltags=document.all? document.all : document.getElementsByTagName("*")
ccollect=getElementbyClass(alltags, "switchcontent")
statecollect=getElementbyClass(alltags, "showstate")
if (enablepersist=="on" && get_cookie(window.location.pathname)!="" && ccollect.length>0)
revivecontent()
if (ccollect.length>0 && statecollect.length>0)
revivestatus()
}

if (window.addEventListener)
window.addEventListener("load", do_onload, false)
else if (window.attachEvent)
window.attachEvent("onload", do_onload)
else if (document.getElementById)
window.onload=do_onload

if (enablepersist=="on" && document.getElementById)
window.onunload=saveswitchstate

</script>



<!--Optional Expand/ Contact All links. Remove if desired-->

<div class="headers"><img src="<portletAPI:encodeURI path="/images/minus.gif" />" class="showstate"
	onClick="expandcontent(this, 'sc1')" /> 股市信息</div>
<div id="sc1" class="switchcontent">
<br>

<table width="100%" cellpadding="2" cellspacing="1" border="0">
	<tr><td class="yfnc_tablehead1" width="52%">最近交易价:</td><td class="yfnc_tabledata1"><big><b>10.42</b></big></td></tr>
	<tr><td class="yfnc_tablehead1" width="52%">交易时间:</td><td class="yfnc_tabledata1">上午10:52 </td></tr>
	<tr><td class="yfnc_tablehead1" width="52%">涨跌:</td><td class="yfnc_tabledata1"><img width="10" height="14" border="0" src="http://cn.yimg.com/i/cn/fi/03rd/up_r.gif" alt="涨"> <b style="color:#cc0000;">0.16 (1.56%)</b></td></tr>
	<tr><td class="yfnc_tablehead1" width="52%">昨日收盘:</td><td class="yfnc_tabledata1">10.26</td></tr>
	<tr><td class="yfnc_tablehead1" width="52%">开盘:</td><td class="yfnc_tabledata1">10.48</td></tr>
	<tr><td class="yfnc_tablehead1" width="52%">买入:</td><td class="yfnc_tabledata1">10.42</td></tr>
	<tr><td class="yfnc_tablehead1" width="52%">卖出:</td><td class="yfnc_tabledata1">10.44</td></tr>
	<tr><td class="yfnc_tablehead1" width="54%">今日价格幅度:</td><td class="yfnc_tabledata1">10.34 - 10.48</td></tr>
	<tr><td class="yfnc_tablehead1" width="54%">52周价格幅度:</td><td class="yfnc_tabledata1">4.00 - 7.45</td></tr>
	<tr><td class="yfnc_tablehead1" width="54%">成交量:</td><td class="yfnc_tabledata1">29,327,209</td></tr>
	<tr><td class="yfnc_tablehead1" width="54%">平均成交量<small>(3个月)</small>:</td><td class="yfnc_tabledata1">111,547,000</td></tr>
	<tr><td class="yfnc_tablehead1" width="54%">市值:</td><td class="yfnc_tabledata1">2,198.51亿</td></tr>
	<tr><td class="yfnc_tablehead1" width="54%">市盈率<small>(12个月)</small>:</td><td class="yfnc_tabledata1">无</td></tr>
	<tr><td class="yfnc_tablehead1" width="54%">每股收益<small>(12个月)</small>:</td><td class="yfnc_tabledata1">0.00</td></tr>
	<tr><td class="yfnc_tablehead1" width="52%">股利和股息:</td><td class="yfnc_tabledata1">0.305()</td></tr>
</table>



</div>




<div class="headers"><img src="<portletAPI:encodeURI path="/images/minus.gif" />" class="showstate"
	onClick="expandcontent(this, 'sc2')" /> 天气预报</div>
<div id="sc2" class="switchcontent">天气预报</div>
<br />