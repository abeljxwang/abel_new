package com.suning.haigou.util;


import org.abel.service.jsonengine.JSONEessentialP;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class PrepareMGDB {
	MongoClient mongoClient = null;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PrepareMGDB newseeting=new PrepareMGDB();
		newseeting.user_dbinit();
		newseeting.shoppingcart_dbinit();
		newseeting.product_dbinit();
	}
	
	
	
	public DB init() throws Exception{
		String dbhost="192.168.1.147", dbname="haigou";
		if(JSONEessentialP.get("server_ip")!=null) dbhost=JSONEessentialP.get("server_ip");
		if(JSONEessentialP.get("database")!=null) dbname=JSONEessentialP.get("database");
		mongoClient = new MongoClient(dbhost, 27017);
		DB db = mongoClient.getDB(dbname);
		return db;
		
	}
	
	public DBCollection getCollection(String collec) throws Exception {
		
		DB db = init();
		
		DBCollection coll = db.getCollection(collec);
		return coll;
	}
	
	public void user_dbinit() throws Exception{
		System.out.println("=: Prepare to set up UserProfile and User_Certification collection= : Starting");
		DB db=init();
		System.out.println("   Clean the old collection:   ===> ");
		DBCollection coll = db.getCollection("UserProfile");
		if(coll!=null) coll.drop();
		coll = db.createCollection("UserProfile",new BasicDBObject("capped",false));
		System.out.println("   new collection(UserProfile) has been set up:   ===> ");
		//DBCollection coll = db.createCollection("mycol");
		
		coll.createIndex(new BasicDBObject("user_id", 1),new BasicDBObject("unique", true));  
		
		coll = db.getCollection("User_Certification");	
		if(coll!=null) coll.drop();
		coll = db.createCollection("User_Certification",new BasicDBObject("capped",false));
		coll.createIndex(new BasicDBObject("login_id", 1),new BasicDBObject("unique", true));  
		System.out.println("   new collection(User_Certification) has been set up:   ===> ");
		//User_Certification
		//UserProfile		
	}
	
	//shoppingCart
	public void shoppingcart_dbinit() throws Exception{
		System.out.println("=: Prepare to set up shoppingCart collection= : Starting");
		DB db=init();
		System.out.println("   Clean the old collection:   ===> ");
		DBCollection coll = db.getCollection("shoppingCart");
		if(coll!=null) coll.drop();
		coll = db.createCollection("shoppingCart",new BasicDBObject("capped",false));
		coll.createIndex(new BasicDBObject("sc_id", 1),new BasicDBObject("unique", true));  
		
		System.out.println("   new collection(shoppingCart) has been set up:   ===> ");

	}
	
	//shoppingCart
	public void product_dbinit() throws Exception{
		System.out.println("=: Prepare to set up Product collection= : Starting");
		DB db=init();
		System.out.println("   Clean the old collection:   ===> ");
		DBCollection coll = db.getCollection("ProductSummary");
		if(coll!=null) coll.drop();
		coll = db.createCollection("ProductSummary",new BasicDBObject("capped",false));
		coll.createIndex(new BasicDBObject("product_id", 1),new BasicDBObject("unique", true));  
		
		System.out.println("   new collection(Product) has been set up:   ===> ");

	}
	
}
