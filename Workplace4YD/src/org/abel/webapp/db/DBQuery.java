//Source file: D:\\EIPPortal\\com\\kmtc\\eip\\db\\DBQuery.java

package org.abel.webapp.db;

import java.sql.*;
import java.util.*;
//import com.kmtc.common.*;

/**
 * @author wx
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DBQuery {

	/**
	 * @param dbc
	 * @param sql
	 * @return com.kmtc.eip.db.EIPResultSet
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB317032C
	 */
	protected EIPResultSet execute(DBConnection dbc, String sql, int number)
		throws SQLException {
		Statement stmt = dbc.createStatement();
		//		System.out.println(sql);
		EIPResultSet ere = new EIPResultSet();
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		ere.putMD(rsmd);

		int cc = rsmd.getColumnCount();
		int rn = 0;
		if(rs!=null){
		while (rs.next()&&rn<number) {
			Vector vrecord = new Vector();
			for (int i = 1; i <= cc; i++) {
				vrecord.add(i - 1, rs.getObject(i));
			}
			ere.put(rn, vrecord);
			rn++;
		}
		}
		rs.close();
		return ere;
	}


	protected EIPResultSet execute(DBConnection dbc, String sql)
		throws SQLException {
		Statement stmt = dbc.createStatement();
		//		System.out.println(sql);
		EIPResultSet ere = new EIPResultSet();
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		ere.putMD(rsmd);

		int cc = rsmd.getColumnCount();
		int rn = 0;
		if(rs!=null){
		while (rs.next()) {
			Vector vrecord = new Vector();
			for (int i = 1; i <= cc; i++) {
				vrecord.add(i - 1, rs.getObject(i));
			}
			ere.put(rn, vrecord);
			rn++;
		}
		}
		rs.close();
		return ere;
	}

	/**
	 * DBConnection dbc = null;
	 * @param args
	 * @roseuid 3F570F4B01A5
	 */
	public static void main(String[] args) {

	}



}
