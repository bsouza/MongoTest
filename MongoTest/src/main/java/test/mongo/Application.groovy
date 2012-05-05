package test.mongo

import test.mongo.controllers.UserController;
import test.mongo.model.User


class Application {
	
	static void main(def args) {		
		new MongoTest().run()		
	}

}