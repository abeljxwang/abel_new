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



public class CoreDataCacheManager {

	// wsdlCacheManager caches the content of transactions.
	private static CoreDataCacheManager descCacheManager = null;

	private static Properties elementList= null;
	private static Properties servlistList= null;

	/**
	 * Initialize the WSDLCacheManager using the singleton pattern.
	 * 
	 * @return WSDLCacheManager
	 */
	private CoreDataCacheManager getInstance() {

		try {
			if (null == descCacheManager) {
				return new CoreDataCacheManager();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return descCacheManager;
	}

	private  CoreDataCacheManager() {}


	/**
	 * Get Service desc from a serviceid/name
	 * 
	 * @param conn
	 * @return
	 */
	public static Properties getAllMessageDesc(String servicename) {
		Properties result = null;

		try {
			
			
				result = DataDAO.getMeta(servicename);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	

	/**
	 * Get Service desc from a serviceid/name
	 * 
	 * @param conn
	 * @return
	 */
	public static Properties getAllMessageDesc() {
		Properties result = null;

		try {
			if(elementList!=null) result=elementList;
			else {
			
				result = DataDAO.getMeta();
				 elementList=result;	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}	

	/**
	 * Get Service desc from a serviceid/name
	 * 
	 * @param conn
	 * @return
	 */
	public static Properties getAllServices() {
		Properties result = null;

		try {
			if(servlistList!=null) result=servlistList;
			else {
			
				result = DataDAO.getServices();
				servlistList=result;	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	/**
	 * Get Service desc from a serviceid/name
	 * 
	 * @param conn
	 * @return
	 */
	public static void refreshAllMessageDesc() {
		
		elementList=null;
		getAllMessageDesc();

	}	
	
	public static void main(String[] a) throws Exception{
		
		//CoreDataCacheManager dm = CoreDataCacheManager.getInstance();
		//ElementDataDAO sdao = new ElementDataDAO();

		 //ElementDesc h = dm.getMessageDesc("E00001");
		
		
		//System.out.println(h);

		
	}


}
