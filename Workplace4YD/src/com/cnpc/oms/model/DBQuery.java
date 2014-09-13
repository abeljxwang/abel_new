/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.cnpc.oms.model;

import org.abel.webapp.configure.ActionCfg;
import org.abel.webapp.configure.ActionForward;
import org.abel.webapp.model.ModelBase;
import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;
import org.abel.webapp.db.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import java.util.Vector;
/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DBQuery extends ModelBase  {


	public ActionForward execute(
		Request request,
		Response response,
		ActionCfg conf) {
		
		
		String sql = null;
		String sqltable = null;
		System.out.println("----------------Test Start----------");
		//List sqlcfgnames = null;

		//String actionname = request.getActionName();
		
		//sqltable
		if (conf.getParameter("sqltable") != null) {
			sqltable = conf.getParameter("sqltable");
		}

		if (request.getParameter("tbname") != null)
			sqltable = request.getParameter("tbname");

		sql = "select * from "+sqltable+"";
		//System.out.println(sql);
		Vector metadata = new Vector();

		DBConnection dbc = null;
		try {
			dbc = new DBConnection();
			Statement stmt = dbc.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData mdd = rs.getMetaData();
			int ccount = mdd.getColumnCount();
			System.out.println(mdd.toString()+"----------------");
			String value="";
			while(rs.next()){
			
			for (int i = 1; i <= ccount; i++) {
				Vector onem = new Vector();

				onem.add(value+rs.getString(i));
				
				
			}
			}
			dbc.close();
			dbc = null;
			response.setRResult("rsresult", metadata);
			//System.out.println(metadata);
			response.setRResult("tbname", sqltable);

		} catch (Exception se) {
			se.printStackTrace();

		} finally {
			try {
				if (dbc != null)
					dbc.close();
			} catch (Exception sse) {
			}
		}
		return conf.getActionFword();

	}

}