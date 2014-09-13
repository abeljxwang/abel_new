<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	String sid = "", opid = "";
String sname=request.getServerName();
	if (request.getParameter("sid") != null)
		sid = request.getParameter("sid");
	if (request.getParameter("sid") != null)
		opid = request.getParameter("opid");

	String sostr = sid + "/" + opid;

	//System.out.println(dm.getMessageDesc("E100001"));
%>




<style>
	
	.fieldName {
	FONT-SIZE: 8pt;
	COLOR: #CCCCFF;
	text-align: left;
	TEXT-TRANSFORM: capitalize
}

.box_header1 {
	width: 600px;
	height: 22px;
	background: #6699CC;
	border-radius: 6px;
	border-style: solid;
	border-width: 1px;
	border-color: #002299;
	font-size: 10pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: 微软雅黑;
	padding: 1px 15px;
	margin-bottom: 0.2em;
}

.endpoint {
	width: 450px;
	height: 20px;
	padding: 1px 5px;	
	background: #CCDDFF;
	border-width: 0px;
	border-color: #002299;
	font-size: 9pt;
	text-align: left;
	COLOR: #000044;
	font-family: 微软雅黑;
	margin: 1px 4px;
}

.button_1 {
	padding: 0px;
	font-size: 10pt;
	text-align: left;
	border-style: solid;
	border-width: 0px;
	border-color: #ffffff;
	background: #CCCCFF;
	height: 18px;
	width: 80px;
	clear: both;
	text-align: center;
	border-radius: 8px;
	font-family: 微软雅黑;
	margin: 1px;
}


.box_header {
	width: 600px;
	background: #333399;
	border-top-left-radius: 10px;
	border-top-right-radius: 1px;
	border-style: solid;
	border-width: 1px;
	border-color: #002299;
	height: 20px;
	font-size: 10pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: 微软雅黑;
	padding: 1px 15px;
}


.box_summary {
	width: 600px;
	background: #000066;
	border-top-left-radius: 10px;
	border-top-right-radius: 1px;
	border-style: solid;
	border-width: 1px;
	border-color: #002299;
	height: 30px;
	font-size: 12pt;
	text-align: center;
	COLOR: #FFFFFF;
	font-family: 微软雅黑;
	padding: 1px 15px;
	margin-bottom: 0.2em;
}


.box_content_1 {
	width: 600px;
	min-height: 100px;
	padding: 3px 15px;
	background: #F0F0FF;
	border-style: solid;
	border-width: 1px;
	border-color: #002299;
	font-size: 10pt;
	text-align: left;
	COLOR: #000044;
	font-family: 微软雅黑;
	padding: 3px 15px;
	border-bottom-left-radius: 8px;
	border-bottom-right-radius: 8px;
	margin-bottom: 0.6em;

}



.box_content_3 {
	
	font-size: 10pt;
	text-align: left;
	COLOR: #000044;
	font-family: Courier;
	
	
}


.box_content_2 {
	width: 600px;
	min-height: 100px;
	padding: 3px 15px;	
	background: #F0F0FF;
	border-style: solid;
	border-width: 1px;
	border-color: #002299;
	font-size: 10pt;
	text-align: left;
	COLOR: #000044;
	font-family: 微软雅黑;
	padding: 3px 15px;
	border-bottom-left-radius: 8px;
	border-bottom-right-radius: 8px;
}

.box_content_2_1 {
	width: 600px;
	min-height: 100px;
	padding: 3px 15px;	
	background: #FFF0F0;
	border-style: solid;
	border-width: 1px;
	border-color: #002299;
	font-size: 10pt;
	text-align: left;
	COLOR: #000044;
	font-family: 微软雅黑;
	padding: 3px 15px;
	border-bottom-left-radius: 8px;
	border-bottom-right-radius: 8px;
}

.box_content_2_2 {
	width: 600px;
	min-height: 100px;
	padding: 3px 15px;	
	background: #F0FFF0;
	border-style: solid;
	border-width: 1px;
	border-color: #002299;
	font-size: 10pt;
	text-align: left;
	COLOR: #000044;
	font-family: 微软雅黑;
	padding: 3px 15px;
	border-bottom-left-radius: 8px;
	border-bottom-right-radius: 8px;
}




.button_2_1 {
border-radius: 9px;
	padding: 0px;
	font-size: 10pt;
	text-align: left;
	margin-top: 1px;
	border-style: solid;
	text-align: center;
	border-width: 0px;
	background: #ffAAAA;
	height: 18px;
	width: 18px;
	clear: both;
	font-family: 微软雅黑;
}
.button_2_2 {
border-radius: 9px;
	padding: 0px;
	font-size: 10pt;
	text-align: left;
	margin-top: 1px;
	border-style: solid;
	text-align: center;
	border-width: 0px;
	background: #AAffAA;
	height: 18px;
	width: 18px;
	clear: both;
	font-family: 微软雅黑;
}


</style>

<body>

	<!-- our form -->
	

	<!-- where the response will be displayed -->

	<table width=90%>
		<tr>
			<td align=center>

			

	<div id="testing">
		<div>
			<div class="box_summary">
				<font class="fieldName">Service Name:</font> <%=sid %> &nbsp;&nbsp;	<font class="fieldName">Operation Name:</font> <%=opid %>
			</div>
			
			<div class="box_header1">
				<table width="100%" cellpadding=0 cellspacing=0>
					<tr>
						<td align=right valign="middle" width=10%><font color=FFFFFF>endpoint: </font></td>
						<td align=left valign="middle" width=75%>
