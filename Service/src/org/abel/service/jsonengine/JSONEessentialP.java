/*
 * 创建日期 2003-10-1
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.jsonengine;

import java.util.*;
import java.io.*;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class JSONEessentialP {

	/**
	 * 
	 */
	static boolean flag = false;
	static Properties pfile = new Properties();

	private JSONEessentialP() {
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
			//System.out.println("参数没有初始化");
		}
		return result;

	}
	
	public static Properties getAll() {
				
		return pfile;

	}

	public static void main(String[] args) throws Exception {

		JSONEessentialP.init("c:\\tst.prop");
		System.out.println(JSONEessentialP.getAll());
	}

}