/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.model;

import java.net.UnknownHostException;
import java.util.HashMap;

import org.abel.service.base.common.MDigest;
import org.abel.service.base.config.OperationCfg;
import org.abel.service.jsonengine.JSONEessentialP;
import org.abel.service.jsonengine.JSONModelBase;
import org.json.simple.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.suning.haigou.bo.UserAuth;
import com.suning.haigou.bo.UserProfile;

/**
 * @author Abel
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation. login { login_id login_pwd }
 * 
 *         User { user_id * first_name last_name sex prefix * * email address
 *         grade_ref order_ref shopping_cart_ref }
 * 
 */

public class MUserAuth extends JSONModelBase {

	final static String UID_LG = "login_id";
	final static String PWD_LG = "login_pwd";
	final static String PWD_LG_NEW = "login_pwd_new";
	final static String ITEM_LIST = "SCItems";
	final static String ITEM_PROD_ID = "product_id";
	final static String ITEM_QNTY = "quantity";
	final static String ITEM_PRC = "price_added";
	final static String ITEM_TIME_ADDED = "time_added";
	final static String ITEM_SOURCE = "source";
	final static String USER_ID = "user_id";
	final static String User_E = "user";

	MongoClient mongoClient = null;

	public JSONObject execute(OperationCfg opcfg, JSONObject mo_in)
			throws Exception {

		JSONObject mot = new JSONObject(); // message for output

		String opname = opcfg.getPropertie("Name");
		logger.debug(""+opname + " Action name from the config");
		// System.out.println(opname + " Action name from the config");
		// get collection name from config
		String collection = null; // collection name from config
		if (opcfg.getParameter("collection") != null)
			collection = opcfg.getParameter("collection");

		DBObject dbo_result = null;

		HashMap hm_in = (HashMap) mo_in;
		BasicDBObject bdo_in = new BasicDBObject();
		bdo_in.putAll(hm_in);

		UserAuth ua = new UserAuth();

		if (opname.equalsIgnoreCase("verify"))
			dbo_result = ua.auth(bdo_in);
		else if (opname.equalsIgnoreCase("addAuthToken")) {
			dbo_result = ua.addAuthToken(bdo_in);

		} else if (opname.equalsIgnoreCase("changePWD")) {
			dbo_result = ua.changePWD(bdo_in);

		}

		if (dbo_result != null)
			mot.putAll(dbo_result.toMap());

		// System.out.println(mot.toJSONString());

		return mot;

	}

	public DBCollection getAuthCollection() throws UnknownHostException {
		String dbhost = "192.168.1.234", dbname = "haigou";
		if (JSONEessentialP.get("server_ip") != null)
			dbhost = JSONEessentialP.get("server_ip");
		if (JSONEessentialP.get("database") != null)
			dbname = JSONEessentialP.get("database");
		mongoClient = new MongoClient(dbhost, 27017);
		DB db = mongoClient.getDB(dbname);

		DBCollection coll = db.getCollection("User_Certification");
		return coll;
	}

	// show shopping cart for customer
	public DBObject auth(DBObject bdo_in_o) throws Exception {

		DBObject dboresult = null;

		BasicDBObject query_cond = new BasicDBObject();
		DBObject dboresult_lg = null;

		String login_id = null, login_pwd = null;
		if (bdo_in_o.get(MUserAuth.UID_LG) != null) 
			login_id = bdo_in_o.get(MUserAuth.UID_LG).toString();
		query_cond.put(MUserAuth.UID_LG, login_id);
		
		if (bdo_in_o.get(MUserAuth.PWD_LG) != null)
			login_pwd = bdo_in_o.get(MUserAuth.PWD_LG).toString();
		query_cond.put(MUserAuth.PWD_LG, MDigest.sha1(login_pwd));

		DBCollection coll_lg = getAuthCollection();
		dboresult_lg = coll_lg.findOne(query_cond);

		String uid = null;
		if (dboresult_lg != null && dboresult_lg.get("user_id") != null)
			uid = dboresult_lg.get("user_id").toString();
		
		UserProfile up = new UserProfile();
		dboresult=up.getUserProfile(new BasicDBObject("user_id", uid));

		if (dboresult != null) {
			dboresult.removeField("_id");
			dboresult.removeField("reg_time");
			dboresult.removeField("address");

		} else
			throw new Exception("user id or pwd wrong");
		
		BasicDBObject rtbo=null;
		
		if (dboresult != null)
			rtbo= new BasicDBObject(MUserAuth.User_E, dboresult);


			return rtbo;

	}

}
