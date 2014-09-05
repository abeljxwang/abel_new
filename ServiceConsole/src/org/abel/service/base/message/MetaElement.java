/*
 * Copyright (C) The IBM China BCS. All rights reserved.
 *
 */

package org.abel.service.base.message;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;




/**
 * MetaElement
 * 
 *  A basic object used to ref CCB String, Array message. 
 *  Function:
 *     1: Transfer a file to a MessageObject or MsgObject
 *     2: Transfer a String to a MessageObject or MsgObject
 *     3: Transfer a MessageObject or MsgObject to a String
  *
  * @author  Abel Wang;
  *
  *  @since 0.8.0 
  * */

public class MetaElement {

	private String name;
	private Vector<Object> subnames = new Vector<Object>();	
	private Vector<String> values = new Vector<String>();
	private Properties attributes = new Properties();
	private String type="S";  //S for key/value, A for Array, G for group:: TBD

	/**
	 * 
	 */
	public MetaElement() {
		super();
	}

	public MetaElement(String  tp) {
		type=tp;
	}
	public void setName(String nn) {
		name = nn;
	}
	public String getName() {
		return name;
	}

	public void setValue(String nn) {
		values.add(nn);
	}
	
	//add by mazhemin 2005.3.11
	public void replaceValue(String nn) {
		values.clear();
		values.add(nn);
	}
	//add end
	
	public String getValue() {
		return getValue(0);
	}

	public String getValue(int i) {
		Object oo=null;		
		if(values.size()>i) oo=values.elementAt(i);
		String result=null;
		if(oo!=null) result=oo.toString();
		
		return result;
	}

//	public Vector getValues() {
//		return values;
//	}

	public int getValueSize() {
		return values.size();
	}

	public void setAttribute(String key, String attr) {
		attributes.setProperty(key, attr);
	}
	public String getAttribute(String key) {
		return attributes.getProperty(key);
	}
	
	public void setAttributes(Properties attr) {
		attributes=attr;
	}
	public Properties getAttributes() {
		return attributes;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("MetaElement{").append("|name="+name+"|type="+type+"");
		sb.append("***keys***:");
		for (int i = 0; i < this.subnames.size(); i++) {
			String key = (String) subnames.get(i);
			sb.append("|key" + i + "=").append(key);
		}
		sb.append("***Valuse***:");
		for (int i = 0; i < this.values.size(); i++) {
			String value = (String) values.get(i);
			sb.append("|value" + i + "=").append(value);
		}
		sb.append("***Attributes***");
		Enumeration enu = attributes.keys();
		while (enu != null && enu.hasMoreElements()) {
			String attKey = (String) enu.nextElement();
			String attVal = (String) attributes.getProperty(attKey);
			sb.append("|" + attKey + "=" + attVal);
		}
		sb.append("}\n");
		return sb.toString();
	}
	public static void main(String argv[]) {
		MetaElement me = new MetaElement();
		me.setName("Test");
		me.setValue("value1");
		me.setValue("");
		System.out.println("name="+me.getName());
		System.out.println("key1="+me.getValue());
		System.out.println("key2="+me.getValue(1));
	}

}//end of class
