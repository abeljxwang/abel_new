/*
 * 创建日期 2005-4-19
 * CCB EAIH Project team
 */
package org.abel.service.wsdlengine.common;

/**
 * @author wangjx@cn.ibm.com
 *
 * 
 */
public class WSDLEngineException extends Exception {

	/**
	 * 
	 */
	String errocode=null;
	public WSDLEngineException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public WSDLEngineException(String arg0) {
		super(arg0);
	}
	
	public WSDLEngineException(String ecode,String arg0) {
		super(arg0);
		errocode=ecode;
	}
	
	

}
