/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.model;


import java.util.HashMap;
import java.util.Vector;

import org.abel.service.base.config.OperationCfg;
import org.abel.service.jsonengine.JSONModelBase;

import org.json.simple.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import com.suning.haigou.bo.ShoppingCart;

/**
 * @author Abel
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class MGShoppingCart extends JSONModelBase {

	final static String SC_ID = "sc_id";
	final static String SC_TIME_CREATed = "time_created";
	final static String ITEM_LIST = "SC_Items";
	final static String ITEM_PROD_ID = "product_id";
	final static String ITEM_QNTY = "quantity";
	final static String ITEM_PRC = "price_added";
	final static String ITEM_TIME_ADDED = "time_added";
	final static String ITEM_SOURCE = "source";
	final static String USER_ID = "user_id";
	final static String SC_E = "ShoppingCart";

	MongoClient mongoClient = null;

	public JSONObject execute(OperationCfg opcfg, JSONObject mo_in)
			throws Exception {

		JSONObject mot = new JSONObject(); // message for output
		Vector ERROR_INFO = new Vector(); // for error message
		String collection = null; // collection name from config
		ShoppingCart sc = new ShoppingCart();

		String opname = opcfg.getPropertie("Name");
		// System.out.println(opname + " Action name from the config");

		// get collection name from config
		if (opcfg.getParameter("collection") != null)
			collection = opcfg.getParameter("collection");

		DBObject dbo_result = null;

		HashMap hm_in = (HashMap) mo_in;
		BasicDBObject bdo_in = new BasicDBObject();
		bdo_in.putAll(hm_in);
		// System.out.println("::::" + bdo_in.toString());

		// System.out.println(bdo_in.get("sc_id").toString());

		if (opname.equalsIgnoreCase("show"))
			dbo_result = sc.showbyID(bdo_in.get("sc_id").toString());
		else if (opname.equalsIgnoreCase("add"))
			dbo_result = sc.stdadd(bdo_in);
		else if (opname.equalsIgnoreCase("removeItem"))
			dbo_result = sc.removeItem(bdo_in);
		else if (opname.equalsIgnoreCase("changeQnty"))
			dbo_result = sc.changeQnty(bdo_in);

		// System.out.println("::::"+dbo_result.toString());

		if (dbo_result != null)
			mot.putAll(dbo_result.toMap());

		//System.out.println(mot.toJSONString());
		return mot;

	}

}
