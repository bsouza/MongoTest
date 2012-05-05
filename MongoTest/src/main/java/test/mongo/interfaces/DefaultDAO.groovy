package test.mongo.interfaces

interface DefaultDAO<E> {
	
	def add(user)
	def update(user)
	def delete(user)
	def find(id)
	def getAll()

}