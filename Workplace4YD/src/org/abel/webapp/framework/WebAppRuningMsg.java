/*
 * �������� 2004-12-4
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.framework;

/**
 * @author abelwang
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class WebAppRuningMsg {
	static String workingpath =null;
	
	public static void setWorkingPath(String url){
		workingpath=url;
	}
	public static String getWorkingPath(){
		return workingpath;
	}

}
