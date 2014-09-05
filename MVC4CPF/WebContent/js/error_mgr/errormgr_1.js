/*  Error Code Mgr framework, version 1.3.1
 *  (c) 2007 Abel Wang <abeljxwang@hotmail.com>
 *
 *
 *  For details, see the Prototype web site: http:///
 *
/*--------------------------------------------------------------------------*/



   var http_request = false;
   
      function selectChainRequest(url, parameters,op) {
      http_request = false;
      if (window.XMLHttpRequest) { // Mozilla, Safari,...
         http_request = new XMLHttpRequest();
         if (http_request.overrideMimeType) {
            http_request.overrideMimeType('text/xml');
         }
      } else if (window.ActiveXObject) { // IE
         try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
         } catch (e) {
            try {
               http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
         }
      }
      if (!http_request) {
         alert('Cannot create XMLHTTP instance');
         return false;
      }
      if(op=="1")  http_request.onreadystatechange = selectChainReq1;
      else if(op=="2")  http_request.onreadystatechange = selectChainReq2;
      http_request.open('GET', url + '?UP_CAT_ID='+ parameters, true);
      http_request.send(null);
   }
 
 function selectChainReq1() {
selectChainReqPaser("Error_Cat");
}
 function selectChainReq2() {
 selectChainReqPaser("Error_Cat_Sub");

}
   
function selectChainReqPaser(opkey) {
    // only if req shows "loaded"
    if (http_request.readyState == 4) {
        // only if "OK"
        if (http_request.status == 200) {            
            var tmpstr="---------";
            var xmldoc = http_request.responseXML;
            var datanode = xmldoc.getElementsByTagName('data').item(0);
             var arr = new Array(len);
            refreshSelect(document.getElementById(opkey));
               for (i = 0; i < datanode.childNodes.length; i++) {
                  var rownode = datanode.childNodes.item(i);
                  var len = parseInt(rownode.childNodes.length);
                 
                  var cnt = 0;
                  for (x = 0; x < rownode.childNodes.length; x++) {
                     var cellnode = rownode.childNodes.item(x);
                     var txtnode;
                     if (cellnode.childNodes.length > 0) {
                        txtnode = cellnode.childNodes.item(0);
                        if (txtnode.data!=null) arr[cnt] = txtnode.data;   
                        else arr[cnt] = "-";   
                        tmpstr=tmpstr+arr[cnt];
                        cnt++;
                        
                     }
                     else{
                     arr[cnt] = "";   
                     cnt++;                     
                     }
                  }
                  addItem(arr,document.getElementById(opkey));
                  tmpstr=tmpstr+"  | ";
            } 

			// testpan.innerText=tmpstr;

        } else {
            alert("There was a problem retrieving the XML data:\n" + http_request.statusText);
        }
    }
}

 function refreshSelect(sel) {
 sel.options.length=1;
}


  function addItem(arr,sel) {
  sel.options[0]=new Option("N/A","");
  sel.options[sel.options.length]=new Option(arr[1],arr[0]);
  }

function add_CItem(arr,sel) {
sel.options[0]=new Option(arr[1],arr[0]);
}

   

function makeRequest(url, parameters) {
      http_request = false;
      if (window.XMLHttpRequest) { // Mozilla, Safari,...
         http_request = new XMLHttpRequest();
         if (http_request.overrideMimeType) {
            http_request.overrideMimeType('text/xml');
         }
      } else if (window.ActiveXObject) { // IE
         try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
         } catch (e) {
            try {
               http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
         }
      }
      if (!http_request) {
         alert('Cannot create XMLHTTP instance');
         return false;
      }
      http_request.onreadystatechange = processReqChange;
      http_request.open('GET', url + parameters, true);
      http_request.send(null);
   }

   
