/*
 * �������� 2003-11-29
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.policy.meta;

import java.util.*;
/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class BaseMeta {

	/**
	 * 
	 */
	public BaseMeta() {
	}
	Properties meta = new Properties();

	public void set(String key, Object value) {
		meta.put(key, value);

	}

	public Object get(String key) {
		return meta.get(key);

	}
}
