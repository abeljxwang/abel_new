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
import org.abel.service.wsdlengine.entity.MultiRespMsg;


/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class EaiMultiRespMsgDAO {

	/**
	 * 
	 */
	public EaiMultiRespMsgDAO() {
		super();

	}
	//	public long getSubRespMessageID(long messageDI)
	//		throws WSDLEngineException {
	//		long result = 0;
	//		return result;
	//	}
	String dbName = "eai_multi_res_msg";

	public ArrayList getRespMessageSet(long resMessageID)
		throws WSDLEngineException {
		ArrayList result = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from " + dbName);
		sb.append(" where res_message_id = " + resMessageID);

		try {
			Connection conn = null;
			DBHelper dbHelper = new DBHelper();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			dbHelper = new DBHelper();
			conn = dbHelper.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			String[] fieldsObjectNameList =
				{
					"responseMessageID",
					"responseMessageName",
					"subResponseMessageID",
					"subResponseMessageName" };
			String[] fieldsTypeList = { "Long", "String", "Long", "String" };
			String[] fieldDBNameList =
				{
					"res_message_id",
					"response_field_name",
					"sub_res_message_id",
					"sub_response_field_name" };
			String className = "com.ibm.ccb.wsdlengine.entity.MultiRespMsg";
			result =
				dbHelper.resultSet2Object(
					rs,
					fieldsObjectNameList,
					fieldsTypeList,
					fieldDBNameList,
					className);
			dbHelper.closeAll(rs, pstmt, conn);
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}
		return result;
	}
}
