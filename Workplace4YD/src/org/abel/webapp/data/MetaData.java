/*
 * 创建日期 2004-2-26
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.data;

import java.util.*;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class MetaData {
	String name;
	String description="one metadata";
	Properties attributes=new Properties();
	
	
	
	/**
	 * 
	 */
	public MetaData(String name) {
		this.name=name;
	}
	public String getname(){
	return name;
	
	}
	
	public void setAttribute(String key,Properties pro){
		attributes.put(key,pro);
	}
	
	public Enumeration getkeys(){
		return attributes.keys();
	}

	public Set getFieldNames(){
		return attributes.keySet();
	}
	

	public static void main(String[] args) {
	}
}
