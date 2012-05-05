package test.mongo.controllers

import test.mongo.db.ConnectionFactory;
import test.mongo.interfaces.IMongoController

class MongoController implements IMongoController {

	def db = new ConnectionFactory().getDefaultConnection()
	
	@Override
	def dropCollection(name) {
		db.getCollection(name).drop()
	}

}