/*
 * 创建日期 2005-4-22
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.abel.service.wsdlengine.database.DBHelper;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class EaiTransStructureDAO {

	/**
	 * 
	 */
	private String dbName = "eai_trans_structure";
	
	public EaiTransStructureDAO() {
		super();

	}

	public ArrayList getSubTransactionIDList(String transactionID) {
		ArrayList result = new ArrayList();

		try {
			// construct sql string.
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select distinct sub_trans_id from ");
			sqlString.append(dbName);
			sqlString.append(" where trans_id = '"+transactionID+"'");
			Connection conn = new DBHelper().getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();
			String [] fieldsList = null;
			String [] fieldsTypeList = null;
			String [] fieldDBNameList = {"sub_trans_id"};
			String calssName = "String";
			DBHelper dbHelper = new DBHelper();
			result = dbHelper.resultSet2Object(rs,fieldsList,fieldsTypeList,fieldDBNameList,calssName);
			dbHelper.closeAll(rs,pstmt,conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
