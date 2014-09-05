/*  Error Code Mgr framework, version 1.3.1
 *  (c) 2007 Abel Wang <abeljxwang@hotmail.com>
 *
 *
 *  For details, see the Prototype web site: http:///
 *
/*--------------------------------------------------------------------------*/

   var http_request = false;
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
            // ...processing statements go here...
            //rows>
            
            
            
            var tmpstr="---------";
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
                  
               }
            }   
			addrow(arr);
            //var sttt=xmldoc;
        // var url_cc="/XMLEngine/XMLC/SysCatCode/codechain/";
        // var pcc="?Error_Code="+arr[0]+"&Sys_ID="+arr[6];
        // getCatChain(url_cc, pcc); 
          
           //result.innerHTML = "----------------asgfa ---"+tmpstr;
           
           document.getElementById("cat_info").innerText="";
           
        } else {
            alert("There was a problem retrieving the XML data:\n" +
                http_request.statusText);
        }
        
        
        
getCatChain12("/XMLEngine/XMLC/SysCatCode/codechain/");
    }
    
    
}

	   function addrow(arr) {
	   var arr1 = new Array(6);
	   arr1[0]="Error_Code";arr1[1]="Error_Msg";arr1[2]="Suggestion";arr1[3]="Error_Level";
	   arr1[4]="Name";arr1[5]="Error_Cat_Sub";
	   // Error_Code,Error_Msg,Suggestion,Error_Level,Error_Cat,Error_Cat_Sub,Sys_ID,Name
   		 var tbl = document.getElementById("tbb1");
   		 for(var i=0;i<arr.length;i++){
            if(tbl.rows[i]==null){
               	tbl.insertRow(i);
 	           	tbl.rows[i].insertCell(0);
 	           	tbl.rows[i].insertCell(1);
 	         }
 	         if(tbl.rows[i].cells[0]==null) tbl.rows[i].cells[0].innerHTML=arr1[i];	 
 	         tbl.rows[i].cells[1].innerHTML=arr[i];
 	      }
	} 


  function getCatChain12(url) {
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
      
      var parameters="?Error_Code=";
      var ecodec=document.getElementById("e_code_c").innerText;
      if(ecodec==null||ecodec.length<=1) return;
      else parameters=parameters+ecodec;
      
      var syscodec=document.getElementById("sys_code_c").innerText;
      if(syscodec==null||syscodec.length<=1) return;      
      else parameters=parameters+"&Sys_ID="+syscodec;
      
      http_request.onreadystatechange = processRCatChain;
      http_request.open('GET', url + parameters, true);
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
      
      var parameters="?Error_Code=";
      var ecodec=document.getElementById("e_code_c").innerText;
      if(ecodec==null||ecodec.length<=1) return;
      else parameters=parameters+ecodec;
      
      var syscodec=document.getElementById("sys_code_c").innerText;
      if(syscodec==null||syscodec.length<=1) return;      
      else parameters=parameters+"&Sys_ID="+syscodec;
      
      http_request.onreadystatechange = processRCatChain;
      http_request.open('GET', url + parameters, true);
      http_request.send(null);
   }

function processRCatChain() {
    // only if req shows "loaded"
    if (http_request.readyState == 4) {
        // only if "OK"
        if (http_request.status == 200) {
            // ...processing statements go here...
            //rows>
            
            document.getElementById("cat_info").innerHTML="";
            
            var tmpstr="---------";
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
                 addrow1(arr); 
               }
               
               
            }   
			
            //var sttt=xmldoc;
            
            
           //result.innerHTML = "----------------asgfa ---"+tmpstr;
        } else {
            alert("There was a problem retrieving the XML data:\n" +
                http_request.statusText);
        }
    }
}

	   function addrow1(arr) {
   		 var tbcell = document.getElementById("cat_info");
   		 //tbcell.innerHTML="";
   		 var valuetbcell=tbcell.innerHTML+arr[0]+"|"+arr[1]+"<br>";
   		 tbcell.innerHTML=valuetbcell;
 	      
	}    
   