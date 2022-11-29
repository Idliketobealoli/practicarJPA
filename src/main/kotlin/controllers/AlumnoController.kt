package controllers

import models.Alumno
import repositories.AlumnoRepository
import java.util.*

object AlumnoController {
    val repo = AlumnoRepository()

    fun findAll(): String {
        return repo.findAll().toString()
    }

    fun findById(id: UUID): String {
        return repo.findById(id)?.toString() ?: "Alumno with id $id not found"
    }

    fun save(alumno: Alumno): String {
        return repo.save(alumno).toString()
    }

    fun delete(alumno: Alumno): String {
        val result = repo.delete(alumno)
        return if (result) alumno.toString()
        else "unable to delete ${alumno.nombre}"
    }
}