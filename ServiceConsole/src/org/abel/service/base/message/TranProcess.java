/*
 * 创建日期 2004-10-12
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.base.message;

import java.util.Iterator;
import java.util.Properties;

/**
 * MetaElement
 *.
 * @author Abel Wang
 */
public class TranProcess {

	//	<process id=”100”>
	//			<transaction_id>DCC003031</transaction_id>
	//			<status>COMPLETE</status>
	//			<process_timestamp>/-200410101230000000-/</process_timestamp>
	//			<rollback>N</rollback>

	String transaction_id = null;
	String timestamp = null;
	boolean rollbackflag = false;
	Properties attrs = new Properties();

	/**
	 * 
	 */
	public TranProcess() {
		super();
	}

	public void setID(String str) {
		setAtrribute(MSGBASE.MSG_TRAN_ID,str);
		transaction_id = str;
	}

	public void setTimeStamp(String str) {
		setAtrribute(MSGBASE.MSG_TRAN_PROCESSTIME,str);		
		timestamp = str;
	}

//	public void setRollBack(boolean flag) {
//		setAtrribute(CCBMSG.MSG_TRAN_PROCESSTIME,str);		
//		rollbackflag = flag;
//	}

	public void setAtrribute(String key, String value) {
		attrs.setProperty(key, value);
	}

	public String getID() {
		if(transaction_id==null) transaction_id=getAtrribute(MSGBASE.MSG_TRAN_ID);
		return transaction_id;
	}

	public String getTimeStamp() {
		if(timestamp==null) timestamp=getAtrribute(MSGBASE.MSG_TRAN_PROCESSTIME);		
		return timestamp;
	}

//	public boolean setRollBack() {
//		return rollbackflag;
//	}

	public String getAtrribute(String key) {
		return attrs.getProperty(key);
	}
	public void setAllAtrributes(Properties values) {
		 attrs=values;
	}	
	public Properties getAllAtrributes() {
		 return attrs;
	}	

	public String toString() {
		StringBuffer buf = new StringBuffer();
		//buf.append("Process " + processID + ":\n");	s
		Iterator keyIter = attrs.keySet().iterator();
		while(keyIter.hasNext()) {
			String key = (String) keyIter.next();
			buf.append(key + " = " + (String) attrs.getProperty(key) + "\n");
		}
		return buf.toString();
	}
}
