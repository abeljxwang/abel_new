package org.abel.webapp.configure;

import java.util.*;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;

//import org.w3c.dom.Attr;
import org.w3c.dom.Document;
//import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import org.xml.sax.SAXException;
//import org.xml.sax.SAXNotRecognizedException;
//import org.xml.sax.SAXNotSupportedException;
//import org.xml.sax.SAXParseException;

import org.apache.xerces.parsers.DOMParser;

public class CFGManager {

	Hashtable eipcfg = new Hashtable();
	Hashtable mcfg = new Hashtable();
	FunctionCfg fcfgtmp = new FunctionCfg();
	ActionCfg acfgtmp = new ActionCfg();

	int accintmp = 0;
	/**
	 * Constructor for PasingX.
	 */
	public CFGManager(String url) {
		super();
		DOMParser parser = new DOMParser();
		//parser.s

		try {
			parser.parse(url);
			Document document = parser.getDocument();

			//NodeList elements = document.getElementsByTagName("Function");
			pasingF(document);
			pasingM(document);

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}

	public Hashtable getcfg() {
		return eipcfg;
	}
	public Hashtable getModels() {
		return mcfg;
	}

	private ActionCfg getacfg() {
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
			if (nname.equals("Function")) {
				fcfgtmp = new FunctionCfg();
				fcfgtmp.setProperties(tmpp);
				fcname = fcfgtmp.getProperties("FunctionID");
			} else if (nname.equals("Portlet")) {
				String iname = tmpp.getProperty("DefaultAction");
				fcfgtmp.setDefaultActName(iname);
			} else if (nname.equals("ActionMapping")) {
				//String iname = tmpp.getProperty("DefaultAction");
				//fcfgtmp.setDefaultActName(iname);
			} else if (nname.equals("Action")) {
				accintmp = 0;
				if (acfgtmp.getPropertie("Name") != null)
					fcfgtmp.addAcction(acfgtmp.getPropertie("Name"), acfgtmp);
				acfgtmp = new ActionCfg();
				acfgtmp.setProperties(tmpp);
			} else if (nname.equals("Policy")) {
				//System.out.println(tmpp.getProperty("id"));
				acfgtmp.setPolicyID(tmpp.getProperty("id"));

			} else if (nname.equals("MetaData")) {
				//System.out.println(tmpp);
				NodeList nl = element.getChildNodes();
				Node ndtmp = null;
				for (int li = 0; li < nl.getLength(); li++) {
					ndtmp = nl.item(li); //.getNodeName());
					if (ndtmp.getNodeName().equals("Attribute")) {
						acfgtmp.setMeta(
							ndtmp
								.getAttributes()
								.getNamedItem("Name")
								.getNodeValue(),
							ndtmp.getFirstChild().getNodeValue());

					}
				}

			} else if (nname.equals("ModelInstance")) {
				String iname = tmpp.getProperty("Name");
				acfgtmp.setModelInstance(iname);
			} else if (nname.equals("Parameter")) {
				String iname = tmpp.getProperty("Name");
				//acfgtmp.setParameter(iname,"");
				//System.out.println(iname);
				if (element.getFirstChild() != null) {

					String valuetmp =
						element.getFirstChild().getNodeValue() + "";
					//System.out.println("name/"+iname+": "+"value/"+valuetmp);
					acfgtmp.setParameter(iname, valuetmp.trim());
					acfgtmp.setPA(iname);
				}

			} else if (nname.equals("Forward")) {
				accintmp++;

				String fname = tmpp.getProperty("Name");
				//System.out.println("---01 " + fname + ":" + tmpp);
				acfgtmp.addFwords(fname, tmpp);
				//acfgtmp.s
				//System.out.println("---02 "+acfgtmp);
				if (accintmp == 1)
					acfgtmp.setDftFDName(fname);

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

	public void pasingF(Document document) throws Exception {
		NodeList elements = document.getElementsByTagName("Function");

		for (int j = 0; j < elements.getLength(); j++) {
			//gen.pasingF(elements.item(0));
			Node ee = elements.item(j);
			pasingN(ee);
			fcfgtmp.addAcction(acfgtmp.getPropertie("Name"), acfgtmp);
			eipcfg.put(fcfgtmp.getProperties("FunctionID"), fcfgtmp);

		}

	}

	public void pasingM(Document document) throws Exception {
		NodeList elements = document.getElementsByTagName("Model");

		for (int j = 0; j < elements.getLength(); j++) {
			//gen.pasingF(elements.item(0));
			Node ee = elements.item(j);
			Properties tmpp = pasingA(ee);
			mcfg.put(tmpp.getProperty("Name"), tmpp);

		}

	}

	public static void main(String argv[]) {

		//String elementName = "Function";
		//String attributeName = null;

		String arg = "c:\\abelwar\\cfgfile2.xml";

		CFGManager gen = new CFGManager(arg);
		Hashtable hh = gen.getcfg();

		FunctionCfg fcfg = (FunctionCfg) hh.get("FORM01");
		ActionCfg acfg = fcfg.getAcction("C02");
		System.out.println(acfg.metas);
		System.out.println(acfg.parameters);
		System.out.println(acfg.getPolicyID());
		//DOMParser parser = new DOMParser();

	}

}
