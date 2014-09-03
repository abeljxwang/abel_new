/*
 * 创建日期 2005-4-22
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.abel.service.wsdlengine.common.ConstantVariables;
import org.abel.service.wsdlengine.database.DBHelper;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class EaiTransListDAO {

	/**
	 * 
	 */
	String dbName = "eai_trans_list";
	
	public EaiTransListDAO() {
		super();

	}
	
	

	/**
	 * 获得所有的transactionID的列表
	 * @return ArrayList
	 */
	public ArrayList getTransactionBasicList() {
		ArrayList result = null;
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select distinct trans_id,request_multi_msg_id,response_multi_msg_id from ");
			sqlString.append(dbName);
			Connection conn = new DBHelper().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sqlString.toString());
			
			ResultSet rs = pstmt.executeQuery();	
			
			DBHelper dbHelper = new DBHelper();
			
			// construct fieldName list need to be set.
			ArrayList fieldObjectNameList = new ArrayList();
			ArrayList fieldTypeList = new ArrayList();
			ArrayList fieldDBNameList = new ArrayList();
			
			String tempObjectField = null;
			String tempDBField = null;
			String tempFieldType = null;
			
			tempObjectField = "transactionID";
			tempDBField = "trans_id";
			tempFieldType = "String";
			fieldObjectNameList.add(tempObjectField);
			fieldDBNameList.add(tempDBField);
			fieldTypeList.add(tempFieldType);
			
			tempObjectField="requestMultiMsgID";
			tempDBField = "request_multi_msg_id";
			tempFieldType = "Long";
			fieldObjectNameList.add(tempObjectField);
			fieldDBNameList.add(tempDBField);
			fieldTypeList.add(tempFieldType);
			
			tempObjectField = "responseMultiMsgID";
			tempDBField = "response_multi_msg_id";
			tempFieldType = "Long";
			fieldObjectNameList.add(tempObjectField);
			fieldDBNameList.add(tempDBField);
			fieldTypeList.add(tempFieldType);
								
			result = dbHelper.extractResultSet(rs,fieldObjectNameList,fieldTypeList,fieldDBNameList,ConstantVariables.CLASS_TRANSACTION_STRUCTURE);
			
			dbHelper.closeAll(rs,pstmt,conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
