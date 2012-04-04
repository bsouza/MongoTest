package test.mongo.controllers

import java.util.ArrayList;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import test.mongo.dao.UserDAO;
import test.mongo.model.User;

class UserController {

	def dao = new UserDAO()
	
	def getUsers() {
		
		def newList = new ArrayList<User>()
		def users = dao.getList()
		
		def user;
		
		while(users.hasNext()) {
			
			def u = users.next();
			
			user = new User(id: 		u.get("id"),
							name:		u.get("name"),
							password:	u.get("password"),
							enabled:	u.get("enabled"))
			
			newList.add(user) 
		}
		
		return user		
	}
	
}