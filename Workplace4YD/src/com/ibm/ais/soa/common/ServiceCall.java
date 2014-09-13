package com.ibm.ais.soa.common;

import javax.xml.soap.*;

public class ServiceCall {
	SOAPConnection conn;
	SOAPConnectionFactory scf;
	MessageFactory mf;
	
	public void init() throws Exception{
		scf = SOAPConnectionFactory.newInstance();
		conn = scf.createConnection();
		mf = MessageFactory.newInstance();
	}
	
	public  SOAPMessage call(String servicename,String operation,String endpoint,SOAPMessage request)  throws Exception{
		// service calling
		java.net.URL urlendpoint = new java.net.URL(endpoint);
		SOAPMessage msgout= conn.call(request, urlendpoint);
		conn.close();	
		conn=null;
		return msgout;	
		
	}
	
	public void close() throws Exception{
		scf=null;
		if(conn!=null)conn.close();
		mf=null;
	}
}
