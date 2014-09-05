//Source file: D:\\EIPPortal\\com\\kmtc\\eip\\db\\DBConnection.java

package org.abel.service.base.db;

import javax.naming.*;

import java.sql.*;


//import com.ibm.db2.jcc.DB2Driver;

/**
 * @author Abel Wang, Wang Xi
 * Just for obtain the connection to special Database
 * Befor using such object, please make sure you have know the
 * DB connectuion url, db driver,DB type, user id and password
 */

public class TestA {
	static boolean initflag = false;
	InitialContext ctx;
	Connection con = null;

	static String dbdri = "oracle.jdbc.driver.OracleDriver"; //"com.ibm.db2.jcc.DB2Driver";
//	//"COM.ibm.db2.jdbc.net.DB2Driver";  //;
	static String url = "jdbc:oracle:thin:@10.2.1.199:1521:omsdb"; //"jdbc:db2://200.200.200.1:50000/ABELTST";
	//"jdbc:db2://localhost:50000/abelsys"; //

		//static String dbdri = "COM.ibm.db2.jdbc.app.DB2Driver";
		//static String url = "jdbc:db2:abelsys";	
	//
	static String userid = "oms";
	static String passwd = "oms";

	boolean dsflag = false;
	public static String dsname = "jdbc/EIP";

	static {
		try {
				Class.forName(dbdri).newInstance();
				//DriverManager.registerDriver(new DB2Driver());
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}



	/**
	 * Constructor for DBCon.
	 * @roseuid 3F4DB31700AC
	 */

	
	public static void main(String argv[]) {

		Connection con = null;
		try {


				con = DriverManager.getConnection(url, userid, passwd);

		
			//retrieve data from the database
			System.out.println("Retrieve some data from the database...");
			Statement stmt = con.createStatement();
			ResultSet rs =
				stmt.executeQuery("select * from FL where 1=1");
			System.out.println("Received results:");
			//rs.next()returns false when there are no more rows
			rs.next();
				
				
//				byte[] btts=rs.ggetBytes(1);
//
//				
//				System.out.print("\n");
//				
//				for(int i=0;i<btts.length;i++) System.out.println(btts[i]);

			String s=rs.getString(2);
			System.out.println(s);
			    //ccc=new String(aaab,"UTF-8");
				//System.out.println(ccc+"------");
		
			rs.close();
			stmt.close();
			//update the database

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
