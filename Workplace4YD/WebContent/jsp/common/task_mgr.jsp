
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<style>
.xl24 {
	font-size: 11.0pt;
	font-family: 华文楷体;
	mso-generic-font-family: auto;
	border-top: none;
	border-right: none;
	border-bottom: 1pt solid #009999;
	border-left: none;
}

.xl25 {
	font-size: 8.0pt;
	font-family: Wingdings;
	mso-generic-font-family: auto;
	mso-font-charset: 2;
	border-top: 0.0pt solid windowtext;
	border-right: none;
	border-bottom: 1pt solid #E0E0E0;
	border-left: none;
}

.xl27 {
	font-size: 9.0pt;
}

.xl29 {
	mso-style-parent: style0;
	font-size: 8.0pt;
	font-family: Verdana, sans-serif;
	mso-font-charset: 0;
	mso-number-format: "Short Time";
	border-top: 0.0pt solid teal;
	border-right: none;
	border-bottom: 1pt solid #E0E0E0;
	border-left: none;
}

.xl31 {
	mso-style-parent: style0;
	font-size: 8.0pt;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	border-top: 0.0pt solid windowtext;
	border-right: none;
	border-bottom: 1pt solid #E0E0E0;
	border-left: none;
}
</style>
<link rel="STYLESHEET" type="text/css"
	href="/Workplace4YD/theme/css_dhtmlsuite/dhtmlxtabbar.css">

<script src="/Workplace4YD/js/dhtmlx/dhtmlxtabbar.js"></script>
<script src="/Workplace4YD/js/dhtmlx/dhtmlxtabbar_start.js"></script>



<div id="mvc_tabbar" style="width: 270; height: 310;" /></div>

<SCRIPT language=JavaScript type=text/javascript>

var tabbar=null;
tabbar=new dhtmlXTabBar("mvc_tabbar","top");
tabbar.setImagePath("/Workplace4YD/images/imgs/");
tabbar.setHrefMode("ajax-html");
tabbar.setSkinColors("#FCFBFC","#F4F3EE");
tabbar.addTab("a1","重要待办","60px");
tabbar.addTab("a2","客户资料","60px");
tabbar.addTab("a3","历史交易信息","80px");
			
tabbar.enableAutoSize(false,false);
tabbar.setContentHref("a1","/Workplace4YD/jsp/task/task1.jsp");
tabbar.setContentHref("a2","/Workplace4YD/jsp/common/tskljsp.jsp");
tabbar.setContentHref("a3","/Workplace4YD/jsp/common/a1.jsp");
tabbar.setTabActive("a1");
			
var c=0;
var t;


function timedCount()
{
	//document.getElementById('txt').value=c;
	var vNum = Math.random()
	vNum = Math.round(vNum*10)
	//tabbar.setContentHref("a1","/MVC4CPF/page/jsp/task/task1.jsp/"+vNum);
	tabbar.forceLoad("a1","/Workplace4YD/jsp/task/task1.jsp");	
	// forceLoad(tabId,href);
	tabbar.setTabActive("a1");
	//c=c+1;
	t=setTimeout("timedCount()",100000);
}

function stopCount()
{
	clearTimeout(t);
}

 

function addrowI4U(arr) {

    var tbl = document.getElementById("TD_tasklist");
   		 
   		 for(var i=1;i<=arr.length;i++){
            if(tbl.rows[i]==null){
               	tbl.insertRow(i);
 	           	tbl.rows[i].insertCell(0);
 	           	tbl.rows[i].insertCell(1);
 	           	tbl.rows[i].insertCell(2);
 	         }
 	         // if(tbl.rows[i].cells[0]==null) tbl.rows[i].cells[0].innerHTML=arr1[i];	
 	         var arrrec= arr[i-1];
 	         tbl.rows[i].cells[1].innerHTML=arrrec[1];
 	         tbl.rows[i].cells[2].innerHTML=arrrec[3];
 	      }    

    
} 

function processI4U() {
    // only if req shows "loaded"
    if (http_request.readyState == 4) {
        // only if "OK"
        if (http_request.status == 200) {           
            var arr = processXMLResponseMR(http_request); 
			addrowI4U(arr);
			//var p = document.getElementById("debug")
	        //p.innerHTML = "<li>"+arr+"</li>"; 
           
        } else {
            alert("There was a problem retrieving the XML data:\n" +
                http_request.statusText);
        }
        
    }
    
    
}

	function protocolIt(str){
	 var p = document.getElementById("debug")
	 p.innerHTML = "<li>"+str+"</li>"; 
		
		makeRequest('<portletAPI:encodeURI path="/XMLENG/ServiceQuery/queryEProfile" />',str,processI4U);
		
	}
	
	function doOnRowSelected(rowID,celInd){
	
		var stt=this.cells(rowID,0).getValue();
		protocolIt("indicatorID=" + stt);
	}


</script>


<script>
	  // tabbar.setTabActive("a1");
	  // doStart();	
	  timedCount();

	</script>



<!---========================== [View:TASK END] ===================================-->

<div id="debug"
	style="width: 270px; height: 20px; overflow: auto; border: 1px solid #ff9999;"></div>