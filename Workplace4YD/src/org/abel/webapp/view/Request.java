package org.abel.webapp.view;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author JohnsonLam
 * @version 1.0
 */

import java.util.*;

public class Request {

	public Request() {
		parameters = new Hashtable();
		attributes = new Hashtable();
		sessionattributes = new Hashtable();
		datas = new Hashtable();
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String name) {
		actionName = name;
	}

	public void setUID(String uid) {
		this.uid = uid;
	}

	public String getUID() {
		return uid;
	}

	/**
	 * 
	 * @param params must be the pair <String, String[]>
	 */
	public void setParameters(Map params) {
		parameters.putAll(params);
	}

	public void setParameter(String name, String[] value) {
		parameters.put(name, value);
	}

	public void setParameter(String name, String value) {
		parameters.put(name, value);
	}

	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	public void setSAttribute(String name, Object value) {
		sessionattributes.put(name, value);
	}

	public void setData(String name, String value) {
		datas.put(name, value);
	}

	public String getData(String name) {
		return (String) datas.get(name);
	}

	public String getParameter(String name) {

		//String values =  parameters.get(name).toString();
		String result=null;

		if (parameters.get(name) != null) {
			//System.out.println(parameters.get(name));
			String[] values = (String[]) parameters.get(name);
			//System.out.println(values );
			if (values.length > 0)
				result= values[0].toString();
		} 
		
		return result;
	}

	public String[] getParameters(String name) {

		return (String[]) parameters.get(name);
	}

	public String getActionForwardName() {
		return getParameter(ACTIONFORWARD);
	}

	public Map getParameters() {
		return parameters;
	}

	public Object getAttribute(String name) {

		return attributes.get(name);
	}
	public Object getSAttrubite(String name) {
		return sessionattributes.get(name);
	}

	public Enumeration getSAttrubiteNames() {
		return sessionattributes.keys();
	}

	public Map getAttributes() {
		return attributes;
	}

	public String toString() {
		return "ActionName("
			+ actionName
			+ ")\n"
			+ "UID("
			+ uid
			+ ")\n"
			+ "Parameters("
			+ parameters.toString()
			+ ")\n"
			+ "Attributes("
			+ attributes.toString()
			+ ")\n"
			+ "PortletData("
			+ datas.toString()
			+ ")\n";
	}

	private static final String ACTIONFORWARD = "ActionForward";
	private String actionName;
	private String uid;
	private Map parameters = null;
	private Map attributes = null;
	private Hashtable sessionattributes = null;
	private Map datas = null;
}