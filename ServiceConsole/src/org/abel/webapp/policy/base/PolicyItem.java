package org.abel.webapp.policy.base;

public class PolicyItem {
	
	UnitObj A=null;
	UnitObj B=null;
	Operator operator=null;
	
	String type="";

	/**
	 * 
	 */
	public PolicyItem() {

	}
		
	public PolicyItem(UnitObj cont,UnitObj up,  Operator opti) {
		A=cont;
		B=up;
		operator=opti;
	}
	
	public void setA(UnitObj a){
		A=a;
	}
	public void setB(UnitObj b){
		B=b;
	}	
	public void setOperator(Operator opti){
		operator=opti;
	}
	
		

}
