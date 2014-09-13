/*
 * 创建日期 2004-12-4
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.framework;

/**
 * @author abelwang
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class WebAppRuningMsg {
	static String workingpath =null;
	
	public static void setWorkingPath(String url){
		workingpath=url;
	}
	public static String getWorkingPath(){
		return workingpath;
	}

}
