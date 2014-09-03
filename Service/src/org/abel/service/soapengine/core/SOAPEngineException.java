
package org.abel.service.soapengine.core;

public class SOAPEngineException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String e_code="0000000000";
	String actor="SOAPEngine";
	public SOAPEngineException(String ecode,String arg0) {
		super(arg0);
		e_code=ecode;
	}
	
	public String getErrorCode() {
		return e_code;
	}
	
}
