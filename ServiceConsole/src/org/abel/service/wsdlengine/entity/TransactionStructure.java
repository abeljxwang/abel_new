/*
 * 创建日期 2005-4-19
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.entity;

import java.util.ArrayList;

/**
 * @author wangjx@cn.ibm.com
 * Single transaction structure information.
 * 
 */
public class TransactionStructure {

	/**
	 * 
	 */
	public TransactionStructure() {
		super();
	}
	
	// external transaction ID.
	public String transactionID ;
	
	// mutiple-step transaction request message ID
	public long requestMultiMsgID ;
	
	// mutiple-step transaction response message ID
	public long responseMultiMsgID;
	
	// store extended message structure for request.
	ArrayList requestExtendedMessage = new ArrayList();
	
	// store extended message structure for response.
	ArrayList responseExtendedMessage = new ArrayList();
	
	// store message structure for request.
	ArrayList requestMessage = new ArrayList();
	
	// store message structure for response.
	ArrayList responseMessage = new ArrayList();
	
	// store basic message structure.
	ArrayList basicMessge = new ArrayList();
	
	// store group message structure.
	ArrayList groupMessage = new ArrayList();
	/**
	 * @return
	 */
	public ArrayList getBasicMessge() {
		return basicMessge;
	}

	/**
	 * @return
	 */
	public ArrayList getGroupMessage() {
		return groupMessage;
	}

	/**
	 * @return
	 */
	public ArrayList getRequestExtendedMessage() {
		return requestExtendedMessage;
	}

	/**
	 * @return
	 */
	public ArrayList getRequestMessage() {
		return requestMessage;
	}

	/**
	 * @return
	 */
	public ArrayList getResponseExtendedMessage() {
		return responseExtendedMessage;
	}

	/**
	 * @return
	 */
	public ArrayList getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param list
	 */
	public void setBasicMessge(ArrayList list) {
		basicMessge = list;
	}

	/**
	 * @param list
	 */
	public void setGroupMessage(ArrayList list) {
		groupMessage = list;
	}

	/**
	 * @param list
	 */
	public void setRequestExtendedMessage(ArrayList list) {
		requestExtendedMessage = list;
	}

	/**
	 * @param list
	 */
	public void setRequestMessage(ArrayList list) {
		requestMessage = list;
	}

	/**
	 * @param list
	 */
	public void setResponseExtendedMessage(ArrayList list) {
		responseExtendedMessage = list;
	}

	/**
	 * @param list
	 */
	public void setResponseMessage(ArrayList list) {
		responseMessage = list;
	}
	
	
	/**
	 * @return
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/**
	 * @param string
	 */
	public void setTransactionID(String string) {
		transactionID = string;
	}

	/**
	 * @return
	 */
	public long getRequestMultiMsgID() {
		return requestMultiMsgID;
	}

	/**
	 * @return
	 */
	public long getResponseMultiMsgID() {
		return responseMultiMsgID;
	}

	/**
	 * @param l
	 */
	public void setRequestMultiMsgID(long l) {
		requestMultiMsgID = l;
	}

	/**
	 * @param l
	 */
	public void setResponseMultiMsgID(long l) {
		responseMultiMsgID = l;
	}

}
