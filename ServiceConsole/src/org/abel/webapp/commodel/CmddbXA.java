package org.abel.webapp.commodel;

import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.configure.*;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;


import org.abel.webapp.db.CmdMetaData;
import org.abel.webapp.db.DBField;
import org.abel.webapp.db.XADBHandler;
import org.abel.webapp.standardmodel.*;

import java.util.*;

/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CmddbXA extends ModelBase {

	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(
	Request request,
	Response response,
	ActionCfg conf)  {
		Vector ERROR_INFO = new Vector();  //null;
		String sql = null;
		Vector sqllist=new Vector();
		String sqlbase="",
		    sqlfield = null,
			fieldname = null,
			sqltable = null;
		int sql_num=1;

		//sqltable
		System.out.println("0----------------------");
		if (conf.getParameter("sqltable") != null) {
			sqltable = conf.getParameter("sqltable");
			response.setRResult("sqltable", sqltable);
		}
		if (conf.getParameter("sql_number") != null) {
			sql_num = Integer.parseInt(conf.getParameter("sql_number"));
			
		}
		System.out.println("1------"+sqltable+"----------"+sql_num+"------");
		//SQL1
		for(int i=0;i<sql_num;i++){
		if (conf.getParameter("sqlbase_"+i) != null)
			sqlbase = conf.getParameter("sqlbase_"+i);
			sqllist.add(sqlbase);
		}
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
			ERROR_INFO.add("���ò�����������");
		}

		response.setRResult("metadata", cmd);
		
		//case by forword, insert, insertform
		//String fwd = conf.getDftFDName();
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

			//creat a sql, based sqlbase and parameter properties :: need to be updated
			if (sqlbase != null && ppkv != null){
				for(int j=0;j<sqllist.size();j++){
					String tmpstr=sqllist.get(j)+"";
				sql = ps.complete(tmpstr, ppkv);
				sqllist.set(j, sql);
				sql=null;
				}
				
			}
			else
				ERROR_INFO.add("SQL ��������sqlbase �� ҳ���������Ϊ�ա���쿴sqlbase, ��ֵΪ��"+sqlbase+" ��������Ϊ��"+ppkv+". �������㣬�޷�����sql���");			
			
			
			System.out.println("2----------------------");

			XADBHandler dbh = new XADBHandler();
			try {
				
					for(int j=0;j<sqllist.size();j++){
						String tmpstr=sqllist.get(j)+"";
						System.out.println("3----------"+tmpstr);
						dbh.cmfunction(tmpstr);
					}
					
				
				
				
				
				dbh.StartXA();
				dbh = null;
			} catch (Exception se) {
				se.printStackTrace();
				ERROR_INFO.add("���ݿ�XA����������쿴��־�ļ�");	
			} finally {
				if (dbh != null) dbh.close();
			}

		
		if(ERROR_INFO.size()>0 ) response.setRResult("ERROR_INFO", ERROR_INFO);
		
		//ActionForward acf=conf.getActionForward(actionname);

		return conf.getActionFword();

	}

}