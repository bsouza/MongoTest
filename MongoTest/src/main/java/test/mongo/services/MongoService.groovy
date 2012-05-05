package test.mongo.services

import test.mongo.db.ConnectionFactory;

class MongoService {

    def db = new ConnectionFactory().getDefaultConnection()
	
    def dropCollection(name) {
    	db.getCollection(name).drop()
    }

}