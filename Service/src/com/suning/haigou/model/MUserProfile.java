/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.model;


import java.util.HashMap;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.jsonengine.JSONModelBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import com.suning.haigou.bo.UserProfile;

import org.json.simple.JSONObject;

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

public class MUserProfile extends JSONModelBase {

	final static String UID_LG = "login_id";
	final static String PWD_LG = "login_pwd";
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
		// Vector ERROR_INFO = new Vector(); // for error message

		String opname = opcfg.getPropertie("Name");
		// System.out.println(opname + " Action name from the config");
		// get collection name from config
		String collection = null; // collection name from config
		if (opcfg.getParameter("collection") != null)
			collection = opcfg.getParameter("collection");

		DBObject dbo_result = null;

		HashMap hm_in = (HashMap) mo_in;
		BasicDBObject bdo_in = new BasicDBObject();
		bdo_in.putAll(hm_in);
		
		BasicDBObject bdo_user = new BasicDBObject();
		HashMap hm_user= null;
		if(mo_in.get("user")!=null) {
			hm_user=(JSONObject)mo_in.get("user");
		
		bdo_user.putAll(hm_user);
		}

		UserProfile up=new UserProfile();
		
		if (opname.equalsIgnoreCase("show"))
			dbo_result = up.getUserProfile(bdo_in);
		else if (opname.equalsIgnoreCase("creatUser"))
		{			
			dbo_result = up.addUser(bdo_user);
		}
		else if (opname.equalsIgnoreCase("register"))
			dbo_result = up.addUser(bdo_user);
		else if (opname.equalsIgnoreCase("addAddress"))
			dbo_result = up.addUserAddress(bdo_user);
		else if (opname.equalsIgnoreCase("changeAddress"))
			dbo_result = up.addUserAddress(bdo_user);
		else if (opname.equalsIgnoreCase("removeAddress"))
			dbo_result = up.removeUserAddress(bdo_user);
		else if (opname.equalsIgnoreCase("updateUser"))
			dbo_result = up.updateUser(bdo_user);

		if (dbo_result != null)
			mot.putAll(dbo_result.toMap());

		// System.out.println(mot.toJSONString());

		return mot;

	}



}
