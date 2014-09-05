/*
 * Created on 2005-4-13
 */
package org.abel.service.soapengine.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.abel.service.base.common.StandCfgPV;
import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.config.SOAConfig;
import org.abel.service.base.config.ServiceCfg;
import org.abel.service.base.db.EIPResultSet;
//import org.w3c.dom.NodeList;



//import com.ibm.ais.soapengine.impl.EAIProcessor;

/**
 * 1 �ж�PATHINFO,�����Ƿ���ҪFORWARD
 * 2 ����������Ϣ����SOAPMessage
 * 3 ������õ�SOAPMessage���ݸ�OnMessage��������
 * 4 OnMessage����������Ӧ��SOAMessage���ٷ��ظ�����
 */
public class XMLEngineBaseNew extends HttpServlet {
	//SOA Configration
	protected static SOAConfig soacfg = null;

	/**
	 * Initialize the SOAPEngine
	 */
	public void init() throws ServletException {
		super.init();
		try {

			String prefix = getServletContext().getRealPath("/");
			//
			String rptcfgfile = getServletConfig().getInitParameter("xmlcfg");
			soacfg = new SOAConfig(prefix + rptcfgfile);
			String commoncfg = getInitParameter("commoncfg");
			StandCfgPV.init(prefix + commoncfg);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		perform(req, resp);
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
			System.out.println("XMLEngine call: "+pathInfo+ "   at "+new java.util.Date());
			if (!validateRequest(pathInfo)) {
				throw new ServletException("Invalid request");
			} else {
				String[] up = paseUrl(pathInfo, "/");
				if (up.length > 1)
					operationstr = up[1];
				request.setAttribute("action", operationstr);
				serviceID = up[0];

			}
			
			//1 Check action/operation
			String servmodelname = null, servmodelclass = null;
			ServiceCfg scfg = soacfg.getFucntion(serviceID);
			if (scfg == null) {
				System.out.println("no such service");
				}

			OperationCfg opcfg = scfg.getAcction(operationstr);
			if (opcfg != null)
				servmodelname = opcfg.getModelInstance();
			else {
				System.out.println("=========> No such operation:" + operationstr
						+ " under service:" + serviceID);
			}
			System.out.println("\t Model: "+servmodelname);


			Hashtable models = soacfg.getModel();
			// //String instancename = actcfg.getModelInstance();
			 if (servmodelname != null && servmodelname.length() > 0) {
				 Hashtable model0 = (Hashtable) models.get(servmodelname);
			 servmodelclass = (String) model0.get("Type");
			 }
			 //System.out.println("CLASS NAME "+servmodelclass);
			
			
			 
			 Object objtmp = ObjectFoundy.factory(servmodelclass);
			 Class clstmp = (objtmp.getClass()).getSuperclass();
			 String cname = clstmp.getName();

			 ModelBase imb = null;
			
			 imb = (ModelBase) objtmp;
			 EIPResultSet sm=imb.execute(opcfg,request);
			 
			String pks=null;
			if(opcfg!=null&&opcfg.getParameter("sqlfield")!=null) pks=opcfg.getParameter("sqlfield");
			String[] pksa=pks.split(",");
			int pksal=pksa.length;
			pks=null;
			pksa=null;
			
			response.setStatus(200);			//
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			ServletOutputStream servletOS = response.getOutputStream();
			//writeNoteMsg(servletOS) ;
			//servletOS.write("<test>aaa</test>".getBytes());
			XMLP xp=new XMLP();
			xp.txt(servletOS, sm, opcfg);
			//respSOAPMsg.writeTo(servletOS);	
			
			servletOS.flush();

		} catch (Exception e) {
			e.printStackTrace();
			//Log.getInstance().error(e.getMessage());
			ServletOutputStream servletOS = response.getOutputStream();
			response.setStatus(500);
			try {
				//XMLFaulMessage.createFaltMsg(servletOS, e, ");
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
		if (pathInfo.length() < 2)
			return false;
		else 
			return true;
		
	}

	void writeNoteMsg(PrintWriter out) {
		out.println("<html><head><title>SOA SOAP Engine Notice</title></head>");
		out.println(
			"<body><font SIZE=\"4\" FACE=\"Verdana\" color=\"ff6633\">EA/SOA XML Engine Message</font><br>");
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

	//protected abstract SOAPMessage onMessage(SOAPMessage reqDoc, String serviceid,String operationid)
	//	throws SOAPException;
}
