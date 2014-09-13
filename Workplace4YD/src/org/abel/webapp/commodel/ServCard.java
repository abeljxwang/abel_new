package org.abel.webapp.commodel;

import java.util.Properties;
import java.util.Vector;

import org.abel.webapp.configure.ActionCfg;
import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.db.CmdMetaData;
import org.abel.webapp.db.DBField;
import org.abel.webapp.db.DBHandler;
import org.abel.webapp.db.EIPResultSet;
import org.abel.webapp.standardmodel.ParameterParser;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;

public class ServCard {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(
	Request request,
	Response response,
	ActionCfg conf) {

		
		//if(ERROR_INFO.size()>0 ) response.setRResult("ERROR_INFO", ERROR_INFO);
		return conf.getActionFword();

	}


}
 