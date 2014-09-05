/*
 * Copyright (C) The IBM China BCS. All rights reserved.
 *
 */

package org.abel.service.base.message;

import java.io.Serializable;
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
	private Vector<String> subkeys = new Vector<String>(); //the name of every value
	private Vector<Serializable> values = new Vector<Serializable>(); //values
	private Properties attributes = new Properties(); //record attributes 
	//��Ӧÿ��key��type����֧�ֶ������������
	private ArrayList<String> types = new ArrayList<String>();
	
	//���ͳ����������ͨString����
	public final static String TYPE_STRING = "S";
	//���ͳ����������ͨ��GroupRecord���ͣ���Ϊ���ΪGroupRecord�����飬��������ΪGA
	public final static String TYPE_GROUP_ARRAY = "GA";
	
	public static final String EXCEPTION_CODE_ERROR_TYPE = "";
	
	/**
	 * ���ñ�GroupRecord������
	 * @param nn
	 */
	public void setName(String nn) {
		name = nn;
	}

	/**
	 * ��ȡ��GroupRecord������
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * ������ͨ�����ֵ
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value) {
		subkeys.add(key);
		values.add((subkeys.size() - 1), value);
		types.add(TYPE_STRING);
	}
	
	/**
	 * ����Group�����ֵ���������ArrayList�е�ÿ����Ա����һ��������key��ͬ��GroupRecord
	 * @param key
	 * @param value
	 */
	public void setSubGroups(String key, ArrayList<GroupRecord> groups) {
		subkeys.add(key);
		values.add((subkeys.size() - 1), groups);
		types.add(TYPE_GROUP_ARRAY);
	}
	
	/**
	 * ͳһ����һ������˳��ָ������ֵ��Խ�緵��null
	 * @param i
	 * @return
	 */
	public Object getFieldValue(int i){
		//Խ���򷵻�null
		if(i>=subkeys.size()||i<0) return null;
		return values.elementAt(i);
	}
	
	/**
	 * ͳһ����һ������ָ������ֵ��Խ�緵��null
	 * @param i
	 * @return
	 */
	public Object getFieldValue(String key){
		int i = subkeys.indexOf(key);
		return getFieldValue(i);
	}
	
	/**
	 * ����һ������˳���Ӧ�������
	 * @param i
	 * @return
	 */
	public String getFieldType(int i){
		//Խ���򷵻�null
		if(i>=types.size()||i<0) return null;
		return (String)types.get(i);
	}
	
	/**
	 * ����һ�������Ӧ�������
	 * @param key
	 * @return
	 */
	public String getFieldType(String key){
		int i = subkeys.indexOf(key);
		return getFieldType(i);
	}
	
	
	/**
	 * ͨ�������ȡString����ֵ���ֵ���ⲿ����ᱣ֤����������Խ��
	 * ������Ҫ��֤������ȷ�������׳��쳣
	 * @param vv
	 * @param i
	 * @return
	 */
	private String getVecytorValue(Vector<Serializable> vv, int i) throws Exception{
		//��ȡ������Ϣ
		Object obj = types.get(i);
		if(null==obj) 
			throw new Exception(EXCEPTION_CODE_ERROR_TYPE);
		//�����String���ͣ��׳��쳣
		if(!TYPE_STRING.equalsIgnoreCase((String)obj)) 
			throw new Exception(EXCEPTION_CODE_ERROR_TYPE);
		Object or = vv.elementAt(i);
		String result = null;
		if (or != null)
			result = or.toString();
		return result;
	}
	
	/**
	 * ͨ�������ȡGroupArray����ֵ���ֵ���ⲿ����ᱣ֤����������Խ��
	 * ������Ҫ��֤������ȷ�������׳��쳣
	 * @param vv
	 * @param i
	 * @return
	 * @throws AdapterException
	 */
	private ArrayList<?> getSubGroupValueFromVector(Vector<Serializable> vv, int i) throws Exception{
		//��ȡ������Ϣ
		Object obj = types.get(i);
		if(null==obj) 
			throw new Exception(EXCEPTION_CODE_ERROR_TYPE);
		//�����String���ͣ��׳��쳣
		if(!TYPE_GROUP_ARRAY.equalsIgnoreCase((String)obj)) 
			throw new Exception(EXCEPTION_CODE_ERROR_TYPE);
			
		Object o = vv.elementAt(i);
		if(null==o) return null;
		return (ArrayList)o;
	}
	
	/**
	 * ͨ��ͨ��˳������ֵ,������Group����
	 * @param i
	 * @return
	 * @throws AdapterException
	 */
	public ArrayList getSubGroups(int i) throws Exception{
		//Խ���򷵻�null
		if(i>=subkeys.size()||i<0) return null;
		return getSubGroupValueFromVector(values, i);
	}
	
	/**
	 * ͨ�������ȡ���ֵ��������Group����
	 * @param key
	 * @return
	 * @throws AdapterException
	 */
	public ArrayList getSubGroups(String key) throws Exception{
		return getSubGroups(subkeys.indexOf(key));
	}
	
	/**
	 * ͨ��˳����ȡ���ֵ,������ͨ����
	 * @param i
	 * @return
	 */
	public String getValue(int i) throws Exception{
		//Խ���򷵻�null
		if(i>=subkeys.size()||i<0) return null;
		return getVecytorValue(values, i);
	}
	
	/**
	 * ͨ�������ȡ���ֵ��������ͨ����
	 * @param key
	 * @return
	 */
	public String getValue(String key) throws Exception{
		return getValue(subkeys.indexOf(key));
	}
	
	/**
	 * �������õ�һ��keyֵ
	 * @param i
	 * @return
	 * @throws AdapterException
	 */
	public String getSubKey(int i){
		//Խ���򷵻�null
		if(i>=subkeys.size()||i<0) return null;
		
		Object obj = subkeys.elementAt(i);
		if(null==obj) return null;
		return (String)obj;
	}
	
	/**
	 * �������е�ֵ
	 * @return
	 */
	public Vector<Serializable> getValues() {
		return values;
	}
	
	/**
	 * ����Group�Ĵ�С����������������ｫ����ͬ�����Group����һ����
	 * @return
	 */
	public int getValueSize() {
		return subkeys.size();
	}
	
	/**
	 * ����������
	 * @param key
	 * @param attr
	 */
	public void setAttribute(String key, String attr) {
		attributes.setProperty(key, attr);
	}
	
	/**
	 * ��ȡ������
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
		
		ArrayList<GroupRecord> al = new ArrayList<GroupRecord>();
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