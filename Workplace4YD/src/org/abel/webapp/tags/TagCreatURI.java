package org.abel.webapp.tags;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TagCreatURI extends BodyTagSupport {

	public int doAfterBody() throws JspException {
		try {
			//PageContext.
			javax.servlet.http.HttpServletRequest request =
				(javax.servlet.http.HttpServletRequest) pageContext
					.getRequest();
			String appbase=request.getContextPath();
			
			//WebA2Z/ET
			String servletstr="/ET";
			if(request.getAttribute("TESTENV")!=null) servletstr=request.getAttribute("TESTENV").toString();
			
			String functionstr=request.getAttribute("function").toString();
			//String actionstr = request.getAttribute("action").toString();
			String uribase=appbase+servletstr+"/"+functionstr;
			
			//System.out.println(uribase);
			
			BodyContent bc = getBodyContent();
			// get the bodycontent as string
			String body = bc.getString();
			// getJspWriter to output content
			JspWriter out = bc.getEnclosingWriter();
			if (body != null) {
				out.print(uribase +"/"+ body.trim() + "");
			}
		} catch (IOException ioe) {
			throw new JspException("Error:    " + ioe.getMessage());
		}
		return SKIP_BODY;
	}
}
