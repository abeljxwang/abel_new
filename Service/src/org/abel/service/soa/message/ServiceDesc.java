package org.abel.service.soa.message;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class ServiceDesc {
	String name=null;
	String cname=null;
	String desc=null;
	String endpoint=null;
	Hashtable<String, OperationDesc> operations=new Hashtable<String, OperationDesc>();
	
	public ServiceDesc(String nm){
		name=nm;
		
	}
	
	public ServiceDesc(String nm,String ep){
		name=nm;
		endpoint=ep;
		
	}
	
	public void setName(String nm){name=nm; }
	public String getName(){ return name; }
	
	public void setEndPoint(String ep){endpoint=ep; }
	public String getEndPoint(){ return endpoint; }
	
	public void setChineseName(String cn){cname=cn; }
	public String getChineseName(){ return cname; }
	
	public void setDesc(String dsc){desc=dsc; }
	public String getDesc(){ return desc; }
	
	public void setOperation(OperationDesc op){
		operations.put(op.getOperationName(), op);
		}
	
	public OperationDesc getOperation(String key){
		OperationDesc od=null;
		if(key!=null&&operations.get(key)!=null) od=(OperationDesc)operations.get(key);
		return od; 
		}

	public Hashtable<String, OperationDesc> getAllOperation(){
		return operations;
		}
	public Vector<String> getAllOperationKeys(){
		Vector<String> keys=new Vector<String>();
		Enumeration<String> keys_e=operations.keys();
		while(keys_e.hasMoreElements()) keys.add(keys_e.nextElement().toString());
		
		return keys;
		}
	
	
	public String toString() {

		return "{"+name+", "+cname+","+ desc+","+endpoint+","+operations+"}";
	}
	
	
}
