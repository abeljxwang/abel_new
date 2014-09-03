/*
 * �������� 2005-5-26
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.service.wsdlengine.entity;

/**
 * @author Abel Wnag
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class MultiRespMsg {
	public long responseMessageID;
	public String responseMessageName;
	public long subResponseMessageID;
	public String subResponseMessageName;
	
	/**
	 * @return
	 */
	public long getResponseMessageID() {
		return responseMessageID;
	}

	/**
	 * @return
	 */
	public String getResponseMessageName() {
		return responseMessageName;
	}

	/**
	 * @return
	 */
	public long getSubResponseMessageID() {
		return subResponseMessageID;
	}

	/**
	 * @return
	 */
	public String getSubResponseMessageName() {
		return subResponseMessageName;
	}

	/**
	 * @param l
	 */
	public void setResponseMessageID(long l) {
		responseMessageID = l;
	}

	/**
	 * @param string
	 */
	public void setResponseMessageName(String string) {
		responseMessageName = string;
	}

	/**
	 * @param l
	 */
	public void setSubResponseMessageID(long l) {
		subResponseMessageID = l;
	}

	/**
	 * @param string
	 */
	public void setSubResponseMessageName(String string) {
		subResponseMessageName = string;
	}

}
