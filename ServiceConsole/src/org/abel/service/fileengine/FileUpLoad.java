package org.abel.service.fileengine;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class for Servlet: FileDownLoad
 * 
 */
public class FileUpLoad extends javax.servlet.http.HttpServlet implements
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
		PrintWriter out = response.getWriter();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart == true) {
			try {
				// Create a factory for disk-based file items

				DiskFileItemFactory factory = new DiskFileItemFactory();

				// Set factory constraints
				factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
				//factory.setRepository(tempPathFile);// 设置缓冲区目录

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				//upload.setAllowedFilesList("doc,xls,"); 

				// Set overall request size constraint
				upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

				List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
				Iterator<FileItem> i = items.iterator();
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					
					if (fi.isFormField()) {
				        //processFormField(item);
						//fi.g
						String name = fi.getFieldName();
					    String value = fi.getString();
					    if(name.equals("Sys_ID")) System.out.println("Syetem Seeting is: "+value);

						
				    } else {
				    	String fileName = fi.getName();
				    

					
					if (fileName != null&&fileName.endsWith("xls")) {
						File fullFile = new File(fi.getName());
						File savedFile = new File(uploadPath, fullFile.getName());
						fi.write(savedFile);
						savedFile=null;
						fullFile=null;
						
						out.println("<center>The File:"+fileName +" upload successfull</center>");
						
					}
					else					
					out.println("<center>The File:"+fileName +"'s format is not permited to upload</center>");
				    }
				}
				System.out.print("upload succeed");
				
			} catch (Exception e) {
				// 可以跳转出错页面
				e.printStackTrace();
				out.println("Exception: "+e.toString());
			}
		} else {
			System.out.println("the enctype must be multipart/form-data");
			out.println("Exception: "+"the enctype must be multipart/form-data");
		}

		out.flush();

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