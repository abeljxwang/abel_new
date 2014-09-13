//Source file: D:\\EIPPortal\\com\\kmtc\\eip\\db\\DBConnection.java

package org.abel.webapp.db;

import javax.naming.*;

import javax.sql.DataSource;
import java.sql.*;
import org.abel.webapp.common.*;


//import com.ibm.db2.jcc.DB2Driver;

/**
 * @author Abel Wang, Wang Xi
 * Just for obtain the connection to special Database
 * Befor using such object, please make sure you have know the
 * DB connectuion url, db driver,DB type, user id and password
 */

public class DBConnection {
	static boolean initflag = false;
	InitialContext ctx;
	Connection con = null;

	//static String dbdri = "com.ibm.db2.jcc.DB2Driver";
	
	static String dbdri = "com.mysql.jdbc.Driver";
	//"oracle.jdbc.driver.OracleDriver";
	//#DBURL = jdbc:oracle:thin:@10.2.1.199:1521:omsdb
		//"com.ibm.db2.jcc.DB2Driver";
	//	//"COM.ibm.db2.jdbc.net.DB2Driver";  //;
	static String url = "jdbc:mysql://localhost/abeldb";
	//static String url = "jdbc:db2://localhost:50000/ABELDB";
		//"jdbc:oracle:thin:@192.168.16.12:1521:ORCL";
	//DBURL = jdbc:db2://localhost:50000/ABELDB
	//"jdbc:db2://localhost:50000/abelsys"; //

	//static String dbdri = "COM.ibm.db2.jdbc.app.DB2Driver";
	//static String url = "jdbc:db2:abelsys";	
	//
	static String userid = "root";
	static String passwd = "zse45tgb";

	boolean dsflag = false;
	public static String dsname = "jdbc/SOA";

	static {
		try {
			Class.forName(dbdri).newInstance();
			//DriverManager.registerDriver(new DB2Driver());

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @param cmtf
	 * @roseuid 3F4DB31700BD
	 */
	public void init() {
		try {
			ctx = new InitialContext();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (StandCfgPV.get("DBDRI") != null) {
			dbdri = StandCfgPV.get("DBDRI");
		}
		if (StandCfgPV.get("DBURL") != null) {
			url = StandCfgPV.get("DBURL");
		}
		if (StandCfgPV.get("DBUID") != null) {
			userid = StandCfgPV.get("DBUID");
		}
		if (StandCfgPV.get("DBPWD") != null) {
			passwd = StandCfgPV.get("DBPWD");
		}
		if (StandCfgPV.get("DSFLAG") != null) {
			String dsflagstr = StandCfgPV.get("DSFLAG");
			if (dsflagstr.equalsIgnoreCase("true"))
				dsflag = true;
		}
		if (StandCfgPV.get("DSNAME") != null) {
			dsname = StandCfgPV.get("DSNAME");
		}

		initflag = true;
	}

	public DBConnection(boolean cmtf) {
		this();
		try {
			con.setAutoCommit(cmtf);
		} catch (SQLException se) {
		}
	}

	/**
	 * @param curl
	 * @roseuid 3F4DB31700BB
	 */
	public DBConnection(String curl) {
		//added by Paul Gu..
		initialize();

		if (!initflag) {
			init();
		}
		if (curl != null) {
			url = curl;
		}
		try {
			con = DriverManager.getConnection(url, userid, passwd);
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	/**
	 * Constructor for DBCon.
	 * @roseuid 3F4DB31700AC
	 */
	public DBConnection() {
		super();

		//added by Paul Gu..
		initialize();

		if (!initflag) {
			init();
		}
		if (dsflag) {
			try {
				con = getConByDS(dsname, userid, passwd);
				System.out.println("��DATASOURCE��" + dsname + "������ݿ�����");
			} catch (Exception se) {
				se.printStackTrace();
				System.out.println(
					"�޷���DATASOURCE��"
						+ dsname
						+ "������ݿ�����, ��쿴���á�ϵͳ����ʹ�� DriverManager��ȡ����");
				try {
					con = DriverManager.getConnection(url, userid, passwd);

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println(
						"ϵͳ���޷���DATASOURCE��ȡ���ݿ�����, Ҳ�޷��� DriverManager��ȡ���ӣ���쿴���á�");
				}
			}
		} else {

			try {
				System.out.println(
					dbdri + "/b" + url + "----" + userid + " -- " + passwd);
				con = DriverManager.getConnection(url, userid, passwd);

			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("ϵͳ�޷��� DriverManager��ȡ���ӣ���쿴���á�");
			}
		}
	}

	/**
	 * @return java.sql.Connection
	 * @roseuid 3F4DB31700CC
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * @return java.sql.Statement
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB31700CD
	 */
	public Statement createStatement() throws SQLException {
		//modified by Paul.. 2003/10/15 10:52
		Statement statement = con.createStatement();
		return statement;
	}

	/**
	 * @return boolean
	 * @roseuid 3F4DB3170109
	 */
	public boolean commit() {
		try {
			con.commit();
			return true;
		} catch (SQLException sse) {
			return false;
		}
	}

	//
	public void rollback() throws SQLException {
		
			con.rollback();
		
	}
	//conn.rollback(svpt1);

	
	/**
	 * @throws java.sql.SQLException
	 * @roseuid 3F4DB3170119
	 */
	public void close() throws SQLException {
		con.setAutoCommit(true);
		con.close();
	}

	/**
	 * @param argv[]
	 * @roseuid 3F4DB31701E4
	 */
	public static void main(String argv[]) {

		DBConnection dbc = new DBConnection();
		try {
			//retrieve data from the database
			System.out.println("Retrieve some data from the database...");
			Statement stmt = dbc.createStatement();
			ResultSet rs = stmt.executeQuery("select * from shoppingcart where 1=1");
			System.out.println("Received results:");
			//rs.next()returns false when there are no more rows
			while (rs.next()) {
				//String a = rs.getString(1);
				//String str = rs.getString(2);

				System.out.print("   " + rs.getString(6));

				System.out.print("\n");
			}
			rs.close();
			stmt.close();
			//update the database

			dbc.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//added by Paul..[2003/9/27 11:04]  //template method or overrided method..
	protected void initialize() {
	}

	//Get Connection from DataSource, connecting pool
	private Connection getConByDS(String dsname, String uid, String pwd)
		throws NamingException, SQLException {

		//javax.sql.DataSource ds = (DataSource) ctx.lookup("jdbc/ds2");
		javax.sql.DataSource ds = (DataSource) ctx.lookup(dsname);
		//where jdbc/myDS is the name of the resource reference
		//java.sql.Connection conn = ds.getConnection();
		return ds.getConnection(uid, pwd);

	}

	public static void setFlag(boolean flg) {
		initflag = flg;
	}

}
