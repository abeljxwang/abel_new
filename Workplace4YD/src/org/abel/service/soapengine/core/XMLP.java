package org.abel.service.soapengine.core;
import java.io.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.abel.service.base.common.ParameterParser;
import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.db.*;
import java.sql.*;
//import org.abel.service.base.db.EIPResultSet;
import java.util.Vector;

public class XMLP {
	
	
	/*
	 * Copyright 2007 Sun Microsystems, Inc.
	 * All rights reserved.  You may not modify, use,
	 * reproduce, or distribute this software except in
	 * compliance with  the terms of the License at:
	 * http://developer.sun.com/berkeley_license.html
	 */




	    private static String filename = null;

	    public void txt(OutputStream out, EIPResultSet sm, OperationCfg opcfg) throws Exception {
	        try {
		            XMLOutputFactory xof = XMLOutputFactory.newInstance();
	            XMLStreamWriter xtw = null;
	            xtw = xof.createXMLStreamWriter(out);
	            //xtw.writeComment("all elements here are explicitly in the HTML namespace");
	            xtw.writeStartDocument("GBK", "1.0");
	            xtw.setPrefix("ns", "http://www.w3.org/TR/REC-html40");
	            xtw.writeStartElement("root");
	            //xtw.writeNamespace("ns", "http://www.w3.org/TR/REC-html40");

	            xtw.writeStartElement("data");
	
	            ParameterParser ps = new ParameterParser();
	            // sqlfield
				Vector resultkeyv = new Vector();
				if (opcfg.getParameter("responsekey") != null) {
					String sqlfield = opcfg.getParameter("responsekey");
					resultkeyv = ps.getparameters(sqlfield);
				}
	            
	            while(sm.next()){
	            	 xtw.writeStartElement("row");
	            for(int ii=0;ii<resultkeyv.size();ii++){
	            	
	            	xtw.writeStartElement(resultkeyv.elementAt(ii).toString());         
		            if(sm.getString(ii+1)!=null) xtw.writeCharacters(sm.getString(ii+1)+"");
		            //else xtw.writeCharacters("N/A");
		            xtw.writeEndElement();
	            }
	            xtw.writeEndElement();
	            }
	            xtw.writeEndElement();
	            xtw.writeEndElement();
	            xtw.writeEndDocument();
	            xtw.flush();
	            xtw.close();
	        } catch (Exception ex) {
	        	ex.printStackTrace();
	            System.err.println(
	                    "Exception occurred while running writer samples");
	        }

	        //System.out.println("Done");
	    }
	}
