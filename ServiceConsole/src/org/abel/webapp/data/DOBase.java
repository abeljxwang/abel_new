/*
 * �������� 2004-3-6
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.data;

import java.io.Serializable;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class DOBase implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DOBase() {

	}
	
	String aaa="basc";
	
	public void set(String a){
		aaa=a;
	}

	public String get(){
		return aaa;
	}

	public static void main(String[] args) {
	}
}
