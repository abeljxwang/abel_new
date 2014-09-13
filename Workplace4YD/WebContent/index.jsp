<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>

<%
// session.invalidate();

%>
<HTML>
<HEAD>
<TITLE>SOA</TITLE>
<META http-equiv=Content-Type content="text/html; charset=Shift_JIS">
<META http-equiv=Content-Script-Type content=text/javascript>
<META http-equiv=Content-Style-Type content=text/css>

<META content="MSHTML 6.00.2900.3157" name=GENERATOR>
<SCRIPT type=text/javascript>


	
	function onSubmitForm()
	{

 	  document.loginform.action ="/Workplace4YD/ET/Security/login";
 	  document.loginform.submit();
	  return true;
	}	
	
	
	</script>
	
	
<style type='text/css'>

body {  background: url("images/bk2.jpg") fixed center center no-repeat;  background-size: cover;  width: 100%;}

#login_main {
	position: absolute;
	position:fixed;
	width: 700px;
	height: 400px;
	left: 50%;
	top: 50%;
	margin-left: -350px;
	margin-top: -200px;
	font-family: "微软雅黑";
	font-size: 10pt;
	text-align: center;

	background: #FddFFF;
	
	
	border: 6px solid #FFFFFF;
		-moz-border-radius: 30px 30px;
	border-radius: 30px 30px;
	background-image:url('images/ImageShow.jpg');
	
	box-shadow: 10px 10px 9px rgba(0,0,0,0.6);

}

#login_m {
	position: absolute;
	height: 400px;
	width: 350px;
	left: 50%;
	z-index:101
	top: 0;
	margin: 0px 0px;
	padding-top: 0px;
	
	background-color: ffffff;
    
    filter: alpha(opacity=80); 
	opacity: 0.8;
	
	border-top-left-radius:0px; 
	border-top-right-radius:24px; 
	border-bottom-left-radius:0px; 
	border-bottom-right-radius:24px; 
}

.input_lg {

width:200px;
height:30px;
	padding: 2px 20px;
	font-size: 10pt;
	text-align: left;
z-index:200;

	background: rgba(254, 254, 254, 0.99);
	filter: alpha(opacity=99); 
	opacity: 0.99;
	margin: 1px 5px;
	border: 1px solid #555555;
		-moz-border-radius: 15px 15px;
	border-radius: 15px 15px;
	font-family: "微软雅黑";

}


</style>

</HEAD>
<BODY>
<DIV align=center id="login_main">

<div id="login_m">
					<br><br>
					<img src="images/ydlog_short.png" width=100><br><br><br><br><br><br>
					<form name="loginform" method="POST"  class="">
					<input type="text" class="input_lg"
						name="USER_ID" placeholder="用户名/员工号/邮箱" /> <br>
						
						<input type="password" 	class="input_lg" name="email"
						placeholder="密码" />


					<p class="text-center">
						<button type="button" class="input_lg" 
							onclick="onSubmitForm();">登录到AST案件系统</button>
					</p>
					<div id="rem" class="wrapper_remember">
						<input id="rememberUn" type="checkbox" checked="checked"
							name="rememberUn"> <label for="rememberUn">记住用户名</label>
					</div>
				</form>
				<div id="forgot" class="forgot-box">
					<span class="forgot-psw"> <a
						href="/secur/forgotpassword.jsp?locale=cn">忘记了密码？</a></span> | <span
						class="reg-link"> <a id="signup_link"
						href="https://www.salesforce.com/cn/form/trial/freetrial.jsp?d=70130000000EndO">免费注册</a>
					</span> <span id="mydomainLink" style="display: block;"> 
					</span>
				</div>
				
				


</div>




</DIV>
<MAP name=Map>
	<AREA shape=RECT target=_blank alt="" coords=163,5,243,22 href="">
</MAP>

</BODY>
</HTML>