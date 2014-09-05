/*
 * 创建日期 2005-4-19
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.database;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.abel.service.wsdlengine.common.ConstantVariables;
import org.abel.service.wsdlengine.common.WSDLEngineException;
import org.abel.service.wsdlengine.entity.TransactionStructure;


/**
 * @author wangjx@cn.ibm.com
 * perform database related operation.
 * 
 */
public class DBHelper {

	/**
	 * 
	 */
	public DBHelper() {
		super();
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds =
				(DataSource) ctx.lookup(ConstantVariables.DATABASE_DATASOURCE);
			con = ds.getConnection();

		} catch (javax.naming.NamingException e) {
			System.out.println("Naming Exception:" + e.getMessage());
		} catch (Exception se) {
			System.out.println("SQL Exception:" + se.getMessage());
		}
		return con;
	}

	/**
	 * Change ResultSet to Object 
	 * @deprecated
	 * @param rs
	 * @param begin
	 * @param count
	 * @param fieldsList
	 * @param className
	 * @return ArrayList
	 * @throws WSDLEngineException
	 */
	public ArrayList extractResultSet(
		ResultSet rs,
		int begin,
		int count,
		ArrayList fieldsList,
		String className)
		throws WSDLEngineException {

		ArrayList result = new ArrayList();
		Class classDefinition = null;
		Object object = null;
		Field objectField = null;
		String fieldName = null;
		Object dbValue = null;
		String fieldType = null;
		try {
			if (count > 0) {
				// if has any content.
				// get class instance
				classDefinition = Class.forName(className);

				if (1 == begin) {
					rs.beforeFirst();
					System.out.println("11111112");
				} else {
					System.out.println("222223");
					rs.absolute(begin - 1);
					System.out.println("22222");
				}
				int i = 0;
				while (rs.next()) {
					System.out.println("iiiiiiiiii");
					object = classDefinition.newInstance();
					// set object value.
					for (int j = 0; j < fieldsList.size(); j++) {
						fieldName = (String) fieldsList.get(i);
						objectField = classDefinition.getField(fieldName);
						//	dbValue = getValueByType(rs, fieldName, fieldType);
						
						//classDefinition.						
						objectField.set(object, dbValue);
						result.add(object);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return result;

	}
	/**
		 * Change ResultSet to Object 
		 * 
		 * @param rs
		 * @param begin
		 * @param count
		 * @param fieldsList
		 * @param className
		 * @return ArrayList
		 * @throws WSDLEngineException
		 */
	public ArrayList extractResultSet(
		ResultSet rs,
		ArrayList fieldsList,
		ArrayList fieldsTypeList,
		ArrayList fieldDBNameList,
		String className)
		throws WSDLEngineException {

		ArrayList result = new ArrayList();
		Class classDefinition = null;
		Object object = null;
		Field objectField = null;
		String fieldObjectName = null;
		String fieldDBName = null;
		Object dbValue = null;
		String fieldType = null;
		TransactionStructure aaaa = null;
		try {
			// if has any content.
			// get class instance
			classDefinition = Class.forName(className);
			while (rs.next()) {
				object = classDefinition.newInstance();
				// set object value.
				for (int j = 0; j < fieldsList.size(); j++) {
					fieldObjectName = (String) fieldsList.get(j);
					fieldDBName = (String) fieldDBNameList.get(j);
					fieldType = (String) fieldsTypeList.get(j);

					objectField = classDefinition.getField(fieldObjectName);
					// fill object from db. 
					// The data type is String, Long
					if (fieldType.equalsIgnoreCase("String")) {
						objectField.set(object, rs.getString(fieldDBName));

					} else if (fieldType.equalsIgnoreCase("Long")) {
						objectField.set(
							object,
							new Long(rs.getLong(fieldDBName)));

					}

				}
				result.add(object);
				aaaa = (TransactionStructure) object;

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return result;

	}

	public ArrayList resultSet2Object(
		ResultSet rs,
		String[] fieldsList,
		String[] fieldsTypeList,
		String[] fieldDBNameList,
		String className)
		throws WSDLEngineException {

		ArrayList result = new ArrayList();
		Class classDefinition = null;
		Object object = null;
		Field objectField = null;
		String fieldObjectName = null;
		String fieldDBName = null;
		Object dbValue = null;
		String fieldType = null;
		TransactionStructure aaaa = null;
		try {
			// if has any content.
			// get class instance
			while (rs.next()) {
				// set object value.

				if (null == fieldsTypeList) {
					// for non-change class
					for (int j = 0; j < fieldDBNameList.length; j++) {

						fieldDBName = (String) fieldDBNameList[j];

						if (className.equalsIgnoreCase("String")) {
							object = new String(rs.getString(fieldDBName));
						} else if (className.equalsIgnoreCase("Long")) {
							object = new Long(rs.getString(fieldDBName));
						}
					}

				} else {
					// for complex class
					classDefinition = Class.forName(className);
					object = classDefinition.newInstance();
					for (int j = 0; j < fieldsList.length; j++) {
						fieldObjectName = (String) fieldsList[j];
						fieldDBName = (String) fieldDBNameList[j];
						fieldType = (String) fieldsTypeList[j];
						objectField = classDefinition.getField(fieldObjectName);
						// fill object from db. 
						// The data type is String, Long, Double
						if (fieldType.equalsIgnoreCase("String")) {
							objectField.set(object, rs.getString(fieldDBName));

						} else if (fieldType.equalsIgnoreCase("Long")) {
							objectField.set(
								object,
								new Long(rs.getLong(fieldDBName)));

						} else if (fieldType.equalsIgnoreCase("Double")) {
							objectField.set(
								object,
								new Double(rs.getLong(fieldDBName)));
						}

					}

				}

				result.add(object);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new WSDLEngineException();
		}

		return result;

	}

	public void closeAll(
		ResultSet rs,
		PreparedStatement pstmt,
		Connection conn)
		throws WSDLEngineException {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null && conn.isClosed() == false) {
				conn.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new WSDLEngineException();
		}
	}

	public void closeStmtAndConn(PreparedStatement pstmt, Connection conn)
		throws WSDLEngineException {

		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null && conn.isClosed() == false) {
				conn.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new WSDLEngineException();
		}
	}
}
