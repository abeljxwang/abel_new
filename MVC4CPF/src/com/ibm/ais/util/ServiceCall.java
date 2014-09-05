package com.ibm.ais.util;

import java.util.Iterator;

import javax.xml.soap.*;

import org.w3c.dom.NodeList;

public class ServiceCall {

	public static void main(String[] a) throws Exception {
		
		
		SOAPConnection conn;
		SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
		conn = scf.createConnection();
		//conn.
		//conn.
		
		MessageFactory mf = MessageFactory.newInstance();
		//mf.
		SOAPMessage msg = mf.createMessage();
		msg.setProperty("SOAPAction", "Well_Mgr/query_Well_Info");
		
		//msg.
		
		SOAPPart sp = msg.getSOAPPart() ;
		SOAPEnvelope envelope = sp.getEnvelope();
		envelope.addNamespaceDeclaration("q0", "http://www.cnpc.org/Well_Mgr/");
		//SOAPHeader hdr = envelope.getHeader();
		SOAPBody bdy = envelope.getBody();

		//hdr.addHeaderElement( envelope.createName("OnlineBooks", "ob","http://www.bookprovider.com" )).addTextNode(
        //                       "Online Book Orders");

		Name bodyName = envelope.createName("query_Well_Info","q0","http://www.cnpc.org/Well_Mgr/");
		SOAPBodyElement bodyElm = bdy.addBodyElement(bodyName);
		SOAPFactory sf=SOAPFactory.newInstance();
		
		SOAPElement cele =sf.createElement("well_id");
		cele.addTextNode("LH1234");
		bodyElm.addChildElement(cele);
		
		cele =sf.createElement("location");
		cele.addTextNode("BJ");
		bodyElm.addChildElement(cele);
		// bodyName.

		// respMessage.
		msg.saveChanges();
		msg.writeTo(System.out);
		java.net.URL urlendpoint = new java.net.URL("http://localhost:8080/Service/SOAPEngineTM/Well_Mgr");
		//conn.call(msg, urlendpoint);
		//conn.close();
		//msg.setContentDescription("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		//urlendpoint.
		
		SOAPMessage msgout= conn.call(msg, urlendpoint);
		conn.close();
		
		SOAPBody response=msgout.getSOAPBody();
		
		msgout.writeTo(System.out);
//		response.getElementsByTagNameNS("q0", "query_Well_InfoResponse");
//		
/*		NodeList bbb= response.getElementsByTagName("q0:query_Well_InfoResponse");
		for(int i=0;i<bbb.getLength();i++){
			org.w3c.dom.Node n=bbb.item(i);
			//System.out.println("\n"+aaab.getElementName().getLocalName()+"   a");
			System.out.println("\n"+n.getNodeName()+" -------------");
			
			
		}*/
	    Iterator aaa=response.getChildElements();
	    SOAPElement aaab=null;
		while(aaa.hasNext()){
			aaab=(SOAPElement)aaa.next();
			System.out.println("\n"+aaab.getElementName().getLocalName()+"   a");
			//System.out.println(aaab.getElementQName()+"   b");
			System.out.println(aaab.getNodeType()+"   c");
		}
		
		
		//System.out.println(response.getLocalName());
		//ServiceMessage sm=new ServiceMessage(aaab);
		// System.out.println(sm.getValue("current_Date"));
		
//		SOAPBody response=msgout.getSOAPBody();
//		SOAPElement se, se2;
//		Iterator it= response.getChildElements();
//		while(it.hasNext()){
//			se=(SOAPElement)it.next();
//			Iterator it2=se.getChildElements();
//			
//			while(it2.hasNext()){
//				se2=(SOAPElement)it2.next();
//				System.out.println("\r"+se2.getNodeName()+"  :  "+se2.getFirstChild().getTextContent());
//				se2=null;
//			}
//			
//			
//		}
		
		//System.out.print(response.getNodeName());


		
	}
}
