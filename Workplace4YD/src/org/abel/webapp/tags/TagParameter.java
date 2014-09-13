package org.abel.webapp.tags;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TagParameter extends TagSupport {
   private String name,value=null;

   public void setName(String name) {
      this.name = name;
   }
   public void setValue(String value) {
      this.value = value;
   }
   
   public int doStartTag() throws JspException {
      try {
         pageContext.getOut().print(name+"="+value+"&");
      } catch (IOException ioe) {
         throw new JspException("Error:    IOException     while writing to client");
      }
      return SKIP_BODY;
   }
}
     
     
     //<portletAPI:URIParameter name='ForwardURI' value='/jsp/kpi/KPIItemAdd2.jsp'/> 