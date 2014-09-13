<%@ page language="java" contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.*,org.abel.webapp.db.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />


<style>
<!--
.thd2 {
	border-right: none;
	border-bottom: none;
	border-left: none;
	border-top: 1pt solid #000000;
	padding: 1px;
	font-size: 10pt;
	text-align: left;
	background-color: #EEEEFF;
	COLOR: #000000;
}

.thd22 {
	border-right: none;
	border-bottom: 1pt solid #000000;
	border-left: none;
	border-top: none;
	padding: 0px;
	font-size: 12pt;
	text-align: left;
	background-color: #FFFFFF;
	COLOR: #000000;
	font-family: 华文楷体;
}

.thd12 {
	border-right: none;
	border-bottom: none;
	border-left: none;
	border-top: none;
	padding: 2px;
	font-size: 11pt;
	text-align: left;
	background-color: #FAFAFF;
	COLOR: #000000;
	font-family: 华文楷体;
}

.x227 {
	mso-style-parent: style0;
	color: black;
	font-size: 10pt;
	font-family: 华文仿宋;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	text-align: left;
	mso-pattern: auto none;
	border-bottom: 1pt solid #aaaaaa;
}

.x228 {
	mso-style-parent: style0;
	color: black;
	font-size: 11pt;
	font-family: 华文楷体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-pattern: auto none;
}


}

/*Default style for SPAN icons. Edit if desired: */
.iconspan {
	float: right;
	margin: 3px;
	cursor: hand;
	cursor: pointer;
	font-weight: bold;
}

/*CSS used to style the examples. Remove if desired: */
.eg-bar {
	background-color: #EEF5D3;
	font-weight: bold;
	border: 1px solid black;
	padding: 3px;
}

div.eg-bar {
	width: 500px;
}

.icongroup1 {
	width: 100%;
}
-->
</style>


<SCRIPT language=JavaScript type=text/javascript>
   var dsp=false;
   
function onSubmitForm() {
   //grid.updateFromXMl(url);
   //grid.updateFromXML(url);
   	mygrid.clearAll();
   	// var p = document.getElementById("debug")
		//p.innerHTML = "<li>"+document.qform.systemAbbr.value+"</li>"; 
		//p.innerHTML = p.innerHTML + document.getElementById('qform_abbr').value;
	mygrid.loadXML("<portletAPI:encodeURI path='/XMLENG/System/list' />?systemAbbr="+document.getElementById('qform_abbr').value);
	
	  return true;
   
   }
 
function onSubmitFormDel()
	{
 	  document.add_form.action ='<portletAPI:createURI><portletAPI:URIAction name="delS"/></portletAPI:createURI>';
 	  document.add_form.submit();
	  return true;
	}	
   

function placeForm() {
     if(!dsp){
     document.getElementById("input_form").style.display="";//显
     var tbl = document.getElementById("tbb1");
   	 if(tbl.rows[0].cells[1]!=null) document.add_form.sysID.value=tbl.rows[0].cells[1].innerHTML;	 
      
     }
     else {
     document.getElementById("input_form").style.display="none";// !显     
     }
          dsp=!dsp;
     return;
    }
   
  
</script>


<table width=100%>
	<tr>
		<td colspan=2 class="thd22">系统基本资料</td>
	</tr>
	<tr>
		<td width=50% valign=top>
		<div id="tmp1" style="height: 25; width: 300;border: 0px solid #ff9999;overflow:hidden;"><form name="qform" id="qform">
		
		<table border=0 cellpadding=0 cellspacing=0 width=300>			
			<tr>
				<td width=80 class=x228>系统类型:</td>
				<td align=right class=x228 width=200><input name="systemAbbr"
					type=text size=15 class="Qinput" id="qform_abbr"></td>
				<td width=20 align=left><img
					src="<portletAPI:encodeURI path="/images/icon/search.gif" />"
					onClick="onSubmitForm()"></td>
			</tr>
		</table>
       </form></div>




		<div id="gridbox" style="height: 300; width: 300;"></div>
		<script>
		function protocolIt(str){
		 var p = document.getElementById("debug")
		p.innerHTML = "<li>"+str+"</li>"; 
		
		makeRequest('<portletAPI:encodeURI path="/XMLENG/System/detail" />',str);
		
	}
	function doOnRowSelected(rowID,celInd){
	
		var stt=this.cells(rowID,0).getValue();
		protocolIt("sysID=" + stt);
	}

	var mygrid = new dhtmlXGridObject('gridbox');
	
	
	
	
	// or 
	//mygrid = new dhtmlXGridObject();
	//mygrid.attachToObject(document.body)
	mygrid.imgURL = "/NGSCP/images/imgs/";
	mygrid.setHeader("ID,标识,中文名");
	mygrid.setInitWidths("50,50,*")
	mygrid.setColAlign("right,left,left")
	mygrid.setColTypes("ro,ro,ro");
	mygrid.setColSorting("int,str,str")
	mygrid.setSkin("light");
	mygrid.attachEvent("onRowSelect",doOnRowSelected);
	mygrid.init();
	mygrid.loadXML("<portletAPI:encodeURI path='/XMLENG/System/list' />");

