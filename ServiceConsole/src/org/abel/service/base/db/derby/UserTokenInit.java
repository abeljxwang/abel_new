package org.abel.service.base.db.derby;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class UserTokenInit {

	/* the default framework is embedded */
	private String framework = "embedded";
	private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private String protocol = "jdbc:derby:";
	private String adminid;

	public void init() {
		Properties p = System.getProperties();
		p.setProperty("derby.system.home", "C:\\data\\sample");

		go();
		System.out.println("SimpleApp finished");
	}

	void go() {
		/* load the desired JDBC driver */
		loadDriver();

		Connection conn = null;

		ArrayList<PreparedStatement> statements = new ArrayList<PreparedStatement>(); // list of Statements,
												// PreparedStatements
		PreparedStatement psInsert = null;
		//PreparedStatement psUpdate = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			Properties props = new Properties(); // connection properties

			props.put("user", "s_console");
			props.put("password", "s_console");

			String dbName = "ConsoleDB"; // the name of the database
			conn = DriverManager.getConnection(protocol + dbName
					+ ";create=true", props);
			System.out.println("Connected to and created database " + dbName);
			conn.setAutoCommit(false);
			s = conn.createStatement();
			//statements.add(s);

			// We create a table...
			// rs=s.executeQuery("select * from SYS.SYSTABLES");
			 
			String tablename="";
			 DatabaseMetaData metaData = conn.getMetaData();
			 // get columns
			 rs = metaData.getTables(null, "S_CONSOLE", "%", null);
			 
			 
				while (rs.next()) {

					tablename=rs.getString(3);
					if(tablename.equalsIgnoreCase("UserToken")){ 
						rs.close();
						break;}
					// do something with the result set
				}
				
				
				if(tablename.equalsIgnoreCase("UserToken")){
					
					System.out.println("table:  already exists");
				}
				else{
					s.execute("create table UserToken(login_id varchar(40), login_pwd varchar(16), user_id varchar(40), user_name varchar(50),PRIMARY KEY (login_id))");
					System.out.println("Created table UserToken");

					
				}
			 
			
				rs = s.executeQuery("SELECT login_id, user_id,user_name FROM UserToken");
				
				adminid = null;
				while (rs.next()) {

					System.out.println(rs.getString(1) + " : " + rs.getString(2) + " : " + rs.getString(3) );
					//adminid=rs.getString(1);
					//if(adminid.equalsIgnoreCase("admin")) break;
					// do something with the result set
					
				}
				rs.close();
				
				if(adminid.equalsIgnoreCase("admin")){					
					System.out.println("user: "+adminid+" already exists");
				}
				else{
					
					// and add a initial user info....
					try {
						psInsert = conn
								.prepareStatement("insert into UserToken values (?, ?,?,?)");
						statements.add(psInsert);

						psInsert.setString(1, "abeljxwang");
						psInsert.setString(2, "abel1234");
						psInsert.setString(3, "abeljxwang");
						psInsert.setString(4, "Abel Wang");
						//psInsert.executeUpdate();
						System.out.println("Inserted Abel");

						psInsert.setString(1, "admin");
						psInsert.setString(2, "admin1234");
						psInsert.setString(3, "admin");
						psInsert.setString(4, "ConsoleAdmin");
						//psInsert.executeUpdate();
						System.out.println("Inserted Admin");
					} catch (Exception sql1e) {

						sql1e.printStackTrace();

						// sql1e.
						// System.err.println("  SQL State:  " + sql1e.getSQLState());
						// System.err.println("  Error Code: " + sql1e.getErrorCode());
						System.err.println("  Message:    " + sql1e.getMessage());
					}

					
				}
			

	
			

			conn.commit();
			System.out.println("Committed the transaction");

			if (framework.equals("embedded")) {
				try {
					// the shutdown=true attribute shuts down Derby
					DriverManager.getConnection("jdbc:derby:ConsoleDB;shutdown=true");

					// To shut down a specific database only, but keep the
					// engine running (for example for connecting to other
					// databases), specify a database in the connection URL:
					// DriverManager.getConnection("jdbc:derby:" + dbName +
					// ";shutdown=true");
				} catch (SQLException se) {
					if (((se.getErrorCode() == 50000) && ("XJ015".equals(se
							.getSQLState())))) {
						// we got the expected exception
						System.out.println("Derby shut down normally");
						// Note that for single database shutdown, the expected
						// SQL state is "08006", and the error code is 45000.
					} else {
						// if the error code or SQLState is different, we have
						// an unexpected exception (shutdown failed)
						System.err.println("Derby did not shut down normally");
						printSQLException(se);
					}
				}
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		} finally {
			// release all open resources to avoid unnecessary memory usage

			// ResultSet
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			// Statements and PreparedStatements
			int i = 0;
			while (!statements.isEmpty()) {
				// PreparedStatement extend Statement
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
	}

	/**
	 * Loads the appropriate JDBC driver for this environment/framework. For
	 * example, if we are in an embedded environment, we load Derby's embedded
	 * Driver, <code>org.apache.derby.jdbc.EmbeddedDriver</code>.
	 */
	private void loadDriver() {
		/*
		 * The JDBC driver is loaded by loading its class. If you are using JDBC
		 * 4.0 (Java SE 6) or newer, JDBC drivers may be automatically loaded,
		 * making this code optional.
		 * 
		 * In an embedded environment, this will also start up the Derby engine
		 * (though not any databases), since it is not already running. In a
		 * client environment, the Derby engine is being run by the network
		 * server framework.
		 * 
		 * In an embedded environment, any static Derby system properties must
		 * be set before loading the driver to take effect.
		 */
		try {
			Class.forName(driver).newInstance();
			System.out.println("Loaded the appropriate driver");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("\nUnable to load the JDBC driver " + driver);
			System.err.println("Please check your CLASSPATH.");
			cnfe.printStackTrace(System.err);
		} catch (InstantiationException ie) {
			System.err.println("\nUnable to instantiate the JDBC driver "
					+ driver);
			ie.printStackTrace(System.err);
		} catch (IllegalAccessException iae) {
			System.err.println("\nNot allowed to access the JDBC driver "
					+ driver);
			iae.printStackTrace(System.err);
		}
	}

	/**
	 * Reports a data verification failure to System.err with the given message.
	 * 
	 * @param message
	 *            A message describing what failed.
	 */
	//private void reportFailure(String message) {
	//	System.err.println("\nData verification failed:");
	//	System.err.println('\t' + message);
	//}

	/**
	 * Prints details of an SQLException chain to <code>System.err</code>.
	 * Details included are SQL State, Error code, Exception message.
	 * 
	 * @param e
	 *            the SQLException from which to print details.
	 */
	public static void printSQLException(SQLException e) {
		// Unwraps the entire exception chain to unveil the real cause of the
		// Exception.
		while (e != null) {
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State:  " + e.getSQLState());
			System.err.println("  Error Code: " + e.getErrorCode());
			System.err.println("  Message:    " + e.getMessage());
			// for stack traces, refer to derby.log or uncomment this:
			// e.printStackTrace(System.err);
			e = e.getNextException();
		}
	}

	/**
	 * Parses the arguments given and sets the values of this class' instance
	 * variables accordingly - that is which framework to use, the name of the
	 * JDBC driver class, and which connection protocol protocol to use. The
	 * protocol should be used as part of the JDBC URL when connecting to Derby.
	 * <p>
	 * If the argument is "embedded" or invalid, this method will not change
	 * anything, meaning that the default values will be used.
	 * </p>
	 * <p>
	 * 
	 * @param args
	 *            JDBC connection framework, either "embedded", "derbyclient".
	 *            Only the first argument will be considered, the rest will be
	 *            ignored.
	 */
	public static void main(String[] args) {
		new UserTokenInit().init();
		;

	}
}
