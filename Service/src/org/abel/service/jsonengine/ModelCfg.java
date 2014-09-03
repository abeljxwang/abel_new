package org.abel.service.jsonengine;
import java.util.*;

public class ModelCfg {

	Properties ap = new Properties();
	Hashtable fds = new Hashtable();
	String MInstance;
	
	public void setProperties(String key, String value) {
		ap.setProperty(key, value);
	}
	public void setProperties(Properties fpt) {
		ap = fpt;

	}
	public void setModelInstance(String value) {
		MInstance = value;

	}
	public String getModelInstance() {
		return MInstance;
	}

	public void addFwords(String name, Properties fcfg) {
		fds.put(name, fcfg);
	}
	public String getPropertie(String key) {
		return (String) ap.get(key);
	}
	public Properties getPropertie() {
		return ap;
	}
	public Properties getFword(String name) {
		//actions.//MInstance= value;
		Properties rt = (Properties) fds.get(name);
		return rt;
	}
}
