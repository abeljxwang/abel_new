/*
 * �������� 2003-10-1
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.service.soapengine.core;

import java.util.*;
import java.io.*;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class StandCfgPV {

	/**
	 * 
	 */
	static boolean flag = false;
	static Properties pfile = new Properties();

	private StandCfgPV() {
		super();

	}

	public static void init(String file) throws Exception {

		File fff = new File(file);
		if (fff.exists()) {
			pfile.load(new FileInputStream(file));
		} else {
			System.out.println("Configration file: " + file + " is not found");

		}
		flag = true;

	}

	public static void set(String key,String value) {
		pfile.setProperty(key,value);
	}

	public static String get(String key) {
		String result = null;
		if (flag) {
			if (pfile.getProperty(key) != null)
				result = pfile.getProperty(key);

		} else {
			//System.out.println("����û�г�ʼ��");
		}
		return result;

	}
	
	public static Properties getAll() {
				
		return pfile;

	}

	public static void main(String[] args) throws Exception {

		StandCfgPV.init("c:\\tst.prop");
		System.out.println(StandCfgPV.getAll());
	}

}