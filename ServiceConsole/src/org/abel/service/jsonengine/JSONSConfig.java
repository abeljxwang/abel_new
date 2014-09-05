package org.abel.service.jsonengine;

import javax.xml.parsers.*; // JAXP

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.abel.service.base.config.CFGHandler;
import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.config.ServiceCfg;
import org.apache.xerces.parsers.DOMParser;



public class JSONSConfig {

	private Hashtable models = new Hashtable();
	private Hashtable functions = new Hashtable();
	private String description="JSON Configration";
	private String cfgf = null;
	//private Document document = null;
	/**
	 * Constructor for EIPConfig.
	 */
	public JSONSConfig(String cfgfile) {
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
		JSONSConfig ecfg = new JSONSConfig("c:\\abelJV\\jsoncfg.xml");
		try {
			
			Enumeration keys=ecfg.getAllCfgKeys();
			while (keys.hasMoreElements())
			{
				System.out.println(keys.nextElement().toString());
			}
			//System.out.println(ecfg.getModel());
			ServiceCfg fcfg=ecfg.getFucntion("CustomerSummary");
			System.out.println(fcfg.getProperties());
			
			
			
			Hashtable allo =fcfg.actions;
			Enumeration names  =allo.keys();
			while (names.hasMoreElements())
			{
				System.out.println(names.nextElement().toString());
				
			}
			
			OperationCfg acfg=fcfg.getAcction("getUser");
			System.out.println(acfg.getPropertie("method")+":"+acfg.getParameter("sqlbase"));
			


		} catch (Exception e) {
		}

	}

}
