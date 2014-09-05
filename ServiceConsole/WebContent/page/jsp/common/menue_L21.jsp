
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%@ page import="java.util.*,com.cnpc.oms.poc.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI" %>

<portletAPI:init />

<%
System.out.println(session.getAttribute("user_profile"));
System.out.println(session.getAttribute("ID"));
UserProfile up=new UserProfile();
//up.
String uid="";
if(session.getAttribute("user_profile")!=null)
	up=(UserProfile)session.getAttribute("user_profile");
	
if(up!=null)
	uid=up.getUserID();


%>


<HEAD>
	
	<script type="text/javascript" src="<portletAPI:encodeURI path="/js/menu-for-applications.js" />"></script>
	<style type="text/css">
	body{
		margin:0px;
	}
	#otherMenu{
		width:500px;
		border-left:1px solid #000;
		border-right:1px solid #000;
	}
	#thirdMenu{
		width:600px;
		
	}
	
	#fourthmenu{
		width:230px;
		height:230px;
	}
	</style>
</head>

<table cellpadding="0" cellspacing="1" border="0" width="100%" height="100%">
<tr><td bgcolor="#1947a3" height=15>
<img src="<portletAPI:encodeURI path="/images/WOD-T.jpg" />"><a class="ename"></a></td></tr>
			

<tr>
<td>
<div id="OMSPOC">
</div>
</td>
</tr>
</table>




<ul id="menuModel" style="display:none">
<%
			Vector menues=(Vector)up.getAllMenueElement();
			
			//String key=null,url=null;String level=null;
			MenueElement func=null;
			String mfurl=null,mfname=null;
			for(int i=0;i<menues.size();i++){
				func = (MenueElement)menues.elementAt(i);
				if(func.getName()!=null) mfname=func.getName();
				if(func.getURL()!=null) mfurl=func.getURL();
				out.println("<li id=\""+((i+1)*100)+"\"><a href=\""+mfurl+"\">"+mfname+"</a></li>\"");	
				out.println("<li id=\"50003\" itemType=\"separator\"></li>");		
			
			}

%>


	<li id="50002"><a href="#">Options</a></li>
</ul>

<script type="text/javascript">

var menuModel = new DHTMLSuite.menuModel();
menuModel.addItemsFromMarkup('menuModel');
menuModel.init();

var menuBar = new DHTMLSuite.menuBar();
menuBar.addMenuItems(menuModel);
menuBar.setTarget('OMSPOC');
menuBar.init(); 
<%
if(uid.equals("user1")){
	out.println("menuBar.setMenuItemState(200,'disabled');");
	}
else if(uid.equals("user2")){
	out.println("menuBar.setMenuItemState(200,'disabled');");
	out.println("menuBar.setMenuItemState(400,'disabled');");
	}
else if(uid.equals("user3"))
	out.println("menuBar.setMenuItemState(200,'disabled');");
else if(uid.equals("user4")){
	out.println("menuBar.setMenuItemState(200,'disabled');");
	out.println("menuBar.setMenuItemState(300,'disabled');");
	}		
%>
menuBar.setMenuItemState(200,'disabled');

</script>