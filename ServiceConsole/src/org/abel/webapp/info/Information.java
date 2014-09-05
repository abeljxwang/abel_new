/*
 * 创建日期 2004-4-2
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.info;

import java.util.Date;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class Information {
	
	String title;
	String content;
	String creator;
	Date createtime;
	InforMeta imeta;
	

	/**
	 * 构造函数：
	 */
	public Information() {
		this.title=null;
		this.content=null;
	}
	
	public Information(String title, String content){
		this.title=title;
		this.content=content;
	
	}
	
	//
	public void setMeta(InforMeta imeta){
		this.imeta=imeta;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public void setContent(String content){
		this.content=content;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public void setCreateTime(Date createtime){
		this.createtime=createtime;
	}
	
	public static void main(String[] args) {
	}
	/**
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @return
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @return
	 */
	public InforMeta getMeta() {
		return imeta;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

}
