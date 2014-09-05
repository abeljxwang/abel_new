/*
 * 创建日期 2005-4-28
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
import org.abel.service.wsdlengine.entity.MetaDataStructure;


/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class MetaDataDAO {

	/**
	 * 
	 */

	private String dbName = "meta_data";

	public MetaDataDAO() {
		super();
	}

	public MetaDataStructure getMeta(long metaID) throws WSDLEngineException {
		MetaDataStructure result = null;

		try {
			// construct sql string.
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select distinct meta_name,meta_type,meta_length from ");
			sqlString.append(dbName);
			sqlString.append(" where meta_id ='" + metaID + "'");
			DBHelper dbh = new DBHelper();
			Connection conn = dbh.getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new MetaDataStructure();
				result.setMetaName(rs.getString("meta_name"));
				result.setMetaType(rs.getString("meta_type"));
				result.setMetaLength(rs.getLong("meta_length"));
				result.setMetaID(metaID);
			}

			dbh.closeAll(rs, pstmt, conn);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return result;
	}

	/**
	 * Get Group metaID set
	 * @return ArrayList
	 * @throws WSDLEngineException
	 */
	public ArrayList getGroupMetaIdSet() throws WSDLEngineException {
		ArrayList metaIDSet = new ArrayList();

		try {
			// construct sql string.
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select distinct meta_id from ");
			sqlString.append(dbName);
			sqlString.append(" where meta_type = 'G' order by meta_id");
			DBHelper dbh = new DBHelper();
			Connection conn = dbh.getConnection();
			PreparedStatement pstmt =
				conn.prepareStatement(sqlString.toString());
			ResultSet rs = pstmt.executeQuery();
			long temp ;
			while (rs.next()){
				temp = rs.getLong("meta_id");
				metaIDSet.add(new Long(temp));
			}
			dbh.closeAll(rs,pstmt,conn);

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}
		return metaIDSet;
	}

}
