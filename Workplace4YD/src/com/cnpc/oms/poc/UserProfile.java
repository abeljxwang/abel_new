/*
 * �������� 2006-12-15
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.cnpc.oms.poc;

import java.util.Properties;
import java.util.Vector;

/**
 * @author abelwang
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class UserProfile {
	String uid = null;
	String user_name = null;
	Properties basicinfo = new Properties();

	Vector menu = new Vector();

	public String getUserID() {
		return uid;
	}
	public void setUserID(String userid) {
		uid = userid;
	}

	public String getUserName() {
		return user_name;
	}
	public void setUser(String userid, String uname) {
		uid = userid;
		user_name = uname;
	}

	public void setAttribute(String key, String value) throws Exception {
		if (key == null)
			throw new Exception("userID is null");
		else {
			basicinfo.setProperty(key, value + "");

		}
	}

	public String getAttribute(String key) throws Exception {
		if (key != null)
			return basicinfo.getProperty(key);
		else
			return null;
	}

	public Properties getAllAttributes() throws Exception {
		
			return basicinfo;

	}

	public void add(MenueElement me)  {

		menu.add(me);
	}
	public MenueElement get(int i)  {
		if(i<menu.size())
		return (MenueElement)menu.elementAt(i);
		else return null;
	}
	
	public Vector getAllMenueElement() throws Exception {

		return menu;

	}
	

}
