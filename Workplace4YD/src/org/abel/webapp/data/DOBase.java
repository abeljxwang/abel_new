/*
 * 创建日期 2004-3-6
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.data;

import java.io.Serializable;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class DOBase implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DOBase() {

	}
	
	String aaa="basc";
	
	public void set(String a){
		aaa=a;
	}

	public String get(){
		return aaa;
	}

	public static void main(String[] args) {
	}
}
