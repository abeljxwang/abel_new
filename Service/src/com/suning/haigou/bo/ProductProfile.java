/*
 * ´´½¨ÈÕÆÚ 2006-12-11
 *
 * ¸ü¸ÄËùÉú³ÉÎÄ¼þÄ£°åÎª
 * ´°¿Ú > Ê×Ñ¡�?î > Java > ´úÂëÉú³É > ´úÂëº�?×¢Ê�?
 */
package com.suning.haigou.bo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;






import org.abel.service.jsonengine.JSONEessentialP;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


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

public class ProductProfile  {

	final static String PROD_ID = "product_id";
	final static String PROD_NAME = "name";
	final static String SKUD_ID = "prod_sku";
	final static String LIST_PRC = "list_price";
	final static String BRAND = "brand";
	final static String User_E = "user";

	MGDBHandler mgdh=new MGDBHandler();
	

	



	// show shopping cart for customer
	public DBObject getProductProfile(DBObject bdo_in) throws Exception {

		DBObject dboresult = null;

		String uid = null;
		if (bdo_in != null && bdo_in.get("product_id") != null)
			uid = bdo_in.get("product_id").toString();
		
		DBCollection coll_user = mgdh.getCollection("ProductSummary");
		dboresult = coll_user.findOne(new BasicDBObject("product_id", uid));

		if (dboresult != null) {
			dboresult.removeField("_id");
			dboresult.removeField("add_time");

		} else
			throw new Exception("Product info is not ready");

		
		

		return dboresult;

	}
	
	
	
	
	// show shopping cart for customer
	//@SuppressWarnings("unchecked")
	public DBObject listProductProfile(DBObject bdo_in) throws Exception {

		BasicDBObject dboresult = new BasicDBObject();
		ArrayList<String> list =  new ArrayList<String>();
		ArrayList<DBObject> list_o =  new ArrayList<DBObject>();
		BasicDBObject inQuery = new BasicDBObject();
		if (bdo_in != null && bdo_in.get("product_id") != null)
			list = (ArrayList)bdo_in.get("product_id");
		
		inQuery.put("product_id", new BasicDBObject("$in", list));
		System.out.println(inQuery.toString());
		DBCollection coll = mgdh.getCollection("ProductSummary");
		DBCursor cursor = coll.find(inQuery);
		
		while(cursor.hasNext()) {
			DBObject dbo=cursor.next();
			dbo.removeField("_id");
			System.out.println(dbo.toString());
			list_o.add(dbo);
			
		}
		cursor.close();
		
		dboresult.append("product",list_o);

		return dboresult;

	}
	
	
	// show shopping cart for customer
		public DBObject getProductProfilebyID(String pid) throws Exception {

			DBObject dboresult = null;

			
			
			DBCollection coll = mgdh.getCollection("ProductSummary");
			dboresult = coll.findOne(new BasicDBObject("product_id", pid));

			if (dboresult != null) {
				dboresult.removeField("_id");
				dboresult.removeField("add_time");

			} else
				throw new Exception("user info is not ready");

			
			

			return dboresult;

		}

		
		
		
		
	public DBObject addProduct(BasicDBObject product) throws Exception {

		DBObject dboresult = null;

		DBCollection coll = mgdh.getCollection("ProductSummary");
		System.out.println(product.toString());
		coll.insert(product);
		dboresult = getProductProfile(product);

		return dboresult;
	}
	
	public DBObject updateProduct(BasicDBObject product) throws Exception {


		DBObject dboresult = null;

		DBCollection coll = mgdh.getCollection("ProductSummary");
		
		String uid = null;
		if (product != null && product.get("product_id") != null)
			uid = product.get("product_idd").toString();
		BasicDBObject index_base = new BasicDBObject("product_id", uid);
		BasicDBObject setobj=new BasicDBObject("$set",product);
		
		coll.update(index_base,setobj);
		dboresult = getProductProfile(product);
		return dboresult;
	}
	
	

	

	public static void main(String[] args) {

		try {

			ProductProfile sc = new ProductProfile();
			BasicDBObject user_q = new BasicDBObject().append("product_id", "5360406297");
			// sc.removeUser("abeljxwang");
			// System.out.println(test_in.toString());

			// DBCollection coll_lg = sc.getAuthCollection();
			// System.out.println(coll_lg.findOne(bdo));
			// coll_lg.remove(bdo);

			// sc.addAuthToken("abelwang", MDigest.sha1("abel1234"),
			// "abeljxwng");
			// sc.addAuthToken("13601291207", MDigest.sha1("abel1234"),
			// "abeljxwng");
			// add a user
			BasicDBObject product = new BasicDBObject();
			product.append("product_id", "5360406297").append("name","name Opus One ")
					.append("prod_sku", "5360406297 ")
					.append("brand", "OPUS")
					.append("summary"," Robert Mondavi Napa Valley 750ml ")
					.append("prod_img_url", "/images/001.jpg  ")
					.append("list_price","19.99  ")					
					.append("description", "1994 Opus One, Red Wine 红酒");
			
			product.append("add_time", new Date());

			sc.addProduct(product);
			 //sc.addUserAddress(user);
			 //sc.updateUser(user);
			System.out.println(sc.getProductProfile(user_q));

		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}
