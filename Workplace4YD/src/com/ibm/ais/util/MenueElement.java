/*
 * �������� 2006-12-15
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.ibm.ais.util;

import java.util.Vector;

/**
 * @author abelwang
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class MenueElement {
	String mid=null;
	String name = null;
	String url=null;
	String up_mid=null;
	Vector child_elements = new Vector();

	public String getID() {
		return mid;
	}
	public String getUpID() {
		return up_mid;
	}
	public String getName() {
		return name;
	}
	public void setURL(String eurl) {
		url = eurl;
	}
	public void setID(String idi){
		mid=idi;
	}
	public void setUpID(String up_idi){
		up_mid=up_idi;
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
	
	public Vector getElements() throws Exception {
		Vector subelements=new Vector();
		
			return subelements;
	}
	
	public int sizeOfChileElement(){
		return child_elements.size();
	}


	

}
