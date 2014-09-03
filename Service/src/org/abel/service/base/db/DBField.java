package org.abel.service.base.db;

/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DBField {

	String name=null;
	String fieldname=null;
	int length=0;
	boolean viewflag=false;
	String type="String";
	
	/**
	 * Constructor for DBField.
	 */
	public DBField(String fieldnamei,String namei) {
		name=namei;
		fieldname=fieldnamei;
			
	}

	public void setlen(int len){
		length=len;
	}	
	public void setview(boolean fg){
		viewflag=fg;
	}
	public void settypr(String tp){
		type=tp;
	}

	
	public boolean getview(){
		return viewflag;
	}
	public String gettypr(){
		return type;
	}
	public int getlen(){
		return length;
	}
	public String getname(){
		return name;
	}
	public String getfield(){
		return fieldname;
	}
	public int getlength(){
		return length;
	}

	
}
