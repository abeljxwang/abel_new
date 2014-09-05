package com.ibm.ais.soa.commonmodel;

import java.util.Vector;
import javax.xml.soap.*;

import org.abel.webapp.configure.*;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.standardmodel.ParameterParser;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;


/**
 * @author administrator
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */

public class ServiceCallModel extends ModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	/* (non-Javadoc)
	 * @see org.abel.webapp.model.ModelBase#execute(org.abel.webapp.view.Request, org.abel.webapp.view.Response, org.abel.webapp.configure.ActionCfg)
	 */
	public ActionForward execute(Request request, Response response,
			ActionCfg conf) {
		// 1 get Configrations
		String endpoint = null;
		String service = null;
		String operation = null;
		ParameterParser ps = new ParameterParser();

		if (conf.getParameter("endpoint") != null)
			endpoint = conf.getParameter("endpoint");
		if (conf.getParameter("service") != null)
			service = conf.getParameter("service");
		if (conf.getParameter("operation") != null)
			operation = conf.getParameter("operation");

		// sqlfield
		Vector request_pv = new Vector();
		if (conf.getParameter("request_p") != null) {
			String request_p = conf.getParameter("request_p");
			request_pv = ps.getparameters(request_p);
		}
		System.out.println("-------"+request_pv);
		
		try{
		SOAPConnection conn;
		SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
		conn = scf.createConnection();
		//conn.
		//conn.
		
		MessageFactory mf = MessageFactory.newInstance();  //MessageFactory.newInstance();
		
		//mf.
		SOAPMessage msg = mf.createMessage();
		msg.setProperty("SOAPAction", "Send_Pkg/send_Pkg");
		
		//msg.
		
		SOAPPart sp = msg.getSOAPPart() ;
		SOAPEnvelope envelope = sp.getEnvelope();
		envelope.addNamespaceDeclaration("q0", "http://ais_gbs.ibm/Service/wsdl/");
		//SOAPHeader hdr = envelope.getHeader();
		SOAPBody bdy = envelope.getBody();

		//hdr.addHeaderElement( envelope.createName("OnlineBooks", "ob","http://www.bookprovider.com" )).addTextNode(
        //                       "Online Book Orders");

		Name bodyName = envelope.createName("send_Pkg","q0","http://ais_gbs.ibm/Service/wsdl/");
		SOAPBodyElement bodyElm = bdy.addBodyElement(bodyName);
		SOAPFactory sf=SOAPFactory.newInstance();
		
		SOAPElement cele =sf.createElement("epr_code");
		cele.addTextNode("1234");
		bodyElm.addChildElement(cele);
		
		//cele =sf.createElement("location");
		//cele.addTextNode("BJ");
		//bodyElm.addChildElement(cele);
		// bodyName.

		// respMessage.
		msg.saveChanges();
		msg.writeTo(System.out);
		java.net.URL urlendpoint = new java.net.URL("http://localhost:18080/Service/SOAPEngineTM/Send_Pkg");
		//conn.call(msg, urlendpoint);
		//conn.close();
		//msg.setContentDescription("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		//urlendpoint.
		
		SOAPMessage msgout= conn.call(msg, urlendpoint);
		msgout.writeTo(System.out);
		
		
		System.out.print(msgout.getSOAPPart().getChildNodes());
		
		//System.out.print(msgout.getContentDescription());
		/*SOAPBody response1=msgout.getSOAPBody();
		Iterator aaa=response1.getChildElements();
	    SOAPElement aaab=null;
		while(aaa.hasNext()){
			aaab=(SOAPElement)aaa.next();
			System.out.println("\n"+aaab.getElementName().getLocalName()+"   a");
			//System.out.println(aaab.getElementQName()+"   b");
			System.out.println(aaab.getNodeType()+"   c");
		}*/
		
		//SOAPBody respBody = msgout.getSOAPPart().getEnvelope().getBody();

		//msgout
		
		//SOAPBody response_b=msgout.getSOAPBody();
		
		/*//msgout.writeTo(System.out);
		//SOAPBody response_b=msgout.getSOAPBody();
		// Create transformer
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
				
		// Get reply content
		Source sc = msgout.getSOAPPart().getContent();
		//sc.
				
		// Set output transformation
		StreamResult result = new StreamResult(System.out);
		tf.transform(sc, result);
		System.out.println();*/
		
		
		
		
		conn.close();
		
		
		} catch (Exception msg_e) {
			msg_e.printStackTrace();

		}

	
		// 2.3 parsing the service call response.

		// 3 set the result to response.

		// 4 fd
		return conf.getActionFword(); // afd;
		// end
	}
	
	public static void main(String yy){
		
		
		
		
	}
}