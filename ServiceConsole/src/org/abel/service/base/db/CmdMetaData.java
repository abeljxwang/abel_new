package org.abel.service.base.db;

import java.util.Vector;



/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CmdMetaData {
	Vector<DBField> value=new Vector<DBField>();
	
	/**
	 * Constructor for CmdMetaData.
	 */
	
	public Vector<DBField> getall() {
		return value;
	}
	public void set(DBField dbf) {
		value.add(dbf);
	}

	public void set(int index,DBField dbf) throws Exception{
		//System.out.println(index+"--"+dbf.getname());
		value.add(index,dbf);
	}

	
	//throws Exception
	
	public void setView(int index) {
		DBField rsult=(DBField)value.get(index);
		rsult.setview(true);
		value.set(index,rsult);
	}
	public DBField get(int index) {
		DBField rsult=(DBField)value.get(index);
		return rsult;
	}

	public int size() {
		return value.size();
	}
	public static void main(String[] args) {
		try{
		CmdMetaData cmd=new CmdMetaData();
		DBField dbf=new DBField("a","a1");

		for(int i=0;i<5;i++){
		cmd.set(i,dbf);
		}
		}catch(Exception e){
		e.printStackTrace();
		
		}
	}
}
