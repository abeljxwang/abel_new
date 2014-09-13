package org.abel.service.fileengine;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
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
public class Copy_2_of_FileUpLoad extends javax.servlet.http.HttpServlet implements
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
	public Copy_2_of_FileUpLoad() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
{
	PrintWriter 
		out = response.getWriter();
	HttpSession 
		session = request.getSession();
	FileUploadListener 
		listener = null; 
    StringBuffer
    	buffy = new StringBuffer();
	long 
    	bytesRead = 0,
		contentLength = 0; 
	
	// Make sure the session has started
	if (session == null)
	{
		return;
	}
	else if (session != null)
	{
		// Check to see if we've created the listener object yet
		listener = (FileUploadListener)session.getAttribute("LISTENER");
		
		if (listener == null)
		{
			return;
		}
		else
		{
			// Get the meta information
	    	bytesRead = listener.getBytesRead();
			contentLength = listener.getContentLength();
		}
	}
	
	/*
	 * XML Response Code
	 */
	response.setContentType("text/xml");
    
    buffy.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
    buffy.append("<response>\n");
    buffy.append("\t<bytes_read>" + bytesRead + "</bytes_read>\n");
    buffy.append("\t<content_length>" + contentLength + "</content_length>\n");

    // Check to see if we're done
    if (bytesRead == contentLength) 
    {
	    buffy.append("\t<finished />\n");
	    
	    // No reason to keep listener in session since we're done
		session.setAttribute("LISTENER", null);
    }
    else
    {
    	// Calculate the percent complete
	    long percentComplete = ((100 * bytesRead) / contentLength);  

	    buffy.append("\t<percent_complete>" + percentComplete + "</percent_complete>\n");
    }
	
    buffy.append("</response>\n");

    out.println(buffy.toString());
    out.flush();
    out.close();
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
{
	// create file upload factory and upload servlet
	FileItemFactory 
		factory = new DiskFileItemFactory();
	ServletFileUpload 
		upload = new ServletFileUpload(factory);

	// set file upload progress listener
	FileUploadListener 
		listener = new FileUploadListener();
	
	HttpSession 
		session = request.getSession();
	
	session.setAttribute("LISTENER", listener);
	
	// upload servlet allows to set upload listener
	upload.setProgressListener(listener);

	List 
		uploadedItems = null;
	FileItem 
		fileItem = null;
	String 
		filePath = "c:\\temp";	// Path to store file on local system
	
		try 
	{
		// iterate over all uploaded files
		uploadedItems = upload.parseRequest(request);
		
		Iterator i = uploadedItems.iterator();
		
		while (i.hasNext()) 
		{
			fileItem = (FileItem) i.next();
			
			if (fileItem.isFormField() == false) 
			{
				if (fileItem.getSize() > 0) 
				{
					File 
						uploadedFile = null; 
					String
						myFullFileName = fileItem.getName(),
						myFileName = "",
						slashType = (myFullFileName.lastIndexOf("\\") > 0) ? "\\" : "/"; // Windows or UNIX
					int
						startIndex = myFullFileName.lastIndexOf(slashType);

					// Ignore the path and get the filename
					myFileName = myFullFileName.substring(startIndex + 1, myFullFileName.length());

					// Create new File object
					uploadedFile = new File(filePath, myFileName);
					
					// Write the uploaded file to the system
					fileItem.write(uploadedFile);
				}
			}
		}
	} 
	catch (FileUploadException e) 
	{
		e.printStackTrace();
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	} 
}       
}
