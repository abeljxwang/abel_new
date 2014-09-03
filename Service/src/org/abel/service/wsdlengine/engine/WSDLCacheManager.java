/*
 * 创建日期 2005-5-10
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.engine;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;

import org.abel.service.wsdlengine.common.ConstantVariables;
import org.abel.service.wsdlengine.common.WSDLEngineException;
import org.abel.service.wsdlengine.dao.AppSystemDAO;
import org.abel.service.wsdlengine.dao.EaiMessageStructureDAO;
import org.abel.service.wsdlengine.dao.EaiTransListDAO;
import org.abel.service.wsdlengine.dao.EaiTransStructureDAO;
import org.abel.service.wsdlengine.dao.MessageControlDAO;
import org.abel.service.wsdlengine.dao.MetaDataDAO;
import org.abel.service.wsdlengine.database.DBHelper;
import org.abel.service.wsdlengine.entity.MessageStructure;
import org.abel.service.wsdlengine.entity.TransactionStructure;
import org.abel.service.wsdlengine.entityutil.TransactionLoader;
import org.abel.service.wsdlengine.entityutil.TransactionStructureHelper;



public class WSDLCacheManager {

	// wsdlCacheManager caches the content of transactions.
	private static WSDLCacheManager wsdlCacheManager = null;

	private static Hashtable transactionList = new Hashtable();

	private static Hashtable definitionList = new Hashtable();
	/**
	 * Initialize the WSDLCacheManager using the singleton pattern.
	 * @return WSDLCacheManager
	 */
	public static WSDLCacheManager getInstance() throws WSDLEngineException {

		try {
			if (null == wsdlCacheManager) {
				// if WSDLCacheManager hasn't initialized.
				//TransactionLoader transactionLoader = new TransactionLoader();
				//transactionLoader.loadAllTransaction(transactionList);
				return new WSDLCacheManager();
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return wsdlCacheManager;
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
				System.out.println("========");
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

	/**@deprecated
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

	/**
	 * @return
	 */
	public static Hashtable getTransactionList() {
		return transactionList;
	}

	/**
	 * @param hashtable
	 */
	public static void setTransactionList(Hashtable hashtable) {
		transactionList = hashtable;
	}

	/**
	 * Get a transaction structure from cache by transactionID. and if there is no 
	 * transactionID structure, look for it in db. 
	 * If transactionList= null or there is no this transactionID in db. return null;
	 * @param transactionID
	 * @return TransactionStructure
	 */
	public TransactionStructure getTransactionStructure(String transactionID) {
		TransactionStructure result = null;
		if (null == transactionList) {
			result = null;
			System.out.println(
				"------------transactionList is null-----------------");
		} else {
			result = (TransactionStructure) transactionList.get(transactionID);
			if (null == result) {
				// to load the message structure from db.	
				if (true) {
					result = null;
				}
			}

		}
		return result;
	}

	/**
	 * @return
	 */
	public static Hashtable getDefinitionList() {
		return definitionList;
	}

	/**
	 * @param hashtable
	 */
	public static void setDefinitionList(Hashtable hashtable) {
		definitionList = hashtable;
	}

}
