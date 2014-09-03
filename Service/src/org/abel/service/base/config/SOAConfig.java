package org.abel.service.base.config;

import javax.xml.parsers.*; // JAXP
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;

import org.apache.xerces.parsers.DOMParser;



public class SOAConfig {

	private Hashtable models = new Hashtable();
	private Hashtable functions = new Hashtable();
	private String description="EIP Configration";
	private String cfgf = null;
	//private Document document = null;
	/**
	 * Constructor for EIPConfig.
	 */
	public SOAConfig(String cfgfile) {
		cfgf = cfgfile;
		CFGHandler px=new CFGHandler(cfgf);
		functions=(Hashtable)px.getcfg().clone();
		models=(Hashtable)px.getModels().clone();
		px=null;
	}

	public Hashtable getCfg()  {
				
		return functions;
	}

	public Enumeration getAllCfgKeys()  {				
		return functions.keys();
	}	
		
	public Hashtable getModel() {
		
		return models;
	}
	public ServiceCfg getFucntion(String fname)  {
			//System.out.print("Function:  ");
			ServiceCfg fff = (ServiceCfg) functions.get(fname);
			return fff;
	}

	public static void main(String[] args) {
		SOAConfig ecfg = new SOAConfig("c:\\cfgtst\\aa.xml");
		try {
			//System.out.println(ecfg.getModel());
			ServiceCfg fcfg=ecfg.getFucntion("Report");
			//System.out.println(fcfg.getProperties());
			OperationCfg acfg=fcfg.getAcction("Test");
			//System.out.println(acfg.getAllForward());


		} catch (Exception e) {
		}

	}

}
