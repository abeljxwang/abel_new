package org.abel.webapp.view;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author JohnsonLam
 * @version 1.0
 */

import java.util.Map;
import java.util.Hashtable;
import java.util.Enumeration;
public class EIPSession {

	public EIPSession() {
		attributes = new Hashtable();
	}

	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	public Object getAttribute(String name) {

		return attributes.get(name);
	}

	public Map getAttributes() {
		return attributes;
	}
	
		public Enumeration getAttributeNames() {

		return attributes.keys();
	}

	public String toString() {
		return "Attributes(" + attributes.toString() + ")\n" + ")\n";
	}

	private Hashtable attributes = null;

}