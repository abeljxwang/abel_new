package org.abel.webapp.tags;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TagAction extends TagSupport {
   private String name;

   public void setName(String name) {
      this.name = name;
   }
   
   public int doStartTag() throws JspException {
      try {
         pageContext.getOut().print(name+"?");
      } catch (IOException ioe) {
         throw new JspException("Error:    IOException     while writing to client");
      }
      return SKIP_BODY;
   }
}
      