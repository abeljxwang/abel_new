package org.abel.service.soapengine.core;

import java.util.Vector;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;


public class ServiceMessage  {
	SOAPFactory sf=SOAPFactory.newInstance();
	SOAPElement element;
	SOAPElement hd_element;
	
	//Constructor a new message
	public ServiceMessage(String msg_name) throws Exception{
		//Name bodyName = sf.createName(msg_name,"q0","http://www.cnpc.org/CNPC_Well_Mgr/");
		//SOAPBodyElement bodyElm = bdy.addBodyElement(bodyName);
		element=sf.createElement(msg_name);
	}
	
	//	Constructor a message from a response
	public ServiceMessage(SOAPElement element1) throws Exception{

		element=element1;
	}

	//	Set a k/v in root
	public void set(String key, String value) throws SOAPException{
		setREleKV(element,key,value);
	}
	
	//	Set a k/v in root	
	private void setREleKV(SOAPElement element1, String key, String value) throws SOAPException{
		
		SOAPElement element_tmp2 =sf.createElement(key);
		element_tmp2.addTextNode(value);

		element.addChildElement(element_tmp2);
		
	}

	//	Set a child k/vt
	public void setChildEleValue(String up_element1, String key, String value) throws SOAPException{
		SOAPElement element_tmp;
		SOAPElement element_tmp2;
	
		if(element.getElementsByTagName(up_element1)!=null)
			element_tmp =(SOAPElement)element.getElementsByTagName(up_element1).item(0);
		else
			element_tmp=sf.createElement(up_element1);
		
		element_tmp2=sf.createElement(key);
		element_tmp2.addTextNode(value);
		element_tmp.addChildElement(element_tmp2);
		//element_tmp.addTextNode(value);
		
	}
	
	
	//	get value from root	
	public String getValue(String key) throws SOAPException{

		String value = SOAHelper.getElmValue(element, key);
		return value;
	}
	
	//	get values from root	
	public Vector getValues(String key) throws SOAPException{

		Vector value = SOAHelper.getElmArrayValue(element, key);
		return value;
	}
	
	//	get element	
	public SOAPElement getElement(){
		return element;
	}
	
	
	//put a message elenent into another message
	public void setChildEle(ServiceMessage  element1,ServiceMessage  ele_Child) throws SOAPException{
		//element.getElementsByTagName(arg0)
		
		element1.getElement().addChildElement(ele_Child.getElement());
	}
	

	
	public SOAPElement createElement(String key) throws SOAPException{
		SOAPElement elementtmp=sf.createElement(key);
		return elementtmp;
	}
	

	
	
	
	public SOAPElement createMsg() throws Exception {
	
	//SOAPFactory sf=SOAPFactory.newInstance();
	//sf.	
	SOAPElement cele =sf.createElement("well_id");
	cele.addTextNode("2006-07-01");
	//bodyElm.addChildElement(cele);
	return cele;
}
	
	
	public static void main(String[] t) throws Exception{
		
		ServiceMessage sm=new ServiceMessage("check_ATP");
		//sm.set("location", "BeiJing");
		
		ServiceMessage sm2=new ServiceMessage("I_PLANT");
		sm2.set("WERKS", "南京——徐庄");
		sm2.set("LGORT", "南京");
		sm2.set("LIFNR", "02512345678");
		sm2.set("MATNR", "PRO001");
		sm2.set("VKORG", "B2C");
		//sm2.set("well_name", "CH7896");


		sm.setChildEle(sm, sm2);
		
		//sm.setChildEleValue("well_info","location", "BeiJing");
		
		//ServiceMessage sm3=new ServiceMessage("well_info666");
		//sm3.set("well_id", "1234");
		//sm3.set("well_name", "CH7896");		
		//sm.setChildEle(sm2, sm3);
		//sm.setChildEle(sm.element,sm.element);
		
		
		//-------------
		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage msg = mf.createMessage();
		
		
		
		SOAPPart sp = msg.getSOAPPart() ;
		SOAPEnvelope envelope = sp.getEnvelope();
		
		envelope.addNamespaceDeclaration("q0", "http://www.suning.com/SIMS/");
		//SOAPHeader hdr = envelope.getHeader();
		SOAPBody bdy = envelope.getBody();

		//envelope.g
		bdy.addChildElement(sm.element);
		msg.saveChanges();
		
		
		
		msg.writeTo(System.out);
		
		
		//System.out.println("--"+sm.getValue("location"));
	}

}
