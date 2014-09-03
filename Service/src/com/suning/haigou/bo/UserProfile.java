/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.bo;


import java.util.ArrayList;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;



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

public class UserProfile  {

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

	MGDBHandler mgdh = new MGDBHandler();

	



	// show shopping cart for customer
	public DBObject getUserProfile(DBObject bdo_in) throws Exception {

		DBObject dboresult = null;

		String uid = null;
		if (bdo_in != null && bdo_in.get("user_id") != null)
			uid = bdo_in.get("user_id").toString();
		

		DBCollection coll_user = mgdh.getCollection("UserProfile");
		dboresult = coll_user.findOne(new BasicDBObject("user_id", uid));

		if (dboresult != null) {
			dboresult.removeField("_id");
			dboresult.removeField("reg_time");

		} else
			throw new Exception("User info is not ready");

		
		

		return dboresult;

	}

	public DBObject addUser(BasicDBObject user) throws Exception {

		
		DBObject dboresult = null;

		DBCollection coll = mgdh.getCollection("UserProfile");

        System.out.println(user.toString());
		coll.insert(user);
		dboresult = getUserProfile(user);
		
		

		return dboresult;
	}
	
	public DBObject updateUser(BasicDBObject user) throws Exception {

		DBCollection coll = mgdh.getCollection("UserProfile");

		DBObject dboresult = null;

		
		
		String uid = null;
		if (user != null && user.get("user_id") != null)
			uid = user.get("user_id").toString();
		BasicDBObject index_base = new BasicDBObject("user_id", uid);
		BasicDBObject setobj=new BasicDBObject("$set",user);
		
		coll.update(index_base,setobj);
		dboresult = getUserProfile(user);
		return dboresult;
	}
	
	

	public DBObject addUserAddress(BasicDBObject user) throws Exception {

		DBCollection coll;
		DBObject dboresult = null;

		BasicDBObject query = new BasicDBObject();
		query.append(UserProfile.USER_ID, user.get(UserProfile.USER_ID));

		// BasicDBObject addresslist = new BasicDBObject();
		ArrayList addresslist = (ArrayList) user.get("address");
		coll = mgdh.getCollection("UserProfile");


		for (int l = 0; l < addresslist.size(); l++) {
			DBObject item = (DBObject) addresslist.get(l);

			BasicDBObject index_obj = new BasicDBObject("address.abbreviation",
					item.get("abbreviation")).append(UserProfile.USER_ID,
					user.get(UserProfile.USER_ID));

			WriteResult aaa = coll.update(index_obj, new BasicDBObject("$set",
					new BasicDBObject("address.$", item)));
			if (aaa.getN() == 0)
				coll.update(query, new BasicDBObject("$push",
						new BasicDBObject("address", item)));

		}

		dboresult = getUserProfile(user);

		return dboresult;

	}
	
	
	public DBObject removeUserAddress(BasicDBObject user) throws Exception {

		DBCollection coll;
		DBObject dboresult = null;

		BasicDBObject query = new BasicDBObject();
		query.append(UserProfile.USER_ID, user.get(UserProfile.USER_ID));

		// BasicDBObject addresslist = new BasicDBObject();
		ArrayList addresslist = (ArrayList) user.get("address");
		coll = mgdh.getCollection("UserProfile");


		for (int l = 0; l < addresslist.size(); l++) {
			DBObject item = (DBObject) addresslist.get(l);

			BasicDBObject index_obj = new BasicDBObject("address.abbreviation",
					item.get("abbreviation")).append(UserProfile.USER_ID,
					user.get(UserProfile.USER_ID));
			
			
			WriteResult aaa = coll.update(index_obj, new BasicDBObject("$pull",
					new BasicDBObject("address", item)));
			
			

		}

		dboresult = getUserProfile(user);

		return dboresult;

	}
	

	public static void main(String[] args) {

		try {

			UserProfile sc = new UserProfile();
			BasicDBObject user_q = new BasicDBObject().append("user_id", "abeljxwang");
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
			BasicDBObject user = new BasicDBObject();
			user.append("user_id", "abeljxwang").append("firstname", "AbelJX")
					.append("lastname", "Wang1").append("sex", "M")
					.append("prefix", "Mr.")
					.append("email", "abeljxwang@hotmail.com");
			ArrayList add_list = new ArrayList();
			add_list.add(new BasicDBObject("city", "Palo Alto")
					.append("zip", "94304").append("state", "CA")
					.append("country", "USA")
					.append("road", "1661 Page Mill Rd")
					.append("abbreviation", "Office"));
			add_list.add(new BasicDBObject("city", "San Ramon")
					.append("zip", "94582").append("state", "CA")
					.append("country", "USA")
					.append("road", "1324 Canyon Side Ave")
					.append("abbreviation", "Home"));
			user.append("address", add_list);
			user.append("reg_time", new Date());

			//sc.addUser(user);
			 //sc.addUserAddress(user);
			 //sc.updateUser(user);
			System.out.println(sc.getUserProfile(user_q));

		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}
