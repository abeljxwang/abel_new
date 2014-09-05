/*
 * �������� 2005-5-26
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.service.wsdlengine.entity;

/**
 * @author Abel Wang
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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
