package org.abel.service.soapengine.core;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Abel Wang
 * @version 1.0
 */

import org.abel.service.base.common.EngineLogger;
import org.abel.service.base.config.OperationCfg;
//import org.apache.log4j.Level;
//import org.abel.service.base.config.OperationCfg;


//import com.ibm.ais.base.message.MsgObject;

//import java.util.*;

/**
 * @author abelwang
 *
 */
public abstract class SOAModelBase implements Cloneable {

	public SOAModelBase() {
		//logger = EIPLogger.getLogger();
	}


//	public abstract ServiceMessage execute(
//			ServiceMessage min)
//		throws Exception ;

	public abstract ServiceMessage execute(OperationCfg opcfg,
			ServiceMessage min)
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
	protected EngineLogger logger;
}