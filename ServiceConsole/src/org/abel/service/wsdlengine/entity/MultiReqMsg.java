/*
 * 创建日期 2005-5-26
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.wsdlengine.entity;

/**
 * @author Abel Wang
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class MultiReqMsg {
	public long reqMessageID;
	public String reqFieldName;
	public long subReqMessageID;
	public String subReqFieldName;
	  
 
	/**
	 * @return
	 */
	public String getReqFieldName() {
		return reqFieldName;
	}

	/**
	 * @return
	 */
	public long getReqMessageID() {
		return reqMessageID;
	}

	/**
	 * @return
	 */
	public String getSubReqFieldName() {
		return subReqFieldName;
	}

	/**
	 * @return
	 */
	public long getSubReqMessageID() {
		return subReqMessageID;
	}

	/**
	 * @param string
	 */
	public void setReqFieldName(String string) {
		reqFieldName = string;
	}

	/**
	 * @param l
	 */
	public void setReqMessageID(long l) {
		reqMessageID = l;
	}

	/**
	 * @param string
	 */
	public void setSubReqFieldName(String string) {
		subReqFieldName = string;
	}

	/**
	 * @param l
	 */
	public void setSubReqMessageID(long l) {
		subReqMessageID = l;
	}

}