</script> DEBUG Info:
		<div id="debug"
			style="width: 300px; height: 20px; overflow: auto; border: 1px solid #ff9999;"></div>


		</td>
		<td width=50% valign=top class="thd12"><div>详细信息：    &nbsp;&nbsp; &nbsp;<a onclick="placeForm();"> 编辑 </a> </div>
		<div id="protocol"
			style="width: 350px; height: 268px; overflow: auto; border: 1px solid #BBCCEE;">
			
			<form name="add_form" method=post
			action="<portletAPI:createURI><portletAPI:URIAction name="addS"/></portletAPI:createURI>">
		<div id="input_form"
			style="Z-INDEX: 12000; width: 100%; height: 100%; POSITION: absolute; display: none;">
		<table width="100%" height="100%">
			<tr align="center" valign="middle">
				<td>
				<table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%" bgcolor="#FFFFFF" style="FILTER: Alpha(Opacity = 99);">
					<tr>
						<td align=left colspan=1>ID: <br><input type=text name="sysID"
							size=6  class="Qinput"></td>
						<td align=left colspan=1>简称:<br><input type=text
							name="systemAbbr" size=10 class="Qinput"></td>	
						<td align=left colspan=1>投产日期: <br><input type=text
							name="systemProdDate" size=10 class="Qinput"></td>
						<td align=left colspan=1>版本号: <br><input type=text
							name="systemVersion" size=6 class="Qinput"></td>
					</tr>
					<tr>
						<td align=left colspan=4>英文名称<br><input type=text
							name="systemName" size=45 class="Qinput"></td>
					</tr>
					<tr>
						<td align=left colspan=2>中文名称<br><input type=text
							name="sysyemCName" size=20 class="Qinput"></td>
						<td align=left colspan=2>开发团队: <br><input type=text
							name="systemDeveloper" size=20 class="Qinput"></td>
					</tr>
					<tr>
						<td align=left colspan=4>systemDesc: <br>
							<textarea cols="50" rows="5" id="systemDesc" name="systemDesc" class="Qinput"></textarea>
							</td>
					</tr>

					<tr>
						<td align=left colspan=2>内码: <input type=text
							name="systemInCode" size=8 class="Qinput"></td>
						<td align=center><INPUT type="submit"
							value="插入记录"> &nbsp;</td>
						<td align=center><INPUT class="ButtonType"
					type="button" onclick="onSubmitFormDel();" value="删除记录"> &nbsp;</td>
					</tr>

				</table>
				</td>
			</tr>

		</table>
		</div>
		</form>

		<table width=97% id="tbb1">
			<tr>
				<td width=20% class=x228>系统码</td>
				<td width=80% class=x227 id="e_code_c">&nbsp;</td>
			</tr>
			<tr>
				<td valign=top class=x228>名称</td>
				<td class=x227>&nbsp;<br>
				</td>
			</tr>
			<tr>
				<td valign=top class=x228>缩写</td>
				<td class=x227>&nbsp;<br>
				</td>
			</tr>
			<tr>
				<td valign=top class=x228>投产日期</td>
				<td class=x227>&nbsp;<br>
				</td>
			</tr>
			<tr>
				<td width=15% class=x228>中文名称</td>
				<td width=85% class=x227 id="e_code_c">&nbsp;</td>
			</tr>
			<tr>
				<td valign=top class=x228>系统描述</td>
				<td class=x227>&nbsp;<br>
				</td>
			</tr>
			<tr>
				<td valign=top class=x228>开发单位</td>
				<td class=x227>&nbsp;<br>
				</td>
			</tr>
			<tr>
				<td valign=top class=x228>版本号</td>
				<td class=x227>&nbsp;<br>
				</td>
			</tr>
			<tr>
				<td valign=top class=x228>内部编码</td>
				<td class=x227>&nbsp;<br>
				</td>
			</tr>			
		</table>

		</div>
		辅助功能<br>
		<div id="funcext"
			style="height: 65; width: 350; border: 1px solid #8888AA; background-color: #FFFFFF;"
			align=right>
		<table>
			<tr>
				<td>
				<div style="width: 50; height: 60"><a>DWN:XLS<img
					src="<portletAPI:encodeURI path="/images/files/xls.gif" />"
					border=0></a></div>
				</td>
				<td>
				<div style="width: 50; height: 60"><a>
				DWN:PDF<img
					src="<portletAPI:encodeURI path="/images/files/pdf.gif" />"
					border=0></a></div>
				</td>
				<td>
				<div style="width: 50; height: 60"><img
					src="<portletAPI:encodeURI path="/images/files/printer.gif" />"></div>
				</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
</table>
