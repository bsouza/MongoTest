package test.mongo.model

import com.mongodb.BasicDBObject;

class User {
		
	private Integer id
	private String name
	private String password
	private Boolean enabled	
	
	def getId() {
		return id
	}
	
	def getName() {
		return name
	}
	
	def getPassword() {
		return password
	}
	
	def isEnabled() {
		return enabled
	}

	@Override
	public String toString() {
		return String.format("ID: %i, NAME: %s, PASSWORD: %s, ENABLED: %i", id, name, password, enabled);
	}
	
}