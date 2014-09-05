package com.ibm.ais.soa.commonmodel;

//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.Properties;
//import java.util.Vector;


import org.abel.webapp.configure.ActionCfg;
import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;

/**
 * @author administrator
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class PocLogOutModel extends ModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(Request request, Response response,
			ActionCfg conf) {


		return conf.getActionFword(); // afd;

	}

}
