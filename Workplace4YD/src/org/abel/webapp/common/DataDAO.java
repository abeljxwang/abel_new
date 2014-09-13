/*
 * 创建日期 2005-4-28
 * CCB EAIH Project team
 */
package org.abel.webapp.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.abel.webapp.db.DBHandler;

//import com.ibm.ais.base.db.DBHandler;
//import com.ibm.ais.wsdlengine.common.WSDLEngineException;
//import com.ibm.ais.wsdlengine.database.DBHelper;


/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class DataDAO {


	public DataDAO() {
		super();
	}

	public static Properties  getMeta() throws Exception {
		Properties  result = new Properties();


		try {
			// construct sql string.
			String sql="select EID, ENAME from MSGELEMENT";
			

			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sql);
			String tmpstr=null;
			
			
			while(rs.next()) {
				result.setProperty(rs.getString("EID"), rs.getString("ENAME")+"");
			}

			dbh.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return result;
	}
	
	
	public static Properties  getMeta(String servname) throws Exception {
		Properties  result = new Properties();


		try {
			// construct sql string.
			String sql="select EID, ENAME from MSGELEMENT" +
					" where" +
					" ENAME in (select operation_name from serviceoperation where serviceid='"+servname+"')" +
					" or" +
					" ENAME in (select CONCAT(operation_name,'_Response') from serviceoperation where serviceid='"+servname+"')";
			

			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sql);
			String tmpstr=null;
			
			
			while(rs.next()) {
				result.setProperty(rs.getString("EID"), rs.getString("ENAME")+"");
			}

			dbh.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return result;
	}

	public static Properties  getServices() throws Exception {
		Properties  result = new Properties();


		try {
			// construct sql string.
			String sql="select ServiceID,ServiceName from SERVICELIST";
			

			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sql);
			String tmpstr=null;
			
			
			while(rs.next()) {
				result.setProperty(rs.getString("ServiceID"), rs.getString("ServiceName")+"");
			}

			dbh.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return result;
	}


	
	public static void main(String[] a) throws Exception{
		
		DataDAO sdao = new DataDAO();

		 Properties h = sdao.getMeta();
		
		
		System.out.println(h);

		
	}

}
