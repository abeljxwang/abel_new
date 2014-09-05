package org.abel.webapp.standardmodel;

import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.configure.*;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;

//import org.abel.webapp.common.*;
//import org.abel.webapp.db.CmdMetaData;
//import org.abel.webapp.db.DBField;
//import org.abel.webapp.db.DBHandler;
//import org.abel.webapp.db.EIPResultSet;

import java.util.*;


public class CmddbListDB extends ModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(
	Request request,
	Response response,
	ActionCfg conf) {
		String tablenames = null;

		//List sqlcfgnames = null;

		String actionname = request.getActionName();
		//sqltable
		if (conf.getParameter("tablenames") != null) {
			tablenames = conf.getParameter("tablenames");
		}
		ParameterParser pps=new ParameterParser();
		

		Vector tbnamev = pps.getparameters(tablenames);


		response.setRResult("tbname", tbnamev);

		return conf.getActionFword();
	}

	public static void main(String[] args) {
	}
}
