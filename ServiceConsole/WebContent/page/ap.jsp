<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
<link rel="stylesheet" href="/ServiceConsole/theme/jquery-ui.css">
<script src="/ServiceConsole/js/jquery/jquery-1.10.2.js"></script>
<script src="/ServiceConsole/js/jquery/jquery-ui.js"></script>


  <script>



    $( "#tags" ).autocomplete({

    	 source: "http://localhost:8080/ServiceConsole/AutoCP/?type=metadata",
           minLength: 1,    
       select: function( event, ui ) { 
      } 
	  



  });

  </script>


</head>
<body>









 
<div class="ui-widget">
	<label for="tags">Tags: </label>
	<input id="tags">
</div>

 





</body>
</html>