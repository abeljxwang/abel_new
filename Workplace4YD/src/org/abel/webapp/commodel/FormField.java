package org.abel.webapp.commodel;


/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FormField {

	//<Parameter Name="tagcname">员工号,姓,名,时间,邮件,名字</Parameter>
	//<Parameter Name="tagname">uid,sn,givenname,ou,mail,cn</Parameter>
	//<Parameter Name="tagtype">String,String,String,Date,String,Hidden</Parameter>
	//<Parameter Name="tagtlength">10,6,10,20,50,20</Parameter>
	//<Parameter Name="tagvalue">00001,sn,givenname,2003-01-01,mail,cn</Parameter>
	//<Parameter Name="tagcondtion">NES,NES,NES,ou,mail,cn</Parameter>	

	String name = null;
	String key = null;
	String type = "String";
	int length = 0;
	String value = "";
	boolean isNotNull = false;

	/**
	 * Constructor for DBField.
	 */
	public FormField(String keyi, String namei) {
		name = namei;
		key = keyi;

	}

	public void setLength(int len) {
		length = len;
	}

	public void setNullFlg(boolean fg) {
		isNotNull = fg;
	}

	public void setType(String tp) {
		type = tp;
	}
	
	public void setValue(String valuei) {
		value = valuei;
	}

	public boolean getNullFlg() {
		return isNotNull;
	}
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	public String getKey() {
		return key;
	}
	public int getLength() {
		return length;
	}
	public String getValue() {
		return value;
	}

}
