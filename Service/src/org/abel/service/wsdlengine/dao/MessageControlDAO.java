/*
 * 创建日期 2005-4-22
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.abel.service.wsdlengine.common.WSDLEngineException;
import org.abel.service.wsdlengine.database.DBHelper;
import org.abel.service.wsdlengine.entity.FieldProperty;


/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class MessageControlDAO {

	/**
	 * 
	 */

	private String dbName = "eai_message_control";

	public MessageControlDAO() {
		super();

	}

	public FieldProperty getSingleExtendedMessage(
		String conType,
		String AppSysID,
		Connection conn)
		throws WSDLEngineException {
		FieldProperty fieldProperty = null;
		try {

		} catch (Exception e) {
			throw new WSDLEngineException();
		}
		return fieldProperty;
	}

	public Hashtable getExtendedMessageList(ArrayList appSysIDList)
		throws WSDLEngineException {
		Hashtable extendedMessageTable = null;
		try {

		} catch (Exception e) {
			throw new WSDLEngineException();
		}
		return extendedMessageTable;
	}

	public ArrayList getExtendedMessage(String appSystemID,
	//Connection conn,
	String type) throws WSDLEngineException {
		ArrayList result = null;
		try {
			// construct sql string.
			StringBuffer sqlString = new StringBuffer();
			sqlString.append(
				"select distinct requester_field_name, provider_field_name, meta_id from ");
			sqlString.append(dbName);
			sqlString.append(
				" where apps_sys_id ='"
					+ appSystemID
					+ "' and message_cont_type ='"
					+ type
					+ "'");

			Connection conn = new DBHelper().getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();

			String[] fieldsObjectNameList =
				{ "requesterFieldName", "providerFieldName", "metaID" };
			String[] fieldsTypeList = { "String", "String", "Long" };
			String[] fieldDBNameList =
				{ "requester_field_name", "provider_field_name", "meta_id" };
			String className = "com.ibm.ccb.wsdlengine.entity.MessageStructure";

			DBHelper dbHelper = new DBHelper();
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

	public ArrayList getResponseExtendedMessage(
		String appSystemID,
		Connection conn,
		String type) {
		ArrayList result = null;

		return result;
	}

}
