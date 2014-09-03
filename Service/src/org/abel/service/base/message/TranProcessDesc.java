package org.abel.service.base.message;

import java.util.Properties;

/**
 * @author abelwang
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TranProcessDesc {
	private String id = null;

	private Properties values = new Properties();

	public TranProcessDesc() {

	}

	public TranProcessDesc(String idd) {
		id = idd;
	}
	
	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public void setAttr(String key, String value) {
		values.setProperty(key, value);
	}

	public String get(String key) {
		return values.getProperty(key);
	}

}
