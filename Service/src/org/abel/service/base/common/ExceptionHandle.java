package org.abel.service.base.common;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author JohnsonLam
 * @version 1.0
 */

import org.apache.log4j.Level;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExceptionHandle {

  protected ExceptionHandle() {
  }

  public static void logException(Level lel, String errMsg, Exception e){
  	ByteArrayOutputStream buf = new ByteArrayOutputStream(); 
    e.printStackTrace(new PrintStream(buf));
    EngineLogger.getLogger().log(lel, errMsg + " Exception Msg :" 
    	+ e.getMessage() + "\n StackTrace :" + buf.toString());
  }
}