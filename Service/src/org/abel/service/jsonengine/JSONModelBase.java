package org.abel.service.jsonengine;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Abel Wang
 * @version 1.0
 */


import org.abel.service.base.config.OperationCfg;
import org.apache.log4j.Logger;
//import org.apache.log4j.Level;
//import org.abel.service.base.config.OperationCfg;
import org.json.simple.JSONObject;


//import com.ibm.ais.base.message.MsgObject;

//import java.util.*;

/**
 * @author abelwang
 *
 */
public abstract class JSONModelBase implements Cloneable {
	
	protected static Logger logger = Logger.getLogger( JSONModelBase.class.getName());

	public JSONModelBase() {
		//logger = EIPLogger.getLogger();
	}


//	public abstract ServiceMessage execute(
//			ServiceMessage min)
//		throws Exception ;

	public abstract JSONObject execute(OperationCfg opcfg,
			JSONObject min)
		throws Exception;

	
	
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
				e.printStackTrace();
		}
		return o;
	}

	protected String name;
	protected boolean multiThreadSupported;
	
}