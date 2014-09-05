/*
 * 创建日期 2004-10-12
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.base.message;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;



/**
 * MetaElement
 *.
 * @author Abel Wang
 */
public class MsgHeader {

	private Properties basicinfo = new Properties();
	private Hashtable processes = new Hashtable(); //for the process
	private Vector processlist = new Vector();
	private String operation = MSGBASE.TRAN_OPERATION_SINGLE;
	private String currentpkey = "1";				//update abel @2005-03-03 
	private int currentprocess = 1;

	//Properties runtimestatus = new Properties();
	private Properties rollbackinfo = new Properties();

//	private Properties ext_attributs = new Properties();
    private Vector ext_attributs = new Vector();
    
	private Properties response_info = new Properties();

	public MsgHeader() {
		super();
	}
	
	//add by mazhenmin 2005-03-04
	public int getProcessCount(){
		return processlist.size();
	}
	//add end

	//Set the basci info 
	public void setProperties(String key, String value) {
		if(key!=null) basicinfo.setProperty(key, value);
	}

	//Get the basci info 
	public String getProperties(String key) {
		if (key != null)
			return basicinfo.getProperty(key);
		else
			return null;
	}
	public Properties getAllProperties() {
		return basicinfo;
	}
	
	//add by mazhenmin
	public Vector getExtAttrs(){
		return ext_attributs;
	}
	
	public String getCurrentProcessKey(){
		return currentpkey;
	}
	//add end

	//Set the extend attribute info 
	//modified by mazhenmin 
	public void setExtAttribute(String key, String value,String process) {

		String tmpvalue = null;
        
        MetaElement me;
        if(key!=null)
        {
			//the next is used for only for mulity-value attribute. start
			for(int i=0;i<ext_attributs.size();i++)
			{
				me = (MetaElement)ext_attributs.get(i);
                
				if((me.getName().equals(key))&&
				   (me.getAttribute(MSGBASE.MSG_TRAN_PROCESS).equals(process)))
			    {
					tmpvalue = me.getValue();
					value = tmpvalue + "," + value;	
					
					ext_attributs.remove(i);
					me = new MetaElement();
					me.setName(key);
					me.setValue(value);
					me.setAttribute(MSGBASE.MSG_TRAN_PROCESS,process);
					ext_attributs.add(me);
					return;				
				}

                				
			}
			//set new MetaElement
			if(value ==null) value = "";
			if(process == null) process ="1";//update by sunkey 2005-03-03
			me = new MetaElement();
			me.setName(key);
			me.setValue(value);
			me.setAttribute(MSGBASE.MSG_TRAN_PROCESS,process);
			// add to ext_attributs
			ext_attributs.add(me);
		
        }
	}
	
	//add by mazhenmin 2005.3.11
	public void replaceExtAttribute(String key, String value,String process) {

		String tmpvalue = null;
        
		MetaElement me;
		if(key!=null)
		{
			//the next is used for only for mulity-value attribute. start
			for(int i=0;i<ext_attributs.size();i++)
			{
				me = (MetaElement)ext_attributs.get(i);
                
				if((me.getName().equals(key))&&
				   (me.getAttribute(MSGBASE.MSG_TRAN_PROCESS).equals(process)))
				{
					me.setName(key);
					me.replaceValue(value);
					me.setAttribute(MSGBASE.MSG_TRAN_PROCESS,process);
					return;				
				}  				
			}
			//set new MetaElement
			if(value ==null) value = "";
			if(process == null) process ="1";//update by sunkey 2005-03-03
			me = new MetaElement();
			me.setName(key);
			me.setValue(value);
			me.setAttribute(MSGBASE.MSG_TRAN_PROCESS,process);
			// add to ext_attributs
			ext_attributs.add(me);
		
		}
	}
	
	//add end
	
	//modified by mazhenmin 
	public void setExtAttributes(Vector values) {
		ext_attributs = values;
	}

	//Get the extend attribute info 
	//modified by mazhenmin 
	public String getExtAttribute(String key,String process) {
		
		MetaElement me;
		if (key != null){
			for(int i=0;i<ext_attributs.size();i++){
				me = (MetaElement)ext_attributs.get(i);
                
				if((me.getName().equals(key))&&
				   (me.getAttribute(MSGBASE.MSG_TRAN_PROCESS).equals(process))){
					return me.getValue();
				}
                				
			}
		}
		return null;
	}
	
	
	public Vector getAllExtAttributes() {
		return ext_attributs;
	}

	//Set the error info 
	public void setErrorInfo(String key, String value) {
		if (value == null)
			value = "";
		if(key!=null) response_info.setProperty(key, value);
	}

	public void setErrorInfos(Properties values) {
		response_info = values;
	}

	public void setResponseInfo(String key, String value) {
		if (value == null)
			value = "";
		if(key!=null)  response_info.setProperty(key, value);
	}

	public void appendResponseInfo(String key, String value) {
	    if(key != null && value != null) {
	        String val = response_info.getProperty(key);
	        if(val != null) {
	            String newVal = val + "," + value;
	            response_info.setProperty(key, newVal);
	        }
	        else {
	            response_info.setProperty(key, value);
	        }
	    }
	}
	
