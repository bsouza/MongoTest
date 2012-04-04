package test.mongo

import test.mongo.controllers.UserController;

class Application {
	
	def static controller = new UserController()
	
	static void main(def args) {
		new Application().run()
	}

	def run() {
		
		def active = true
		
		while (active) {
		
			this.printOptions()	
			
			def result = (byte) System.in.read()
			
			switch ((char) result) {
				
				case 'l':
					this.listUsers()
					break
					
				case 'q':
					active = false
					break
					
			}
		}
		
		println "\n\n Encerrando..."		
	}
	
	def printOptions() {
		
		println "***********************************"
		println "* MONGODB TEST                    *"
		println "***********************************"
		println "*  Escolha uma das opções abaixo: \n"
		println "*  a -> Adicionar usuário"
		println "*  e -> Editar usuários"
		println "*  l -> Listar usuários"
		println "*  r -> Remover usuário"
		println "*  q -> Encerrar"
		println "***********************************"		
	}
	
	def listUsers() {
		
		println "Listando usuários"
		
		controller.getUsers().each {
			
			println it
			
		}
		
	}

}