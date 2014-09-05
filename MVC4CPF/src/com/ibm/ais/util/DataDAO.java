/*
 * 创建日期 2005-4-28
 * CCB EAIH Project team
 */
package com.ibm.ais.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.abel.webapp.db.DBHandler;
import org.abel.webapp.db.EIPResultSet;

// import com.ibm.ais.base.db.DBHandler;
// import com.ibm.ais.wsdlengine.common.WSDLEngineException;
// import com.ibm.ais.wsdlengine.database.DBHelper;

/**
 * @author wangjx@cn.ibm.com
 * 
 * 
 */
public class DataDAO {

	public DataDAO() {
		super();
	}

	public static Properties getMeta() throws Exception {
		Properties result = new Properties();

		try {
			// construct sql string.
			String sql = "select EID, ENAME from MSGELEMENT";
			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sql);
			// String tmpstr=null;

			while (rs.next()) {
				result.setProperty(rs.getString("EID"), rs.getString("ENAME")
						+ "");
			}

			dbh.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return result;
	}

	public static Properties getMeta(String servname) throws Exception {
		Properties result = new Properties();

		try {
			// construct sql string.
			String sql = "select EID, ENAME from MSGELEMENT"
					+ " where"
					+ " ENAME in (select operation_name from serviceoperation where serviceid='"
					+ servname
					+ "')"
					+ " or"
					+ " ENAME in (select CONCAT(operation_name,'_Response') from serviceoperation where serviceid='"
					+ servname + "')";

			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sql);
			// String tmpstr=null;

			while (rs.next()) {
				result.setProperty(rs.getString("EID"), rs.getString("ENAME")
						+ "");
			}

			dbh.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return result;
	}

	public static Properties getMenus() throws Exception {
		Properties result = new Properties();

		try {
			// construct sql string.
			String sql = "select funcid,up_funcid, funcname from func_tree order by up_funcid DESC";

			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sql);
			// String tmpstr=null;

			String tmp = "0";
			while (rs.next()) {
				if (rs.getString("up_funcid") != null)
					tmp = rs.getString("up_funcid");
				result.setProperty(rs.getString("funcid"), rs
						.getString("funcname")
						+ ":" + rs.getString("funcid") + ":" + tmp);
				tmp = "0";
			}

			dbh.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return result;
	}

	public static Properties getAllMenus() throws Exception {
		Properties result = new Properties();

		try {
			// construct sql string.
			String sql = "select funcid,up_funcid, funcname,func_link from func_tree order by up_funcid DESC";
			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sql);

			while (rs.next()) {
				MenueElement me = new MenueElement();
				me.setID(rs.getString("funcid"));
				if (rs.getString("up_funcid") != null)
					me.setUpID(rs.getString("up_funcid"));
				me.setName(rs.getString("funcname"));
				me.setURL(rs.getString("func_link"));
				result.put(me.getID(), me);
			}
			dbh.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static MenueElement getMenu(String id) throws Exception {
		MenueElement result = new MenueElement();
		try {
			// construct sql string.
			String sql = "select funcid,up_funcid, funcname,func_link from func_tree where funcid='"
					+ id + "'";
			DBHandler dbh = new DBHandler();
			ResultSet rs = dbh.stdquery(sql);
			while (rs.next()) {

				result.setID(rs.getString("funcid"));
				result.setName(rs.getString("funcname"));
				result.setURL(rs.getString("func_link"));

			}
			dbh.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return result;
	}

	public static int getERecAmount(String codevalue, String sysid)
			throws Exception {

		int size = 0;

		String sql = "select count(1) from ErrorBasicMsg where Error_Code like'%"
				+ codevalue + "%' and Sys_ID='" + sysid + "'";
		// System.out.println(sql);
		DBHandler dbh = new DBHandler();
		try {
			EIPResultSet eresult = dbh.query(sql);
			dbh.close();
			dbh = null;
			if (eresult.next()) {
				size = eresult.getInt(1);
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			if (dbh != null)
				dbh.close();
		}

		return size;

	}

	public static Properties getSystemList() throws Exception {

		Properties result = new Properties();

		String sql = "select Cat_ID cid,Cat_Name Name from Category where up_cat_id IS NULL order by cid";
		// System.out.println(sql);
		DBHandler dbh = new DBHandler();
		try {
			EIPResultSet eresult = dbh.query(sql);
			dbh.close();
			dbh = null;
			while (eresult.next()) {
				result.setProperty(eresult.getString(1), eresult.getString(2)+ "");
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			if (dbh != null)
				dbh.close();
		}
		return result;
	}

	public static void main(String[] a) throws Exception {

		// DataDAO sdao = new DataDAO();

		// Properties h = sdao.getMeta();

		// System.out.println(h);

	}

}
