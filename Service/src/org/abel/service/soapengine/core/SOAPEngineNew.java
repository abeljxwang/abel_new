/*
 * Created on 2005-4-14
 * 
 */
package org.abel.service.soapengine.core;


import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletException;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import javax.xml.soap.MessageFactory;


import org.abel.service.base.common.ParameterParser;
import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.config.ServiceCfg;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 1 得到SOAP消息,解析消息 
 * 2 生成MB需要的XML格式报文（使用FrontAdapter） 
 * 3 发出请求报文，并且得到响应报文（需要维护connection pool,利用MQ本身的功能） 
 * 4 解析响应报文，并且根据对应的WSDL，生成WEB SERVICE的响应报文
 */

public class SOAPEngineNew extends SOAPEngineBaseNew {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public SOAPEngineNew() throws ServletException {
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
		SOAPMessage respMessage =null;

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

		String servmodelname = null, servmodelclass = null;
		ServiceCfg scfg = soacfg.getFucntion(servicename);
		if (scfg == null) {
			System.out.println("no such service");
			return null;
		}

		OperationCfg opcfg = scfg.getAcction(opname);
		if (opcfg != null)
			servmodelname = opcfg.getModelInstance();
		else {
			System.out.println("=========> No such operation:" + opname
					+ " under service:" + servicename);
		}
		System.out.println(servmodelname);

		// //Pase the request msg from body/Operation name
		//
		NodeList elementlist = service_node.getChildNodes();
		//
		System.out.println("Element size: " + elementlist.getLength());

		//
		 Hashtable models = soacfg.getModel();
		// //String instancename = actcfg.getModelInstance();
		 if (servmodelname != null && servmodelname.length() > 0) {
			 Hashtable model0 = (Hashtable) models.get(servmodelname);
		 servmodelclass = (String) model0.get("Type");
		 }
		 System.out.println("CLASS NAME "+servmodelclass);
		//
		// servmodelclass = "com.ibm.ais.soamodel.DBQuery";
		//
		
		//Call backend model
		 //SOAPMessage respMessage = null;
		
		
		 try {
		 ServiceMessage smoin=new ServiceMessage(body);
		 Object objtmp = ObjectFoundy.factory(servmodelclass);
		 Class clstmp = (objtmp.getClass()).getSuperclass();
		 String cname = clstmp.getName();
		//
		 if (cname != null) {
		 SOAModelBase imb = null;
		
		 imb = (SOAModelBase) objtmp;
		 ServiceMessage sm=imb.execute(opcfg,smoin);
		 //System.out.println(sm.getValue(""));
		 //respMessage=new SOAPMessage(sm);
		 //respMessage=sm.getElement();
		 //prepare the SOAP Message
		 
		 System.out.println("Such operation confifration is: "+ opcfg );
		 
		 MessageFactory mf = MessageFactory.newInstance();
		 respMessage = mf.createMessage();
			
		 SOAPPart sp = respMessage.getSOAPPart() ;
		 SOAPEnvelope envelope = sp.getEnvelope();
			
		 envelope.addNamespaceDeclaration("q0", "http://ais_gbs.ibm/Service/wsdl/" );
			//SOAPHeader hdr = envelope.getHeader();
		SOAPBody bdy = envelope.getBody();

			//envelope.g
		bdy.addChildElement(sm.element);
		respMessage.saveChanges();
		 
		 
		 }
		
		 } catch (Exception e) {
		 e.printStackTrace();
		 }

		// prepare the response
			//prepare_Test_soapmsg();

		return respMessage;
	}




	public SOAPElement createElement(SOAPEnvelope envelope, SOAPElement gltp,
			String key, String value) throws SOAPException {
		Name name = envelope.createName(key);

		SOAPElement symbol = gltp.addChildElement(name);
		if (value != null) {
			symbol.addTextNode(value);
		}
		return symbol;

	}

}