function processReqChange() {
    // only if req shows "loaded"
    if (http_request.readyState == 4) {
        // only if "OK"
        if (http_request.status == 200) {
            //refreshSelect21();
            var tmpstr="---------";
            //var tmpstr = http_request.responseText;
            var xmldoc = http_request.responseXML;
            var datanode = xmldoc.getElementsByTagName('data').item(0);
             var arr = new Array(len);
            
               for (i = 0; i < datanode.childNodes.length; i++) {
                  var rownode = datanode.childNodes.item(i);
                  var len = parseInt(rownode.childNodes.length);
                 
                  var cnt = 0;
                  for (x = 0; x < rownode.childNodes.length; x++) {
                     var cellnode = rownode.childNodes.item(x);
                     var txtnode;
                     if (cellnode.childNodes.length > 0) {
                        txtnode = cellnode.childNodes.item(0);
                        if (txtnode.data!=null) arr[cnt] = txtnode.data;   
                        else arr[cnt] = "-";   
                        cnt++;
                     }
                     else{
                     arr[cnt] = "";   
                     cnt++;                     
                     }
                  }
            }   
			addrow(arr);
           
            
           //result.innerHTML = "----------------asgfa ---"+tmpstr;
        } else {
            alert("There was a problem retrieving the XML data:\n" +
                http_request.statusText);
        }
        getCatChain("/XMLEngine/XMLC/ErrorCode/codechainsys/");
        
    }
}


	   function addrow(arr) {
	   	 document.errorinfoform.Error_Code4I.value = arr[0];
   		 document.errorinfoform.Error_Code.value = arr[0];
   		 document.errorinfoform.Error_Msg.value = arr[1];
   		 document.errorinfoform.Suggestion.value = arr[2];
   		 if(arr[3]==""||arr[3]=="-") arr[3]="9";
   		 document.errorinfoform.Error_Level.value = arr[3];
   		 var arr1=new Array(2);
   		 // if(arr[5].Trim().length>=2){
   		 // arr1[0]=arr[5];
   		 // arr1[1]=arr[5].Trim().substr(arr[5].length-2,2);
   		 // add_CItem(arr1,document.getElementById("Sys_ID"));}
   		 //document.errorinfoform.Error_Cat.value = arr[4];
   		 // if(arr[6].Trim().length>=2){
   		 // arr1[0]=arr[6];
   		 // arr1[1]=arr[6].Trim().substr(arr[6].length-2,2);
   		 // add_CItem(arr1,document.getElementById("Error_Cat_Sub"));
   		 // }
   		 //document.errorinfoform.Error_Cat_Sub.value = arr[5];
   		 
   		 document.errorinfoform.Sys_ID.value = arr[5];
   		 document.errorinfoform.Sys_ID4I.value = arr[4];
   		 
   		 
   		 // Error_Code,Error_Msg,Suggestion,Error_Level, Error_Cat, Error_Cat_Sub,Sys_ID
   		   		 
     		} 



 
  
String.prototype.Trim = function()
{
return this.replace(/(^\s*)|(\s*$)/g, "");
}
   
function processRespEcode() {
    // only if req shows "loaded"
    if (http_request.readyState == 4) {
        // only if "OK"
        if (http_request.status == 200) {
            // ...processing statements go here...
            //rows>
            
     
     		var sysid=document.getElementById("Sys_ID_1").value;
      		var e_cat=null;
      		if(document.getElementById("Cat_ID")!=null) e_cat=document.getElementById("Cat_ID").value.Trim();
	       
	      	var tempurlext_cat="";
      
      		if(e_cat!=null&&e_cat.length>0) {
       			var puresysid="0000"+e_cat;
       	
       		 tempurlext_cat = puresysid.substr(puresysid.indexOf(sysid)+sysid.length);
        	}
            
            
            var tmpstr="---------";
            var xmldoc = http_request.responseXML;
            
            
            var value=  xmldoc.getElementsByTagName("Error_Code")[0].childNodes[0].nodeValue; 
            var vv1;
            var tmpstrr=tempurlext_cat;
            
			if(value!="0"){
             vv1=value.replace(sysid,'');
             vv1=vv1.replace(/[^\d]/g,'');
             var ttt=parseInt(vv1,10)+1;
             vv1=ttt+"";
             tmpstrr="0000001".substr(0, 7-vv1.length);   }
             else{
             vv1="000001".substr(7-tempurlext_cat.length,tempurlext_cat.length);
              
              
             }
     
             
             var levelid=document.getElementById("Error_Level").options[document.getElementById("Error_Level").selectedIndex].text;

           document.errorinfoform.Error_Code.value=sysid+levelid+tmpstrr+vv1;
            
           //result.innerHTML = "----------------asgfa ---"+tmpstr;
        } else {
            alert("There was a problem retrieving the XML data:\n" +
                http_request.statusText);
        }
    }
}





	function getE_DetailMsg(value1)
	{
              document.inputform.E_CODE.value=value1;
	}

	
	function onSubmitForm()
	{

 	  document.inputform.action ="/MVC4CPF/ET/Console/addErrorInfoForm";
 	  document.inputform.submit();
	  return true;
	}	
	
	function onSubmitFormUpdate()
	{
 	  document.errorinfoform.action ="/MVC4CPF/ET/Console/updateErrorInfo";
 	  document.errorinfoform.submit();
	  return true;
	}	

	function onSubmitFormDel()
	{
 	  document.errorinfoform.action ="/MVC4CPF/ET/Console/delErrorInfo";
 	  document.errorinfoform.submit();
	  return true;
	}	

	
	 
