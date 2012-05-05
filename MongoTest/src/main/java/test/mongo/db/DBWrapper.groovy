package test.mongo.db

import com.mongodb.Mongo;
import com.mongodb.DB;

class DBWrapper {
	
    def mongo
    def db

    public DBWrapper(user, password) {				

        mongo = new Mongo("localhost")		
        DB db = mongo.getDB("dbTest")

        /*if (!db.authenticate(user, password.getChars()))
                throw new Exception("Erro ao autenticar no banco")*/
    }

    def getDB() {		
        return db;
    }

}