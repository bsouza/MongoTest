package test.mongo.model

import java.io.Serializable;

class User implements Serializable {
		
    private Integer id
    private String name
    private String password
    private Boolean enabled	

    def getId() { return id }

    def getName() { return name }

    def getPassword() { return password }

    def isEnabled() { return enabled }

    @Override
    public String toString() {
            return String.format("ID: %s, NAME: %s, PASSWORD: %s, ENABLED: %s", 
                id, name, password, (enabled) ? "true" : "false");
    }
	
}