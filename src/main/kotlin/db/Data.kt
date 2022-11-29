package db

import models.Alumno
import models.Curso
import java.util.*

val a1 = Alumno(
    id = null,
    nombre = "Loli R.",
    nota = 6.9,
    curso = null
)
val a2 = Alumno(
    id = null,
    nombre = "Adolfo H.",
    nota = 9.9,
    curso = null
)
val a3 = Alumno(
    id = null,
    nombre = "Paco F.",
    nota = 1.0,
    curso = null
)

val c1 = Curso(
    id = null,
    nombre = "DAM",
    alumnos = null
)

val c2 = Curso(
    id = null,
    nombre = "DAW",
    alumnos = null
)

fun getAlumnos(): List<Alumno> {
    a1.curso = c1
    a2.curso = c1
    a3.curso = c2

    return listOf(a1,a2,a3)
}

fun getCursos(): List<Curso> {
    c1.alumnos = mutableListOf(a1, a2)
    c2.alumnos = mutableListOf(a3)

    return listOf(c1)
}