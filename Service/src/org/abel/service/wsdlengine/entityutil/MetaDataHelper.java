/*
 * 创建日期 2005-4-29
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.entityutil;
import java.util.ArrayList;

import org.abel.service.wsdlengine.common.ConstantVariables;
import org.abel.service.wsdlengine.common.WSDLEngineException;
import org.abel.service.wsdlengine.dao.MetaDataDAO;
import org.abel.service.wsdlengine.entity.MessageStructure;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class MetaDataHelper {

	/**
	 * 
	 */
	public MetaDataHelper() {
		super();

	}

	/**
	 * Get WSDL data type.
	 * @param metaDataType
	 * @return
	 */
	public String getDataWSDLType(String metaDataType) {
		String result = null;
		if (metaDataType.equalsIgnoreCase("C")) {
			result = "string";
		} else if (metaDataType.equalsIgnoreCase("N")) {
			result = "long";
		} else if (metaDataType.equalsIgnoreCase("B")) {
			result = "binary";
			
		} else if (metaDataType.equalsIgnoreCase("G")) {
			result = "group";
			
		} else {
			System.out.println("sorry! there is no such type that be handled:" + metaDataType);
		}
		return result;
	}

	/**
	 * Set wsdl data type field for every message in a list.
	 * @param messageList
	 */
	public void setMetaData4Message(
		ArrayList messageList,
		ArrayList groupMessageList) {

		long metaDataID;
		String metaDataType = null;
		MetaDataDAO metaDataDAO = new MetaDataDAO();
		String wsdlDataString = null;
		MessageStructure ms = null;
		String groupMessageTypeName = null;
		double metaFeildSequence;

		try {
			for (int i = 0; i < messageList.size(); i++) {
				ms = (MessageStructure) messageList.get(i);
				metaDataID = ms.getMetaID();
				metaDataType = (metaDataDAO.getMeta(metaDataID)).getMetaType();
				wsdlDataString =
					new MetaDataHelper().getDataWSDLType(metaDataType);
				//if (ms.getRequesterFieldName().equalsIgnoreCase(""))

				if ("group".equalsIgnoreCase(wsdlDataString)) {
					// if the message field type is group.
					groupMessageTypeName = ms.getRequesterFieldName();
					ms.setWsdlDataType(groupMessageTypeName);
					metaFeildSequence = ms.getMetaFieldSequence();
					if (groupContain(groupMessageList, ms)	== false) {
						// if this message group isn't in the groupMessageList.
						groupMessageList.add(ms);
					} else {
						System.out.println("valid error" + metaFeildSequence);
					}

//					System.out.println(
//						"-----------add--group----------"
//							+ groupMessageTypeName);
				} else if ("binary".equalsIgnoreCase(wsdlDataString)) {
					// if the message field type is binary.
					ms.setWsdlDataType(ConstantVariables.SCHEMA_BINARYARRAY);
//					System.out.println(
//						"-----------add--array----------"
//							+ ms.getRequesterFieldName());

				} else {
					// if the message field type is primary type.
					ms.setWsdlDataType(wsdlDataString);
				}
				//System.out.println("type:"+ms.getWsdlType());

				
			}
		} catch (WSDLEngineException e) {

			e.printStackTrace();
		}
	}

	
	/**
	 * Judge wether this message has been added into the group message.
	 * If it has been added, then return true. else return false.
	 * @param messageList
	 * @param message
	 * @return boolean 
	 */
	private boolean groupContain(ArrayList<MessageStructure> messageList, MessageStructure message){
		boolean result = false;
		MessageStructure temp = null;
		for (int i = 0 ; i < messageList.size(); i ++){
			temp = (MessageStructure)messageList.get(i);
			if ((temp.getMetaFieldSequence() == message.getMetaFieldSequence())){
				// this message group has been added.  
				result = true;
				break;
			}
		}
		
		return result;
	}
	

	/**
	 * @deprecated
	 * If this message has been added, return false
	 * Judge wether this group message has been added into the groupMessageList.
	 * @param groupMessageList
	 * @param metaID
	 * @return boolean
	 */
	private boolean judge(ArrayList groupMessageList, long metaID) {
		boolean result = true;
		MessageStructure messageStructure = null;
		for (int i = 0; i < groupMessageList.size(); i++) {
			messageStructure = (MessageStructure) groupMessageList.get(i);
			if (messageStructure.getMetaID() == metaID) {
				// this group message has been added into the groupMessageList.
				result = false;
				break;
			}
		}

		return result;
	}

}
