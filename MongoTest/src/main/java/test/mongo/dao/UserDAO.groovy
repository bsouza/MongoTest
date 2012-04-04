package test.mongo.dao

import test.mongo.db.DBWrapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

class UserDAO {
	
	def TABLE_NAME = "users"
	def wrapper
	
	public UserDAO() {		
		wrapper = new DBWrapper("root", "adm123").getDb()			
	}	
	
	def insert(user) {
		
		def collection = wrapper.getCollection(TABLE_NAME) 
		
		def dbUser = new BasicDBObject()
		
		dbUser.put("id", collection.find({$max: {"id"} }) );
		dbUser.put("name", user.getName)
		dbUser.put("password", user.getPassword)
		dbUser.put("enabled", dbUser.isEnabled)
		
		collection.insert(dbUser)
	}
	
	def getList() {
		return wrapper.getCollection(TABLE_NAME)
	}

}