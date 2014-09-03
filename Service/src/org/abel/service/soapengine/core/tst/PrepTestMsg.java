package org.abel.service.soapengine.core.tst;

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




public class PrepTestMsg {

	
	public static SOAPMessage prepare_Test_soapmsg(String servicename,String operationstr) throws SOAPException {
		
		DescCacheManager dm=DescCacheManager.getInstance();
		MessageFactory msgFactory = MessageFactory.newInstance();
		SOAPMessage respMessage = msgFactory.createMessage();
		SOAPEnvelope respEnvelope = respMessage.getSOAPPart().getEnvelope();

		//respEnvelope.addNamespaceDeclaration("p0", "http://ais_gbs.ibm/Service/wsdl/");
		// Namespace nsp =new Namespace("q0","1234");
		SOAPBody respBody = respEnvelope.getBody();
		// respBody.setPrefix("q0");
		Name bodyName = respEnvelope.createName(operationstr+"_response",
				"p0", "http://ais_gbs.ibm/Service/wsdl/");
		// respEnvelope.c

		SOAPBodyElement bodyElm = respBody.addBodyElement(bodyName);

		SOAPFactory sf = SOAPFactory.newInstance();

		//Add Testring response
		System.out.print("-------------------");
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
			 
			 System.out.println(out_put_name);
		
			 
			 ElementDesc edsc=dm.getMessageDesc(out_put_name);
			 addSubElement(bodyElm,edsc,sf);
				 

				

			 
    		
		}
		
	
		
		
//		Add Testring response
		
		respMessage.saveChanges();
		
		return respMessage;

	}
	
	private static void addSubElement(SOAPElement sup_ele,ElementDesc ed,SOAPFactory sf) throws SOAPException {
		DescCacheManager dm=DescCacheManager.getInstance();
		for(int i=0;i<ed.getMetaAcount();i++){
			 MetaDesc mdsctmp=ed.getMetaDesc(i);
			 SOAPElement  cele =null;
			 
			 if(mdsctmp.getType().equals("E"))
			 {
				
				 ElementDesc tmpme=dm.getMessageDesc(mdsctmp.getName());
				 cele=sf.createElement(tmpme.getName());
				 addSubElement(cele,tmpme,sf);
				 
			 }
			 else{
				 cele=sf.createElement(mdsctmp.getName());
				 cele.addTextNode(mdsctmp.getDF_Value());
			 }
			
			 sup_ele.addChildElement(cele);
			 
		 }

	}		
	
	public static void main(String[] a) throws Exception {
		
		PrepTestMsg.prepare_Test_soapmsg("UserMgr", "creatUser").writeTo(System.out);;
	}
}