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

public class ActionCfg {

	Properties ap = new Properties(); //action properties

	Properties metas = new Properties(); //MetaData
	String policyid = null; //Policy

	String MInstance; //Model Name
	Properties parameters = new Properties(); //Model parameter
	ArrayList pnal = new ArrayList(); //Model Parameter Array Order List

	String defaultFDname; //Default Forward Action Name
	Hashtable fds = new Hashtable(); //Fowards

	//000	-----------------------Action Properties---Start
	public void setProperties(Properties fpt) {
		ap = fpt;

	}
	public void setProperties(String key, String value) {
		ap.setProperty(key, value);

	}
	public String getPropertie(String key) {
		return (String) ap.get(key);
	}
	public Properties getPropertie() {
		return ap;
	}

	//001	-----------------------Policy---Start	
	public void setPolicyID(String key) {
		policyid = key;
	}
	public String getPolicyID() {
		return policyid;
	}

	//002	-----------------------MetaData---Start	
	public void setMeta(String key, String value) {
		metas.setProperty(key, value);
	}
	public String getMeta(String key) {
		return metas.getProperty(key);
	}
	public Properties getMetas() {
		return metas;
	}

	//003	-----------------------Model Instance Name/Parameters/Order---Start
	public void setModelInstance(String value) {
		MInstance = value;
	}
	public String getModelInstance() {
		return MInstance;
	}
	public void setParameter(String key, String value) {
		parameters.setProperty(key, value);
	}
	public void setParameters(Properties pts) {
		parameters = pts;
	}
	public String getParameter(String key) {
		return (String) parameters.get(key);
	}
	public Properties getParameters() {
		return parameters;
	}
	public void setPA(int index, String pnname) {
		pnal.add(index, pnname);
	}
	public void setPA(String pnname) {
		pnal.add(pnname);
	}
	public String getPA(int index) {
		return pnal.get(index).toString();
	}
	public ArrayList getPAL() {
		return pnal;
	}

	//004   -----------------------Foward Name---Start
	public void setDftFDName(String fdname) {
		defaultFDname = fdname;
	}
	public String getDftFDName() {
		return defaultFDname;
	}
	public void addFwords(String name, Properties fcfg) {
		fds.put(name, fcfg);
	}


	public Properties getFword(String name) {
		Properties rt = (Properties) fds.get(name);
		return rt;
	}
	public Hashtable getAllForward() {
		return fds;
	}


	public ActionForward getActionFword(String name) {
		Properties afdp = (Properties) fds.get(name);
		ActionForward afd=new ActionForward(afdp.getProperty("Name"),afdp.getProperty("Path"));
		return afd;
	}

	public ActionForward getActionFword() {
		//ActionForward afd = (ActionForward) fds.get(defaultFDname);
		Properties afdp = (Properties) fds.get(defaultFDname);
		ActionForward afd=new ActionForward(afdp.getProperty("Name"),afdp.getProperty("Path"));
		return afd;
		
	}	

}
