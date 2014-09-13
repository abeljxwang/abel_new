/*
 * Copyright (C) The IBM China BCS. All rights reserved.
 *
 */

package org.abel.service.base.message;

import java.util.*;




/**
 *  GroupRecord
 *.
 *  A basic object used to ref CCB GROUP message. 
 *  Function:
 *     1: Transfer a file to a MessageObject or MsgObject
 *     2: Transfer a String to a MessageObject or MsgObject
 *     3: Transfer a MessageObject or MsgObject to a String
  *
  * @author  Abel Wang;
  *
  *  @since 0.8.0 
  * */

public class GroupRecord {

	private String name; //the name of record
	private Vector subkeys = new Vector(); //the name of every value
	private Vector values = new Vector(); //values
	private Properties attributes = new Properties(); //record attributes 
	//对应每个key的type，以支持多层组合数据类型
	private ArrayList types = new ArrayList();
	
	//类型常量，代表普通String类型
	public final static String TYPE_STRING = "S";
	//类型常量，代表普通子GroupRecord类型，因为多半为GroupRecord的数组，所以命名为GA
	public final static String TYPE_GROUP_ARRAY = "GA";
	
	public static final String EXCEPTION_CODE_ERROR_TYPE = "";
	
	/**
	 * 设置本GroupRecord的名字
	 * @param nn
	 */
	public void setName(String nn) {
		name = nn;
	}

	/**
	 * 获取本GroupRecord的名字
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置普通子域的值
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value) {
		subkeys.add(key);
		values.add((subkeys.size() - 1), value);
		types.add(TYPE_STRING);
	}
	
	/**
	 * 设置Group子域的值，输入参数ArrayList中的每个成员都是一个名字与key相同的GroupRecord
	 * @param key
	 * @param value
	 */
	public void setSubGroups(String key, ArrayList groups) {
		subkeys.add(key);
		values.add((subkeys.size() - 1), groups);
		types.add(TYPE_GROUP_ARRAY);
	}
	
	/**
	 * 统一返回一个索引顺序指定的域值，越界返回null
	 * @param i
	 * @return
	 */
	public Object getFieldValue(int i){
		//越界则返回null
		if(i>=subkeys.size()||i<0) return null;
		return values.elementAt(i);
	}
	
	/**
	 * 统一返回一个域名指定的域值，越界返回null
	 * @param i
	 * @return
	 */
	public Object getFieldValue(String key){
		int i = subkeys.indexOf(key);
		return getFieldValue(i);
	}
	
	/**
	 * 返回一个索引顺序对应域的类型
	 * @param i
	 * @return
	 */
	public String getFieldType(int i){
		//越界则返回null
		if(i>=types.size()||i<0) return null;
		return (String)types.get(i);
	}
	
	/**
	 * 返回一个域名对应域的类型
	 * @param key
	 * @return
	 */
	public String getFieldType(String key){
		int i = subkeys.indexOf(key);
		return getFieldType(i);
	}
	
	
	/**
	 * 通过索引获取String类型值域的值，外部函数会保证这里的索引不会越界
	 * 这里需要保证类型正确，否则抛出异常
	 * @param vv
	 * @param i
	 * @return
	 */
	private String getVecytorValue(Vector vv, int i) throws Exception{
		//获取类型信息
		Object obj = types.get(i);
		if(null==obj) 
			throw new Exception(EXCEPTION_CODE_ERROR_TYPE);
		//如果不是String类型，抛出异常
		if(!TYPE_STRING.equalsIgnoreCase((String)obj)) 
			throw new Exception(EXCEPTION_CODE_ERROR_TYPE);
		Object or = vv.elementAt(i);
		String result = null;
		if (or != null)
			result = or.toString();
		return result;
	}
	
	/**
	 * 通过索引获取GroupArray类型值域的值，外部函数会保证这里的索引不会越界
	 * 这里需要保证类型正确，否则抛出异常
	 * @param vv
	 * @param i
	 * @return
	 * @throws AdapterException
	 */
	private ArrayList getSubGroupValueFromVector(Vector vv, int i) throws Exception{
		//获取类型信息
		Object obj = types.get(i);
		if(null==obj) 
			throw new Exception(EXCEPTION_CODE_ERROR_TYPE);
		//如果不是String类型，抛出异常
		if(!TYPE_GROUP_ARRAY.equalsIgnoreCase((String)obj)) 
			throw new Exception(EXCEPTION_CODE_ERROR_TYPE);
			
		Object o = vv.elementAt(i);
		if(null==o) return null;
		return (ArrayList)o;
	}
	
