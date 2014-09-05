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


/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class EaiMultiReqMsgDAO {

	/**
	 * 
	 */
	public EaiMultiReqMsgDAO() {
		super();
	}

	String dbName = "EAI_MULTI_REQ_MSG";

	//	public long getSubReqMessageID (long messageID) throws WSDLEngineException{
	//		long result = 0;
	//		return result;
	//	}

	/** get request message field info.
	 * @param reqMessageID
	 * @return ArrayList
	 */
	public ArrayList getReqMessageSet(long reqMessageID)
		throws WSDLEngineException {
		ArrayList result = new ArrayList();

		StringBuffer sb = new StringBuffer();
		sb.append("select * from " + dbName);
		sb.append(" where req_message_id = " + reqMessageID);
		try {
			DBHelper dbHelper = new DBHelper();
			Connection conn = dbHelper.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();

			String[] fieldsObjectNameList =
				{"reqMessageID" ,
					"reqFieldName",
					"subReqMessageID",
					"subReqFieldName"
					 };
			String[] fieldsTypeList =
				{ "Long", "String", "Long", "String" };
			String[] fieldDBNameList =
				{
					"req_message_id" ,
					"req_field_name",
					"sub_req_message_id",
					"sub_req_field_name"};
			String className = "com.ibm.ccb.wsdlengine.entity.MultiReqMsg";
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
