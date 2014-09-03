/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.model;

//import com.ibm.ais.base.message.MsgObject;

import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.base.db.*;
import org.abel.service.base.common.*;
import org.abel.service.jsonengine.JSONEessentialP;
import org.abel.service.jsonengine.JSONModelBase;
import org.abel.service.soapengine.core.SOAModelBase;
import org.abel.service.soapengine.core.ServiceMessage;
import org.json.simple.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

/**
 * @author administrator
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class MGDBQueryJ extends JSONModelBase {

	public JSONObject execute(OperationCfg opcfg, JSONObject mo) {

		JSONObject mot = new JSONObject();

		try {

			String opname = opcfg.getPropertie("Name");

			Vector ERROR_INFO = new Vector(); // null;

			String sql = null;
			String sqlbase = null, sqlfield = null;

			ParameterParser ps = new ParameterParser();
			Properties ppkv = new Properties();

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
					if (mo.get(tmp1) != null
							&& mo.get(tmp1).toString().trim().length() > 0)
						ppkv.setProperty(tmp1, mo.get(tmp1).toString());
				}

			}

			// sqlfield
			Vector resultkeyv = new Vector();
			if (opcfg.getParameter("responsekey") != null) {
				sqlfield = opcfg.getParameter("responsekey");
				resultkeyv = ps.getparameters(sqlfield);
			}

			sql = sqlbase;
			sql = ps.complete(sql, ppkv);

			DBHandler dbh = new DBHandler();
			String ktmp = null;
			try {
				// To connect to mongodb server
				MongoClient mongoClient = new MongoClient("localhost", 27017);
				DB db = mongoClient.getDB("test");
				//System.out.println("Connect to database successfully");

				DBCollection coll = db.getCollection("testCollection");
				DBCursor cursor;
				BasicDBObject query = new BasicDBObject("name", "MongoDB");

				cursor = coll.find(query);
				try {
					while (cursor.hasNext()) {
						DBObject dbo = cursor.next();
						mot.putAll(dbo.toMap());
					}
				} finally {
					cursor.close();
				}

				// prepareResult(mot,eresult);
			} catch (Exception se) {
				se.printStackTrace();
				ERROR_INFO.add("数据库查询操作处理错误");

			} finally {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mot;

	}

	void prepareResult(ServiceMessage mo, EIPResultSet eresult) {

	}

}


/*
	String dbhost = "192.168.1.234", dbname = "haigou";
	if (JSONEessentialP.get("server_ip") != null)
		dbhost = JSONEessentialP.get("server_ip");
	if (JSONEessentialP.get("database") != null)
		dbname = JSONEessentialP.get("database");
	mongoClient = new MongoClient(dbhost, 27017);
	DB db = mongoClient.getDB(dbname);

	DBCollection coll = db.getCollection("UserProfile");*/

