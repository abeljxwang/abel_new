package org.abel.service.soa.message;

import java.util.Vector;

public class ElementDesc {
	Vector<MetaDesc> cont_list=new Vector<MetaDesc>();
	String name=null;
	String eid=null;
	
	public ElementDesc(String nm){
		name=nm;	
	}
	public ElementDesc(){
		
	}
	
	public int size(){
		
		return cont_list.size();
	}
	
	public void setID(String idt){eid=idt; }
	public String getID(){ return eid; }
	
	public void setName(String nm){name=nm; }
	public String getName(){ return name; }
	
	public void AddMeta(MetaDesc md){
		
		cont_list.add(md);
		
	}

	public Vector<MetaDesc> getAllMetaDesc(){ return cont_list;}
	
	public MetaDesc getMetaDesc(int i){ 
		MetaDesc md=null;
		if(i<cont_list.size()&&cont_list.get(i)!=null) md=(MetaDesc)cont_list.get(i);
		return md;}
	
	public int getMetaAcount(){ 

		return cont_list.size();}
	
	public String toString() {

		return "{"+name+":,"+ cont_list+"}";
	}
	
}
