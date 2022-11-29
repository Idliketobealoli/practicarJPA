package controllers

import models.Curso
import repositories.CursoRepository
import java.util.*

object CursoController {
    val repo = CursoRepository()

    fun findAll(): String {
        return repo.findAll().toString()
    }

    fun findById(id: UUID): String {
        return repo.findById(id)?.toString() ?: "Curso with id $id not found"
    }

    fun save(curso: Curso): String {
        return repo.save(curso).toString()
    }

    fun delete(curso: Curso): String {
        val result = repo.delete(curso)
        return if (result) curso.toString()
        else "Unable to delete ${curso.nombre}"
    }
}