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
import org.abel.webapp.db.EIPResultSet;

import java.util.*;

/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CmddbQuery extends ModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(
	Request request,
	Response response,
	ActionCfg conf) {
		Vector ERROR_INFO = new Vector(); //null;
		String sql = null;
		String sqltable = null,
			sqlbase = null,
			sqlparas = null,
			sqlfield = null,
			fieldname = null,
			sqlcondition = null,
			fieldlength = null;
		ParameterParser ps = new ParameterParser();
		Properties ppkv = new Properties();

		//basic info for all db process model
		
		//sqltable
		if (conf.getParameter("sqltable") != null) {
			sqltable = conf.getParameter("sqltable");
			response.setRResult("sqltable", sqltable);
		}

		//sqlbase	
		if (conf.getParameter("sqlbase") != null)
			sqlbase = conf.getParameter("sqlbase");

		//sqlfield
		Vector fieldv = new Vector();
		if (conf.getParameter("sqlfield") != null) {
			sqlfield = conf.getParameter("sqlfield");
			fieldv = ps.getparameters(sqlfield);
		}
		//fieldname
		Vector namev = new Vector();
		if (conf.getParameter("fieldname") != null) {
			fieldname = conf.getParameter("fieldname");
			namev = ps.getparameters(fieldname);
		}
		//fieldlength
		Vector flv = new Vector();
		if (conf.getParameter("fieldlength") != null) {
			fieldlength =conf.getParameter("fieldlength");
			flv = ps.getparameters(fieldlength);
		}
		//sql cond
		Vector sqlcnv = new Vector();
		if (conf.getParameter("sqlcondition") != null) {
			sqlcondition = conf.getParameter("sqlcondition");
			sqlcnv = ps.getparameters(sqlcondition);
		}

		//---
		CmdMetaData cmd = new CmdMetaData();
		try {
			for (int i = 0; i < fieldv.size(); i++) {
				DBField dbf =
					new DBField((String) fieldv.elementAt(i),(String) namev.elementAt(i));
				cmd.set(i, dbf);

			}
		} catch (Exception e) {
			e.printStackTrace();
			ERROR_INFO.add("���ò����������");
		}

		String condstrext = "where 1=1";
		for (int i = 0; i < sqlcnv.size(); i++) {
			String ccc = (String) sqlcnv.elementAt(i);
			try {
				int index = (new Integer(ccc)).intValue();
				String tmp1 = cmd.get(index).getfield();

				if (request.getParameter(tmp1) != null
					&& request.getParameter(tmp1).trim().length() > 0) {
					ppkv.setProperty(tmp1, request.getParameter(tmp1));
					condstrext =
						condstrext + " AND " + tmp1 + "=#" + tmp1 + "# ";
				} else {
					ppkv.setProperty(tmp1, "");

				}

				cmd.setView(index);
			} catch (Exception ee) {
				ee.printStackTrace();
				ERROR_INFO.add("��ѯ���������������");
			}
		}
		//System.out.println(condstrext);

		sql = sqlbase;
		System.out.println(sql+"---------------"+ppkv);
		sql = ps.complete(sql, ppkv);
		System.out.println(sql+"---------------"+ppkv);
		response.setRResult("metadata", cmd);

		DBHandler dbh = new DBHandler();
		try {
			EIPResultSet eresult = dbh.query(sql,200);
			System.out.println(eresult.size());
			dbh.close();
			dbh = null;
			response.setRResult("dbresult", eresult);
		} catch (Exception se) {
			se.printStackTrace();
			ERROR_INFO.add("���ݿ��ѯ�����������");

		} finally {
			if (dbh != null)
				dbh.close();
		}
		
		if(ERROR_INFO.size()>0 ) response.setRResult("ERROR_INFO", ERROR_INFO);
		return conf.getActionFword();

	}

	public static void main(String[] args) {
	}
}
