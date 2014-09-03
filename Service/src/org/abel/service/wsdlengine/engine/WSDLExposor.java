package org.abel.service.wsdlengine.engine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.wsdl.Definition;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLWriter;

import org.abel.service.base.common.StandCfgPV;
import org.abel.service.soa.message.DescCacheManager;
import org.abel.service.soa.message.ServiceDesc;
import org.abel.service.soapengine.core.SOAConstant;

/**
 * @version 1.0
 * @author
 */
public class WSDLExposor extends HttpServlet implements Servlet {
	DescCacheManager dcm;

	// = DescCacheManager.getInstance();

	public void init() throws ServletException {
		super.init();
		dcm = DescCacheManager.getInstance();
		try {
		String prefix = getServletContext().getRealPath("/");
		
		//Commonfile
		String commoncfg = getServletConfig().getInitParameter("commoncfg");
		StandCfgPV.init(prefix + commoncfg);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see javax.servlet.http.HttpServlet#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		perform(req, resp);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		perform(req, resp);
	}

	private void perform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String serv_name = null;
		String operationname=null;

		try {

			String pathInfo = request.getPathInfo();
			System.out.println("for test pathinfo: " + pathInfo);
			if (pathInfo != null) {
				if (!validateRequest(pathInfo)) {

					throw new ServletException("Invalid request");
				} else {
					String[] up = paseUrl(pathInfo, "/");
					if (up.length >= 1){
						serv_name = up[0];
						int posi=serv_name.indexOf(".wsdl");
					if(posi>0){
						//int posi=
						serv_name=serv_name.substring(0,posi);
					}
					
					}
					
				}
			}
			// 1 Check if it is a request for WSDL

			//if (serv_name == null) {
				if (request.getParameter("serviceid") != null)
					serv_name = request.getParameter("serviceid");
				
				if(request.getParameter("operation")!=null)
					operationname=request.getParameter("operation");
				// serviceID = transID.substring(0, REQ_TRANS_LEN);
			//}

			// 2 Get WSDL
			// Definition definition = getTransWSDL(transID);
			if (serv_name != null) {
				ServiceDesc sdc1 = dcm.getServiceDesc(serv_name);
				if (sdc1 != null) {

					WSDLGeneratorNew wsdlGenerator = new WSDLGeneratorNew();
					wsdlGenerator.setSO(serv_name, operationname);
					System.out.println();
					Definition definition = wsdlGenerator
							.generatorSingleWSDL_DEF(sdc1);
					// wsdlGenerator.generatorSingleWSDLDefinition(transID);
					WSDLFactory factory = WSDLFactory.newInstance();
					WSDLWriter wsdlWriter = factory.newWSDLWriter();
					
					//response.setContentType("text/xml");
					response.setContentType("text/xml; charset=utf-8");
					response.setHeader("Content-disposition","inline; filename=\""+serv_name+".wsdl\"");
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-control", "must-revalidate");
					//out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
					PrintWriter out = response.getWriter();
					wsdlWriter.writeWSDL(definition, out);

					// out.w
					out.flush();
					out.close();
				} else {
					PrintWriter out = response.getWriter();
					response.setContentType("text/xml");
					writeNoteMsg(out, serv_name);
					out.flush();
					out.close();
				}

			} else {

				PrintWriter out = response.getWriter();
				response.setContentType("text/xml");
				writeNoteMsg1(out, "Please input valid service");
				out.flush();
				out.close();

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private boolean validateRequest(String pathInfo) {
		if (pathInfo.length() < 5)
			return false;
		else
			return true;

	}

	void writeNoteMsg(PrintWriter out, String servid) {
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<Error_Msg>");
		out.println("<error_id>Not Found</error_id>");
		out.println("<error_desc>Service:" + servid
				+ "  is not found.</error_desc>");
		out.println("</Error_Msg>");

	}

	void writeNoteMsg1(PrintWriter out, String msg) {
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<Error_Msg>");
		out.println("<error_desc> " + msg + " </error_desc>");
		out.println("</Error_Msg>");

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

}
