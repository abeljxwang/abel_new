/*
 * 创建日期 2006-12-15
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.cnpc.oms.poc;

import java.util.Vector;

/**
 * @author abelwang
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class MenueElement {
	String name = null;
	String url=null;
	Vector child_elements = new Vector();

	public String getName() {
		return name;
	}
	public void setURL(String eurl) {
		url = eurl;
	}

	public void setName(String ename){
		name=ename;
	}
	
	public String getURL() {
		return url;
	}
	
	public void addElement(MenueElement me) {
		if (me != null)
		child_elements.add(me);
	}

	public MenueElement getElement(int i) throws Exception {
		if(i<child_elements.size())
			return (MenueElement)child_elements.elementAt(i);
		else
			return null;
	}


	

}
