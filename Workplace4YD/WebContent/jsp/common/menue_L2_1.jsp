<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />
<%@ page import="java.util.*,com.ibm.ais.util.*"%>
<link rel="stylesheet" type="text/css" href="<portletAPI:encodeURI path="/jscmpt/dhtmlx/dhtmlxmenu.css" />"/>
<script type="text/javascript" src="<portletAPI:encodeURI path="/jscmpt/dhtmlx/dhtmlxmenu.js" />"></script>
<%
com.ibm.ais.util.UserProfile up=new com.ibm.ais.util.UserProfile();
		MenueElement me = new MenueElement();
	if(session!=null){
	//session.removeAttribute("user_profile");
	if(session.getAttribute("user_profile")!=null){
		
		up=(UserProfile)session.getAttribute("user_profile");
		
		
		
		//if(up!=null&&up.getAllMenueElement().size()>0)
		
			//me=(MenueElement)up.getAllMenueElement().firstElement();
		
		//out.println(me.getID()+" : "+me.getUpID());
		//Vector childs=me.getElements();
		//for(int c=0; c<childs.size();c++){
			//MenueElement ec=(MenueElement)childs.elementAt(c);
			
			//out.println(ec.getID()+" : "+ec.getUpID());
		//}
	}
	}
%>


<script>
var myMenu;
function doOnLoad() {
	myMenu = new dhtmlXMenuObject("menuObj");
	myMenu.setIconsPath("<portletAPI:encodeURI path="/jscmpt/dhtmlx/imgs/" />");
	// initing
	
	<%
	
	Vector mlist=up.getAllMenueElement();
	
	for(int i=0; i<mlist.size();i++){
		me=(MenueElement)mlist.elementAt(i);
		if(me.getUpID()==null) out.println("myMenu.addNewSibling(null, \""+me.getID()+"\", \"" + me.getName() +"\", false);");
		
		else out.println("myMenu.addNewSibling(\""+me.getUpID()+"\", "+i+", \""+me.getID()+"\", \""+me.getName()+"\", false, \"open.gif\");");
		
	}
	%>
	//myMenu.addNewSibling(null, "file", "File", false);
		myMenu.addNewChild("file", 0, "new", "New", false, "new.gif");
		myMenu.addNewSeparator("new");
		myMenu.addNewChild("file", 2, "open", "Open", false, "open.gif");
		myMenu.addNewChild("file", 3, "save", "Save", false, "save.gif");
		myMenu.addNewChild("file", 4, "saveAs", "Save As...", true, null, "save_as_dis.gif");
		myMenu.addNewSeparator("saveAs");
		myMenu.addNewChild("file", 6, "print", "Print", false, "print.gif");
		myMenu.addNewChild("file", 7, "pageSetup", "Page Setup", true, null, "page_setup_dis.gif");
		myMenu.addNewSeparator("pageSetup");
		myMenu.addNewChild("file", 12, "close", "Close", false, "close.gif");
	myMenu.addNewSibling("file", "edit", "Edit", false);
		myMenu.addNewChild("edit", 0, "edit_undo", "Undo", false, "undo.gif");
		myMenu.addNewSibling("edit_undo", "edit_redo", "Redo", false, "redo.gif");
		myMenu.addNewSeparator("edit_redo", "sep_1");
		myMenu.addNewSibling("sep_1", "edit_select_all", "Select All", false, "select_all.gif");
		myMenu.addNewSeparator("edit_select_all", "sep_2");
		myMenu.addNewSibling("sep_2", "edit_cut", "Cut", false, "cut.gif");
		myMenu.addNewSibling("edit_cut", "edit_copy", "Copy", false, "copy.gif");
		myMenu.addNewSibling("edit_copy", "edit_paste", "Paste", false, "paste.gif");
	myMenu.addNewSibling("edit", "help", "Help", false);
		myMenu.addNewChild("help", 0, "about", "About...", false, "about.gif");
		myMenu.addNewChild("help", 1, "help2", "Help", false, "help.gif");
		myMenu.addNewChild("help", 2, "bugrep", "Bug Reporting", false, "bug_reporting.gif");
}



	window.onload =doOnLoad;
	
	
	
</script>

	<div>
		<div id="menuObj"></div><div class="menue_plus"><img src="<portletAPI:encodeURI path="/images/plus2.png" />" width=24></div> 
	</div>
