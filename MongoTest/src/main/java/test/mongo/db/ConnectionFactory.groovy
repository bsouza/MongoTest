package test.mongo.db

import com.mongodb.Mongo;
import com.mongodb.DB;

class ConnectionFactory {	
	
	def user = "root", pass = "adm123"
	
	def getDefaultConnection() {				
		return getConnection(user, pass)
	}
	
	def getConnection(user, password) {				
		
		Mongo mongo = new Mongo("127.0.0.1")		
		DB db = mongo.getDB("dbTest")
		
		def value = db.authenticate(user, password.getChars())
		
		if (value == 0)
			throw new Exception("Erro ao autenticar no banco")
			
		return db;
	}

}