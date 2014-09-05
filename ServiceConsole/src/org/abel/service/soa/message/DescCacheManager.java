/*
 * �������� 2005-5-10
 * CCB EAIH Project team
 */
package org.abel.service.soa.message;

/**
 * @author wangjx@cn.ibm.com
 * 
 * 
 */

import java.util.Hashtable;



public class DescCacheManager {

	// wsdlCacheManager caches the content of transactions.
	private static boolean flag_s_refresh=true;
	private static boolean flag_e_refresh=true;
	private static DescCacheManager descCacheManager = null;
	private static Hashtable serviceList = new Hashtable();
	private static Hashtable elementList = new Hashtable();
	private static Hashtable metaList = new Hashtable();

	/**
	 * Initialize the WSDLCacheManager using the singleton pattern.
	 * @return WSDLCacheManager
	 */
	
	
	public static DescCacheManager getInstance() {

		try {
			if (null == descCacheManager) {
				// if WSDLCacheManager hasn't initialized.
				// TransactionLoader transactionLoader = new
				// TransactionLoader();
				// transactionLoader.loadAllTransaction(transactionList);
				return new DescCacheManager();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return descCacheManager;
	}

	/**
	 * load extended message by AppSystemID
	 * 
	 * @param conn
	 * @return
	 */
	
	public static void refresh() {
		flag_s_refresh=true;
		flag_e_refresh=true;
		elementList.clear();
		DescCacheManager.loadServiceDesc();
		//DescCacheManager.
		
		
	}

	
	public static Hashtable loadServiceDesc() {
		Hashtable result = new Hashtable();

		try {
			serviceList = ServiceListDAO.getServiceBasicList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	/**
	 * Get Dervice desc from a serviceid/name
	 * 
	 * @param conn
	 * @return
	 */
	public ServiceDesc getServiceDesc(String s_name) {
		ServiceDesc result = null;
		if(flag_s_refresh) {
			result = ServiceListDAO.getServiceBasic(s_name);
			if (result!=null) serviceList.put(s_name,result);	
			flag_s_refresh=false;
		
		}else{

		try {
			if(serviceList.get(s_name)!=null) result=(ServiceDesc)serviceList.get(s_name);
			else {
			
				result = ServiceListDAO.getServiceBasic(s_name);
				if (result!=null) serviceList.put(s_name,result);	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		return result;
	}
	
	

	/**
	 * Get Service desc from a serviceid/name
	 * 
	 * @param conn
	 * @return
	 */
	public ElementDesc getMessageDesc(String m_id) {
		ElementDesc result = null;
		try {
		if(flag_e_refresh) {
			result = ElementDataDAO.getMeta(m_id);
			if (result!=null&&result.getName()!=null) elementList.put(m_id,result);	
			flag_e_refresh=false;
		
		}else{

		
			if(elementList.get(m_id)!=null) result=(ElementDesc)elementList.get(m_id);
			else {
			
				result = ElementDataDAO.getMeta(m_id);
				if (result!=null&&result.getName()!=null) elementList.put(m_id,result);	
			}

		
		}} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	
	public static void main(String[] a) throws Exception{
		
		DescCacheManager dm = DescCacheManager.getInstance();
		//ElementDataDAO sdao = new ElementDataDAO();

		// ElementDesc h = dm.getMessageDesc("E100001");
		
		
		//System.out.println(h);
		
		System.out.println(dm.getServiceDesc("Order_Mgr").getOperation("query_Order_Info").getInputName());
		System.out.println(dm.getMessageDesc("E100001"));
		
	}


}
