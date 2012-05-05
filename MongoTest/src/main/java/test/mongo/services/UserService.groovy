package test.mongo.services

import test.mongo.dao.UserDAO;
import test.mongo.model.User;

class UserService {

    private UserDAO dao = new UserDAO()

    def add(user) {
        dao.add(user)
    }
	
    def delete(id) {
        dao.delete(dao.find(id)[0])
    }
	
    def update(id, key, value) {
        dao.update(dao.find(id).put(key, value))
    }

    def getAll() {			
        def newList = []
        dao.getAll().each { newList.add(newUser(it)) }

        return newList
    }

    private def newUser(item) {
        return [ id: item.get("id"),
                 name: item.get("name"),
                 password: item.get("password"),
                 enabled: item.get("enabled") ] as User
    }
	
}