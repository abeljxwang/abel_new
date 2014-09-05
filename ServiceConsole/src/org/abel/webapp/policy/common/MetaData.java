/*
 * �������� 2004-2-26
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.policy.common;

import java.util.*;
import java.io.Serializable;


/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class MetaData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2392435878953840359L;
	String name;
	String description="one metadata";
	Properties attributes=new Properties();
	
	
	
	/**
	 * 
	 */
	public MetaData(String name) {
		this.name=name;
	}
	public String getname(){
	return name;
	
	}
	
	public void setAttribute(String key,Properties pro){
		attributes.put(key,pro);
	}
	
	public Enumeration getkeys(){
		return attributes.keys();
	}

	public Set getFieldNames(){
		return attributes.keySet();
	}
	

	public static void main(String[] args) {
	}
}
