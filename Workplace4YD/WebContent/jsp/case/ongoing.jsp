
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />





	<div id="ongoing-case" class="a_panel" >
		<div class="p-heading">进行中的案件</div>
		<div class="panel-body">
			<table width=100% cellSpacing=0 cellPadding=0 >
				<thead>
					<tr>
						<th>案件名称</th>
						<th>案件类型</th>
						<th>客户名称</th>
						<th>委托范围</th>
						<th>案件状态</th>
						<th>代理何方</th>
						<th>收案日期</th>
						<th>受理机构</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="a_td">李某离婚案</td>
						<td class="a_td">民事</td>
						<td class="a_td">李某</td>
						<td class="a_td">一审</td>
						<td class="a_td">已受理</td>
						<td class="a_td">被告</td>
						<td class="a_td">2014年5月1日</td>
						<td class="a_td">洪山区法院</td>
					</tr>
					<tr>
						<td class="a_td">李某离婚案</td>
						<td class="a_td">民事</td>
						<td class="a_td">李某</td>
						<td class="a_td">一审</td>
						<td class="a_td">已受理</td>
						<td class="a_td">被告</td>
						<td class="a_td">2014年5月1日</td>
						<td class="a_td">洪山区法院</td>
					</tr>
					<tr>
						<td class="a_td">李某离婚案</td>
						<td class="a_td">民事</td>
						<td class="a_td">李某</td>
						<td class="a_td">一审</td>
						<td class="a_td"><a class="warning icon-in-table"><span
								class="glyphicon glyphicon-warning-sign"></span></a>等待结案</td>
						<td class="a_td">被告</td>
						<td class="a_td">2014年5月1日</td>
						<td class="a_td">洪山区法院</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>