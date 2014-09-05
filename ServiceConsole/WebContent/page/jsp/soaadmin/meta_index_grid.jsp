
	<link href="/ServiceConsole/theme/dhtmlXGrid.css" rel="stylesheet" type="text/css">
	<script src="/ServiceConsole/js/dhtmlx/dhtmlXCommon.js" type="text/javascript"></script>
	<script src="/ServiceConsole/js/dhtmlx/dhtmlXGrid.js" type="text/javascript"></script>
	<script src="/ServiceConsole/js/dhtmlx/dhtmlXGridCell.js" type="text/javascript"></script>


			<table border=0>
				<tr><td><div id="gridbox" height="230px" width="600px"></div></td></tr>
				
			</table>

	<script>
			mygrid = new dhtmlXGridObject('gridbox');
		 	mygrid.setImagePath("/ServiceConsole/images/imgs/"); 
			//set columns properties
			mygrid.setHeader("ID,CName,TYPE,LENG,DESC");
			mygrid.setColTypes("ed,ed,ed,ed,ed");
			mygrid.setInitWidths("100,150,50,50,200")
			mygrid.setColAlign("left,left,left,center,left")
			mygrid.setColumnColor("#f7f6f0,#ffffff,#f7f6f0")
			mygrid.loadXML("/ServiceConsole/XMLC/SOA/queryM");
			mygrid.init()

	</script>