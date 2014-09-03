/*
 * 创建日期 2005-4-22
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import org.abel.service.wsdlengine.common.WSDLEngineException;
import org.abel.service.wsdlengine.database.DBHelper;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class AppSystemDAO {

	/**
	 * 
	 */

	String dbName = "apps_sys_list";

	public AppSystemDAO() {
		super();
	}

	/**
	 * Get all the app. system id list.
	 * @return
	 */
	public ArrayList getAppSystemIDList() throws WSDLEngineException {
		ArrayList result = null;

		try {
			// construct sql string.
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select distinct apps_sys_id from ");
			sqlString.append(dbName);
			

			Connection conn = new DBHelper().getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();	
				
			String [] fieldsObjectNameList = null;
			String [] fieldsTypeList = null;
			String [] fieldDBNameList = {"apps_sys_id"};
			String className = "String";
			
			DBHelper dbHelper = new DBHelper();
			result = dbHelper.resultSet2Object(rs,fieldsObjectNameList,fieldsTypeList,fieldDBNameList,className);
			dbHelper.closeAll(rs,pstmt,conn);
				
		} catch (SQLException e) {

			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return result;
	}
}
