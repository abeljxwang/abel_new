/*
 * 创建日期 2005-4-25
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.abel.service.wsdlengine.common.WSDLEngineException;
import org.abel.service.wsdlengine.database.DBHelper;
import org.abel.service.wsdlengine.entity.MessageStructure;
import org.abel.service.wsdlengine.entity.MultiReqMsg;
import org.abel.service.wsdlengine.entity.MultiRespMsg;


/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class EaiMessageStructureDAO {

	/**
	 * 
	 */

	String dbName = "eai_message_structure";

	public EaiMessageStructureDAO() {
		super();
	}

	/**
	 * get Message List by message ID
	 * @param messageID
	 * @return
	 */
	public ArrayList getMessageList(long messageID)
		throws WSDLEngineException {
		ArrayList result = null;
		try {
			// construct sql string.
			StringBuffer sqlString = new StringBuffer();
			sqlString.append(
				"select distinct requester_field_name, provider_field_name, meta_id, meta_field_sequence ,message_id from ");
			sqlString.append(dbName);
			sqlString.append(" where message_id = " + messageID + "");
			DBHelper dbHelper = new DBHelper();
			Connection conn = dbHelper.getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();

			String[] fieldsObjectNameList =
				{
					"requesterFieldName",
					"providerFieldName",
					"metaID",
					"metaFieldSequence",
					"messageID" };
			String[] fieldsTypeList =
				{ "String", "String", "Long", "Double", "Long" };
			String[] fieldDBNameList =
				{
					"requester_field_name",
					"provider_field_name",
					"meta_id",
					"meta_field_sequence",
					"message_id" };
			String className = "com.ibm.ccb.wsdlengine.entity.MessageStructure";
			result =
				dbHelper.resultSet2Object(
					rs,
					fieldsObjectNameList,
					fieldsTypeList,
					fieldDBNameList,
					className);
			dbHelper.closeAll(rs, pstmt, conn);

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return result;

	}

	public ArrayList getGroupMessageList(ArrayList metaIDSet)
		throws WSDLEngineException {
		ArrayList groupMessageList = new ArrayList();
		if (metaIDSet.size() == 0) {
			return groupMessageList;
		}
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append(
				"select distinct requester_field_name, provider_field_name,meta_id ");
			sqlString.append(" from ");
			sqlString.append(dbName);
			sqlString.append(
				" where "
					+ transformSQLString(metaIDSet)
					+ " order by meta_id");
			DBHelper dbHelper = new DBHelper();
			Connection conn = dbHelper.getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();
			String[] fieldsObjectNameList =
				{ "requesterFieldName", "providerFieldName", "metaID" };
			String[] fieldsTypeList = { "String", "String", "Long" };
			String[] fieldDBNameList =
				{ "requester_field_name", "provider_field_name", "meta_id" };
			String className = "com.ibm.ccb.wsdlengine.entity.MessageStructure";

			groupMessageList =
				dbHelper.resultSet2Object(
					rs,
					fieldsObjectNameList,
					fieldsTypeList,
					fieldDBNameList,
					className);

			dbHelper.closeAll(rs, pstmt, conn);
			////for (int p = 0; p < groupMessageList.size();p ++){
			////	MessageStructure m = null;
			////	m = (MessageStructure)groupMessageList.get(p);
			////	System.out.println("metaID"+m.getMetaID()+"reqname"+m.getRequesterFieldName());
			//}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return groupMessageList;
	}

	public String transformSQLString(ArrayList metaIDSet) {
		StringBuffer idList = new StringBuffer();
		if (metaIDSet.size() > 0) {
			MessageStructure ms = null;
			ms = (MessageStructure) metaIDSet.get(0);
			idList.append(
				" (meta_id ='"
					+ (ms.getMetaID())
					+ "' and message_id ="
					+ ms.getMessageID()
					+ ") ");
			for (int i = 1; i < metaIDSet.size(); i++) {
				ms = (MessageStructure) metaIDSet.get(i);
				idList.append(
					" or ("
						+ " meta_id ='"
						+ ms.getMetaID()
						+ "' and message_id = "
						+ ms.getMessageID()
						+ ") ");
			}
		}

		return idList.toString();
	}

	/**
	 * get group message name by metaID.
	 * query the message structure table's requester_field_name &  field_group_sequence ==0
	 * @param metaID
	 * @return String
	 * @throws WSDLEngineException
	 */
	public String getGroupMessageName(long metaID) throws WSDLEngineException {
		String groupMessageName = null;
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append(" select distinct requester_field_name from ");
			sqlString.append(dbName);
			sqlString.append(
				" where field_group_sequence =0 and meta_id=" + metaID);
			DBHelper dbHelper = new DBHelper();
			Connection conn = dbHelper.getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				groupMessageName =
					(String) rs.getString("requester_field_name");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}
		return groupMessageName;
	}

	public ArrayList getGroupMessageLists(ArrayList messageList)
		throws WSDLEngineException {
		ArrayList groupMessageList = new ArrayList();
		ArrayList groupMessageItem = null;
		double metaFieldSequence;
		MessageStructure ms = null;
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("select distinct * from ");
		sqlString.append(dbName);
		//sqlString.append(" where meta_field_sequence = ? order by field_group_sequence ");
		sqlString.append(
			" where meta_field_sequence = ? and message_id = ? order by field_group_sequence ASC");
		Connection conn = null;
		DBHelper dbHelper = new DBHelper();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long msID;
		try {
			conn = dbHelper.getConnection();
			pstmt = conn.prepareStatement(sqlString.toString());
			for (int i = 0; i < messageList.size(); i++) {
				ms = (MessageStructure) messageList.get(i);
				metaFieldSequence = ms.getMetaFieldSequence();
				msID = ms.getMessageID();
				pstmt.setDouble(1, metaFieldSequence);
				pstmt.setLong(2, msID);
				rs = pstmt.executeQuery();
				String[] fieldsObjectNameList =
					{ "requesterFieldName", "providerFieldName", "metaID" };
				String[] fieldsTypeList = { "String", "String", "Long" };
				String[] fieldDBNameList =
					{
						"requester_field_name",
						"provider_field_name",
						"meta_id" };
				String className =
					"com.ibm.ccb.wsdlengine.entity.MessageStructure";

				groupMessageItem =
					dbHelper.resultSet2Object(
						rs,
						fieldsObjectNameList,
						fieldsTypeList,
						fieldDBNameList,
						className);
				groupMessageList.add(groupMessageItem);
			}
			dbHelper.closeAll(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}

		
		return groupMessageList;
	}

	public ArrayList getMultiStepReqMessage(ArrayList reqMultiMessageSet)
		throws WSDLEngineException {
		ArrayList reqMessageList = new ArrayList();
		Connection conn = null;
		DBHelper dbHelper = new DBHelper();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select * from " + dbName);
			sb.append(" where message_id = ? and requester_field_name=? ");
			conn = dbHelper.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			MultiReqMsg multiReqMsg = null;
			MessageStructure ms = null;
			for (int i = 0; i < reqMultiMessageSet.size(); i++) {
				multiReqMsg = (MultiReqMsg) reqMultiMessageSet.get(i);
				pstmt.setLong(1, multiReqMsg.getSubReqMessageID());
				pstmt.setString(2, multiReqMsg.getSubReqFieldName());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					ms = new MessageStructure();
					ms.setRequesterFieldName(multiReqMsg.getReqFieldName());
					ms.setMessageID(rs.getLong("message_id"));
					ms.setMetaID(rs.getLong("meta_id"));
					ms.setMetaFieldSequence(rs.getDouble("meta_field_sequence"));
					reqMessageList.add(ms);

				}

			}

			dbHelper.closeAll(rs, pstmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}
		return reqMessageList;

	}

	public ArrayList getMultiStepResMessage(ArrayList resMultiMessageSet)
		throws WSDLEngineException {
		ArrayList resMessageList = new ArrayList();
		Connection conn = null;
		DBHelper dbHelper = new DBHelper();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select * from " + dbName);
			sb.append(" where message_id = ? and provider_field_name=? ");
			conn = dbHelper.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			MultiRespMsg multiResMsg = null;
			MessageStructure ms = null;
			for (int i = 0; i < resMultiMessageSet.size(); i++) {
				multiResMsg = (MultiRespMsg) resMultiMessageSet.get(i);
				pstmt.setLong(1, multiResMsg.getSubResponseMessageID());
				pstmt.setString(2, multiResMsg.getSubResponseMessageName());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					ms = new MessageStructure();
					ms.setRequesterFieldName(
						multiResMsg.getResponseMessageName());
					ms.setMessageID(rs.getLong("message_id"));
					ms.setMetaID(rs.getLong("meta_id"));
					ms.setMetaFieldSequence(rs.getDouble("meta_field_sequence"));
					resMessageList.add(ms);
				}
				// debug begin
//				
//				System.out.println("attention");
//				System.out.println("name:"+multiResMsg.getResponseMessageName()+ms.getMetaID());
				
			//	 debug end
				

			}

			dbHelper.closeAll(rs, pstmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}
		return resMessageList;
	}

}
