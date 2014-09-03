/*
 * 创建日期 2004-10-12
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.base.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import java.util.Set;
import java.util.Iterator;



/**
 * MetaElement
 *.
 * @author Abel Wang
 */
public class MsgObject {

	MsgHeader header = new MsgHeader();
	MsgBody request = new MsgRequest();
	//add by mazhenmin 2005.3.11
	MsgBody multirequest = new MsgRequest();
	//add end
	
	MsgBody response = null;

    //add by mazhenmin
	HashMap hmResp = new HashMap();
    //add end
    
	public MsgObject() {
	}
	
	
	//add by mazhenmin
	public MsgBody creatResponse(){
		MsgBody resultMb = new MsgBody();
		return resultMb;
	}
	
	public HashMap getAllResponses(){
		return hmResp;
	}
    //add end
    
	public void addHeader(MsgHeader hd) {
		header = hd;
	}
	public MsgHeader getHeader() {
		return header;
	}

	public void addRequest(MsgBody req) {
		request = req;
	}
	
	//add by mazhenmin 2005.3.11
	public void addMultiRequest(MsgBody req) {
		multirequest = req;
	}

	public MsgBody getMultiRequest() {
		return multirequest;
	}	
	//add end
	
	
	public MsgBody getRequest() {
		return request;
	}

    //modify by mazhenmin
	public void addResponse(MsgBody res, String process) {
//		response = res;
		if(process==null) return;//update by sunkey 2005-03-03
        if(hmResp.get(process)==null){
			hmResp.put(process,res);
        }
	}
	//modify end
	
	public MsgBody getResponse() {
		if(response == null)
		   getCurrentResponse();
		return response;
	}

	//for ender user friendly set
	public void setTransactionID(String id) {
		header.setProperties(MSGBASE.MSG_TRAN_ID, id);
	}
	public void setResponseStatus(String status) {
		header.setResponseInfo(MSGBASE.MSG_TRAN_RESP_STATUS, status);
	}

	public void setResponseInfoC_D(String res_code,String res_msg) {
		//header.setResponseInfo(CCBMSG.MSG_TRAN_RESP_CODE, res_code);
		//header.setResponseInfo(CCBMSG.MSG_TRAN_RESP_DESC, res_msg);
	    header.appendResponseInfo(MSGBASE.MSG_TRAN_RESP_CODE, res_code);
	    header.appendResponseInfo(MSGBASE.MSG_TRAN_RESP_DESC, res_msg);
	}



	public void setCurrentProcessStatus(String status) {
		if (header.getCurrentProces() != null)
			header.getCurrentProces().setAtrribute(
				MSGBASE.MSG_TRAN_STATUS,
				status);
		else {
			TranProcess ttp = new TranProcess();
			ttp.setAtrribute(MSGBASE.MSG_TRAN_STATUS, status);
			header.setProces("1", ttp);
		}
	}

	public void setErrorMsg(String error_code, String error_msg) {
		setResponseInfoC_D(error_code,error_msg);
	}

	public void setHeaderAttribute(String key, String value) {
		header.setProperties(key, value);
	}

	public void setExtAttribute(String key, String value) {
//      header.setExtAttribute(key,value);
        //add by mazhenmin
        
		//String strProcess = header.getCurrentProcessKey();
		/* update by sunkey 2005-03-03
		int i = 0;
		i = header.getProcessOrder();
		String process = Integer.toString(i);
		*/
		String process = null;
		if(header.getCurrentProcessKey()!= null)            
			process = header.getCurrentProcessKey();    //update by Abel @2005-03-03 
		else 
			process = Integer.toString(header.getProcessOrder());

		header.setExtAttribute(key, value, process);
		//add end
	}

	//add by mazhenmin 2005.3.11
	public void replaceExtAttribute(String key, String value) {

		String process = null;
		if(header.getCurrentProcessKey()!= null)            
			process = header.getCurrentProcessKey();    //update by Abel @2005-03-03 
		else 
			process = Integer.toString(header.getProcessOrder());

		header.replaceExtAttribute(key, value, process);
		//add end
	}
	//add end

	public void setRequestParameter(String key, String value) {
		request.setValue(key, value);
	}

	public void setCurrentProcess(int order) {
		header.setProcessOrder(order);
		
		//add by mzm
		//同时设置当前的response
		String strProcess = Integer.toString(order);
		MsgBody response = (MsgBody)hmResp.get(strProcess);
		if(null==response)
		{
			response = creatResponse();
			hmResp.put(strProcess,response);
		}
		this.response = response;
		//add end
	}
	
