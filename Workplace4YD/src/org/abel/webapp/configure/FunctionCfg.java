package org.abel.webapp.configure;

import java.util.*;
/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FunctionCfg {

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

	public ActionCfg getDefaultAct() {
		ActionCfg actdef = (ActionCfg) actions.get(defalutAction);
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
	public void addAcction(String name, ActionCfg acfg) {
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
	public ActionCfg getAcction(String name) {
		//actions.//MInstance= value;

		return (ActionCfg) actions.get(name);

	}

}
