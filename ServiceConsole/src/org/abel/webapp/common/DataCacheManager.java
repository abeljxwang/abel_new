/*
 * 创建日期 2005-5-10
 * CCB EAIH Project team
 */
package org.abel.webapp.common;

/**
 * @author wangjx@cn.ibm.com
 * 
 * 
 */

import java.util.Hashtable;
import java.util.Properties;

import org.abel.webapp.db.DBHandler;
import org.abel.webapp.db.EIPResultSet;



public class DataCacheManager {

	// wsdlCacheManager caches the content of transactions.
	private static DataCacheManager descCacheManager = null;

	private static Properties tb_row_count_List= new Properties();


	/**
	 * Initialize the WSDLCacheManager using the singleton pattern.
	 * 
	 * @return WSDLCacheManager
	 */
	private DataCacheManager getInstance() {

		try {
			if (null == descCacheManager) {
				return new DataCacheManager();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return descCacheManager;
	}

	private  DataCacheManager() {}



	
	

	/**
	 * Get Service desc from a serviceid/name
	 * 
	 * @param conn
	 * @return
	 */
	public static int CountAllRows(String tb_key) {
		int size=0;
		if(tb_row_count_List.getProperty(tb_key)!=null)
			size=Integer.parseInt(tb_row_count_List.getProperty(tb_key));
		else{
			String sql="select count(1) from "+tb_key;
			DBHandler dbh = new DBHandler();
			try {
				EIPResultSet eresult = dbh.query(sql);
				dbh.close();
				dbh = null;
				if(eresult.next()){ size=eresult.getInt(1);
				tb_row_count_List.setProperty(tb_key, size+"");
				}
				
			} catch (Exception se) {
				se.printStackTrace();
			} finally {
				if (dbh != null)
					dbh.close();
			}
			
			
		}
		return size;

	}	
	/**
	 * Get Service desc from a serviceid/name
	 * 
	 * @param conn
	 * @return
	 */
	public static int CountAllRows(String tb_key,String key,String value) {
		int size=0;
		
			String sql="select count(1) from "+tb_key +" where "+key+" like '%"+value+"%'";
			//System.out.println(sql);
			DBHandler dbh = new DBHandler();
			try {
				EIPResultSet eresult = dbh.query(sql);
				dbh.close();
				dbh = null;
				if(eresult.next()){ size=eresult.getInt(1);
				}
				
			} catch (Exception se) {
				se.printStackTrace();
			} finally {
				if (dbh != null)
					dbh.close();
			}
			
			
		
		return size;

	}
	
	public static void main(String[] a) throws Exception{
		
		//CoreDataCacheManager dm = CoreDataCacheManager.getInstance();
		//ElementDataDAO sdao = new ElementDataDAO();

		 //ElementDesc h = dm.getMessageDesc("E00001");
		
		
		//System.out.println(h);

		
	}


}
