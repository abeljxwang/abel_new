
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<link rel="StyleSheet" href="/ServiceConsole/theme/dtree.css" type="text/css" />
<script type="text/javascript" src="/ServiceConsole/js/dtree.js"></script>
	
<div class="dtree">

	<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>

	<script type="text/javascript">
		<!--

		d = new dTree('d');

		d.add(0,-1,'Service Directory');
		d.add(1,0,'��Ӫ�ƻ�');
		d.add(103,1,'��¼��ҵ����','queryS_Detaile?serviceid=Well_Mgr');
		d.add(104,1,'�ƶ���ȼƻ�','example01.html');
		d.add(105,1,'��ȼƻ�ִ���ܽ�','example01.html');
		d.add(106,1,'��Ӫ����','example01.html');
		d.add(2,0,'��������','example01.html');
		d.add(201,2,'������Ϣ�ռ�','example01.html');
		d.add(202,2,'�ճ�����Э��','example01.html');
		d.add(203,2,'����Э��','example01.html');
		d.add(204,2,'�������/׷��','example01.html');
		d.add(202,2,'����ͻ���¼�','example01.html');
		d.add(203,2,'Σ��Ʒ����','example01.html');
		d.add(204,2,'����ͳ��','example01.html');		

		d.add(4,0,'��Դ����','example01.html');
		d.add(401,4,'��Ա����','example01.html');
		d.add(402,4,'װ(��)������','example01.html');
		d.add(403,4,'���ʹ���','example01.html');
		d.add(404,4,'����������','example01.html');
		

		d.add(7,0,'ͳ�Ʒ���','example01.html');
		d.add(701,7,'�豸ʹ����Ϣ����','example01.html');
		d.add(702,7,'��Ա��ʱͳ��','example01.html');
		d.add(703,7,'����ʹ����Ϣ����','example01.html');
		d.add(704,7,'����ͳ��','example01.html');
		d.add(705,7,'���̼���ͳ��','example01.html');




		d.add(8,705,'Node 1.2','example01.html');
		d.add(9,0,'���̼����ĵ�����','example01.html','Pictures I\'ve taken over the years','','','/ServiceConsole/images/tree/imgfolder.gif');
		d.add(901,9,'�ύ�ĵ�','example01.html');
		d.add(902,9,'�ռ���������','example01.html');
		d.add(903,9,'�ĵ��黹','example01.html');
		d.add(904,9,'�ĵ����','example01.html');
		d.add(905,9,'�ĵ��鵵����','example01.html');
		d.add(906,9,'�ĵ�����','example01.html');
		d.add(907,9,'�ĵ�����','example01.html');
		d.add(908,9,'�ĵ�����','example01.html');		
		d.add(909,9,'���ú���','example01.html');	
	
		d.add(10,909,'The trip to Iceland','example01.html','Pictures of Gullfoss and Geysir');
		d.add(11,909,'Mom\'s birthday','example01.html');
		d.add(12,0,'��Ŀ����','example01.html','','','/ServiceConsole/images/tree/trash.gif');
		d.add(1201,12,'�ƶ���Ŀ�滮','example01.html');
		d.add(1202,12,'����׼���ƻ�','example01.html');
		d.add(1203,12,'�豸�ƻ�','example01.html');
		d.add(1204,12,'��Ա���żƻ�','example01.html');
		d.add(1205,12,'������Ŀ�ɱ�����','example01.html');
		d.add(1206,12,'ǩ����Ŀ��ͬ','example01.html');
		d.add(1207,12,'������Ŀ','example01.html');
		d.add(1208,12,'��Ŀʵʩ','example01.html');		
		d.add(1209,12,'�ɱ�����','example01.html');	
		d.add(12010,12,'�ƻ�׷��','example01.html');		
		d.add(12011,12,'��ͬ����','example01.html');			
		document.write(d);

		//-->
	</script>

</div>

