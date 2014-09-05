
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<style>

.xl24
	{
	font-size:11.0pt;
	font-family:华文楷体;
	mso-generic-font-family:auto;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid #009999;
	border-left:none;}
.xl25
	{
	font-size:8.0pt;
	font-family:Wingdings;
	mso-generic-font-family:auto;
	mso-font-charset:2;
	border-top:0.0pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid #E0E0E0;
	border-left:none;}

.xl27
	{
	font-size:9.0pt;}
.xl29
	{mso-style-parent:style0;
	font-size:8.0pt;
	font-family:Verdana, sans-serif;
	mso-font-charset:0;
	mso-number-format:"Short Time";
	border-top:0.0pt solid teal;
	border-right:none;
	border-bottom:.5pt solid #E0E0E0;
	border-left:none;}

.xl31
	{mso-style-parent:style0;
	font-size:8.0pt;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	border-top:0.0pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid #E0E0E0;
	border-left:none;}



</style>
	<link rel="STYLESHEET" type="text/css" href="/MVC4CPF/theme/css_dhtmlsuite/dhtmlXTabbar.css">
	<script  src="/MVC4CPF/js/dhtmlx/dhtmlXCommon.js"></script>
	<script  src="/MVC4CPF/js/dhtmlx/dhtmlXTabbar.js"></script>
	<script  src="/MVC4CPF/js/dhtmlx/dhtmlXTabbar_start.js"></script>
	
<div id="a_tabbar" style="width:230; height:310;"/>		</div>

<SCRIPT language=JavaScript type=text/javascript>

    function doStart() {    
       	setTimeout("pollServer(tabbar)", 50000);    
        refreshTime();      
    }    
 
   
    function pollServer() {    
        pollCallback();     
    }    
        
    function refreshTime(){    
        var time_val = 30;    
        var int_val = parseInt(time_val);    
        var new_int_val = int_val - 1;    
        setTimeout("refreshTime()", 50000);     
    }
    
    function pollCallback() {
                var table = document.getElementById("schedulecontent"); 
                var vNum
				vNum = Math.random()
				vNum = Math.round(vNum*10)
                setRowValue(vNum);       
                setTimeout("pollServer()",50000);    
                refreshTime();    
 }    
 
            var tabbar=null;
            tabbar=new dhtmlXTabBar("a_tabbar","top");
            tabbar.setImagePath("/MVC4CPF/images/imgs/");
 			tabbar.setHrefMode("ajax-html");
			tabbar.setSkinColors("#FCFBFC","#F4F3EE");
			tabbar.addTab("a1","待办业务","60px");
			tabbar.addTab("a2","常用功能速查","80px");
			
			tabbar.enableAutoSize(false,false);
			tabbar.setContentHref("a1","/MVC4CPF/page/jsp/task/task1.jsp");
			tabbar.setContentHref("a2","/MVC4CPF/page/jsp/common/tskljsp.jsp");
			tabbar.setTabActive("a1");
			
		var current=false;					
     function setRowValue(message) { 
      
			var a="a1"; b="a2";
			var vNum
				vNum = Math.random()
				vNum = Math.round(vNum*10)
			// if(current%2){
			// tabbar.setContentHref("a1","/MVC4CPF/page/jsp/common/tskljsp.jsp/"+vNum);
			//tabbar.setTabActive(a);
			// }
			// else{
			tabbar.setContentHref("a1","/MVC4CPF/page/jsp/task/task1.jsp?"+vNum);
			tabbar.setTabActive(a);
			// }
			current=!current;
			

 	           
    }  
 


</script>

	<script>

	  tabbar.setTabActive("a1");
	  doStart();	


	</script>



<!---========================== [View:TASK END] ===================================-->