/*
 * �������� 2004-3-4
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.policy.common;
import java.util.*;
/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class WFParameters {
	Properties parameters=new Properties();

	/**
	 * 
	 */
	public WFParameters() {
	}
	
	public void set(String key,String value){
		parameters.setProperty(key,value);
	}

	public void setAll(Properties pp){
		parameters=pp;
	}


	public String get(String key){
		return parameters.getProperty(key);
	}
	
	public Properties  getAll(){
		return parameters;
	}

}
