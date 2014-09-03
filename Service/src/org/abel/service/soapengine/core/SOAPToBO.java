/*
 * Created on 2005-4-30
 */
package org.abel.service.soapengine.core;

import java.util.List;

import javax.wsdl.Message;
import javax.xml.soap.SOAPException;

import org.w3c.dom.Element;

/**
 */
public interface SOAPToBO {
	public Object genRequestBO(
		Element service,
		Message reqMsgDef,
		List lstTypes,
		String requesterID,
		String transID)
		throws SOAPException;

}

