package test.mongo

import test.mongo.controllers.MongoController
import test.mongo.controllers.UserController
import test.mongo.interfaces.IMongoController
import test.mongo.interfaces.IUserController
import test.mongo.model.User


class MongoTest {
	
	private Scanner console = new Scanner(System.in)
	private IMongoController mongoController = new MongoController()
	private IUserController userController = new UserController()
	
	/**
	 * Método que inicia e controla as funcionalidades da aplicação
	 * @return
	 */
	def run() {
		
		def active = true
		
		while (active) {
			
			// Apresenta as opções da aplicação para o usuário
			printOptions()
			
			// Pega a opção selecionada pelo usuário
			def result = console.nextLine()
			
			switch (result) {
				
				// Adicionar um novo usuário
				case "a":
					execFunction(add)
					break;
					
				// Editar usuário
				case "e":
					execFunction(edit)
					break
				
				// Listar os usuários do banco
				case "l":
					execFunction(list)
					break
					
				// Remove um usuário
				case "r":
					execFunction(delete)
					break
				
				// Deleta a coleção (tabela) usuários
				case "d":
					execFunction(drop)
					break
					
				// Encerrar a aplicação
				case "q":
					active = false
					break
					
				default:
					active = false
					println "Opção inválida!"
					break
			}
			
			// Apenas para dar uma quebra de linha no console
			println ""
		}
		
		println "\n\n Encerrando..."
		
	}
	
	/**
	 * Imprime no console as funcionalidades da aplicação
	 * @return
	 */
	def printOptions = {
		
		println "***********************************"
		println "* MONGODB TEST                    *"
		println "***********************************"
		println "*  Escolha uma das opções abaixo: \n*"
		println "*  a -> Adicionar usuário"
		println "*  e -> Editar usuários"
		println "*  l -> Listar usuários"
		println "*  r -> Remover usuário"
		println "*  d -> Remover coleção"
		println "*  q -> Encerrar"
		println "***********************************"
	}
	
	/**
	 * Adiciona um novo usuário
	 * @return
	 */
	def add = {
		
		println "\n*** Adicionar usuários ********\n"
		
		def name, password, enabled
		
		println "Digite o nome do usuário: "
		name = console.nextLine()
		
		println "Digite a senha do usuário: "
		password = console.nextLine()
		
		println "Novo usuário estará ativo? "
		enabled = (console.nextLine() == "s") ? true : false
		
		userController.add(new User(name: name, password: password, enabled: enabled))
		
		println "Usuario adicionado com sucesso!"
	}
	
	/**
	 * Atualiza os dados de um usuário
	 * @return
	 */
	def edit = {
		
		println "\n*** Editar usuário ************\n"
	
		def id, key, editing = true
		
		println "Digite o id do usuário que deseja alterar: "
		id = console.nextLine()
		
		while(editing) {
		
			println "***********************************"
			println "*  Opções de edição:            \n*"
			println "*  nome "
			println "*  password "
			println "*  enabled "
			println "*  q "
			println "***********************************"
							
			key = console.nextLine()
			
			def lstAttributes = ["nome", "password", "enabled"]
			
			if(lstAttributes.contains(key)) {
				
				println String.format("Digite o novo valor para %s: ", key)
				
				try {
					userController.update(id, key, console.nextLine())
				} catch (Exception e) {
					printErrors(e)
				}
				
			} else {
				
				if(key != "q")
					println "opção inválida"
					
				editing = false;
			}
		}
	}
	
	/**
	 * Lista usuários cadastrados
	 * @return
	 */
	def list = {		
		println "\n*** Listando usuários *********\n"
		
		def users = userController.getAll()
		
		if(users.size > 0)
			users.each { println it }
		else
			println "Não há usuários na base"
	}
	
	/**
	 * Remove um usuário
	 * @return
	 */
	def delete = {		
		println "\n*** Deletar usuários **********\n"
		println "Digite o id do usuário que você deseja deleter: "
	
		userController.delete(console.nextLine()) 		
	}
	
	/**
	 * Dropa a coleção (tabela) de usuários
	 * @return
	 */
	def drop = {		
		mongoController.dropCollection("users")
		println "Coleção removida!"
	}
	
	/**
	 * Executa as funcionalidades do sistema gerenciando o tratamento de erro
	 * @param function
	 * @return
	 */
	def execFunction(function) {		
		
		try {
			function()	
		} catch (Exception e) {
			printErrors(e)
		}
				
	}
	
	/**
	 * Método padrão para imprimir exceções
	 * @param exception
	 * @return
	 */
	def printErrors(exception) {
		println "\n************ ERRO ************"
		println exception.getMessage() + "\n"
		exception.printStackTrace()
	}

}