function autoEcodeRequest(url, parameters) {
      http_request = false;
      if (window.XMLHttpRequest) { // Mozilla, Safari,...
         http_request = new XMLHttpRequest();
         if (http_request.overrideMimeType) {
            http_request.overrideMimeType('text/xml');
         }
      } else if (window.ActiveXObject) { // IE
         try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
         } catch (e) {
            try {
               http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
         }
      }
      if (!http_request) {
         alert('Cannot create XMLHTTP instance');
         return false;
      }
      
      // Sys_ID
      
      var sysid=document.getElementById("Sys_ID_1").value;
      var e_cat=null;
      if(document.getElementById("Cat_ID")!=null) e_cat=document.getElementById("Cat_ID").value.Trim();
	  testpan.innerHTML= sysid+" : "+e_cat+ "<br>";
       
      var tempurlext_sys="";
      var tempurlext_code="&Cat_ID_C=";
      if(sysid!=null&&sysid.length>0) tempurlext_sys = "?Sys_ID="+sysid;
      else return;
      
      if(e_cat!=null&&e_cat.length>0) {
       	var puresysid="0000"+e_cat;
       	
        tempurlext_code = tempurlext_code +puresysid.substr(puresysid.indexOf(sysid)+sysid.length);
        }
      
      testpan.innerHTML=testpan.innerHTML+"code:"+tempurlext_code;
      

	  //testpan.innerHTML=testpan.innerHTML+ tempurlext_sys+"-"+tempurlext_code+"puresysid:"+puresysid;
		
      
      http_request.onreadystatechange = processRespEcode;
      http_request.open('GET', url + tempurlext_sys + tempurlext_code, true);
      http_request.send(null);
   }



function getCatChain(url) {
      http_request = false;
      if (window.XMLHttpRequest) { // Mozilla, Safari,...
         http_request = new XMLHttpRequest();
         if (http_request.overrideMimeType) {
            http_request.overrideMimeType('text/xml');
         }
      } else if (window.ActiveXObject) { // IE
         try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
         } catch (e) {
            try {
               http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
         }
      }
      if (!http_request) {
         alert('Cannot create XMLHTTP instance');
         return false;
      }
      
      testpan.innerHTML = document.getElementById("Error_Code4I").value+":"+document.getElementById("Sys_ID_1").value;
               
      var parameters="?Error_Code=";
      var ecodec=document.getElementById("Error_Code4I").value;
      //if(ecodec==null||ecodec.length<=1) return;
      //else 
      parameters=parameters+ecodec;
      
      var syscodec=document.getElementById("Sys_ID_1").value;
      if(syscodec==null||syscodec.length<=1) return;      
      else parameters=parameters+"&Sys_ID="+syscodec;
      
      testpan.innerHTML=testpan.innerHTML+parameters;
      
      http_request.onreadystatechange = processRCatChain1;
      http_request.open('GET', url + parameters, true);
      http_request.send(null);
   }

function processRCatChain1() {
    // only if req shows "loaded"
    if (http_request.readyState == 4) {
        // only if "OK"
        if (http_request.status == 200) {
            // ...processing statements go here...
            //rows>
           refreshSelect21();
            //document.getElementById("cat_info").innerHTML="";
            
            //var tmpstr="---------";
            //var tmpstr = http_request.responseText;
            var xmldoc = http_request.responseXML;
            var root = xmldoc.getElementsByTagName('root').item(0);
             var arr = new Array(len);
            for (var iNode = 0; iNode < root.childNodes.length; iNode++) {
               var node = root.childNodes.item(iNode);
               for (i = 0; i < node.childNodes.length; i++) {
                  var sibl = node.childNodes.item(i);
                  var len = parseInt(sibl.childNodes.length / 2);
                 
                  var cnt = 0;
                  for (x = 0; x < sibl.childNodes.length; x++) {
                     var sibl2 = sibl.childNodes.item(x);
                     var sibl3;
                     if (sibl2.childNodes.length > 0) {
                        sibl3 = sibl2.childNodes.item(0);
                        arr[cnt] = sibl3.data;   
                        //tmpstr=tmpstr+sibl3.data+"---|---";
                        
                     }
                     else arr[cnt] = "N/A";  
                     cnt++; 
                  }
                 addrow21(arr); 
                 // testpan.innerHTML = testpan.innerHTML +"<br>"+arr(0)+":"+arr(1);
               }
               
               
            }   
			
            //var sttt=xmldoc;
            
            
           //testpan.innerHTML = "----------------asgfa ---"+arr(0)+":"+arr(1);
        } else {
            alert("There was a problem retrieving the XML data:\n" +
                http_request.statusText);
        }
    }
}

 function refreshSelect21() {
 var sel=document.getElementById("Cat_ID");
 sel.options.length=0;
}

	   function addrow21(arr) {
	   var tmpstr="          ";
	   		var sel=document.getElementById("Cat_ID");
	   		//sel.options[0]=new Option(document.getElementById("Error_Code4I").value,document.getElementById("Sys_ID4I").value);
  		var atmp=tmpstr.substr(0,arr[0].Trim().length);
  			sel.options[sel.options.length]=new Option(atmp+arr[1],arr[0].Trim());
	      
	}   
		