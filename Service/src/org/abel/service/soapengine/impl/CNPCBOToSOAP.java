/*
 * Created on 2005-4-30
 */
package org.abel.service.soapengine.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Message;
import javax.wsdl.Part;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.abel.service.base.message.*;
import org.abel.service.soapengine.core.BOToSOAP;
import org.abel.service.soapengine.core.SOAConstant;
import org.abel.service.soapengine.core.SOAHelper;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



/**
 */
public class CNPCBOToSOAP implements BOToSOAP {
	//Prefix of Array in SOAP
	private static String PREFIX_ARRAY = "ArrayOf";

	public SOAPMessage genRespMsg(
		Object respBO,
		List lstTypes,
		Message respMsgDef,
		String requesterID)
		throws SOAPException {
		SOAPMessage respMessage = null;
		MsgObject respMO = (MsgObject) respBO;
		try {
			MessageFactory msgFactory = MessageFactory.newInstance();
			respMessage = msgFactory.createMessage();
			SOAPEnvelope respEnvelope = respMessage.getSOAPPart().getEnvelope();
			SOAPBody respBody = respEnvelope.getBody();
			Name bodyName =
				respEnvelope.createName(
					"performServiceResponse",
					"tns",
					SOAConstant.CCB_EAI_TARGET_NS_PREFIX
						+ respMO.getTransactionID());
			SOAPBodyElement bodyElm = respBody.addBodyElement(bodyName);
			//6.1 Set basic information
			SOAHelper.createElement(
				respEnvelope,
				bodyElm,
				SOAConstant.WSDL_TRANSACTION_ID,
				respMO.getTransactionID());
			SOAHelper.createElement(
				respEnvelope,
				bodyElm,
				SOAConstant.WSDL_REQUESTER_ID,
				requesterID);
			SOAHelper.createElement(
				respEnvelope,
				bodyElm,
				SOAConstant.WSDL_TRANSACTION_SN,
				respMO.getTransactionID());
			//6.2 Set response extra attributes
			Element extType =
				SOAHelper.getType(lstTypes, SOAConstant.WSDL_RESP_EXTENSION);
			NodeList extList = extType.getElementsByTagName("*");
			SOAPElement extElm =
				SOAHelper.createElement(
					respEnvelope,
					bodyElm,
					SOAConstant.WSDL_RESP_EXTENSION,
					null);
			for (int i = 0; i < extList.getLength(); i++) {
				Element extNode = (Element) extList.item(i);
				String name = extNode.getAttribute("name");
				String value = respMO.getExtAttribute(name);
				if (value != null) {
					SOAHelper.createElement(respEnvelope, extElm, name, value);
				}
			}
			//6.3 Set response status
			Element sttType =
				SOAHelper.getType(lstTypes, SOAConstant.WSDL_RESP_STATUS);
			NodeList sttList = sttType.getElementsByTagName("*");
			SOAPElement sttElm =
				SOAHelper.createElement(
					respEnvelope,
					bodyElm,
					SOAConstant.WSDL_RESP_STATUS,
					null);
			SOAHelper.createElement(
				respEnvelope,
				sttElm,
				"code",
				respMO.getResponseCode());
			SOAHelper.createElement(
				respEnvelope,
				sttElm,
				"status",
				respMO.getResponseStatus());
			SOAHelper.createElement(
				respEnvelope,
				sttElm,
				"desc",
				respMO.getResponseDesc());

			//6.4 Set other response result
			Map mapParts = respMsgDef.getParts();
			Iterator parts = mapParts.values().iterator();
			while (parts.hasNext()) {
				Part respField = (Part) parts.next();
				String name = respField.getName();
				QName type = respField.getTypeName();
				if ((!name.equalsIgnoreCase(SOAConstant.WSDL_REQUESTER_ID))
					&& (!name.equalsIgnoreCase(SOAConstant.WSDL_TRANSACTION_SN))
					&& (!name.equalsIgnoreCase(SOAConstant.WSDL_RESP_EXTENSION))
					&& (!name
						.equalsIgnoreCase(SOAConstant.WSDL_TRANSACTION_ID))) {
					// Not the fields process before
					if (type
						.getNamespaceURI()
						.equalsIgnoreCase(SOAConstant.CCB_EAI_XSD_NS)) {
						//Simple Type
						String value = respMO.getResponseParameter(name);
						if (value != null) {
							SOAHelper.createElement(
								respEnvelope,
								bodyElm,
								name,
								value);
						}
					} else {
						//Complex Type
						//Check if it's a complex array type
						String typeName = "";
						boolean isArray = false;
						if (name.startsWith(PREFIX_ARRAY)) {
							typeName = name.substring(PREFIX_ARRAY.length());
							isArray = true;
						} else {
							typeName = name;
						}
						Element complexType =
							SOAHelper.getType(lstTypes, typeName);
						if (complexType != null) {
							NodeList subTypes =
								complexType.getElementsByTagName("*");
							ArrayList alGroup =
								respMO.getResponseArrayParameter(name);
							Iterator itGroup = null;
							if (alGroup != null) {
								itGroup = alGroup.iterator();
							} else {
								continue;
							}
							if (itGroup != null) {
								SOAPElement cmplxElm =
									SOAHelper.createElement(
										respEnvelope,
										bodyElm,
										typeName,
										null);
								while (itGroup.hasNext()) {
									GroupRecord groupRecord =
										(GroupRecord) itGroup.next();
									SOAPElement itemElm = null;
									if (isArray) {
										itemElm =
											SOAHelper.createElement(
												respEnvelope,
												cmplxElm,
												"item",
												null);
									} else {
										itemElm = cmplxElm;
									}

									for (int i = 0;
										i < subTypes.getLength();
										i++) {
										Element subType =
											(Element) subTypes.item(i);
										String subName =
											subType.getAttribute("name");
										String value =
											groupRecord.getValue(subName);
										if (value != null) {
											SOAHelper.createElement(
												respEnvelope,
												cmplxElm,
												subName,
												value);
										}
									}
								}
							}
						} else {
							ArrayList alValue =
								//respMO.getResponseArrayParameters(name);
	respMO.getResponseArrayElements(name);
							System.out.println("|||name" + name);
							Iterator itValue = null;
							if (alValue != null) {
								itValue = alValue.iterator();
							} else {
								continue;
							}
							if (itValue != null) {
								SOAPElement cmplxElm =
									SOAHelper.createElement(
										respEnvelope,
										bodyElm,
										typeName,
										null);
								while (itValue.hasNext()) {
									String value = (String) itValue.next();
									SOAHelper.createElement(
										respEnvelope,
										cmplxElm,
										"item",
										value);
								}
							}
						}

					}
				}
			}
			return respMessage;
		} catch (Exception e) {
			//Log.getInstance().error(e);
			e.printStackTrace();
			throw new SOAPException("Compose web service response error.", e);
		}
	}
	
	
	public static void main(String[] a) throws Exception {
	
		try{

		//EAIXMLMessageParser pser = new EAIXMLMessageParser();
		MsgObject mo = new MsgObject();
//request		
		mo.setRequestParameter("Well_ID","10000");
		mo.setRequestParameter("OperationTeam_ID","00 \b /b04");	
		mo.setRequestParameter("Create_Date","20060603");		
		mo.setRequestParameter("Tour_ID","1234");		

		//String teststr = pser.Format2XML(mo);
		//System.out.println(teststr);
						
		}catch(Throwable e){
		
		e.printStackTrace();
		
		}			
	
	
	
	}

}
