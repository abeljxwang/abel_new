/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.bo;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.abel.service.jsonengine.JSONEessentialP;
import org.json.simple.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 * @author Abel
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class ShoppingCart  {

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

	MGDBHandler mgdh=new MGDBHandler();

	



	// show shopping cart for customer
	public DBObject show(DBObject bdo_in_o) throws Exception {

		DBObject dboresult = null;

		HashMap hmp_in = (HashMap) bdo_in_o.get(ShoppingCart.SC_E);
		BasicDBObject bdo_in = new BasicDBObject();
		bdo_in.putAll(hmp_in);
		DBCollection coll = mgdh.getCollection("shoppingCart");
		BasicDBObject query_cond = new BasicDBObject();

		String sc_id = null;
		if (bdo_in.get(ShoppingCart.SC_ID) != null)
			sc_id = bdo_in.get(ShoppingCart.SC_ID).toString();
		query_cond.put(ShoppingCart.SC_ID, sc_id);

		
			dboresult = coll.findOne(query_cond);
			if (dboresult != null)
				dboresult.removeField("_id");
			if (dboresult != null)
				dboresult.removeField("ShoppingCart.SC_Items.time_added");

		

		if (dboresult != null)

			return new BasicDBObject(ShoppingCart.SC_E, dboresult);
		else
			return null;

	}

	// show shopping cart for customer
	public DBObject showbyID(String scid) throws Exception {

		DBCollection coll = mgdh.getCollection("shoppingCart");
		BasicDBObject query_cond = new BasicDBObject();
		if (scid != null)
			query_cond.put(ShoppingCart.SC_ID, scid);
		DBObject dboresult = null;

		dboresult = coll.findOne(query_cond);
		if (dboresult != null)
			dboresult.removeField("_id");

		if (dboresult != null)
			return new BasicDBObject(ShoppingCart.SC_E, dboresult);
		else
			return null;

	}

	// add some terms into the shopping cart from customer, set the time with
	// server time
	public DBObject stdadd(DBObject bdo_in_o) throws Exception {

		DBCollection coll;
		DBObject dboresult = null;
		BasicDBObject bdo_in = new BasicDBObject();

		System.out.println("--->>>" + bdo_in_o.toString());
		if (bdo_in_o.get(ShoppingCart.SC_E) != null) {
			HashMap hm_sc = (HashMap) bdo_in_o.get("ShoppingCart");
			System.out.println("--->>>" + hm_sc.toString());
			bdo_in.putAll(hm_sc);
		}

		

			// check exit or not
			DBObject dboresult_e = show(bdo_in_o);

			if (dboresult_e != null) // Shopping Cart done
			{
				coll = mgdh.getCollection("shoppingCart");
				// add a new product or update a product quantity/price
				BasicDBObject index_base = new BasicDBObject("sc_id",
						bdo_in.get("sc_id"));
				// BasicDBObject itemObject = null;

				ArrayList item_list = new ArrayList();
				item_list = (ArrayList) bdo_in.get(ShoppingCart.ITEM_LIST);
				for (int l = 0; l < item_list.size(); l++) {
					// DBObject item = (DBObject) item_list.get(l);
					HashMap hm_item = (HashMap) item_list.get(l);
					BasicDBObject item = new BasicDBObject();
					item.putAll(hm_item);
					BasicDBObject index_obj = new BasicDBObject(
							"SC_Items.product_id",
							item.get(ShoppingCart.ITEM_PROD_ID)).append(
							"sc_id", bdo_in.get(ShoppingCart.SC_ID));
					if (item.get(ShoppingCart.ITEM_TIME_ADDED) != null)
						item.removeField(ShoppingCart.ITEM_TIME_ADDED);

					item.put(ShoppingCart.ITEM_TIME_ADDED, new Date());

					if (item.get(ShoppingCart.ITEM_SOURCE) != null)
						item.removeField(ShoppingCart.ITEM_SOURCE);
					item.put(ShoppingCart.ITEM_SOURCE, "oversea_shopping");
					WriteResult aaa = coll.update(index_obj, new BasicDBObject(
							"$set", new BasicDBObject("SC_Items.$", item)));
					// System.out.println(aaa.getN() + " : " + aaa.toString());
					if (aaa.getN() == 0)
						coll.update(index_base,
								new BasicDBObject("$push", new BasicDBObject(
										ShoppingCart.ITEM_LIST, item)));

				}
			}

			else {
				coll = mgdh.getCollection("shoppingCart");

				// bdo_in.put("time_created", new Date());
				ArrayList item_list = new ArrayList();
				item_list = (ArrayList) bdo_in.get(ShoppingCart.ITEM_LIST);
				for (int l = 0; l < item_list.size(); l++) {
					HashMap hm_item = (HashMap) item_list.get(l);
					BasicDBObject item = new BasicDBObject();
					item.putAll(hm_item);
					if (item.get(ShoppingCart.ITEM_TIME_ADDED) != null)
						item.removeField(ShoppingCart.ITEM_TIME_ADDED);

					item.put(ShoppingCart.ITEM_TIME_ADDED, new Date());

					if (item.get(ShoppingCart.ITEM_SOURCE) != null)
						item.removeField(ShoppingCart.ITEM_SOURCE);
					item.put(ShoppingCart.ITEM_SOURCE, "oversea_shopping");

					item_list.set(l, item);
				}
				WriteResult resultstatus = coll.save(bdo_in);

			}

			dboresult = show(bdo_in_o);

		

		return dboresult;
	}

	public DBObject changeQnty(DBObject bdo_in_o)
			throws Exception {
		DBObject dboresult = null;
		DBCollection coll = mgdh.getCollection("shoppingCart");
		ArrayList item_list = new ArrayList();

		HashMap bdo_in_item = (HashMap) bdo_in_o.get(ShoppingCart.SC_E);
		BasicDBObject bdo_in = new BasicDBObject();
		bdo_in.putAll(bdo_in_item);

		String sc_id = bdo_in.get(ShoppingCart.SC_ID).toString();
		// BasicDBObject up_id = new BasicDBObject(MGShoppingCart.SC_ID, sc_id);

		item_list = (ArrayList) bdo_in.get(ShoppingCart.ITEM_LIST);
		for (int l = 0; l < item_list.size(); l++) {
			HashMap hm_item = (HashMap) item_list.get(l);
			BasicDBObject item = new BasicDBObject();
			item.putAll(hm_item);

			BasicDBObject up_id_item = new BasicDBObject("SC_Items.product_id",
					item.get(ShoppingCart.ITEM_PROD_ID)).append(
					ShoppingCart.SC_ID, sc_id);
			BasicDBObject quantity_doc = new BasicDBObject(
					"SC_Items.$.quantity", item.get(ShoppingCart.ITEM_QNTY));
			BasicDBObject update = new BasicDBObject();
			update.put("$set", quantity_doc);

			WriteResult aaa = coll.update(up_id_item, update);

		}

		dboresult = show(bdo_in_o);
		return dboresult;

	}

	public DBObject removeItem(DBObject bdo_in_o)
			throws Exception {

		DBObject dboresult = null;
		DBCollection coll = mgdh.getCollection("shoppingCart");
		ArrayList item_list = new ArrayList();

		HashMap bdo_in_item = (HashMap) bdo_in_o.get(ShoppingCart.SC_E);
		BasicDBObject bdo_in = new BasicDBObject();
		bdo_in.putAll(bdo_in_item);

		BasicDBObject update = new BasicDBObject();

		String sc_id = bdo_in.get(ShoppingCart.SC_ID).toString();
		BasicDBObject up_id = new BasicDBObject(ShoppingCart.SC_ID, sc_id);

		item_list = (ArrayList) bdo_in.get(ShoppingCart.ITEM_LIST);
		for (int l = 0; l < item_list.size(); l++) {

			HashMap hm_item = (HashMap) item_list.get(l);
			BasicDBObject item = new BasicDBObject();
			item.putAll(hm_item);

			BasicDBObject up_id_item = new BasicDBObject(
					ShoppingCart.ITEM_PROD_ID,
					item.get(ShoppingCart.ITEM_PROD_ID));
			update.put("$pull", new BasicDBObject(ShoppingCart.ITEM_LIST,
					up_id_item));

			WriteResult aaa = coll.update(up_id, update);
		}

		dboresult = show(bdo_in_o);
		return dboresult;
	}



	public DBObject removeSC(String sc_id) throws Exception {

		DBObject dboresult = null;
		DBCollection coll = mgdh.getCollection("shoppingCart");

		BasicDBObject rm_id = new BasicDBObject(ShoppingCart.SC_ID, sc_id);
		coll.remove(rm_id);

		dboresult = show(rm_id);
		return dboresult;
	}

	public static void main(String[] args) {

		try {

			ArrayList list = new ArrayList();
			list.add(new BasicDBObject("product_id", "0682785_3")
					.append("quantity", 12).append("time_added", new Date())
					.append("price_added", 98.99).append("source", "S001"));

			list.add(new BasicDBObject("product_id", "06827465").append(
					"quantity", 12).append("price_added", 98.99));

			BasicDBObject doc = new BasicDBObject("sc_id",
					"abeljxwang@hotmail.com").append("SC_Items", list);

			BasicDBObject test_in = new BasicDBObject(ShoppingCart.SC_E, doc);
			// System.out.println(test_in.toString());

			JSONObject respJSONMsg = new JSONObject();
			respJSONMsg.putAll(test_in.toMap());
			// System.out.println(respJSONMsg.toJSONString());

			ShoppingCart sc = new ShoppingCart();

			// for add
			// System.out.println(sc.removeSC("abeljxwang@hotmail.com"));
			// System.out.println(sc.stdadd(test_in).toString());
			// System.out.println(test_in.toString());
			System.out.println(sc.changeQnty(test_in).toString());
			// System.out.println(sc.show(test_in).toString());
			// System.out.println(sc.showbyID("abeljxwang@hotmail.com").toString());

		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}
