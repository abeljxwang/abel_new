package org.abel.webapp.policy.base;

public class PolicyRecord {
	
	Object contentp=null;
	Object userp=null;
	Object operator=null;

	/**
	 * 
	 */
	
	public PolicyRecord(Object up,Object cont,  Object opti) {
		contentp=cont;
		userp=up;
		operator=opti;
	}
	
	public boolean verify(){
		//boolean result =Operation.getResult(contentp,userp,operator);
		return false;
	}

}
