package org.abel.webapp.configure;

//import javax.xml.parsers.*; // JAXP
//import org.w3c.dom.Node;
//import org.xml.sax.SAXException;
//import java.io.IOException;
import java.util.*;

//import org.w3c.dom.Attr;
//import org.w3c.dom.Document;

//import org.apache.xerces.parsers.DOMParser;
public class EIPConfig {

	private Hashtable models = new Hashtable();
	private Hashtable functions = new Hashtable();
	//private String description="EIP Configration";
	private String cfgf = null;
	//private Document document = null;
	/**
	 * Constructor for EIPConfig.
	 */
	public EIPConfig(String cfgfile) {
		cfgf = cfgfile;
		CFGManager px=new CFGManager(cfgf);
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
	public FunctionCfg getFucntion(String fname)  {
			//System.out.print("Function:  ");
			FunctionCfg fff = (FunctionCfg) functions.get(fname);
			return fff;
	}

	public static void main(String[] args) {
		EIPConfig ecfg = new EIPConfig("c:\\cfgtst\\aa.xml");
		try {
			//System.out.println(ecfg.getModel());
			FunctionCfg fcfg=ecfg.getFucntion("Report");
			//System.out.println(fcfg.getProperties());
			ActionCfg acfg=fcfg.getAcction("Test");
			System.out.println(acfg.getAllForward());


		} catch (Exception e) {
		}

	}

}
