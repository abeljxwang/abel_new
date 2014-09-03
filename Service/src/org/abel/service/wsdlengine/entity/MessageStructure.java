/*
 * 创建日期 2005-4-27
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.entity;

/**
 *
 * 
 */
public class MessageStructure {

	/**
	 * 
	 */
	public MessageStructure() {
		super();
	}
	
	public String requesterFieldName;
	public String providerFieldName;
	public long metaID;
	public String wsdlType;
	public double metaFieldSequence;
	public long messageID;
	
	/**
	 * @return
	 */
	public long getMetaID() {
		return metaID;
	}

	/**
	 * @return
	 */
	public String getRequesterFieldName() {
		return requesterFieldName;
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
	public void setRequesterFieldName(String string) {
		requesterFieldName = string;
	}

	/**
	 * @return
	 */
	public String getProviderFieldName() {
		return providerFieldName;
	}

	/**
	 * @param string
	 */
	public void setProviderFieldName(String string) {
		providerFieldName = string;
	}

	/**
	 * @return
	 */
	public String getWsdlType() {
		return wsdlType;
	}

	/**
	 * @param string
	 */
	public void setWsdlDataType(String string) {
		wsdlType = string;
	}
	

	/**
	 * @return
	 */
	public double getMetaFieldSequence() {
		return metaFieldSequence;
	}

	/**
	 * @param d
	 */
	public void setMetaFieldSequence(double d) {
		metaFieldSequence = d;
	}

	/**
	 * @param string
	 */
	public void setWsdlType(String string) {
		wsdlType = string;
	}

	/**
	 * @return
	 */
	public long getMessageID() {
		return messageID;
	}

	/**
	 * @param l
	 */
	public void setMessageID(long l) {
		messageID = l;
	}

}
