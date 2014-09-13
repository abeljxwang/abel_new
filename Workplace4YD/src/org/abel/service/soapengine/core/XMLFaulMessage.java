
package org.abel.service.soapengine.core;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.soap.*;



public class XMLFaulMessage {

	public static void  createFaltMsg(OutputStream out, Exception e,MessageFactory mf) throws Exception {
		SOAPMessage message = mf.createMessage();
		SOAPPart soapPart = message.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		SOAPHeader header = envelope.getHeader();
		SOAPBody body = envelope.getBody();
		body.addFault();
		SOAPFault sfault = body.getFault();
		sfault.setFaultString(e.getMessage());
		sfault.setFaultActor("SOAPEngine");
		sfault.setFaultCode("Server");
		sfault.addDetail().addTextNode(e.toString());
		message.saveChanges();
		message.writeTo(out);
	}

	public static void main(String[] aa) throws Exception{
		MessageFactory msgFactory = MessageFactory.newInstance();
		createFaltMsg(System.out,new Exception("wr"),msgFactory);
		
	}
}
