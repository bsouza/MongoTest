package test.mongo.controllers

import test.mongo.dao.UserDAO;
import test.mongo.interfaces.DefaultDAO
import test.mongo.interfaces.IUserController
import test.mongo.model.User;

class UserController implements IUserController {

	private DefaultDAO dao = new UserDAO()

	@Override	
	def add(user) {
		dao.add(user)
	}
	
	@Override
	def delete(id) {
		dao.delete(dao.find(id)[0])
	}
	
	@Override
	def update(id, key, value) {
		dao.update(dao.find(id).put(key, value))
	}
	
	@Override
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