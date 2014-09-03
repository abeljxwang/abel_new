package com.suning.haigou.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.abel.service.jsonengine.JSONEessentialP;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class PrepareMYSQL {
	MongoClient mongoClient = null;
	static String dbdri = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost/abeldb";

	static String userid = "db2admin";
	static String passwd = "db2password";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PrepareMYSQL newseeting=new PrepareMYSQL();
		newseeting.user_dbinit();
		newseeting.shoppingcart_dbinit();
	}
	
	
	
	public DB init() throws Exception{
		String dbhost="localhost", dbname="haigou";
		if(JSONEessentialP.get("server_ip")!=null) dbhost=JSONEessentialP.get("server_ip");
		if(JSONEessentialP.get("database")!=null) dbname=JSONEessentialP.get("database");
		mongoClient = new MongoClient(dbhost, 27017);
		DB db = mongoClient.getDB(dbname);
		return db;
		
	}
	
	public DBCollection getAuthCollection(String collec) throws Exception {
		DB db = init();
		
		DBCollection coll = db.getCollection(collec);
		return coll;
	}
	
	public void user_dbinit() throws Exception{

		
		
		
		
		String myTableName = "CREATE TABLE AgentDetail (" 
	            + "idNo INT(64) NOT NULL AUTO_INCREMENT,"  
	            + "initials VARCHAR(2)," 
	            + "agentDate DATE,"  
	            + "agentCount INT(64), "
	            + "PRIMARY KEY(idNo))";  
	    try {
	        Class.forName(dbdri);
	        Connection con = DriverManager.getConnection(url, userid, passwd);
	        Statement statement = con.createStatement();
	        //This line has the issue
	        statement.executeUpdate(myTableName);
	        System.out.println("Table Created");
	    }
	    catch (SQLException e ) {
	        System.out.println("An error has occured on Table Creation");
	        e.printStackTrace();
	    }
	    catch (ClassNotFoundException e) {
	        System.out.println("An Mysql drivers were not found");
	    }	
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
	
}
