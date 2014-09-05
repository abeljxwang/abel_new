package org.abel.webapp.tags;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class KMTCTag extends TagSupport   {
	private int num1, num2;

   public void setNum1(int num1) {
      this.num1 = num1;
   }
   
   public void setNum2(int num2) {
      this.num2 = num2;
   }


   public int doStartTag() throws JspException {
      try {
         pageContext.getOut().print("Welcome to First   Grade Math! ");
         pageContext.getOut().print("The sum of: " + num1 + " and " + num2 + " is: " + (num1+num2));
      } catch (IOException ioe) {
         throw new JspException("Error:  IOException  while writing to client");
      }
      return SKIP_BODY;
   }
}
