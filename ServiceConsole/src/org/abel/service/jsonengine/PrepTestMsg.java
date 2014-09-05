package org.abel.service.jsonengine;

import java.util.ArrayList;
import java.util.Vector;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;

import org.abel.service.soa.message.DescCacheManager;
import org.abel.service.soa.message.ElementDesc;
import org.abel.service.soa.message.MetaDesc;
import org.abel.service.soa.message.OperationDesc;
import org.abel.service.soa.message.ServiceDesc;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;




public class PrepTestMsg {

	
	public static JSONObject prepare_Test_jsonmsg(String servicename,String operationstr) {
		JSONObject respMessage=new JSONObject();
		
		DescCacheManager dm=DescCacheManager.getInstance();

		//Add Testring response
		//System.out.print("-------------------");
		String out_put_name=null;
		if(servicename!=null||servicename.length()>1){
    		//operationstr="test";
			 ServiceDesc sdsc=dm.getServiceDesc(servicename);
			 Vector op_keys=sdsc.getAllOperationKeys();
			 String key_tmp=null;
			 if(op_keys.size()>0) key_tmp=op_keys.get(0)+"";
			 if(operationstr==null||operationstr.length()<=1) operationstr=key_tmp;

			 OperationDesc op_dsc= sdsc.getOperation(operationstr);
			 out_put_name=op_dsc.getOutputName();
			 
			 //System.out.println("the element id of response element in the body:"+out_put_name);
		
			 
			 ElementDesc edsc=dm.getMessageDesc(out_put_name);
			 addSubElement(respMessage,edsc);
				    		
		}
		
		return respMessage;

	}
	
	public static JSONObject prepare_Request_jsonmsg(String servicename,String operationstr) {
		JSONObject respMessage=new JSONObject();
		
		DescCacheManager dm=DescCacheManager.getInstance();

		//Add Testring response
		//System.out.print("-------------------");
		String in_put_name=null;
		if(servicename!=null||servicename.length()>1){
    		//operationstr="test";
			 ServiceDesc sdsc=dm.getServiceDesc(servicename);
			 Vector op_keys=sdsc.getAllOperationKeys();
			 String key_tmp=null;
			 if(op_keys.size()>0) key_tmp=op_keys.get(0)+"";
			 if(operationstr==null||operationstr.length()<=1) operationstr=key_tmp;

			 OperationDesc op_dsc= sdsc.getOperation(operationstr);
			 in_put_name=op_dsc.getInputName();
			 
			 //System.out.println("the element id of response element in the body:"+in_put_name);
		
			 
			 ElementDesc edsc=dm.getMessageDesc(in_put_name);
			 addSubElement(respMessage,edsc);
				    		
		}
		
		
		
	
		
		
//		Add Testring response
		
		//respMessage.saveChanges();
		
		return respMessage;

	}
	
	
	
	
	
	private static void addSubElement(JSONObject sup_ele,ElementDesc ed)  {
		DescCacheManager dm=DescCacheManager.getInstance();
		
		for(int i=0;i<ed.getMetaAcount();i++){
			 MetaDesc mdsctmp=ed.getMetaDesc(i);
			 //sup_ele.put(mdsctmp.getName(),mdsctmp.getDF_Value());
			 int rpt=1;
			 if(mdsctmp!=null) rpt=mdsctmp.getRpt();
			 
			 JSONArray list_e = null;
			 			 
			 if(mdsctmp.getType().equals("E"))
			 {
				 JSONObject  cele =new JSONObject();;
				 
				 ElementDesc tmpme=dm.getMessageDesc(mdsctmp.getName());
				 addSubElement(cele,tmpme);
				 if(rpt>1){
					 list_e = new JSONArray();
					 for(int ai=0;ai<rpt&&ai<3;ai++){
						 list_e.add(cele);
						 if(cele!=null) sup_ele.put(tmpme.getName(), list_e);
					 }
					 
					
				 }
				 else{
					 if(cele!=null) sup_ele.put(tmpme.getName(), cele);
					 }
				 
				 
			 }
			 else{
				 //cele=new JSONObject();
				 
				 if(mdsctmp.getDF_Value()!=null)
					 {
					 if(mdsctmp.getRpt()>1){
						 JSONArray listofj=new JSONArray();
						 
						 for(int k=0;k<mdsctmp.getRpt();k++) listofj.add(mdsctmp.getDF_Value()+""+k);
							 sup_ele.put(mdsctmp.getName(),listofj);
						 
						 
					 }
					 else 
					 sup_ele.put(mdsctmp.getName(),mdsctmp.getDF_Value());
					 }
			 }
			
			 //sup_ele.put(ed.getName(), cele);
			 
			 
		 }

	}		
	
	public static void main(String[] a) throws Exception {
		
		System.out.println(PrepTestMsg.prepare_Request_jsonmsg("CustomerSummary", "query").toJSONString());
	}
}