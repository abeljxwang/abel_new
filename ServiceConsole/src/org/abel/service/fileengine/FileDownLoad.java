package org.abel.service.fileengine;

import java.io.*;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.config.SOAConfig;
import org.abel.service.base.config.ServiceCfg;
import org.abel.service.soapengine.core.RRModelBase;
import org.abel.service.soapengine.core.ObjectFoundy;
import org.apache.poi.hssf.usermodel.*;

/**
 * Servlet implementation class for Servlet: FileDownLoad
 * 
 */
public class FileDownLoad extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	protected static SOAConfig xmlcfg = null;
	String prefix="", templatepath="";
	/**
	 * Initialize the SOAPEngine
	 */
	public void init() throws ServletException {
		super.init();
		try {
			prefix = getServletContext().getRealPath("/");
			String rptcfgfile = getServletConfig().getInitParameter("xmlcfg");
			xmlcfg = new SOAConfig(prefix + rptcfgfile);
			templatepath=getServletConfig().getInitParameter("template");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public FileDownLoad() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		prepareFile(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		prepareFile(request, response);
	}

	protected void prepareFile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String serviceID = null;
		String operationstr = null;

//		FileInputStream fileIn = null;
		// FileOutputStream fileOut = null;

		try {
			SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String a1=dateformat1.format(new Date());
			 

			String filebase = getServletContext().getRealPath("/");
			String fpath=filebase+templatepath;
			request.setAttribute("fpath", fpath);
			response.setContentType("application/unknow");
			response.setHeader("Content-disposition","attachment; filename=\"ErrorCode "+a1+".xls\"");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-control", "must-revalidate");

			String pathInfo = request.getPathInfo();
			if (!validateRequest(pathInfo)) {
				throw new ServletException("Invalid request");
			} else {
				String[] up = paseUrl(pathInfo, "/");
				if (up.length > 1)
					operationstr = up[1];
				request.setAttribute("action", operationstr);
				serviceID = up[0];

			}

			// 1 Check action/operation
			String servmodelname = null, servmodelclass = null;
			ServiceCfg scfg = xmlcfg.getFucntion(serviceID);
			if (scfg == null) System.out.println("no such Function");
			OperationCfg opcfg = scfg.getAcction(operationstr);
			if (opcfg != null) servmodelname = opcfg.getModelInstance();
			else System.out.println("=========> No such operation:"+ operationstr + " under service:" + serviceID);
			
			System.out.println("FileDownLoad Engine: "+ serviceID+"/"+operationstr + " at "+new Date());
			System.out.println("\t"+servmodelname);

			Hashtable models = xmlcfg.getModel();
			// //String instancename = actcfg.getModelInstance();
			if (servmodelname != null && servmodelname.length() > 0) {
				Hashtable model0 = (Hashtable) models.get(servmodelname);
				servmodelclass = (String) model0.get("Type");
			}

			Object objtmp = ObjectFoundy.factory(servmodelclass);


			RRModelBase imb = null;

			imb = (RRModelBase) objtmp;
			imb.execute(opcfg, request,response);


		} catch (Exception e) {

			e.printStackTrace();}

	}

	void prepareCellContent(HSSFCell cell, String value) {
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(value));
	}


	private boolean validateRequest(String pathInfo) {
		if (pathInfo.length() < 2)
			return false;
		else
			return true;

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