<input name=endpoint id=endpoint class="endpoint" type=text value="http://<%=sname%>:80/Service/R4JE/<%=sid %>/<%=opid %>">
						</td>
						<td align=right valign="bottom"  width=15%><input type='button'
							class="button_1" value='TESTING....' id='testR4JE' onclick="showtestrun()" /></td>
					</tr>
				</table>
			</div>
		</div>
		
		
		<div>
			
			<div class="box_header">
				<table width="100%" cellpadding=0 cellspacing=0>
					<tr>
						<td align=left valign="middle"><font color=FFFFFF>(in  --- &gt;&gt;)REQUEST</font></td>
						<td align=right valign="bottom"></td>
					</tr>
				</table>
			</div>
			<div class="box_content_1">
				<div id="input" class="textarea_1" contenteditable>input area</div>
			</div>

		</div>
		<div>
			<div class="box_header">
				<table width="100%" cellpadding=0 cellspacing=0>
					<tr>
						<td align=left valign="middle"><font color=FFFFFF>(out  &lt;&lt;----)RESPONSE</font></td>
						<td align=right valign="bottom"><input type='button'
							class="button_2_1" value='T'  onclick="dispTM()" />&nbsp;<input
							type='button' class="button_2_2" value='E'  onclick="dispR4JE()" /></td>
					</tr>
				</table>
			</div>
			<div id="output" class="box_content_2_1"></div>
			<div id="output_r4je" class="box_content_2_2"> Real Testing Result----:  </div>
		</div>


	</div>


			</td>
		</tr>
	</table>


	<script src="/ServiceConsole/js/jquery/jquery-1.10.2.js "></script>
	<script>
	
	var hostbase="http://<%=sname%>";
	var urlbase= "http://<%=sname%>/Service/R4JE";
	
		$(document).ready(function() {
			document.getElementById("output_r4je").style.display = "none";
												/*
												 * 'post_receiver.php' - where you will pass the form data
												 * $(this).serialize() - to easily read form data
												 * function(data){... - data contains the response from post_receiver.php
												 */
												var person = {
													"login_id" : "abeljxwang@hotmail.com",
													"login_pwd" : "abel1234"
												}

												$
														.ajax({
															type : "POST",
															url : hostbase+"/ServiceConsole/JETM/<%=sostr%>",
															dataType : "json",
															contentType : "text/plain; charset=utf-8",
															processData : true,
															data : JSON
																	.stringify(person),
															success : function(
																	response) {

																// var json_obj = $.parseJSON(response);//parse JSON
																// var formattedDate = new Date(response.ShoppingCart.SCItems[0].time_added);
																var output = "<pre class='box_content_3'> "
																		+ JSON
																				.stringify(
																						response,
																						null,
																						4)
																		+ "</pre>";
																//null, '\t'

																$('#output')
																		.html(
																				output);

															},
															error : function(
																	jqXHR,
																	textStatus,
																	errorThrown) {
																$('#output')
																		.html(
																				errorThrown
																						+ textStatus);
															}

														})

												$.ajax({
															type : 'GET',
															url : hostbase+'/ServiceConsole/JETMREQ/<%=sostr%>',
						dataType : "json",
						contentType : "text/plain; charset=utf-8",
						processData : true,
						success : function(response) {

							// var json_obj = $.parseJSON(response);//parse JSON
							// var formattedDate = new Date(response.ShoppingCart.SCItems[0].time_added);
							var output = "<pre class='box_content_3'> "
									+ JSON.stringify(response, null, 4)
									+ "</pre>";
							//null, '\t'

							$('#input').html(output);

						},
						error : function(jqXHR, textStatus, errorThrown) {
							$('#input').html(errorThrown + textStatus);
						}

					})

					// to prevent refreshing the whole page page
					return false;

				});
		
		
		function dispTM() {
			// show that something is loading
			document.getElementById("output_r4je").style.display = "none";
			document.getElementById("output").style.display = "";

			}
		
		function dispR4JE() {
			// show that something is loading
			document.getElementById("output_r4je").style.display = "";
			document.getElementById("output").style.display = "none";
			}
		
		function showtestrun() {
							// show that something is loading
							document.getElementById("output_r4je").style.display = "";
							document.getElementById("output").style.display = "none";
							$('#output_r4je').html("calling backend service---->>>>");	
							
							
							var inputstr=document.getElementById("input").innerHTML;
							 var n_s = inputstr.indexOf("{"); 
							 var n_e = inputstr.lastIndexOf("}")+1; 
							 var res = inputstr.substring(n_s, n_e);							
							 //document.getElementById("debug_w").value=res;
							 
							/*
							 * 'post_receiver.php' - where you will pass the form data
							 * $(this).serialize() - to easily read form data
							 * function(data){... - data contains the response from post_receiver.php
							 */
							
									 
							var input_obj = res;
							 
							var endpoint_url= document.getElementById('endpoint').value;
							var urltoendpoint=urlbase+'/<%=sid %>/<%=opid %>';

							 if(endpoint_url!=null) urltoendpoint=endpoint_url;

							$.ajax({
										type : 'POST',
										url : urltoendpoint,
										headers: {
									        access_id:"121481941749174"
									    },
										dataType : "json",
										contentType : "text/plain; charset=utf-8",
										processData : true,
										data : input_obj,
										success : function(
												response) {

											// var json_obj = $.parseJSON(response);//parse JSON
											// var formattedDate = new Date(response.ShoppingCart.SCItems[0].time_added);
											var output = "<pre class='box_content_3'> "
													+ JSON
															.stringify(response,null,4)
													+ "</pre>";
											//null, '\t'

											$('#output_r4je').html(output);			

										},
										error : function(jqXHR,	textStatus,	errorThrown) {
											$('#output_r4je').html(errorThrown + textStatus);
											$('#output_r4je').style.display = 'visible';
										}

									})
											
							// to prevent refreshing the whole page page
							return false;

						}
	</script>




</body>
</html>