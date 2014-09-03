/*
 * 创建日期 2005-4-28
 * CCB EAIH Project team
 */
package org.abel.service.soa.message;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.abel.service.base.db.DBHandler;
import org.abel.service.wsdlengine.common.WSDLEngineException;
import org.abel.service.wsdlengine.entity.MetaDataStructure;


/**
 * @author daijun@cn.ibm.com
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

	public MetaDataStructure getMeta(long metaID) throws Exception {
		MetaDataStructure result = null;

		try {
			// construct sql string.
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select distinct meta_name,meta_type,meta_length from ");
			sqlString.append(dbName);
			sqlString.append(" where meta_id ='" + metaID + "'");
			DBHandler dbh = new DBHandler();
			//Connection conn = dbh.getConnection();
			//PreparedStatement pstmt =
			//	conn.prepareStatement(sqlString.toString());
			ResultSet rs = dbh.stdquery(sqlString.toString());

			if (rs.next()) {
				result = new MetaDataStructure();
				result.setMetaName(rs.getString("meta_name"));
				result.setMetaType(rs.getString("meta_type"));
				result.setMetaLength(rs.getLong("meta_length"));
				result.setMetaID(metaID);
			}

			dbh.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return result;
	}

	/**
	 * Get Group metaID set
	 * @return ArrayList
	 * @throws WSDLEngineException
	 */
	public ArrayList<Long> getGroupMetaIdSet() throws Exception {
		ArrayList<Long> metaIDSet = new ArrayList<Long>();

		try {
			// construct sql string.
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("select distinct meta_id from ");
			sqlString.append(dbName);
			sqlString.append(" where meta_type = 'G' order by meta_id");
			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sqlString.toString());
			long temp ;
			while (rs.next()){
				temp = rs.getLong("meta_id");
				metaIDSet.add(new Long(temp));
			}
			dbh.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return metaIDSet;
	}

}
