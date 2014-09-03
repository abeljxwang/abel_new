package org.abel.service.jsonengine;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.config.ServiceCfg;
import org.abel.service.soapengine.core.ObjectFoundy;
import org.abel.service.soapengine.core.ServiceMessage;
import org.json.simple.JSONObject;
import org.w3c.dom.Node;

/**
 * Servlet implementation class JSON
 */
public class REST4JSONEngine extends REST4JSONEngineBase {
	private static final long serialVersionUID = 1L;
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public REST4JSONEngine() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JSONObject onMessage(JSONObject reqDoc, String serviceid,
			String operationid) {

		String servmodelname = null, servmodelclass = null;
		JSONObject respMessage = new JSONObject();

		//Date dateobj = new Date();

		try {

			ServiceCfg scfg = soacfg.getFucntion(serviceid);

			if (scfg == null) {
				logger.info("no such service");
				throw new Exception("no such service: [" + serviceid	+ "]");
				//return null;
			}

			OperationCfg opcfg = scfg.getAcction(operationid);
			if (opcfg != null) {
				servmodelname = opcfg.getModelInstance();
				if (servmodelname == null)
					throw new Exception("Service Model:" + servmodelname
							+ " is not ready, Please check the configration");
			} else {
				logger.info("=========> No such operation:" + operationid
						+ " under service:" + serviceid);
				throw new Exception("No such operation:" + operationid
						+ " under service:" + serviceid);
			}

			//
			Hashtable models = soacfg.getModel();
			// //String instancename = actcfg.getModelInstance();
			if (servmodelname != null && servmodelname.length() > 0) {
				Hashtable model0 = (Hashtable) models.get(servmodelname);
				servmodelclass = (String) model0.get("Type");
			}
			if (servmodelclass == null)
				throw new Exception("No class configration for such service");

			logger.info("Service Name: [" + serviceid
					+ " : " + operationid + "]" + " moodel: [" + servmodelname
					+ "], " + " CLASS NAME: [" + servmodelclass + "]");

			
			// ServiceMessage smoin=new ServiceMessage(body);
			Object objtmp = ObjectFoundy.factory(servmodelclass);
			Class clstmp = (objtmp.getClass()).getSuperclass();
			String cname = clstmp.getName();
			//
			if (cname != null) {
				JSONModelBase imb = null;

				imb = (JSONModelBase) objtmp;
				logger.debug("--->  " + reqDoc.toString());
				respMessage = imb.execute(opcfg, reqDoc);

			}

		} catch (Exception e) {

			respMessage = JSONExceptionMessage.createFaltMsg(e);
			logger.error(e);
			// System.out.println(e.g);
			e.printStackTrace();

		}

		return respMessage;
	}

	private JSONObject SOAP2JSON(ServiceMessage sm) throws SOAPException,
			IOException {

		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage msg = mf.createMessage();

		SOAPPart sp = msg.getSOAPPart();
		SOAPEnvelope envelope = sp.getEnvelope();
		SOAPBody bdy = envelope.getBody();

		// envelope.g
		bdy.addChildElement(sm.getElement());
		msg.saveChanges();

		msg.writeTo(System.out);

		JSONObject jo = new JSONObject();
		SOAPElement ele = sm.getElement();
		Iterator childes = ele.getChildElements();
		while (childes.hasNext()) {

			SOAPElement oc = (SOAPElement) childes.next();
			// System.out.println(oc.getLocalName()+":"+oc.getFirstChild().getTextContent()+":"+oc.getNodeType());
			jo.put(oc.getLocalName(), oc.getFirstChild().getTextContent());

		}

		// System.out.println(ele.getElementName().getLocalName()+"   :01");

		return jo;
	}

}
