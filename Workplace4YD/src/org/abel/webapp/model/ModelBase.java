package org.abel.webapp.model;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author JohnsonLam
 * @version 1.0
 */

import org.apache.log4j.Level;

import org.abel.webapp.configure.*;
import org.abel.webapp.view.*;
import org.abel.webapp.common.*;
import java.util.*;

public abstract class ModelBase implements Cloneable {

	public ModelBase() {
		//logger = EIPLogger.getLogger();
	}

	public ModelBase(String name, boolean mulitThread) {
		this.name = name;
		this.multiThreadSupported = mulitThread;
		//logger = EIPLogger.getLogger();
	}

	//public abstract ActionForward execute(Request request, Response response, Config conf) throws Exception;

	public ActionForward execute(
		Request request,
		Response response,
		EIPSession eipsession,
		ActionCfg conf)
		throws Exception {
		Enumeration attnames;
		Map reqparameters;
		String nametmp;
		Object oo;

		if ((attnames = eipsession.getAttributeNames()) != null) {
			while (attnames.hasMoreElements()) {
				nametmp = attnames.nextElement().toString();
				oo = eipsession.getAttribute(nametmp);
				if (oo != null)
					request.setSAttribute(nametmp, oo);
			}
		}

		ActionForward af = execute(request, response, conf);

		if ((attnames = response.getSResultNames()) != null) {
			while (attnames.hasMoreElements()) {
				nametmp = attnames.nextElement().toString();
				oo = response.getSResult(nametmp);
				if (oo != null)
					eipsession.setAttribute(nametmp, oo);
			}
		}
		return af;
	}

	public ActionForward execute(
		Request request,
		Response response,
		ActionCfg conf)
		throws Exception {
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSupportedByMultiThread(boolean multiThread) {
		this.multiThreadSupported = multiThread;

	}

	public boolean isSupportedByMultiThread() {
		return multiThreadSupported;
	}

	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			ExceptionHandle.logException(
				Level.toLevel("ERROR"),
				"Model can't clone",
				e);
		}
		return o;
	}

	protected String name;
	protected boolean multiThreadSupported;
	protected EIPLogger logger;
}