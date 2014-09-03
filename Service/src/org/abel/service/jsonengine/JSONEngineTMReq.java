/*
 * Created on 2005-4-14
 * 
 */
package org.abel.service.jsonengine;




import javax.servlet.ServletException;



import javax.xml.soap.SOAPException;

import org.json.simple.JSONObject;





/**
 * 1 µÃµ½SOAPÏûÏ¢,½âÎöÏûÏ¢ 
 * 2 Éú³ÉMBÐèÒªµÄXML¸ñÊ½±¨ÎÄ£¨Ê¹ÓÃFrontAdapter£© 
 * 3 ·¢³öÇëÇó±¨ÎÄ£¬²¢ÇÒµÃµ½ÏìÓ¦±¨ÎÄ£¨ÐèÒªÎ¬»¤connection pool,ÀûÓÃMQ±¾ÉíµÄ¹¦ÄÜ£© 
 * 4 ½âÎöÏìÓ¦±¨ÎÄ£¬²¢ÇÒ¸ù¾Ý¶ÔÓ¦µÄWSDL£¬Éú³ÉWEB
 * SERVICEµÄÏìÓ¦±¨ÎÄ
 */
public class JSONEngineTMReq extends REST4JSONEngineBase {


	//WSDLGenerator_OLD wsdlGenerator = new WSDLGenerator_OLD();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JSONEngineTMReq() throws ServletException {
		try {
			// soapToBO = new EAISOAPToBO();
			// processor = new EAIProcessor();
			// boToSOAP = new EAIBOToSOAP();
		} catch (Exception e) {
			// soaplog.error(e);
			throw new ServletException("SOAPEngine Initialize error.", e);
		}
	}

	protected JSONObject onMessage(JSONObject elmReq, String servicename,
			String operationstr) throws SOAPException {

		System.out.println("Service Name: " + servicename +"  | operation: "+ operationstr);
		String opname = null;
		// 1.1 Get SOAP Message Body and validate the request

		// prepare the response
		opname=operationstr;
		JSONObject respMessage = prepare_Request_jsonmsg(servicename,opname);

		return respMessage;
	}

//	private JSONObject prepare_Test_jsonmsg(String servicename,String operationstr) throws SOAPException {
//		JSONObject rsp=PrepTestMsg.prepare_Test_jsonmsg(servicename, operationstr);
//		return rsp;
//
//	}
	
	private JSONObject prepare_Request_jsonmsg(String servicename,String operationstr) throws SOAPException {
		JSONObject rsp=PrepTestMsg.prepare_Request_jsonmsg(servicename, operationstr);
		return rsp;

	}




}