	//Get the error info 
	public String getErrorInfo(String key) {
		if (key != null)
			return response_info.getProperty(key);
		else
			return null;
	}
	public Properties getAllErrorInfo() {
		return response_info;
	}
	public String getResponseInfo(String key) {
		if(key!=null)
		return response_info.getProperty(key);
		else return null;
	}

	//Set the rollbackinfo info 
	public void setRollBackInfo(String key, String value) {
		if (value == null)
			value = "";
		if(key!=null) rollbackinfo.setProperty(key, value);
	}
	public void setRollBackInfos(Properties values) {
		rollbackinfo = values;
	}
	//Get the rollbackinfo info 
	public String getRollBackInfo(String key) {
		if(key!=null) 
		return rollbackinfo.getProperty(key);
		else return null;
	}

	public Properties getAllRollBackInfo() {
		return rollbackinfo;
	}

	//Set the process
	public void setProces(String key, TranProcess value) {

		if (key!=null && !processes.containsKey(key))
			processlist.add(key);
		if(key!=null) processes.put(key, value);
		//System.out.println(processlist);
	}

	public void setCurrentProces(String key) {
		currentpkey = key;
		if (key!=null&&processes.get(key) != null) {
			processlist.add(key);
		}
	}

	private int getOrderbyKey(String key) {
		int result = 0;
		for (int k = 0; k < processlist.size(); k++) {
			if (key.equals(processlist.elementAt(k))) {
				result = k;
				currentprocess = k;
				break;
			}

		}
		return result;
	}

	//	Get the process by name
	public TranProcess getProces(String key) {

		TranProcess resultp = null;

		if (key != null && processes != null) {
			if (getOrder(key) >= 0) {
				currentprocess = getOrder(key);

				if (key != null && processes.get(key) != null)
					resultp = (TranProcess) processes.get(key);

			}

		}
		return resultp;

	}

	//	Get  process by order
	public TranProcess getProces(int order) {
		TranProcess result = null;
		if (order <= processlist.size() && order >= 0) {
			currentprocess = order;
			String currentpkey = (String) processlist.get(order);
			result = (TranProcess) processes.get(currentpkey);

		}

		return result;

	}

	public String getProcesID(int order) {
		if (order < processlist.size())
			return processlist.elementAt(order).toString();
		else
			return null;

	}

	//	Get current process
	public TranProcess getCurrentProces() {
		if (getProces(currentpkey) != null)
			return getProces(currentpkey);
		else
			return null;
	}
	//	Get Next process
	public TranProcess getNextProces() {
		currentprocess++;
		return getProces(currentprocess);

	}
	//	Get Pre process
	public TranProcess getPreProces() {
		if (currentprocess > 0) {
			currentprocess--;
			return getProces(currentprocess);
		} else
			return null;

	}

	public void setProcessOrder(int ordnum) {
		currentprocess = ordnum;
	}
	public int getProcessOrder() {
		return currentprocess;
	}

	public int getProcessSize() {
		return processlist.size();
	}

	public void setProcessOperation(String op) {
		operation = op;
	}
	public String getProcessOperation() {
		return operation;
	}

	private int getOrder(String key) {
		int i = 0;
		for (; i < processlist.size(); i++) {
			if (key.equals(processlist.elementAt(i)))
				break;

		}
		return i;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("MsgHeader{\n");
		
		buf.append("***processes***:\n");
		buf.append("current process = " + currentpkey + "\n");
		buf.append("operation = " + operation + "\n");
		for(int i = 0; i < processlist.size(); i++) {
			String processID = (String)processlist.get(i);
			TranProcess process = (TranProcess) processes.get(processID);
			buf.append("Process " + processID + ":\n");
			buf.append(process.toString());
		}
		
		buf.append("***basic info***:\n");
		Iterator iter = basicinfo.keySet().iterator();
		while(iter.hasNext()) {
			String key = (String) iter.next();
			buf.append(key + " = " + (String) basicinfo.getProperty(key) + "\n");
		}
		
		buf.append("***extension attributes***:\n");
   
        //modified by mazhenmin
        iter = ext_attributs.iterator();
		while(iter.hasNext()) {
			MetaElement me = (MetaElement) iter.next();
			buf.append(me.getName() + " = " + me.getValue()+"    process attribute = " +me.getAttribute("process") + "\n");
		}
		//modified end
		
		if(response_info != null && response_info.size() > 0) {
			buf.append("***response***:\n");
			iter = response_info.keySet().iterator();
			while(iter.hasNext()) {
				String key = (String) iter.next();
				buf.append(key + " = " + (String) response_info.getProperty(key) + "\n");
			}
		}
		
		if(rollbackinfo != null && rollbackinfo.size() > 0) {
			buf.append("***rollback***:\n");
			iter = rollbackinfo.keySet().iterator();
			while(iter.hasNext()) {
				String key = (String) iter.next();
				buf.append(key + " = " + (String) rollbackinfo.getProperty(key) + "\n");
			}
		}
		
		buf.append("}\n");
		
		return buf.toString();
	}

	public static void main(String[] args) {
	}
}
