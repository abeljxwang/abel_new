package org.abel.webapp.view;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author JohnsonLam
 * @version 1.0
 */

import java.util.Hashtable;
import java.util.Enumeration;

public class Response {

	public Response() {
	}

	public void setActionReport(String actionReport) {
		requestObjects.put(ACTIONREPORTINFO, actionReport);
	}

	public void setRResult(String name, Object o) {
		if (name != null) {
			requestObjects.put(name, o);
		}
	}

	public void setSResult(String name, Object o) {
		if (name != null) {
			sessionObjects.put(name, o);
		}
	}

	public void setCResult(String name, Object o) {
		if (name != null) {
			contextObjects.put(name, o);
		}
	}

	public Object getRResult(String name) {
		if (name == null)
			return null;

		return requestObjects.get(name);
	}

	public Enumeration getRResultNames() {
		return requestObjects.keys();
	}

	public Object getSResult(String name) {
		if (name == null)
			return null;

		return sessionObjects.get(name);
	}

	public Enumeration getSResultNames() {
		return sessionObjects.keys();
	}

	public Object getCResult(String name) {
		if (name == null)
			return null;

		return contextObjects.get(name);
	}

	public Enumeration getCResultNames() {
		return contextObjects.keys();
	}

	private Hashtable requestObjects = new Hashtable();
	private Hashtable sessionObjects = new Hashtable();
	private Hashtable contextObjects = new Hashtable();

	private final static String ACTIONREPORTINFO = "ActionReportInfo";
}