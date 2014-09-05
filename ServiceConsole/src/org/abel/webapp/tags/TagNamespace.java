package org.abel.webapp.tags;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TagNamespace extends TagSupport {
   private String value;

   public void setValue(String value) {
      this.value = value;
   }
   
   public int doStartTag() throws JspException {
      try {
      	
         pageContext.getOut().print(""+value);
      } catch (IOException ioe) {
         throw new JspException("Error:    IOException     while writing to client");
      }
      return SKIP_BODY;
   }
}
      