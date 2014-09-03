//Source file: D:\\EIPPortal\\com\\kmtc\\eip\\db\\DBFunction.java

package org.abel.service.base.db;

import java.sql.*;



/**
 * @author wx
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DBFunction {

  /**
   * @param dbc
   * @param sql
   * @throws java.sql.SQLException
   */

  protected void execute(DBConnection dbc, String sql) throws SQLException {
    Statement stmt = dbc.createStatement();
    stmt.executeUpdate(sql);
    //dbc.commit();
  }

  /**
   * @param args
   * @roseuid 3F570F4A03DA
   */
  public static void main(String[] args) {

  }
}
