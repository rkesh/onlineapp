package com.cisco.action.db;

import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class chatCommonDAO {

	public static DB getDb() {
		DB db = null;
		try {
			MongoClient mongoClient = new MongoClient("prrq-jobs", 27017);
			db = mongoClient.getDB("chat");
			System.out.println("Connected to database successfully");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;

	}

	public static String validatelogintoonline(String username, String password)
			throws UnknownHostException {

		DB db = getDb();
		String validuser = "";
		// check if he is valid user and make the status of the user online
		DBCollection registration_coll = db.getCollection("resgistration");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", username);
		whereQuery.put("password", password);
		DBCursor cursor = registration_coll.find(whereQuery);
		if (cursor.hasNext()) {
			validuser = "Y";
			DBCollection onlineuser = db.getCollection("onlineuser");
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.append("$set",
					new BasicDBObject().append("status", "online"));
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("username", username);
			onlineuser.update(searchQuery, updateQuery);

		} else {
			validuser = "N";
		}
		return validuser;

	}

	public static String logoutuser(String username) {
		String logout = "N";
		DB db = getDb();
		DBCollection online_user = db.getCollection("onlineuser");
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set",
				new BasicDBObject().append("status", "offline"));
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append("username", username);
		online_user.update(searchQuery, updateQuery);
		System.out.println("Record udpated successfully");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", username);
		whereQuery.put("status", "offline");
		DBCursor cursor = online_user.find(whereQuery);
		if (cursor.hasNext()) {
			logout = "Y";
		}

		return logout;

	}

	public static List<String> getOnlineUsers(String username) {
		List<String> olusers = new ArrayList<String>();
		DB db = getDb();
		DBCollection onlineuser_coll = db.getCollection("onlineuser");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("status", "online");
		whereQuery.put("username", new BasicDBObject("$ne", username));
		DBCursor cursor = onlineuser_coll.find(whereQuery);
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			System.out.println("Online user::" + obj.get("username"));
			olusers.add((String) obj.get("username"));
		}
		System.out.println("List of avaliable online users for user:"
				+ username + "are :" + olusers);
		return olusers;

	}

	public static String signupchatUser(HashMap<String, String> hm_user) {
		String singupsuccess = "No";
		DB db = getDb();
		DBCollection registration_coll = db.getCollection("resgistration");
		DBCollection onlineuser_coll = db.getCollection("onlineuser");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", hm_user.get("username"));
		DBCursor cursor = registration_coll.find(whereQuery);
		if (cursor.hasNext()) {
			System.out.println("User already exists in the system");
			singupsuccess = "No";
		} else {
			BasicDBObject signup_document = new BasicDBObject();
			signup_document.put("username", hm_user.get("username"));
			signup_document.put("password", hm_user.get("password"));
			signup_document.put("email", hm_user.get("email"));
			signup_document.put("fname", hm_user.get("fname"));
			signup_document.put("lname", hm_user.get("lname"));
			registration_coll.insert(signup_document);
			System.out.println("New user document got updated successfully");
			// insert record in online user table as offline user
			BasicDBObject online_document = new BasicDBObject();
			online_document.put("username", hm_user.get("username"));
			online_document.put("status", "offline");
			onlineuser_coll.insert(online_document);
			System.out
					.println("New user with status as offline added to onlineuser collection");

			singupsuccess = "Yes";
		}
		return singupsuccess;

	}

	public static String getChatMessages(String sender, String reciever) {
		DB db = getDb();
		JSON json = new JSON();
		DBCollection chat_collection = db.getCollection("chatmessages");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("sender", sender);
		whereQuery.put("reciever", reciever);
		BasicDBObject whereQuery2 = new BasicDBObject();
		whereQuery2.put("reciever", sender);
		whereQuery2.put("sender", reciever);
		BasicDBList or = new BasicDBList();
		or.add(whereQuery);
		or.add(whereQuery2);
		DBObject query = new BasicDBObject("$or", or);
		DBCursor cursor = chat_collection.find(query);
		System.out.println(cursor);
		String serialize = json.serialize(cursor);
		System.out.println("Records fetch for the chat" + serialize);
		return serialize;

	}

	public static String insertChatMessages(String sender, String reciever,
			String message) {
		DB db = getDb();
		System.out.println("In Insert message to chat table");
		DBCollection chat_collection = db.getCollection("chatmessages");
		BasicDBObject chat_document = new BasicDBObject();
		chat_document.put("sender", sender);
		chat_document.put("reciever", reciever);
		chat_document.put("message", message);
		chat_collection.insert(chat_document);
		System.out.println("New message got added to datastore");
		return "message added successfully";

	}

}
