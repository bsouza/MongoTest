package test.mongo.interfaces

interface IUserController {

	def add(user)
	def delete(id)
	def update(id, key, value)
	def getAll()
	
}