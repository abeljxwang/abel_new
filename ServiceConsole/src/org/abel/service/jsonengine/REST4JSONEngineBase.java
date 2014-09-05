/*
 * Created on 2005-4-13
 */
package org.abel.service.jsonengine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 1 判断PATHINFO,决定是否需要FORWARD 2 根据输入的信息构造SOAPMessage 3
 * 将构造好的SOAPMessage传递给OnMessage方法处理 4
 * OnMessage方法返回响应的SOAMessage，再返回给请求方
 */
public abstract class REST4JSONEngineBase extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// SOA Configration
	protected static JSONSConfig soacfg = null;
	private static int REQ_TRANS_LEN = 4;
	static Logger logger = Logger.getLogger(REST4JSONEngineBase.class.getName());

	/**
	 * Initialize the SOAPEngine
	 */
	public void init() throws ServletException {
		super.init();
		try {
			System.out.println("Service Engine init====> ");
			String prefix = getServletContext().getRealPath("/");
			String rptcfgfile = getInitParameter("jsonservicecfg");

			// System.out.println(prefix + rptcfgfile+ " tag for path");
			soacfg = new JSONSConfig(prefix + rptcfgfile);

			// Loging configration
			String logcfgfile = getInitParameter("logcfg");
			System.out.println(prefix + logcfgfile + " tag for log confg path");
			// EngineLogger.init(prefix + logcfgfile);

			PropertyConfigurator.configure(prefix + logcfgfile);
			logger.info("Cfg Starting working");
			//String commoncfg = getServletConfig().getInitParameter("commoncfg");
			String commoncfg = getInitParameter("commoncfg");
			System.out.println(prefix + commoncfg + " tag for log cmmon cfg path");
			JSONEessentialP.init(prefix + commoncfg);

		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject reqMessage = new JSONObject();

		Enumeration<String> aat = req.getParameterNames();
		while (aat.hasMoreElements()) {
			String nametmp = aat.nextElement().toString();
			Object oo = req.getParameter(nametmp);
			if (oo != null)
				reqMessage.put(nametmp, oo);
		}

		Enumeration<String> attnames = req.getAttributeNames();
		while (attnames.hasMoreElements()) {
			String nametmp = attnames.nextElement().toString();
			Object oo = req.getAttribute(nametmp);
			if (oo != null)
				reqMessage.put(nametmp, oo);
		}

		perform(req, resp, reqMessage);

	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject reqjo = new JSONObject();
		JSONParser parser = new JSONParser();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = req.getReader();
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}

		try {

			logger.info(sb.toString());
			reqjo = (JSONObject) parser.parse(sb.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e);

		}

		perform(req, resp, reqjo);

	}

	/**
	 * SOAP or json/rest Http request should be process on this method.
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void perform(HttpServletRequest request,
			HttpServletResponse response, JSONObject reqMessage) {
		String serviceID = null;
		String operationstr = null;

		try {
			response.setHeader("ContentType", "application/json");
			response.setCharacterEncoding("UTF-8");

			// 0 Check if it is a valid request, if yes, sedt service name.
			String pathInfo = request.getPathInfo();
			if (!validateRequest(pathInfo)) {
				throw new ServletException("Invalid request");
			} else {
				String[] up = paseUrl(pathInfo, "/");
				if (up.length > 1)
					operationstr = up[1];
				request.setAttribute("key", operationstr);
				serviceID = up[0];

			}

			if (serviceID.equalsIgnoreCase("REFRESH")
					&& operationstr.equalsIgnoreCase("refresh")) {
				init();
				//throw new Exception("REFRESH/refresh DONE");
				logger.info("REFRESH/refresh DONE");
				return;

			}

			// 1 Check action/operation
			String headers = request.getHeader("SOAPAction");
			if (headers != null) {
				operationstr = headers;
				int posit = headers.lastIndexOf("/");
				if (posit > 0) {
					if (headers.length() > posit)
						operationstr = headers.substring(posit);

				}
				
			}

			// 2 Generate SOAP DOM request

			//JSONObject reqJSONMessage = reqMessage;

			// if SOAModel

			// else if JSON Model

			JSONObject respJSONMsg = onMessage(reqMessage, serviceID,operationstr);

			response.setStatus(200);
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "GET, POST");
			response.addHeader("Access-Control-Max-Age", "1000");
			response.setContentType("text/html; charset = UTF-8");
			response.setHeader("Cache-Control", "no-cache");

			ServletOutputStream servletOS = response.getOutputStream();
			servletOS.write(respJSONMsg.toString().getBytes("UTF-8"));
			servletOS.flush();

		} catch (Exception e) {
			// e.printStackTrace();
			// Log.getInstance().error(e.getMessage());
			try {
				ServletOutputStream servletOS = response.getOutputStream();

				JSONExceptionMessage.createFaltMsg(servletOS, e);
				servletOS.flush();

			} catch (Exception e1) {
				// soaplog.error(e1);
				e1.printStackTrace();
			}
		}

	}

	/**
	 * Validate the request path information. The path information should start
	 * with a transaction ID
	 * 
	 * @param String
	 *            pathInfo The path Info contained in the URL request.
	 * @return boolean If the request path information is valid.
	 */
	private boolean validateRequest(String pathInfo) {
		if (pathInfo.length() < REQ_TRANS_LEN + 1)
			return false;
		else
			return true;

	}

	void writeNoteMsg(PrintWriter out) {
		out.println("<html><head><title>SOA SOAP Engine Notice</title></head>");
		out.println("<body><font SIZE=\"4\" FACE=\"Verdana\" color=\"ff6633\">EA/SOA SOAP Engine Message</font><br>");
		out.println("<hr size=1 width=200 align=left>");
		out.println("<font SIZE=\"2\" FACE=\"Verdana\" color=\"336699\">Sorry, I don't speak via HTTP GET- you have to use");
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

	private void fdwsdl(HttpServletRequest request,
			HttpServletResponse response, String wsdlpath) {

		try {
			getServletContext().getRequestDispatcher(wsdlpath).forward(request,
					response);
		} catch (Exception se) {
			//
			System.err.println("error is not defined");
			se.printStackTrace();

		}
	}

	protected abstract JSONObject onMessage(JSONObject reqDoc,
			String serviceid, String operationid) throws Exception;
}
