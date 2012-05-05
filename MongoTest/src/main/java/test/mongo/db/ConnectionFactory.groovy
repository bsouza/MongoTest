package test.mongo.db

import com.mongodb.Mongo;
import com.mongodb.DB;

class ConnectionFactory {	
	
    def user = "root", pass = "adm123"

    def getDefaultConnection() { return getConnection(user, pass) }

    def getConnection(user, password) {
        
        DB db = new Mongo("127.0.0.1").getDB("dbTest")

        if(!db.authenticate(user, password.getChars()))
            throw new Exception("Erro ao autenticar no banco")

        return db;
    }

}