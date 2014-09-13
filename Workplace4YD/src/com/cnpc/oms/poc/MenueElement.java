/*
 * �������� 2006-12-15
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.cnpc.oms.poc;

import java.util.Vector;

/**
 * @author abelwang
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class MenueElement {
	String name = null;
	String url=null;
	Vector child_elements = new Vector();

	public String getName() {
		return name;
	}
	public void setURL(String eurl) {
		url = eurl;
	}

	public void setName(String ename){
		name=ename;
	}
	
	public String getURL() {
		return url;
	}
	
	public void addElement(MenueElement me) {
		if (me != null)
		child_elements.add(me);
	}

	public MenueElement getElement(int i) throws Exception {
		if(i<child_elements.size())
			return (MenueElement)child_elements.elementAt(i);
		else
			return null;
	}


	

}
