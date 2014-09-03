/*
 * 创建日期 2005-5-26
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.service.soa.test;
import java.io.OutputStream;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
//import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;



public class SOAPTestMsg {

	
	public String process(OutputStream out) throws Exception {
		//String tickerSymbol = "aaa";
		//SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
		//MessageFactory mf = MessageFactoryImpl.newInstance();

		//MessageFactory mf = con.createMessageFactory(schemaURL);

		MessageFactory mf = MessageFactory.newInstance();
		

//		SOAPMessage msg = mf.createMessage();
//		//----------------------------
//		SOAPPart sp = msg.getSOAPPart();
//		SOAPEnvelope env = sp.getEnvelope();
//		env.addNamespaceDeclaration(
//			"xsd",
//			"http://www.w3.org/2001/XMLSchema");
//		env.addNamespaceDeclaration(
//			"xsi",
//			"http://www.w3.org/2001/XMLSchema-instance");
//		env.addNamespaceDeclaration(
//			"enc",
//			"http://schemas.xmlsoap.org/soap/encoding/");
//		env.addNamespaceDeclaration(
//			"env",
//			"http://schemas.xmlsoap.org/soap/envelop/");
//		env.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");
//
//		SOAPBody bd = env.getBody();
//
//		// Populate body
//		// Main element and namespace
//		SOAPElement be =
//			bd.addChildElement(
//				env.createName(
//					"performService",
//					"tns",
//					"http://localhost:9081/wsdl/100000"));
//		// namespace to use for my rpc/encoded wsdl version is:
//		// http://phonedirlux.homeip.net/wsdl
//		// note, in this case the endpoint address is /rcx-ws-rpc/rcx
//
//		// Add content
//		be
//			.addChildElement("EMAIL")
//			.addTextNode("your message or e-mail")
//			.setAttribute("xsi:type", "xsd:string");
//
//		// Save message
//		msg.saveChanges();
//
//		msg.writeTo(out);

		
		
		//-----------------------------------
		
		
//		String tenpstr=null;
//		//StringBufferInputStream temp = new StringBufferInputStream(tenpstr);
//		MimeHeaders mimih = new MimeHeaders();
//					mimih.setHeader("Content-Type", "text/xml");
//		
		SOAPMessage message = mf.createMessage();;

		SOAPPart soapPart = message.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();

		//SOAPHeader header = envelope.getHeader();
		//header.addChildElement("Abel","test");

		SOAPBody body = envelope.getBody();

		//header.detachNode();

		Name bodyName =
			envelope.createName(
				"processService",
				"tns",
				"http://hosts/ccb/eai/soa/1010101");
		SOAPBodyElement gltp = body.addBodyElement(bodyName);
		
		
		createElement(envelope,gltp,"transaction_sn","10234992187401218");
		createElement(envelope,gltp,"transaction_id","00001010231");
		createElement(envelope,gltp,"requester_id","0101");
		createElement(envelope,gltp,"ACCT_NO","10234992187401218");
		createElement(envelope,gltp,"ACCT_SER","1");
		createElement(envelope,gltp,"STRT_DT","0");
		
		message.saveChanges();
						
		//System.out.println(message.toString());
		message.writeTo(out);

		return null;
	}

	public void createElement(
		SOAPEnvelope envelope,
		SOAPBodyElement gltp,
		String key,
		String value)
		throws SOAPException {

		SOAPElement symbol = gltp.addChildElement(key);
		symbol.addTextNode(value);


	}
	
	public static void main(String arg[]) throws Exception{
	
		SOAPTestMsg st = new SOAPTestMsg();
		st.process(System.out);
	
	}
}
