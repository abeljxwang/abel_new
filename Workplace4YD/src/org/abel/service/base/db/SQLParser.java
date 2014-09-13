//Source file: D:\\EIPPortal\\com\\kmtc\\eip\\db\\SQLParser.java

package org.abel.service.base.db;

import java.util.*;

/**
 * @author wx
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SQLParser 
{
   String sql;
   
   /**
    * @param sqltmp
    * @roseuid 3F4DB318008E
    */
   public void set(String sqltmp) 
   {
    sql = sqltmp;    
   }
   
   /**
    * generate operation type
    * @param sqltmp
    * @return java.lang.String
    * @roseuid 3F4DB318009D
    */
   public String setop(String sqltmp) 
   {
    if ( ( (sqltmp.trim()).toLowerCase()) == "select") {
      sql = "select ";
    }
    else if ( ( (sqltmp.trim()).toLowerCase()) == "insert") {
      sql = "insert into ";
    }
    else if ( ( (sqltmp.trim()).toLowerCase()) == "delete") {
      sql = "delete from ";
    }
    else if ( ( (sqltmp.trim()).toLowerCase()) == "update") {
      sql = "update ";
    }
    else {
      sql = sqltmp;

    }
    return sql;    
   }
   
   /**
    * generate condition
    * 现在只考虑了and条件，or条件还未考虑
    * @param sqlcdt
    * @return java.lang.String
    * @roseuid 3F4DB318009F
    */
   public String setcdt(Properties sqlcdt) 
   {

    String strtmp = "";
    int i = sqlcdt.size();
    Enumeration ee = sqlcdt.keys();
    while (ee.hasMoreElements()) {
      String col = (String) ee.nextElement();
      strtmp = strtmp + col + "=";
      String val = sqlcdt.getProperty(col);
      strtmp = strtmp + "'" + val + "' ";
      i--;
      if (i > 0) {
        strtmp = (strtmp + "and ");
      }
    }
    sql = sql + " where " + strtmp;
    return sql;    
   }
   
   /**
    * generate table
    * @param sqltbn
    * @return java.lang.String
    * @roseuid 3F4DB31800BC
    */
   public String settbn(String sqltbn) 
   {
    sql = sql + sqltbn;
    return sql;    
   }
   
   /**
    * generate entire sql
    * @return java.lang.String
    * @roseuid 3F4DB31800DC
    */
   public String getSql() 
   {
    return sql;    
   }
   
   /**
    * @param args
    * @roseuid 3F570F4B003E
    */
   public void main(String[] args) 
   {
    //SQLParser sp = new SQLParser();
    //String sql=null;
    //for testing select with condition
    //		sp.setop("select");
    //		sp.settbn("tmp");
    //		sp.getSql();
    //		System.out.println(sql);
    //		return sql;

    //		String[] aaa = { "a", "b", "c" };
    //		String sss = null;
    //		sp.set(aaa, "tmp", sss);

    //for testing insert
    //String[] aaa = { "4", "parser insert ok" };
    //sp.set("tmp", aaa);    
   }
   
   /**
    * generate select columns
    * @param sqlcol
    * @return java.lang.String
    * @roseuid 3F570F4B006E
    */
   public String setfld(String[] sqlcol) 
   {
    String strtmp = "";
    int len = sqlcol.length;
    for (int i = 0; i < len; i++) {
      strtmp = strtmp + sqlcol[i];
      if (i < (len - 1)) {
        strtmp = strtmp + ",";
      }
    }
    sql = sql + strtmp + " from ";
    System.out.println(sql);
    return sql;    
   }
   
   /**
    * generate update values
    * @param sqlcol
    * @param sqlval
    * @roseuid 3F570F4B007E
    */
   public void setupdtvls(String[] sqlcol, String[] sqlval) 
   {
    
   }
   
   /**
    * generate insert values
    * @param sqlistvls
    * @return java.lang.String
    * @roseuid 3F570F4B008E
    */
   public String setistvls(String[] sqlistvls) 
   {

    String strtmp = " values('";
    int len = sqlistvls.length;
    for (int i = 0; i < len; i++) {
      strtmp += sqlistvls[i];
      if (i < (len - 1)) {
        strtmp = strtmp + "','";
      }
      if (i == (len - 1)) {
        strtmp = strtmp + "')";
      }
    }
//		sql = "insert into " + tbname + " values(" + strtmp+")";
    sql = sql + strtmp;
    return sql;    
   }
   /**
    * for testing
    * generate sql
    * public void set(String tbname) {
    * String Strtmp = tbname+" ";
    * System.out.println(sql);
    * }
    */
}
/**
 * 
 * //for testing
 * //generate insert sql
 * public void set(String tbname,String[] sqltmp) {
 * //sql=sqltmp;
 * String strtmp = "'";
 * int len = sqltmp.length;
 * for (int i = 0; i < len; i++) {
 * strtmp += sqltmp[i];
 * if (i < (len - 1))
 * strtmp = strtmp + "','";
 * if (i==(len-1))
 * strtmp = strtmp + "'";
 * }
 * sql = "insert into " + tbname + " values(" + strtmp+")";
 * System.out.println(sql);
 * }
 * }
 * //generate select sql with string condition
 * public void set(String[] sqltmp, String tbname, String condition) {
 * //sql=sqltmp;
 * String strtmp = "";
 * int len = sqltmp.length;
 * for (int i = 0; i < len; i++) {
 * strtmp += sqltmp[i];
 * if (i < (len - 1))
 * strtmp = strtmp + ",";
 * }
 * sql = "select " + strtmp + " from " + tbname;
 * if (condition != null)
 * sql = sql + " where " + condition;
 * System.out.println(sql);
 * }
 * //generate select sql with DBCondition condition
 * public void set(String[] sqltmp, String tbname, SQLCondition dbcondition) {
 * //sql=sqltmp;
 * String strtmp = "";
 * int len = sqltmp.length;
 * for (int i = 0; i < len; i++) {
 * strtmp += sqltmp[i];
 * if (i < len)
 * strtmp = strtmp + ",";
 * }
 * sql = "select " + strtmp + " from " + tbname;
 * if (dbcondition != null)
 * sql = sql + " where " + dbcondition.toString();
 * //for testing
 * System.out.println(sql);
 * }
 * 
 */
