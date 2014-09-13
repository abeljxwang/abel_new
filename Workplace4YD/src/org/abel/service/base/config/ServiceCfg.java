package org.abel.service.base.config;

import java.util.*;


/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ServiceCfg {

	String MInstance;
	Properties fp = new Properties();
	Hashtable actions = new Hashtable();
	String defalutAction = null;

	public void setDefaultActName(String name) {
		defalutAction = name;

	}
	public String getDefaultActName() {
		return defalutAction; // = name;

	}

	public OperationCfg getDefaultAct() {
		OperationCfg actdef = (OperationCfg) actions.get(defalutAction);
		return actdef;
	}

	public void setProperties(String key, String value) {
		fp.setProperty(key, value);

	}

	public void setProperties(Properties fpt) {
		fp = (Properties) fpt.clone();

	}
	//	public void setModelInstance(String value){
	//		MInstance= value;
	//	
	//	}
	public void addAcction(String name, OperationCfg acfg) {
		//actions.//MInstance= value;

		actions.put(name, acfg);

	}

	public String getProperties(String key) {
		return fp.getProperty(key);

	}
	public Properties getProperties() {
		return fp;

	}
	//	public String getModelInstance(){
	//		return MInstance;
	//	
	//	}
	public OperationCfg getAcction(String name) {
		//actions.//MInstance= value;

		return (OperationCfg) actions.get(name);

	}

}
