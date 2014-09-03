/*
 * Copyright (C) The IBM China BCS. All rights reserved.
 *
 */

package org.abel.service.base.message;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;



/* MsgBody
 * 
 *  A basic object used to ref the partion of CCB MesObject. 
 *  Function:
 *     1: Transfer a file to a MessageObject or MsgObject
 *     2: Transfer a String to a MessageObject or MsgObject
 *     3: Transfer a MessageObject or MsgObject to a String
  *
  * @author  Abel Wang;
  *
  *  @since 0.8.0 
  * */
public class MsgBody {

	private Properties elements = new Properties();
	private Vector orders = new Vector();
	private Properties attris = new Properties();
	private String msgid = null;
	//private MsgMeta metas = new MsgMeta();

	private int current_order = 0;

	public MsgBody() {
		super();

	}

	public MsgBody(String msgid) {
		this.msgid = msgid;

	}

	protected void setValues(Properties pvalues) {
		elements = pvalues;
		Enumeration pkeys = pvalues.keys();
		while (pkeys.hasMoreElements()) {
			orders.add(pkeys.nextElement().toString());
		}
		pkeys = null;

	}

	protected void setValue(String key, String value) {
		if (key != null) {
			MetaElement me = new MetaElement();

			me.setValue(value);
			me.setName(key);
			if (orders.indexOf(key) < 0)
				orders.add(key);

			elements.put("KEY_" + orders.indexOf(key), me);
			attris.put("KEY_" + orders.indexOf(key), MSGBASE.FIELD_TYPE_S);

			me = null;
		}
	}

	public String getType(int orderid) {

		//String ordstr=orders.get(orderid);
		String result = null;
		if (attris.get("KEY_" + orderid) != null)
			result = attris.get("KEY_" + orderid).toString();
		return result;
	}

	protected Properties getTypes() {

		return attris;
	}

	protected String getType(String key) {

		String result = null;
		if (key != null) {
			int iii = orders.indexOf(key);
			if (attris.get("KEY_" + iii) != null)
				result = attris.get("KEY_" + iii).toString();
		}
		return result;
	}

	protected void setArrayValue(String key, String value) {
		int orderid = -1;

		MetaElement me = new MetaElement();
		if (key != null) {
			me.setValue(value);
			me.setName(key);
			orderid = orders.size();
			orders.add(key);
			elements.put("KEY_" + orderid, me);
			attris.put("KEY_" + orderid, MSGBASE.FIELD_TYPE_A);
		}
		me = null;
	}

	protected void setArrayValues(String key, ArrayList values){
//		throws Exception {
		if (key != null) {
			int orderid = orders.indexOf(key);
			//if (orderid < 0) {

			MetaElement me;

			orderid = orders.size();
			for (int i = 0; i < values.size(); i++) {
				me = new MetaElement();
				me.setName(key);
				me.setValue(values.get(i).toString());
				orders.add(key);
				elements.put("KEY_" + (orderid + i), me);
				attris.put("KEY_" + (orderid + i), MSGBASE.FIELD_TYPE_A);
			}
			me = null;

			//-------------------------------------------------
			//orders.add(key);
			//elements.put("KEY_" + orders.size(), me);
			//} else {
			//	throw (new Exception("使用了重复的key"));
			//}
		}
	}

	public void setGroupValues(String key, GroupRecord values) {
		if (key != null) {
			int orderid = orders.size();
			values.setName(key);
			orders.add(key);
			elements.put("KEY_" + orderid, values);
			attris.put("KEY_" + orderid, MSGBASE.FIELD_TYPE_G);
		}

	}

	protected void setGroupArrayValues(String key, ArrayList values)
		throws Exception {
		int orderid = -1;
		if (key != null)
			orderid = orders.indexOf(key);
		if (orderid < 0) {

			GroupRecord gr;

			orderid = orders.size();
			for (int i = 0; i < values.size(); i++) {
				gr = (GroupRecord) values.get(i);
				gr.setName(key);
				orders.add(key);
				elements.put("KEY_" + (orderid + i), gr);
				attris.put("KEY_" + (orderid + i), MSGBASE.FIELD_TYPE_G);
			}
			gr = null;

		} else {
			throw (new Exception("使用了重复的key"));
		}

	}

	protected ArrayList getArrayValues(String key) {
		int orderid = -1;
		if (key != null)
			orderid = orders.indexOf(key);
		ArrayList alresult = null;
		if (orderid >= 0) {
			alresult = new ArrayList();
			MetaElement me;

			int endordersid = orders.lastIndexOf(key);
			for (int i = orderid; i <= endordersid; i++) {

				me = new MetaElement();
				if (elements.get("KEY_" + i) != null)
					me = (MetaElement) elements.get("KEY_" + i);
				if (me != null)
					alresult.add(me.getValue());

			}
			me = null;

		}

		return alresult;
	}

