package org.abel.service.base.message;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class StringEncoder {

	static String objencode = "UTF-8";
	static String oriencode = "GBK";

	/**
	 * 
	 */
	public StringEncoder() {
		super();
	}

	public static String encode(String aa) {
		return encode(aa, oriencode, objencode);
	}

	public static String decode(String aa) {

		return encode(aa, objencode, oriencode);
	}

	public static String encode(
		String aa,
		String orgencode,
		String tarencode) {
		String rt = null;
		if (aa != null) {
			try {
				byte[] bta = aa.getBytes(orgencode);
				rt = new String(bta, tarencode);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return rt;
	}

	public static void main(String[] args) throws Exception{

		//StringEncoder.encode("")
		
		String  aaa="����";		
		byte[] bts=aaa.getBytes("GBK");
		String bbb=new String(bts,"iso8859-1");
		byte[] bts2=bbb.getBytes("iso8859-1");		
		
		for(int i=0;i<bts.length;i++) System.out.print(" "+bts[i]);
		for(int i=0;i<bts2.length;i++) System.out.print(" "+bts2[i]);	
	}
}