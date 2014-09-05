package org.abel.service.base.common;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author JohnsonLam
 * @version 1.0
 */

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import java.util.Properties;
import java.io.FileInputStream;

//import com.kmtc.eip.message.*;

public class EngineLogger {

  protected EngineLogger() {
      logger = Logger.getLogger(EngineLogger.class.getName());
      PropertyConfigurator.configure (configFilename);
//      sender = MessageSender.getMessageSender();
			//sender = null;

      Properties prop = new Properties();
      try{
      	prop.load(new FileInputStream(configFilename));
		String alarmStr = (String)prop.get("log4j.rootLogger.AlarmLevel");
		alarmLevel = Level.toLevel(alarmStr);
        alarmReceivers=(String)prop.get("log4j.rootLogger.AlarmReceivers");
      }
      catch(Exception e){
        alarmLevel = defaultAlertLevel;
      	// need to do sth??
      }
  }

  public static EngineLogger getLogger(){
    if (eipLogger == null){
			eipLogger = new EngineLogger();
    }
    return eipLogger;
  }
  
  public static void init (String logcfgfile){
	    
	    	configFilename=logcfgfile;

	    
	  }

  public void debug(String message){
  	logger.debug(message);
  	sendAlarmMsg(Level.toLevel("DEBUG"), message);
  }

  public void info(String message){
  	logger.info(message);
  	sendAlarmMsg(Level.toLevel("INFO"), message);
  }

  public void warn(String message){
  	logger.warn(message);
  	sendAlarmMsg(Level.toLevel("WARN"), message);
  }

  public void error(String message){
  	logger.error(message);
    sendAlarmMsg(Level.toLevel("ERROR"), message);
  }

  public void fatal(String message){
  	logger.fatal(message);
  	sendAlarmMsg(Level.toLevel("FATAL"), message);
  }

	public void log(Level lev, String message){
		logger.log(lev, message);
		sendAlarmMsg(lev, message);
	}


	private void sendAlarmMsg(Level lev ,String message){
		if (lev.toInt() < alarmLevel.toInt())
			return;

    if(alarmReceivers == null)
      return;

	}


  public static void main(String[] args) {
    EngineLogger logger = EngineLogger.getLogger();
    logger.debug("Priority DEBUG Message");
    logger.info("Priority INFO Message");
    logger.warn("Priority WARN Message");
    logger.error("Priority ERROR Message");
    logger.fatal("Priority FATAL Message");
  }


  private static EngineLogger eipLogger = null;
  private static String configFilename = "c:/data/log4j.properties";
  //private static final String SENDER = "Logger@portal.eip";
  private static final Level defaultAlertLevel = Level.toLevel("INFO");

	private Level alarmLevel;
  //private MessageSender sender;
  private String alarmReceivers=null;
  private Logger logger = null;
}