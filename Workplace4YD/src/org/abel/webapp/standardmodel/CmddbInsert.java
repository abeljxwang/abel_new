package org.abel.webapp.standardmodel;

import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.configure.*;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;


import org.abel.webapp.db.CmdMetaData;
import org.abel.webapp.db.DBField;
import org.abel.webapp.db.DBHandler;


import java.util.*;

/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CmddbInsert extends ModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(
	Request request,
	Response response,
	ActionCfg conf)  {
		Vector ERROR_INFO = new Vector();  //null;
		String sql = null;
		String sqlbase = null,
			sqlfield = null,
			fieldname = null,
			sqltable = null;

		//sqltable
		if (conf.getParameter("sqltable") != null) {
			sqltable = conf.getParameter("sqltable");
			response.setRResult("sqltable", sqltable);
		}

		//SQLBASE
		if (conf.getParameter("sqlbase") != null)
			sqlbase = conf.getParameter("sqlbase");

		//sqlfield
		if (conf.getParameter("sqlfield") != null)
			sqlfield = conf.getParameter("sqlfield");

		//fieldname
		if (conf.getParameter("fieldname") != null)
			fieldname = conf.getParameter("fieldname");

		//parsing the sqlfield, string splited with ,
		ParameterParser ps = new ParameterParser();
		Vector fieldv = new Vector();
		if (sqlfield != null) {
			fieldv = ps.getparameters(sqlfield);
			//response.setRResult("formparameter", fieldv);
		}

		//parsing the fieldname, string splited with ,
		Vector namev = new Vector();
		if (fieldname != null) {
			namev = ps.getparameters(fieldname);
			//response.setRResult("fieldname", namev);
		}
		
		CmdMetaData cmd = new CmdMetaData();
		try {
			for (int i = 0; i < fieldv.size(); i++) {
				DBField dbf =
					new DBField((String) fieldv.elementAt(i),(String) namev.elementAt(i));
				cmd.set(i, dbf);

			}
		} catch (Exception e) {
			e.printStackTrace();
			ERROR_INFO.add("配置参数处理错误");
		}

		response.setRResult("metadata", cmd);
		
		//case by forword, insert, insertform
		String fwd = conf.getDftFDName();
		//process insert
		
			//fetch imput parameters and put them into a properties
			Properties ppkv = new Properties();
			String tmp = null;
			for (int i = 0; i < fieldv.size(); i++) {
				tmp = (String) fieldv.elementAt(i);
				if (request.getParameter(tmp) != null) {
					ppkv.setProperty(tmp, request.getParameter(tmp));
				} else
					ppkv.setProperty(tmp, "");
			}

			//creat a sql, based sqlbase and parameter properties
			if (sqlbase != null && ppkv != null)
				sql = ps.complete(sqlbase, ppkv);
			else
				ERROR_INFO.add("SQL 处理错误，sqlbase 或 页面输入参数为空。请察看sqlbase, 其值为："+sqlbase+" 参数参数为："+ppkv+". 条件不足，无法生成sql语句");			
			System.out.println(sql);

			DBHandler dbh = new DBHandler();
			try {
				dbh.insert(sql);
				dbh.close();
				dbh = null;
			} catch (Exception se) {
				se.printStackTrace();
				ERROR_INFO.add("数据库插入操作错误，请察看日志文件");	
			} finally {
				if (dbh != null) dbh.close();
			}

		
		if(ERROR_INFO.size()>0 ) response.setRResult("ERROR_INFO", ERROR_INFO);
		
		//ActionForward acf=conf.getActionForward(actionname);

		return conf.getActionFword();

	}

}