	protected ArrayList getArrayElements(String key) {
		ArrayList alresult = null;
		int orderid = -1;

		if (key != null)
			orders.indexOf(key);

		if (orderid >= 0) {
			alresult = new ArrayList();
			MetaElement me;

			int endordersid = orders.lastIndexOf(key);
			//System.out.println(endordersid+" "+orderid);
			//orderid = orders.size();
			for (int i = orderid; i <= endordersid; i++) {

				me = new MetaElement();
				if (elements.get("KEY_" + i) != null)
					me = (MetaElement) elements.get("KEY_" + i);
				if (me != null)
					alresult.add(me);
				//System.out.println(alresult+"--"+i);

			}
			me = null;
		}

		return alresult;
	}

	protected ArrayList getGroupArrayValues(String key) {
		int orderid = -1;
		if (key != null)
			orderid = orders.indexOf(key);
		ArrayList alresult = null;
		if (orderid >= 0) {
			alresult = new ArrayList();
			GroupRecord gr;

			int endordersid = orders.lastIndexOf(key);

			for (int i = orderid; i <= endordersid; i++) {

				gr = new GroupRecord();
				if (elements.get("KEY_" + i) != null)
					gr = (GroupRecord) elements.get("KEY_" + i);
				//me.g
				alresult.add(gr);

			}
			gr = null;

		}

		return alresult;
	}

	public String getValue(String key) {

		String resultstr = null;
		int orderid = -1;
		if (key != null)
			orderid = orders.indexOf(key);
		MetaElement result = null;
		if (orderid >= 0) {
			if (elements.get("KEY_" + orderid) != null)
				try {
					result = (MetaElement) elements.get("KEY_" + orderid);
					resultstr = result.getValue();
				} catch (ClassCastException ce) {

				}
			//System.out.println(orderid);
			return resultstr;
		} else
			return null;

	}

	public String getValue(int id) {

		MetaElement result = null;

		if (elements.get("KEY_" + id) != null)
			result = (MetaElement) elements.get("KEY_" + id);
		return result.getValue();

	}

	public void setElement(String key, MetaElement element) {
		if (key != null) {
			orders.add(key);
			elements.put("KEY_" + orders.lastIndexOf(key), element);
			attris.put("KEY_" + orders.lastIndexOf(key), "");
		}
	}

	protected MetaElement getElement(String key) {

		int orderid = -1;
		if (key != null)
			orderid = orders.indexOf(key);
		MetaElement result = null;

		if (orderid >= 0 && elements.get("KEY_" + orderid) != null)
			result = (MetaElement) elements.get("KEY_" + orderid);
		return result;

	}

	protected Properties getAllElements() {

		return elements;

	}

	public GroupRecord getGroupRecord(int id) {
		GroupRecord result = new GroupRecord();
		if (elements.get("KEY_" + id) != null
			&& elements.get("KEY_" + id) != null)
			result = (GroupRecord) elements.get("KEY_" + id);
		return result;

	}

	protected int getOrder(String key) {

		if (key != null && orders.indexOf(key) >= 0)
			current_order = orders.indexOf(key);

		return current_order;
	}

//	protected MsgMeta getMsgMeta() {
//		return metas;
//	}

	public Vector getKeys() {
		return orders;
	}
	private Properties getAllValues() {
		return elements;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("MsgBody{").append("|msgid="+msgid+"\n");
		sb.append("***key orders***:");
		for (int i = 0; i < this.orders.size(); i++) {
			String key = (String) orders.get(i);
			sb.append("|key" + i + "=").append(key);
		}
		sb.append("\n***element valuse:***\n");
		Enumeration en = elements.keys();
		while(en != null && en.hasMoreElements()) {
			String ekey = (String) en.nextElement();
			Object eVal =  elements.get(ekey);
			if(eVal instanceof MetaElement && eVal != null) {
				MetaElement me = (MetaElement) eVal;
				sb.append("|"+ ekey + "="+ me);
			}else if(eVal instanceof GroupRecord && eVal != null) {
				GroupRecord gr = (GroupRecord) eVal;
				sb.append("|"+ ekey + "="+ gr);
			}else {
				sb.append("|"+ ekey + "="+ eVal);
			}
		}
		sb.append("***attributes***");
		Enumeration enu = attris.keys();
		while (enu != null && enu.hasMoreElements()) {
			String attKey = (String) enu.nextElement();
			String attVal = (String) attris.getProperty(attKey);
			sb.append("|" + attKey + "=" + attVal);
		}
		sb.append("}\n");
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {

		MsgBody mb = new MsgBody();

		mb.setValue("key", "abel");
		mb.setValue("key1", "abel1 1");
		mb.setValue("key1", "abel1 2");
		mb.setArrayValue("key2", "sunkey1");
		mb.setArrayValue("key2", "sunkey2");
		ArrayList al = new ArrayList();
		al.add("xuqiang1");
		al.add("xuqiang2");
		System.out.println(mb.getValue("key1"));
		System.out.println(mb.getType("key1"));
		mb.setArrayValues("key3", al);
		GroupRecord gr = new GroupRecord();
		gr.setValue("name", "tianbing");
		gr.setValue("value", "male");
		mb.setGroupValues("key4", gr);
		
		System.out.println(mb.getKeys());
		//System.out.println(mb.getAllValues());
		//System.out.println(mb.getOrder());		
		System.out.println(mb.getArrayValues("key3"));
		System.out.println(mb.getType("key3"));

		System.out.println(mb.getTypes());
		
		
		System.out.println(mb);
	}
}