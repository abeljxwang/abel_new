package org.abel.webapp.standardmodel;

import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.configure.*;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;

//import org.abel.webapp.common.*;
import org.abel.webapp.db.CmdMetaData;
import org.abel.webapp.db.DBField;
import org.abel.webapp.db.DBHandler;
//import org.abel.webapp.db.EIPResultSet;

import java.util.*;

/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CmddbUpdate extends ModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(
	Request request,
	Response response,
	ActionCfg conf){
		String sql = null;
		String sqlbase = null,
			sqlfield = null,
			fieldname = null,
			sqltable = null;
		Vector ERROR_INFO = new Vector(); //null;


		//sqltable
		if (conf.getParameter("sqltable") != null) {
			sqltable = conf.getParameter("sqltable");
			response.setRResult("sqltable", sqltable);
		}
		//sql base
		if (conf.getParameter("sqlbase") != null)
			sqlbase = conf.getParameter("sqlbase");

		//sqlfield	
		if (conf.getParameter("sqlfield") != null)
			sqlfield = conf.getParameter("sqlfield");

		//sqlname	
		if (conf.getParameter("fieldname") != null)
			fieldname = conf.getParameter("fieldname");

		ParameterParser ps = new ParameterParser();
		Vector fieldv = ps.getparameters(sqlfield);

		response.setRResult("formparameter", fieldv);
		//System.out.println(conf.getActionForward(actionname).getPath());
		Vector namev = new Vector();
		if (fieldname != null)
			namev = ps.getparameters(fieldname);
		response.setRResult("fieldname", namev);

		CmdMetaData cmd = new CmdMetaData();
		try {
			for (int i = 0; i < fieldv.size(); i++) {
				DBField dbf =
					new DBField(
						(String) fieldv.elementAt(i),
						(String) namev.elementAt(i));
				System.out.println(
					(String) fieldv.elementAt(i)
						+ "==="
						+ (String) namev.elementAt(i));
				cmd.set(i, dbf);

			}
		} catch (Exception e) {
			e.printStackTrace();
			ERROR_INFO.add("配置参数处理错误");
		}
		response.setRResult("metadata", cmd);


			Properties ppkv = new Properties();
			String tmp = null;
			for (int i = 0; i < fieldv.size(); i++) {
				tmp = (String) fieldv.elementAt(i);
				//System.out.print(tmp+" = ");
				if (request.getParameter(tmp) != null) {
					ppkv.setProperty(tmp, request.getParameter(tmp));
					//System.out.print(request.getParameter(tmp)+"\t\r");
				} else
					ppkv.setProperty(tmp, "");

				//
				//request.getParameter()
			}
			sql = ps.complete(sqlbase, ppkv);
			System.out.println(sql);

			DBHandler dbh = new DBHandler();
			try {
				dbh.update(sql);
				dbh.close();
				dbh = null;
			} catch (Exception se) {
				se.printStackTrace();
			} finally {
				if (dbh != null)
					dbh.close();
			}

		
		if (ERROR_INFO.size() > 0)
			response.setRResult("ERROR_INFO", ERROR_INFO);
		return conf.getActionFword();

	}

	public static void main(String[] args) {
	}
}
