package repositories

import db.HibernateManager
import db.HibernateManager.manager
import models.Alumno
import mu.KotlinLogging
import java.util.UUID
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {  }

class AlumnoRepository: ICRUDRepository<Alumno, UUID> {
    override fun findAll(): List<Alumno> {
        logger.debug { "findAll" }
        var result = mutableListOf<Alumno>()
        HibernateManager.query {
            val query: TypedQuery<Alumno> = manager.createNamedQuery("Alumno.findAll", Alumno::class.java)
            result = query.resultList
        }
        return result
    }

    override fun findById(id: UUID): Alumno? {
        logger.debug { "findById with id: $id" }
        var result:Alumno? = null
        HibernateManager.query {
            result = manager.find(Alumno::class.java, id)
        }
        return result
    }

    override fun save(t: Alumno): Alumno {
        logger.debug { "create ${t.nombre}" }
        HibernateManager.query {
            val alumno = manager.find(Alumno::class.java, t.id)
            alumno?.let { manager.merge(t) }
                .run { manager.persist(t) }
        }
        return t
    }

    override fun delete(t: Alumno): Boolean {
        var result = false
        logger.debug { "delete ${t.nombre}" }
        HibernateManager.query {
            val alumno = manager.find(Alumno::class.java, t.id)
            alumno?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}