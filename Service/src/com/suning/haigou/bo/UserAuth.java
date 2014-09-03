/*
 * 创建日期 2006-12-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.suning.haigou.bo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

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

public class UserAuth  {

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

	MGDBHandler mgdh = new MGDBHandler();





	
	// show shopping cart for customer
	public DBObject auth(DBObject bdo_in_o) throws Exception {

		DBObject dboresult = null;

		
			BasicDBObject query_cond = new BasicDBObject();
			DBObject dboresult_lg = null;

			String login_id = null, login_pwd = null;
			if (bdo_in_o.get(UserAuth.UID_LG) != null)
				login_id = bdo_in_o.get(UserAuth.UID_LG).toString();
			query_cond.put(UserAuth.UID_LG, login_id);
			if (bdo_in_o.get(UserAuth.PWD_LG) != null)
				login_pwd = bdo_in_o.get(UserAuth.PWD_LG).toString();
			query_cond.put(UserAuth.PWD_LG,  MDigest.sha1(login_pwd));

			
			DBCollection coll_lg = mgdh.getCollection("User_Certification");
			dboresult_lg = coll_lg.findOne(query_cond);

			String uid = null;
			if (dboresult_lg != null && dboresult_lg.get("user_id") != null)
				uid = dboresult_lg.get("user_id").toString();
			
			UserProfile up=new UserProfile();
			dboresult=up.getUserProfile(new BasicDBObject("user_id", uid));

			if (dboresult != null) {
				dboresult.removeField("_id");
				dboresult.removeField("reg_time");
				dboresult.removeField("address");

			} else
				throw new Exception("user id or pwd wrong");

	
		if (dboresult != null)
			return new BasicDBObject(UserAuth.User_E, dboresult);
		else
			return dboresult;

	}

	// server time
	public DBObject addAuthToken(BasicDBObject new_obj) throws Exception {
		//DBObject dboresult = null;

		DBCollection coll = mgdh.getCollection("User_Certification");
		BasicDBObject index_obj = new BasicDBObject(UserAuth.UID_LG, new_obj.getString(UserAuth.UID_LG));
		
		String pwd=new_obj.getString(UserAuth.PWD_LG);
		
		new_obj.remove(UserAuth.PWD_LG);
		
		new_obj.put(UserAuth.PWD_LG, MDigest.sha1(pwd));
		
		DBObject dboresult_auth=coll.findOne(index_obj);
		if(dboresult_auth!=null) throw(new Exception("Such ID has been used"));
		else coll.insert(new_obj);
		dboresult_auth=coll.findOne(index_obj);
		dboresult_auth.removeField(UserAuth.PWD_LG);
		dboresult_auth.removeField("_id");
		return dboresult_auth;	
	}
	
	// server time
	public DBObject changePWD(BasicDBObject new_obj) throws Exception {
		//DBObject dboresult = null;
		DBCollection coll = mgdh.getCollection("User_Certification");
		BasicDBObject index_obj = new BasicDBObject(UserAuth.UID_LG, new_obj.getString(UserAuth.UID_LG));
		String pwd=new_obj.getString(UserAuth.PWD_LG);
		
		
		pwd=MDigest.sha1(pwd);
		index_obj.append(UserAuth.PWD_LG, pwd);
		
		String pwd_new=new_obj.getString(UserAuth.PWD_LG_NEW);
		if(pwd_new==null) throw new Exception("Please input new pwd");
		else pwd_new=MDigest.sha1(pwd_new);
		
		BasicDBObject setobj=new BasicDBObject("$set",new BasicDBObject(UserAuth.PWD_LG,pwd_new));
		
		System.out.println(index_obj.toString());
		System.out.println(setobj.toString());
		coll.update(index_obj,setobj);
		
		index_obj.remove(UserAuth.PWD_LG);
		
		DBObject dboresult_auth=coll.findOne(index_obj);
		dboresult_auth.removeField(UserAuth.PWD_LG);
		dboresult_auth.removeField("_id");
		return dboresult_auth;	
	}
	


	public static void main(String[] args) {

		try {

			BasicDBObject bdo = new BasicDBObject(); // .append("login_pwd",
														// "abel1234");
			//bdo.append("login_id", "abeljxwang@hotmail.com").append("login_pwd", "abel1234").append("user_id", "abeljxwang"); //.append("login_pwd_new", "abeljxwang");
			bdo.append("user_id", "abeljxwang"); //.append("login_pwd_new", "abeljxwang");

			UserAuth sc = new UserAuth();
			//sc.removeUser("abeljxwang");
			// System.out.println(test_in.toString());
			// sc.addAuthToken(bdo);

			 //DBCollection coll_lg = sc.getAuthCollection();
			 //System.out.println(coll_lg.findOne(bdo));
			 //coll_lg.remove(bdo);

			//sc.addAuthToken("abelwang",  MDigest.sha1("abel1234"), "abeljxwng");
			//sc.addAuthToken("13601291207",  MDigest.sha1("abel1234"), "abeljxwng");
			// add a user
			BasicDBObject user = new BasicDBObject();
			user.append("user_id", "abeljxwang").append("firstname", "Abel")
					.append("lastname", "Wang").append("sex", "M")
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
			//sc.resetPWD(bdo);
			//System.out.println(sc.auth(bdo));
		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}
