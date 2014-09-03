/*
 * 创建日期 2005-5-10
 * CCB EAIH Project team
 */
package org.abel.service.soa.message;

/**
 * @author daijun@cn.ibm.com
 * 
 * 
 */

import java.util.Hashtable;



public class DescCacheManager {

	// wsdlCacheManager caches the content of transactions.
	private static DescCacheManager descCacheManager = null;
	private static Hashtable<String, ServiceDesc> serviceList = new Hashtable<String, ServiceDesc>();
	private static Hashtable<String, ElementDesc> elementList = new Hashtable<String, ElementDesc>();
	//private static Hashtable metaList = new Hashtable();

	/**
	 * Initialize the WSDLCacheManager using the singleton pattern.
	 * 
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
	public Hashtable<String, ServiceDesc> loadServiceDesc() {
		Hashtable<String, ServiceDesc> result = new Hashtable<String, ServiceDesc>();

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

		try {
			if(serviceList.get(s_name)!=null) result=(ServiceDesc)serviceList.get(s_name);
			else {
			
				result = ServiceListDAO.getServiceBasic(s_name);
				if (result!=null) serviceList.put(s_name,result);	
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
	public ElementDesc getMessageDesc(String m_id) {
		ElementDesc result = null;

		try {
			if(elementList.get(m_id)!=null) result=(ElementDesc)elementList.get(m_id);
			else {
			
				result = ElementDataDAO.getMeta(m_id);
				if (result!=null&&result.getName()!=null) elementList.put(m_id,result);	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}	
	
	public static void main(String[] a) throws Exception{
		
		DescCacheManager dm = DescCacheManager.getInstance();
		//ElementDataDAO sdao = new ElementDataDAO();

		 ElementDesc h = dm.getMessageDesc("E00001");
		
		
		System.out.println(h);

		
	}


}
