/*
 * �������� 2003-11-29
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.policy.base;

import java.util.*;
import java.text.*;
/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class PTest {

	/**
	 * 
	 */
	public PTest() {

	}

	public static void main(String[] args) throws Exception {



	}

	public static Object pasing(String iii) {

		Object result = null;
		//Calendar cc = Calendar.getInstance();
		Date date = new Date();
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");

		if (iii.equals("CURRENTDATE"))
			result = sdfd.format(date);
		else if (iii.equals("CURRENTTIME"))
			result = sdft.format(date);
		else
			result = iii;

		return result;
	}

}
