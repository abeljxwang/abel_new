package org.abel.service.base.config;

import java.util.*;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import org.xml.sax.SAXException;
//import org.xml.sax.SAXNotRecognizedException;
//import org.xml.sax.SAXNotSupportedException;
//import org.xml.sax.SAXParseException;

import org.apache.xerces.parsers.DOMParser;




public class CFGHandler {

	Hashtable soa_cfg = new Hashtable();
	Hashtable m_cfg = new Hashtable();
	ServiceCfg serv_cfgtmp = new ServiceCfg();
	OperationCfg acfgtmp = new OperationCfg();
	DOMParser parser = new DOMParser();

	int accintmp = 0;
	/**
	 * Constructor for PasingX.
	 */
	public CFGHandler() {
		//super();
		//DOMParser parser = new DOMParser();
		//parser.s

	}
	
	public CFGHandler(String url) {
		init(url);

	}

	private void init(String url) {
		

		try {
			parser.parse(url);
			Document document = parser.getDocument();

			pasingS(document);
			pasingM(document);

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}


	public Hashtable getcfg() {
		return soa_cfg;
	}
	public Hashtable getModels() {
		return m_cfg;
	}

	private OperationCfg getacfg() {
		return acfgtmp;
	}

	/** Prints the specified elements in the given document. */

	public Properties pasingA(Node element) throws Exception {
		Properties result = new Properties();
		NamedNodeMap attributes = element.getAttributes();
		Node tmpnode = null;
		for (int a = 0; a < attributes.getLength(); a++) {
			tmpnode = attributes.item(a);
			String aname = null, avalue = null;
			aname = tmpnode.getLocalName();
			avalue = tmpnode.getNodeValue();
			result.setProperty(aname, avalue);
		}
		return result;

	}

	public Properties pasingP(Node element) throws Exception {
		Properties result = new Properties();
		if (element.hasChildNodes()) {
			NodeList nl = element.getChildNodes();
			for (int a = 0; a < nl.getLength(); a++) {
				Node nd = nl.item(a);
				String ppname = nd.getLocalName();
				if (ppname != null) {
					result.put(ppname, nd.getFirstChild().getNodeValue());
					//System.out.println(ppname);
				}
			}

		}

		return result;

	}

	public void pasingN(Node element) throws Exception {
		String nname = element.getLocalName();
		String fcname = null;

		if (nname != null) {
			//System.out.println(nname);
			Properties tmpp = pasingA(element);
			if (nname.equals(SOA_CMSG.SERVICE)) {
				serv_cfgtmp = new ServiceCfg();
				serv_cfgtmp.setProperties(tmpp);
				fcname = serv_cfgtmp.getProperties(SOA_CMSG.NAME);
			} else if (nname.equals(SOA_CMSG.OPERATION)) {
				accintmp = 0;
				if (acfgtmp.getPropertie(SOA_CMSG.NAME) != null)
				serv_cfgtmp.addAcction(acfgtmp.getPropertie(SOA_CMSG.NAME), acfgtmp);
				acfgtmp = new OperationCfg();
				acfgtmp.setProperties(tmpp);
			} else if (nname.equals(SOA_CMSG.MODELINST)) {
				String iname = tmpp.getProperty(SOA_CMSG.NAME);
				acfgtmp.setModelInstance(iname);
			} else if (nname.equals(SOA_CMSG.PARAMETER)) {
				String iname = tmpp.getProperty(SOA_CMSG.NAME);
				if (element.getFirstChild() != null) {
					String valuetmp =
						element.getFirstChild().getNodeValue() + "";
					acfgtmp.setParameter(iname, valuetmp);
					acfgtmp.setPA(iname);
				}

			} 

		}
		while (element.hasChildNodes()) {
			NodeList elel = element.getChildNodes();
			for (int i = 0; i < elel.getLength(); i++) {
				element = elel.item(i);
				pasingN(element);
			}

		}
		//return acfg;
	}

	public void pasingS(Document document) throws Exception {
		NodeList elements = document.getElementsByTagName(SOA_CMSG.SERVICE);

		for (int j = 0; j < elements.getLength(); j++) {
			//gen.pasingF(elements.item(0));
			Node ee = elements.item(j);
			pasingN(ee);
			serv_cfgtmp.addAcction(acfgtmp.getPropertie(SOA_CMSG.NAME), acfgtmp);
			soa_cfg.put(serv_cfgtmp.getProperties(SOA_CMSG.NAME), serv_cfgtmp);

		}

	}

	public void pasingM(Document document) throws Exception {
		NodeList elements = document.getElementsByTagName(SOA_CMSG.MODEL);

		for (int j = 0; j < elements.getLength(); j++) {
			//gen.pasingF(elements.item(0));
			Node ee = elements.item(j);
			Properties tmpp = pasingA(ee);
			m_cfg.put(tmpp.getProperty(SOA_CMSG.NAME), tmpp);

		}

	}

	public static void main(String argv[]) {

		String elementName = "Service";
		String attributeName = null;
//
		String arg = "c:\\cnpcwar\\soacfg.xml";
//
  		CFGHandler  gen = new CFGHandler(arg);
  		//gen.init(arg);
		Hashtable hh = gen.getcfg();
//
		ServiceCfg fcfg = (ServiceCfg) hh.get("DrillOperationProcess");
		OperationCfg acfg = fcfg.getAcction("query_unit_process");
	//System.out.println(acfg.metas);
		System.out.println(acfg.parameters);
		System.out.println(acfg.getModelInstance());
		//System.out.println(acfg.getPolicyID());
//		//DOMParser parser = new DOMParser();

	}

}
