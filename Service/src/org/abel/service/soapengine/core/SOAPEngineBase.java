/*
 * Created on 2005-4-13
 */
package org.abel.service.soapengine.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
//import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.abel.service.base.config.SOAConfig;
import org.w3c.dom.Element;


//import com.ibm.ais.soapengine.impl.EAIProcessor;

/**
 * 1 判断PATHINFO,决定是否需要FORWARD
 * 2 根据输入的信息构造SOAPMessage
 * 3 将构造好的SOAPMessage传递给OnMessage方法处理
 * 4 OnMessage方法返回响应的SOAMessage，再返回给请求方
 */
public abstract class SOAPEngineBase extends HttpServlet {
	//SOA Configration
	protected static SOAConfig soacfg = null;

	//Message Factory
	protected MessageFactory msgFactory = null;
	//Length of request path
	private static int REQ_TRANS_LEN = 4;

	//WSDLURL, Can be set in configfile(web.xml)
	static String wsdlurlbase = "/wsdl/";
	static String configfileurl = "./config/config.properties";
	static DocumentBuilderFactory dbf = null;
	static DocumentBuilder db = null;
	//static Log soaplog = Log.getInstance();

	/**
	 * Initialize the SOAPEngine
	 */
	public void init() throws ServletException {
		super.init();
		try {

			String prefix = getServletContext().getRealPath("/");
			//
			String rptcfgfile = getServletConfig().getInitParameter("soacfg");
			soacfg = new SOAConfig(prefix + rptcfgfile);

			//processor = new EAIProcessor();
			if (getServletConfig().getInitParameter("wsdlurlbase") != null)
				wsdlurlbase =
					getServletConfig().getInitParameter("wsdlurlbase");
			if (getServletConfig().getInitParameter("configfileurl") != null)
				configfileurl =
					prefix
						+ getServletConfig().getInitParameter("configfileurl");
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			msgFactory = MessageFactory.newInstance();

			//soaplog.

		} catch (Exception e) {
			//release the resource
			wsdlurlbase = null;
			dbf = null;
			msgFactory = null;
			db = null;
			//log		
			//soaplog.error(e);
		}

	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		//set a message, said POST is nessary.
		System.out.println(
			"-------------set a message, said POST is nessary-----------------");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		writeNoteMsg(out);
		out.flush();
		out.close();

		//perform(req, resp);
	}

	/**
	* @see javax.servlet.http.HttpServlet#doPost (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		perform(req, resp);

	}

	/**
	 * SOAP Http request should be process on this method.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void perform(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		String serviceID = null;
		String operationstr = null;

		try {
			//0 Check if it is a valid request, if yes, sedt service name.
			String pathInfo = request.getPathInfo();
			System.out.println("for test pathinfo: "+pathInfo);
			if (!validateRequest(pathInfo)) {
				throw new ServletException("Invalid request");
			} else {
				String[] up = paseUrl(pathInfo, "/");
				if (up.length > 1)
					operationstr = up[1];
				request.setAttribute("action", operationstr);
				serviceID = up[0];

			}
			//1 Check if it is a request for WSDL

			if (serviceID.endsWith(SOAConstant.WSDL_SUFFIX)) {
				//serviceID = transID.substring(0, REQ_TRANS_LEN);
				request.getRequestDispatcher(wsdlurlbase + serviceID).forward(
					request,
					response);
				return;
			}

			//2 Generate SOAP DOM request
			Element elmReq =
				db.parse(request.getInputStream()).getDocumentElement();
			

			//SOAPEnvelope se=new SOAPEnvelope();

			//3 Process request SOAP Message
			SOAPMessage respSOAPMsg = onMessage(elmReq, serviceID,operationstr);

			//4 Return response SOAP Message
			if (respSOAPMsg.saveRequired())
				respSOAPMsg.saveChanges();

			response.setStatus(200);
			SOAHelper.putHeaders(respSOAPMsg.getMimeHeaders(), response);
			ServletOutputStream servletOS = response.getOutputStream();
			respSOAPMsg.writeTo(servletOS);
			servletOS.flush();

		} catch (Exception e) {
			e.printStackTrace();
			//Log.getInstance().error(e.getMessage());
			ServletOutputStream servletOS = response.getOutputStream();
			response.setStatus(500);
			try {
				SOAPFaulMessage.createFaltMsg(servletOS, e, msgFactory);
			} catch (Exception e1) {
				//soaplog.error(e1);
				e1.printStackTrace();
			}

			servletOS.flush();

			//throw new ServletException("SOAP Engine Base process error.", e);
		}

	}

	/**
	 * Validate the request path information. The path information should start with a transaction ID
	 * @param 		String		pathInfo	The path Info contained in the URL request.
	 * @return		boolean					If the request path information is valid.
	 */
	private boolean validateRequest(String pathInfo) {
		if (pathInfo.length() < REQ_TRANS_LEN + 1)
			return false;
		else 
			return true;
		
	}

	void writeNoteMsg(PrintWriter out) {
		out.println("<html><head><title>SOA SOAP Engine Notice</title></head>");
		out.println(
			"<body><font SIZE=\"4\" FACE=\"Verdana\" color=\"ff6633\">EA/SOA SOAP Engine Message</font><br>");
		out.println("<hr size=1 width=200 align=left>");
		out.println(
			"<font SIZE=\"2\" FACE=\"Verdana\" color=\"336699\">Sorry, I don't speak via HTTP GET- you have to use");
		out.println("HTTP POST to talk to me.</font></body></html>");

	}

	private String[] paseUrl(String url, String split) {

		StringTokenizer valuetmp = new StringTokenizer(url, split);
		int insize = valuetmp.countTokens();
		if (insize < 3)
			insize = 3;
		String[] up = new String[insize];

		for (int i = 0; i < insize && valuetmp.hasMoreTokens(); i++) {
			up[i] = valuetmp.nextToken();

		}
		return up;

	}

	protected abstract SOAPMessage onMessage(Element reqDoc, String serviceid,String operationid)
		throws SOAPException;
}
