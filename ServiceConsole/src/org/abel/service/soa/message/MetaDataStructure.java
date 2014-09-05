/*
 * 创建日期 2005-4-28
 * CCB EAIH Project team
 */
package org.abel.service.soa.message;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class MetaDataStructure {

	/**
	 * 
	 */
	public MetaDataStructure() {
		super();

	}
	
	String metaType;
	long metaID;
	String metaName;
	long metaLength;
	
	/**
	 * @return
	 */
	public long getMetaID() {
		return metaID;
	}

	/**
	 * @return
	 */
	public String getMetaType() {
		return metaType;
	}

	/**
	 * @param l
	 */
	public void setMetaID(long l) {
		metaID = l;
	}

	/**
	 * @param string
	 */
	public void setMetaType(String string) {
		metaType = string;
	}

	/**
	 * @return
	 */
	public String getMetaName() {
		return metaName;
	}

	/**
	 * @param string
	 */
	public void setMetaName(String string) {
		metaName = string;
	}

	/**
	 * @return
	 */
	public long getMetaLength() {
		return metaLength;
	}

	/**
	 * @param l
	 */
	public void setMetaLength(long l) {
		metaLength = l;
	}

}
