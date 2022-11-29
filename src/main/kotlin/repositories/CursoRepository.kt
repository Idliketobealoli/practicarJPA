package repositories

import db.HibernateManager
import db.HibernateManager.manager
import models.Curso
import mu.KotlinLogging
import java.util.UUID
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {  }

class CursoRepository: ICRUDRepository<Curso, UUID> {
    override fun findAll(): List<Curso> {
        var result = mutableListOf<Curso>()
        HibernateManager.query {
            val query: TypedQuery<Curso> = manager.createNamedQuery("Curso.findAll", Curso::class.java)
            result = query.resultList
        }
        return result
    }

    override fun findById(id: UUID): Curso? {
        var result: Curso? = null
        HibernateManager.query {
            result = manager.find(Curso::class.java, id)
        }
        return result
    }

    override fun save(t: Curso): Curso {
        HibernateManager.query {
            val curso = manager.find(Curso::class.java, t.id)
            curso?.let { manager.merge(t) }
                .run { manager.persist(t) }
        }
        return t
    }

    override fun delete(t: Curso): Boolean {
        var result = false
        HibernateManager.query {
            val curso = manager.find(Curso::class.java, t.id)
            curso?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}