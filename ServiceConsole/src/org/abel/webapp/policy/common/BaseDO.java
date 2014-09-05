/*
 * 创建日期 2004-3-4
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.policy.common;

import java.util.*;


import java.io.Serializable;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class BaseDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public BaseDO() {

	}
	boolean renewflag=false;
	String name = "BasicDO";
	String desc = "a Basic data object for testing";
	Properties parameters = new Properties();
	MetaData md;

	Vector approvallist = new Vector();


	public Vector getAapprovalHis() {
		return approvallist;
	}

	public boolean isReNew() {
		return renewflag;
	}

	public void setFlag(final boolean flg) {
		renewflag=flg;
	}

	public void setParameters(final Map pss) {

		parameters.putAll(pss);
		renewflag=true;
	}
	public Properties getParameters() {

		return parameters; ///.putAll(pss);
	}

	public void setParameter(final String key, final String value) {

		parameters.setProperty(key, value);
		renewflag=true;
	}

	public void setParameter(final String key, final Object value) {

		parameters.put(key, value);
		renewflag=true;
	}
	public String getParameter(final String key) {
		return parameters.getProperty(key);
	}

	public Object getObject(final String key) {

		return parameters.get(key);
	}
	


	public void setMetaData(final MetaData md) {

		this.md=md;
		renewflag=true;
	}
	
	public MetaData getMD() {

		return md;
	}	

	public Set getKeys() {

		return md.getFieldNames();
	}	

}
