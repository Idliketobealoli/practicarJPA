package repositories

interface ICRUDRepository<T,ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(t: T): T
    fun delete(t: T): Boolean
}