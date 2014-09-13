/*
 * �������� 2003-10-15
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.common;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class StringEncoder {
	
	static String objencode = "GBK";
	static String oriencode = "UTF-8";

	/**
	 * 
	 */
	public StringEncoder() {
		super();
	}
	

	public static String encode(String aa) {
			String rt = null;
			if (aa != null) {
				try {
					byte[] bta = aa.getBytes(oriencode);
					rt = new String(bta, objencode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return rt;
		}
		
	public static String decode(String aa) {
			String rt = null;
			if (aa != null) {
				try {
					byte[] bta = aa.getBytes(objencode);
					rt = new String(bta,oriencode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return rt;
		}
		
	
	public static String toA7String(String aa) {
			String rt = null;
			if (aa != null) {
				try {
					byte[] bta = aa.getBytes("GBK");
					rt = new String(bta, "iso-8859-1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return rt;
		}
		
	public static String toGBKString(String aa) {
			String rt = null;
			if (aa != null) {
				try {
					byte[] bta = aa.getBytes("iso-8859-1");
					rt = new String(bta, "GBK");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return rt;
		}
		
			
			
	public static void main(String[] args) {
		String aaa="��";
		//String bbb="aaaa";
		String ccc;
		try{
			byte[] aaab=aaa.getBytes("UTF-8");
			
			for(int i=0;i<aaab.length;i++) System.out.println(aaab[i]);
			
			ccc=new String(aaab,"UTF-8");
			System.out.println(ccc+"------");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
