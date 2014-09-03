/*
 * 创建日期 2005-4-25
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.entityutil;

import java.util.ArrayList;
import java.util.Hashtable;

import org.abel.service.wsdlengine.common.ConstantVariables;
import org.abel.service.wsdlengine.common.WSDLEngineException;
import org.abel.service.wsdlengine.dao.EaiMessageStructureDAO;
import org.abel.service.wsdlengine.dao.EaiMultiReqMsgDAO;
import org.abel.service.wsdlengine.dao.EaiMultiRespMsgDAO;
import org.abel.service.wsdlengine.dao.SubTransactionStructureDAO;
import org.abel.service.wsdlengine.entity.TransactionStructure;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class TransactionStructureHelper {

	/**
	 * 
	 */
	public TransactionStructureHelper() {
		super();
	}

	/**
	 * Set a transactionStructure's extendedMessage Structure(request message and response message)  
	 * @param transactionStructure
	 * @param extendMessageList
	 * @param transactionID
	 */
	public void loadSingleStepTransactionExtendMessage(
		TransactionStructure transactionStructure,
		Hashtable extendMessageList,
		String subTransactionID,
		String appSystemID,
		ArrayList groupMessageList)
		throws WSDLEngineException {

		try {
			// get extended message
			ArrayList requestExtendedMessage = null;
			ArrayList responseExtendedMessage = null;
			TransactionStructure tempTransactionStructure =
				(TransactionStructure) extendMessageList.get(appSystemID);

			requestExtendedMessage =
				tempTransactionStructure.getRequestExtendedMessage();
			responseExtendedMessage =
				tempTransactionStructure.getResponseExtendedMessage();

			ArrayList temp = null;

			// set extended message 
			// mh is used to set data type
			MetaDataHelper mh = new MetaDataHelper();

			temp = transactionStructure.getRequestExtendedMessage();
			temp.addAll(requestExtendedMessage);
			mh.setMetaData4Message(temp, groupMessageList);

			//mh.setMetaData4Message(requestExtendedMessage,new ArrayList());

			transactionStructure.setRequestExtendedMessage(temp);

			temp = transactionStructure.getResponseExtendedMessage();
			temp.addAll(responseExtendedMessage);
			mh.setMetaData4Message(temp, groupMessageList);
			transactionStructure.setResponseExtendedMessage(temp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load a transactionStructure's message structure.
	 * @param transactionStructure
	 */
	public void loadSingleTransactionMessage(
		TransactionStructure transactionStructure,
		String subTransactionID)
		throws WSDLEngineException {

		ArrayList groupMessageList = new ArrayList();
		try {

			SubTransactionStructureDAO tempSubTransactionStructureDAO =
				new SubTransactionStructureDAO();

			// get requestMessageID and responseMessageID 
			long[] messageID =
				tempSubTransactionStructureDAO
					.getResquestMessageIDAndResponseMessageID(
					subTransactionID);

			long requestMessageID = messageID[0];
			long responseMessageID = messageID[1];

			EaiMessageStructureDAO tempEaiMessageStructure =
				new EaiMessageStructureDAO();
			ArrayList requestMessageList =
				tempEaiMessageStructure.getMessageList(requestMessageID);
			ArrayList responseMessageList =
				tempEaiMessageStructure.getMessageList(responseMessageID);

			// mh is used to set data type
			MetaDataHelper mh = new MetaDataHelper();
			mh.setMetaData4Message(requestMessageList, groupMessageList);
			mh.setMetaData4Message(responseMessageList, groupMessageList);
			// set group Message List
			ArrayList groupMessage =
				tempEaiMessageStructure.getGroupMessageLists(groupMessageList);
			ArrayList gm = null;
			for (int o = 0; o < groupMessage.size(); o++) {
				gm = (ArrayList) groupMessage.get(o);
				mh.setMetaData4Message(gm, new ArrayList());
			}

			// delete the duplicated message item.
			MessageStructureHelper msh = new MessageStructureHelper();
			requestMessageList =
				msh.DelDuplicate(
					requestMessageList,
					ConstantVariables.DUPLICATE_SINGLE);
			responseMessageList =
				msh.DelDuplicate(
					responseMessageList,
					ConstantVariables.DUPLICATE_SINGLE);

			transactionStructure.setGroupMessage(groupMessage);
			transactionStructure.setRequestMessage(requestMessageList);
			transactionStructure.setResponseMessage(responseMessageList);

		} catch (WSDLEngineException e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}
	}

	/**
	 * Load a multiple steps transation's extended message
	 * @param transactionStructure
	 * @param extendMessageList
	 * @param subTransactionIDSet
	 */
	public void loadMultiStepTransactionExtendMessage(
		TransactionStructure transactionStructure,
		Hashtable extendMessageList,
		ArrayList subTransactionIDSet,
		ArrayList groupMessageList)
		throws WSDLEngineException {

		String transactionID = null;

		// get appSystemIDSet from transactionID set.
		ArrayList appSystemIDSet = null;

		SubTransactionStructureDAO stsdao = new SubTransactionStructureDAO();

		try {
			appSystemIDSet = stsdao.getAppSystemIDSet(subTransactionIDSet);
		} catch (WSDLEngineException e) {

			e.printStackTrace();
			throw new WSDLEngineException();
		}

		TransactionStructure iteratorTransactionSture = null;
		String iteratorappSystemID = null;
		ArrayList requestExtendMessageList = new ArrayList();
		ArrayList responseExtendMessageList = new ArrayList();

		for (int j = 0; j < appSystemIDSet.size(); j++) {
			iteratorappSystemID = (String) appSystemIDSet.get(j);
			iteratorTransactionSture =
				(TransactionStructure) extendMessageList.get(
					iteratorappSystemID);
			requestExtendMessageList.addAll(
				iteratorTransactionSture.getRequestExtendedMessage());
			responseExtendMessageList.addAll(
				iteratorTransactionSture.getResponseExtendedMessage());
		}

		// set extended message 
		// mh is used to set data type
		MetaDataHelper mh = new MetaDataHelper();
		mh.setMetaData4Message(requestExtendMessageList, groupMessageList);
		mh.setMetaData4Message(responseExtendMessageList, groupMessageList);

		transactionStructure.setRequestExtendedMessage(
			requestExtendMessageList);
		transactionStructure.setResponseExtendedMessage(
			responseExtendMessageList);
	}

	/**
	 * Load a mutlitple steps transaction's message
	 * @param transactionStructure
	 * @throws WSDLEngineException
	 */
	public void loadMutiStepTransactionMessage(
		TransactionStructure iTransactionStructure,
		TransactionStructure transactionStructure)
		throws WSDLEngineException {
		try {

			// get request message field name and correspondent sub transaction message id and field.
			ArrayList reqMultiMessageSet =
				new EaiMultiReqMsgDAO().getReqMessageSet(
					iTransactionStructure.getRequestMultiMsgID());

			ArrayList resMultiMessageSet =
				new EaiMultiRespMsgDAO().getRespMessageSet(
					iTransactionStructure.getResponseMultiMsgID());

			// get correspondent message List contain data type information. 
			ArrayList requestMessageList =
				new EaiMessageStructureDAO().getMultiStepReqMessage(
					reqMultiMessageSet);
			ArrayList responseMessageList =
				new EaiMessageStructureDAO().getMultiStepResMessage(
					resMultiMessageSet);

			ArrayList groupMessageList = new ArrayList();

			MetaDataHelper mh = new MetaDataHelper();
			
			mh.setMetaData4Message(requestMessageList, groupMessageList);
			mh.setMetaData4Message(responseMessageList, groupMessageList);

			// set group Message List
			EaiMessageStructureDAO tempEaiMessageStructure =
				new EaiMessageStructureDAO();
			ArrayList groupMessage =
				tempEaiMessageStructure.getGroupMessageLists(groupMessageList);
			ArrayList gm = null;
			for (int o = 0; o < groupMessage.size(); o++) {
				gm = (ArrayList) groupMessage.get(o);
				
				mh.setMetaData4Message(gm, new ArrayList());
			}

			MessageStructureHelper msh = new MessageStructureHelper();
			requestMessageList =
				msh.DelDuplicate(
					requestMessageList,
					ConstantVariables.DUPLICATE_MULTIPLE);
			responseMessageList =
				msh.DelDuplicate(
					responseMessageList,
					ConstantVariables.DUPLICATE_MULTIPLE);
			transactionStructure.setGroupMessage(groupMessage);
			transactionStructure.setRequestMessage(requestMessageList);
			transactionStructure.setResponseMessage(responseMessageList);

		} catch (WSDLEngineException e) {
			e.printStackTrace();
		}

	}

}
