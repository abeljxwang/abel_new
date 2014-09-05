/*
 * 创建日期 2005-5-10
 * CCB EAIH Project team
 */
package com.ibm.ais.util;

/**
 * @author wangjx@cn.ibm.com
 * 
 * 
 */


import java.util.Properties;

import org.abel.webapp.db.DBHandler;
import org.abel.webapp.db.EIPResultSet;


public class MVCDataCacheManager {

	// wsdlCacheManager caches the content of transactions.
	private static MVCDataCacheManager descCacheManager = null;

	private static Properties sysList= null;
	private static boolean sysListRF= true;
	
	/**
	 * Initialize the WSDLCacheManager using the singleton pattern.
	 * 
	 * @return WSDLCacheManager
	 */
	private MVCDataCacheManager getInstance() {

		try {
			if (null == descCacheManager) {
				return new MVCDataCacheManager();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return descCacheManager;
	}

	private  MVCDataCacheManager() {}




	public static Properties getAllSysElements() {
		Properties result = null;

		try {
			if(sysListRF){
			if(sysList!=null) result=sysList;
			else {			
				result = DataDAO.getSystemList();
				sysList=result;	
				sysListRF=false;
			}}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}	

	public static void refreshSysElements() {

		sysListRF=true;
	}	
	
	public static int CountAllRows(String tb_key,String key,String value) {
		int size=0;
		
			String sql="select count(1) from "+tb_key +" where "+key+" like '%"+value+"%'";
			System.out.println(sql);
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
		
		
	}


}
