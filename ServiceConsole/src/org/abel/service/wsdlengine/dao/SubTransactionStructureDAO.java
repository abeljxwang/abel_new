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
public class SubTransactionStructureDAO {

	/**
	 * 
	 */

	private String dbName = "sub_trans_structure";

	public SubTransactionStructureDAO() {
		super();
	}

	public long getResponseMessageID(String subTransactionID) {
		long result = 0;
		return result;
	}

	public long getRequestMessageID(String subTransactionID) {
		long result = 0;
		return result;
	}

	/**
	 * Get requestMessageID and responseMessageID from sub_transaction_message
	 * @param subTransactionID
	 * @return
	 */
	public long[] getResquestMessageIDAndResponseMessageID(String subTransactionID) {
		long[] result = new long[2];

		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append(
				"select distinct request_message_id,response_message_id from ");
			sqlString.append(dbName);
			sqlString.append(
				" where sub_trans_id = '" + subTransactionID + "'");
			DBHelper dbh = new DBHelper();
			Connection conn = dbh.getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result[0] = rs.getLong(1);
				result[1] = rs.getLong(2);
			}
			dbh.closeAll(rs, pstmt, conn);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String getAppSystemID(String subTransactionID)
		throws WSDLEngineException {
		String appSystemID = null;

		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append(" select distinct provider_apps_sys_id from ");
			sqlString.append(dbName);
			sqlString.append(
				" where sub_trans_id ='" + subTransactionID + "' ");
			DBHelper dbh = new DBHelper();
			Connection conn = dbh.getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				appSystemID = (String) rs.getString(1);
			}
			dbh.closeAll(rs, pstmt, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return appSystemID;
	}

	public ArrayList getAppSystemIDSet(ArrayList subTransactionIDSet)
		throws WSDLEngineException {
		ArrayList appSystemIDSet = new ArrayList();
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select distinct provider_apps_sys_id from ");
			sqlString.append(dbName);
			sqlString.append(expandSubTransactionID(subTransactionIDSet));
			
			DBHelper dbh = new DBHelper();
			Connection conn = dbh.getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()){
				appSystemIDSet.add(rs.getString(1));
			}
			dbh.closeAll(rs,pstmt,conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}
		return appSystemIDSet;
	}
	
	private String expandSubTransactionID(ArrayList subTransactionIDSet){
		StringBuffer sb = new StringBuffer();
		String tempSubTransactionID = null;
		
		if (0 == subTransactionIDSet.size()){
			return " false ";
		}else {
			tempSubTransactionID = (String)subTransactionIDSet.get(0);
			sb.append(" where sub_trans_id ='"+tempSubTransactionID+"' ");
		}
		
		for (int i =  1; i < subTransactionIDSet.size();i++){
			tempSubTransactionID = (String)subTransactionIDSet.get(i);
			sb.append(" or sub_trans_id ='"+tempSubTransactionID+"' ");
		}
		
		return sb.toString();
		
	}
}
