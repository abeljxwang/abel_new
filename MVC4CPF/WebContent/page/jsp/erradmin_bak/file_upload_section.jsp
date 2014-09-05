 <%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%> 

<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI"%>
<portletAPI:init />

<center>
<form name="myform" action="/XMLEngine/FileUpLoad/" method="post" enctype="multipart/form-data">
		<table>
		<tr>
		<td>System: </td><td><select name="Sys_ID" id="Sys_ID">
			<option value="0700">EAIH</option>
			<option value="0250">ECTIP</option>
			<option value="0140">CCI</option>
			<option value="0141">CSR</option>
			<option value="0400">Long-NS</option>
			<option value="0020">CCBSS</option>
			<option value="0420">CCFS</option>
			<option value="0130">IBS</option>
			<option value="0010">CCBS</option>
			<option value="0260">ECIF</option>
			<option value="0150">VSS</option>
			<option value="9999">Õ®”√</option>
			<option value="">----</option>
		</select></td>
		</tr>
		<tr>
		<td>File:</td><td><input type="file" id="excelfile" name="excelfile"></td>
		</tr>
		<tr>
		<td></td><td><input type="submit" name="submit" value=" Upload " > &nbsp;</td>
		</tr>
		</table>
	</form>
</center>