	/**
	 * 通过通过顺序号域的值,用于子Group类型
	 * @param i
	 * @return
	 * @throws AdapterException
	 */
	public ArrayList getSubGroups(int i) throws Exception{
		//越界则返回null
		if(i>=subkeys.size()||i<0) return null;
		return getSubGroupValueFromVector(values, i);
	}
	
	/**
	 * 通过域名获取域的值，用于子Group类型
	 * @param key
	 * @return
	 * @throws AdapterException
	 */
	public ArrayList getSubGroups(String key) throws Exception{
		return getSubGroups(subkeys.indexOf(key));
	}
	
	/**
	 * 通过顺序获号取域的值,用于普通类型
	 * @param i
	 * @return
	 */
	public String getValue(int i) throws Exception{
		//越界则返回null
		if(i>=subkeys.size()||i<0) return null;
		return getVecytorValue(values, i);
	}
	
	/**
	 * 通过域名获取域的值，用于普通类型
	 * @param key
	 * @return
	 */
	public String getValue(String key) throws Exception{
		return getValue(subkeys.indexOf(key));
	}
	
	/**
	 * 根据索引得到一个key值
	 * @param i
	 * @return
	 * @throws AdapterException
	 */
	public String getSubKey(int i){
		//越界则返回null
		if(i>=subkeys.size()||i<0) return null;
		
		Object obj = subkeys.elementAt(i);
		if(null==obj) return null;
		return (String)obj;
	}
	
	/**
	 * 返回所有的值
	 * @return
	 */
	public Vector getValues() {
		return values;
	}
	
	/**
	 * 返回Group的大小，即包含多少子域，这里将所有同名的子Group算作一个域
	 * @return
	 */
	public int getValueSize() {
		return subkeys.size();
	}
	
	/**
	 * 设置域属性
	 * @param key
	 * @param attr
	 */
	public void setAttribute(String key, String attr) {
		attributes.setProperty(key, attr);
	}
	
	/**
	 * 获取域属性
	 * @param key
	 * @return
	 */
	public String getAttribute(String key) {
		return attributes.getProperty(key);
	}

	public void setAttributes(Properties attr) {
		attributes = attr;
	}
	public Properties getAttributes() {
		return attributes;
	}
	
	
	private String toString(int level) {
		StringBuffer sb = new StringBuffer();
		for(int j=0; j<level; j++)
			sb.append("\t");
		sb.append("GroupRecord\t" + name + "\n");
		for(int i=0; i<this.getValueSize(); i++){
			String key = this.getSubKey(i);
			String type = this.getFieldType(i);
			Object obj = this.getFieldValue(i);
			if(TYPE_STRING.equalsIgnoreCase(type)){
				for(int j=0; j<=level; j++)
					sb.append("\t");
				sb.append(key + "=" + obj.toString() + "\n");
			}
			if(TYPE_GROUP_ARRAY.equalsIgnoreCase(type)){
				ArrayList al = (ArrayList)obj;
				for(int k=0; k<al.size(); k++){
					GroupRecord gr = (GroupRecord)al.get(k);
					sb.append(gr.toString(level+1));
				}
			}
		}	
		return sb.toString();
	}
	
	
	public String toString() {
		/*	StringBuffer sb = new StringBuffer();
			
			sb.append("GroupRecord{").append("|name="+name);
			sb.append("***Subkeys:***");
			for (int i = 0; i < this.subkeys.size(); i++) {
				String key = (String) subkeys.get(i);
				sb.append("|key" + i + "=").append(key);
			}
			sb.append("***Valuse:***");
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
			*/
			return toString(0);
		}
	
	public static void main(String argv[]) {
		GroupRecord record = new GroupRecord();
		record.setName("G");
		record.setValue("key1", "value1");
		record.setValue("key2", "value2");
		
		ArrayList al = new ArrayList();
		GroupRecord gr1 = new GroupRecord();
		gr1.setName("GS");
		gr1.setValue("A", "a");
		gr1.setValue("B", "b");
		al.add(gr1);
		GroupRecord gr2 = new GroupRecord();
		gr2.setName("GS");
		gr2.setValue("A", "aa");
		gr2.setValue("B", "bb");
		al.add(gr2);
		
		record.setSubGroups("GS", al);
		System.out.println(record.toString());
		
		System.out.println("key1=" + record.getFieldValue("key1"));
		System.out.println("key1's type=" + record.getFieldType("key1"));
		System.out.println("GS=" + record.getFieldValue("GS").toString());
		System.out.println("GS's type=" + record.getFieldType("GS"));
	}
}//end of class