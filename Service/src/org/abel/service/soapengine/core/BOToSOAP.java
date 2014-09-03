/*
 * Created on 2005-4-30
 */
package org.abel.service.soapengine.core;

import java.util.List;

import javax.wsdl.Message;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Interface for the transfer from EAI MO to SOAP envelope
 */
public interface BOToSOAP {
	public SOAPMessage genRespMsg(
		Object respBO,
		List lstTypes,
		Message respMsgDef,
		String requesterID)
		throws SOAPException;

}
