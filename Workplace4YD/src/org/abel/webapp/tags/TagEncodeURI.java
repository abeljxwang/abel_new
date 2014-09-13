package org.abel.webapp.tags;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TagEncodeURI extends TagSupport {
   private String path;

   public void setPath(String path) {
      this.path = path;
   }
   
   public int doStartTag() throws JspException {
      try {
      	
		javax.servlet.http.HttpServletRequest request =
			(javax.servlet.http.HttpServletRequest) pageContext
				.getRequest();
		String appbase=request.getContextPath();
		
      	
         pageContext.getOut().print(appbase+path);
      } catch (IOException ioe) {
         throw new JspException("Error:    IOException     while writing to client");
      }
      return SKIP_BODY;
   }
}
      