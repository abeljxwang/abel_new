package org.abel.webapp.tags;

//import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TagInit extends TagSupport   {


   public int doStartTag() throws JspException {
      
      return SKIP_BODY;
   }
}
