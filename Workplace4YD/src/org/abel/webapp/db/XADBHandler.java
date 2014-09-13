//Source file: D:\\EIPPortal\\com\\kmtc\\eip\\db\\DBHandler.java

package org.abel.webapp.db;

import java.sql.*;
import java.io.*;

/**
 * @author Abel Wang, Wang Xi
 * Change to current vertion:
 * 1: rewrite the method query
     * 2: add new method cmfunction, service all three function:update,delete,insert;
 * Sample:
 * 1:
 * DBHandler dh = new DBHandler();
 * EIPResultSet eresult = dh.query("select * from CONTACTLIST");
 * dh.close();
 * 2:
 * dh.insert("insert into CONTACTLIST (name) values('wjx')");
 * dh.close();
 */
public class XADBHandler {
	boolean autocf = false;
	static String objencode = "GBK";
	static String oriencode = "GBK";
	/**
	 * Constructor for DBHandler.
	 */
	DBConnection dbc = null;


	/**
	 * @roseuid 3F4DB318012B
	 */
	public XADBHandler() {
		dbc = createVirtualDbConn(false);

	}



	/**
	 * @param sql
	 * @return boolean
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB31801A6
	 */
	public boolean update(String sql)
		throws SQLException, UnsupportedEncodingException {
		return cmfunction(sql);
	}

	/**
	 * @param sql
	 * @return boolean
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB31801E5
	 */
	public boolean delete(String sql)
		throws SQLException, UnsupportedEncodingException {

		return cmfunction(sql);
	}

	/**
	 * @param sql
	 * @return boolean
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB3180222
	 */
	public boolean insert(String sql)
		throws SQLException, UnsupportedEncodingException {
		return cmfunction(sql);
	}

	/**
	 * @param sql
	 * @return boolean
	 * @roseuid 3F4DB3180252
	 */
	public boolean cmfunction(String sql) throws UnsupportedEncodingException,SQLException{
		//System.out.println(sql);
		String sqltmp = encode(sql);
		//System.out.println(sqltmp);
		boolean rt = false;
		DBFunction dbf = new DBFunction();

			try{
				dbf.execute(dbc, sqltmp);
				rt = true;
			}catch (SQLException sqle) {
				throw sqle;
			}
				
		

		

		return rt;
	}

	/**
	 * @roseuid 3F4DB3180262
	 */
	public void StartXA() {
		
			dbc.commit();
			//close();
		
	}
	
	/**
	 * @roseuid 3F4DB3180262
	 */
	public void rollBackXA() throws SQLException  {
		
			dbc.rollback();
			//close();
		
	}
	/**
	 * @roseuid 3F4DB3180262
	 */
	public void close() {
		try {
			dbc.close();
		} catch (SQLException sqle) {
			dbc = null;
			System.out.println(
				"DB Connection close error: " + sqle.getMessage());
		}
	}

	/**
	 * Just for testing
	 * @param args
	 * @roseuid 3F570F4C01A5
	 */

	public String encode(String orgstr) throws UnsupportedEncodingException {
		String resulttemp = null;
		byte[] bta = orgstr.getBytes(oriencode);
		resulttemp = new String(bta, objencode);
		return resulttemp;
	}

	public String decode(String orgstr) throws UnsupportedEncodingException {
		String resulttemp = null;
		byte[] bta = orgstr.getBytes(objencode);
		resulttemp = new String(bta, oriencode);
		return resulttemp;
	}

	/**
	 * @param sql
	 * @return java.sql.ResultSet
	 * @throws java.sql.SQLException
	 * @roseuid 3F5D6D1D038B
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		XADBHandler dbh = new XADBHandler();
		try {
			
			String sql000="insert into MSGELEMENT (EID,ENAME)" +
					"values (CONCAT('E',(SELECT  CHAR(int(MAX(SUBSTR(EID,2)))+1)   FROM    MSGELEMENT)), 'abel')";
					
			
			String sql111="insert into MSGELEMENT (EID,ENAME)" +
			"values (CONCAT('E',(SELECT  CHAR(int(MAX(SUBSTR(EID,2)))+1)   FROM    MSGELEMENT )), 'abel')";

			
			String sql112="insert into serviceoperation"+
						"(Serviceid,operation_name) values"+
						"('User_Admin','test1234')";
			dbh.cmfunction(sql000);
			dbh.cmfunction(sql111);
			dbh.cmfunction(sql112);		
			
//			try{
//				dbh.rollBackXA();	
//			}catch(SQLException sssse){
//				sssse.printStackTrace();   //rollback Exception
//						
//			}
			dbh.StartXA();
			
			dbh = null;
		} catch (Exception se) {
			
			se.printStackTrace();
			
		} finally {
			if (dbh != null) dbh.close();
		}
	}




	//added by Paul.. [2003/9/27 10:00]
	protected DBConnection createVirtualDbConn(boolean aurocnnflg) {
		DBConnection dbc = null;
		//note: just to copy Abel's codes in Constructor..
		if (aurocnnflg) {
			autocf = aurocnnflg;
		} else {
			dbc = new DBConnection();
			//dbc = createDbConnection();
			//System.out.print(dbc);
		}
		return dbc;
	}



}
