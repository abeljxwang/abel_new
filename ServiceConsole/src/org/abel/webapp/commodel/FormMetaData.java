package org.abel.webapp.commodel;


import java.util.Properties;
import java.util.Enumeration;

/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FormMetaData {

	//<Parameter Name="tagcname">员工号,姓,名,时间,邮件,名字</Parameter>
	//<Parameter Name="tagname">uid,sn,givenname,ou,mail,cn</Parameter>
	//<Parameter Name="tagtype">String,String,String,Date,String,Hidden</Parameter>
	//<Parameter Name="tagtlength">10,6,10,20,50,20</Parameter>
	//<Parameter Name="tagvalue">00001,sn,givenname,2003-01-01,mail,cn</Parameter>
	//<Parameter Name="tagcondtion">NES,NES,NES,ou,mail,cn</Parameter>	

	Properties value = new Properties();
	
	/**
	 * Constructor for CmdMetaData.
	 */
	public FormMetaData() {
		value = new Properties();
	}
	public Properties getall() {
		return value;
	}
	public void set(FormField ffd) {
		String tname=ffd.getName();
		value.put(tname,ffd);
	}

	public void set(String name,FormField ffd) {
		value.put(name,ffd);
	}

	public FormField get(String key) {
		FormField rsult = (FormField) value.get(key);
		return rsult;
	}

	public Enumeration getKeys() {
		
		return value.keys();
	}

	public int size() {
		return value.size();
	}

	public static void main(String[] args) {
		try {
			//FormMetaData cmd = new FormMetaData();
			//FormField dbf = new FormField("a", "a1");

			
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