	//add by mazhenmin
	public void getCurrentResponse(){
		String strProcess = header.getCurrentProcessKey();
		MsgBody response = (MsgBody)hmResp.get(strProcess);
		if(null==response)
		{
			response = creatResponse();
			hmResp.put(strProcess,response);
		}
		this.response = response;
		
	}
	//add end

	public TranProcess getCurrentProcess() {
		return header.getCurrentProces();
	}

	//	set request value
	public void setRequestParameter(
		String key,
		String value,
		Properties attrs) {
		MetaElement me = new MetaElement();
		me.setAttributes(attrs);
		me.setValue(value);
		me.setName(key);
		request.setElement(key, me);
	}

	public void setRequestArrayParameters(String key, String value) {
		request.setArrayValue(key, value);
	}

	public void setRequestArrayParameters(String key, ArrayList values)
		throws Exception {
		request.setArrayValues(key, values);
	}

	public void setRequestGroupArrayParameters(String key, ArrayList values)
		throws Exception {
		request.setGroupArrayValues(key, values);
	}

	//set response value
	public void setResponseParameter(String key, String value) {
		if(response == null)
		   getCurrentResponse();

		response.setValue(key, value);
	}

	public void setResponseParameter(
		String key,
		String value,
		Properties attrs) {
	    if(response == null)
		    getCurrentResponse();

		MetaElement me = new MetaElement();
		me.setAttributes(attrs);
		me.setValue(value);
		me.setName(key);
		response.setElement(key, me);
	}

	public void setResponseArrayParameters(String key, String value) {
		if(response == null)
		   getCurrentResponse();
		response.setArrayValue(key, value);
	}

	public void setResponseArrayParameters(String key, ArrayList values)
		throws Exception {
	    if(response == null)
		   getCurrentResponse();
	    response.setArrayValues(key, values);
	}

	public void setResponseGroupArray(String key, ArrayList values)
		throws Exception {
	    if(response == null)
			   getCurrentResponse();
		response.setGroupArrayValues(key, values);
	}

	public void setResponseGroupParameters(String key, GroupRecord values)
		throws Exception {
		if(response == null)
			   getCurrentResponse();
		response.setGroupValues(key, values);
	}

	//for get

	public String getTransactionID() {
		return header.getProperties(MSGBASE.MSG_TRAN_ID);
	}

	public String getResponseStatus() {
		return header.getResponseInfo(MSGBASE.MSG_TRAN_RESP_STATUS);
	}
	public String getResponseCode() {
		return header.getResponseInfo(MSGBASE.MSG_TRAN_RESP_CODE);
	}
	public String getResponseDesc() {
		return header.getResponseInfo(MSGBASE.MSG_TRAN_RESP_DESC);
	}	


	public String getCurrentProcessStatus() {
		//header.setProperties();
		String result = null;
		if (header.getCurrentProces() != null) {
			result =
				header.getCurrentProces().getAtrribute(MSGBASE.MSG_TRAN_STATUS);

		}
		return result;
	}

	public String getErrorMsgCode() {
		Properties pp = new Properties();
		if (header.getAllErrorInfo() != null) {
			pp = header.getAllErrorInfo();
		}
		return pp.get(MSGBASE.MSG_TRAN_ERROR_NO) + "";

	}
	
	public String getErrorMsgInfo() {
		Properties pp = new Properties();
		if (header.getAllErrorInfo() != null) {
			pp = header.getAllErrorInfo();
		}
		return pp.get(MSGBASE.MSG_TRAN_ERROR_DESC) + "";
	}

	public String getHeaderAttribute(String key) {
		return header.getProperties(key);
	}
	
	/* add by sunkey 2004-03-03 */
	public String getRequestExtAttribute(String key){
		
		String value = getExtAttribute(key);
		if(value==null) value = header.getExtAttribute(key, "1");
		return value;
	}

	public String getExtAttribute(String key) {
//			return header.getExtAttribute(key);
        // add by mazhenmin
//		int i = 0;
//		i = header.getProcessOrder();
//		String process = Integer.toString(i);
        String process = header.getCurrentProcessKey();    //updated by abel 2005-03-03
		return header.getExtAttribute(key, process);
		//add end

	}
	
