package models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table

@Entity
@Table(name = "Alumnos")
@NamedQueries(
    NamedQuery(name = "Alumno.findAll", query = "select a from Alumno a"),
    NamedQuery(name = "Alumno.findByNombre",
        query = "select a from Alumno a where a.nombre = ?1")
)
class Alumno() {
    @Id
    //@GeneratedValue
    //@GenericGenerator(
    //    name = "UUID",
    //    strategy = "org.hibernate.id.UUIDGenerator"
    //)
    @Column(name = "id")
    //@Type(type = "uuid-char")
    lateinit var id: UUID

    lateinit var nombre: String

    var nota: Double = 0.0

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = true)
    var curso: Curso? = null

    @Column(name = "created_at")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @CreationTimestamp
    lateinit var createdAt: LocalDateTime

    constructor(
        id: UUID?,
        nombre: String,
        nota: Double,
        curso: Curso?
    ): this() {
        this.id = id ?: UUID.randomUUID()
        this.nombre = nombre
        this.nota = nota
        this.curso = curso
        this.createdAt = LocalDateTime.now()
    }

    override fun toString(): String {
        return "Alumno(id=$id, nombre=$nombre, " +
                "nota=$nota, curso=${curso?.id}, " +
                "createdAt=$createdAt)"
    }
}