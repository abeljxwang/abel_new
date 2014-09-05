package org.abel.webapp.policy.base;
import java.util.Vector;


public class Policy {

	
	String pid = null;
	Vector policies = new Vector();


	/**
	 * 
	 */
	public void setkey(String key){
	pid=key;
	}
	
	public String getkey(){
	   return pid;
	};
	
	
	public void add(PolicyItem pr) {
		policies.add(pr);
	}

	public int size() {
		return policies.size();
	}

	public PolicyItem get(int i) {
		return (PolicyItem)policies.elementAt(i);
	}

}
