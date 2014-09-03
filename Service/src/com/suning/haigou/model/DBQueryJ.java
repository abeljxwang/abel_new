/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.model;

//import com.ibm.ais.base.message.MsgObject;

import java.util.Properties;
import java.util.Vector;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.db.*;
import org.abel.service.base.common.*;
import org.abel.service.jsonengine.JSONModelBase;
import org.abel.service.soapengine.core.ServiceMessage;
import org.json.simple.*;

/**
 * @author administrator
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class DBQueryJ extends JSONModelBase {

	public JSONObject execute(OperationCfg opcfg, JSONObject mo) throws Exception {

		JSONObject mot = new JSONObject();

		//System.out.println("------------- DBQueryJ-------------------");

		try {
			// System.out.print(":"+"="+ mo.getValue("well_id"));
			//
			//String opname = opcfg.getPropertie("Name");

			// System.out.println(opcfg.getParameter("sqlbase")+"   p: sqlbase");

			Vector<String> ERROR_INFO = new Vector<String>(); // null;

			String sql = null;
			String // sqltable = null,
			sqlbase = null,
			// sqlparas = null,
			sqlfield = null;
			// fieldname = null;
			// sqlcondition = null,
			// fieldlength = null;
			ParameterParser ps = new ParameterParser();
			Properties ppkv = new Properties();

			// basic info for all db process model

			// sqlbase
			if (opcfg.getParameter("sqlbase") != null)
				sqlbase = opcfg.getParameter("sqlbase");

			// sqlfield
			Vector fieldv = new Vector();
			if (opcfg.getParameter("sqlfield") != null) {
				sqlfield = opcfg.getParameter("sqlfield");
				fieldv = ps.getparameters(sqlfield);
				String tmp1 = null;
				for (int k = 0; k < fieldv.size(); k++) {
					tmp1 = fieldv.elementAt(k) + "";
					//System.out.println(tmp1 + ":" + "=" + mo.get(tmp1));
					if (mo.get(tmp1) != null
							&& mo.get(tmp1).toString().trim().length() > 0)
						ppkv.setProperty(tmp1, mo.get(tmp1).toString());
				}

			}

			// sqlfield
			Vector resultkeyv = new Vector();
			//System.out.println("responsekey:");
			if (opcfg.getParameter("responsekey") != null) {
				sqlfield = opcfg.getParameter("responsekey");
				//System.out.println(sqlfield);
				resultkeyv = ps.getparameters(sqlfield);
				//System.out.println(resultkeyv);

			}

			sql = sqlbase;
			// System.out.println(sql+"---------------"+ppkv);
			sql = ps.complete(sql, ppkv);
			//System.out.println(sql + "---------------" + ppkv);

			DBHandler dbh = new DBHandler();
			String ktmp = null;
			try {
				EIPResultSet eresult = dbh.query(sql, 20);
				//System.out.println(eresult.size());
				dbh.close();
				dbh = null;

				if (eresult.size() > 1) {
					String listkey = "list";
					JSONArray list1 = new JSONArray();

					int snlist = 0;

					while (eresult.next()) {
						JSONObject record = new JSONObject();
						for (int k = 0; k < resultkeyv.size(); k++) {
							if (resultkeyv.elementAt(k) != null)
								ktmp = resultkeyv.elementAt(k).toString();
							if (eresult.getString(k + 1) != null)
								record.put(ktmp, eresult.getString(k + 1));

						}
						list1.add(record);

					}
					mot.put(listkey, list1);
				} else if (eresult.size() == 1) {

					while (eresult.next()) {

						for (int k = 0; k < resultkeyv.size(); k++) {
							if (resultkeyv.elementAt(k) != null)
								ktmp = resultkeyv.elementAt(k).toString();
							if (eresult.getString(k + 1) != null)
								mot.put(ktmp, eresult.getString(k + 1));

						}

					}

				}

				// prepareResult(mot,eresult);
			} catch (Exception se) {
				se.printStackTrace();
				ERROR_INFO.add("数据库查询操作处理错误");

			} finally {
				if (dbh != null)
					dbh.close();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return mot;

	}

	void prepareResult(ServiceMessage mo, EIPResultSet eresult) {

	}

}
