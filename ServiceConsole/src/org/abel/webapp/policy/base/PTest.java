/*
 * 创建日期 2003-11-29
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.policy.base;

import java.util.*;
import java.text.*;
/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
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
