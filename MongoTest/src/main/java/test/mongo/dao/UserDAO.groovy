package test.mongo.dao

import test.mongo.db.ConnectionFactory;
import test.mongo.interfaces.DefaultDAO

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

class UserDAO implements DefaultDAO {
	
	private final def TABLE_NAME = "users"
	private final def db = new ConnectionFactory().getDefaultConnection()
	
	@Override
	def add(user) {		
		getCollection().insert([id: getNextID(), name: user.getName(), password: user.getPassword(), 
			enabled: user.isEnabled()] as BasicDBObject)
	}
	
	@Override
	def update(user) {
		getCollection().save(user)
	}

	@Override
	def delete(user) {
		getCollection().delete(user)
	}
	
	@Override
	def find(id) {
		return getCollection().find({id: id})
	}
	
	@Override
	def getAll() {
		return getCollection().find()
	}
	
	private def getCollection() {
		return db.getCollection(TABLE_NAME)
	}
	
	private def getNextID() {
		
		def next		
				
		if (getCollection().count > 0) {
			def c = getCollection() as DBCollection
			def f = c.find()
			
			println f.count()
			
			def s = f.sort({id: -1})
			
			if(s.count() > 0)			
				next = s[0].get("id") + 1
			else
				next = 100
		} else
			next = 0;
			
		return next
	}

}