package org.abel.webapp.commodel;

import org.abel.webapp.configure.ActionCfg;
import org.abel.webapp.configure.ActionForward;

import org.abel.webapp.view.Request;
import org.abel.webapp.view.Response;
import org.abel.webapp.db.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
/**
 * @author administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CmddbQMD {


	/**
	 * @see com.kmtc.eip.model.ModelBase#execute(Request, Response, Config)
	 */
	public ActionForward execute(
		Request request,
		Response response,
		ActionCfg conf) {
		String sql = null;
		String sqltable = null;

		//List sqlcfgnames = null;

		//String actionname = request.getActionName();
		//sqltable
		if (conf.getParameter("sqltable") != null) {
			sqltable = conf.getParameter("sqltable");
		}

		if (request.getParameter("tbname") != null)
			sqltable = request.getParameter("tbname");

		sql = "select * from " + sqltable + " where 1=1 AND rownum=1";
		//System.out.println(sql);
		Vector metadata = new Vector();

	DBConnection dbc = null;
		try {
			dbc = new DBConnection();
			Statement stmt = dbc.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData mdd = rs.getMetaData();
			int ccount = mdd.getColumnCount();

			for (int i = 1; i <= ccount; i++) {
				Properties onem = new Properties();
				onem.setProperty("Lable", mdd.getColumnLabel(i));
				onem.setProperty("ClassName", mdd.getColumnClassName(i));
				onem.setProperty("Name", mdd.getColumnName(i));
				onem.setProperty("Type", mdd.getColumnTypeName(i));
				onem.setProperty("Precision", "" + mdd.getPrecision(i));
				onem.setProperty("SchemaName", mdd.getSchemaName(i));
				onem.setProperty("Nullable", "" + mdd.isNullable(i));

				metadata.add(i - 1, onem);

			}
			dbc.close();
			dbc = null;
			response.setRResult("mdresult", metadata);
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