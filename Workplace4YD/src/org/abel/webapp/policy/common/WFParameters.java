/*
 * 创建日期 2004-3-4
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.policy.common;
import java.util.*;
/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class WFParameters {
	Properties parameters=new Properties();

	/**
	 * 
	 */
	public WFParameters() {
	}
	
	public void set(String key,String value){
		parameters.setProperty(key,value);
	}

	public void setAll(Properties pp){
		parameters=pp;
	}


	public String get(String key){
		return parameters.getProperty(key);
	}
	
	public Properties  getAll(){
		return parameters;
	}

}
