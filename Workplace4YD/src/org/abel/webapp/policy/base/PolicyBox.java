/*
 * �������� 2003-11-3
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.policy.base;

import java.util.*;
/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class PolicyBox {

	/**
	 * 
	 */
	Properties pilicies = new Properties();

	public void set(String key, Policy p) {
		pilicies.put(key, p);

	}

	public Policy get(String key) {
		return (Policy) pilicies.get(key);

	}

	public PolicyBox() {
		super();

	}

	public static void main(String[] args) {
	}
}
