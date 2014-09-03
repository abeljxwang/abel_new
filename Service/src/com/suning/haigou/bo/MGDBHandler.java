package com.suning.haigou.bo;

import java.net.UnknownHostException;

import org.abel.service.jsonengine.JSONEessentialP;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MGDBHandler {

	MongoClient mongoClient = null;

	public DBCollection getCollection() throws UnknownHostException {
		String dbhost = "192.168.1.234", dbname = "haigou";
		if (JSONEessentialP.get("server_ip") != null)
			dbhost = JSONEessentialP.get("server_ip");
		if (JSONEessentialP.get("database") != null)
			dbname = JSONEessentialP.get("database");
		mongoClient = new MongoClient(dbhost, 27017);
		DB db = mongoClient.getDB(dbname);

		DBCollection coll = db.getCollection("UserProfile");
		return coll;
	}

	public DBCollection getCollection(String collection_id) throws UnknownHostException {
		String dbhost = "192.168.1.234", dbname = "haigou";
		if (JSONEessentialP.get("server_ip") != null)
			dbhost = JSONEessentialP.get("server_ip");
		if (JSONEessentialP.get("database") != null)
			dbname = JSONEessentialP.get("database");
		mongoClient = new MongoClient(dbhost, 27017);
		DB db = mongoClient.getDB(dbname);

		DBCollection coll = db.getCollection(collection_id);
		return coll;
	}
	
}
