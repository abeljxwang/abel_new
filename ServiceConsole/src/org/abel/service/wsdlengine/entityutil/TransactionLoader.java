/*
 * 创建日期 2005-4-20
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.entityutil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.abel.service.wsdlengine.common.ConstantVariables;
import org.abel.service.wsdlengine.common.WSDLEngineException;
import org.abel.service.wsdlengine.dao.AppSystemDAO;
import org.abel.service.wsdlengine.dao.EaiMessageStructureDAO;
import org.abel.service.wsdlengine.dao.EaiTransListDAO;
import org.abel.service.wsdlengine.dao.EaiTransStructureDAO;
import org.abel.service.wsdlengine.dao.MessageControlDAO;
import org.abel.service.wsdlengine.dao.MetaDataDAO;
import org.abel.service.wsdlengine.dao.SubTransactionStructureDAO;
import org.abel.service.wsdlengine.database.DBHelper;
import org.abel.service.wsdlengine.entity.MessageStructure;
import org.abel.service.wsdlengine.entity.TransactionStructure;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class TransactionLoader {

	/**
	 *  Load transaction.
	 */
	public TransactionLoader() {
		super();
	}

	/**
	 * load all the transaction structure information.
	 * @return
	 */
	public void loadAllTransaction(Hashtable transactionStructures)
		throws WSDLEngineException {
		ArrayList transactionBasicSet = null;

		try {

			// get all the transactionIDs 
			transactionBasicSet =
				new EaiTransListDAO().getTransactionBasicList();
			Connection conn = new DBHelper().getConnection();

			// load extended message by appSystemID
			// structure of extendMessage: 
			// Hashtable: index is appSystemID, value is  
			Hashtable extendMessageList = loadExtendedMessage();

			// load every transaction structure info. by transactionID.
			TransactionStructure singleTransactionStructure = null,
				iTransactionStructure = null;
			String transactionID = null;

			for (int i = 0; i < transactionBasicSet.size(); i++) {
				iTransactionStructure =
					(TransactionStructure) transactionBasicSet.get(i);
				transactionID = iTransactionStructure.getTransactionID();
				singleTransactionStructure =
					loadSingleTransaction(
						iTransactionStructure,
						extendMessageList);

				transactionStructures.put(
					transactionID,
					singleTransactionStructure);
		
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException("");
		}

	}

	/**
	 * Load all information need in a single transaction specified by a transactionID.
	 * @param transactionID
	 * @return
	 */
	public TransactionStructure loadSingleTransaction(
		TransactionStructure iTransactionStructure,
		Hashtable extendMessageList)
		throws WSDLEngineException {

		TransactionStructure transactionStructure = new TransactionStructure();
		TransactionStructureHelper transactionStructureHelper =
			new TransactionStructureHelper();
		ArrayList groupMessageList = new ArrayList();

		try {
			String transactionID = iTransactionStructure.getTransactionID();
			// get sub transaction id
			ArrayList subTransactionIDSet =
				new EaiTransStructureDAO().getSubTransactionIDList(
					transactionID);
			// load all the sub transaction structure
			if (subTransactionIDSet.size() > 1) {
				// multiple steps operation 
				
				transactionStructureHelper.loadMultiStepTransactionExtendMessage(transactionStructure,extendMessageList,subTransactionIDSet,groupMessageList);
				transactionStructureHelper.loadMutiStepTransactionMessage(iTransactionStructure,transactionStructure);
					

			} else {
				// single step operation
				
				if (subTransactionIDSet.size() > 0) {
					String subTransactionID =
						(String) subTransactionIDSet.get(0);
					String appSystemID =
						new SubTransactionStructureDAO().getAppSystemID(
							subTransactionID);

					transactionStructureHelper
						.loadSingleStepTransactionExtendMessage(
						transactionStructure,
						extendMessageList,
						subTransactionID,
						appSystemID,
						groupMessageList);
					transactionStructureHelper.loadSingleTransactionMessage(
						transactionStructure,
						subTransactionID);
				

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException("");
		}

		return transactionStructure;

	}

	/**
	 * load extended message by AppSystemID
	 * @param conn
	 * @return
	 */
	public Hashtable loadExtendedMessage() throws WSDLEngineException {
		Hashtable result = new Hashtable();

		ArrayList requestExtendedMessageList = new ArrayList();
		ArrayList responseExtendedMessageList = new ArrayList();

		try {

			TransactionStructure transactionStructure = null;
			String appSystemID = null;
			// load AppSystemID Set
			ArrayList appSystemIDSet = null;
			appSystemIDSet = new AppSystemDAO().getAppSystemIDList();
			// load extended message 
			for (int i = 0; i < appSystemIDSet.size(); i++) {
				transactionStructure = new TransactionStructure();
				appSystemID = (String) appSystemIDSet.get(i);
				requestExtendedMessageList =
					new MessageControlDAO().getExtendedMessage(appSystemID,
					//conn,
	ConstantVariables.DATABASE_EXTENDEDMESSAGE_REQUEST);
				responseExtendedMessageList =
					new MessageControlDAO().getExtendedMessage(appSystemID,
					//conn,
	ConstantVariables.DATABASE_EXTENDEDMESSAGE_RESPONSE);
				transactionStructure.setRequestExtendedMessage(
					requestExtendedMessageList);
				transactionStructure.setResponseExtendedMessage(
					responseExtendedMessageList);
				result.put(appSystemID, transactionStructure);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return result;
	}

	/**
		 * Load Group Message structure.
		 * @return Hashtable
		 * @throws WSDLEngineException
		 */
	public Hashtable loadGroupMessage() throws WSDLEngineException {
		Hashtable result = new Hashtable();

		try {
			EaiMessageStructureDAO eaiMessageStructureDAO =
				new EaiMessageStructureDAO();
			MetaDataDAO metaDataDAO = new MetaDataDAO();
			ArrayList groupMetaIdSet = metaDataDAO.getGroupMetaIdSet();
			ArrayList groupMessageList =
				eaiMessageStructureDAO.getGroupMessageList(groupMetaIdSet);
			// construct group message.
			MessageStructure ms = null;
			MessageStructure tempMs = null;
			ArrayList groupMessage = null;
			String groupMessageName = null;
			long groupMessageMetaID;
			long tempMetaID;
			// before the construction, you must sure that the groupMessageList is in order.
			for (int o = 0; o < groupMessageList.size();) {
				ms = (MessageStructure) groupMessageList.get(o);
				// get the group message name.
				groupMessageName = ms.getRequesterFieldName();
				groupMessageMetaID = ms.getMetaID();
				System.out.println("original" + groupMessageMetaID);
				o++;
				groupMessage = new ArrayList();
				MessageStructure a = (MessageStructure) groupMessageList.get(o);
				//				if (groupMessageMetaID ==((MessageStructure)groupMessageList.get(o)).getMetaID()){
				//					System.out.println("ss");
				//				}
				if (groupMessageMetaID
					== ((MessageStructure) groupMessageList.get(o)).getMetaID()) {

					while (groupMessageMetaID
						== ((MessageStructure) groupMessageList.get(o))
							.getMetaID()) {
						// add this message into the group message
						//System.out.println("sssssssssssssssss");
						tempMs = (MessageStructure) groupMessageList.get(o);
						System.out.println(
							groupMessageMetaID
								+ "tempMs==repeat"
								+ tempMs.metaID);
						groupMessage.add(tempMs);

						//				new Long(tempMs.getMetaID());
						//result.put(new Long(tempMs.getMetaID()),groupMessage);
						// construct the group message.

						o++;
					}
				} else {
					System.out.println(
						"temp others:"
							+ (
								(MessageStructure) groupMessageList.get(
									o)).metaID);
					o++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return result;
	}

}
