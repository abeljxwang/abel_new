/*
 * Created on 2005-4-14
 * 
 */
package org.abel.service.soapengine.core;


import java.util.Vector;

import javax.servlet.ServletException;


import javax.xml.soap.SOAPBody;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.abel.service.base.common.ParameterParser;
import org.abel.service.soapengine.core.tst.PrepTestMsg;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




/**
 * 1 得到SOAP消息,解析消息 
 * 2 生成MB需要的XML格式报文（使用FrontAdapter） 
 * 3 发出请求报文，并且得到响应报文（需要维护connection pool,利用MQ本身的功能） 
 * 4 解析响应报文，并且根据对应的WSDL，生成WEB
 * SERVICE的响应报文
 */
public class SOAPEngineTM extends SOAPEngineBaseNew {


	//WSDLGenerator_OLD wsdlGenerator = new WSDLGenerator_OLD();

	public SOAPEngineTM() throws ServletException {
		try {
			// soapToBO = new EAISOAPToBO();
			// processor = new EAIProcessor();
			// boToSOAP = new EAIBOToSOAP();
		} catch (Exception e) {
			// soaplog.error(e);
			throw new ServletException("SOAPEngine Initialize error.", e);
		}
	}

	protected SOAPMessage onMessage(SOAPMessage elmReq, String servicename,
			String operationstr) throws SOAPException {

		System.out.println("Service Name: " + servicename);
		// 1.1 Get SOAP Message Body and validate the request

		SOAPBody body = elmReq.getSOAPBody();
		// body.g
		// body.

		NodeList service_nodelist = body.getChildNodes();

		// //1.2 get service/operation element, for diffrent service, the name
		// is diffrent

		Node service_node = null;
		String opname = null;
		for (int i = 0; i < service_nodelist.getLength(); i++) {

			service_node = service_nodelist.item(i);
			System.out.println(service_node.getNodeType());
			System.out.println(service_node.getNodeName() + "");
			if (service_node.getNodeType() == 1) {
				
				// sreqmsg=new ServiceMessage(service_node.);
				opname = service_node.getNodeName();
				System.out.println("Service Operation Name: " + opname);
				//
				if (opname.indexOf(":") > 0) {
					Vector vv = ParameterParser.pasingStr(opname, ":");
					opname = vv.elementAt(1) + "";
					System.out.println(vv + " : " + opname);
					break;
				}

			}
		}

		// String opname = service_node.getNodeName();
		System.out.println("Service Operation Name: " + opname);


		// prepare the response
		SOAPMessage respMessage = prepare_Test_soapmsg(servicename,opname);
		//System.out.println(" :aaa"+respMessage.toString()+" :aaa");
		//SOAPMessage respMessage = sm.;

		return respMessage;
	}

	private SOAPMessage prepare_Test_soapmsg(String servicename,String operationstr) throws SOAPException {
		SOAPMessage rsp=PrepTestMsg.prepare_Test_soapmsg(servicename, operationstr);
		return rsp;

	}





}
