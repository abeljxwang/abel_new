package org.abel.webapp.framework;

import java.io.*; //import java.sql.DriverManager;
//import java.sql.SQLException;
import java.util.*; //import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.*;

import org.abel.webapp.model.*;
import org.abel.webapp.view.*;
import org.abel.webapp.common.*;
import org.abel.webapp.configure.*;

//import org.abel.webapp.db.DBConnection;

public class PFContainer extends javax.servlet.http.HttpServlet {

	private static String CONSTANT_SPATH = "TESTENV";
	private static String CONSTANT_AFWORD = "ActionFword";
	private static String PAGE_ERROR = "/page/jsp/common/error.jsp";
	private static String PAGE_LOGON = "/page/jsp/common/logon.jsp";
	private static String PAGE_INDEX = "/page/jsp/common/index.jsp";
	private static String PAGE_REFRESH = "/page/jsp/common/refresh.jsp";

	// private java.util.Hashtable cfg;
	private static EIPConfig ecfg = null;
	private java.lang.String outurl = PAGE_INDEX;
	// private java.lang.String outurlright = null;
	// private java.lang.String errorurl = PAGE_ERROR;

	static Logger logger = Logger.getLogger(PFContainer.class.getName());

	// Main process for the servlet control.
	public void performTask(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws UnsupportedEncodingException {

		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");

		String servletpath = request.getServletPath();
		request.setAttribute(CONSTANT_SPATH, servletpath);

		HttpSession session = request.getSession(true);

		String cuid = null;
		boolean flagms = true;
		long starttime = System.currentTimeMillis();

		outurl = PAGE_INDEX; // init the out page as index

		String pinfo = null;
		String functionstr = null, actionstr = null, outstr = null;

		String outpath = "";
		String outputtype = "page";
		String errorurl = "";
		String OpeType = null;

		FunctionCfg fcfg = new FunctionCfg();

		try {

			if (request.getPathInfo() != null) {
				pinfo = request.getPathInfo(); // get path info

				String[] up = paseUrl(pinfo, "/");
				if (up.length > 1)
					actionstr = up[1];
				request.setAttribute("action", actionstr);
				functionstr = up[0];
				request.setAttribute("function", functionstr);
				// message for info.......
				//System.out.println(request.getServletPath());
				System.out.println(functionstr + "===" + actionstr);

			}

			if (request.getParameter(CONSTANT_AFWORD) != null)
				outstr = request.getParameter(CONSTANT_AFWORD);

			if (functionstr == null) {
				outurl = PAGE_INDEX;

			} else if (functionstr.equals("LOGIN")) {
				session = request.getSession(false);

				outurl = PAGE_LOGON;

				if (actionstr != null && actionstr.equals("Login")) { // LOGIN/login

					if (request.getParameter("User_Name") != null)
						cuid = request.getParameter("User_Name");

					// KMTCEmployee curruser = loginUser(cuid);
					// if (curruser != null) {
					// session.setAttribute("kmtcuser", curruser);
					session.setAttribute("cuid", cuid);

					// }
					outurl = PAGE_INDEX;
				}

			} else if (functionstr.equals("RF") && actionstr.equals("rf")) {
				init();
				request.setAttribute("eipconfig", ecfg);
				outurl = PAGE_REFRESH;

			} else if (actionstr != null && actionstr.equals("ForwardAction")) {

				if (request.getParameter("ForwardURI") != null)
					outurl = request.getParameter("ForwardURI");

			} else {
				if (ecfg.getFucntion(functionstr) == null) {
					// System.out.println("Function not exsit");
					throw new WebAppException("Function: [" + functionstr
							+ "] not exsit");

				} else if (ecfg.getFucntion(functionstr) != null) {
					request.setAttribute("FMFName", functionstr);
					fcfg = ecfg.getFucntion(functionstr);

					if (actionstr == null)
						actionstr = fcfg.getDefaultActName();

					if (fcfg.getAcction(actionstr) == null)
						throw new WebAppException("Action: [" + actionstr
								+ "] in the function of:[" + functionstr
								+ "] not exsit");
					else {
						// System.out.print(actionstr+"========\n");
						request.setAttribute("FMAName", actionstr);
						ActionCfg actcfg = fcfg.getAcction(actionstr);
						// ActionForward dafd = actcfg.getDftFDName();

						outurl = actcfg.getActionFword().getPath();

						if (outstr == null) {

							outstr = actcfg.getDftFDName();
							if (outstr == null)
								throw new WebAppException(
										"Forward Page in the function/action of:["
												+ functionstr + "|" + actionstr
												+ "] not exsit");
						}

						if (actcfg.getFword(outstr) != null) {
							Properties pfd = actcfg.getFword(outstr);
							if (pfd.getProperty("Type") != null) {
								outputtype = pfd.getProperty("Type");
								if (pfd.getProperty("Path") != null)
									outurl = pfd.getProperty("Path");

							}

						} else {
							flagms = false;
						}

						if (flagms) {
							Hashtable models = new Hashtable();
							models = ecfg.getModel();
							String instancename = actcfg.getModelInstance();
							if (instancename != null
									&& instancename.length() > 0) {
								Hashtable model0 = (Hashtable) models
										.get(instancename);
								OpeType = (String) model0.get("Type");

								Object objtmp = ObjectFoundy.factory(OpeType);

								Class clstmp = (objtmp.getClass())
										.getSuperclass();
								String cname = clstmp.getName();

								// cname.equals("com.kmtc.eip.model.InformationModelBase")
								// || cname.equals(
								// "com.kmtc.eip.model.ModelBase")
								// || cname.equals(
								// "com.kmtc.kpi.model.QueryModel")
								if (cname != null) {

									ModelBase imb = null;
									try {
										imb = (ModelBase) objtmp;
									} catch (Exception te) {
										throw new WebAppException(
												"the Model:["
														+ OpeType
														+ "] is not inherited to ModelBase");
									}

									Request ereq = new Request();
									Response eres = new Response();

									if (session.getAttribute("cuid") != null)
										cuid = session.getAttribute("cuid")
												.toString();

									setRequest(request, actionstr, cuid, ereq);
									setSessionRequest(session, ereq);

									ActionForward currentacfd = null;

									try {
										currentacfd = imb.execute(ereq, eres,
												actcfg);

									} catch (Exception mec) {
										outurl = PAGE_ERROR;
										request.setAttribute("modelexception",
												mec);
										throw new WebAppException("the Model:["
												+ OpeType
												+ "] Process Exception");
									}
									if (currentacfd != null) {
										// outurl
										outurl = currentacfd.getPath();
										// outurlleft=currentacfd.getPath();
										outstr = currentacfd.getName();

									}

									Enumeration rkeys = eres.getRResultNames();
									while (rkeys.hasMoreElements()) {
										String keytmp = rkeys.nextElement()
												.toString();
										request.setAttribute(keytmp, eres
												.getRResult(keytmp));
									}
									Enumeration skeys = eres.getSResultNames();
									while (skeys.hasMoreElements()) {
										String keytmp = skeys.nextElement()
												.toString();
										session.setAttribute(keytmp, eres
												.getSResult(keytmp));
									}
									//									
									// Enumeration rkeys =
									// eres.getRResultNames();
									// while (rkeys.hasMoreElements()) {
									// String keytmp =
									// rkeys.nextElement().toString();
									// request.setAttribute(
									// keytmp,
									// eres.getRResult(keytmp));
									// }
									//									

									ereq = null;
									// code safe, clearn the unused obj
									eres = null;
									// code safe, clearn the unused obj
									actcfg = null;
									// code safe, clearn the unused obj

								}
							} // if instance !=null
						}
					}
				}
			}
			if (outurl == null)
				throw new WebAppException("Out page: [" + outurl
						+ "] in the function of:[" + functionstr
						+ "] not exsit");

			// testTask();

		} catch (Exception e) {
			System.err.println(e.getMessage());
			outurl = PAGE_ERROR;
			request.setAttribute("ERROR_MSG", e);

		} finally {

			request.setAttribute("currentjsp", outurl);
			// request.setAttribute("currentjspright", outurlright);

			String portlletbase = outurl;   //"/portlet/basepf.jsp";
			long endtime = System.currentTimeMillis();

			//request.setAttribute("FUNKEYS", ecfg.getAllCfgKeys());

			request.setAttribute("MENDTIME", endtime + "");
			request.setAttribute("MRUNTIME", (endtime - starttime) + "");

			if (outputtype.equals("action"))
				portlletbase = servletpath + "/" + functionstr + "/" + outurl;

			try {
				getServletContext().getRequestDispatcher(portlletbase).forward(
						request, response);
			} catch (Exception se) {
				//
				System.err.println("error is not defined");
				se.printStackTrace();

			}

		}
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

	private void setRequest(javax.servlet.http.HttpServletRequest request,
			String acn, String ccuid, Request ereq) {

		ereq.setUID(ccuid);
		ereq.setActionName(acn);

		Map aat = request.getParameterMap();

		ereq.setParameters(aat);

		Enumeration attnames = request.getAttributeNames();
		while (attnames.hasMoreElements()) {
			String nametmp = attnames.nextElement().toString();
			Object oo = request.getAttribute(nametmp);
			if (oo != null)
				ereq.setAttribute(nametmp, oo);
		}

	}

	private void setSessionRequest(HttpSession sessiontmp, Request ereq) {

		Enumeration attnames = sessiontmp.getAttributeNames();
		while (attnames.hasMoreElements()) {
			String nametmp = attnames.nextElement().toString();
			Object oo = sessiontmp.getAttribute(nametmp);
			if (oo != null)
				ereq.setSAttribute(nametmp, oo);
		}

	}

	// public org.abel.user.KMTCEmployee loginUser(String uid) throws Exception
	// {
	// KMTCUserMgr kmtcum = new KMTCUserMgr();
	// KMTCEmployee currentuser = null;
	// if (uid != null)
	// currentuser = kmtcum.getUser(uid);
	// return currentuser;
	// }

	/*
	 * £¨·Ç Javadoc£©
	 * 
	 * @see javax.servlet.Servlet#destroy()
	 */

	/**
	 * Initializes the servlet.
	 */
	public void init() {

		try {
			String prefix = getServletContext().getRealPath("/");
			String rptcfgfile = getServletConfig().getInitParameter("webcfg");
			ecfg = new EIPConfig(prefix + rptcfgfile);
			WebAppRuningMsg.setWorkingPath(prefix);

			// String rptcfgfile =
			// getServletConfig().getInitParameter("dictPath");
			//			
			// ecfg = new EIPConfig(prefix + rptcfgfile);
			//			
			// dictPath

			// DictPathCache.

			// Loging configration
			// String logcfgfile =
			// getServletConfig().getInitParameter("logcfg");
			// PropertyConfigurator.configure(prefix + logcfgfile);
			// logger.info("Cfg Starting working");

			// Commonfile
			String commoncfg = getServletConfig().getInitParameter("commoncfg");
			StandCfgPV.init(prefix + commoncfg);

			if (StandCfgPV.get("dictcfg") != null)
				StandCfgPV.set("dictcfg", prefix + StandCfgPV.get("dictcfg"));

			// DBConnection.setFlag(false);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void destroy() {
		super.destroy();
		// try{
		// DriverManager.deregisterDriver(new DB2Driver());
		// }catch (SQLException sqle){}

	}

	public void doGet(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		performTask(request, response);

	}

	public void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		performTask(request, response);

	}

	/**
	 * Returns the servlet info string.
	 */
	public String getServletInfo() {
		return super.getServletInfo();
	}

}
