/*
 * Created on 2005-4-14
 * 
 */
package org.abel.service.jsonengine;


import java.util.Vector;

import javax.servlet.ServletException;


import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.abel.service.base.common.ParameterParser;
import org.abel.service.base.common.StringEncoder;
import org.json.simple.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




/**
 * 1 µÃµ½SOAPÏûÏ¢,½âÎöÏûÏ¢ 
 * 2 Éú³ÉMBÐèÒªµÄXML¸ñÊ½±¨ÎÄ£¨Ê¹ÓÃFrontAdapter£© 
 * 3 ·¢³öÇëÇó±¨ÎÄ£¬²¢ÇÒµÃµ½ÏìÓ¦±¨ÎÄ£¨ÐèÒªÎ¬»¤connection pool,ÀûÓÃMQ±¾ÉíµÄ¹¦ÄÜ£© 
 * 4 ½âÎöÏìÓ¦±¨ÎÄ£¬²¢ÇÒ¸ù¾Ý¶ÔÓ¦µÄWSDL£¬Éú³ÉWEB
 * SERVICEµÄÏìÓ¦±¨ÎÄ
 */
public class JSONEngineTM extends REST4JSONEngineBase {


	protected JSONObject onMessage(JSONObject elmReq, String servicename,
			String operationstr) throws SOAPException {

		logger.info("Service Name: " + servicename +"  | operation: "+ operationstr);
		String opname = null;
		// 1.1 Get SOAP Message Body and validate the request

		// prepare the response
		opname=operationstr;
		JSONObject respMessage = prepare_Test_jsonmsg(servicename,opname);

		return respMessage;
	}

	private JSONObject prepare_Test_jsonmsg(String servicename,String operationstr) throws SOAPException {
		JSONObject rsp=PrepTestMsg.prepare_Test_jsonmsg(servicename, operationstr);
		return rsp;

	}
	
	private JSONObject prepare_Request_jsonmsg(String servicename,String operationstr) throws SOAPException {
		JSONObject rsp=PrepTestMsg.prepare_Test_jsonmsg(servicename, operationstr);
		return rsp;

	}




}
