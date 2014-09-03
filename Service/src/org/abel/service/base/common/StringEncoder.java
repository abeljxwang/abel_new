/*
 * 创建日期 2003-10-15
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.base.common;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
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
		
	public static String toUTFString(String aa) {
		String rt = null;
		if (aa != null) {
			try {
				byte[] bta = aa.getBytes("GBK");
				rt = new String(bta, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rt;
	}		
			
	public static void main(String[] args) {
		String aaa="羲";
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
