var xmlHttp;    
    function createXMLHttpRequest() {    
        if (window.ActiveXObject) {    
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");    
        }    
        else if (window.XMLHttpRequest) {    
        xmlHttp = new XMLHttpRequest();    
        }    
    }    
    function doStart() {    
        createXMLHttpRequest();    
        var url = "http://localhost:8080/XMLEngine/XMLC/User_Mgr/op1/?Error_Code=0010";    
        xmlHttp.open("GET", url, true);    
        xmlHttp.onreadystatechange = startCallback;    
        xmlHttp.send(null);    
    }    
    function startCallback() {    
        if (xmlHttp.readyState == 4) {    
            if (xmlHttp.status == 200) {    
            setTimeout("pollServer()", 5000);    
            refreshTime();    
            }    
        }    
    }    
   
    function pollServer() {    
        createXMLHttpRequest();    
        var url = "http://localhost:8080/XMLEngine/XMLC/User_Mgr/op1/?Error_Code=0010";    
        xmlHttp.open("GET", url, true);    
        xmlHttp.onreadystatechange = pollCallback;    
        xmlHttp.send(null);    
    }    
        
    function refreshTime(){    
        //var time_span = document.getElementById("time");    
        var time_val = 30;    
        var int_val = parseInt(time_val);    
        var new_int_val = int_val - 1;    
        //if (new_int_val > -1) {    
        setTimeout("refreshTime()", 1000);    
        //time_span.innerHTML = new_int_val;    
        //} else {    
        //time_span.innerHTML = 5;    
        //}    
    }    
        
    function pollCallback() {    
        if (xmlHttp.readyState == 4) {    
            if (xmlHttp.status == 200) {    
                var message = xmlHttp.responseXML.getElementsByTagName("Error_Msg")[0].firstChild.data;    
                if (message != "done") {    
                
                var table = document.getElementById("schedulecontent");  
                setRowValue(table, "1234");      
                //var table_body =table.getElementsByTagName("tbody").item(0);    
                //var first_row = table.getElementsByTagName("tr").item(1);    
                //table_body.insertBefore(new_row, first_row);    
                setTimeout("pollServer()", 5000);    
                refreshTime();    
                }    
            }    
        }    
    }    
 
     function setRowValue(table,message) {  
        var rowlength=table.rows.length;  
        for(var i=4;i<rowlength;i++){
               if(table.rows[i]==null){ table.insertRow(i);
 	           table.rows[i].insertCell(0);
 	           table.rows[i].insertCell(1);
 	           }
 	          table.rows[i].cells[1].innerHTML=message;
 	           }
 	     }      
 	           
    }  
           
    function createRow(message) {    
        var row = document.createElement("tr");    
        var cell = document.createElement("td");    
        var cell_data = document.createTextNode(message);    
        cell.appendChild(cell_data);    
        row.appendChild(cell);    
        return row;    
    }  