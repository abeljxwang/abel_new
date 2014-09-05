package org.abel.service.fileengine;

import java.io.*;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class for Servlet: FileDownLoad
 * 
 */
public class CopyOfFileUpLoad extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	// protected static SOAConfig xmlcfg = null;
	private String uploadPath = "C:\\temp"; // 上传文件的目录
	private String tempPath = "C:\\temp\\buffer\\"; // 临时文件目录
	File tempPathFile;

	String prefix = "", templatepath = "";

	/**
	 * Initialize the SOAPEngine
	 */
	public void init() throws ServletException {
		super.init();
		try {
			File uploadFile = new File(uploadPath);
			if (!uploadFile.exists()) {
				uploadFile.mkdirs();
			}
			File tempPathFile = new File(tempPath);
			if (!tempPathFile.exists()) {
				tempPathFile.mkdirs();
			}

			prefix = getServletContext().getRealPath("/");
			templatepath = getServletConfig().getInitParameter("template");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CopyOfFileUpLoad() {
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

	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processFile(request, response);
	}

	protected void processFile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		 if(isMultipart==true){
		try {
			// Create a factory for disk-based file items
			 
			      
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Set factory constraints
			factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
			//factory.setRepository(tempPathFile);// 设置缓冲区目录

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Set overall request size constraint
			upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

			List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
			Iterator<FileItem> i = items.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				String fileName = fi.getName();
				if (fileName != null) {
					File fullFile = new File(fi.getName());
					File savedFile = new File(uploadPath, fullFile.getName());
					fi.write(savedFile);
				}
			}
			System.out.print("upload succeed");
		} catch (Exception e) {
			// 可以跳转出错页面
			e.printStackTrace();
		}
		}
		  else{
		      System.out.println("the enctype must be multipart/form-data");
		  }
		 
		 PrintWriter out = response.getWriter();
		    out.println("The File upload successfull");



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