	//add by mazhenmin 2005-03-04
	public String getExtAttribute(String key,String process){
		return header.getExtAttribute(key,process);
	}

/*	
	public HashMap getAllExtAttribute(String key){
		HashMap resultAttrs = new HashMap();
		Vector extAttrs = header.getExtAttrs();
		String strTemp = null, process;
		if(null == extAttrs)
		    return null;
		for(int i = 1;i<=header.getProcessSize();i++)
		{
			process = Integer.toString(i);
			strTemp = header.getExtAttribute(key,process);
			if(null != strTemp)
			{
				resultAttrs.put(process,strTemp);
			}
		}
		return resultAttrs;
		
	}
*/
    public HashMap getAllExtAttribute(String key){
		HashMap resultAttrs = new HashMap();
		if(key == null) return resultAttrs;
		Vector extAttrs = header.getExtAttrs();
		String strTemp = null, process;
		if(null == extAttrs) return resultAttrs;
		MetaElement me;
		if (key != null){
			for(int i=0;i<extAttrs.size();i++){
				me = (MetaElement)extAttrs.get(i);
				if(key.equals(me.getName())){
					process = me.getAttribute(MSGBASE.MSG_TRAN_PROCESS);
					strTemp = me.getValue();
					if(null != strTemp && null != process)	{
						resultAttrs.put(process,strTemp);
					}
				}			
			}
		}
		return resultAttrs;
    }    	
	//add end

	//get request parameters
	public MetaElement getRequestElement(String key) {

		return request.getElement(key);
	}

	public String getRequestParameter(String key) {
		return request.getValue(key);
	}

	public ArrayList getRequestArrayParameter(String key) {
		return request.getArrayValues(key);
	}

	public ArrayList getRequestGroupArray(String key) {
		return request.getGroupArrayValues(key);
	}

	//get response parameters
	public MetaElement getResponseElement(String key) {

		if(response == null)
		   getCurrentResponse();
		return response.getElement(key);
	}

	public String getResponseParameter(String key) {
		if(response == null)
		   getCurrentResponse();
		return response.getValue(key);
	}

	//-------------------------------------
	public ArrayList getResponseArrayParameter(String key) {
		if(response == null)
		   getCurrentResponse();
		return response.getArrayValues(key);
	}

	public ArrayList getResponseArrayElements(String key) {
		if(response == null)
		   getCurrentResponse();
		return response.getArrayElements(key);
	}

	public ArrayList getResponseGroupArray(String key) {
		if(response == null)
		   getCurrentResponse();
		return response.getGroupArrayValues(key);
	}

	private GroupRecord getResponseGroupRecord(int orderid) {
		if(response == null)
		   getCurrentResponse();
		return response.getGroupRecord(orderid);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("MsgObject{\n");
		sb.append("******header:******\n");
		if(header != null) sb.append(header.toString());
		sb.append("******request:******\n");
		if(request!=null) sb.append(request.toString());
		sb.append("******response******\n");
		if(response!=null) sb.append(response.toString());
		if(null!=hmResp){
			Set s =  hmResp.keySet();
			Iterator i = s.iterator();
			while(i.hasNext()){
				String key = (String)i.next();
				MsgBody msgbody = (MsgBody)hmResp.get(key);
				sb.append(msgbody.toString());
			}
		}
		sb.append("}\n");
		return sb.toString();
		
	}
	public static void main(String[] args) {
		//EAIXMLMessageParser parser = new EAIXMLMessageParser();
		try{
		//MsgObject mo = parser.process2MOByFile("c:\\new.xml");

		MsgBody mb = new MsgBody();

		mb.setValue("key", "abel");
		mb.setValue("key1", "abel1 1");
		mb.setValue("key1", "abel1 2");
		mb.setArrayValue("key2", "sunkey1");
		mb.setArrayValue("key2", "sunkey2");
		ArrayList al = new ArrayList();
		al.add("xuqiang1");
		al.add("xuqiang2");
		mb.setArrayValues("key3", al);
		GroupRecord gr = new GroupRecord();
		gr.setValue("name", "tianbing");
		gr.setValue("value", "male");
		mb.setGroupValues("key4", gr);
		
		//mo.request = mb;
		System.out.println(mb);
	
        //System.out.println(mo.getExtAttribute("INM_CHANNEL_ID"));
		}catch(Exception e){
			
		}
	}

}//end of class
